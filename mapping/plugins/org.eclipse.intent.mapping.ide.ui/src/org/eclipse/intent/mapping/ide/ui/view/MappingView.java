/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation and/or initial documentation
 *    ...
 *******************************************************************************/
package org.eclipse.intent.mapping.ide.ui.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.intent.mapping.MappingUtils;
import org.eclipse.intent.mapping.base.IBase;
import org.eclipse.intent.mapping.base.IBaseListener;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.ILocationDescriptor;
import org.eclipse.intent.mapping.base.ILocationListener;
import org.eclipse.intent.mapping.base.IReport;
import org.eclipse.intent.mapping.ide.ILocationMarker;
import org.eclipse.intent.mapping.ide.IdeMappingUtils;
import org.eclipse.intent.mapping.ide.ui.Activator;
import org.eclipse.intent.mapping.ide.ui.UiIdeMappingUtils;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;

/**
 * Mapping {@link ViewPart}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MappingView extends ViewPart {

	/**
	 * Selection changed Listener for {@link IBase} combo.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	private final class BaseComboSelectionChangedListener implements ISelectionChangedListener {

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
		 */
		public void selectionChanged(SelectionChangedEvent event) {
			final IBase oldBase = IdeMappingUtils.getCurrentBase();
			final Object selected = ((IStructuredSelection)event.getSelection()).getFirstElement();
			final IBase newBase;
			if (selected == MappingBaseRegistryContentProvider.NO_VALUE) {
				newBase = null;
			} else {
				newBase = (IBase)selected;
			}
			if (oldBase != null) {
				for (ILocation child : oldBase.getContents()) {
					deleteLocation(child);
				}
				try {
					oldBase.save();
				} catch (IOException e) {
					if (Activator.getDefault() != null) {
						Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
								"unable to save base " + oldBase.getName(), e));
					}
				}
			}
			IdeMappingUtils.setCurrentBase(newBase);
			if (newBase != null) {
				for (ILocation child : newBase.getContents()) {
					addLocation(child);
				}
			}
		}
	}

	/**
	 * Adds and removes location markers according to edited locations and changes in {@link IBase}.
	 * 
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	private final class MarkerBaseListener extends ILocationListener.Stub implements IBaseListener, ILocationListener {

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#contentsAdded(org.eclipse.intent.mapping.base.ILocation)
		 */
		public void contentsAdded(ILocation location) {
			location.addListener(this);
			if (isOpenedInEditor(location)) {
				IdeMappingUtils.getOrCreateMarker(location);
			}
		}

		/**
		 * Tells if the given {@link ILocation} is opened in an editor.
		 * 
		 * @param location
		 *            the {@link ILocation} to check
		 * @return <code>true</code> if the given {@link ILocation} is opened in an editor, <code>false</code>
		 *         otherwise
		 */
		private boolean isOpenedInEditor(ILocation location) {
			final boolean res;

			// we assume all opened location are directly contained by the base
			if (location.getContainer() instanceof ILocation) {
				res = isOpenedInEditor((ILocation)location.getContainer());
			} else {
				res = editedLocations.contains(location);
			}

			return res;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#contentsRemoved(org.eclipse.intent.mapping.base.ILocation)
		 */
		public void contentsRemoved(ILocation location) {
			location.removeListener(this);
			IdeMappingUtils.deleteMarker(location);
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#nameChanged(java.lang.String, java.lang.String)
		 */
		public void nameChanged(String oldName, String newName) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#reportAdded(org.eclipse.intent.mapping.base.IReport)
		 */
		public void reportAdded(IReport report) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#reportRemoved(org.eclipse.intent.mapping.base.IReport)
		 */
		public void reportRemoved(IReport report) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#containerProviderAdded(java.lang.String)
		 */
		public void containerProviderAdded(String provider) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#containerProviderRemoved(java.lang.String)
		 */
		public void containerProviderRemoved(String provider) {
			// nothing to do here
		}

	}

	/**
	 * Listen to {@link org.eclipse.ui.IEditorPart IEditorPart}.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	private final class EditorPartListener implements IWindowListener, IPageListener, IPartListener2 {

		/**
		 * Unable to create location mesage.
		 */
		private static final String UNABLE_TO_CREATE_LOCATION = "unable to create location";

		/**
		 * The array of {@link Viewer}.
		 */
		private final Viewer[] viewers;

		/**
		 * Constructor.
		 * 
		 * @param viewers
		 *            the array of {@link Viewer}
		 */
		EditorPartListener(Viewer... viewers) {
			this.viewers = viewers;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IWindowListener#windowActivated(org.eclipse.ui.IWorkbenchWindow)
		 */
		public void windowActivated(IWorkbenchWindow window) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IWindowListener#windowDeactivated(org.eclipse.ui.IWorkbenchWindow)
		 */
		public void windowDeactivated(IWorkbenchWindow window) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IWindowListener#windowClosed(org.eclipse.ui.IWorkbenchWindow)
		 */
		public void windowClosed(IWorkbenchWindow window) {
			window.removePageListener(this);
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IWindowListener#windowOpened(org.eclipse.ui.IWorkbenchWindow)
		 */
		public void windowOpened(IWorkbenchWindow window) {
			window.addPageListener(this);
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPageListener#pageActivated(org.eclipse.ui.IWorkbenchPage)
		 */
		public void pageActivated(IWorkbenchPage page) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPageListener#pageClosed(org.eclipse.ui.IWorkbenchPage)
		 */
		public void pageClosed(IWorkbenchPage page) {
			page.removePartListener(this);
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPageListener#pageOpened(org.eclipse.ui.IWorkbenchPage)
		 */
		public void pageOpened(IWorkbenchPage page) {
			page.addPartListener(this);
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partActivated(IWorkbenchPartReference partRef) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partBroughtToTop(IWorkbenchPartReference partRef) {
			final IWorkbenchPart part = partRef.getPart(false);
			if (part instanceof IEditorPart) {
				setInput((IEditorPart)part);
			}
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partClosed(IWorkbenchPartReference partRef) {
			final IWorkbenchPart part = partRef.getPart(false);
			if (part instanceof IEditorPart) {
				final IBase currentBase = IdeMappingUtils.getCurrentBase();
				createLocationMarker(currentBase, (IEditorPart)part);
			}
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partDeactivated(IWorkbenchPartReference partRef) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partOpened(IWorkbenchPartReference partRef) {
			final IWorkbenchPart part = partRef.getPart(false);
			if (part instanceof IEditorPart) {
				setInput((IEditorPart)part);
				final IBase currentBase = IdeMappingUtils.getCurrentBase();
				createLocationMarker(currentBase, (IEditorPart)part);
			}
		}

		/**
		 * Sets input according to the given {@link IEditorPart}.
		 * 
		 * @param editorPart
		 *            the {@link IEditorPart}
		 */
		public void setInput(final IEditorPart editorPart) {
			if (editorPart == null) {
				for (Viewer viewer : viewers) {
					viewer.setInput(null);
				}
			} else {
				final IEditorInput input = editorPart.getEditorInput();
				final IFile file = UiIdeMappingUtils.getFile(input);
				final IBase base = IdeMappingUtils.getCurrentBase();
				if (base != null) {
					try {
						// TODO get the location and add a listener to be notified when the location is
						// created
						final ILocation location = MappingUtils.getConnectorRegistry().getOrCreateLocation(
								base, file);
						for (Viewer viewer : viewers) {
							viewer.setInput(location);
						}
					} catch (InstantiationException e) {
						Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
								UNABLE_TO_CREATE_LOCATION, e));
					} catch (IllegalAccessException e) {
						Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
								UNABLE_TO_CREATE_LOCATION, e));
					} catch (ClassNotFoundException e) {
						Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
								UNABLE_TO_CREATE_LOCATION, e));
					}
				}
			}
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partHidden(IWorkbenchPartReference partRef) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partVisible(IWorkbenchPartReference partRef) {
			// nothing to do here
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partInputChanged(IWorkbenchPartReference partRef) {
			final IWorkbenchPart part = partRef.getPart(false);
			if (part instanceof IEditorPart) {
				setInput((IEditorPart)part);
				final IBase currentBase = IdeMappingUtils.getCurrentBase();
				createLocationMarker(currentBase, (IEditorPart)part);
			}
		}

	}

	/**
	 * The view ID.
	 */
	public static final String ID = "org.eclipse.intent.mapping.ide.ui.view.MappingView"; //$NON-NLS-1$

	/**
	 * Default width.
	 */
	private static final int WIDTH = 300;

	/**
	 * Source column label.
	 */
	private static final String SOURCE_LABEL = "Master source";

	/**
	 * Target column label.
	 */
	private static final String TARGET_LABEL = "Slave target";

	/**
	 * The {@link SelectionProviderIntermediate} delegating to {@link org.eclipse.jface.viewers.Viewer Viewer}
	 * .
	 */
	private final SelectionProviderIntermediate selectionProvider = new SelectionProviderIntermediate();

	/**
	 * The {@link ISelectionListener} updating selection tree viewer input.
	 */
	private ISelectionListener selectionListener;

	/**
	 * The {@link EditorPartListener}.
	 */
	private EditorPartListener editorPartListener;

	/**
	 * The {@link List} of edited {@link ILocation}.
	 */
	private final List<ILocation> editedLocations = new ArrayList<ILocation>();

	/**
	 * The listener maintaining the {@link ILocation} to {@link org.eclipse.core.resources.IMarker IMarker}
	 * mapping.
	 */
	private final MarkerBaseListener markerBaseListener = new MarkerBaseListener();

	/**
	 * The {@link MenuManager}.
	 */
	private final MenuManager menuManager = new MenuManager();

	/**
	 * {@link List} of created {@link Menu}.
	 */
	private final List<Menu> menus = new ArrayList<Menu>();

	/**
	 * Constructor.
	 */
	public MappingView() {
	}

	@Override
	public void createPartControl(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		Composite headerComposite = new Composite(composite, SWT.NONE);
		headerComposite.setLayout(new GridLayout(3, false));
		headerComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		final ComboViewer mappingBaseCombo = addMappingBaseCombo(headerComposite);

		TabFolder bodyTabFolder = new TabFolder(composite, SWT.NONE);
		bodyTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		addSelectionTabItem(bodyTabFolder, mappingBaseCombo);
		addDocumentTabItem(bodyTabFolder, mappingBaseCombo);
		addReportTabItem(bodyTabFolder, mappingBaseCombo);

		final Button containerProviderButton = new Button(headerComposite, SWT.NONE);
		containerProviderButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		containerProviderButton.setText("Select Container Providers");
		containerProviderButton.setEnabled(IdeMappingUtils.getCurrentBase() != null);
		mappingBaseCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				containerProviderButton.setEnabled(IdeMappingUtils.getCurrentBase() != null);
			}
		});
		containerProviderButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				final ContainerProviderSelectionDialog dialog = new ContainerProviderSelectionDialog(getSite()
						.getShell(), IdeMappingUtils.getCurrentBase());
				dialog.open();
			}

		});

		getSite().setSelectionProvider(selectionProvider);

		createActions();
		initializeToolBar();

		getSite().registerContextMenu(menuManager, selectionProvider);
	}

	/**
	 * Add the mapping base {@link ComboViewer} to the given header {@link Composite}.
	 * 
	 * @param headerComposite
	 *            the header {@link Composite}
	 * @return the mapping base {@link ComboViewer}
	 */
	private ComboViewer addMappingBaseCombo(Composite headerComposite) {

		final Label selectMappingBaseLabel = new Label(headerComposite, SWT.NONE);
		selectMappingBaseLabel.setToolTipText("Select a mapping base.");
		selectMappingBaseLabel.setText("Mapping base:");
		final ComboViewer mappingCombo = new ComboViewer(headerComposite, SWT.READ_ONLY);
		Combo combo = mappingCombo.getCombo();
		combo.setToolTipText("Select the mapping base to use.");
		mappingCombo.setContentProvider(new MappingBaseRegistryContentProvider());
		mappingCombo.setLabelProvider(new MappingLabelProvider(MappingLabelProvider.SOURCE));
		mappingCombo.setComparator(new ViewerComparator());
		mappingCombo.setInput(MappingUtils.getMappingRegistry());
		mappingCombo.addSelectionChangedListener(new BaseComboSelectionChangedListener());

		return mappingCombo;
	}

	/**
	 * Adds the selection {@link TabItem} to the given body {@link TabFolder}.
	 * 
	 * @param bodyTabFolder
	 *            the body {@link TabFolder}
	 * @param mappingBaseCombo
	 *            the mapping base {@link ComboViewer}
	 */
	private void addSelectionTabItem(TabFolder bodyTabFolder, ComboViewer mappingBaseCombo) {
		TabItem selectionTabItem = new TabItem(bodyTabFolder, SWT.NONE);
		selectionTabItem.setText("Selection");

		Composite treeComposite = new Composite(bodyTabFolder, SWT.NONE);
		selectionTabItem.setControl(treeComposite);
		treeComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(treeComposite, SWT.NONE);

		final Composite referencingComposite = new Composite(sashForm, SWT.NONE);
		referencingComposite.setLayout(new GridLayout(1, false));

		final Label referencingLabel = new Label(referencingComposite, SWT.CENTER);
		referencingLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		referencingLabel.setToolTipText("Locations referencing the current selection via a Link.");
		referencingLabel.setText("Referencing Locations");

		final FilteredTree referencingTree = new FilteredTree(referencingComposite, SWT.MULTI | SWT.BORDER,
				new PatternFilter(), false);
		referencingTree.getViewer().getTree().addListener(SWT.KeyDown, new MappingKeyUpListener());
		referencingTree.getViewer().getTree().addListener(SWT.MouseDoubleClick,
				new ShowLocationDoubleClickListener(referencingTree.getViewer().getTree()));
		referencingTree.getViewer().setContentProvider(new LinkedLocationContentProvider(false,
				LinkedLocationContentProvider.SOURCE, false));
		referencingTree.getViewer().setLabelProvider(new MappingLabelProvider(MappingLabelProvider.SOURCE));
		referencingTree.getViewer().getControl().addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(null);
			}

			public void focusGained(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(referencingTree.getViewer());
			}
		});
		Menu menu = menuManager.createContextMenu(referencingTree.getViewer().getControl());
		menus.add(menu);
		referencingTree.getViewer().getControl().setMenu(menu);

		final Composite referencedComposite = new Composite(sashForm, SWT.NONE);
		referencedComposite.setLayout(new GridLayout(1, false));

		final Label referencedLabel = new Label(referencedComposite, SWT.CENTER);
		referencedLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		referencedLabel.setToolTipText("Locations referenced by the current selection via a Link.");
		referencedLabel.setText("Referenced Locations");

		final FilteredTree referencedTree = new FilteredTree(referencedComposite, SWT.MULTI | SWT.BORDER,
				new PatternFilter(), false);
		referencedTree.getViewer().getTree().addListener(SWT.KeyUp, new MappingKeyUpListener());
		referencedTree.getViewer().getTree().addListener(SWT.MouseDoubleClick,
				new ShowLocationDoubleClickListener(referencedTree.getViewer().getTree()));
		referencedTree.getViewer().setContentProvider(new LinkedLocationContentProvider(false,
				LinkedLocationContentProvider.TARGET, false));
		referencedTree.getViewer().setLabelProvider(new MappingLabelProvider(MappingLabelProvider.SOURCE));
		referencedTree.getViewer().getControl().addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(null);
			}

			public void focusGained(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(referencedTree.getViewer());
			}
		});
		menu = menuManager.createContextMenu(referencedTree.getViewer().getControl());
		menus.add(menu);
		referencedTree.getViewer().getControl().setMenu(menu);

		mappingBaseCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				final IBase base;
				final Object selected = ((IStructuredSelection)event.getSelection()).getFirstElement();
				if (selected == MappingBaseRegistryContentProvider.NO_VALUE) {
					base = null;
				} else {
					base = (IBase)selected;
				}
				final ILocation location = (ILocation)referencedTree.getViewer().getInput();
				if (location != null && areSameBase(MappingUtils.getBase(location), base)) {
					referencingTree.getViewer().setInput(null);
					referencedTree.getViewer().setInput(null);
				}
			}
		});

		selectionListener = new ISelectionListener() {

			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				final IBase currentBase = IdeMappingUtils.getCurrentBase();
				if (part != MappingView.this && currentBase != null) {
					final ILocationDescriptor locationDescriptor = IdeMappingUtils.adapt(selection,
							ILocationDescriptor.class);
					if (locationDescriptor != null && locationDescriptor.exists()) {
						final ILocation location = locationDescriptor.getLocation();
						if (location != null && areSameBase(currentBase, MappingUtils.getBase(location))) {
							referencingTree.getViewer().setInput(location);
							referencedTree.getViewer().setInput(location);
						}
					}
				}
			}
		};

		referencingTree.getViewer().getTree().addListener(SWT.MouseDoubleClick,
				new ShowLocationDoubleClickListener(referencingTree.getViewer().getTree()));
		referencedTree.getViewer().getTree().addListener(SWT.MouseDoubleClick,
				new ShowLocationDoubleClickListener(referencedTree.getViewer().getTree()));

		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);

		sashForm.setWeights(new int[] {1, 1 });
	}

	/**
	 * Adds the document {@link TabItem} to the given body {@link TabFolder}.
	 * 
	 * @param bodyTabFolder
	 *            the body {@link TabFolder}
	 * @param mappingBaseCombo
	 *            the mapping base {@link ComboViewer}
	 */
	private void addDocumentTabItem(TabFolder bodyTabFolder, ComboViewer mappingBaseCombo) {
		TabItem selectionTabItem = new TabItem(bodyTabFolder, SWT.NONE);
		selectionTabItem.setText("Document");

		Composite treeComposite = new Composite(bodyTabFolder, SWT.NONE);
		selectionTabItem.setControl(treeComposite);
		treeComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(treeComposite, SWT.NONE);

		final Composite referencingComposite = new Composite(sashForm, SWT.NONE);
		referencingComposite.setLayout(new GridLayout(1, false));

		final Label referencingLabel = new Label(referencingComposite, SWT.CENTER);
		referencingLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		referencingLabel.setToolTipText("Links from other documents to the current document.");
		referencingLabel.setText("Incoming Links");

		final FilteredTree referencingTree = new FilteredTree(referencingComposite, SWT.MULTI | SWT.BORDER,
				new PatternFilter(), false);
		referencingTree.getViewer().getTree().addListener(SWT.KeyDown, new MappingKeyUpListener());
		referencingTree.getViewer().setContentProvider(new LinkedLocationContentProvider(true,
				LinkedLocationContentProvider.SOURCE, true));
		referencingTree.getViewer().getTree().addListener(SWT.MouseDoubleClick,
				new ShowLocationDoubleClickListener(referencingTree.getViewer().getTree()));

		referencingTree.getViewer().getTree().setHeaderVisible(true);
		TreeViewerColumn referencingTreeSourceColumn = new TreeViewerColumn(referencingTree.getViewer(),
				SWT.NONE);
		referencingTreeSourceColumn.getColumn().setData(LinkedLocationContentProvider.SOURCE);
		referencingTreeSourceColumn.getColumn().setResizable(true);
		referencingTreeSourceColumn.getColumn().setText(SOURCE_LABEL);
		referencingTreeSourceColumn.getColumn().setWidth(WIDTH);
		referencingTreeSourceColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(
				new MappingLabelProvider(MappingLabelProvider.SOURCE)));

		TreeViewerColumn referencingTreeTargetColumn = new TreeViewerColumn(referencingTree.getViewer(),
				SWT.NONE);
		referencingTreeTargetColumn.getColumn().setData(LinkedLocationContentProvider.TARGET);
		referencingTreeTargetColumn.getColumn().setResizable(true);
		referencingTreeTargetColumn.getColumn().setText(TARGET_LABEL);
		referencingTreeTargetColumn.getColumn().setWidth(WIDTH);
		referencingTreeTargetColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(
				new MappingLabelProvider(MappingLabelProvider.TARGET)));

		referencingTree.getViewer().getControl().addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(null);
			}

			public void focusGained(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(referencingTree.getViewer());
			}
		});
		Menu menu = menuManager.createContextMenu(referencingTree.getViewer().getControl());
		menus.add(menu);
		referencingTree.getViewer().getControl().setMenu(menu);

		final Composite referencedComposite = new Composite(sashForm, SWT.NONE);
		referencedComposite.setLayout(new GridLayout(1, false));

		final Label referencedLabel = new Label(referencedComposite, SWT.CENTER);
		referencedLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		referencedLabel.setToolTipText("Links from the current document to other documents.");
		referencedLabel.setText("Outgoing Links");

		final FilteredTree referencedTree = new FilteredTree(referencedComposite, SWT.MULTI | SWT.BORDER,
				new PatternFilter(), false);
		referencedTree.getViewer().getTree().addListener(SWT.KeyUp, new MappingKeyUpListener());
		referencedTree.getViewer().setContentProvider(new LinkedLocationContentProvider(true,
				LinkedLocationContentProvider.TARGET, true));
		referencedTree.getViewer().getTree().addListener(SWT.MouseDoubleClick,
				new ShowLocationDoubleClickListener(referencedTree.getViewer().getTree()));

		referencedTree.getViewer().getTree().setHeaderVisible(true);
		TreeViewerColumn referencedTreeSourceColumn = new TreeViewerColumn(referencedTree.getViewer(),
				SWT.NONE);
		referencedTreeSourceColumn.getColumn().setResizable(true);
		referencedTreeSourceColumn.getColumn().setText(SOURCE_LABEL);
		referencedTreeSourceColumn.getColumn().setWidth(WIDTH);
		referencedTreeSourceColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(
				new MappingLabelProvider(MappingLabelProvider.SOURCE)));

		TreeViewerColumn referencedTreeTargetColumn = new TreeViewerColumn(referencedTree.getViewer(),
				SWT.NONE);
		referencedTreeTargetColumn.getColumn().setResizable(true);
		referencedTreeTargetColumn.getColumn().setText(TARGET_LABEL);
		referencedTreeTargetColumn.getColumn().setWidth(WIDTH);
		referencedTreeTargetColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(
				new MappingLabelProvider(MappingLabelProvider.TARGET)));

		referencedTree.getViewer().getControl().addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(null);
			}

			public void focusGained(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(referencedTree.getViewer());
			}
		});
		menu = menuManager.createContextMenu(referencedTree.getViewer().getControl());
		menus.add(menu);
		referencedTree.getViewer().getControl().setMenu(menu);

		mappingBaseCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				final Object selected = ((IStructuredSelection)event.getSelection()).getFirstElement();
				if (selected == MappingBaseRegistryContentProvider.NO_VALUE) {
					editorPartListener.setInput(null);
				} else {
					final IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().getActiveEditor();
					if (activeEditor != null && editorPartListener != null) {
						editorPartListener.setInput(activeEditor);
					}
				}
			}
		});

		editorPartListener = new EditorPartListener(referencingTree.getViewer(), referencedTree.getViewer());
		for (

		IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for (IWorkbenchPage page : window.getPages()) {
				final IEditorPart activeEditor = page.getActiveEditor();
				if (activeEditor != null) {
					editorPartListener.setInput(activeEditor);
				}
				page.addPartListener(editorPartListener);
			}
			window.addPageListener(editorPartListener);
		}
		PlatformUI.getWorkbench().addWindowListener(editorPartListener);

		sashForm.setWeights(new int[] {1, 1 });
	}

	/**
	 * Adds the report {@link TabItem} to the given body {@link TabFolder}.
	 * 
	 * @param bodyTabFolder
	 *            the body {@link TabFolder}
	 * @param mappingBaseCombo
	 *            the mapping base {@link ComboViewer}
	 */
	private void addReportTabItem(TabFolder bodyTabFolder, ComboViewer mappingBaseCombo) {
		TabItem selectionTabItem = new TabItem(bodyTabFolder, SWT.NONE);
		selectionTabItem.setText("Report");

		Composite treeComposite = new Composite(bodyTabFolder, SWT.NONE);
		selectionTabItem.setControl(treeComposite);
		treeComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		final FilteredTree reportTree = new FilteredTree(treeComposite, SWT.MULTI | SWT.BORDER,
				new PatternFilter(), false);
		reportTree.getViewer().getTree().addListener(SWT.KeyDown, new MappingKeyUpListener());
		reportTree.getViewer().getTree().addListener(SWT.MouseDoubleClick,
				new ShowLocationDoubleClickListener(reportTree.getViewer().getTree()));
		reportTree.getViewer().setContentProvider(new SynchronizationLocationContentProvider());

		reportTree.getViewer().getTree().setHeaderVisible(true);
		TreeViewerColumn linkTreeSourceColumn = new TreeViewerColumn(reportTree.getViewer(), SWT.NONE);
		linkTreeSourceColumn.getColumn().setResizable(true);
		linkTreeSourceColumn.getColumn().setText(SOURCE_LABEL);
		linkTreeSourceColumn.getColumn().setWidth(WIDTH);
		linkTreeSourceColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new MappingLabelProvider(
				MappingLabelProvider.SOURCE)));

		TreeViewerColumn linkTreeTargetColumn = new TreeViewerColumn(reportTree.getViewer(), SWT.NONE);
		linkTreeTargetColumn.getColumn().setResizable(true);
		linkTreeTargetColumn.getColumn().setText(TARGET_LABEL);
		linkTreeTargetColumn.getColumn().setWidth(WIDTH);
		linkTreeTargetColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new MappingLabelProvider(
				MappingLabelProvider.TARGET)));

		reportTree.getViewer().getControl().addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(null);
			}

			public void focusGained(FocusEvent e) {
				selectionProvider.setSelectionProviderDelegate(reportTree.getViewer());
			}
		});
		final Menu menu = menuManager.createContextMenu(reportTree.getViewer().getControl());
		menus.add(menu);
		reportTree.getViewer().getControl().setMenu(menu);

		mappingBaseCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				final IBase base;
				final Object selected = ((IStructuredSelection)event.getSelection()).getFirstElement();
				if (selected == MappingBaseRegistryContentProvider.NO_VALUE) {
					base = null;
				} else {
					base = (IBase)selected;
				}
				reportTree.getViewer().setInput(base);
			}
		});

	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void dispose() {
		super.dispose();
		if (selectionListener != null) {
			getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
			selectionListener = null;
		}

		for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for (IWorkbenchPage page : window.getPages()) {
				page.removePartListener(editorPartListener);
			}
			window.removePageListener(editorPartListener);
		}
		PlatformUI.getWorkbench().removeWindowListener(editorPartListener);

		final IBase oldBase = IdeMappingUtils.getCurrentBase();
		if (oldBase != null) {
			try {
				oldBase.save();
			} catch (IOException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						"unable to save base " + oldBase.getName(), e));
			}
		}
		IdeMappingUtils.setCurrentBase(null);
		for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for (IWorkbenchPage page : window.getPages()) {
				for (IEditorReference editorRef : page.getEditorReferences()) {
					final IEditorPart editorPart = editorRef.getEditor(false);
					if (editorPart != null) {
						clearLocationMarker(oldBase, editorPart);
					}
				}
			}
		}

		getSite().setSelectionProvider(null);

		for (Menu menu : menus) {
			menu.dispose();
		}
		menuManager.dispose();
	}

	/**
	 * Tells if the two given {@link IBase} are the same.
	 * 
	 * @param firstBase
	 *            the first {@link IBase}
	 * @param secondBase
	 *            the second {@link IBase}
	 * @return <code>true</code> if the two given {@link IBase} are the same, <code>false</code> otherwise
	 */
	private boolean areSameBase(IBase firstBase, IBase secondBase) {
		// TODO change this when we work on same instances of IBase
		final boolean res;

		if (firstBase == null) {
			if (secondBase == null) {
				res = true;
			} else {
				res = false;
			}
		} else {
			if (secondBase == null) {
				res = false;
			} else {
				res = firstBase.getName().equals(secondBase.getName());
			}
		}

		return res;
	}

	/**
	 * Clears all location markers for the given {@link IEditorPart}.
	 * 
	 * @param base
	 *            the {@link IBase}
	 * @param part
	 *            the {@link IEditorPart}
	 */
	private void clearLocationMarker(IBase base, IEditorPart part) {
		final IEditorInput editorInput = part.getEditorInput();

		if (editorInput != null) {
			final IFile file = UiIdeMappingUtils.getFile(editorInput);
			if (file != null && base != null) {
				try {
					file.deleteMarkers(ILocationMarker.LOCATION_ID, true, IResource.DEPTH_INFINITE);
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
							"unable to clear all location markers for " + file.getFullPath().toString(), e));
				}
				final ILocation fileLocation = MappingUtils.getConnectorRegistry().getLocation(base, file);
				if (fileLocation != null) {
					editedLocations.remove(fileLocation);
					removeLocation(fileLocation);
				}
			}
		}
	}

	/**
	 * Removes {@link ILocation} to {@link org.eclipse.core.resources.IMarker IMarker} mapping recursively.
	 * 
	 * @param location
	 *            the {@link ILocation} to remove from the mapping
	 */
	private void removeLocation(ILocation location) {
		IdeMappingUtils.removeMarker(location);
		location.removeListener(markerBaseListener);
		for (ILocation child : location.getContents()) {
			removeLocation(child);
		}
	}

	/**
	 * Removes {@link ILocation} to {@link org.eclipse.core.resources.IMarker IMarker} mapping recursively and
	 * {@link org.eclipse.core.resources.IMarker#delete() deletes} the
	 * {@link org.eclipse.core.resources.IMarker IMarker}.
	 * 
	 * @param location
	 *            the {@link ILocation} to remove from the mapping
	 */
	private void deleteLocation(ILocation location) {
		IdeMappingUtils.deleteMarker(location);
		location.removeListener(markerBaseListener);
		for (ILocation child : location.getContents()) {
			removeLocation(child);
		}
	}

	/**
	 * Creates markers for the given {@link ILocation} and its {@link ILocation#getContents() contained}
	 * {@link ILocation}.
	 * 
	 * @param location
	 *            the {@link ILocation}
	 */
	private void addLocation(ILocation location) {
		IdeMappingUtils.getOrCreateMarker(location);
		location.addListener(markerBaseListener);
		for (ILocation child : location.getContents()) {
			addLocation(child);
		}
	}

	/**
	 * Creates all location markers for the given {@link IEditorPart}.
	 * 
	 * @param base
	 *            the IBase
	 * @param part
	 *            the {@link IEditorPart}
	 */
	private void createLocationMarker(IBase base, IEditorPart part) {
		final IEditorInput editorInput = part.getEditorInput();

		if (editorInput != null) {
			final IFile file = UiIdeMappingUtils.getFile(editorInput);
			if (file != null && base != null) {
				final ILocation fileLocation = MappingUtils.getConnectorRegistry().getLocation(base, file);
				if (fileLocation != null) {
					editedLocations.add(fileLocation);
					addLocation(fileLocation);
				}
			}
		}
	}

}

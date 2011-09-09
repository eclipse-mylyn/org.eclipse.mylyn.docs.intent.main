/*******************************************************************************
 * Copyright (c) 2010, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.test.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.client.ui.IntentEditorActivator;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.ide.builder.ToggleNatureAction;
import org.eclipse.mylyn.docs.intent.client.ui.ide.launcher.IDEApplicationManager;
import org.eclipse.mylyn.docs.intent.client.ui.ide.launcher.IntentProjectManager;
import org.eclipse.mylyn.docs.intent.client.ui.utils.IntentEditorOpener;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryObjectHandler;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.ReadOnlyException;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.ReadWriteRepositoryObjectHandlerImpl;
import org.eclipse.mylyn.docs.intent.collab.handlers.impl.notification.typeListener.TypeNotificator;
import org.eclipse.mylyn.docs.intent.collab.handlers.notification.Notificator;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;
import org.eclipse.mylyn.docs.intent.collab.utils.RepositoryCreatorHolder;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentChapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.test.utils.FileToStringConverter;
import org.eclipse.ui.PlatformUI;

/**
 * An abstract test class providing API for manage an Intent IDE projects and editors.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public abstract class AbstractIntentUITest extends TestCase implements ILogListener {

	public static final String INTENT_NEW_PROJECT_WIZARD_ID = "org.eclipse.mylyn.docs.intent.client.ui.ide.wizards.NewIntentProjectWizard";

	protected IProject intentProject;

	protected Repository repository;

	protected RepositoryAdapter repositoryAdapter;

	protected RepositoryListenerForTests repositoryListener;

	private IntentDocument intentDocument;

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		WorkspaceUtils.cleanWorkspace();
		WorkspaceUtils.closeWelcomePage();
		waitForAllOperationsInUIThread();
		IntentEditorActivator.getDefault().getLog().addLogListener(this);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {

		// Step 1 : clean workspace
		waitForAllOperationsInUIThread();
		if (intentProject != null) {
			intentProject.delete(true, true, new NullProgressMonitor());
		}
		IntentEditorActivator.getDefault().getLog().removeLogListener(this);
		WorkspaceUtils.cleanWorkspace();

		// Step 2 : setting all fields to null (to avoid memory leaks)
		setAllFieldsToNull();

		super.tearDown();
	}

	/**
	 * Creates and register an new {@link org.eclipse.mylyn.docs.intent.collab.handlers.RepositoryClient } in
	 * charge of detecting that events happened on the repository.
	 */
	protected void registerRepositoryListener() {
		// Step 1 : creating the handler
		// we listen for all modifications made on the traceability index
		RepositoryObjectHandler handler = new ReadWriteRepositoryObjectHandlerImpl(repositoryAdapter);
		Set<EStructuralFeature> listenedFeatures = new LinkedHashSet<EStructuralFeature>();
		listenedFeatures.addAll(CompilerPackage.eINSTANCE.getTraceabilityIndex().getEStructuralFeatures());
		Notificator listenedElementsNotificator = new TypeNotificator(listenedFeatures);
		handler.setNotificator(listenedElementsNotificator);

		// Step 2 : creating the client
		this.repositoryListener = new RepositoryListenerForTests();
		repositoryListener.addRepositoryObjectHandler(handler);
	}

	/**
	 * Creates a new Intent project using the intent document located at the given path.
	 * 
	 * @param projectName
	 *            the intent project name
	 * @param intentDocumentPath
	 *            the path of the intent document to use (relative to the
	 *            org.eclipse.mylyn.docs.intent.client.ui.test project).
	 */
	protected void setUpIntentProject(final String projectName, String intentDocumentPath) {
		setUpIntentProject(projectName, intentDocumentPath, false);
	}

	/**
	 * Creates a new Intent project using the intent document located at the given path.
	 * 
	 * @param projectName
	 *            the intent project name
	 * @param intentDocumentPath
	 *            the path of the intent document to use (relative to the
	 *            org.eclipse.mylyn.docs.intent.client.ui.test project)
	 * @param listenForRepository
	 *            indicates whether a repository listener should be registered. If you want to determine if a
	 *            client has done a job (by calling {@link AbstractIntentUITest#waitForIndexer() for example}
	 *            ), this must be true.
	 */
	protected void setUpIntentProject(final String projectName, String intentDocumentPath,
			boolean listenForRepository) {
		try {
			// Step 1 : getting the content of the intent document located at the given path.
			File file = new File(intentDocumentPath);
			final String intentDocumentContent = FileToStringConverter.getFileAsString(file);

			// Step 2 : creating the intent project
			IWorkspaceRunnable create = new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					IProject project = WorkspaceUtils.createProject(projectName, monitor);

					IDEApplicationManager.initializeContent(project, intentDocumentContent);
					ToggleNatureAction.toggleNature(project);

					// Step 3 : initializing all useful informations
					intentProject = project;
					setUpRepository(project);
				}

			};
			ResourcesPlugin.getWorkspace().run(create, null);

			while (repositoryAdapter == null
			// && (repository == null || ((WorkspaceRepository)repository).getEditingDomain()
			// .getCommandStack() == null)
			) {
				Thread.sleep(10);
			}

			// Step 3 : registering the repository listener
			registerRepositoryListener();

		} catch (CoreException e) {
			AssertionFailedError error = new AssertionFailedError("Failed to create Intent project");
			error.setStackTrace(e.getStackTrace());
			throw error;
		} catch (IOException e) {
			AssertionFailedError error = new AssertionFailedError(
					"Failed to get content of intent document '" + intentDocumentPath + "'");
			error.setStackTrace(e.getStackTrace());
			throw error;
		} catch (InterruptedException e) {
			AssertionFailedError error = new AssertionFailedError("Failed to create Intent project");
			error.setStackTrace(e.getStackTrace());
			throw error;
		}
	}

	/**
	 * Set up the repository for the given project.
	 * 
	 * @param project
	 *            the project
	 */
	protected void setUpRepository(IProject project) {
		try {
			repository = IntentProjectManager.getRepository(project);
			repositoryAdapter = RepositoryCreatorHolder.getCreator().createRepositoryAdapterForRepository(
					repository);
		} catch (RepositoryConnectionException e) {
			AssertionFailedError error = new AssertionFailedError(
					"Cannot connect to the created IntentRepository");
			error.setStackTrace(e.getStackTrace());
			throw error;
		}
		// wait for initialization completed
		waitForAllOperationsInUIThread();
	}

	/**
	 * Returns the intentDocument associated to the current Intent project.
	 * 
	 * @return the intentDocument associated to the current Intent project
	 */
	protected IntentDocument getIntentDocument() {
		if (intentDocument == null) {
			try {
				Resource documentResource = repositoryAdapter
						.getOrCreateResource(IntentLocations.INTENT_INDEX);
				assertTrue("Invalid content of resource '" + IntentLocations.INTENT_INDEX + "'",
						documentResource.getContents().iterator().next() instanceof IntentDocument);
				intentDocument = (IntentDocument)documentResource.getContents().iterator().next();
			} catch (ReadOnlyException e) {
				// Cannot happen in the test context : no readonly access
			}

		}
		return intentDocument;
	}

	/**
	 * Return the chapter at the given number.
	 * 
	 * @param number
	 *            the number of the chapter
	 * @return the chapter
	 */
	protected IntentChapter getIntentChapter(int number) {
		return getIntentDocument().getChapters().get(number - 1);
	}

	/**
	 * Return the section at the given number.
	 * 
	 * @param number
	 *            the number of the section
	 * @return the section
	 */
	protected IntentSection getIntentSection(int... number) {
		IntentSection section = getIntentChapter(number[0]).getSubSections().get(number[1] - 1);
		if (number.length > 2) {
			for (int i = 2; i < number.length; i++) {
				section = section.getSubSections().get(number[i] - 1);
			}
		}
		return section;
	}

	/**
	 * Opens an editor on the Document contained in the intent project.
	 * 
	 * @return the opened editor
	 */
	protected IntentEditor openIntentEditor() {
		return openIntentEditor(getIntentDocument());
	}

	/**
	 * Opens an editor on the given {@link IntentStructuredElement}.
	 * 
	 * @param element
	 *            the {@link IntentStructuredElement} to open an editor on
	 * @return the opened editor
	 */
	protected IntentEditor openIntentEditor(IntentStructuredElement element) {
		IntentEditorOpener.openIntentEditor(repository, element, true, null, true);
		waitForAllOperationsInUIThread();
		return IntentEditorOpener.getAlreadyOpenedEditor(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.ILogListener#logging(org.eclipse.core.runtime.IStatus, java.lang.String)
	 */
	public void logging(IStatus status, String plugin) {
		if (status.getSeverity() == IStatus.ERROR) {
			fail(status.getMessage());
		}
	}

	/**
	 * Wait until the end of all asynchronous operations launched in the UI Thread.
	 */
	protected static void waitForAllOperationsInUIThread() {
		while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// Nothing to do
			}
		}
	}

	/**
	 * Wait for synchronizer to complete work.
	 */
	protected void waitForSynchronizer() {
		assertNotNull(
				"Cannot wait for synchronizer : you need to initialize a repository listener by calling the registerRepositoryListener() method",
				repositoryListener);
		assertTrue("Time out : synchronizer should have handle changes but did not",
				repositoryListener.waitForModificationOn(IntentLocations.TRACEABILITY_INFOS_INDEX_PATH));
		waitForAllOperationsInUIThread();
	}

	/**
	 * Wait for repository to complete work.
	 */
	protected void waitForIndexer() {
		assertNotNull(
				"Cannot wait for Indexer : you need to initialize a repository listener by calling the registerRepositoryListener() method",
				repositoryListener);
		assertTrue("Time out : indexer should have handle changes but did not",
				repositoryListener.waitForModificationOn(IntentLocations.INTENT_INDEX));
		waitForAllOperationsInUIThread();
	}

	/**
	 * Wait for compiler to complete work.
	 */
	protected void waitForCompiler() {
		assertNotNull(
				"Cannot wait for compiler : you need to initialize a repository listener by calling the registerRepositoryListener() method",
				repositoryListener);
		assertTrue("Time out : compiler should have handle changes but did not",
				repositoryListener.waitForModificationOn(IntentLocations.TRACEABILITY_INFOS_INDEX_PATH));
		waitForAllOperationsInUIThread();
	}

	/**
	 * Sets all fields of the current test case to null (to avoid memory leaks).
	 */
	private void setAllFieldsToNull() {
		// For all fields defined in the current test and all its superclasses
		for (Class<?> clazz = this.getClass(); clazz != TestCase.class; clazz = clazz.getSuperclass()) {
			for (Field field : clazz.getDeclaredFields()) {
				boolean isReference = !field.getType().isPrimitive();
				try {
					field.setAccessible(true);
					boolean isSet = field.get(this) != null;
					// We do not clean non set references
					if (isReference && isSet) {
						boolean isFinal = Modifier.isFinal(field.getModifiers());
						// We do not clean final fields
						if (!isFinal) {
							// Setting the field to null
							field.set(this, null);
						}
					}
				} catch (IllegalArgumentException e) {
					// Do nothing
				} catch (IllegalAccessException e) {
					// Do nothing
				}
			}
		}
	}

}
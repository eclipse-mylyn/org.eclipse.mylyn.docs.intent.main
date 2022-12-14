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
package org.eclipse.intent.mapping.ide.ui.adapter;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.intent.mapping.MappingUtils;
import org.eclipse.intent.mapping.base.IBase;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.ILocationDescriptor;
import org.eclipse.intent.mapping.base.LocationDescriptor;
import org.eclipse.intent.mapping.ide.IdeMappingUtils;
import org.eclipse.intent.mapping.ide.adapter.MarkerToLocationDescriptorAdapterFactory;
import org.eclipse.intent.mapping.ide.ui.Activator;
import org.eclipse.intent.mapping.ide.ui.UiIdeMappingUtils;
import org.eclipse.intent.mapping.text.TextRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Adapts {@link Object} to {@link ILocation} and {@link ILocationDescriptor}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ObjectToLocationAdapterFactory extends MarkerToLocationDescriptorAdapterFactory {

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {
		final Object res;

		final Object superRes = super.getAdapter(adaptableObject, adapterType);
		if (superRes != null) {
			res = superRes;
		} else {
			if (adaptableObject instanceof IStructuredSelection) {
				final Object firstElement = ((IStructuredSelection)adaptableObject).getFirstElement();
				if (firstElement instanceof ILocation) {
					res = (ILocation)firstElement;
				} else {
					res = selectionToLocationDescriptor((IStructuredSelection)adaptableObject);
				}
			} else if (adaptableObject instanceof ITextSelection) {
				res = textSelectionToLocationDescriptor((ITextSelection)adaptableObject);
			} else {
				res = objectToLocationDescriptor(adaptableObject);
			}
		}

		return adapt(res, adapterType);
	}

	/**
	 * Adapts {@link ILocationDescriptor} to {@link ILocation} and vise versa.
	 * 
	 * @param object
	 *            {@link ILocation} or {@link ILocationDescriptor}
	 * @param adapterType
	 *            the type of adapter to look up
	 * @return {@link ILocation} or {@link ILocationDescriptor}
	 */
	private Object adapt(Object object, Class<?> adapterType) {
		final Object res;

		if (ILocation.class == adapterType) {
			if (object instanceof ILocation) {
				res = object;
			} else {
				if (object instanceof ILocationDescriptor && ((ILocationDescriptor)object).exists()) {
					res = ((ILocationDescriptor)object).getLocation();
				} else {
					res = null;
				}
			}
		} else if (ILocationDescriptor.class == adapterType) {
			if (object instanceof ILocation) {
				res = new LocationDescriptor((ILocation)object);
			} else if (object instanceof ILocationDescriptor) {
				res = object;
			} else {
				res = null;
			}
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * Adapts {@link IStructuredSelection} to {@link ILocationDescriptor}.
	 * 
	 * @param selection
	 *            the {@link IStructuredSelection}
	 * @return the {@link ILocationDescriptor}
	 */
	private ILocationDescriptor selectionToLocationDescriptor(IStructuredSelection selection) {
		final ILocationDescriptor res;

		final Object firstElement = selection.getFirstElement();
		res = objectToLocationDescriptor(firstElement);

		return res;
	}

	/**
	 * Adapts the given element to {@link ILocationDescriptor}.
	 * 
	 * @param element
	 *            the element
	 * @return the {@link ILocationDescriptor}
	 */
	private ILocationDescriptor objectToLocationDescriptor(final Object element) {
		final ILocationDescriptor res;

		final IBase currentBase = IdeMappingUtils.getCurrentBase();
		if (currentBase != null) {
			res = MappingUtils.getConnectorRegistry().getLocationDescriptor(currentBase, element);
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * Adapts {@link ITextSelection} {@link ILocationDescriptor}.
	 * 
	 * @param selection
	 *            the {@link ITextSelection}
	 * @return the {@link ILocationDescriptor}
	 */
	private ILocationDescriptor textSelectionToLocationDescriptor(ITextSelection selection) {
		ILocationDescriptor res;

		final IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActivePart();
		if (activePart instanceof IEditorPart) {
			final IEditorInput input = ((IEditorPart)activePart).getEditorInput();
			final IFile file = UiIdeMappingUtils.getFile(input);
			try {
				final String content = MappingUtils.getContent((int)file.getLocation().toFile().length(), file
						.getContents());
				final int start = selection.getOffset();
				final Integer end = start + selection.getLength();
				if (start > -1 && end < content.length()) {
					// TODO find a way to provide more dynamic container
					final TextRegion region = new TextRegion(file, content.substring(start, end), start, end);
					res = IdeMappingUtils.adapt(region, ILocationDescriptor.class);
				} else {
					res = null;
				}
			} catch (IOException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						"can't read content " + file.getLocation().toString(), e));
				res = null;
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						"can't read content " + file.getLocation().toString(), e));
				res = null;
			}
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class<?>[] getAdapterList() {
		return new Class[] {ILocation.class, ILocationDescriptor.class };
	}

}

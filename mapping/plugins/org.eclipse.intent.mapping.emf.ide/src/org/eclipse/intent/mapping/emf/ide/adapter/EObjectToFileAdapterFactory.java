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
package org.eclipse.intent.mapping.emf.ide.adapter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * Adapts {@link EObject} to {@link IFile}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EObjectToFileAdapterFactory implements IAdapterFactory {

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {
		final IFile res;

		if (adaptableObject instanceof EObject && ((EObject)adaptableObject).eResource() != null
				&& ((EObject)adaptableObject).eResource().getURI() != null) {
			final URI uri = ((EObject)adaptableObject).eResource().getURI();
			if (uri.isPlatformResource()) {
				final String path = ((EObject)adaptableObject).eResource().getURI().toString().substring(
						"platform:/resource".length());
				res = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
			} else {
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
		return new Class[] {IFile.class };
	}

}

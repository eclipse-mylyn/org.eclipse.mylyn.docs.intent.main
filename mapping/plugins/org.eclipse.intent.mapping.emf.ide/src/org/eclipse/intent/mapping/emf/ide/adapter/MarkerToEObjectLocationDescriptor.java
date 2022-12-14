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

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.intent.mapping.base.ILocationDescriptor;
import org.eclipse.intent.mapping.emf.ide.marker.IEObjectLocationMaker;
import org.eclipse.intent.mapping.ide.Activator;
import org.eclipse.intent.mapping.ide.IdeMappingUtils;
import org.eclipse.intent.mapping.ide.adapter.IMarkerToLocationDescriptor;

/**
 * Marker to {@link ILocationDescriptor} descriptor.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MarkerToEObjectLocationDescriptor implements IMarkerToLocationDescriptor {

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.ide.adapter.IMarkerToLocationDescriptor#getAdapter(org.eclipse.core.resources.IMarker)
	 */
	public ILocationDescriptor getAdapter(IMarker marker) {
		ILocationDescriptor res = null;

		try {
			if (marker.isSubtypeOf(EValidator.MARKER)) {
				final String uri = (String)marker.getAttribute(IEObjectLocationMaker.URI_ATTRIBUTE);
				// TODO we should change this to use a global ResourceSet...
				final ResourceSet rs = new ResourceSetImpl();
				final EObject eObject = rs.getEObject(URI.createURI(uri), true);
				res = IdeMappingUtils.adapt(eObject, ILocationDescriptor.class);
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(),
					e));
		}

		return res;
	}
}

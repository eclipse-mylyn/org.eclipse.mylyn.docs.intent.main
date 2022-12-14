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
package org.eclipse.intent.mapping.ide.adapter;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.intent.mapping.MappingUtils;
import org.eclipse.intent.mapping.base.ILocationDescriptor;
import org.eclipse.intent.mapping.ide.Activator;
import org.eclipse.intent.mapping.ide.IdeMappingUtils;
import org.eclipse.intent.mapping.text.TextRegion;

/**
 * Marker to {@link ILocationDescriptor} descriptor.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MarkerToTextLocationDescriptor implements IMarkerToLocationDescriptor {

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.ide.adapter.IMarkerToLocationDescriptor#getAdapter(org.eclipse.core.resources.IMarker)
	 */
	public ILocationDescriptor getAdapter(IMarker marker) {
		ILocationDescriptor res = null;

		try {
			if (marker.isSubtypeOf(IMarker.TEXT)) {
				final int start = (Integer)marker.getAttribute(IMarker.CHAR_START);
				final Integer end = (Integer)marker.getAttribute(IMarker.CHAR_END);
				final String content = MappingUtils.getContent((int)marker.getResource().getLocation()
						.toFile().length(), ((IFile)marker.getResource()).getContents());
				// TODO find a way to provide more dynamic container
				final TextRegion region = new TextRegion(marker.getResource(), content.substring(start, end),
						start, end);
				res = IdeMappingUtils.adapt(region, ILocationDescriptor.class);
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(),
					e));
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(),
					e));
		}

		return res;
	}
}

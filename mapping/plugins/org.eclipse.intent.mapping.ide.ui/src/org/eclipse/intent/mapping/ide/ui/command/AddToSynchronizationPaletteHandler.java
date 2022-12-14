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
package org.eclipse.intent.mapping.ide.ui.command;

import org.eclipse.intent.mapping.base.ILocationDescriptor;
import org.eclipse.intent.mapping.ide.IdeMappingUtils;

/**
 * Adds selected {@link ILocationDescriptor} to the {@link IdeMappingUtils#getSynchronizationPalette()
 * synchronization palette}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AddToSynchronizationPaletteHandler extends AbstractLocationHandler {

	@Override
	protected void handleLocationDescriptor(ILocationDescriptor locationDescriptor) {
		IdeMappingUtils.addLocationToPalette(locationDescriptor);
	}

	@Override
	protected boolean canHandleLocation(ILocationDescriptor locationDescriptor) {
		return !IdeMappingUtils.getSynchronizationPalette().contains(locationDescriptor);
	}

}

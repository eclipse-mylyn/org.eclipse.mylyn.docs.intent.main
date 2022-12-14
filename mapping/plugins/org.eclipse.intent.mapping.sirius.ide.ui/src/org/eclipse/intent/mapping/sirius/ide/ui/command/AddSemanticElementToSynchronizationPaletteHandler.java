/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation and/or initial documentation
 *    ...
 *******************************************************************************/
package org.eclipse.intent.mapping.sirius.ide.ui.command;

import org.eclipse.intent.mapping.ide.ui.command.AddToSynchronizationPaletteHandler;

/**
 * Adds selected semantic {@link org.eclipse.intent.mapping.base.ILocationDescriptor ILocationDescriptor} to
 * the {@link org.eclipse.intent.mapping.ide.IdeMappingUtils#getSynchronizationPalette() synchronization
 * palette}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AddSemanticElementToSynchronizationPaletteHandler extends AddToSynchronizationPaletteHandler {

	@Override
	protected Object getElementFromSelectedObject(Object selected) {
		return SiriusMappingUtils.getSemanticElementFromSelectedObject(selected);
	}

}

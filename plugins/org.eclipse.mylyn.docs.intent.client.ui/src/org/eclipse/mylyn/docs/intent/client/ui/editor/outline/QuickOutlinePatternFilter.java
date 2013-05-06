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
package org.eclipse.mylyn.docs.intent.client.ui.editor.outline;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnit;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * We'll use a custom filter for our quick outline as the tree we display to the user contains the elements'
 * type : "template", "query", "macro" ... and we don't want this to be taken into account when filtering.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class QuickOutlinePatternFilter extends PatternFilter {
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.dialogs.PatternFilter#isLeafMatch(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object)
	 */
	@Override
	protected boolean isLeafMatch(Viewer viewer, Object element) {
		boolean isLeafMatch = false;

		// we simply ignore description units
		if (!(element instanceof DescriptionUnit)) {
			String labelText = ((ILabelProvider)((StructuredViewer)viewer).getLabelProvider())
					.getText(element);

			if (labelText != null) {

				String elementName = labelText;
				isLeafMatch = wordMatches(elementName);
			}
		}
		return isLeafMatch;
	}
}

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
package org.eclipse.mylyn.docs.intent.collab.handlers.notification;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * Notification sent by the repository to the clients containing the changing objects.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public interface RepositoryChangeNotification {

	/**
	 * Returns the list of the impacted elements.
	 * 
	 * @return the list of the impacted elements
	 */
	List<EObject> getImpactedElements();
}

/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.intent.mapping.base;

import java.util.List;

/**
 * Contains {@link ILocation}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface ILocationContainer {

	/**
	 * Gets the {@link List} of contained {@link ILocation}.
	 * 
	 * @return the {@link List} of contained {@link ILocation}
	 */
	List<ILocation> getContents();

}

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
package org.eclipse.intent.mapping.emf;

import org.eclipse.intent.mapping.base.ILocation;

/**
 * An {@link ILocation} for {@link org.eclipse.emf.cdo.eresource.CDOResourceNode CDOResourceNode}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface ICDOResourceNodeLocation extends ILocation {

	/**
	 * Gets the {@link org.eclipse.emf.cdo.eresource.CDOResourceNode#getPath() path}.
	 * 
	 * @return the {@link org.eclipse.emf.cdo.eresource.CDOResourceNode#getPath() path}
	 */
	String getPath();

	/**
	 * Sets the {@link org.eclipse.emf.cdo.eresource.CDOResourceNode#getPath() path}.
	 * 
	 * @param path
	 *            the new path
	 */
	void setPath(String path);

}

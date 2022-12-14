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

import java.io.Serializable;
import java.util.List;

/**
 * Locate a part of a resource according to available {@link ILocationAdapter}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface ILocation extends IMappingElement, ILocationContainer, IBaseElement {

	/**
	 * Gets the {@link List} of {@link ILink} that have this {@link ILocation} as {@link ILink#getTarget()
	 * target}.
	 * 
	 * @return the {@link List} of {@link ILink} that have this {@link ILocation} as {@link ILink#getTarget()
	 *         target}
	 */
	List<ILink> getSourceLinks();

	/**
	 * Gets the {@link List} of {@link ILink} that have this {@link ILocation} as {@link ILink#getSource()
	 * source}.
	 * 
	 * @return the {@link List} of {@link ILink} that have this {@link ILocation} as {@link ILink#getSource()
	 *         source}
	 */
	List<ILink> getTargetLinks();

	/**
	 * Sets the containing {@link ILocation}.
	 * 
	 * @param container
	 *            the containing {@link ILocationContainer}
	 */
	void setContainer(ILocationContainer container);

	/**
	 * Gets the containing {@link ILocationContainer}.
	 * 
	 * @return the containing {@link ILocationContainer} if any, <code>null</code> otherwise
	 */
	ILocationContainer getContainer();

	/**
	 * Adds a {@link ILocationListener}.
	 * 
	 * @param listener
	 *            the {@link ILocationListener} to add
	 */
	void addListener(ILocationListener listener);

	/**
	 * Removes a {@link ILocationListener}.
	 * 
	 * @param listener
	 *            the {@link ILocationListener} to remove
	 */
	void removeListener(ILocationListener listener);

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	Serializable getType();

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the type
	 */
	void setType(Serializable type);

	/**
	 * Sets mark the location as deleted.
	 * 
	 * @param markedAsDeleted
	 *            if <code>true</code> mark the location as deleted
	 */
	void setMarkedAsDeleted(boolean markedAsDeleted);

	/**
	 * Tells if the location is marked as deleted.
	 * 
	 * @return <code>true</code> if the location is marked as deleted, <code>false</code> otherwise
	 */
	boolean isMarkedAsDeleted();

	/**
	 * Change the {@link ILocation}.
	 * 
	 * @param reportDescription
	 *            the {@link IReport} {@link IReport#getDescription() description}
	 */
	void change(String reportDescription);

}

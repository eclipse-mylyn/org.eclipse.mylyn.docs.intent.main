/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation and/or initial documentation
 *    ...
 *******************************************************************************/
package org.eclipse.intent.mapping.emf.tests.base;

import org.eclipse.intent.mapping.impl.MappingFactoryImpl;
import org.eclipse.intent.mapping.tests.base.IBaseFactory;

/**
 * The tested objects factory.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class Factory extends MappingFactoryImpl implements IBaseFactory {

	/**
	 * The {@link Factory} instance.
	 */
	public static final Factory INSTANCE = new Factory();

}

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
package org.eclipse.intent.mapping.emf.ide.tests.resource;

import org.eclipse.intent.mapping.emf.tests.base.Factory;
import org.eclipse.intent.mapping.ide.tests.resource.AbstractTextFileLocationTests;
import org.eclipse.intent.mapping.tests.base.IBaseFactory;

/**
 * Tests {@link org.eclipse.intent.mapping.ide.TextFileLocation TextFileLocation}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EMFTextFileLocationTests extends AbstractTextFileLocationTests {

	@Override
	protected IBaseFactory getFactory() {
		return Factory.INSTANCE;
	}

}

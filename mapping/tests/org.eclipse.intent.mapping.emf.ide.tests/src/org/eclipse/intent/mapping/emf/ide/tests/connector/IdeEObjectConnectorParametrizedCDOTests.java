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
package org.eclipse.intent.mapping.emf.ide.tests.connector;

import org.eclipse.intent.mapping.emf.tests.connector.EObjectConnectorParametrizedCDOTests;

/**
 * Tests {@link org.eclipse.intent.mapping.emf.ide.connector.IdeEObjectConnector IdeEObjectConnector} with
 * CDO.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class IdeEObjectConnectorParametrizedCDOTests extends EObjectConnectorParametrizedCDOTests {

	public IdeEObjectConnectorParametrizedCDOTests(Object[] original, Object[] altered) {
		super(original, altered);
	}

}

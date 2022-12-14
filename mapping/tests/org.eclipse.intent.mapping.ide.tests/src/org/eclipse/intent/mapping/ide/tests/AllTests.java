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
package org.eclipse.intent.mapping.ide.tests;

import org.eclipse.intent.mapping.ide.tests.adapter.MarkerToTextLocationDesciptorTests;
import org.eclipse.intent.mapping.ide.tests.connector.IdeTextConnectorTests;
import org.eclipse.intent.mapping.ide.tests.connector.ResourceConnectorTests;
import org.eclipse.intent.mapping.ide.tests.connector.ResourceLocationListenerTests;
import org.eclipse.intent.mapping.ide.tests.connector.TextFileConnectorDelegateTests;
import org.eclipse.intent.mapping.ide.tests.content.ContentTests;
import org.eclipse.intent.mapping.ide.tests.internal.connector.FileDelegateRegistryTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Aggregates tests for the org.eclipse.intent.mapping.ide plug-in.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Suite.class)
@SuiteClasses(value = {FileDelegateRegistryTests.class, ResourceConnectorTests.class,
		TextFileConnectorDelegateTests.class, IdeTextConnectorTests.class,
		MarkerToTextLocationDesciptorTests.class, ResourceLocationListenerTests.class,
		IdeMappingUtilsTests.class, ContentTests.class, })
public class AllTests {

}

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
package org.eclipse.intent.mapping.tests;

import org.eclipse.intent.mapping.tests.base.LocationDescriptorTests;
import org.eclipse.intent.mapping.tests.base.ObjectLocationDescriptorTests;
import org.eclipse.intent.mapping.tests.connector.ConnectorTests;
import org.eclipse.intent.mapping.tests.content.ContentTests;
import org.eclipse.intent.mapping.tests.internal.base.BaseTests;
import org.eclipse.intent.mapping.tests.text.TextTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Aggregates tests for the org.eclipse.intent.mapping plug-in.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Suite.class)
@SuiteClasses(value = {BaseTests.class, ConnectorTests.class, TextTests.class, MappingUtilsTests.class,
		ObjectLocationDescriptorTests.class, LocationDescriptorTests.class, ContentTests.class })
public class AllTests {

}

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
package org.eclipse.intent.mapping.emf.ide.tests;

import org.eclipse.intent.mapping.emf.ide.tests.adapter.EObjectLocationToMarkerAdapterFactoryTests;
import org.eclipse.intent.mapping.emf.ide.tests.adapter.MarkerToEObjectLocationTests;
import org.eclipse.intent.mapping.emf.ide.tests.connector.EObjectContainerProviderTests;
import org.eclipse.intent.mapping.emf.ide.tests.connector.EObjectFileConnectorDelegateTests;
import org.eclipse.intent.mapping.emf.ide.tests.connector.IdeCDOContainerProviderTests;
import org.eclipse.intent.mapping.emf.ide.tests.connector.IdeCDOResourceNodeConnectorTests;
import org.eclipse.intent.mapping.emf.ide.tests.connector.IdeCDOViewConnectorTests;
import org.eclipse.intent.mapping.emf.ide.tests.connector.IdeEObjectConnectorCDOTests;
import org.eclipse.intent.mapping.emf.ide.tests.connector.IdeEObjectConnectorParametrizedCDOTests;
import org.eclipse.intent.mapping.emf.ide.tests.connector.IdeEObjectConnectorParametrizedTests;
import org.eclipse.intent.mapping.emf.ide.tests.connector.IdeEObjectConnectorTests;
import org.eclipse.intent.mapping.emf.ide.tests.resource.EMFEObjectFileLocationTests;
import org.eclipse.intent.mapping.emf.ide.tests.resource.EMFResourceLocationTests;
import org.eclipse.intent.mapping.emf.ide.tests.resource.EMFTextFileLocationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Aggregates tests for the org.eclipse.intent.mapping.emf.ide plug-in.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Suite.class)
@SuiteClasses(value = {EMFResourceLocationTests.class, EMFTextFileLocationTests.class,
		EMFEObjectFileLocationTests.class, EObjectFileConnectorDelegateTests.class,
		WorkspaceMappingBaseListenerTests.class, IdeEObjectConnectorTests.class,
		IdeEObjectConnectorCDOTests.class, IdeCDOContainerProviderTests.class,
		EObjectContainerProviderTests.class, IdeEObjectConnectorParametrizedTests.class,
		IdeEObjectConnectorParametrizedCDOTests.class, EObjectLocationToMarkerAdapterFactoryTests.class,
		MarkerToEObjectLocationTests.class, IdeCDOViewConnectorTests.class,
		IdeCDOResourceNodeConnectorTests.class, })

public class AllTests {

}

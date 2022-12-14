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
package org.eclipse.intent.mapping.emf.tests.connector;

import org.eclipse.emf.cdo.internal.net4j.CDONet4jSessionImpl;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.ILocationContainer;
import org.eclipse.intent.mapping.base.ILocationDescriptor;
import org.eclipse.intent.mapping.emf.ICDORepositoryLocation;
import org.eclipse.intent.mapping.emf.connector.CDOViewConnector;
import org.eclipse.intent.mapping.emf.tests.AllTests;
import org.eclipse.intent.mapping.emf.tests.CDOServer;
import org.eclipse.intent.mapping.emf.tests.CDOUtils;
import org.eclipse.intent.mapping.tests.base.BaseElementFactoryTests.TestLocation;
import org.eclipse.intent.mapping.tests.base.BaseRegistryTests.TestBase;
import org.eclipse.net4j.connector.IConnector;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link CDOViewConnector}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CDOViewConnectorTests {

	/**
	 * Test {@link ICDORepositoryLocation}.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	public static class TestCDORepositoryLocation extends TestLocation implements ICDORepositoryLocation {

		/**
		 * The URL.
		 */
		private String url;

		/**
		 * The UUID.
		 */
		private String uuid;

		/**
		 * The name.
		 */
		private String name;

		/**
		 * The branch ID.
		 */
		private int branchID;

		public String getURL() {
			return url;
		}

		public void setURL(String newURL) {
			this.url = newURL;
		}

		public String getUUID() {
			return uuid;
		}

		public void setUUID(String newUUID) {
			this.uuid = newUUID;
		}

		public String getName() {
			return name;
		}

		public void setName(String newName) {
			this.name = newName;
		}

		public int getBranchID() {
			return branchID;
		}

		public void setBranchID(int id) {
			this.branchID = id;
		}

	}

	/**
	 * Test {@link CDOViewConnector}.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	private static class TestCDOViewConnector extends CDOViewConnector {
		@Override
		public Class<? extends ILocation> getLocationType(Class<? extends ILocationContainer> containerType,
				Object element) {
			return super.getLocationType(containerType, element);
		}

		@Override
		public void initLocation(ILocationContainer container, ILocation location, Object element) {
			super.initLocation(container, location, element);
		}

		@Override
		public boolean match(ILocation location, Object element) {
			return super.match(location, element);
		}

	}

	/**
	 * The {@link CDOTransaction}.
	 */
	private static CDOTransaction transaction;

	/**
	 * The connector to test.
	 */
	private static TestCDOViewConnector connector = new TestCDOViewConnector();

	@BeforeClass
	public static void beforeClass() {
		AllTests.startCDOServer();
		IConnector c = CDOUtils.getConnector(CDOServer.PROTOCOL + "://" + CDOServer.IP + ":"
				+ CDOServer.PORT);
		CDOSession session = CDOUtils.openSession(c, CDOServer.REPOSITORY_NAME);
		transaction = CDOUtils.openTransaction(session);
		connector.addSessionToCache(transaction);
	}

	@AfterClass
	public static void afterClass() {
		connector.removeSessionFromCache(transaction);
		transaction.close();
		AllTests.stopCDOServer();
	}

	@Test
	public void getElement() {
		final TestCDORepositoryLocation location = new TestCDORepositoryLocation();
		location.setUUID(transaction.getSession().getRepositoryInfo().getUUID());
		final Object element = connector.getElement(location);

		assertEquals(transaction, element);
	}

	@Test
	public void getLocationDescriptor() {
		final TestBase base = new TestBase();
		final ILocationDescriptor descriptor = connector.getLocationDescriptor(base, transaction);

		assertEquals(null, descriptor.getContainerDescriptor());
		assertEquals(transaction, descriptor.getElement());
		assertEquals("testRepo (tcp://127.0.0.1:12345)", descriptor.getName());
	}

	@Test
	public void getLocationTypeNotCDOView() {
		assertNull(connector.getLocationType(null, new Object()));
	}

	@Test
	public void getLocationTypeCDOView() {
		assertEquals(ICDORepositoryLocation.class, connector.getLocationType(null, transaction));
	}

	@Test
	public void getName() {
		final TestCDORepositoryLocation location = new TestCDORepositoryLocation();

		location.setName(transaction.getSession().getRepositoryInfo().getName());
		location.setURL(((CDONet4jSessionImpl)transaction.getSession()).getConnector().getURL());
		location.setUUID(transaction.getSession().getRepositoryInfo().getUUID());

		assertEquals("testRepo (tcp://127.0.0.1:12345)", connector.getName(location));
	}

	@Test
	public void getType() {
		assertEquals(ICDORepositoryLocation.class, connector.getType());
	}

	@Test
	public void initLocation() {
		final TestCDORepositoryLocation location = new TestCDORepositoryLocation();

		connector.initLocation(null, location, transaction);

		assertEquals(null, location.getContainer());
		assertEquals("testRepo", location.getName());
		assertEquals(0, location.getBranchID());
		assertEquals("tcp://127.0.0.1:12345", location.getURL());
		assertEquals(transaction.getSession().getRepositoryInfo().getUUID(), location.getUUID());
	}

	@Test
	public void match() {
		final TestCDORepositoryLocation location = new TestCDORepositoryLocation();

		location.setName(transaction.getSession().getRepositoryInfo().getName());
		location.setURL(((CDONet4jSessionImpl)transaction.getSession()).getConnector().getURL());
		location.setUUID(transaction.getSession().getRepositoryInfo().getUUID());

		assertTrue(connector.match(location, transaction));
	}

	@Test
	public void matchDifferentBranch() {
		final TestCDORepositoryLocation location = new TestCDORepositoryLocation();

		location.setName(transaction.getSession().getRepositoryInfo().getName());
		location.setURL(((CDONet4jSessionImpl)transaction.getSession()).getConnector().getURL());
		location.setUUID(transaction.getSession().getRepositoryInfo().getUUID() + 42);

		assertFalse(connector.match(location, transaction));
	}

	@Test
	public void matchDifferentRepository() {
		final TestCDORepositoryLocation location = new TestCDORepositoryLocation();

		location.setName(transaction.getSession().getRepositoryInfo().getName());
		location.setURL(((CDONet4jSessionImpl)transaction.getSession()).getConnector().getURL());
		location.setUUID(transaction.getSession().getRepositoryInfo().getUUID() + "42");

		assertFalse(connector.match(location, transaction));
	}

}

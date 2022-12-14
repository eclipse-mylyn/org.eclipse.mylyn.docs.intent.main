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
package org.eclipse.intent.mapping.tests.base;

import org.eclipse.intent.mapping.base.IBase;
import org.eclipse.intent.mapping.base.IBaseListener;
import org.eclipse.intent.mapping.base.ILink;
import org.eclipse.intent.mapping.base.ILinkListener;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.ILocationContainer;
import org.eclipse.intent.mapping.base.ILocationListener;
import org.eclipse.intent.mapping.base.IReport;
import org.eclipse.intent.mapping.base.IReportListener;
import org.eclipse.intent.mapping.text.ITextLocation;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * Abstract mapping tests.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractMappingTests {

	/**
	 * Test {@link IBaseListener}.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	protected final class TestBaseListener implements IBaseListener {

		/**
		 * Number of time {@link TestBaseListener#nameChanged(String)} has been called.
		 */
		private int nameChanged;

		/**
		 * Number of time {@link TestBaseListener#contentsAdded(ILocation)} has been called.
		 */
		private int rootLocationAdded;

		/**
		 * Number of time {@link TestBaseListener#contentsRemoved(ILocation)} has been called.
		 */
		private int rootLocationRemoved;

		/**
		 * Number of time {@link TestBaseListener#reportAdded(IReport)} has been called.
		 */
		private int reportAdded;

		/**
		 * Number of time {@link TestBaseListener#reportRemoved(IReport)} has been called.
		 */
		private int reportRemoved;

		/**
		 * Number of time {@link TestBaseListener#containerProviderAdded(String)} has been called.
		 */
		private int containerProviderAdded;

		/**
		 * Number of time {@link TestBaseListener#containerProviderRemoved(String)} has been called.
		 */
		private int containerProviderRemoved;

		public void nameChanged(String oldName, String newName) {
			nameChanged++;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#contentsAdded(org.eclipse.intent.mapping.base.ILocation)
		 */
		public void contentsAdded(ILocation location) {
			rootLocationAdded++;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#contentsRemoved(org.eclipse.intent.mapping.base.ILocation)
		 */
		public void contentsRemoved(ILocation location) {
			rootLocationRemoved++;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#reportAdded(org.eclipse.intent.mapping.base.IReport)
		 */
		public void reportAdded(IReport report) {
			reportAdded++;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#reportRemoved(org.eclipse.intent.mapping.base.IReport)
		 */
		public void reportRemoved(IReport report) {
			reportRemoved++;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#containerProviderAdded(java.lang.String)
		 */
		public void containerProviderAdded(String provider) {
			containerProviderAdded++;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.intent.mapping.base.IBaseListener#containerProviderRemoved(java.lang.String)
		 */
		public void containerProviderRemoved(String provider) {
			containerProviderRemoved++;
		}

	}

	/**
	 * Test {@link ILinkListener}.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	protected final class TestLinkListener implements ILinkListener {

		/**
		 * Number of time {@link TestLinkListener#targetChanged(ILocation)} has been called.
		 */
		private int targetChanged;

		/**
		 * Number of time {@link TestLinkListener#sourceChanged(ILocation)} has been called.
		 */
		private int sourceChanged;

		/**
		 * Number of time {@link TestLinkListener#descriptionChanged(String)} has been called.
		 */
		private int descriptionChanged;

		/**
		 * Number of time {@link TestLinkListener#reportAdded(IReport)} has been called.
		 */
		private int reportAdded;

		/**
		 * Number of time {@link TestLinkListener#reportRemoved(IReport)} has been called.
		 */
		private int reportRemoved;

		public void descriptionChanged(String oldDescription, String newDescription) {
			descriptionChanged++;
		}

		public void sourceChanged(ILocation oldSource, ILocation newSource) {
			sourceChanged++;
		}

		public void targetChanged(ILocation oldTarget, ILocation newTarget) {
			targetChanged++;
		}

		public void reportAdded(IReport report) {
			reportAdded++;
		}

		public void reportRemoved(IReport report) {
			reportRemoved++;
		}
	}

	/**
	 * Test {@link IReportListener}.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	protected final class TestReportListener implements IReportListener {

		/**
		 * Number of time {@link TestReportListener#descriptionChanged(String)} has been called.
		 */
		private int descriptionChanged;

		/**
		 * Number of time {@link TestReportListener#linkChanged(ILink, ILink)} has been called.
		 */
		private int linkChanged;

		public void descriptionChanged(String oldDescription, String newDescription) {
			descriptionChanged++;
		}

		public void linkChanged(ILink oldLink, ILink newLink) {
			linkChanged++;
		}
	}

	/**
	 * Test {@link ILocationListener}.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	protected final class TestLocationListener implements ILocationListener {

		/**
		 * Number of time {@link TestLocationListener#contentRemoved(ILocation)} has been called.
		 */
		private int contentsLocationRemoved;

		/**
		 * Number of time {@link TestLocationListener#contentAdded(ILocation)} has been called.
		 */
		private int contentsLocationAdded;

		/**
		 * Number of time {@link TestLocationListener#targetLinkRemoved(ILink)} has been called.
		 */
		private int targetLinkRemoved;

		/**
		 * Number of time {@link TestLocationListener#targetLinkAdded(ILink)} has been called.
		 */
		private int targetLinkAdded;

		/**
		 * Number of time {@link TestLocationListener#sourceLinkRemoved(ILink)} has been called.
		 */
		private int sourceLinkRemoved;

		/**
		 * Number of time {@link TestLocationListener#sourceLinkAdded(ILink)} has been called.
		 */
		private int sourceLinkAdded;

		/**
		 * Number of time
		 * {@link TestLocationListener#containerChanged(ILocationContainer, ILocationContainer)} has been
		 * called.
		 */
		private int containerChanged;

		/**
		 * Number of time {@link TestLocationListener#changed(String)} has been called.
		 */
		private int changed;

		/**
		 * Number of time {@link TestLocationListener#markedAsDeletedChanged(boolean)} has been called.
		 */
		private int markedAsDeletedChanged;

		public void sourceLinkAdded(ILink link) {
			sourceLinkAdded++;
		}

		public void sourceLinkRemoved(ILink link) {
			sourceLinkRemoved++;
		}

		public void targetLinkAdded(ILink link) {
			targetLinkAdded++;
		}

		public void targetLinkRemoved(ILink link) {
			targetLinkRemoved++;
		}

		public void contentsAdded(ILocation location) {
			contentsLocationAdded++;
		}

		public void contentsRemoved(ILocation location) {
			contentsLocationRemoved++;
		}

		public void containerChanged(ILocationContainer oldContainer, ILocationContainer newContainer) {
			containerChanged++;
		}

		public void markedAsDeletedChanged(boolean newValue) {
			markedAsDeletedChanged++;
		}

		public void changed(String reportDescription) {
			changed++;
		}

	}

	/**
	 * The {@link IBase} to test.
	 */
	protected IBase base;

	@Before
	public void before() {
		base = getFactory().createBase();
	}

	/**
	 * Gets the {@link IBaseFactory}.
	 * 
	 * @return the {@link IBaseFactory}
	 */
	protected abstract IBaseFactory getFactory();

	protected void assertTestBaseListener(TestBaseListener listener, int nameChanged, int rootLocationAdded,
			int rootLocationRemoved, int reportAdded, int reportRemoved, int containerProviderAdded,
			int containerProvidersRemoved) {
		assertEquals(nameChanged, listener.nameChanged);
		assertEquals(rootLocationAdded, listener.rootLocationAdded);
		assertEquals(rootLocationRemoved, listener.rootLocationRemoved);
		assertEquals(reportAdded, listener.reportAdded);
		assertEquals(reportRemoved, listener.reportRemoved);
		assertEquals(containerProviderAdded, listener.containerProviderAdded);
		assertEquals(containerProvidersRemoved, listener.containerProviderRemoved);
	}

	protected void assertTestLinkListener(TestLinkListener listener, int descriptionChanged,
			int sourceChanged, int targetChanged, int reportAdded, int reportRemoved) {
		assertEquals(descriptionChanged, listener.descriptionChanged);
		assertEquals(sourceChanged, listener.sourceChanged);
		assertEquals(targetChanged, listener.targetChanged);
		assertEquals(reportAdded, listener.reportAdded);
		assertEquals(reportRemoved, listener.reportRemoved);
	}

	protected void assertTestReportListener(TestReportListener listener, int descriptionChanged,
			int linkChanged) {
		assertEquals(descriptionChanged, listener.descriptionChanged);
		assertEquals(linkChanged, listener.linkChanged);
	}

	// CHECKSTYLE:OFF
	protected void assertTestLocationListener(TestLocationListener listener, int contentLocationRemoved,
			int contentLocationAdded, int targetLinkRemoved, int targetLinkAdded, int sourceLinkRemoved,
			int sourceLinkAdded, int containerChanged, int markedAsDeletedChanged, int changed) {
		assertEquals(contentLocationRemoved, listener.contentsLocationRemoved);
		assertEquals(contentLocationAdded, listener.contentsLocationAdded);
		assertEquals(targetLinkRemoved, listener.targetLinkRemoved);
		assertEquals(targetLinkAdded, listener.targetLinkAdded);
		assertEquals(sourceLinkRemoved, listener.sourceLinkRemoved);
		assertEquals(sourceLinkAdded, listener.sourceLinkAdded);
		assertEquals(containerChanged, listener.containerChanged);
		assertEquals(markedAsDeletedChanged, listener.markedAsDeletedChanged);
		assertEquals(changed, listener.changed);
	}

	// CHECKSTYLE:ON

	protected IBase getBase() {
		return base;
	}

	protected ILink createLink() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return getBase().getFactory().createElement(ILink.class);
	}

	protected ILocation createLocation() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return getBase().getFactory().createElement(ITextLocation.class);
	}

	protected IReport createReport() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return getBase().getFactory().createElement(IReport.class);
	}

}

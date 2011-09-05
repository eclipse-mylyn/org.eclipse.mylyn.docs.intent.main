/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractUITest;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.WorkspaceUtils;

/**
 * Tests the Intent demo, part 1: navigation behavior.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public abstract class AbstractDemoTest extends AbstractUITest {
	private static final String DEMO_ZIP_LOCATION = "data/unit/demo/demo.zip";

	private static final String BUNDLE_NAME = "org.eclipse.mylyn.docs.intent.client.ui.test";

	private static final String INTENT_PROJECT_NAME = "org.eclipse.emf.compare.idoc";

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractUITest#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		WorkspaceUtils.unzipAllProjects(BUNDLE_NAME, DEMO_ZIP_LOCATION, new NullProgressMonitor());
		intentProject = ResourcesPlugin.getWorkspace().getRoot().getProject(INTENT_PROJECT_NAME);
		setUpRepository(intentProject);

		waitForAllOperationsInUIThread(); // TODO check why necessary
	}

}
/*******************************************************************************
 * Copyright (c) 2010, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.test.suite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.mylyn.docs.intent.client.ui.test.unit.compare.ChangeEditorUpdateTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.compilation.CompileTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.opening.OpenEditorTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.synchronization.EcoreTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.demo.synchronization.JavaTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.project.ProjectTest;
import org.eclipse.mylyn.docs.intent.client.ui.test.unit.scenario.IntentAbstractResourceTest;

/**
 * This suite will launch all the tests relative to the UI behavior.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class UITestSuite extends TestCase {

	/**
	 * Launches the collaborative test suite.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * Creates the {@link junit.framework.TestSuite TestSuite} for all Intent UI tests.
	 * 
	 * @return The test suite containing all intent ui tests
	 */
	public static Test suite() {
		final TestSuite suite = new TestSuite("Intent UI TestSuite");

		// Core tests
		// All tests that test a technical concern (emf compare behavior, project lifecycle...)
		final TestSuite basicTestSuite = new TestSuite("Technical tests");
		basicTestSuite.addTestSuite(ProjectTest.class);
		basicTestSuite.addTestSuite(ChangeEditorUpdateTest.class);

		suite.addTest(basicTestSuite);

		// Scenario tests
		// all tests that test an identified scenario for the end-user (very simple use case)
		final TestSuite scenarioSuite = new TestSuite("Simple End-User Scenarios");
		scenarioSuite.addTestSuite(IntentAbstractResourceTest.class);

		suite.addTest(scenarioSuite);

		// Complete use case testSuite
		// all tests that ensures the behavior of complete use cases
		final TestSuite demoSuite = new TestSuite("Intent Demo TestSuite");
		demoSuite.addTestSuite(OpenEditorTest.class);
		demoSuite.addTestSuite(CompileTest.class);
		demoSuite.addTestSuite(EcoreTest.class);
		demoSuite.addTestSuite(JavaTest.class);

		suite.addTest(demoSuite);

		return suite;
	}

}

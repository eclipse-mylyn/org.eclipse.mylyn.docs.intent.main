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
package org.eclipse.mylyn.docs.intent.markup.test.unit.parsing;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test specific elements.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TestSpecificElements {

	/**
	 * Ensures that TOC are correctly detected (see textile file for further details).
	 */
	@Test
	public void testTOCDetection() {
		String[] result = TestParsingTextual.getTextSerializations("specificElements/tocDetection.textile");
		String actual = result[1];

		String expected = result[0];
		int indexToRemove = expected.indexOf("\n\nAnd this one also");
		expected = expected.substring(0, indexToRemove);
		expected += "And this one also";
		expected += "\n\n";
		expected += "{toc}\n";
		Assert.assertEquals(expected, actual);

	}

	/**
	 * Ensure that HTMLEntites are correctly managed.
	 */
	@Test
	public void testHTMLEntities() {
		String[] result = TestParsingTextual
				.getTextSerializations("specificElements/testHTMLEntities.textile");
		String actual = result[1];

		String expected = result[0];
		expected = expected.replace("<br/>", "\n");
		Assert.assertEquals(expected, actual);
	}
}

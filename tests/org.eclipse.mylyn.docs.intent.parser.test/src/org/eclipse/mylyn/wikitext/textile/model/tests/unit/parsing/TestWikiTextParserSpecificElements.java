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
package org.eclipse.mylyn.wikitext.textile.model.tests.unit.parsing;

//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;

import org.junit.Test;

//CHECKSTYLE:ON
/**
 * Test the wikiText parser on specific syntaxic details.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TestWikiTextParserSpecificElements {

	/**
	 * Ensures that TOC are correctly detected (see the textile File for details).
	 */
	@Test
	public void testTOCDetection() {
		String[] result = TestWikiTextParserGeneral
				.getTextSerializations("specificElements/tocDetection.textile");
		String actual = result[1];

		String expected = result[0];
		int indexToRemove = expected.indexOf("\n\nAnd this one also");
		expected = expected.substring(0, indexToRemove);
		expected += "And this one also";
		expected += "\n\n";
		expected += "{toc}\n";
		assertEquals(expected, actual);

	}

	/**
	 * Ensures that HTMLEntites are correctly managed.
	 */
	@Test
	public void testHTMLEntities() {
		String[] result = TestWikiTextParserGeneral
				.getTextSerializations("specificElements/testHTMLEntities.textile");
		String actual = result[1];

		String expected = result[0];
		expected = expected.replace("<br/>", "\n");
		assertEquals(expected, actual);
	}
}

/*******************************************************************************
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.compare.test.unit;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.mylyn.docs.intent.compare.test.utils.AbstractEMFCompareTest;
import org.eclipse.mylyn.docs.intent.compare.utils.EMFCompareUtils;
import org.eclipse.mylyn.docs.intent.compare.utils.IntentPrettyPrinter;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.serializer.IntentSerializer;

/**
 * Tests the potential merging issues.
 * 
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class MergingIssues extends AbstractEMFCompareTest {
	private static final boolean USE_DEFAULT_COMPARE = false;

	private static final boolean DEBUG = false;

	public void testFillEmptyDoc() throws IOException, ParseException {
		compareAndMerge("fillEmptyDoc");
	}

	public void testModelingUnitDeletion() throws IOException, ParseException {
		compareAndMerge("modelingUnitDeletion");
	}

	public void testSectionsAdditions() throws IOException, ParseException {
		compareAndMerge("sectionsAdditions");
	}

	public void testNewChapter() throws IOException, ParseException {
		compareAndMerge("newChapter");
	}

	public void testNewChapterWorking() throws IOException, ParseException {
		compareAndMerge("newChapterWorking");
	}

	public void testDoubleSectionInsertion() throws IOException, ParseException {
		compareAndMerge("doubleSectionInsertion");
	}

	public void testDoubleTopTextAddition() throws IOException, ParseException {
		compareAndMerge("doubleTopTextAddition");
	}

	public void testEndTextAddition() throws IOException, ParseException {
		compareAndMerge("endTextAddition");
	}

	public void testMuAddition() throws IOException, ParseException {
		compareAndMerge("muAddition");
	}

	public void testNewInstruction() throws IOException, ParseException {
		compareAndMerge("newInstruction");
	}

	public void testNewSection() throws IOException, ParseException {
		compareAndMerge("newSection");
	}

	public void testNewSectionUpdate1() throws IOException, ParseException {
		compareAndMerge("newSectionUpdate1");
	}

	public void testNewSectionUpdate2() throws IOException, ParseException {
		compareAndMerge("newSectionUpdate2");
	}

	public void testInversionIssue() throws IOException, ParseException {
		compareAndMerge("inversionIssue");
	}

	public void testRenameAll() throws IOException, ParseException {
		compareAndMerge("renameAll");
	}

	public void testSectionInsertion() throws IOException, ParseException {
		compareAndMerge("sectionInsertion");
	}

	public void testTextDeletion() throws IOException, ParseException {
		compareAndMerge("textDeletion");
	}

	public void testTextInsertion() throws IOException, ParseException {
		compareAndMerge("textInsertion");
	}

	public void testTopChapterAddition() throws IOException, ParseException {
		compareAndMerge("topChapterAddition");
	}

	public void testTopTextAddition() throws IOException, ParseException {
		compareAndMerge("topTextAddition");
	}

	public void testRename() throws IOException, ParseException {
		compareAndMerge("rename");
	}

	public void testReverseRename() throws IOException, ParseException {
		compareAndMerge("reverseRename");
	}

	private void compareAndMerge(String testName) throws IOException, ParseException {

		String repository = getFileAsString(new File("data/" + testName + "/IntentDocument.text"));
		String modified = getFileAsString(new File("data/" + testName + "/IntentDocument.text.modifications"));
		IntentStructuredElement left = parseIntentDocument(modified);
		IntentStructuredElement right = parseIntentDocument(repository);

		Comparison comparison = null;

		if (USE_DEFAULT_COMPARE) {
			comparison = EMFCompareUtils.compare(left, right);
		} else {
			comparison = EMFCompareUtils.compareDocuments(left, right);
		}

		if (DEBUG) {
			System.out.println("TESTING: " + testName);
			System.out.println("Was:");
			IntentPrettyPrinter.displayModel(right);
			System.out.println();
			System.out.println("Now:");
			IntentPrettyPrinter.displayModel(left);
			System.out.println();
			IntentPrettyPrinter.printMatch(comparison, System.out);
			IntentPrettyPrinter.printDifferences(comparison, System.out);
			System.out.println("=========================================================");
		}

		for (Diff diff : comparison.getDifferences()) {
			diff.copyLeftToRight();
		}

		assertEquals(modified, new IntentSerializer().serialize(right));
	}

}

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
package org.eclipse.mylyn.docs.intent.client.ui.test.unit.compare;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.mylyn.docs.intent.client.ui.test.util.AbstractIntentUITest;
import org.eclipse.mylyn.docs.intent.compare.IntentASTMerger;
import org.eclipse.mylyn.docs.intent.core.document.IntentChapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.document.IntentSubSectionContainer;
import org.eclipse.mylyn.docs.intent.parser.IntentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;

public class IntentMatchEngineTests extends AbstractIntentUITest {

	private static final String INTENT_DOCUMENT_FOLDER = "unit/models/documents/";

	public void testCompareDocumentWithoutChaperTitles() {
		doTestDiffEngine("compareTest-02.xmi");
	}

	public void testCompareBigDocument() {
		doTestDiffEngine("compareTest-03.xmi");

	}

	public void testCompareDocumentWithChaperTitles() {

		doTestDiffEngine("compareTest-01.xmi");
	}

	/**
	 * Ensures that all modifications that can be made on the IntentDocument located at the given model path
	 * are correctly detected.
	 * 
	 * @param modelPath
	 *            the path of the Intent document model (from
	 *            org.eclipse.mylyn.docs.intent.client.ui.test/data/unit/models/documents/)
	 */
	protected void doTestDiffEngine(String modelPath) {
		// Step 1: we load the intent document
		loadIntentDocumentFromTests(INTENT_DOCUMENT_FOLDER + modelPath);

		// Step 2: compare with a copy of this element
		doTestCopyIsEqualToOriginal();

		// Step 3 : test that adding chapters works as expected
		doTestAddingChapter();

		// Step 4 : test that removing chapters works as expected
		doTestRemovingChapter();

		// Step 5 : test that adding sections works as expected
		doTestAddingSections();

		// Step 5 : test that removing sections works as expected
		doTestRemovingSections();
	}

	/**
	 * Ensures that no differences are detected when comparing a Document with a copy of itself.
	 */
	protected void doTestCopyIsEqualToOriginal() {
		// Create a copy of the document
		IntentStructuredElement copy = EcoreUtil.copy(getIntentDocument());

		// Check that copy is equal to original
		List<DiffElement> differences = IntentASTMerger.getDifferences(copy, getIntentDocument());
		String message = "No difference should be detected between an Intent document and its copy";
		assertDiffElementIsAsExpected(message, differences, 0);
	}

	/**
	 * Ensures that the creation of any Chapter in the Intent document is correctly detected.
	 */
	protected void doTestAddingChapter() {

		// Create a copy with a new chapter
		IntentDocument copy = EcoreUtil.copy(getIntentDocument());
		IntentChapter newChapter;
		try {
			newChapter = (IntentChapter)new IntentParser().parse("Chapter {\n\tChapter 1\n\tChaper1\n\t");

			// according to where the chapter is added, we should have the following results :
			for (int position = 0; position <= getIntentDocument().getChapters().size(); position++) {
				copy.getChapters().add(position, newChapter);
				List<DiffElement> differences = IntentASTMerger.getDifferences(copy, getIntentDocument());
				String message = "One new chapter should be detected at position " + position;
				DiffElement diff = assertDiffElementIsAsExpected(message, differences, 2);
				assertEquals(message + "\n" + getDiffAsString(differences), DifferenceKind.ADDITION,
						diff.getKind());
				assertEquals(message + getDiffAsString(differences), newChapter,
						((ModelElementChangeLeftTarget)diff).getLeftElement());
				assertEquals(message + getDiffAsString(differences), getIntentDocument(),
						((ModelElementChangeLeftTarget)diff).getRightParent());
				copy.getChapters().remove(newChapter);
			}
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Ensures that the deletion of any Chapter in the Intent document is correctly detected.
	 */
	protected void doTestRemovingChapter() {
		// Create a copy in which a chapter is removed
		IntentDocument copy = EcoreUtil.copy(getIntentDocument());
		// according to where the chapter is added, we should have the following results :
		for (int position = 0; position < getIntentDocument().getChapters().size(); position++) {
			IntentChapter chapterToRemoveInOriginal = getIntentDocument().getChapters().get(position);
			IntentChapter chapterToRemoveinCopy = copy.getChapters().get(position);
			copy.getChapters().remove(chapterToRemoveinCopy);
			String message = "A Chapter deletion should be detected at " + position;
			List<DiffElement> differences = IntentASTMerger.getDifferences(copy, getIntentDocument());
			DiffElement diff = assertDiffElementIsAsExpected(message, differences, 2);
			assertEquals(message + "\n" + getDiffAsString(differences), DifferenceKind.DELETION,
					diff.getKind());
			assertEquals(message + "\n" + getDiffAsString(differences), chapterToRemoveInOriginal,
					((ModelElementChangeRightTarget)diff).getRightElement());
			assertEquals(message + "\n" + getDiffAsString(differences), copy,
					((ModelElementChangeRightTarget)diff).getLeftParent());
			copy.getChapters().add(position, chapterToRemoveinCopy);
		}

	}

	/**
	 * Ensures that the creation of any section/subsection in the Intent document is correctly detected.
	 */
	protected void doTestAddingSections() {
		// Create a copy
		IntentDocument copy = EcoreUtil.copy(getIntentDocument());

		// For each chapter of the document
		for (int chapterID = 0; chapterID < getIntentDocument().getChapters().size(); chapterID++) {
			doTestAddingSectionsRecursive(copy, getIntentDocument().getChapters().get(chapterID), copy
					.getChapters().get(chapterID), 3);
		}
	}

	/**
	 * Ensures that the creation of any section/subsection in the Intent document is correctly detected.
	 */
	private void doTestAddingSectionsRecursive(IntentDocument copy, IntentSubSectionContainer container,
			IntentSubSectionContainer containerCopy, int containerLevel) {
		IntentSection newSection;
		try {
			newSection = (IntentSection)new IntentParser().parse("Section {\n\tSection 1\n\tSection1\n\t");

			// according to where the section is added, we should have the following results :
			for (int sectionID = 0; sectionID <= container.getSubSections().size(); sectionID++) {
				containerCopy.getIntentContent().add(sectionID, newSection);
				List<DiffElement> differences = IntentASTMerger.getDifferences(copy, getIntentDocument());
				String message = "One new section should be detected at position " + sectionID;
				DiffElement diff = assertDiffElementIsAsExpected(message, differences, containerLevel);
				assertEquals(message + "\n" + getDiffAsString(differences), DifferenceKind.ADDITION,
						diff.getKind());
				assertEquals(message + "\n" + getDiffAsString(differences), newSection,
						((ModelElementChangeLeftTarget)diff).getLeftElement());
				assertEquals(message + "\n" + getDiffAsString(differences), container,
						((ModelElementChangeLeftTarget)diff).getRightParent());
				containerCopy.getIntentContent().remove(sectionID);

				// considering subsections of the current container
				for (int subSectionID = 0; subSectionID < container.getSubSections().size(); subSectionID++) {
					doTestAddingSectionsRecursive(copy, container.getSubSections().get(subSectionID),
							containerCopy.getSubSections().get(subSectionID), containerLevel + 1);
				}
			}
		} catch (ParseException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Ensures that the deletion of any section/subsection in the Intent document is correctly detected.
	 */
	protected void doTestRemovingSections() {
		// Create a copy
		IntentDocument copy = EcoreUtil.copy(getIntentDocument());

		// For each chapter of the document
		for (int chapterID = 0; chapterID < getIntentDocument().getChapters().size(); chapterID++) {
			doTestRemovingSectionsRecursive(copy, getIntentDocument().getChapters().get(chapterID), copy
					.getChapters().get(chapterID), 3);
		}

	}

	/**
	 * Ensures that the deletion of any section/subsection in the Intent document is correctly detected.
	 */
	private void doTestRemovingSectionsRecursive(IntentDocument copy, IntentSubSectionContainer container,
			IntentSubSectionContainer containerCopy, int containerLevel) {

		// Delete each subsection
		for (int sectionID = 0; sectionID < container.getSubSections().size(); sectionID++) {
			IntentSection removedSection = (IntentSection)containerCopy.getSubSections().get(sectionID);
			int trueIndex = containerCopy.getIntentContent().indexOf(removedSection);
			containerCopy.getIntentContent().remove(removedSection);
			List<DiffElement> differences = IntentASTMerger.getDifferences(copy, getIntentDocument());
			String message = "A Section deletion should be detected at position" + sectionID + " position "
					+ sectionID;
			DiffElement diff = assertDiffElementIsAsExpected(message, differences, containerLevel);
			assertEquals(message + "\n" + getDiffAsString(differences), DifferenceKind.DELETION,
					diff.getKind());
			assertEquals(message + "\n" + getDiffAsString(differences),
					container.getIntentContent().get(trueIndex),
					((ModelElementChangeRightTarget)diff).getRightElement());
			assertEquals(message + "\n" + getDiffAsString(differences), containerCopy,
					((ModelElementChangeRightTarget)diff).getLeftParent());
			containerCopy.getIntentContent().add(trueIndex, removedSection);

			// considering subsections of the current container
			for (int subSectionID = 0; subSectionID < container.getSubSections().size(); subSectionID++) {
				doTestRemovingSectionsRecursive(copy, container.getSubSections().get(subSectionID),
						containerCopy.getSubSections().get(subSectionID), containerLevel + 1);
			}
		}
	}

	/**
	 * Ensures that the given differences describes exactly one difference at the expected level, with no
	 * sub-differences.
	 * 
	 * @param message
	 *            the failure message
	 * @param differences
	 *            the differences to inspect
	 * @param expectedLevel
	 *            the expected level for the diff (2 for chapter, 3 for root section, 4 for subsection...).
	 * @return the expected diff element if correct
	 */
	protected DiffElement assertDiffElementIsAsExpected(String message, Collection<DiffElement> differences,
			int expectedLevel) {
		assertEquals(message + "\n" + getDiffAsString(differences), 1, differences.size());

		// We want to have exactly one difference at the expected level
		DiffElement childDiff = differences.iterator().next();
		int currentLevel = 0;
		while (childDiff != null && currentLevel < expectedLevel) {
			assertEquals(message + "\n" + getDiffAsString(differences), 1, childDiff.getSubDiffElements()
					.size());
			currentLevel++;
			childDiff = childDiff.getSubDiffElements().iterator().next();
		}

		// This difference should not have any sub difference elements
		assertEquals(message + "\n" + getDiffAsString(differences), 0, childDiff.getSubDiffElements().size());
		return childDiff;
	}

	protected String getDiffAsString(Collection<DiffElement> differences) {
		String diff = "";
		for (DiffElement element : differences) {
			diff += element.toString() + "\n";
			diff += getDiffAsString(element.getSubDiffElements());
		}
		return diff;
	}
}

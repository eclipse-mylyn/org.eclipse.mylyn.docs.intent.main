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
package org.eclipse.mylyn.docs.intent.collab.common.query;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.descriptionunit.DescriptionUnit;
import org.eclipse.mylyn.docs.intent.core.document.IntentChapter;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocument;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentFactory;
import org.eclipse.mylyn.docs.intent.core.document.IntentSection;
import org.eclipse.mylyn.docs.intent.core.document.IntentStructuredElement;
import org.eclipse.mylyn.docs.intent.core.document.IntentSubSectionContainer;
import org.eclipse.mylyn.docs.intent.core.genericunit.IntentReferenceInstruction;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexEntry;
import org.eclipse.mylyn.docs.intent.markup.markup.Text;

/**
 * An utility class allowing to query the {@link IntentDocument}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentDocumentQuery extends AbstractIntentQuery {

	private IntentDocument intentDocument;

	/**
	 * Creates the query.
	 * 
	 * @param repositoryAdapter
	 *            the {@link RepositoryAdapter} to use for querying the repository.
	 */
	public IntentDocumentQuery(RepositoryAdapter repositoryAdapter) {
		super(repositoryAdapter);
	}

	/**
	 * Returns the {@link IntentDocument} of the queried repository. If none find, creates it.
	 * 
	 * @return the {@link IntentDocument} of the queried repository. If none find, creates it
	 */
	public IntentDocument getOrCreateIntentDocument() {
		if (intentDocument == null) {
			Resource resource = repositoryAdapter.getResource(IntentLocations.INTENT_INDEX);
			if (resource.getContents().isEmpty()) {
				resource.getContents().add(IntentDocumentFactory.eINSTANCE.createIntentDocument());
			}
			intentDocument = (IntentDocument)resource.getContents().get(0);
		}
		return intentDocument;
	}

	/**
	 * Returns the {@link IntentStructuredElement} located at the given level (e.g. "3.2.1"), or null if none
	 * found.
	 * 
	 * @param level
	 *            the level of the searched {@link IntentStructuredElement} (e.g. "3.2.1")
	 * @throws NumberFormatException
	 *             if the given level is not syntaxically correct
	 * @return the {@link IntentStructuredElement} located at the given level, or null if none found
	 */
	public IntentStructuredElement getElementAtLevel(String level) throws NumberFormatException {
		IntentStructuredElement elementAtLevel = null;
		IntentIndexEntry indexEntry = new IndexQuery(repositoryAdapter).getIndexEntryAtLevel(level);
		if (indexEntry != null && indexEntry.getReferencedElement() instanceof IntentStructuredElement) {
			elementAtLevel = (IntentStructuredElement)indexEntry.getReferencedElement();
		}
		return elementAtLevel;
	}

	/**
	 * Returns all the {@link org.eclipse.mylyn.docs.intent.core.document.IntentReference}s contained in the
	 * queried {@link IntentDocument}.
	 * 
	 * @return all the {@link org.eclipse.mylyn.docs.intent.core.document.IntentReference}s contained in the
	 *         queried {@link IntentDocument}
	 */
	public Collection<IntentReferenceInstruction> getAllIntentReferenceInstructions() {
		Collection<IntentReferenceInstruction> intentReferences = Sets.newLinkedHashSet();
		// Step 1: get all descriptions units
		for (DescriptionUnit unit : getAllDescriptionUnits()) {
			intentReferences.addAll(Sets.newLinkedHashSet(Iterables.filter(unit.getInstructions(),
					IntentReferenceInstruction.class)));
		}
		return intentReferences;
	}

	/**
	 * Returns all the {@link DescriptionUnit} contained in the queried {@link IntentDocument}.
	 * 
	 * @return all the {@link DescriptionUnit}s contained in the queried {@link IntentDocument}
	 */
	public Collection<DescriptionUnit> getAllDescriptionUnits() {
		Collection<DescriptionUnit> descriptionUnits = Sets.newLinkedHashSet();
		for (IntentChapter chapter : getOrCreateIntentDocument().getChapters()) {
			descriptionUnits.addAll(getAllDescriptionUnits(chapter));
		}
		return descriptionUnits;
	}

	/**
	 * Returns all the {@link DescriptionUnit} contained in the given {@link IntentSubSectionContainer}.
	 * 
	 * @param intentElement
	 *            the {@link IntentSubSectionContainer} to get the description units from
	 * @return all the {@link DescriptionUnit}s contained in the given {@link IntentSubSectionContainer}
	 */
	public Collection<DescriptionUnit> getAllDescriptionUnits(IntentSubSectionContainer intentElement) {
		Collection<DescriptionUnit> descriptionUnits = Sets.newLinkedHashSet();
		descriptionUnits.addAll(intentElement.getDescriptionUnits());
		for (IntentSection subSection : intentElement.getSubSections()) {
			descriptionUnits.addAll(getAllDescriptionUnits(subSection));
		}
		return descriptionUnits;
	}

	/**
	 * Returns the map of all the sections per IDs.
	 * 
	 * @return the map of all the sections
	 */
	public Map<String, IntentSection> getAllIdentifiedSections() {
		Map<String, IntentSection> res = new HashMap<String, IntentSection>();
		for (IntentChapter chapter : getOrCreateIntentDocument().getChapters()) {
			res.putAll(getAllIdentifiedSections(chapter));
		}
		return res;
	}

	/**
	 * Returns the map of all the sections per IDs.
	 * 
	 * @param root
	 *            the root container
	 * @return the map of all the sections
	 */
	private Map<String, IntentSection> getAllIdentifiedSections(IntentSubSectionContainer root) {
		Map<String, IntentSection> res = new HashMap<String, IntentSection>();
		for (IntentSection section : root.getSubSections()) {
			String sectionID = getSectionID(section);
			if (sectionID != null) {
				res.put(sectionID, section);
				res.putAll(getAllIdentifiedSections(section));
			}
		}
		return res;
	}

	/**
	 * Returns the given section id.
	 * 
	 * @param section
	 *            the section
	 * @return the given section id
	 */
	private String getSectionID(IntentSection section) {
		if (!section.getTitle().getContent().isEmpty()) {
			// TODO improve id computation
			// TODO manage duplicates titles
			return ((Text)section.getTitle().getContent().get(0)).getData().replaceAll(" ", "");
		}
		return null;
	}
}

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
package org.eclipse.mylyn.docs.intent.parser;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.parser.descriptionunit.DescriptionUnitParser;
import org.eclipse.mylyn.docs.intent.parser.internal.IntentDocumentParser;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ModelingUnitParserImpl;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;
import org.eclipse.mylyn.docs.intent.serializer.IntentPositionManager;
import org.eclipse.mylyn.docs.intent.serializer.ParsedElementPosition;

/**
 * High-level parser that delegates the parsing to the correct parser according to the possible entry points
 * for a Modeling Unit.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public class IntentParser {
	/**
	 * The position manager that handle the mapping between Intent element to positions.
	 */
	private IntentPositionManager positionManager;

	/**
	 * Returns the position of the given instruction element.
	 * 
	 * @param element
	 *            the element for witch we want the position
	 * @return the position of the given instruction element (null if no position).
	 */
	public ParsedElementPosition getPositionForElement(EObject element) {
		return this.positionManager.getPositionForElement(element);
	}

	/**
	 * Parse the given content and return the described element.
	 * 
	 * @param contentToParse
	 *            textual form of an Intent entity (can be a IntentDocument, a Section, a Chapter, a Modeling
	 *            Unit or a Description Unit).
	 * @return the given content and return the described element
	 * @throws ParseException
	 *             if the given content contain error or doesn't describe an Intent entity
	 */
	public EObject parse(String contentToParse) throws ParseException {
		IntentDocumentParser documentParser = new IntentDocumentParser();
		ModelingUnitParserImpl modelingUnitParser = new ModelingUnitParserImpl();
		DescriptionUnitParser descriptionUnitParser = new DescriptionUnitParser();
		this.positionManager = new IntentPositionManager();

		EObject generatedObject = null;

		try {
			// We have 3 possibilities for the type of the element to parse :
			// If it matches "@M (.*) M@", it's a modelingUnit
			if (modelingUnitParser.isParserFor(contentToParse)) {
				generatedObject = modelingUnitParser.parseString(contentToParse);
			} else {
				// If it starts with a IntentDocument's Keyword (like "Section, Document, Chapter..."
				if (documentParser.isParserFor(contentToParse)) {
					generatedObject = documentParser.parse(contentToParse);
					positionManager.addIntentPositionManagerInformations(documentParser.getPositionManager());
				} else {
					// In the other cases, we consider that the given contentToParse is a DescriptionUnit
					generatedObject = descriptionUnitParser.parse(contentToParse);
				}
			}
			return generatedObject;
		} catch (ParseException e) {
			ParseException parseException = new ParseException(e.getMessage(), e.getErrorOffset(),
					e.getErrorLength());
			parseException.setStackTrace(e.getStackTrace());
			throw parseException;
		}

	}

	/**
	 * Returns the position manager of this serializer.
	 * 
	 * @return the position manager of this serializer.
	 */
	public IntentPositionManager getPositionManager() {
		return this.positionManager;
	}
}

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
package org.eclipse.mylyn.docs.intent.parser.modelingunit.parser.linker;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnit;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValue;
import org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation;
import org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch;
import org.eclipse.mylyn.docs.intent.parser.modelingunit.ParseException;

/**
 * Sets the "href" values on Annotation, SectionReference and LabelReference.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ModelingUnitLinker extends ModelingUnitSwitch<Object> {

	/**
	 * Sets the "href" values on Annotation, SectionReference and LabelReference.
	 * 
	 * @param modelingUnit
	 *            the modelingUnit to link.
	 * @return the given Modeling Unit where internal links have been setted up.
	 * @throws ParseException
	 *             if some elements can't be attached to anything.
	 */
	public ModelingUnit resolveInternalLinks(ModelingUnit modelingUnit) throws ParseException {
		ModelingUnit resolvedModelingUnit = modelingUnit;
		for (EObject instruction : modelingUnit.getInstructions()) {
			Object linkingResult = doSwitch(instruction);
			if (linkingResult != null) {
				throw (ParseException)linkingResult;
			}
		}
		return resolvedModelingUnit;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseAnnotationDeclaration(org.eclipse.mylyn.docs.intent.core.modelingunit.AnnotationDeclaration)
	 */
	@Override
	public Object caseAnnotationDeclaration(AnnotationDeclaration object) {
		try {
			AnnotationDeclarationLinker.attachAnnotationDeclaration(object);
		} catch (ParseException e) {
			return e;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseContributionInstruction(org.eclipse.mylyn.docs.intent.core.modelingunit.ContributionInstruction)
	 */
	@Override
	public Object caseContributionInstruction(ContributionInstruction object) {
		for (ModelingUnitInstruction contribution : object.getContributions()) {
			return doSwitch(contribution);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseInstanciationInstruction(org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction)
	 */
	@Override
	public Object caseInstanciationInstruction(InstanciationInstruction object) {
		for (ModelingUnitInstruction structuralFeatures : object.getStructuralFeatures()) {
			return doSwitch(structuralFeatures);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseNewObjectValueForStructuralFeature(org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValueForStructuralFeature)
	 */
	@Override
	public Object caseNewObjectValue(NewObjectValue object) {
		return doSwitch(object.getValue());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseStructuralFeatureAffectation(org.eclipse.mylyn.docs.intent.core.modelingunit.StructuralFeatureAffectation)
	 */
	@Override
	public Object caseStructuralFeatureAffectation(StructuralFeatureAffectation object) {
		for (ModelingUnitInstruction structuralFeatures : object.getValues()) {
			return doSwitch(structuralFeatures);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.core.modelingunit.util.ModelingUnitSwitch#caseInstanciationInstructionReference(org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference)
	 */
	@Override
	public Object caseInstanciationInstructionReference(InstanciationInstructionReference object) {
		return doSwitch(object.getInstanciation());
	}

}

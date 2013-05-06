/**
 * Copyright (c) 2010, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.mylyn.docs.intent.core.document.impl;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.mylyn.docs.intent.core.document.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IntentDocumentFactoryImpl extends EFactoryImpl implements IntentDocumentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntentDocumentFactory init() {
		try {
			IntentDocumentFactory theIntentDocumentFactory = (IntentDocumentFactory)EPackage.Registry.INSTANCE
					.getEFactory("http://www.eclipse.org/intent/intentdocument/0.8");
			if (theIntentDocumentFactory != null) {
				return theIntentDocumentFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IntentDocumentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentDocumentFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IntentDocumentPackage.INTENT_GENERIC_ELEMENT:
				return (EObject)createIntentGenericElement();
			case IntentDocumentPackage.INTENT_SECTION:
				return (EObject)createIntentSection();
			case IntentDocumentPackage.INTENT_DOCUMENT:
				return (EObject)createIntentDocument();
			case IntentDocumentPackage.INTENT_REFERENCE_INSTRUCTION:
				return (EObject)createIntentReferenceInstruction();
			case IntentDocumentPackage.LABEL_DECLARATION:
				return (EObject)createLabelDeclaration();
			case IntentDocumentPackage.LABEL_REFERENCE_INSTRUCTION:
				return (EObject)createLabelReferenceInstruction();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName()
						+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case IntentDocumentPackage.TYPE_LABEL:
				return createTypeLabelFromString(eDataType, initialValue);
			case IntentDocumentPackage.URI:
				return createURIFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName()
						+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case IntentDocumentPackage.TYPE_LABEL:
				return convertTypeLabelToString(eDataType, instanceValue);
			case IntentDocumentPackage.URI:
				return convertURIToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName()
						+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentGenericElement createIntentGenericElement() {
		IntentGenericElementImpl intentGenericElement = new IntentGenericElementImpl();
		return intentGenericElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentSection createIntentSection() {
		IntentSectionImpl intentSection = new IntentSectionImpl();
		return intentSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentDocument createIntentDocument() {
		IntentDocumentImpl intentDocument = new IntentDocumentImpl();
		return intentDocument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentReferenceInstruction createIntentReferenceInstruction() {
		IntentReferenceInstructionImpl intentReferenceInstruction = new IntentReferenceInstructionImpl();
		return intentReferenceInstruction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelDeclaration createLabelDeclaration() {
		LabelDeclarationImpl labelDeclaration = new LabelDeclarationImpl();
		return labelDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelReferenceInstruction createLabelReferenceInstruction() {
		LabelReferenceInstructionImpl labelReferenceInstruction = new LabelReferenceInstructionImpl();
		return labelReferenceInstruction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeLabel createTypeLabelFromString(EDataType eDataType, String initialValue) {
		TypeLabel result = TypeLabel.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue
					+ "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeLabelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public URI createURIFromString(EDataType eDataType, String initialValue) {
		return URI.createURI(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertURIToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentDocumentPackage getIntentDocumentPackage() {
		return (IntentDocumentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IntentDocumentPackage getPackage() {
		return IntentDocumentPackage.eINSTANCE;
	}

} //IntentDocumentFactoryImpl

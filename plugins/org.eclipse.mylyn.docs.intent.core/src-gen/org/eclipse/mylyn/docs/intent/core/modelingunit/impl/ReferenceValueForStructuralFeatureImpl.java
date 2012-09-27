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
package org.eclipse.mylyn.docs.intent.core.modelingunit.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstructionReference;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ReferenceValueForStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Value For Structural Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ReferenceValueForStructuralFeatureImpl#getInstanciationReference <em>Instanciation Reference</em>}</li>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.ReferenceValueForStructuralFeatureImpl#getReferencedMetaType <em>Referenced Meta Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceValueForStructuralFeatureImpl extends ValueForStructuralFeatureImpl implements ReferenceValueForStructuralFeature {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceValueForStructuralFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelingUnitPackage.Literals.REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanciationInstructionReference getInstanciationReference() {
		return (InstanciationInstructionReference)eGet(
				ModelingUnitPackage.Literals.REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__INSTANCIATION_REFERENCE,
				true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanciationReference(InstanciationInstructionReference newInstanciationReference) {
		eSet(ModelingUnitPackage.Literals.REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__INSTANCIATION_REFERENCE,
				newInstanciationReference);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getReferencedMetaType() {
		return (EObject)eGet(
				ModelingUnitPackage.Literals.REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__REFERENCED_META_TYPE,
				true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedMetaType(EObject newReferencedMetaType) {
		eSet(ModelingUnitPackage.Literals.REFERENCE_VALUE_FOR_STRUCTURAL_FEATURE__REFERENCED_META_TYPE,
				newReferencedMetaType);
	}

} //ReferenceValueForStructuralFeatureImpl

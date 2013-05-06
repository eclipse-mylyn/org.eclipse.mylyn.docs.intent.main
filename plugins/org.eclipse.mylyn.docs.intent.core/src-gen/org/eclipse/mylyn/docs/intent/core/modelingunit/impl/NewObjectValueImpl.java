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
package org.eclipse.mylyn.docs.intent.core.modelingunit.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.mylyn.docs.intent.core.modelingunit.InstanciationInstruction;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.NewObjectValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>New Object Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.mylyn.docs.intent.core.modelingunit.impl.NewObjectValueImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NewObjectValueImpl extends AbstractValueImpl implements NewObjectValue {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NewObjectValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelingUnitPackage.Literals.NEW_OBJECT_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanciationInstruction getValue() {
		return (InstanciationInstruction)eGet(ModelingUnitPackage.Literals.NEW_OBJECT_VALUE__VALUE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(InstanciationInstruction newValue) {
		eSet(ModelingUnitPackage.Literals.NEW_OBJECT_VALUE__VALUE, newValue);
	}

} //NewObjectValueImpl

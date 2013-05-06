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
package org.eclipse.mylyn.docs.intent.core.document.descriptionunit;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.mylyn.docs.intent.core.document.descriptionunit.DescriptionUnitPackage
 * @generated
 */
public interface DescriptionUnitFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DescriptionUnitFactory eINSTANCE = org.eclipse.mylyn.docs.intent.core.document.descriptionunit.impl.DescriptionUnitFactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Description Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Description Unit</em>'.
	 * @generated
	 */
	DescriptionUnit createDescriptionUnit();

	/**
	 * Returns a new object of class '<em>Description Bloc</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Description Bloc</em>'.
	 * @generated
	 */
	DescriptionBloc createDescriptionBloc();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DescriptionUnitPackage getDescriptionUnitPackage();

} //DescriptionUnitFactory

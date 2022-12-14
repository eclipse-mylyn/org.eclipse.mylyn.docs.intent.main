/**
 *  Copyright (c) 2015 Obeo.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     Obeo - initial API and implementation and/or initial documentation
 *     ...
 * 
 */
package org.eclipse.intent.mapping;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract
 * class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.intent.mapping.MappingPackage
 * @generated
 */
public interface MappingFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	MappingFactory eINSTANCE = org.eclipse.intent.mapping.impl.MappingFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Link</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Link</em>'.
	 * @generated
	 */
	Link createLink();

	/**
	 * Returns a new object of class '<em>Base</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Base</em>'.
	 * @generated
	 */
	Base createBase();

	/**
	 * Returns a new object of class '<em>Text Location</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Text Location</em>'.
	 * @generated
	 */
	TextLocation createTextLocation();

	/**
	 * Returns a new object of class '<em>EObject Location</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return a new object of class '<em>EObject Location</em>'.
	 * @generated
	 */
	EObjectLocation createEObjectLocation();

	/**
	 * Returns a new object of class '<em>Report</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Report</em>'.
	 * @generated
	 */
	Report createReport();

	/**
	 * Returns a new object of class '<em>Couple</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Couple</em>'.
	 * @generated
	 */
	Couple createCouple();

	/**
	 * Returns a new object of class '<em>CDO Folder Location</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return a new object of class '<em>CDO Folder Location</em>'.
	 * @generated
	 */
	CDOFolderLocation createCDOFolderLocation();

	/**
	 * Returns a new object of class '<em>CDO Repository Location</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return a new object of class '<em>CDO Repository Location</em>'.
	 * @generated
	 */
	CDORepositoryLocation createCDORepositoryLocation();

	/**
	 * Returns a new object of class '<em>CDO Binary Resource Location</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return a new object of class '<em>CDO Binary Resource Location</em>'.
	 * @generated
	 */
	CDOBinaryResourceLocation createCDOBinaryResourceLocation();

	/**
	 * Returns a new object of class '<em>CDO Text Resource Location</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return a new object of class '<em>CDO Text Resource Location</em>'.
	 * @generated
	 */
	CDOTextResourceLocation createCDOTextResourceLocation();

	/**
	 * Returns a new object of class '<em>CDO Resource Location</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return a new object of class '<em>CDO Resource Location</em>'.
	 * @generated
	 */
	CDOResourceLocation createCDOResourceLocation();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	MappingPackage getMappingPackage();

} // MappingFactory

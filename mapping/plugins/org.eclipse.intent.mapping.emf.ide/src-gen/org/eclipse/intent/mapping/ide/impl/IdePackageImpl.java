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
package org.eclipse.intent.mapping.ide.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.intent.mapping.MappingPackage;
import org.eclipse.intent.mapping.ide.EObjectFileLocation;
import org.eclipse.intent.mapping.ide.FileLocation;
import org.eclipse.intent.mapping.ide.IdeFactory;
import org.eclipse.intent.mapping.ide.IdePackage;
import org.eclipse.intent.mapping.ide.ResourceLocation;
import org.eclipse.intent.mapping.ide.TextFileLocation;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class IdePackageImpl extends EPackageImpl implements IdePackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass resourceLocationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass fileLocationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass textFileLocationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eObjectFileLocationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()},
	 * which also performs initialization of the package, or returns the registered package, if one already
	 * exists. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.intent.mapping.ide.IdePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IdePackageImpl() {
		super(eNS_URI, IdeFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it
	 * depends.
	 * <p>
	 * This method is used to initialize {@link IdePackage#eINSTANCE} when that field is accessed. Clients
	 * should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IdePackage init() {
		if (isInited)
			return (IdePackage)EPackage.Registry.INSTANCE.getEPackage(IdePackage.eNS_URI);

		// Obtain or create and register package
		IdePackageImpl theIdePackage = (IdePackageImpl)(EPackage.Registry.INSTANCE.get(
				eNS_URI) instanceof IdePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new IdePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MappingPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theIdePackage.createPackageContents();

		// Initialize created meta-data
		theIdePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIdePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IdePackage.eNS_URI, theIdePackage);
		return theIdePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getResourceLocation() {
		return resourceLocationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getResourceLocation_FullPath() {
		return (EAttribute)resourceLocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getFileLocation() {
		return fileLocationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getTextFileLocation() {
		return textFileLocationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getEObjectFileLocation() {
		return eObjectFileLocationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IdeFactory getIdeFactory() {
		return (IdeFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on any
	 * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		resourceLocationEClass = createEClass(RESOURCE_LOCATION);
		createEAttribute(resourceLocationEClass, RESOURCE_LOCATION__FULL_PATH);

		fileLocationEClass = createEClass(FILE_LOCATION);

		textFileLocationEClass = createEClass(TEXT_FILE_LOCATION);

		eObjectFileLocationEClass = createEClass(EOBJECT_FILE_LOCATION);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect
	 * on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		MappingPackage theMappingPackage = (MappingPackage)EPackage.Registry.INSTANCE.getEPackage(
				MappingPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		resourceLocationEClass.getESuperTypes().add(theMappingPackage.getLocation());
		fileLocationEClass.getESuperTypes().add(this.getResourceLocation());
		textFileLocationEClass.getESuperTypes().add(this.getFileLocation());
		textFileLocationEClass.getESuperTypes().add(theMappingPackage.getTextContainer());
		eObjectFileLocationEClass.getESuperTypes().add(this.getFileLocation());
		eObjectFileLocationEClass.getESuperTypes().add(theMappingPackage.getEObjectContainer());

		// Initialize classes, features, and operations; add parameters
		initEClass(resourceLocationEClass, ResourceLocation.class, "ResourceLocation", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceLocation_FullPath(), ecorePackage.getEString(), "fullPath", null, 1, 1,
				ResourceLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fileLocationEClass, FileLocation.class, "FileLocation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(textFileLocationEClass, TextFileLocation.class, "TextFileLocation", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(eObjectFileLocationEClass, EObjectFileLocation.class, "EObjectFileLocation", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // IdePackageImpl

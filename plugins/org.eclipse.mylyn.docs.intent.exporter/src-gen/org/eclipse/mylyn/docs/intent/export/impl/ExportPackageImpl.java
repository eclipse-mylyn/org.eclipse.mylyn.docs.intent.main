/**
 */
package org.eclipse.mylyn.docs.intent.export.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.mylyn.docs.intent.core.compiler.CompilerPackage;
import org.eclipse.mylyn.docs.intent.core.document.IntentDocumentPackage;
import org.eclipse.mylyn.docs.intent.core.indexer.IntentIndexerPackage;
import org.eclipse.mylyn.docs.intent.core.modelingunit.ModelingUnitPackage;
import org.eclipse.mylyn.docs.intent.export.ExportFactory;
import org.eclipse.mylyn.docs.intent.export.ExportPackage;
import org.eclipse.mylyn.docs.intent.export.IntentGen;
import org.eclipse.mylyn.docs.intent.export.LatexDocument;

import org.eclipse.mylyn.docs.intent.markup.markup.MarkupPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExportPackageImpl extends EPackageImpl implements ExportPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentGenEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass latexDocumentEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.mylyn.docs.intent.export.ExportPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExportPackageImpl() {
		super(eNS_URI, ExportFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ExportPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExportPackage init() {
		if (isInited) return (ExportPackage)EPackage.Registry.INSTANCE.getEPackage(ExportPackage.eNS_URI);

		// Obtain or create and register package
		ExportPackageImpl theExportPackage = (ExportPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExportPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExportPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CompilerPackage.eINSTANCE.eClass();
		IntentDocumentPackage.eINSTANCE.eClass();
		IntentIndexerPackage.eINSTANCE.eClass();
		ModelingUnitPackage.eINSTANCE.eClass();
		MarkupPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theExportPackage.createPackageContents();

		// Initialize created meta-data
		theExportPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theExportPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExportPackage.eNS_URI, theExportPackage);
		return theExportPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentGen() {
		return intentGenEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentGen_Documents() {
		return (EReference)intentGenEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLatexDocument() {
		return latexDocumentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLatexDocument_Title() {
		return (EAttribute)latexDocumentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLatexDocument_Authors() {
		return (EAttribute)latexDocumentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLatexDocument_Roots() {
		return (EReference)latexDocumentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLatexDocument_DocumentClass() {
		return (EAttribute)latexDocumentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLatexDocument_ReplaceSubSubSectionByPara() {
		return (EAttribute)latexDocumentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExportFactory getExportFactory() {
		return (ExportFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		intentGenEClass = createEClass(INTENT_GEN);
		createEReference(intentGenEClass, INTENT_GEN__DOCUMENTS);

		latexDocumentEClass = createEClass(LATEX_DOCUMENT);
		createEAttribute(latexDocumentEClass, LATEX_DOCUMENT__TITLE);
		createEAttribute(latexDocumentEClass, LATEX_DOCUMENT__AUTHORS);
		createEReference(latexDocumentEClass, LATEX_DOCUMENT__ROOTS);
		createEAttribute(latexDocumentEClass, LATEX_DOCUMENT__DOCUMENT_CLASS);
		createEAttribute(latexDocumentEClass, LATEX_DOCUMENT__REPLACE_SUB_SUB_SECTION_BY_PARA);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		IntentDocumentPackage theIntentDocumentPackage = (IntentDocumentPackage)EPackage.Registry.INSTANCE.getEPackage(IntentDocumentPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(intentGenEClass, IntentGen.class, "IntentGen", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIntentGen_Documents(), this.getLatexDocument(), null, "documents", null, 0, -1, IntentGen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(latexDocumentEClass, LatexDocument.class, "LatexDocument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLatexDocument_Title(), ecorePackage.getEString(), "title", null, 1, 1, LatexDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLatexDocument_Authors(), ecorePackage.getEString(), "authors", null, 1, -1, LatexDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLatexDocument_Roots(), theIntentDocumentPackage.getIntentSection(), null, "roots", null, 0, -1, LatexDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLatexDocument_DocumentClass(), ecorePackage.getEString(), "documentClass", "book", 1, 1, LatexDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLatexDocument_ReplaceSubSubSectionByPara(), ecorePackage.getEBoolean(), "replaceSubSubSectionByPara", null, 0, 1, LatexDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ExportPackageImpl

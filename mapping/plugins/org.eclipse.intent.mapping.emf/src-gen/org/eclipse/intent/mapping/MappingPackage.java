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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to
 * represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.intent.mapping.MappingFactory
 * @model kind="package"
 * @generated
 */
public interface MappingPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "mapping";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/intent/mapping";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "mapping";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	MappingPackage eINSTANCE = org.eclipse.intent.mapping.impl.MappingPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.IEMFBaseElement <em>IEMF Base
	 * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.IEMFBaseElement
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getIEMFBaseElement()
	 * @generated
	 */
	int IEMF_BASE_ELEMENT = 3;

	/**
	 * The number of structural features of the '<em>IEMF Base Element</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IEMF_BASE_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IEMF Base Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IEMF_BASE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.LocationImpl <em>Location</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.LocationImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getLocation()
	 * @generated
	 */
	int LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION__CONTENTS = IEMF_BASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION__SOURCE_LINKS = IEMF_BASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION__TARGET_LINKS = IEMF_BASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION__CONTAINER = IEMF_BASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION__TYPE = IEMF_BASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION__MARKED_AS_DELETED = IEMF_BASE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Location</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION_FEATURE_COUNT = IEMF_BASE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Location</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION_OPERATION_COUNT = IEMF_BASE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.LinkImpl <em>Link</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.LinkImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__DESCRIPTION = IEMF_BASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__SOURCE = IEMF_BASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__TARGET = IEMF_BASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__TYPE = IEMF_BASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__REPORTS = IEMF_BASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Link</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = IEMF_BASE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Link</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = IEMF_BASE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.BaseImpl <em>Base</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.BaseImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getBase()
	 * @generated
	 */
	int BASE = 2;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BASE__CONTENTS = IEMF_BASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BASE__NAME = IEMF_BASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BASE__REPORTS = IEMF_BASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Container Providers</b></em>' attribute list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BASE__CONTAINER_PROVIDERS = IEMF_BASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Base</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BASE_FEATURE_COUNT = IEMF_BASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Base</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BASE_OPERATION_COUNT = IEMF_BASE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.TextLocationImpl <em>Text
	 * Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.TextLocationImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getTextLocation()
	 * @generated
	 */
	int TEXT_LOCATION = 4;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION__CONTENTS = LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION__SOURCE_LINKS = LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION__TARGET_LINKS = LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION__CONTAINER = LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION__TYPE = LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION__MARKED_AS_DELETED = LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>Start Offset</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION__START_OFFSET = LOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>End Offset</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION__END_OFFSET = LOCATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Text Location</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Text Location</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_LOCATION_OPERATION_COUNT = LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.TextContainer <em>Text Container</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.TextContainer
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getTextContainer()
	 * @generated
	 */
	int TEXT_CONTAINER = 5;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINER__CONTENTS = LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINER__SOURCE_LINKS = LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINER__TARGET_LINKS = LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINER__CONTAINER = LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINER__TYPE = LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINER__MARKED_AS_DELETED = LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINER__TEXT = LOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Container</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINER_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Text Container</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINER_OPERATION_COUNT = LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.EObjectLocationImpl <em>EObject
	 * Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.EObjectLocationImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getEObjectLocation()
	 * @generated
	 */
	int EOBJECT_LOCATION = 6;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__CONTENTS = LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__SOURCE_LINKS = LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__TARGET_LINKS = LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__CONTAINER = LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__TYPE = LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__MARKED_AS_DELETED = LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>URI Fragment</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__URI_FRAGMENT = LOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Saved URI Fragment</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__SAVED_URI_FRAGMENT = LOCATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__FEATURE_NAME = LOCATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION__INDEX = LOCATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>EObject Location</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>EObject Location</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LOCATION_OPERATION_COUNT = LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.LocationContainer <em>Location
	 * Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.LocationContainer
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getLocationContainer()
	 * @generated
	 */
	int LOCATION_CONTAINER = 7;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION_CONTAINER__CONTENTS = 0;

	/**
	 * The number of structural features of the '<em>Location Container</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Location Container</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.ReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.ReportImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getReport()
	 * @generated
	 */
	int REPORT = 8;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPORT__DESCRIPTION = IEMF_BASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Link</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPORT__LINK = IEMF_BASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Report</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPORT_FEATURE_COUNT = IEMF_BASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Report</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPORT_OPERATION_COUNT = IEMF_BASE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.CoupleImpl <em>Couple</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.CoupleImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCouple()
	 * @generated
	 */
	int COUPLE = 9;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COUPLE__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COUPLE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Couple</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COUPLE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Couple</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COUPLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.EObjectContainer <em>EObject
	 * Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.EObjectContainer
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getEObjectContainer()
	 * @generated
	 */
	int EOBJECT_CONTAINER = 10;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER__CONTENTS = LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER__SOURCE_LINKS = LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER__TARGET_LINKS = LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER__CONTAINER = LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER__TYPE = LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER__MARKED_AS_DELETED = LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>XMI Content</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER__XMI_CONTENT = LOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Saved URI Fragments</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER__SAVED_URI_FRAGMENTS = LOCATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>EObject Container</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>EObject Container</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER_OPERATION_COUNT = LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.CDOResourceNodeLocation <em>CDO Resource
	 * Node Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.CDOResourceNodeLocation
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOResourceNodeLocation()
	 * @generated
	 */
	int CDO_RESOURCE_NODE_LOCATION = 11;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_NODE_LOCATION__CONTENTS = LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_NODE_LOCATION__SOURCE_LINKS = LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_NODE_LOCATION__TARGET_LINKS = LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_NODE_LOCATION__CONTAINER = LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_NODE_LOCATION__TYPE = LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_NODE_LOCATION__MARKED_AS_DELETED = LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_NODE_LOCATION__PATH = LOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>CDO Resource Node Location</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_NODE_LOCATION_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>CDO Resource Node Location</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_NODE_LOCATION_OPERATION_COUNT = LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.CDOFileLocation <em>CDO File
	 * Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.CDOFileLocation
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOFileLocation()
	 * @generated
	 */
	int CDO_FILE_LOCATION = 12;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FILE_LOCATION__CONTENTS = CDO_RESOURCE_NODE_LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FILE_LOCATION__SOURCE_LINKS = CDO_RESOURCE_NODE_LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FILE_LOCATION__TARGET_LINKS = CDO_RESOURCE_NODE_LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FILE_LOCATION__CONTAINER = CDO_RESOURCE_NODE_LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FILE_LOCATION__TYPE = CDO_RESOURCE_NODE_LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FILE_LOCATION__MARKED_AS_DELETED = CDO_RESOURCE_NODE_LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FILE_LOCATION__PATH = CDO_RESOURCE_NODE_LOCATION__PATH;

	/**
	 * The number of structural features of the '<em>CDO File Location</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FILE_LOCATION_FEATURE_COUNT = CDO_RESOURCE_NODE_LOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>CDO File Location</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FILE_LOCATION_OPERATION_COUNT = CDO_RESOURCE_NODE_LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.CDOFolderLocationImpl <em>CDO Folder
	 * Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.CDOFolderLocationImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOFolderLocation()
	 * @generated
	 */
	int CDO_FOLDER_LOCATION = 13;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FOLDER_LOCATION__CONTENTS = CDO_RESOURCE_NODE_LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FOLDER_LOCATION__SOURCE_LINKS = CDO_RESOURCE_NODE_LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FOLDER_LOCATION__TARGET_LINKS = CDO_RESOURCE_NODE_LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FOLDER_LOCATION__CONTAINER = CDO_RESOURCE_NODE_LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FOLDER_LOCATION__TYPE = CDO_RESOURCE_NODE_LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FOLDER_LOCATION__MARKED_AS_DELETED = CDO_RESOURCE_NODE_LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FOLDER_LOCATION__PATH = CDO_RESOURCE_NODE_LOCATION__PATH;

	/**
	 * The number of structural features of the '<em>CDO Folder Location</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FOLDER_LOCATION_FEATURE_COUNT = CDO_RESOURCE_NODE_LOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>CDO Folder Location</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_FOLDER_LOCATION_OPERATION_COUNT = CDO_RESOURCE_NODE_LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.CDORepositoryLocationImpl <em>CDO
	 * Repository Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.CDORepositoryLocationImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDORepositoryLocation()
	 * @generated
	 */
	int CDO_REPOSITORY_LOCATION = 14;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__CONTENTS = LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__SOURCE_LINKS = LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__TARGET_LINKS = LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__CONTAINER = LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__TYPE = LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__MARKED_AS_DELETED = LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>URL</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__URL = LOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>UUID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__UUID = LOCATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__NAME = LOCATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Branch ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION__BRANCH_ID = LOCATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>CDO Repository Location</em>' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>CDO Repository Location</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_REPOSITORY_LOCATION_OPERATION_COUNT = LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.CDOBinaryResourceLocationImpl
	 * <em>CDO Binary Resource Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.CDOBinaryResourceLocationImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOBinaryResourceLocation()
	 * @generated
	 */
	int CDO_BINARY_RESOURCE_LOCATION = 15;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_BINARY_RESOURCE_LOCATION__CONTENTS = CDO_FILE_LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_BINARY_RESOURCE_LOCATION__SOURCE_LINKS = CDO_FILE_LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_BINARY_RESOURCE_LOCATION__TARGET_LINKS = CDO_FILE_LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_BINARY_RESOURCE_LOCATION__CONTAINER = CDO_FILE_LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_BINARY_RESOURCE_LOCATION__TYPE = CDO_FILE_LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_BINARY_RESOURCE_LOCATION__MARKED_AS_DELETED = CDO_FILE_LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_BINARY_RESOURCE_LOCATION__PATH = CDO_FILE_LOCATION__PATH;

	/**
	 * The number of structural features of the '<em>CDO Binary Resource Location</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_BINARY_RESOURCE_LOCATION_FEATURE_COUNT = CDO_FILE_LOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>CDO Binary Resource Location</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_BINARY_RESOURCE_LOCATION_OPERATION_COUNT = CDO_FILE_LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.CDOTextResourceLocationImpl <em>CDO
	 * Text Resource Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.CDOTextResourceLocationImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOTextResourceLocation()
	 * @generated
	 */
	int CDO_TEXT_RESOURCE_LOCATION = 16;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION__CONTENTS = CDO_FILE_LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION__SOURCE_LINKS = CDO_FILE_LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION__TARGET_LINKS = CDO_FILE_LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION__CONTAINER = CDO_FILE_LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION__TYPE = CDO_FILE_LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION__MARKED_AS_DELETED = CDO_FILE_LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION__PATH = CDO_FILE_LOCATION__PATH;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION__TEXT = CDO_FILE_LOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>CDO Text Resource Location</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION_FEATURE_COUNT = CDO_FILE_LOCATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>CDO Text Resource Location</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_TEXT_RESOURCE_LOCATION_OPERATION_COUNT = CDO_FILE_LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.intent.mapping.impl.CDOResourceLocationImpl <em>CDO
	 * Resource Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.intent.mapping.impl.CDOResourceLocationImpl
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOResourceLocation()
	 * @generated
	 */
	int CDO_RESOURCE_LOCATION = 17;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION__CONTENTS = CDO_FILE_LOCATION__CONTENTS;

	/**
	 * The feature id for the '<em><b>Source Links</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION__SOURCE_LINKS = CDO_FILE_LOCATION__SOURCE_LINKS;

	/**
	 * The feature id for the '<em><b>Target Links</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION__TARGET_LINKS = CDO_FILE_LOCATION__TARGET_LINKS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION__CONTAINER = CDO_FILE_LOCATION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION__TYPE = CDO_FILE_LOCATION__TYPE;

	/**
	 * The feature id for the '<em><b>Marked As Deleted</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION__MARKED_AS_DELETED = CDO_FILE_LOCATION__MARKED_AS_DELETED;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION__PATH = CDO_FILE_LOCATION__PATH;

	/**
	 * The feature id for the '<em><b>XMI Content</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION__XMI_CONTENT = CDO_FILE_LOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Saved URI Fragments</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION__SAVED_URI_FRAGMENTS = CDO_FILE_LOCATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>CDO Resource Location</em>' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION_FEATURE_COUNT = CDO_FILE_LOCATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>CDO Resource Location</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CDO_RESOURCE_LOCATION_OPERATION_COUNT = CDO_FILE_LOCATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>Type</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.io.Serializable
	 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 18;

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.Location <em>Location</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Location</em>'.
	 * @see org.eclipse.intent.mapping.Location
	 * @generated
	 */
	EClass getLocation();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.intent.mapping.Location#getSourceLinks <em>Source Links</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Source Links</em>'.
	 * @see org.eclipse.intent.mapping.Location#getSourceLinks()
	 * @see #getLocation()
	 * @generated
	 */
	EReference getLocation_SourceLinks();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.intent.mapping.Location#getTargetLinks <em>Target Links</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Target Links</em>'.
	 * @see org.eclipse.intent.mapping.Location#getTargetLinks()
	 * @see #getLocation()
	 * @generated
	 */
	EReference getLocation_TargetLinks();

	/**
	 * Returns the meta object for the container reference '
	 * {@link org.eclipse.intent.mapping.Location#getContainer <em>Container</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Container</em>'.
	 * @see org.eclipse.intent.mapping.Location#getContainer()
	 * @see #getLocation()
	 * @generated
	 */
	EReference getLocation_Container();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.intent.mapping.Location#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.intent.mapping.Location#getType()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.intent.mapping.Location#isMarkedAsDeleted
	 * <em>Marked As Deleted</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Marked As Deleted</em>'.
	 * @see org.eclipse.intent.mapping.Location#isMarkedAsDeleted()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_MarkedAsDeleted();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.Link <em>Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Link</em>'.
	 * @see org.eclipse.intent.mapping.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the attribute ' {@link org.eclipse.intent.mapping.Link#getDescription
	 * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.intent.mapping.Link#getDescription()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Description();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.intent.mapping.Link#getSource
	 * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see org.eclipse.intent.mapping.Link#getSource()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Source();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.intent.mapping.Link#getTarget
	 * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.eclipse.intent.mapping.Link#getTarget()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Target();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.intent.mapping.Link#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.intent.mapping.Link#getType()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.intent.mapping.Link#getReports
	 * <em>Reports</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Reports</em>'.
	 * @see org.eclipse.intent.mapping.Link#getReports()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Reports();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.Base <em>Base</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Base</em>'.
	 * @see org.eclipse.intent.mapping.Base
	 * @generated
	 */
	EClass getBase();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.intent.mapping.Base#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.intent.mapping.Base#getName()
	 * @see #getBase()
	 * @generated
	 */
	EAttribute getBase_Name();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link org.eclipse.intent.mapping.Base#getReports <em>Reports</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Reports</em>'.
	 * @see org.eclipse.intent.mapping.Base#getReports()
	 * @see #getBase()
	 * @generated
	 */
	EReference getBase_Reports();

	/**
	 * Returns the meta object for the attribute list
	 * '{@link org.eclipse.intent.mapping.Base#getContainerProviders <em>Container Providers</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Container Providers</em>'.
	 * @see org.eclipse.intent.mapping.Base#getContainerProviders()
	 * @see #getBase()
	 * @generated
	 */
	EAttribute getBase_ContainerProviders();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.IEMFBaseElement <em>IEMF Base
	 * Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IEMF Base Element</em>'.
	 * @see org.eclipse.intent.mapping.IEMFBaseElement
	 * @generated
	 */
	EClass getIEMFBaseElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.TextLocation <em>Text
	 * Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Text Location</em>'.
	 * @see org.eclipse.intent.mapping.TextLocation
	 * @generated
	 */
	EClass getTextLocation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.intent.mapping.TextLocation#getStartOffset <em>Start Offset</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Start Offset</em>'.
	 * @see org.eclipse.intent.mapping.TextLocation#getStartOffset()
	 * @see #getTextLocation()
	 * @generated
	 */
	EAttribute getTextLocation_StartOffset();

	/**
	 * Returns the meta object for the attribute ' {@link org.eclipse.intent.mapping.TextLocation#getEndOffset
	 * <em>End Offset</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>End Offset</em>'.
	 * @see org.eclipse.intent.mapping.TextLocation#getEndOffset()
	 * @see #getTextLocation()
	 * @generated
	 */
	EAttribute getTextLocation_EndOffset();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.TextContainer <em>Text
	 * Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Text Container</em>'.
	 * @see org.eclipse.intent.mapping.TextContainer
	 * @generated
	 */
	EClass getTextContainer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.intent.mapping.TextContainer#getText
	 * <em>Text</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.eclipse.intent.mapping.TextContainer#getText()
	 * @see #getTextContainer()
	 * @generated
	 */
	EAttribute getTextContainer_Text();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.EObjectLocation <em>EObject
	 * Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EObject Location</em>'.
	 * @see org.eclipse.intent.mapping.EObjectLocation
	 * @generated
	 */
	EClass getEObjectLocation();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.intent.mapping.EObjectLocation#getURIFragment <em>URI Fragment</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>URI Fragment</em>'.
	 * @see org.eclipse.intent.mapping.EObjectLocation#getURIFragment()
	 * @see #getEObjectLocation()
	 * @generated
	 */
	EAttribute getEObjectLocation_URIFragment();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.intent.mapping.EObjectLocation#getSavedURIFragment <em>Saved URI Fragment</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Saved URI Fragment</em>'.
	 * @see org.eclipse.intent.mapping.EObjectLocation#getSavedURIFragment()
	 * @see #getEObjectLocation()
	 * @generated
	 */
	EAttribute getEObjectLocation_SavedURIFragment();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.intent.mapping.EObjectLocation#getFeatureName <em>Feature Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Feature Name</em>'.
	 * @see org.eclipse.intent.mapping.EObjectLocation#getFeatureName()
	 * @see #getEObjectLocation()
	 * @generated
	 */
	EAttribute getEObjectLocation_FeatureName();

	/**
	 * Returns the meta object for the attribute ' {@link org.eclipse.intent.mapping.EObjectLocation#getIndex
	 * <em>Index</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see org.eclipse.intent.mapping.EObjectLocation#getIndex()
	 * @see #getEObjectLocation()
	 * @generated
	 */
	EAttribute getEObjectLocation_Index();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.LocationContainer <em>Location
	 * Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Location Container</em>'.
	 * @see org.eclipse.intent.mapping.LocationContainer
	 * @generated
	 */
	EClass getLocationContainer();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.intent.mapping.LocationContainer#getContents <em>Contents</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Contents</em>'.
	 * @see org.eclipse.intent.mapping.LocationContainer#getContents()
	 * @see #getLocationContainer()
	 * @generated
	 */
	EReference getLocationContainer_Contents();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.Report <em>Report</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Report</em>'.
	 * @see org.eclipse.intent.mapping.Report
	 * @generated
	 */
	EClass getReport();

	/**
	 * Returns the meta object for the attribute ' {@link org.eclipse.intent.mapping.Report#getDescription
	 * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.intent.mapping.Report#getDescription()
	 * @see #getReport()
	 * @generated
	 */
	EAttribute getReport_Description();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.intent.mapping.Report#getLink
	 * <em>Link</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Link</em>'.
	 * @see org.eclipse.intent.mapping.Report#getLink()
	 * @see #getReport()
	 * @generated
	 */
	EReference getReport_Link();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.Couple <em>Couple</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Couple</em>'.
	 * @see org.eclipse.intent.mapping.Couple
	 * @generated
	 */
	EClass getCouple();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.intent.mapping.Couple#getKey
	 * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.intent.mapping.Couple#getKey()
	 * @see #getCouple()
	 * @generated
	 */
	EAttribute getCouple_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.intent.mapping.Couple#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.intent.mapping.Couple#getValue()
	 * @see #getCouple()
	 * @generated
	 */
	EAttribute getCouple_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.EObjectContainer <em>EObject
	 * Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EObject Container</em>'.
	 * @see org.eclipse.intent.mapping.EObjectContainer
	 * @generated
	 */
	EClass getEObjectContainer();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.intent.mapping.EObjectContainer#getXMIContent <em>XMI Content</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>XMI Content</em>'.
	 * @see org.eclipse.intent.mapping.EObjectContainer#getXMIContent()
	 * @see #getEObjectContainer()
	 * @generated
	 */
	EAttribute getEObjectContainer_XMIContent();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link org.eclipse.intent.mapping.EObjectContainer#getSavedURIFragments <em>Saved URI
	 * Fragments</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Saved URI Fragments</em>'.
	 * @see org.eclipse.intent.mapping.EObjectContainer#getSavedURIFragments()
	 * @see #getEObjectContainer()
	 * @generated
	 */
	EReference getEObjectContainer_SavedURIFragments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.CDOResourceNodeLocation <em>CDO
	 * Resource Node Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CDO Resource Node Location</em>'.
	 * @see org.eclipse.intent.mapping.CDOResourceNodeLocation
	 * @generated
	 */
	EClass getCDOResourceNodeLocation();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.intent.mapping.CDOResourceNodeLocation#getPath <em>Path</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.eclipse.intent.mapping.CDOResourceNodeLocation#getPath()
	 * @see #getCDOResourceNodeLocation()
	 * @generated
	 */
	EAttribute getCDOResourceNodeLocation_Path();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.CDOFileLocation <em>CDO File
	 * Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CDO File Location</em>'.
	 * @see org.eclipse.intent.mapping.CDOFileLocation
	 * @generated
	 */
	EClass getCDOFileLocation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.CDOFolderLocation <em>CDO Folder
	 * Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CDO Folder Location</em>'.
	 * @see org.eclipse.intent.mapping.CDOFolderLocation
	 * @generated
	 */
	EClass getCDOFolderLocation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.CDORepositoryLocation <em>CDO
	 * Repository Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CDO Repository Location</em>'.
	 * @see org.eclipse.intent.mapping.CDORepositoryLocation
	 * @generated
	 */
	EClass getCDORepositoryLocation();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.intent.mapping.CDORepositoryLocation#getURL <em>URL</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>URL</em>'.
	 * @see org.eclipse.intent.mapping.CDORepositoryLocation#getURL()
	 * @see #getCDORepositoryLocation()
	 * @generated
	 */
	EAttribute getCDORepositoryLocation_URL();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.intent.mapping.CDORepositoryLocation#getUUID <em>UUID</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>UUID</em>'.
	 * @see org.eclipse.intent.mapping.CDORepositoryLocation#getUUID()
	 * @see #getCDORepositoryLocation()
	 * @generated
	 */
	EAttribute getCDORepositoryLocation_UUID();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.intent.mapping.CDORepositoryLocation#getName <em>Name</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.intent.mapping.CDORepositoryLocation#getName()
	 * @see #getCDORepositoryLocation()
	 * @generated
	 */
	EAttribute getCDORepositoryLocation_Name();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.intent.mapping.CDORepositoryLocation#getBranchID <em>Branch ID</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Branch ID</em>'.
	 * @see org.eclipse.intent.mapping.CDORepositoryLocation#getBranchID()
	 * @see #getCDORepositoryLocation()
	 * @generated
	 */
	EAttribute getCDORepositoryLocation_BranchID();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.CDOBinaryResourceLocation <em>CDO
	 * Binary Resource Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CDO Binary Resource Location</em>'.
	 * @see org.eclipse.intent.mapping.CDOBinaryResourceLocation
	 * @generated
	 */
	EClass getCDOBinaryResourceLocation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.CDOTextResourceLocation <em>CDO
	 * Text Resource Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CDO Text Resource Location</em>'.
	 * @see org.eclipse.intent.mapping.CDOTextResourceLocation
	 * @generated
	 */
	EClass getCDOTextResourceLocation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.intent.mapping.CDOResourceLocation <em>CDO
	 * Resource Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CDO Resource Location</em>'.
	 * @see org.eclipse.intent.mapping.CDOResourceLocation
	 * @generated
	 */
	EClass getCDOResourceLocation();

	/**
	 * Returns the meta object for data type '{@link java.io.Serializable <em>Type</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Type</em>'.
	 * @see java.io.Serializable
	 * @model instanceClass="java.io.Serializable"
	 * @generated
	 */
	EDataType getType();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MappingFactory getMappingFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.LocationImpl
		 * <em>Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.LocationImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getLocation()
		 * @generated
		 */
		EClass LOCATION = eINSTANCE.getLocation();

		/**
		 * The meta object literal for the '<em><b>Source Links</b></em>' reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LOCATION__SOURCE_LINKS = eINSTANCE.getLocation_SourceLinks();

		/**
		 * The meta object literal for the '<em><b>Target Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LOCATION__TARGET_LINKS = eINSTANCE.getLocation_TargetLinks();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' container reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LOCATION__CONTAINER = eINSTANCE.getLocation_Container();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LOCATION__TYPE = eINSTANCE.getLocation_Type();

		/**
		 * The meta object literal for the '<em><b>Marked As Deleted</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LOCATION__MARKED_AS_DELETED = eINSTANCE.getLocation_MarkedAsDeleted();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.LinkImpl <em>Link</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.LinkImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LINK__DESCRIPTION = eINSTANCE.getLink_Description();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LINK__SOURCE = eINSTANCE.getLink_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LINK__TARGET = eINSTANCE.getLink_Target();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LINK__TYPE = eINSTANCE.getLink_Type();

		/**
		 * The meta object literal for the '<em><b>Reports</b></em>' reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LINK__REPORTS = eINSTANCE.getLink_Reports();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.BaseImpl <em>Base</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.BaseImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getBase()
		 * @generated
		 */
		EClass BASE = eINSTANCE.getBase();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BASE__NAME = eINSTANCE.getBase_Name();

		/**
		 * The meta object literal for the '<em><b>Reports</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference BASE__REPORTS = eINSTANCE.getBase_Reports();

		/**
		 * The meta object literal for the '<em><b>Container Providers</b></em>' attribute list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BASE__CONTAINER_PROVIDERS = eINSTANCE.getBase_ContainerProviders();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.IEMFBaseElement <em>IEMF Base
		 * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.IEMFBaseElement
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getIEMFBaseElement()
		 * @generated
		 */
		EClass IEMF_BASE_ELEMENT = eINSTANCE.getIEMFBaseElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.TextLocationImpl <em>Text
		 * Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.TextLocationImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getTextLocation()
		 * @generated
		 */
		EClass TEXT_LOCATION = eINSTANCE.getTextLocation();

		/**
		 * The meta object literal for the '<em><b>Start Offset</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TEXT_LOCATION__START_OFFSET = eINSTANCE.getTextLocation_StartOffset();

		/**
		 * The meta object literal for the '<em><b>End Offset</b></em>' attribute feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TEXT_LOCATION__END_OFFSET = eINSTANCE.getTextLocation_EndOffset();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.TextContainer <em>Text
		 * Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.TextContainer
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getTextContainer()
		 * @generated
		 */
		EClass TEXT_CONTAINER = eINSTANCE.getTextContainer();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TEXT_CONTAINER__TEXT = eINSTANCE.getTextContainer_Text();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.EObjectLocationImpl
		 * <em>EObject Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.EObjectLocationImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getEObjectLocation()
		 * @generated
		 */
		EClass EOBJECT_LOCATION = eINSTANCE.getEObjectLocation();

		/**
		 * The meta object literal for the '<em><b>URI Fragment</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOBJECT_LOCATION__URI_FRAGMENT = eINSTANCE.getEObjectLocation_URIFragment();

		/**
		 * The meta object literal for the '<em><b>Saved URI Fragment</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOBJECT_LOCATION__SAVED_URI_FRAGMENT = eINSTANCE.getEObjectLocation_SavedURIFragment();

		/**
		 * The meta object literal for the '<em><b>Feature Name</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOBJECT_LOCATION__FEATURE_NAME = eINSTANCE.getEObjectLocation_FeatureName();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOBJECT_LOCATION__INDEX = eINSTANCE.getEObjectLocation_Index();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.LocationContainer <em>Location
		 * Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.LocationContainer
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getLocationContainer()
		 * @generated
		 */
		EClass LOCATION_CONTAINER = eINSTANCE.getLocationContainer();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LOCATION_CONTAINER__CONTENTS = eINSTANCE.getLocationContainer_Contents();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.ReportImpl
		 * <em>Report</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.ReportImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getReport()
		 * @generated
		 */
		EClass REPORT = eINSTANCE.getReport();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPORT__DESCRIPTION = eINSTANCE.getReport_Description();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference REPORT__LINK = eINSTANCE.getReport_Link();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.CoupleImpl
		 * <em>Couple</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.CoupleImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCouple()
		 * @generated
		 */
		EClass COUPLE = eINSTANCE.getCouple();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COUPLE__KEY = eINSTANCE.getCouple_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COUPLE__VALUE = eINSTANCE.getCouple_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.EObjectContainer <em>EObject
		 * Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.EObjectContainer
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getEObjectContainer()
		 * @generated
		 */
		EClass EOBJECT_CONTAINER = eINSTANCE.getEObjectContainer();

		/**
		 * The meta object literal for the '<em><b>XMI Content</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOBJECT_CONTAINER__XMI_CONTENT = eINSTANCE.getEObjectContainer_XMIContent();

		/**
		 * The meta object literal for the '<em><b>Saved URI Fragments</b></em>' containment reference list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EOBJECT_CONTAINER__SAVED_URI_FRAGMENTS = eINSTANCE.getEObjectContainer_SavedURIFragments();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.CDOResourceNodeLocation <em>CDO
		 * Resource Node Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.CDOResourceNodeLocation
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOResourceNodeLocation()
		 * @generated
		 */
		EClass CDO_RESOURCE_NODE_LOCATION = eINSTANCE.getCDOResourceNodeLocation();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CDO_RESOURCE_NODE_LOCATION__PATH = eINSTANCE.getCDOResourceNodeLocation_Path();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.CDOFileLocation <em>CDO File
		 * Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.CDOFileLocation
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOFileLocation()
		 * @generated
		 */
		EClass CDO_FILE_LOCATION = eINSTANCE.getCDOFileLocation();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.CDOFolderLocationImpl
		 * <em>CDO Folder Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.CDOFolderLocationImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOFolderLocation()
		 * @generated
		 */
		EClass CDO_FOLDER_LOCATION = eINSTANCE.getCDOFolderLocation();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.CDORepositoryLocationImpl
		 * <em>CDO Repository Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.CDORepositoryLocationImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDORepositoryLocation()
		 * @generated
		 */
		EClass CDO_REPOSITORY_LOCATION = eINSTANCE.getCDORepositoryLocation();

		/**
		 * The meta object literal for the '<em><b>URL</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CDO_REPOSITORY_LOCATION__URL = eINSTANCE.getCDORepositoryLocation_URL();

		/**
		 * The meta object literal for the '<em><b>UUID</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CDO_REPOSITORY_LOCATION__UUID = eINSTANCE.getCDORepositoryLocation_UUID();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CDO_REPOSITORY_LOCATION__NAME = eINSTANCE.getCDORepositoryLocation_Name();

		/**
		 * The meta object literal for the '<em><b>Branch ID</b></em>' attribute feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CDO_REPOSITORY_LOCATION__BRANCH_ID = eINSTANCE.getCDORepositoryLocation_BranchID();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.intent.mapping.impl.CDOBinaryResourceLocationImpl <em>CDO Binary Resource
		 * Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.CDOBinaryResourceLocationImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOBinaryResourceLocation()
		 * @generated
		 */
		EClass CDO_BINARY_RESOURCE_LOCATION = eINSTANCE.getCDOBinaryResourceLocation();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.CDOTextResourceLocationImpl
		 * <em>CDO Text Resource Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.CDOTextResourceLocationImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOTextResourceLocation()
		 * @generated
		 */
		EClass CDO_TEXT_RESOURCE_LOCATION = eINSTANCE.getCDOTextResourceLocation();

		/**
		 * The meta object literal for the '{@link org.eclipse.intent.mapping.impl.CDOResourceLocationImpl
		 * <em>CDO Resource Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.intent.mapping.impl.CDOResourceLocationImpl
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getCDOResourceLocation()
		 * @generated
		 */
		EClass CDO_RESOURCE_LOCATION = eINSTANCE.getCDOResourceLocation();

		/**
		 * The meta object literal for the '<em>Type</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see java.io.Serializable
		 * @see org.eclipse.intent.mapping.impl.MappingPackageImpl#getType()
		 * @generated
		 */
		EDataType TYPE = eINSTANCE.getType();

	}

} // MappingPackage

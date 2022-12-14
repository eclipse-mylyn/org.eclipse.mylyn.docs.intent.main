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
package org.eclipse.intent.mapping.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.intent.mapping.Base;
import org.eclipse.intent.mapping.CDOBinaryResourceLocation;
import org.eclipse.intent.mapping.CDOFileLocation;
import org.eclipse.intent.mapping.CDOFolderLocation;
import org.eclipse.intent.mapping.CDORepositoryLocation;
import org.eclipse.intent.mapping.CDOResourceLocation;
import org.eclipse.intent.mapping.CDOResourceNodeLocation;
import org.eclipse.intent.mapping.CDOTextResourceLocation;
import org.eclipse.intent.mapping.Couple;
import org.eclipse.intent.mapping.EObjectContainer;
import org.eclipse.intent.mapping.EObjectLocation;
import org.eclipse.intent.mapping.IEMFBaseElement;
import org.eclipse.intent.mapping.Link;
import org.eclipse.intent.mapping.Location;
import org.eclipse.intent.mapping.LocationContainer;
import org.eclipse.intent.mapping.MappingPackage;
import org.eclipse.intent.mapping.Report;
import org.eclipse.intent.mapping.TextContainer;
import org.eclipse.intent.mapping.TextLocation;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.intent.mapping.MappingPackage
 * @generated
 */
public class MappingAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static MappingPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MappingAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = MappingPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
	 * implementation returns <code>true</code> if the object is either the model's package or is an instance
	 * object of the model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected MappingSwitch<Adapter> modelSwitch = new MappingSwitch<Adapter>() {
		@Override
		public Adapter caseLocation(Location object) {
			return createLocationAdapter();
		}

		@Override
		public Adapter caseLink(Link object) {
			return createLinkAdapter();
		}

		@Override
		public Adapter caseBase(Base object) {
			return createBaseAdapter();
		}

		@Override
		public Adapter caseIEMFBaseElement(IEMFBaseElement object) {
			return createIEMFBaseElementAdapter();
		}

		@Override
		public Adapter caseTextLocation(TextLocation object) {
			return createTextLocationAdapter();
		}

		@Override
		public Adapter caseTextContainer(TextContainer object) {
			return createTextContainerAdapter();
		}

		@Override
		public Adapter caseEObjectLocation(EObjectLocation object) {
			return createEObjectLocationAdapter();
		}

		@Override
		public Adapter caseLocationContainer(LocationContainer object) {
			return createLocationContainerAdapter();
		}

		@Override
		public Adapter caseReport(Report object) {
			return createReportAdapter();
		}

		@Override
		public Adapter caseCouple(Couple object) {
			return createCoupleAdapter();
		}

		@Override
		public Adapter caseEObjectContainer(EObjectContainer object) {
			return createEObjectContainerAdapter();
		}

		@Override
		public Adapter caseCDOResourceNodeLocation(CDOResourceNodeLocation object) {
			return createCDOResourceNodeLocationAdapter();
		}

		@Override
		public Adapter caseCDOFileLocation(CDOFileLocation object) {
			return createCDOFileLocationAdapter();
		}

		@Override
		public Adapter caseCDOFolderLocation(CDOFolderLocation object) {
			return createCDOFolderLocationAdapter();
		}

		@Override
		public Adapter caseCDORepositoryLocation(CDORepositoryLocation object) {
			return createCDORepositoryLocationAdapter();
		}

		@Override
		public Adapter caseCDOBinaryResourceLocation(CDOBinaryResourceLocation object) {
			return createCDOBinaryResourceLocationAdapter();
		}

		@Override
		public Adapter caseCDOTextResourceLocation(CDOTextResourceLocation object) {
			return createCDOTextResourceLocationAdapter();
		}

		@Override
		public Adapter caseCDOResourceLocation(CDOResourceLocation object) {
			return createCDOResourceLocationAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.Location
	 * <em>Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.Location
	 * @generated
	 */
	public Adapter createLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.Link <em>Link</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.Link
	 * @generated
	 */
	public Adapter createLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.Base <em>Base</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.Base
	 * @generated
	 */
	public Adapter createBaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class ' {@link org.eclipse.intent.mapping.IEMFBaseElement
	 * <em>IEMF Base Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.IEMFBaseElement
	 * @generated
	 */
	public Adapter createIEMFBaseElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.TextLocation <em>Text
	 * Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.TextLocation
	 * @generated
	 */
	public Adapter createTextLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.TextContainer <em>Text
	 * Container</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.TextContainer
	 * @generated
	 */
	public Adapter createTextContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class ' {@link org.eclipse.intent.mapping.EObjectLocation
	 * <em>EObject Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.EObjectLocation
	 * @generated
	 */
	public Adapter createEObjectLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class ' {@link org.eclipse.intent.mapping.LocationContainer
	 * <em>Location Container</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.LocationContainer
	 * @generated
	 */
	public Adapter createLocationContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.Report
	 * <em>Report</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.Report
	 * @generated
	 */
	public Adapter createReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.Couple
	 * <em>Couple</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.Couple
	 * @generated
	 */
	public Adapter createCoupleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.EObjectContainer
	 * <em>EObject Container</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.EObjectContainer
	 * @generated
	 */
	public Adapter createEObjectContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.CDOResourceNodeLocation
	 * <em>CDO Resource Node Location</em>}'. <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.CDOResourceNodeLocation
	 * @generated
	 */
	public Adapter createCDOResourceNodeLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.CDOFileLocation <em>CDO
	 * File Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.CDOFileLocation
	 * @generated
	 */
	public Adapter createCDOFileLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.CDOFolderLocation
	 * <em>CDO Folder Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.CDOFolderLocation
	 * @generated
	 */
	public Adapter createCDOFolderLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.CDORepositoryLocation
	 * <em>CDO Repository Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.CDORepositoryLocation
	 * @generated
	 */
	public Adapter createCDORepositoryLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.eclipse.intent.mapping.CDOBinaryResourceLocation <em>CDO Binary Resource Location</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.CDOBinaryResourceLocation
	 * @generated
	 */
	public Adapter createCDOBinaryResourceLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.CDOTextResourceLocation
	 * <em>CDO Text Resource Location</em>}'. <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.CDOTextResourceLocation
	 * @generated
	 */
	public Adapter createCDOTextResourceLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.CDOResourceLocation
	 * <em>CDO Resource Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.CDOResourceLocation
	 * @generated
	 */
	public Adapter createCDOResourceLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns
	 * null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // MappingAdapterFactory

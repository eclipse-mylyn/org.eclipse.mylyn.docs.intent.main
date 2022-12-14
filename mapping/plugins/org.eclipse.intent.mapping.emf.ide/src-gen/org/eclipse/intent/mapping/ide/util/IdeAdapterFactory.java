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
package org.eclipse.intent.mapping.ide.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.intent.mapping.EObjectContainer;
import org.eclipse.intent.mapping.IEMFBaseElement;
import org.eclipse.intent.mapping.Location;
import org.eclipse.intent.mapping.LocationContainer;
import org.eclipse.intent.mapping.TextContainer;
import org.eclipse.intent.mapping.ide.EObjectFileLocation;
import org.eclipse.intent.mapping.ide.FileLocation;
import org.eclipse.intent.mapping.ide.IdePackage;
import org.eclipse.intent.mapping.ide.ResourceLocation;
import org.eclipse.intent.mapping.ide.TextFileLocation;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.intent.mapping.ide.IdePackage
 * @generated
 */
public class IdeAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static IdePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IdeAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = IdePackage.eINSTANCE;
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
	protected IdeSwitch<Adapter> modelSwitch = new IdeSwitch<Adapter>() {
		@Override
		public Adapter caseResourceLocation(ResourceLocation object) {
			return createResourceLocationAdapter();
		}

		@Override
		public Adapter caseFileLocation(FileLocation object) {
			return createFileLocationAdapter();
		}

		@Override
		public Adapter caseTextFileLocation(TextFileLocation object) {
			return createTextFileLocationAdapter();
		}

		@Override
		public Adapter caseEObjectFileLocation(EObjectFileLocation object) {
			return createEObjectFileLocationAdapter();
		}

		@Override
		public Adapter caseIEMFBaseElement(IEMFBaseElement object) {
			return createIEMFBaseElementAdapter();
		}

		@Override
		public Adapter caseLocationContainer(LocationContainer object) {
			return createLocationContainerAdapter();
		}

		@Override
		public Adapter caseLocation(Location object) {
			return createLocationAdapter();
		}

		@Override
		public Adapter caseTextContainer(TextContainer object) {
			return createTextContainerAdapter();
		}

		@Override
		public Adapter caseEObjectContainer(EObjectContainer object) {
			return createEObjectContainerAdapter();
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
	 * Creates a new adapter for an object of class ' {@link org.eclipse.intent.mapping.ide.ResourceLocation
	 * <em>Resource Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.ide.ResourceLocation
	 * @generated
	 */
	public Adapter createResourceLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class ' {@link org.eclipse.intent.mapping.ide.FileLocation
	 * <em>File Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.ide.FileLocation
	 * @generated
	 */
	public Adapter createFileLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class ' {@link org.eclipse.intent.mapping.ide.TextFileLocation
	 * <em>Text File Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.ide.TextFileLocation
	 * @generated
	 */
	public Adapter createTextFileLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.intent.mapping.ide.EObjectFileLocation
	 * <em>EObject File Location</em>}'. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.intent.mapping.ide.EObjectFileLocation
	 * @generated
	 */
	public Adapter createEObjectFileLocationAdapter() {
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
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns
	 * null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // IdeAdapterFactory

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

import java.io.Serializable;

import org.eclipse.emf.common.util.EList;
import org.eclipse.intent.mapping.base.ILink;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.ILocationContainer;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Location</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.intent.mapping.Location#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.intent.mapping.Location#getScope <em>Scope</em>}</li>
 * <li>{@link org.eclipse.intent.mapping.Location#getSourceLinks <em>Source Links</em>}</li>
 * <li>{@link org.eclipse.intent.mapping.Location#getTargetLinks <em>Target Links</em>}</li>
 * <li>{@link org.eclipse.intent.mapping.Location#getContents <em>Contents</em>}</li>
 * <li>{@link org.eclipse.intent.mapping.Location#getContainer <em>Container</em>}</li>
 * <li>{@link org.eclipse.intent.mapping.Location#getReferencingScopes <em>Referencing Scopes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.intent.mapping.MappingPackage#getLocation()
 * @model interface="true" abstract="true"
 * @generated NOT
 */
public interface Location extends IEMFBaseElement, LocationContainer, ILocation {

	/**
	 * Returns the value of the '<em><b>Source Links</b></em>' reference list. The list contents are of type
	 * {@link org.eclipse.intent.mapping.Link}. It is bidirectional and its opposite is '
	 * {@link org.eclipse.intent.mapping.Link#getTarget <em>Target</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Links</em>' reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Source Links</em>' reference list.
	 * @see org.eclipse.intent.mapping.MappingPackage#getLocation_SourceLinks()
	 * @see org.eclipse.intent.mapping.Link#getTarget
	 * @model opposite="target"
	 * @generated NOT
	 */
	EList<ILink> getSourceLinks();

	/**
	 * Returns the value of the '<em><b>Target Links</b></em>' containment reference list. The list contents
	 * are of type {@link org.eclipse.intent.mapping.Link}. It is bidirectional and its opposite is
	 * '{@link org.eclipse.intent.mapping.Link#getSource <em>Source</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Links</em>' containment reference list isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Target Links</em>' containment reference list.
	 * @see org.eclipse.intent.mapping.MappingPackage#getLocation_TargetLinks()
	 * @see org.eclipse.intent.mapping.Link#getSource
	 * @model opposite="source" containment="true"
	 * @generated NOT
	 */
	EList<ILink> getTargetLinks();

	/**
	 * Returns the value of the '<em><b>Contents</b></em>' containment reference list. The list contents are
	 * of type {@link org.eclipse.intent.mapping.Location}. It is bidirectional and its opposite is
	 * '{@link org.eclipse.intent.mapping.Location#getContainer <em>Container</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' containment reference list isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Contents</em>' containment reference list.
	 * @see org.eclipse.intent.mapping.MappingPackage#getLocation_Contents()
	 * @see org.eclipse.intent.mapping.Location#getContainer
	 * @model opposite="container" containment="true"
	 * @generated NOT
	 */
	EList<ILocation> getContents();

	/**
	 * Returns the value of the '<em><b>Container</b></em>' container reference. It is bidirectional and its
	 * opposite is '{@link org.eclipse.intent.mapping.LocationContainer#getContents <em>Contents</em>}'. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container</em>' container reference isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Container</em>' container reference.
	 * @see #setContainer(LocationContainer)
	 * @see org.eclipse.intent.mapping.MappingPackage#getLocation_Container()
	 * @see org.eclipse.intent.mapping.LocationContainer#getContents
	 * @model opposite="contents" transient="false"
	 * @generated
	 */
	LocationContainer getContainer();

	/**
	 * Sets the value of the '{@link org.eclipse.intent.mapping.Location#getContainer <em>Container</em>}'
	 * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Container</em>' container reference.
	 * @see #getContainer()
	 * @generated NOT
	 */
	void setContainer(ILocationContainer value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(Serializable)
	 * @see org.eclipse.intent.mapping.MappingPackage#getLocation_Type()
	 * @model dataType="org.eclipse.mylyn.docs.intent.mapping.Type"
	 * @generated
	 */
	Serializable getType();

	/**
	 * Sets the value of the '{@link org.eclipse.intent.mapping.Location#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(Serializable value);

	/**
	 * Returns the value of the '<em><b>Marked As Deleted</b></em>' attribute. The default value is
	 * <code>"false"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Marked As Deleted</em>' attribute isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Marked As Deleted</em>' attribute.
	 * @see #setMarkedAsDeleted(boolean)
	 * @see org.eclipse.intent.mapping.MappingPackage#getLocation_MarkedAsDeleted()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isMarkedAsDeleted();

	/**
	 * Sets the value of the '{@link org.eclipse.intent.mapping.Location#isMarkedAsDeleted <em>Marked As
	 * Deleted</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Marked As Deleted</em>' attribute.
	 * @see #isMarkedAsDeleted()
	 * @generated
	 */
	void setMarkedAsDeleted(boolean value);

} // Location

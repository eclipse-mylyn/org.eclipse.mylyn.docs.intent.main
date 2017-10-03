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
package org.eclipse.intent.mapping.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.intent.mapping.util.MappingAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers. The adapters
 * generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged
 * fireNotifyChanged}. The adapters also support Eclipse property sheets. Note that most of the adapters are
 * shared among multiple instances. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class MappingItemProviderAdapterFactory extends MappingAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MappingItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.intent.mapping.Link} instances.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected LinkItemProvider linkItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.Link}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createLinkAdapter() {
		if (linkItemProvider == null) {
			linkItemProvider = new LinkItemProvider(this);
		}

		return linkItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.intent.mapping.Base} instances.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected BaseItemProvider baseItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.Base}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createBaseAdapter() {
		if (baseItemProvider == null) {
			baseItemProvider = new BaseItemProvider(this);
		}

		return baseItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.intent.mapping.TextLocation}
	 * instances. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TextLocationItemProvider textLocationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.TextLocation}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createTextLocationAdapter() {
		if (textLocationItemProvider == null) {
			textLocationItemProvider = new TextLocationItemProvider(this);
		}

		return textLocationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.intent.mapping.EObjectLocation}
	 * instances. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EObjectLocationItemProvider eObjectLocationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.EObjectLocation}. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createEObjectLocationAdapter() {
		if (eObjectLocationItemProvider == null) {
			eObjectLocationItemProvider = new EObjectLocationItemProvider(this);
		}

		return eObjectLocationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.intent.mapping.Report} instances.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ReportItemProvider reportItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.Report}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createReportAdapter() {
		if (reportItemProvider == null) {
			reportItemProvider = new ReportItemProvider(this);
		}

		return reportItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.intent.mapping.Couple} instances.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CoupleItemProvider coupleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.Couple}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createCoupleAdapter() {
		if (coupleItemProvider == null) {
			coupleItemProvider = new CoupleItemProvider(this);
		}

		return coupleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.intent.mapping.CDOFolderLocation}
	 * instances. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CDOFolderLocationItemProvider cdoFolderLocationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.CDOFolderLocation}. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createCDOFolderLocationAdapter() {
		if (cdoFolderLocationItemProvider == null) {
			cdoFolderLocationItemProvider = new CDOFolderLocationItemProvider(this);
		}

		return cdoFolderLocationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.intent.mapping.CDORepositoryLocation} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected CDORepositoryLocationItemProvider cdoRepositoryLocationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.CDORepositoryLocation}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createCDORepositoryLocationAdapter() {
		if (cdoRepositoryLocationItemProvider == null) {
			cdoRepositoryLocationItemProvider = new CDORepositoryLocationItemProvider(this);
		}

		return cdoRepositoryLocationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.intent.mapping.CDOBinaryResourceLocation} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected CDOBinaryResourceLocationItemProvider cdoBinaryResourceLocationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.CDOBinaryResourceLocation}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createCDOBinaryResourceLocationAdapter() {
		if (cdoBinaryResourceLocationItemProvider == null) {
			cdoBinaryResourceLocationItemProvider = new CDOBinaryResourceLocationItemProvider(this);
		}

		return cdoBinaryResourceLocationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link org.eclipse.intent.mapping.CDOTextResourceLocation} instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected CDOTextResourceLocationItemProvider cdoTextResourceLocationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.CDOTextResourceLocation}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createCDOTextResourceLocationAdapter() {
		if (cdoTextResourceLocationItemProvider == null) {
			cdoTextResourceLocationItemProvider = new CDOTextResourceLocationItemProvider(this);
		}

		return cdoTextResourceLocationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.intent.mapping.CDOResourceLocation}
	 * instances. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CDOResourceLocationItemProvider cdoResourceLocationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.intent.mapping.CDOResourceLocation}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createCDOResourceLocationAdapter() {
		if (cdoResourceLocationItemProvider == null) {
			cdoResourceLocationItemProvider = new CDOResourceLocationItemProvider(this);
		}

		return cdoResourceLocationItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void dispose() {
		if (linkItemProvider != null)
			linkItemProvider.dispose();
		if (baseItemProvider != null)
			baseItemProvider.dispose();
		if (textLocationItemProvider != null)
			textLocationItemProvider.dispose();
		if (eObjectLocationItemProvider != null)
			eObjectLocationItemProvider.dispose();
		if (reportItemProvider != null)
			reportItemProvider.dispose();
		if (coupleItemProvider != null)
			coupleItemProvider.dispose();
		if (cdoFolderLocationItemProvider != null)
			cdoFolderLocationItemProvider.dispose();
		if (cdoRepositoryLocationItemProvider != null)
			cdoRepositoryLocationItemProvider.dispose();
		if (cdoBinaryResourceLocationItemProvider != null)
			cdoBinaryResourceLocationItemProvider.dispose();
		if (cdoTextResourceLocationItemProvider != null)
			cdoTextResourceLocationItemProvider.dispose();
		if (cdoResourceLocationItemProvider != null)
			cdoResourceLocationItemProvider.dispose();
	}

}

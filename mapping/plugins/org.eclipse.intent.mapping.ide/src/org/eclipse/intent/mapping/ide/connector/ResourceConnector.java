/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation and/or initial documentation
 *    ...
 *******************************************************************************/
package org.eclipse.intent.mapping.ide.connector;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.intent.mapping.MappingUtils;
import org.eclipse.intent.mapping.base.IBase;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.ILocationContainer;
import org.eclipse.intent.mapping.base.ILocationDescriptor;
import org.eclipse.intent.mapping.connector.AbstractConnector;
import org.eclipse.intent.mapping.content.IFileType;
import org.eclipse.intent.mapping.ide.Activator;
import org.eclipse.intent.mapping.ide.IdeMappingUtils;
import org.eclipse.intent.mapping.ide.resource.IFileLocation;
import org.eclipse.intent.mapping.ide.resource.IResourceLocation;

/**
 * An {@link IResource} {@link org.eclipse.intent.mapping.connector.IConnector IConnector}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ResourceConnector extends AbstractConnector {

	/**
	 * The {@link ResourceLocationListener} reacting to workspace changes.
	 */
	private final ResourceLocationListener resourceLocationListener;

	/**
	 * Constructor.
	 */
	public ResourceConnector() {
		resourceLocationListener = new ResourceLocationListener(true, this);
		Activator.getDefault().setResourceLocationListener(resourceLocationListener);
	}

	@Override
	public ILocation getLocation(ILocationContainer container, Object element) {
		if (element instanceof IResource) {
			for (ILocation child : container.getContents()) {
				if (match(child, element)) {
					return child;
				}
			}
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.connector.IConnector#getLocationType(java.lang.Class, java.lang.Object)
	 */
	public Class<? extends ILocation> getLocationType(Class<? extends ILocationContainer> containerType,
			Object element) {
		final Class<? extends ILocation> res;

		if (element instanceof IFile) {
			res = getIFileLocationType((IFile)element);
		} else if (element instanceof IResource) {
			res = getType();
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * Gets the {@link IFileLocation} for the given {@link IFile}.
	 * 
	 * @param file
	 *            the {@link IFile}
	 * @return the {@link IFileLocation} for the given {@link IFile} if any, <code>null</code> otherwise
	 */
	private Class<? extends IFileLocation> getIFileLocationType(IFile file) {
		Class<? extends IFileLocation> res = null;

		final IFileConnectorDelegate delegate = getDelegate(file);
		if (delegate != null) {
			res = delegate.getFileLocationType();
		}

		return res;
	}

	/**
	 * Gets the {@link IFileConnectorDelegate} for the given {@link IFile}.
	 * 
	 * @param file
	 *            the {@link IFile}
	 * @return the {@link IFileConnectorDelegate} for the given {@link IFile} if any, <code>null</code>
	 *         otherwise
	 */
	private IFileConnectorDelegate getDelegate(IFile file) {
		IFileConnectorDelegate res = null;

		if (file.exists()) {
			try {
				InputStream contents = file.getContents();
				final IFileType fileType = MappingUtils.getFileTypeRegistry().getFileTypeFor(contents, file
						.getName());
				contents.close();

				if (fileType != null) {
					for (IFileConnectorDelegate delegate : IdeMappingUtils.getFileConectorDelegateRegistry()
							.getConnectorDelegates()) {
						if (fileType.isKindOf(delegate.getFileType().getID())) {
							res = delegate;
							break;
						}
					}
				}
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e
						.getMessage(), e));
			} catch (IOException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e
						.getMessage(), e));
			}
		}

		return res;
	}

	@Override
	protected boolean canUpdate(Object element) {
		final boolean res;

		if (element instanceof IFile) {
			final IFileConnectorDelegate delegate = getDelegate((IFile)element);
			res = delegate != null;
		} else if (element instanceof IResource) {
			res = true;
		} else {
			res = false;
		}

		return res;
	}

	@Override
	protected void update(ILocation location, Object element) {
		final IResourceLocation toUpdate = (IResourceLocation)location;

		toUpdate.setFullPath(((IResource)element).getFullPath().toPortableString());
		if (element instanceof IFile) {
			final IFileConnectorDelegate delegate = getDelegate((IFile)element);
			if (delegate != null) {
				delegate.update((IFileLocation)location, (IFile)element);
			}
		}
	}

	@Override
	protected void initLocation(ILocationContainer container, ILocation location, Object element) {
		final IResourceLocation toInit = (IResourceLocation)location;

		toInit.setFullPath(((IResource)element).getFullPath().toPortableString());
		if (element instanceof IFile) {
			final IFileConnectorDelegate delegate = getDelegate((IFile)element);
			if (delegate != null) {
				delegate.initLocation(container, (IFileLocation)location, (IFile)element);
			}
		}
	}

	@Override
	protected boolean match(ILocation location, Object element) {
		final IResourceLocation resourceLocation = (IResourceLocation)location;
		final IResource resource = (IResource)element;

		return resourceLocation.getFullPath().equals(resource.getFullPath().toPortableString());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.connector.IConnector#getName(org.eclipse.intent.mapping.base.ILocation)
	 */
	public String getName(ILocation location) {
		final String res;

		res = ((IResourceLocation)location).getFullPath();

		return res;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.connector.IConnector#getLocationDescriptor(org.eclipse.intent.mapping.base.IBase,
	 *      java.lang.Object)
	 */
	public ILocationDescriptor getLocationDescriptor(IBase base, Object element) {
		final ILocationDescriptor res;

		final Object adapted = adapt(element);
		if (adapted instanceof IResource) {
			res = new ObjectLocationDescriptor(base, adapted, ((IResource)adapted).getFullPath().toString());
			resourceLocationListener.addKnownDescriptor(res);
		} else {
			res = null;
		}

		return res;
	}

	@Override
	public void dispose(ILocationDescriptor locationDescriptor) {
		super.dispose(locationDescriptor);
		resourceLocationListener.removeKnownDescriptor(locationDescriptor);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.connector.IConnector#getElement(org.eclipse.intent.mapping.base.ILocation)
	 */
	public Object getElement(ILocation location) {
		final Object res;

		if (location instanceof IFileLocation) {
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(Path.fromPortableString(
					((IFileLocation)location).getFullPath()));
			final IFileConnectorDelegate delegate = getDelegate(file);
			if (delegate != null) {
				res = delegate.getElement((IFileLocation)location);
			} else {
				res = null;
			}
		} else {
			res = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(Path.fromPortableString(
					((IResourceLocation)location).getFullPath()));
		}

		return res;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.connector.IConnector#getType()
	 */
	public Class<? extends ILocation> getType() {
		return IResourceLocation.class;
	}

}

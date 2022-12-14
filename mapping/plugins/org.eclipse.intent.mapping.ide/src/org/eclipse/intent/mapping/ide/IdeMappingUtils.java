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
package org.eclipse.intent.mapping.ide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.intent.mapping.base.IBase;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.ILocationDescriptor;
import org.eclipse.intent.mapping.ide.connector.IFileDelegateRegistry;
import org.eclipse.intent.mapping.ide.internal.connector.FileDelegateRegistry;
import org.eclipse.intent.mapping.ide.resource.IFileLocation;

/**
 * Ide mapping utilities.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class IdeMappingUtils {

	/**
	 * Listener for the {@link IdeMappingUtils#getSynchronizationPalette() synchronization palette}.
	 * 
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	public interface ISynchronizationPaletteListener {

		/**
		 * Stub implementation.
		 *
		 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
		 */
		class Stub implements ISynchronizationPaletteListener {

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.intent.mapping.ide.IdeMappingUtils.ISynchronizationPaletteListener#locationActivated(org.eclipse.intent.mapping.base.ILocationDescriptor)
			 */
			public void locationActivated(ILocationDescriptor locationDescriptor) {
				// nothing to do here
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.intent.mapping.ide.IdeMappingUtils.ISynchronizationPaletteListener#locationDeactivated(org.eclipse.intent.mapping.base.ILocationDescriptor)
			 */
			public void locationDeactivated(ILocationDescriptor locationDescriptor) {
				// nothing to do here
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.intent.mapping.ide.IdeMappingUtils.ISynchronizationPaletteListener#contentsAdded(org.eclipse.intent.mapping.base.ILocationDescriptor)
			 */
			public void contentsAdded(ILocationDescriptor locationDescriptor) {
				// nothing to do here
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.intent.mapping.ide.IdeMappingUtils.ISynchronizationPaletteListener#contentsRemoved(org.eclipse.intent.mapping.base.ILocationDescriptor)
			 */
			public void contentsRemoved(ILocationDescriptor locationDescriptor) {
				// nothing to do here
			}

		}

		/**
		 * Notifies that the given {@link ILocationDescriptor} has been activated.
		 * 
		 * @param locationDescriptor
		 *            the activated {@link ILocationDescriptor}
		 */
		void locationActivated(ILocationDescriptor locationDescriptor);

		/**
		 * Notifies that the given {@link ILocationDescriptor} has been deactivated.
		 * 
		 * @param locationDescriptor
		 *            the deactivated {@link ILocationDescriptor}
		 */
		void locationDeactivated(ILocationDescriptor locationDescriptor);

		/**
		 * Notifies that a {@link ILocationDescriptor} has been added.
		 * 
		 * @param locationDescriptor
		 *            the added {@link ILocationDescriptor}
		 */
		void contentsAdded(ILocationDescriptor locationDescriptor);

		/**
		 * Notifies that a {@link ILocationDescriptor} has been removed.
		 * 
		 * @param locationDescriptor
		 *            the removed {@link ILocationDescriptor}
		 */
		void contentsRemoved(ILocationDescriptor locationDescriptor);

	}

	/**
	 * Listener for {@link IdeMappingUtils#getCurrentBase() current base}
	 * {@link IdeMappingUtils#setCurrentBase(IBase) change}.
	 *
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	public interface ICurrentBaseListener {

		/**
		 * Notifies that the {@link IdeMappingUtils#getCurrentBase() current base} has been
		 * {@link IdeMappingUtils#setCurrentBase(IBase) changed}.
		 * 
		 * @param oldBase
		 *            the old {@link IBase}
		 * @param newBase
		 *            the new {@link IBase}
		 */
		void currentBaseChanged(IBase oldBase, IBase newBase);

	}

	/**
	 * The {@link IFileDelegateRegistry} instance.
	 */
	private static final IFileDelegateRegistry REGISTRY = new FileDelegateRegistry();

	/**
	 * Mapping from an {@link ILocation} and it's {@link IMarker}.
	 */
	private static final Map<ILocation, IMarker> LOCATION_TO_MARKER = new HashMap<ILocation, IMarker>();

	/**
	 * The palette of {@link ILocationDescriptor} to link with.
	 */
	private static final Map<ILocationDescriptor, Boolean> LOCATIONS_PALETTE = new LinkedHashMap<ILocationDescriptor, Boolean>();

	/**
	 * The {@link List} of {@link ISynchronizationPaletteListener}.
	 */
	private static final List<ISynchronizationPaletteListener> LOCATIONS_PALETTE_LISTENERS = new ArrayList<ISynchronizationPaletteListener>();

	/**
	 * The {@link List} of {@link ISynchronizationPaletteListener}.
	 */
	private static final List<ICurrentBaseListener> CURRENT_BASE_LISTENERS = new ArrayList<ICurrentBaseListener>();

	/**
	 * The current {@link IBase}.
	 */
	private static IBase currentBase;

	static {
		CURRENT_BASE_LISTENERS.add(new ICurrentBaseListener() {

			public void currentBaseChanged(IBase oldBase, IBase newBase) {
				for (ILocationDescriptor descriptor : getSynchronizationPalette()) {
					removeLocationFromPalette(descriptor);
				}
			}
		});
	}

	/**
	 * Constructor.
	 */
	private IdeMappingUtils() {
		// nothing to do here
	}

	/**
	 * Gets the {@link IFileDelegateRegistry}.
	 * 
	 * @return the {@link IFileDelegateRegistry}
	 */
	public static IFileDelegateRegistry getFileConectorDelegateRegistry() {
		return REGISTRY;
	}

	/**
	 * Adapts the given {@link Object element} into an {@link Object} of the given {@link Class}.
	 * 
	 * @param element
	 *            the {@link Object element}
	 * @param cls
	 *            the {@link Class}
	 * @return the {@link Object} of the given {@link Class} if any, <code>null</code> otherwise
	 * @param <T>
	 *            the kind of result
	 */
	@SuppressWarnings("unchecked")
	public static <T> T adapt(Object element, Class<T> cls) {
		final T res;

		if (cls.isInstance(element)) {
			res = (T)element;
		} else if (element instanceof IAdaptable) {
			final T adaptedElement = (T)((IAdaptable)element).getAdapter(cls);
			if (adaptedElement != null) {
				res = adaptedElement;
			} else {
				res = (T)Platform.getAdapterManager().getAdapter(element, cls);
			}
		} else if (element != null) {
			res = (T)Platform.getAdapterManager().getAdapter(element, cls);
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * Sets the current {@link IBase} to the given {@link IBase}.
	 * 
	 * @param newBase
	 *            the new {@link IBase}
	 */
	public static void setCurrentBase(IBase newBase) {
		if (currentBase != newBase) {
			final IBase oldBase = currentBase;
			currentBase = newBase;
			for (ICurrentBaseListener listener : getCurrentBaseListeners()) {
				listener.currentBaseChanged(oldBase, newBase);
			}
		}
	}

	/**
	 * Gets the current {@link IBase}.
	 * 
	 * @return the current {@link IBase}
	 */
	public static IBase getCurrentBase() {
		return currentBase;
	}

	/**
	 * Gets the {@link ILocation#getContainer() containing} {@link IFileLocation} of the given
	 * {@link ILocation} or itself if it's a {@link IFileLocation}.
	 * 
	 * @param location
	 *            the {@link ILocation}
	 * @return the {@link ILocation#getContainer() containing} {@link IFileLocation} of the given
	 *         {@link ILocation} or itself if it's a {@link IFileLocation}, <code>null</code> if none is found
	 */
	public static IFileLocation getContainingFileLocation(ILocation location) {
		final IFileLocation res;

		if (location instanceof IFileLocation) {
			res = (IFileLocation)location;
		} else if (location.getContainer() instanceof ILocation) {
			res = getContainingFileLocation((ILocation)location.getContainer());
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * Gets or creates the {@link IMarker} for the given {@link ILocation}.
	 * 
	 * @param location
	 *            the {@link ILocation}
	 * @return the {@link IMarker} for the given {@link ILocation} if any exists or can be created,
	 *         <code>null</code> otherwise
	 */
	public static IMarker getOrCreateMarker(ILocation location) {
		final IMarker res;

		final IMarker existingMarker = getMarker(location);
		if (existingMarker == null) {
			res = IdeMappingUtils.adapt(location, IMarker.class);
			if (res != null) {
				LOCATION_TO_MARKER.put(location, res);
			}
		} else {
			res = existingMarker;
		}

		return res;
	}

	/**
	 * Gets the cached marker for the given {@link ILocation}.
	 * 
	 * @param location
	 *            the {@link ILocation}
	 * @return the cached marker for the given {@link ILocation} if any, <code>null</code> otherwise
	 */
	public static IMarker getMarker(ILocation location) {
		return LOCATION_TO_MARKER.get(location);
	}

	/**
	 * {@link IMarker#delete() Deletes} the {@link IMarker} for the given {@link ILocation}.
	 * 
	 * @param location
	 *            the {@link ILocation}
	 */
	public static void deleteMarker(ILocation location) {
		final IMarker marker = LOCATION_TO_MARKER.remove(location);
		if (marker != null) {
			try {
				marker.delete();
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						"unable to delete a location markers for " + marker.getResource().getFullPath()
								.toString(), e));
			}
		}
	}

	/**
	 * Removes the {@link IMarker} for the given {@link ILocation}.
	 * 
	 * @param location
	 *            the {@link ILocation}
	 * @see #deleteMarker(ILocation) for deletion
	 */
	public static void removeMarker(ILocation location) {
		LOCATION_TO_MARKER.remove(location);
	}

	/**
	 * Gets the palette of {@link ILocationDescriptor} to link with.
	 * 
	 * @return the palette of {@link ILocationDescriptor} to link with
	 */
	public static Set<ILocationDescriptor> getSynchronizationPalette() {
		synchronized(LOCATIONS_PALETTE) {
			return Collections.unmodifiableSet(new LinkedHashSet(LOCATIONS_PALETTE.keySet()));
		}
	}

	/**
	 * Gets the {@link List} of {@link ISynchronizationPaletteListener} in a thread safe way.
	 * 
	 * @return the {@link List} of {@link ISynchronizationPaletteListener} in a thread safe way
	 */
	private static List<ISynchronizationPaletteListener> getSynchronizationPaletteListeners() {
		synchronized(LOCATIONS_PALETTE_LISTENERS) {
			return new ArrayList<ISynchronizationPaletteListener>(LOCATIONS_PALETTE_LISTENERS);
		}
	}

	/**
	 * Adds the given {@link ILocationDescriptor} to the {@link #getSynchronizationPalette() palette of
	 * location descriptor}.
	 * 
	 * @param locationDescriptor
	 *            the {@link ILocationDescriptor} to add
	 */
	public static void addLocationToPalette(ILocationDescriptor locationDescriptor) {
		final Boolean added;
		synchronized(LOCATIONS_PALETTE) {
			added = LOCATIONS_PALETTE.put(locationDescriptor, false);
		}
		if (added == null || added) {
			for (ISynchronizationPaletteListener listener : getSynchronizationPaletteListeners()) {
				listener.contentsAdded(locationDescriptor);
			}
		}
	}

	/**
	 * Removes the given {@link ILocationDescriptor} from the {@link #getSynchronizationPalette() palette of
	 * location descriptor}.
	 * 
	 * @param locationDescriptor
	 *            the {@link ILocation} to remove
	 */
	public static void removeLocationFromPalette(ILocationDescriptor locationDescriptor) {
		final Boolean removed;
		synchronized(LOCATIONS_PALETTE) {
			removed = LOCATIONS_PALETTE.remove(locationDescriptor);
		}
		if (removed != null) {
			for (ISynchronizationPaletteListener listener : getSynchronizationPaletteListeners()) {
				listener.contentsRemoved(locationDescriptor);
			}
		}
	}

	/**
	 * Adds the given {@link ISynchronizationPaletteListener}.
	 * 
	 * @param listener
	 *            the {@link ISynchronizationPaletteListener} to add
	 */
	public static void addSynchronizationPaletteListener(ISynchronizationPaletteListener listener) {
		synchronized(LOCATIONS_PALETTE_LISTENERS) {
			LOCATIONS_PALETTE_LISTENERS.add(listener);
		}
	}

	/**
	 * removes the given {@link ISynchronizationPaletteListener}.
	 * 
	 * @param listener
	 *            the {@link ISynchronizationPaletteListener} to remove
	 */
	public static void removeSynchronizationPaletteListener(ISynchronizationPaletteListener listener) {
		synchronized(LOCATIONS_PALETTE_LISTENERS) {
			LOCATIONS_PALETTE_LISTENERS.remove(listener);
		}
	}

	/**
	 * Activates the given {@link ILocationDescriptor} form the {@link #getSynchronizationPalette() palette of
	 * location descriptor}.
	 * 
	 * @param locationDescriptor
	 *            the {@link ILocationDescriptor} to activate
	 */
	public static void activateLocation(ILocationDescriptor locationDescriptor) {
		final boolean changed;

		synchronized(LOCATIONS_PALETTE) {
			if (LOCATIONS_PALETTE.containsKey(locationDescriptor)) {
				final Boolean lastValue = LOCATIONS_PALETTE.put(locationDescriptor, Boolean.TRUE);
				changed = lastValue == null || !lastValue;
			} else {
				changed = false;
			}
		}

		if (changed) {
			for (ISynchronizationPaletteListener listener : getSynchronizationPaletteListeners()) {
				listener.locationActivated(locationDescriptor);
			}
		}
	}

	/**
	 * Deactivates the given {@link ILocationDescriptor} form the {@link #getSynchronizationPalette() palette
	 * of location descriptor}.
	 * 
	 * @param locationDescriptor
	 *            the {@link ILocationDescriptor} to deactivate
	 */
	public static void deactivateLocation(ILocationDescriptor locationDescriptor) {
		final boolean changed;

		synchronized(LOCATIONS_PALETTE) {
			if (LOCATIONS_PALETTE.containsKey(locationDescriptor)) {
				final Boolean lastValue = LOCATIONS_PALETTE.put(locationDescriptor, Boolean.FALSE);
				changed = lastValue == null || lastValue;
			} else {
				changed = false;
			}
		}

		if (changed) {
			for (ISynchronizationPaletteListener listener : getSynchronizationPaletteListeners()) {
				listener.locationDeactivated(locationDescriptor);
			}
		}
	}

	/**
	 * Tells if the given {@link ILocationDescriptor} from the
	 * {@link IdeMappingUtils#getSynchronizationPalette() location descriptors palette} is active.
	 * 
	 * @param locationDescriptor
	 *            the {@link ILocationDescriptor}
	 * @return <code>true</code> if the given {@link ILocationDescriptor} from the
	 *         {@link IdeMappingUtils#getSynchronizationPalette() location descriptors palette} is active,
	 *         <code>false</code> otherwise
	 */
	public static boolean isActive(ILocationDescriptor locationDescriptor) {
		final boolean res;

		synchronized(LOCATIONS_PALETTE) {
			final Boolean isActive = LOCATIONS_PALETTE.get(locationDescriptor);
			res = isActive != null && isActive;
		}

		return res;
	}

	/**
	 * Tells if there is at least one {@link IdeMappingUtils#isActive(ILocationDescriptor) active}
	 * {@link ILocationDescriptor} in the {@link IdeMappingUtils#getSynchronizationPalette() location
	 * descriptors palette} .
	 * 
	 * @return <code>true</code> if there is at least one {@link IdeMappingUtils#isActive(ILocationDescriptor)
	 *         active} {@link ILocationDescriptor} in the {@link IdeMappingUtils#getSynchronizationPalette()
	 *         location descriptors palette}, <code>false</code> otherwise
	 */
	public static boolean asActiveLocationDescriptor() {
		synchronized(LOCATIONS_PALETTE) {
			return LOCATIONS_PALETTE.values().contains(Boolean.TRUE);
		}
	}

	/**
	 * Gets the {@link List} of {@link ICurrentBaseListener} in a thread safe way.
	 * 
	 * @return the {@link List} of {@link ICurrentBaseListener} in a thread safe way
	 */
	private static List<ICurrentBaseListener> getCurrentBaseListeners() {
		synchronized(CURRENT_BASE_LISTENERS) {
			return new ArrayList<ICurrentBaseListener>(CURRENT_BASE_LISTENERS);
		}
	}

	/**
	 * Adds the given {@link ICurrentBaseListener}.
	 * 
	 * @param listener
	 *            the {@link ICurrentBaseListener} to add
	 */
	public static void addCurrentBaseListener(ICurrentBaseListener listener) {
		synchronized(CURRENT_BASE_LISTENERS) {
			CURRENT_BASE_LISTENERS.add(listener);
		}
	}

	/**
	 * Removes the given {@link ICurrentBaseListener}.
	 * 
	 * @param listener
	 *            the {@link ICurrentBaseListener} to remove
	 */
	public static void removeCurrentBaseListener(ICurrentBaseListener listener) {
		synchronized(CURRENT_BASE_LISTENERS) {
			CURRENT_BASE_LISTENERS.remove(listener);
		}
	}

}

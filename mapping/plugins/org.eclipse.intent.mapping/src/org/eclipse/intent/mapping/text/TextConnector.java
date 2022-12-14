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
package org.eclipse.intent.mapping.text;

import org.eclipse.intent.mapping.MappingUtils;
import org.eclipse.intent.mapping.MappingUtils.DiffMatch;
import org.eclipse.intent.mapping.base.IBase;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.ILocationContainer;
import org.eclipse.intent.mapping.base.ILocationDescriptor;
import org.eclipse.intent.mapping.connector.AbstractConnector;

/**
 * Text {@link org.eclipse.intent.mapping.connector.IConnector IConnector}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TextConnector extends AbstractConnector {

	/**
	 * Utility helper to
	 * {@link TextContainerHelper#updateTextContainer(ILocationContainer, ITextContainer, String) update text
	 * container}.
	 * 
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	public static class TextContainerHelper {

		/**
		 * Updates the given {@link ITextContainer} with the given {@link ITextContainer#getText() text}.
		 * 
		 * @param container
		 *            the {@link ILocationContainer}
		 * @param textContainer
		 *            the {@link ITextContainer}
		 * @param text
		 *            the {@link ITextContainer#getText() text}
		 * @throws IllegalAccessException
		 *             if the class or its nullary constructor is not accessible.
		 * @throws InstantiationException
		 *             if this Class represents an abstract class, an interface, an array class, a primitive
		 *             type, or void; or if the class has no nullary constructor; or if the instantiation
		 *             fails for some other reason.
		 * @throws ClassNotFoundException
		 *             if the {@link Class} can't be found
		 */
		public void updateTextContainer(ILocationContainer container, ITextContainer textContainer,
				String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			final String oldText = textContainer.getText();
			textContainer.setText(text);
			if (oldText != null) {
				final DiffMatch diff = MappingUtils.getDiffMatch(oldText, text);
				for (ILocation child : textContainer.getContents()) {
					if (child instanceof ITextLocation && !child.isMarkedAsDeleted()) {
						final ITextLocation location = (ITextLocation)child;
						final int newStartOffset = diff.getIndex(location.getStartOffset());
						final int newEndOffset = diff.getIndex(location.getEndOffset());
						final String oldValue = oldText.substring(location.getStartOffset(), location
								.getEndOffset());
						final String newValue = text.substring(newStartOffset, newEndOffset);
						if (newStartOffset == newEndOffset) {
							MappingUtils.markAsDeletedOrDelete(location, String.format(
									"\"%s\" at (%d, %d) has been deleted.", oldValue, location
											.getStartOffset(), location.getEndOffset()));
						} else if (!oldValue.equals(newValue)) {
							MappingUtils.markAsChanged(location, String.format(
									"\"%s\" at (%d, %d) has been changed to \"%s\" at (%d, %d).", oldValue,
									location.getStartOffset(), location.getEndOffset(), newValue,
									newStartOffset, newEndOffset));
						}
						location.setStartOffset(newStartOffset);
						location.setEndOffset(newEndOffset);
					}
				}
			}
		}

	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.connector.IConnector#getLocationType(java.lang.Class, java.lang.Object)
	 */
	public Class<? extends ILocation> getLocationType(Class<? extends ILocationContainer> containerType,
			Object element) {
		final Class<? extends ILocation> res;

		if (ITextContainer.class.isAssignableFrom(containerType) && element instanceof TextRegion) {
			res = getType();
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.connector.IConnector#getElement(org.eclipse.intent.mapping.base.ILocation)
	 */
	public Object getElement(ILocation location) {
		final TextRegion res;

		final ITextLocation textLocation = (ITextLocation)location;
		final int startOffset = textLocation.getStartOffset();
		final int endOffset = textLocation.getEndOffset();
		final String text = ((ITextContainer)location.getContainer()).getText().substring(startOffset,
				endOffset);
		final Object container = MappingUtils.getConnectorRegistry().getElement((ITextContainer)location
				.getContainer());
		res = new TextRegion(container, text, startOffset, endOffset);

		return res;
	}

	@Override
	protected void initLocation(ILocationContainer container, ILocation location, Object element) {
		final ITextLocation toInit = (ITextLocation)location;
		final TextRegion region = (TextRegion)element;

		toInit.setStartOffset(region.getStartOffset());
		toInit.setEndOffset(region.getEndOffset());
	}

	@Override
	protected boolean match(ILocation location, Object element) {
		final ITextLocation textLocation = (ITextLocation)location;
		final TextRegion region = (TextRegion)element;

		return textLocation.getStartOffset() == region.getStartOffset() && textLocation
				.getEndOffset() == region.getEndOffset();
	}

	@Override
	protected boolean canUpdate(Object element) {
		return element instanceof TextRegion;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.connector.IConnector#getName(org.eclipse.intent.mapping.base.ILocation)
	 */
	public String getName(ILocation location) {
		final String res;

		final String text = ((ITextContainer)location.getContainer()).getText();
		final int start = ((ITextLocation)location).getStartOffset();
		final int end = ((ITextLocation)location).getEndOffset();
		res = String.format("\"%s\"", text.substring(start, end));

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
		if (adapted instanceof TextRegion) {
			res = new ObjectLocationDescriptor(base, adapted, ((TextRegion)adapted).getText());
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.intent.mapping.connector.IConnector#getType()
	 */
	public Class<? extends ILocation> getType() {
		return ITextLocation.class;
	}

}

/*******************************************************************************
 * Copyright (c) 2010, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.client.ui.editor.annotation;

import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.jface.text.quickassist.IQuickFixableAnnotation;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;

/**
 * Represents an Intent Annotation that can be used in any Editor.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class IntentAnnotation extends Annotation implements IQuickFixableAnnotation {

	/**
	 * Message type corresponding to this annotation (represents the semantic value of this annotation : can
	 * be COMPILER.ERROR, PARSER.WARNING...).
	 */
	private IntentAnnotationMessageType messageType;

	/**
	 * Indicates if the annotation is in quickfixable state.
	 * 
	 * @see org.eclipse.jface.text.quickassist.IQuickFixableAnnotation#isQuickFixableStateSet()
	 */
	private boolean quickFixableState;

	/**
	 * The associated compilation status.
	 */
	private CompilationStatus compilationStatus;

	/**
	 * IntentAnnotation constructor.
	 * 
	 * @param isPersistent
	 *            <code>true</code> if persistent, <code>false</code> otherwise
	 */
	public IntentAnnotation(boolean isPersistent) {
		super(isPersistent);
		// We set the default message type to GENERAL_WARNING
		this.messageType = IntentAnnotationMessageType.DEFAULT;
	}

	/**
	 * Returns the Message type corresponding to this annotation (represents the semantic value of this
	 * annotation).
	 * 
	 * @return the messageType of this IntentAnnotation (for example COMPILER.ERROR, PARSER.WARNING...)
	 */
	public IntentAnnotationMessageType getMessageType() {
		return messageType;
	}

	/**
	 * Sets the Message type corresponding to this annotation (represents the semantic value of this
	 * annotation).
	 * 
	 * @param messageType
	 *            the messageType to set (for example COMPILER.ERROR, PARSER.WARNING...)
	 */
	public void setMessageType(IntentAnnotationMessageType messageType) {
		this.messageType = messageType;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.quickassist.IQuickFixableAnnotation#setQuickFixable(boolean)
	 */
	public void setQuickFixable(boolean state) {
		this.quickFixableState = state;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.quickassist.IQuickFixableAnnotation#isQuickFixableStateSet()
	 */
	public boolean isQuickFixableStateSet() {
		return quickFixableState;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.text.quickassist.IQuickFixableAnnotation#isQuickFixable()
	 */
	public boolean isQuickFixable() throws AssertionFailedException {
		return true;
	}

	/**
	 * Returns compilation status relative to this Annotation.
	 * 
	 * @return compilation status relative to this Annotation
	 */
	public CompilationStatus getCompilationStatus() {
		return compilationStatus;
	}

	/**
	 * Sets the compilation status relative to this Annotation.
	 * 
	 * @param compilationStatus
	 *            the compilation status relative to this Annotation
	 */
	public void setCompilationStatus(CompilationStatus compilationStatus) {
		this.compilationStatus = compilationStatus;
	}

}

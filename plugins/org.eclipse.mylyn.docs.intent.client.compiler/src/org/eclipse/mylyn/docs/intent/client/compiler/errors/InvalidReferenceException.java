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
package org.eclipse.mylyn.docs.intent.client.compiler.errors;

import org.eclipse.mylyn.docs.intent.core.document.UnitInstruction;

/**
 * Exception that occurred when failing to resolve a reference to a created instance.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class InvalidReferenceException extends AbstractRuntimeCompilationException {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -1996338680173671147L;

	/**
	 * ResolveException constructor.
	 * 
	 * @param message
	 *            the message explaining the cause of the error
	 * @param instruction
	 *            the instruction that caused the error
	 */
	public InvalidReferenceException(UnitInstruction instruction, String message) {
		super(instruction, message);
	}
}

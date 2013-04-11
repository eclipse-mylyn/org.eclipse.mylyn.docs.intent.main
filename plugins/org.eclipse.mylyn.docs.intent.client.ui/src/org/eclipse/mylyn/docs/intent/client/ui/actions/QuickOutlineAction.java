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
package org.eclipse.mylyn.docs.intent.client.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.mylyn.docs.intent.client.ui.editor.IntentEditor;
import org.eclipse.mylyn.docs.intent.client.ui.editor.outline.IntentQuickOutlineControl;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * This action will display a quick outline view within a styled text popup menu.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a><br/>
 *         modified by @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a> to adapt this action to
 *         the IntentEditor.
 */
public class QuickOutlineAction extends Action implements IWorkbenchWindowActionDelegate {

	/**
	 * The action ID.
	 */
	public static final String ACTION_ID = "org.eclipse.mylyn.docs.intent.client.ui.editor.actions.show.outline"; //$NON-NLS-1$

	/**
	 * The associated command ID.
	 */
	public static final String COMMAND_ID = "org.eclipse.mylyn.docs.intent.client.ui.show.outline"; //$NON-NLS-1$

	/**
	 * constructor.
	 */
	public QuickOutlineAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
		// No disposal needed here
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		// no initialization required
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		super.run();
		IntentEditor currentEditor = getCurrentEditor();
		IntentQuickOutlineControl currentQuickOutline = currentEditor.getCurrentQuickOutline();
		if (currentQuickOutline == null) {
			IInformationPresenter presenter = getCurrentEditor().createQuickOutlinePresenter();
			presenter.showInformation();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// We don't need to react to selection changes
	}

	/**
	 * Returns the currently active editor if it's an IntentEditor.
	 * 
	 * @return The currently active editor if it's an IntentEditor, <code>null</code> otherwise.
	 */
	protected IntentEditor getCurrentEditor() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null && window.getActivePage() != null
				&& window.getActivePage().getActiveEditor() instanceof IntentEditor) {
			return (IntentEditor)window.getActivePage().getActiveEditor();
		}
		return null;
	}
}

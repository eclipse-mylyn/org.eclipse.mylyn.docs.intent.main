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
package org.eclipse.mylyn.docs.intent.bridge.java.listeners;

import java.util.Set;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.mylyn.docs.intent.bridge.java.util.JavaBridgeUtils;
import org.eclipse.mylyn.docs.intent.client.synchronizer.SynchronizerRepositoryClient;
import org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtension;
import org.eclipse.mylyn.docs.intent.client.ui.ide.generatedelementlistener.IDEGeneratedElementListener;

/**
 * A {@link IDEGeneratedElementListener} in charge of launching the synchronizer when java files referenced
 * inside an Intent document have changed.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class JavaElementListener extends IDEGeneratedElementListener implements ISynchronizerExtension, IResourceChangeListener {

	/**
	 * JavaElementListener constructor.
	 */
	public JavaElementListener() {
		super();
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtension#isExtensionFor(org.eclipse.emf.common.util.URI)
	 */
	public boolean isExtensionFor(URI uri) {
		return JavaBridgeUtils.isHandledByJavaBridge(uri);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtension#addListenedElements(org.eclipse.mylyn.docs.intent.client.synchronizer.SynchronizerRepositoryClient,
	 *      java.util.Set)
	 */
	public void addListenedElements(SynchronizerRepositoryClient synchronizerClient,
			Set<URI> listenedElementsURIs) {
		this.synchronizer = synchronizerClient;
		for (URI listenedElementURI : listenedElementsURIs) {
			addElementToListen(URI.createPlatformResourceURI(listenedElementURI.toString(), false)
					.trimFragment());
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.client.synchronizer.api.contribution.ISynchronizerExtension#removeListenedElements(org.eclipse.mylyn.docs.intent.client.synchronizer.SynchronizerRepositoryClient,
	 *      java.util.Set)
	 */
	public void removeListenedElements(SynchronizerRepositoryClient synchronizer,
			Set<URI> listenedElementsURIs) {
		for (URI listenedElementURI : listenedElementsURIs) {
			removeElementToListen(URI.createPlatformResourceURI(listenedElementURI.toString(), false)
					.trimFragment());
		}
	}

}

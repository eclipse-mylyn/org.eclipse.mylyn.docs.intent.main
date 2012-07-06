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
package org.eclipse.mylyn.docs.intent.collab.common.internal;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager;
import org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManagerContribution;
import org.eclipse.mylyn.docs.intent.collab.repository.Repository;
import org.eclipse.mylyn.docs.intent.collab.repository.RepositoryConnectionException;

/**
 * Handles an Intent Project lifecycle :
 * <ul>
 * <li>Create/Delete the Repository</li>
 * <li>Launch/Stop Intent Clients</li>.
 * </ul>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * @author <a href="mailto:william.piers@obeo.fr">William Piers</a>
 */
public final class IntentRepositoryManagerImpl implements IntentRepositoryManager {

	private static Collection<IntentRepositoryManagerContribution> managerContributions = Sets
			.newLinkedHashSet();

	/**
	 * The list of created repositories, associated to the corresponding project.
	 */
	private Map<String, Repository> repositoriesByProject = new HashMap<String, Repository>();

	private boolean lock;

	/**
	 * {@inheritDoc}
	 * 
	 * @throws CoreException
	 * @see org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager#getRepository(java.lang.String)
	 */
	public synchronized Repository getRepository(String identifier) throws RepositoryConnectionException,
			CoreException {
		Assert.isTrue(!lock);
		Repository repository = null;
		String normalizedIdentifier = normalizeIdentifier(identifier);
		lock = true;
		try {

			// First looking for an already opened repository
			if (repositoriesByProject.get(normalizedIdentifier) != null) {
				repository = repositoriesByProject.get(normalizedIdentifier);
			}

			// then delegating the repository creation to a registered repository manager
			for (Iterator<IntentRepositoryManagerContribution> iterator = managerContributions.iterator(); iterator
					.hasNext() && repository == null;) {
				IntentRepositoryManagerContribution repositoryManagerContribution = iterator.next();
				if (repositoryManagerContribution.canCreateRepository(normalizedIdentifier)) {
					repository = repositoryManagerContribution.createRepository(normalizedIdentifier);
					if (repository != null) {
						repositoriesByProject.put(normalizedIdentifier, repository);
					}
				}
			}
		} finally {
			lock = false;
		}
		return repository;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager#isManagedProject(java.lang.String)
	 */
	public synchronized boolean isManagedProject(String identifier) {
		String normalizedIdentifier = normalizeIdentifier(identifier);
		return repositoriesByProject.get(normalizedIdentifier) != null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager#deleteRepository(java.lang.String)
	 */
	public synchronized void deleteRepository(String identifier) {
		String normalizedIdentifier = normalizeIdentifier(identifier);
		repositoriesByProject.remove(normalizedIdentifier);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager#addIntentRepositoryManagerContribution(org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManagerContribution)
	 */
	public void addIntentRepositoryManagerContribution(IntentRepositoryManagerContribution contribution) {
		managerContributions.add(contribution);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManager#removeIntentRepositoryManagerContribution(org.eclipse.mylyn.docs.intent.collab.common.IntentRepositoryManagerContribution)
	 */
	public void removeIntentRepositoryManagerContribution(IntentRepositoryManagerContribution contribution) {
		managerContributions.remove(contribution);
	}

	/**
	 * Returns the normalized form of the given identifier (e.g. if identifier is of the form
	 * platform:/resource/PROJECT_NAME/..., returns PROJECT_NAME
	 * 
	 * @param identifier
	 *            the identifier to normalize
	 * @return the normalized form of the given identifier (e.g. if identifier is of the form
	 *         platform:/resource/PROJECT_NAME/..., returns PROJECT_NAME
	 */
	private String normalizeIdentifier(String identifier) {
		String normalizedIdentifier = identifier;
		// If identifier is of the form platform:/resource/PROJECT_NAME/...
		// we extract the IProject name
		if (identifier.startsWith("platform:/resource")) {
			normalizedIdentifier = identifier.toString().replaceFirst("platform:/resource/", "");
			normalizedIdentifier = normalizedIdentifier.split("/")[0];
		}
		return normalizedIdentifier;
	}

}

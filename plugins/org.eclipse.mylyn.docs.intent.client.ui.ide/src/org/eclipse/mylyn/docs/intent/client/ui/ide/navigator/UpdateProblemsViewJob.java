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
package org.eclipse.mylyn.docs.intent.client.ui.ide.navigator;

import com.google.common.collect.Sets;

import java.util.ConcurrentModificationException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.mylyn.docs.intent.collab.common.location.IntentLocations;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IIntentLogger.LogType;
import org.eclipse.mylyn.docs.intent.collab.common.logger.IntentLogger;
import org.eclipse.mylyn.docs.intent.collab.common.query.CompilationStatusQuery;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatus;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusManager;
import org.eclipse.mylyn.docs.intent.core.compiler.CompilationStatusSeverity;
import org.eclipse.mylyn.docs.intent.core.compiler.SynchronizerCompilationStatus;

/**
 * A job in charge of updating the "Problems" View my creating IMarkers for each Compilation or
 * Synchronization status associed to the Intent Document.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class UpdateProblemsViewJob extends Job {

	/**
	 * Job name.
	 */
	private static final String UPDATE_PROBLEMS_VIEW_JOB_NAME = "Updating problem view";

	/**
	 * The {@link RepositoryAdapter} to use for getting the intent compilation issues.
	 */
	private RepositoryAdapter adapter;

	/**
	 * The Intent {@link IProject} associated to this job.
	 */
	private IProject project;

	/**
	 * A query allowing to get compilation statuses information.
	 */
	private CompilationStatusQuery statusQuery;

	/**
	 * Default constructor.
	 * 
	 * @param project
	 *            the Intent project to update
	 * @param adapter
	 *            the current repository adapter
	 */
	public UpdateProblemsViewJob(IProject project, RepositoryAdapter adapter) {
		super(UPDATE_PROBLEMS_VIEW_JOB_NAME);
		this.project = project;
		this.adapter = adapter;
		this.statusQuery = new CompilationStatusQuery(adapter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {

		Resource statusResource = adapter.getResource(IntentLocations.COMPILATION_STATUS_INDEX_PATH);
		Resource intentDocumentResource = adapter.getResource(IntentLocations.INTENT_INDEX);
		String platformString = intentDocumentResource.getURI().toPlatformString(true);
		IFile intentDocumentFile = (IFile)ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
		if (intentDocumentFile != null && !(statusResource.getContents().isEmpty())) {

			// Step 1: delete currently defined markers
			try {
				if (project.isAccessible()) {
					project.deleteMarkers("org.eclipse.core.resources.problemmarker", false,
							IResource.DEPTH_INFINITE);
				}
			} catch (CoreException e) {
				// Nothing to do, problem view will still reference old markers
				IntentLogger.getInstance().log(LogType.WARNING,
						"Intent - failed to delete markers of project " + project.getName(), e);
			}

			// Step 2: create marker for each status
			CompilationStatusManager statusManager = statusQuery.getOrCreateCompilationStatusManager();

			try {
				for (CompilationStatus status : Sets.newLinkedHashSet(statusManager
						.getCompilationStatusList())) {
					createMarkerFromStatus(status);
				}
			} catch (ConcurrentModificationException e) {
				IntentLogger.getInstance().log(LogType.WARNING, "Error while updating problem view", e);
			}
		}
		return Status.OK_STATUS;
	}

	/**
	 * Creates a marker on the intent project that represents the given status.
	 * 
	 * @param status
	 *            the compilation status to create the marker from
	 */
	private void createMarkerFromStatus(CompilationStatus status) {
		IMarker marker = null;
		try {
			boolean statusIsProxy = status.eIsProxy() || status.getTarget() == null
					|| status.getTarget().eIsProxy();
			statusIsProxy = statusIsProxy || status.eResource() == null
					|| status.getTarget().eResource() == null;

			if (project.isAccessible() && !statusIsProxy) {
				marker = project.createMarker("org.eclipse.core.resources.problemmarker");
				if (status.getSeverity() == CompilationStatusSeverity.WARNING) {
					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
				} else if (status.getSeverity() == CompilationStatusSeverity.INFO) {
					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
				} else {
					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				}

				String markerMessage = status.getMessage();
				if (status instanceof SynchronizerCompilationStatus) {
					markerMessage = "[Sync] " + markerMessage;
				}
				marker.setAttribute(IMarker.MESSAGE, markerMessage);
				marker.setAttribute(IMarker.LOCATION, EcoreUtil.getURI(status).toString());
				marker.setAttribute(IMarker.SOURCE_ID, "Intent");
			}
		} catch (CoreException e) {
			// Nothing to do, problem view will not be updated
			IntentLogger.getInstance().log(LogType.WARNING,
					"Intent - error occured while creating problem markers on project " + project.getName(),
					e);
		}
	}
}

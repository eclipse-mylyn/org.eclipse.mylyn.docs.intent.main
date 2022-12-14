/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation and/or initial documentation
 *    ...
 *******************************************************************************/
package org.eclipse.intent.mapping.ide.ui.view;

import java.util.Arrays;
import java.util.List;

import org.eclipse.intent.mapping.base.IBase;
import org.eclipse.intent.mapping.base.IBaseListener;
import org.eclipse.intent.mapping.base.ILink;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.IReport;
import org.eclipse.intent.mapping.base.IReportListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

/**
 * Provide {@link ILink linked} {@link ILocation} for a given {@link ILocation}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class SynchronizationLocationContentProvider extends AbstractLocationContentProvider {

	/**
	 * Refresh the current viewer when the {@link IReport} change.
	 */
	private final IReportListener reportListener = new IReportListener.Stub() {

		public void descriptionChanged(String oldDescription, String newDescription) {
			update();
			refresh();
		}

		public void linkChanged(ILink oldLink, ILink newLink) {
			update();
			refresh();
		}
	};

	/**
	 * Refresh the current viewer when the current {@link IBase} {@link IBase#getReports() reports} change.
	 */
	private final IBaseListener baseListener = new IBaseListener.Stub() {

		public void reportAdded(IReport report) {
			update();
			refresh();
			report.addListener(reportListener);
		}

		public void reportRemoved(IReport report) {
			update();
			refresh();
			report.removeListener(reportListener);
		}
	};

	/**
	 * Constructor.
	 */
	public SynchronizationLocationContentProvider() {
		super();
		showReport = true;
	}

	/**
	 * Refresh the {@link Viewer}.
	 */
	private void refresh() {
		if (!currentViewer.getControl().isDisposed()) {
			if (Display.getDefault().getThread() == Thread.currentThread()) {
				currentViewer.refresh();
			} else {
				Display.getDefault().asyncExec(new Runnable() {

					public void run() {
						currentViewer.refresh();
					}
				});
			}
		}
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (oldInput instanceof IBase) {
			((IBase)oldInput).removeListener(baseListener);
			for (IReport report : ((IBase)oldInput).getReports()) {
				report.removeListener(reportListener);
			}
		}
		if (newInput instanceof IBase) {
			((IBase)newInput).addListener(baseListener);
			for (IReport report : ((IBase)newInput).getReports()) {
				report.addListener(reportListener);
			}
		}
		super.inputChanged(viewer, oldInput, newInput);
	}

	@Override
	protected void setLeavesAndLinks(Object input, List<ILocation> locationLeaves, List<ILink> links) {
		if (input instanceof IBase) {
			for (IReport report : ((IBase)input).getReports()) {
				links.add(report.getLink());
				locationLeaves.add(report.getLink().getSource());
			}
		}
	}

	@Override
	public boolean hasChildren(Object element) {
		final boolean res;

		if (element instanceof ILocation) {
			res = !((ILocation)element).getTargetLinks().isEmpty() || super.hasChildren(element);
		} else if (element instanceof ILink) {
			res = !((ILink)element).getReports().isEmpty();
		} else {
			res = false;
		}

		return res;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		final Object[] res;

		if (parentElement instanceof ILocation) {
			final Object[] childLocations = super.getChildren(parentElement);
			res = concat(childLocations, ((ILocation)parentElement).getTargetLinks().toArray());
		} else if (parentElement instanceof ILink) {
			res = ((ILink)parentElement).getReports().toArray();
		} else {
			res = new Object[0];
		}

		return res;
	}

	/**
	 * Concatenates two given arrays of {@link Object}.
	 * 
	 * @param first
	 *            the first array
	 * @param second
	 *            the second array
	 * @return a new array composed of the two given arrays
	 */
	private Object[] concat(Object[] first, Object[] second) {
		final Object[] result = Arrays.copyOf(first, first.length + second.length);

		System.arraycopy(second, 0, result, first.length, second.length);

		return result;
	}

	@Override
	public Object getParent(Object element) {
		final Object res;

		if (element instanceof ILink) {
			res = ((ILink)element).getSource();
		} else if (element instanceof IReport) {
			res = ((IReport)element).getLink();
		} else {
			res = super.getParent(element);
		}

		return res;
	}

}

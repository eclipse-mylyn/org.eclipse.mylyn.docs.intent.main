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
package org.eclipse.mylyn.docs.intent.exporter.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.mylyn.docs.intent.client.ui.logger.IntentUiLogger;
import org.eclipse.mylyn.docs.intent.collab.handlers.adapters.RepositoryAdapter;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndex;
import org.eclipse.mylyn.docs.intent.core.compiler.TraceabilityIndexEntry;
import org.eclipse.mylyn.docs.intent.markup.gen.services.ImageServices;

/**
 * Utility class allowing to determine the images associated to some EObject, and copy them inside the
 * exported documentation.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class CopyImageUtils {

	/**
	 * The {@link AdapterFactoryItemDelegator} to use to get images from {@link EObject}s.
	 */
	private static AdapterFactoryItemDelegator itemDelegator;

	/**
	 * The resource set to use.
	 */
	private static ResourceSetImpl resourceSet;

	/**
	 * Private constructor.
	 */
	private CopyImageUtils() {

	}

	/**
	 * Determines the image associated to the given EObject and copies it inside the exported documentation.
	 * 
	 * @param any
	 *            the eobject to get the image from
	 * @param repositoryAdapter
	 *            the repository adapter to use to access to informations (traceability index...)
	 * @param outputFolder
	 *            the target folder in which image will be copied
	 * @return the image associated to the given EObject
	 */
	public static String copyImageAndGetImageID(EObject any, RepositoryAdapter repositoryAdapter,
			File outputFolder) {
		String qualifiedImageID = "";
		// Step 1: getting the image URL thanks to the item delegator
		Object imageURL = getImageURL(any);

		if (imageURL instanceof URL) {
			try {
				// Step 2: resolve URL
				URL resolvedURL = FileLocator.resolve((URL)imageURL);
				EClassifier classifier = null;
				if (any instanceof EClassifier) {
					classifier = (EClassifier)any;
				} else {
					classifier = any.eClass();
				}

				// If default "Item" image has been
				if (resolvedURL.toString().contains("org.eclipse.emf.edit")
						&& resolvedURL.toString().endsWith("/icons/full/obj16/Item.gif")) {
					// we search for an "edit" plugin in the workspace
					imageURL = getImageFromWorkspace(classifier, repositoryAdapter);
					if (imageURL != null) {
						resolvedURL = (URL)imageURL;
					}
				}

				// Step 3: copy image in the exported documentation
				if (resolvedURL != null && resolvedURL.getFile() != null && outputFolder != null) {
					qualifiedImageID = copyImageIfNeeded(classifier, outputFolder, resolvedURL,
							resolvedURL.openStream());
				}
			} catch (IOException e) {
				IntentUiLogger.logError(e);
			}
		}
		return qualifiedImageID;
	}

	/**
	 * Searches for an edit plugin corresponding to the given EObject and tries to retrieve its associated
	 * image.
	 * 
	 * @param repositoryAdapter
	 *            the repository adapter to use to access to informations (traceability index...)
	 * @param any
	 *            the eobject to get the image from
	 * @return the URL of the image corresponding to the given EObject, null if none found
	 */
	private static URL getImageFromWorkspace(EClassifier any, RepositoryAdapter repositoryAdapter) {
		// If the core.resource plugin is not available, we return null
		if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE) {
			TraceabilityIndex projectTraceabilityIndex = IntentAcceleoServices
					.getTraceabilityIndex(repositoryAdapter);
			if (projectTraceabilityIndex != null) {
				for (TraceabilityIndexEntry entry : projectTraceabilityIndex.getEntries()) {
					if (any.eResource().getURI().toString().contains(entry.getGeneratedResourcePath())) {
						URI metamodelURI = entry.getResourceDeclaration().getUri();
						doGetImageFromWorkspace(metamodelURI, any);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Searches for an edit plugin corresponding to the given EObject and tries to retrieve its associated
	 * image.
	 * 
	 * @param metamodelURI
	 *            the {@link URI}
	 * @param classifier
	 *            the {@link EObject} to get the image from
	 * @return the URL of the image associated to the given classifier (if any found)
	 */
	private static URL doGetImageFromWorkspace(URI metamodelURI, EClassifier classifier) {
		URL imageURL = null;
		if (metamodelURI != null && metamodelURI.toString().replace("\"", "").endsWith("ecore")) {
			URI genModelURI = URI.createURI(metamodelURI.toString().replace("\"", "")
					.replace("ecore", "genmodel"));

			try {
				Resource resource = getResourceSet().getResource(genModelURI, true);
				if (resource.getContents().isEmpty()
						|| !(resource.getContents().iterator().next() instanceof GenModel)) {
					return null;
				}

				String editIconsDirectory = ((GenModel)resource.getContents().iterator().next())
						.getEditIconsDirectory();
				IFolder folder = ResourcesPlugin.getWorkspace().getRoot()
						.getFolder(new Path(editIconsDirectory + "/full/obj16"));
				if (folder.exists()) {
					for (String imageExtension : new String[] {"gif", "png",
					}) {
						if (folder.getFile(classifier.getName() + "." + imageExtension).exists()) {
							imageURL = new URL("file:/"
									+ folder.getFile(classifier.getName() + "." + imageExtension)
											.getLocation().toString());
						}
					}
				}
				// CHECKSTYLE:OFF
			} catch (RuntimeException e) {
				// CHECKSTYLE:ON
				IntentUiLogger.logInfo("Cannot find genmodel at " + genModelURI
						+ ". Default image will be use to display " + classifier.getName());
			} catch (MalformedURLException e) {
				IntentUiLogger.logError(e);
			}
		}
		return imageURL;
	}

	/**
	 * Returns the URL of the image associated to the given {@link EObject} through AdapterFactories.
	 * 
	 * @param any
	 *            the element to get the image from
	 * @return the URL of the image associated to the given {@link EObject}
	 */
	private static Object getImageURL(EObject any) {
		Object imageURL = null;
		// Step 1: getting the image URL thanks to the item delegator
		if (any instanceof EClass && ((EClassifier)any).getEPackage() != null) {
			EObject instance = ((EClassifier)any).getEPackage().getEFactoryInstance().create((EClass)any);
			imageURL = getItemDelegator(any).getImage(instance);
		} else {
			imageURL = getItemDelegator(any).getImage(any);
		}

		// Step 2: convert image location into URL format if needed
		if (!(imageURL instanceof URL)) {
			if (imageURL instanceof URI) {
				try {
					imageURL = new URL(imageURL.toString());
				} catch (MalformedURLException e) {
					// silent catch
				}
			} else {
				if (imageURL instanceof org.eclipse.emf.edit.provider.ComposedImage) {
					imageURL = ((org.eclipse.emf.edit.provider.ComposedImage)imageURL).getImages().iterator()
							.next();
				}
			}
		}
		return imageURL;
	}

	/**
	 * Copies the image associated to the given classifier (and with the given URL) into the given output
	 * folder.
	 * 
	 * @param classifier
	 *            the type associated to the image
	 * @param outputFolder
	 *            the output folder in which the image will be copied
	 * @param imageURL
	 *            the URL of the image to copy
	 * @param sourceStream
	 *            an input stream on the image
	 * @return the path where the image was copied
	 * @throws IOException
	 *             if files cannot be properly accessed
	 */
	private static String copyImageIfNeeded(EClassifier classifier, File outputFolder, URL imageURL,
			InputStream sourceStream) throws IOException {
		String packageName = "";
		if (classifier.getEPackage() != null) {
			packageName = classifier.getEPackage().getName();
		}
		File targetFile = new File(outputFolder.getAbsolutePath() + "/icons/generated/" + packageName
				+ imageURL.getFile().substring(imageURL.getFile().lastIndexOf('/')));
		// create folders if needed :
		new File(outputFolder.getAbsolutePath() + "/icons/generated/" + packageName).mkdirs();
		if (!targetFile.exists()) {
			ImageServices.copyFile(sourceStream, targetFile);
		}
		String copiedImagePath = targetFile.getAbsolutePath().toString();
		String outputFolderPath = outputFolder.getAbsolutePath();
		return "../" + copiedImagePath.substring(outputFolderPath.length());
	}

	/**
	 * Returns the {@link AdapterFactoryItemDelegator} to use for getting images associated to the given
	 * EObject.
	 * 
	 * @param any
	 *            the EObject the EObject to get the image from
	 * @return the {@link AdapterFactoryItemDelegator} to use for getting images associated to the given
	 *         EObject
	 */
	private static AdapterFactoryItemDelegator getItemDelegator(EObject any) {
		if (itemDelegator == null) {
			ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

			adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
			itemDelegator = new AdapterFactoryItemDelegator(adapterFactory);
		}
		return itemDelegator;
	}

	/**
	 * Returns the Resource set to use.
	 * 
	 * @return the resource set to use
	 */
	private static ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	/**
	 * Disposes the service.
	 */
	public static void dispose() {
		if (itemDelegator != null) {
			((ComposedAdapterFactory)itemDelegator.getAdapterFactory()).dispose();
		}
		resourceSet = null;
		itemDelegator = null;
	}

}

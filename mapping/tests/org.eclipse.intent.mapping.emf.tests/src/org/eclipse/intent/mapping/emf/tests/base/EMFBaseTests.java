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
package org.eclipse.intent.mapping.emf.tests.base;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.intent.mapping.Base;
import org.eclipse.intent.mapping.MappingPackage;
import org.eclipse.intent.mapping.base.IBase;
import org.eclipse.intent.mapping.emf.ICDOBinaryResourceLocation;
import org.eclipse.intent.mapping.emf.ICDOFolderLocation;
import org.eclipse.intent.mapping.emf.ICDORepositoryLocation;
import org.eclipse.intent.mapping.emf.ICDOResourceLocation;
import org.eclipse.intent.mapping.emf.ICDOTextResourceLocation;
import org.eclipse.intent.mapping.emf.IEObjectLocation;
import org.eclipse.intent.mapping.tests.base.AbstractBaseTests;
import org.eclipse.intent.mapping.tests.base.IBaseFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test {@link org.eclipse.intent.mapping.Base Base}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EMFBaseTests extends AbstractBaseTests {

	@Override
	protected IBaseFactory getFactory() {
		return Factory.INSTANCE;
	}

	@Test(expected = IOException.class)
	public void saveNotInResource() throws IOException {
		final IBase base = MappingPackage.eINSTANCE.getMappingFactory().createBase();

		base.save();
	}

	@Test
	public void save() throws IOException {
		final Base base = MappingPackage.eINSTANCE.getMappingFactory().createBase();
		base.setName("testBase");

		final ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		rs.getPackageRegistry().put(MappingPackage.eNS_URI, MappingPackage.eINSTANCE);
		rs.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		final Resource r = rs.createResource(URI.createURI("./test.mapping"));
		r.getContents().add(base);

		base.save();

		final ResourceSet rs2 = new ResourceSetImpl();
		rs2.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		rs2.getPackageRegistry().put(MappingPackage.eNS_URI, MappingPackage.eINSTANCE);
		rs2.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		final Resource r2 = rs2.getResource(URI.createFileURI("./test.mapping"), true);

		assertEquals(1, r2.getContents().size());
		assertTrue(r2.getContents().get(0) instanceof Base);
		assertEquals("testBase", ((Base)r2.getContents().get(0)).getName());

		r2.delete(null);

		assertTrue(!new File("./test.mapping").exists());
	}

	@Test
	public void testCreateEObjectLocation() throws Exception {
		final IBase base = getBase();
		final IEObjectLocation element = base.getFactory().createElement(IEObjectLocation.class);

		assertTrue(element instanceof IEObjectLocation);
	}

	@Test
	public void testCreateCDORepositoryLocation() throws Exception {
		final IBase base = getBase();
		final ICDORepositoryLocation element = base.getFactory().createElement(ICDORepositoryLocation.class);

		assertTrue(element instanceof ICDORepositoryLocation);
	}

	@Test
	public void testCreateCDOFolderLocation() throws Exception {
		final IBase base = getBase();
		final ICDOFolderLocation element = base.getFactory().createElement(ICDOFolderLocation.class);

		assertTrue(element instanceof ICDOFolderLocation);
	}

	@Test
	public void testCreateCDOBinaryResourceLocation() throws Exception {
		final IBase base = getBase();
		final ICDOBinaryResourceLocation element = base.getFactory().createElement(
				ICDOBinaryResourceLocation.class);

		assertTrue(element instanceof ICDOBinaryResourceLocation);
	}

	@Test
	public void testCreateCDOTextResourceLocation() throws Exception {
		final IBase base = getBase();
		final ICDOTextResourceLocation element = base.getFactory().createElement(
				ICDOTextResourceLocation.class);

		assertTrue(element instanceof ICDOTextResourceLocation);
	}

	@Test
	public void testCreateCDOResourceLocation() throws Exception {
		final IBase base = getBase();
		final ICDOResourceLocation element = base.getFactory().createElement(ICDOResourceLocation.class);

		assertTrue(element instanceof ICDOResourceLocation);
	}

}

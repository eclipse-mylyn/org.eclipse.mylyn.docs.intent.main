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
package org.eclipse.intent.mapping.emf.tests.connector;

import anydsl.AnydslPackage;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.intent.mapping.MappingUtils;
import org.eclipse.intent.mapping.base.BaseElementFactory;
import org.eclipse.intent.mapping.base.IBase;
import org.eclipse.intent.mapping.base.ILink;
import org.eclipse.intent.mapping.base.ILocation;
import org.eclipse.intent.mapping.base.IReport;
import org.eclipse.intent.mapping.emf.ICouple;
import org.eclipse.intent.mapping.emf.IEObjectLocation;
import org.eclipse.intent.mapping.emf.connector.EObjectConnector;
import org.eclipse.intent.mapping.emf.tests.connector.EObjectConnectorParametrizedTests.TestEObjectContainerConnector;
import org.eclipse.intent.mapping.emf.tests.connector.EObjectConnectorParametrizedTests.TestEObjectContainerLocation;
import org.eclipse.intent.mapping.emf.tests.connector.EObjectConnectorParametrizedTests.TestEObjectLocation;
import org.eclipse.intent.mapping.tests.base.BaseElementFactoryTests;
import org.eclipse.intent.mapping.tests.base.BaseElementFactoryTests.ITestLocation;
import org.eclipse.intent.mapping.tests.base.BaseElementFactoryTests.TestCouple;
import org.eclipse.intent.mapping.tests.base.BaseElementFactoryTests.TestReport;
import org.eclipse.intent.mapping.tests.base.BaseRegistryTests;
import org.eclipse.intent.mapping.tests.text.TextConnectorParametrizedTests.TestTextLocation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link EObjectConnector}.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EObjectConnectorTests extends EObjectConnector {

	/**
	 * The {@link EObjectContainerHelper}.
	 */
	private final EObjectContainerHelper eObjectContainerHelper = new EObjectContainerHelper();

	@Test
	public void getLocationTypeNotTypeContainer() {
		final Class<? extends ILocation> type = getLocationType(ITestLocation.class, "");

		assertNull(type);
	}

	@Test
	public void getLocationTypeNotElement() {
		final Class<? extends ILocation> type = getLocationType(TestEObjectLocation.class, new Object());

		assertNull(type);
	}

	@Test
	public void getLocationTypeEObject() {
		final Class<? extends ILocation> type = getLocationType(TestEObjectContainerLocation.class,
				AnydslPackage.eINSTANCE);

		assertEquals(IEObjectLocation.class, type);
	}

	@Test
	public void getLocationTypeESetting() {
		final Class<? extends ILocation> type = getLocationType(TestEObjectContainerLocation.class,
				((InternalEObject)AnydslPackage.eINSTANCE.getProducer()).eSetting(EcorePackage.eINSTANCE
						.getENamedElement_Name()));

		assertEquals(IEObjectLocation.class, type);
	}

	/**
	 * Creates a new {@link Resource}.
	 * 
	 * @return the created {@link Resource}
	 */
	protected Resource createResource(String name) {
		return new XMIResourceImpl(URI.createFileURI(name));
	}

	@Test
	public void initLocationEObject() throws Exception {
		final IBase base = new BaseRegistryTests.TestBase();
		base.getFactory().addDescriptor(ICouple.class, new BaseElementFactory.FactoryDescriptor<TestCouple>(
				TestCouple.class));
		final TestEObjectContainerLocation container = new TestEObjectContainerLocation();
		container.setContainer(base);
		final Copier copier = new Copier();
		final List<EObject> eObjects = new ArrayList<EObject>(copier.copyAll(AnydslPackage.eINSTANCE
				.eContents()));
		copier.copyReferences();
		final EClass producerEClass = (EClass)copier.get(AnydslPackage.eINSTANCE.getProducer());
		final Resource resource = createResource("test.xmi");
		resource.getContents().addAll(eObjects);
		container.setResource(resource);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);
		final IEObjectLocation location = new TestEObjectLocation();
		location.setContainer(container);

		final TestEObjectContainerConnector testEObjectContainerConnector = new TestEObjectContainerConnector();
		MappingUtils.getConnectorRegistry().register(testEObjectContainerConnector);
		final EObjectConnector eObjectConnector = new EObjectConnector();
		MappingUtils.getConnectorRegistry().register(eObjectConnector);
		super.initLocation(container, location, producerEClass);

		final Object element = eObjectConnector.getElement(location);
		assertTrue(element instanceof EObject);
		assertEquals(producerEClass, element);
		assertNull(location.getFeatureName());

		MappingUtils.getConnectorRegistry().unregister(testEObjectContainerConnector);
		MappingUtils.getConnectorRegistry().unregister(eObjectConnector);
	}

	@Test
	public void initLocationSetting() throws Exception {
		final IBase base = new BaseRegistryTests.TestBase();
		base.getFactory().addDescriptor(ICouple.class, new BaseElementFactory.FactoryDescriptor<TestCouple>(
				TestCouple.class));
		final TestEObjectContainerLocation container = new TestEObjectContainerLocation();
		container.setContainer(base);
		final Copier copier = new Copier();
		final List<EObject> eObjects = new ArrayList<EObject>(copier.copyAll(AnydslPackage.eINSTANCE
				.eContents()));
		copier.copyReferences();
		final EClass producerEClass = (EClass)copier.get(AnydslPackage.eINSTANCE.getProducer());
		final Resource resource = createResource("test.xmi");
		resource.getContents().addAll(eObjects);
		container.setResource(resource);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);
		final IEObjectLocation location = new TestEObjectLocation();
		location.setContainer(container);

		final TestEObjectContainerConnector testEObjectContainerConnector = new TestEObjectContainerConnector();
		MappingUtils.getConnectorRegistry().register(testEObjectContainerConnector);
		final EObjectConnector eObjectConnector = new EObjectConnector();
		MappingUtils.getConnectorRegistry().register(eObjectConnector);
		super.initLocation(container, location, ((InternalEObject)producerEClass).eSetting(
				EcorePackage.eINSTANCE.getENamedElement_Name()));

		final Object element = eObjectConnector.getElement(location);
		assertTrue(element instanceof Setting);
		assertEquals(producerEClass, ((Setting)element).getEObject());
		assertEquals(EcorePackage.eINSTANCE.getENamedElement_Name(), ((Setting)element)
				.getEStructuralFeature());
		assertEquals("Producer", ((Setting)element).get(true));
		assertEquals(EcorePackage.eINSTANCE.getENamedElement_Name().getName(), location.getFeatureName());

		MappingUtils.getConnectorRegistry().unregister(testEObjectContainerConnector);
		MappingUtils.getConnectorRegistry().unregister(eObjectConnector);
	}

	@Test
	public void getLocationEObject() throws Exception {
		final IBase base = new BaseRegistryTests.TestBase();
		base.getFactory().addDescriptor(ICouple.class, new BaseElementFactory.FactoryDescriptor<TestCouple>(
				TestCouple.class));
		final TestEObjectContainerLocation container = new TestEObjectContainerLocation();
		container.setContainer(base);
		final Copier copier = new Copier();
		final List<EObject> eObjects = new ArrayList<EObject>(copier.copyAll(AnydslPackage.eINSTANCE
				.eContents()));
		copier.copyReferences();
		final EClass producerEClass = (EClass)copier.get(AnydslPackage.eINSTANCE.getProducer());
		final Resource resource = createResource("test.xmi");
		resource.getContents().addAll(eObjects);
		container.setResource(resource);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);
		final IEObjectLocation location = new TestEObjectLocation();
		location.setContainer(container);
		container.getContents().add(location);

		final TestEObjectContainerConnector connector = new TestEObjectContainerConnector();
		MappingUtils.getConnectorRegistry().register(connector);
		super.initLocation(container, location, producerEClass);

		assertEquals(location, super.getLocation(container, producerEClass));
		assertEquals(null, super.getLocation(container, ((InternalEObject)producerEClass).eSetting(
				EcorePackage.eINSTANCE.getENamedElement_Name())));

		MappingUtils.getConnectorRegistry().unregister(connector);
	}

	@Test
	public void getLocationSetting() throws Exception {
		final IBase base = new BaseRegistryTests.TestBase();
		base.getFactory().addDescriptor(ICouple.class, new BaseElementFactory.FactoryDescriptor<TestCouple>(
				TestCouple.class));
		final TestEObjectContainerLocation container = new TestEObjectContainerLocation();
		container.setContainer(base);
		final Copier copier = new Copier();
		final List<EObject> eObjects = new ArrayList<EObject>(copier.copyAll(AnydslPackage.eINSTANCE
				.eContents()));
		final EClass producerEClass = (EClass)copier.get(AnydslPackage.eINSTANCE.getProducer());
		copier.copyReferences();
		final Resource resource = createResource("test.xmi");
		resource.getContents().addAll(eObjects);
		container.setResource(resource);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);
		final IEObjectLocation location = new TestEObjectLocation();
		location.setContainer(container);
		container.getContents().add(location);

		final TestEObjectContainerConnector connector = new TestEObjectContainerConnector();
		MappingUtils.getConnectorRegistry().register(connector);
		super.initLocation(container, location, ((InternalEObject)producerEClass).eSetting(
				EcorePackage.eINSTANCE.getENamedElement_Name()));

		assertEquals(null, super.getLocation(container, producerEClass));
		assertEquals(location, super.getLocation(container, ((InternalEObject)producerEClass).eSetting(
				EcorePackage.eINSTANCE.getENamedElement_Name())));

		MappingUtils.getConnectorRegistry().unregister(connector);
	}

	@Test
	public void updateEObjectContainerEObjectLocationDeleted() throws Exception {
		final IBase base = new BaseRegistryTests.TestBase();
		base.getFactory().addDescriptor(IReport.class, new BaseElementFactory.FactoryDescriptor<TestReport>(
				TestReport.class));
		base.getFactory().addDescriptor(ICouple.class, new BaseElementFactory.FactoryDescriptor<TestCouple>(
				TestCouple.class));
		final TestEObjectContainerLocation container = new TestEObjectContainerLocation();
		container.setContainer(base);
		final Copier copier = new Copier();
		final List<EObject> eObjects = new ArrayList<EObject>(copier.copyAll(AnydslPackage.eINSTANCE
				.eContents()));
		copier.copyReferences();
		final EClass producerEClass = (EClass)copier.get(AnydslPackage.eINSTANCE.getProducer());
		final Resource resource = createResource("test.xmi");
		resource.getContents().addAll(eObjects);
		container.setResource(resource);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);
		final IEObjectLocation location = new TestEObjectLocation();
		location.setContainer(container);
		final ILocation target = new TestTextLocation();
		final ILink link = new BaseElementFactoryTests.TestLink();
		location.getTargetLinks().add(link);
		link.setSource(location);
		target.getSourceLinks().add(link);
		link.setTarget(target);

		final TestEObjectContainerConnector connector = new TestEObjectContainerConnector();
		MappingUtils.getConnectorRegistry().register(connector);

		super.initLocation(container, location, producerEClass);

		resource.getContents().remove(producerEClass);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);

		assertTrue(location.isMarkedAsDeleted());
		assertEquals(1, base.getReports().size());
		final IReport report = base.getReports().get(0);
		assertEquals(link, report.getLink());
		assertTrue(report.getDescription().contains("Producer"));
		assertTrue(report.getDescription().contains(location.getURIFragment()));
		assertTrue(report.getDescription().contains("has been deleted."));

		MappingUtils.getConnectorRegistry().unregister(connector);
	}

	@Test
	public void updateEObjectContainerEObjectLocationChanged() throws Exception {
		final IBase base = new BaseRegistryTests.TestBase();
		base.getFactory().addDescriptor(IReport.class, new BaseElementFactory.FactoryDescriptor<TestReport>(
				TestReport.class));
		base.getFactory().addDescriptor(ICouple.class, new BaseElementFactory.FactoryDescriptor<TestCouple>(
				TestCouple.class));
		final TestEObjectContainerLocation container = new TestEObjectContainerLocation();
		container.setContainer(base);
		final Copier copier = new Copier();
		final List<EObject> eObjects = new ArrayList<EObject>(copier.copyAll(AnydslPackage.eINSTANCE
				.eContents()));
		copier.copyReferences();
		final EClass producerEClass = (EClass)copier.get(AnydslPackage.eINSTANCE.getProducer());
		final Resource resource = createResource("test.xmi");
		resource.getContents().addAll(eObjects);
		container.setResource(resource);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);
		final IEObjectLocation location = new TestEObjectLocation();
		location.setContainer(container);
		final ILocation target = new TestTextLocation();
		final ILink link = new BaseElementFactoryTests.TestLink();
		location.getTargetLinks().add(link);
		link.setSource(location);
		target.getSourceLinks().add(link);
		link.setTarget(target);

		final TestEObjectContainerConnector connector = new TestEObjectContainerConnector();
		MappingUtils.getConnectorRegistry().register(connector);

		super.initLocation(container, location, producerEClass);

		producerEClass.setInterface(!producerEClass.isInterface());
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);

		assertEquals(1, base.getReports().size());
		final IReport report = base.getReports().get(0);
		assertEquals(link, report.getLink());
		assertTrue(report.getDescription().contains("Attribure interface was false changed to true."));

		MappingUtils.getConnectorRegistry().unregister(connector);
	}

	@Test
	public void updateEObjectContainerSettingLocationChanged() throws Exception {
		final IBase base = new BaseRegistryTests.TestBase();
		base.getFactory().addDescriptor(IReport.class, new BaseElementFactory.FactoryDescriptor<TestReport>(
				TestReport.class));
		base.getFactory().addDescriptor(ICouple.class, new BaseElementFactory.FactoryDescriptor<TestCouple>(
				TestCouple.class));
		final TestEObjectContainerLocation container = new TestEObjectContainerLocation();
		container.setContainer(base);
		final Copier copier = new Copier();
		final List<EObject> eObjects = new ArrayList<EObject>(copier.copyAll(AnydslPackage.eINSTANCE
				.eContents()));
		copier.copyReferences();
		final EClass producerEClass = (EClass)copier.get(AnydslPackage.eINSTANCE.getProducer());
		final Resource resource = createResource("test.xmi");
		resource.getContents().addAll(eObjects);
		container.setResource(resource);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);
		final IEObjectLocation location = new TestEObjectLocation();
		location.setContainer(container);
		final ILocation target = new TestTextLocation();
		final ILink link = new BaseElementFactoryTests.TestLink();
		location.getTargetLinks().add(link);
		link.setSource(location);
		target.getSourceLinks().add(link);
		link.setTarget(target);

		final TestEObjectContainerConnector connector = new TestEObjectContainerConnector();
		MappingUtils.getConnectorRegistry().register(connector);

		super.initLocation(container, location, new Setting() {
			public void unset() {
				// nothing to do here
			}

			public void set(Object newValue) {
				// nothing to do here
			}

			public boolean isSet() {
				return false;
			}

			public EStructuralFeature getEStructuralFeature() {
				return EcorePackage.eINSTANCE.getEClass_Interface();
			}

			public EObject getEObject() {
				return producerEClass;
			}

			public Object get(boolean resolve) {
				return producerEClass.getName();
			}
		});

		producerEClass.setInterface(!producerEClass.isInterface());
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);

		assertEquals(1, base.getReports().size());
		final IReport report = base.getReports().get(0);
		assertEquals(link, report.getLink());
		assertTrue(report.getDescription().contains("Producer"));
		assertTrue(report.getDescription().contains(location.getURIFragment()));
		assertTrue(report.getDescription().contains(
				"feature interface value false has been changed to true."));

		MappingUtils.getConnectorRegistry().unregister(connector);
	}

	@Test
	public void updateEObjectContainerSettingLocationManyRemoved() throws Exception {
		final IBase base = new BaseRegistryTests.TestBase();
		base.getFactory().addDescriptor(IReport.class, new BaseElementFactory.FactoryDescriptor<TestReport>(
				TestReport.class));
		base.getFactory().addDescriptor(ICouple.class, new BaseElementFactory.FactoryDescriptor<TestCouple>(
				TestCouple.class));
		final TestEObjectContainerLocation container = new TestEObjectContainerLocation();
		container.setContainer(base);
		final Copier copier = new Copier();
		final List<EObject> eObjects = new ArrayList<EObject>(copier.copyAll(AnydslPackage.eINSTANCE
				.eContents()));
		copier.copyReferences();
		final EClass foodEClass = (EClass)copier.get(AnydslPackage.eINSTANCE.getFood());
		final Resource resource = createResource("test.xmi");
		resource.getContents().addAll(eObjects);
		container.setResource(resource);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);
		final IEObjectLocation location = new TestEObjectLocation();
		location.setContainer(container);
		final ILocation target = new TestTextLocation();
		final ILink link = new BaseElementFactoryTests.TestLink();
		location.getTargetLinks().add(link);
		link.setSource(location);
		target.getSourceLinks().add(link);
		link.setTarget(target);

		final TestEObjectContainerConnector connector = new TestEObjectContainerConnector();
		MappingUtils.getConnectorRegistry().register(connector);

		super.initLocation(container, location, new Setting() {
			public void unset() {
				// nothing to do here
			}

			public void set(Object newValue) {
				// nothing to do here
			}

			public boolean isSet() {
				return false;
			}

			public EStructuralFeature getEStructuralFeature() {
				return EcorePackage.eINSTANCE.getEClass_EOperations();
			}

			public EObject getEObject() {
				return foodEClass;
			}

			public Object get(boolean resolve) {
				return foodEClass.getEOperations().get(1);
			}
		});

		foodEClass.getEOperations().remove(1);
		eObjectContainerHelper.updateEObjectContainer(container.getContainer(), container, resource);

		assertEquals(1, base.getReports().size());
		final IReport report = base.getReports().get(0);
		assertEquals(link, report.getLink());
		assertTrue(report.getDescription().contains("Food"));
		assertTrue(report.getDescription().contains(location.getURIFragment()));
		assertTrue(report.getDescription().contains("has been removed from feature eOperations."));

		MappingUtils.getConnectorRegistry().unregister(connector);
	}

}

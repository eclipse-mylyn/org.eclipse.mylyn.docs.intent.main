/*******************************************************************************
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.mylyn.docs.intent.compare.match;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.DifferenceSource;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.compare.diff.IDiffProcessor;
import org.eclipse.emf.compare.match.eobject.PairCharDistance;
import org.eclipse.emf.compare.match.eobject.ProximityEObjectMatcher.DistanceFunction;
import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This distance function implementation will actually compare the given EObject.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class EditionDistance implements DistanceFunction {
	/**
	 * Weight coefficient of a change on a reference.
	 */
	private int referenceChangeCoef = 10;

	/**
	 * Weight coefficient of a change on an attribute.
	 */
	private int attributeChangeCoef = 10 + 10;

	/**
	 * Weight coefficient of a change of location (uri).
	 */
	private int locationChangeCoef = 1;

	/**
	 * Weight coefficient of a change of order within a reference.
	 */
	private int orderChangeCoef = 5;

	/**
	 * The list of specific weight to apply on specific Features.
	 */
	private Map<EStructuralFeature, Integer> weights;

	/**
	 * The list of features to ignore during the distance computation.
	 */
	private Set<EStructuralFeature> toBeIgnored;

	/**
	 * The equality helper used to retrieve the URIs through its cache and to instanciate a specific diff
	 * engine.
	 */
	private EqualityHelper helper;

	/**
	 * Instanciate a new Edition Distance using the given equality helper.
	 * 
	 * @param equalityHelper
	 *            the equality helper to use.
	 */
	public EditionDistance(EqualityHelper equalityHelper) {
		weights = Maps.newHashMap();
		this.helper = equalityHelper;
		this.toBeIgnored = Sets.newLinkedHashSet();
	}

	/**
	 * {@inheritDoc}
	 */
	public int distance(EObject a, EObject b, int maxDistance) {
		return new IntentCountingDiffEngine(this, maxDistance).measureDifferences(a, b);
	}

	/**
	 * Create a new builder to instanciate and configure an EditionDistance.
	 * 
	 * @param helper
	 *            the equality helper (required to instanciate an EditionDistance).
	 * @return a configuration builder.
	 */
	public static Builder builder(EqualityHelper helper) {
		return new Builder(helper);
	}

	/**
	 * Builder class to configure an EditionDistance instance.
	 */
	public static class Builder {
		/**
		 * The EditionDistance built by the builder.
		 */
		private EditionDistance toBeBuilt;

		/**
		 * Create the builder.
		 * 
		 * @param toBe
		 *            the equality helper (required to instanciate an EditionDistance).
		 */
		public Builder(EqualityHelper toBe) {
			this.toBeBuilt = new EditionDistance(toBe);
		}

		/**
		 * Specify a weight for a given feature.
		 * 
		 * @param feat
		 *            the feature to customize.
		 * @param weight
		 *            the weight, it will be multiplied by the type of change coefficient.
		 * @return the current builder instance.
		 */
		public Builder weight(EStructuralFeature feat, Integer weight) {
			this.toBeBuilt.weights.put(feat, weight);
			return this;
		}

		/**
		 * Specify a feature to ignore during the measure.
		 * 
		 * @param featToIgnore
		 *            the feature to ignore.
		 * @return the current builder instance.
		 */
		public Builder ignore(EStructuralFeature featToIgnore) {
			this.toBeBuilt.toBeIgnored.add(featToIgnore);
			return this;
		}

		/**
		 * Specify the weight of any change of uri between two instances.
		 * 
		 * @param weight
		 *            the new weight.
		 * @return the current builder instance.
		 */
		public Builder uri(int weight) {
			this.toBeBuilt.locationChangeCoef = weight;
			return this;
		}

		/**
		 * Specify the weight of any change of reference order between two instances.
		 * 
		 * @param weight
		 *            the new weight.
		 * @return the current builder instance.
		 */

		public Builder order(int weight) {
			this.toBeBuilt.orderChangeCoef = weight;
			return this;
		}

		/**
		 * Specify the weight of any change of attribute value between two instances.
		 * 
		 * @param weight
		 *            the new weight.
		 * @return the current builder instance.
		 */

		public Builder attribute(int weight) {
			this.toBeBuilt.attributeChangeCoef = weight;
			return this;
		}

		/**
		 * Specify the weight of any change of reference between two instances.
		 * 
		 * @param weight
		 *            the new weight.
		 * @return the current builder instance.
		 */

		public Builder reference(int weight) {
			this.toBeBuilt.referenceChangeCoef = weight;
			return this;
		}

		/**
		 * return the configured instance.
		 * 
		 * @return the configured instance.
		 */
		public EditionDistance build() {
			return toBeBuilt;
		}
	}

	/**
	 * This class is an implementation of a {@link IDiffProcessor} which counts the number of differences to
	 * given an overall distance between two objects.
	 */
	class CountingDiffProcessor implements IDiffProcessor {
		/**
		 * The current distance.
		 */
		private int distance;

		/**
		 * {@inheritDoc}
		 */
		public void referenceChange(Match match, EReference reference, EObject value, DifferenceKind kind,
				DifferenceSource source) {
			distance += getWeight(reference) * referenceChangeCoef;
		}

		/**
		 * {@inheritDoc}
		 */
		public void attributeChange(Match match, EAttribute attribute, Object value, DifferenceKind kind,
				DifferenceSource source) {
			Object aValue = match.getLeft().eGet(attribute);
			Object bValue = match.getRight().eGet(attribute);
			switch (kind) {
				case MOVE:
					distance += getWeight(attribute) * orderChangeCoef;
					break;
				case ADD:
				case DELETE:
				case CHANGE:
					if (aValue instanceof String && bValue instanceof String) {
						distance += getWeight(attribute)
								* new PairCharDistance().distance((String)aValue, (String)bValue);
					} else {
						distance += getWeight(attribute) * attributeChangeCoef;
					}
					break;
				default:
					break;
			}
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.emf.compare.diff.IDiffProcessor#resourceAttachmentChange(org.eclipse.emf.compare.Match,
		 *      java.lang.String, org.eclipse.emf.compare.DifferenceKind,
		 *      org.eclipse.emf.compare.DifferenceSource)
		 */
		public void resourceAttachmentChange(Match match, String uri, DifferenceKind kind,
				DifferenceSource source) {
			// Not important for the distance computation
		}

		/**
		 * Return the weight for the given feature.
		 * 
		 * @param attribute
		 *            any {@link EStructuralFeature}.
		 * @return the weight for the given feature.
		 */
		private int getWeight(EStructuralFeature attribute) {
			Integer found = weights.get(attribute);
			if (found == null) {
				found = Integer.valueOf(1);
			}
			return found.intValue();
		}

		/**
		 * return the computed distance.
		 * 
		 * @return the computed distance.
		 */
		public int getComputedDistance() {
			return distance;
		}

	}

	/**
	 * An implementation of a diff engine which count and measure the detected changes.
	 */
	class CountingDiffEngine extends DefaultDiffEngine {
		/**
		 * A fake comparison object required so that the diff engine does his job correctly.
		 */
		private Comparison fakeComparison;

		/**
		 * The maximum distance until which we just have to stop.
		 */
		private int maxDistance;

		/**
		 * Create the diff engine.
		 * 
		 * @param maxDistance
		 *            the maximum distance we might reach.
		 */
		public CountingDiffEngine(int maxDistance) {
			super(new CountingDiffProcessor());
			this.maxDistance = maxDistance;
			this.fakeComparison = CompareFactory.eINSTANCE.createComparison();
			this.helper = EditionDistance.this.helper;

		}

		@Override
		protected void computeDifferences(Match match, EAttribute attribute, boolean checkOrdering) {
			if (getCounter().getComputedDistance() <= maxDistance) {
				super.computeDifferences(match, attribute, checkOrdering);
			}
		}

		@Override
		protected void computeDifferences(Match match, EReference reference, boolean checkOrdering) {
			if (getCounter().getComputedDistance() <= maxDistance) {
				super.computeDifferences(match, reference, checkOrdering);
			}
		}

		/**
		 * Measure the difference between two objects and return a distance value.
		 * 
		 * @param a
		 *            first object.
		 * @param b
		 *            second object.
		 * @return the distance between them computed using the number of changes required to change a to b.
		 */
		public int measureDifferences(EObject a, EObject b) {
			Match fakeMatch = CompareFactory.eINSTANCE.createMatch();
			fakeMatch.setLeft(a);
			fakeMatch.setRight(b);
			URI aLocation = helper.getURI(a);
			URI bLocation = helper.getURI(b);
			int changes = 0;
			if (!aLocation.fragment().equals(bLocation.fragment())) {
				int dist = new PairCharDistance().distance(aLocation.fragment(), bLocation.fragment());
				changes += dist * locationChangeCoef * 1;
			}
			if (changes <= maxDistance) {
				checkForDifferences(fakeMatch);
				changes += getCounter().getComputedDistance();
			}
			return changes;

		}

		protected CountingDiffProcessor getCounter() {
			return (CountingDiffProcessor)getDiffProcessor();
		}

		@Override
		protected Comparison getComparison() {
			return fakeComparison;
		}

		@Override
		protected FeatureFilter createFeatureFilter() {
			return new FeatureFilter() {

				@Override
				public Iterator<EReference> getReferencesToCheck(Match match) {
					return Iterators.filter(super.getReferencesToCheck(match), new Predicate<EReference>() {

						public boolean apply(EReference input) {
							return toBeIgnored.contains(input) && !input.isContainment();
						}
					});
				}

				@Override
				public Iterator<EAttribute> getAttributesToCheck(Match match) {
					return Iterators.filter(super.getAttributesToCheck(match), new Predicate<EAttribute>() {

						public boolean apply(EAttribute input) {
							return !toBeIgnored.contains(input);
						}
					});
				}

			};
		}

	}

}

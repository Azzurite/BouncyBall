package name.azzu.bouncyballsimulation.universe;

import name.azzu.bouncyballsimulation.simulation.HitGroundNotifier;

public abstract class PlanetMatterPhysics extends HitGroundNotifier {

	/**
	 * Performs the interaction between matter and a nearby planet
	 * 
	 * @param matter
	 * @param nearbyPlanet
	 */
	public abstract void performPhysics(Matter matter, Planet nearbyPlanet);

}

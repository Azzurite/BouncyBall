package name.azzu.bouncyballsimulation.simulation;

import name.azzu.bouncyballsimulation.universe.Matter;

/**
 * Signifies a class that can accept hit ground notifications
 */
public interface HitGroundListener {

	/**
	 * Signals the listener that the matter m hit the ground.
	 *
	 * @param m
	 */
	public void hitGround(Matter m);

}

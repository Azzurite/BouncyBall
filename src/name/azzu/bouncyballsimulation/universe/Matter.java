package name.azzu.bouncyballsimulation.universe;

import name.azzu.bouncyballsimulation.simulation.HitGroundNotifier;

/**
 * Matter in the universe. Currently its only property is having a height and falling/rising. lol
 */
public class Matter extends HitGroundNotifier {

	private double verticalVelocity = 0;

	private double height = 0;

	/**
	 * Creates matter. Destroys don't matter.
	 */
	public Matter() {
	}

	/**
	 * Creates matter with an initial velocity.
	 *
	 * @param initialVelocity
	 */
	public Matter(double initialVelocity) {
		verticalVelocity = initialVelocity;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the vertical velocity
	 */
	public double getVerticalVelocity() {
		return verticalVelocity;
	}

	/**
	 * Makes the matter hit the floor. Something something dinosaur <br/>
	 * Also signals the registered listeners that the matter hit the floor.
	 *
	 * @param groundBounciness
	 */
	protected void hitGround(double groundBounciness) {
		setHeight(getHeight() * groundBounciness);
		setVerticalVelocity(getVerticalVelocity() * groundBounciness);
		notifyListeners(this);
	}

	/**
	 * A second passed in the universe! Update the matter with things that happened this second.
	 *
	 * @param nearbyPlanet
	 *            the nearby planet the matter is affected by
	 */
	public void secondPassed(Planet nearbyPlanet) {
		if (nearbyPlanet == null) {
			// What?! No nearby planet? It's madness! At least not worht being simulated.
			return;
		}

		final Gravity currentGravity = nearbyPlanet.getGravity();

		// this is the specified bouncing algorithm. It is terribly wrong though, a ball can never stop bouncing.
		height += verticalVelocity;
		verticalVelocity -= currentGravity.getVelocityDecay();

		if (height < 0) {
			hitGround(nearbyPlanet.getGroundBounciness());
		}
	}

	protected void setHeight(double height) {
		this.height = height;
	}

	protected void setVerticalVelocity(double verticalVelocity) {
		this.verticalVelocity = verticalVelocity;
	}
}

package name.azzu.bouncyballsimulation.universe;

import name.azzu.bouncyballsimulation.simulation.HitGroundListener;
import name.azzu.bouncyballsimulation.simulation.HitGroundNotifier;

/**
 * Matter in the universe. Currently its only property is having a height and falling/rising. lol
 */
public class Matter extends HitGroundNotifier implements HitGroundListener {

	private double verticalVelocity = 0;

	private double height = 0;

	private final PlanetMatterPhysics physicsStrategy;

	/**
	 * Creates matter. Destroys don't matter.
	 */
	public Matter() {
		this.physicsStrategy = new BetterPlanetMatterPhysics();
	}

	/**
	 * Creates matter with an initial velocity.
	 *
	 * @param initialVelocity
	 */
	public Matter(double initialVelocity, PlanetMatterPhysics physicsStrategy) {
		verticalVelocity = initialVelocity;

		physicsStrategy.addHitGroundListener(this);
		this.physicsStrategy = physicsStrategy;
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
		physicsStrategy.performPhysics(this, nearbyPlanet);
	}

	@Override
	public void hitGround(Matter m, double processedTime) {
		if (this == m) {
			notifyListeners(this, processedTime);
		}
	}

	protected void setHeight(double height) {
		this.height = height;
	}

	protected void setVerticalVelocity(double verticalVelocity) {
		this.verticalVelocity = verticalVelocity;
	}

	/**
	 * @param matter
	 * @return if the matter is effectively resting on the ground.
	 */
	public static boolean isEffectivelyResting(Matter matter) {
		return matter.getVerticalVelocity() < 0.1 && matter.getHeight() < 0.1;
	}
}

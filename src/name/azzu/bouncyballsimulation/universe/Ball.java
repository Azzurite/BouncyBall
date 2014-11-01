package name.azzu.bouncyballsimulation.universe;

/**
 * A ball is matter that can bounce off the ground! Yay!
 */
public class Ball extends Matter {

	/**
	 * Create a ball with an initial vertical velocity. Higher means higher bounce!
	 *
	 * @param initialVelocity
	 */
	public Ball(double initialVelocity, PlanetMatterPhysics physicsStrategy) {
		super(initialVelocity, physicsStrategy);
	}

}

package name.azzu.bouncyballsimulation.universe;

/**
 * A planet in a galaxy far, far away...
 */
public class Planet {

	private final String name;

	private final Gravity gravity;

	private final double groundBounciness;

	/**
	 * Creates a new planet.
	 *
	 * @param name
	 * @param gravity
	 * @param groundBounciness
	 */
	public Planet(String name, Gravity gravity, double groundBounciness) {
		this.name = name;
		this.gravity = gravity;
		this.groundBounciness = groundBounciness;
	}

	/**
	 * @return the gravity of the planet
	 */
	public Gravity getGravity() {
		return gravity;
	}

	public double getGroundBounciness() {
		return groundBounciness;
	}

	/**
	 * @return the name of the planet
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Planet " + name + ". Current gravity: " + gravity + ". Bounciness: " + groundBounciness;
	}

}

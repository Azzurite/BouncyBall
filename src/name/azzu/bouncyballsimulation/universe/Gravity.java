package name.azzu.bouncyballsimulation.universe;

/**
 * Represents the gravity physics on a planet.
 */
public class Gravity {

	@Override
	public String toString() {
		return "velocity decay - " + velocityDecay;
	}

	private final Double velocityDecay;

	/**
	 * Creates a new gravity object valid for a planet
	 * 
	 * @param velocityDecay
	 */
	public Gravity(double velocityDecay) {
		this.velocityDecay = velocityDecay;
	}

	/**
	 * @return the velocity decay
	 */
	public double getVelocityDecay() {
		return velocityDecay;
	}
}

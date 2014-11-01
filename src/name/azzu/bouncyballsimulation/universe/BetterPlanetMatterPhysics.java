package name.azzu.bouncyballsimulation.universe;

/**
 * uses the physics formula of uniform accelerated movement
 */
public class BetterPlanetMatterPhysics extends PlanetMatterPhysics {

	/**
	 * Can perform multiple bounces, if, in 1 second, multiple bounces should happen.
	 */
	@Override
	public void performPhysics(Matter matter, Planet nearbyPlanet) {
		Gravity planetGravity = nearbyPlanet.getGravity();
		double hitsGroundIn = getHitGroundTime(matter, planetGravity);
		double processedTime = 0;
		double currentTimeStep = 1;
		while (hitsGroundIn < currentTimeStep && !Matter.isEffectivelyResting(matter)) {
			double hitGroundVelocity = advanceVelocity(hitsGroundIn, matter.getVerticalVelocity(),
					planetGravity.getVelocityDecay());
			double afterBounceVelocity = hitGroundVelocity * nearbyPlanet.getGroundBounciness();

			processedTime += hitsGroundIn;
			notifyListeners(matter, processedTime);

			currentTimeStep = currentTimeStep - hitsGroundIn;
			matter.setHeight(0);
			matter.setVerticalVelocity(afterBounceVelocity);
			hitsGroundIn = getHitGroundTime(matter, planetGravity);
		}
		double height;
		double velocity;
		if (!Matter.isEffectivelyResting(matter)) {
			height = advanceHeight(currentTimeStep, matter.getHeight(), matter.getVerticalVelocity(),
					planetGravity.getVelocityDecay());
			velocity = advanceVelocity(currentTimeStep, matter.getVerticalVelocity(), planetGravity.getVelocityDecay());
		} else {
			height = 0;
			velocity = 0;
		}
		matter.setHeight(height);
		matter.setVerticalVelocity(velocity);
	}

	/**
	 * 
	 * @param seconds
	 * @param verticalVelocity
	 * @param velocityDecay
	 *            a positive number which denotes how much the velocity should be reduced by each second
	 * @return the new velocity of the object after the given time
	 */
	private static double advanceVelocity(double seconds, double verticalVelocity, double velocityDecay) {
		return verticalVelocity - seconds * velocityDecay;
	}

	/**
	 * 
	 * @param seconds
	 * @param height
	 *            the current height
	 * @param verticalVelocity
	 *            current velocity
	 * @param velocityDecay
	 *            a positive number which denotes how much the velocity should be reduced by each second
	 * @return the height of the object after the given time
	 */
	private static double advanceHeight(double seconds, double height, double verticalVelocity, double velocityDecay) {
		return -velocityDecay / 2 * seconds * seconds + verticalVelocity * seconds + height;
	}

	/**
	 * @param height
	 * @param velocity
	 * @param planetGravity
	 * @return the time after which the object hits the ground
	 */
	private static double getHitGroundTime(Matter matter, Gravity planetGravity) {
		double a = planetGravity.getVelocityDecay();
		double v = matter.getVerticalVelocity();
		double h = matter.getHeight();

		double time = (Math.sqrt(2 * a * h + Math.pow(v, 2)) + v) / a;
		// System.out.println("Hits ground in: " + (-time) + " or alternatively "
		// + (-((Math.sqrt(2 * a * h + Math.pow(v, 2)) - v) / a)));
		return time;
	}

}

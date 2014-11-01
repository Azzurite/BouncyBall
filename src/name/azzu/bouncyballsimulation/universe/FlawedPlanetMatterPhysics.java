package name.azzu.bouncyballsimulation.universe;

public class FlawedPlanetMatterPhysics extends PlanetMatterPhysics {

	@Override
	public void performPhysics(Matter matter, Planet nearbyPlanet) {
		matter.setVerticalVelocity(getNewVerticalVelocity(matter, nearbyPlanet.getGravity()));
		matter.setHeight(getNewHeight(matter));

		boolean hasHitGround = matter.getHeight() < 0;
		if (hasHitGround) {
			performHitGroundPhysics(matter, nearbyPlanet.getGroundBounciness());
			notifyListeners(matter, 1);
		}
	}

	private static double getNewVerticalVelocity(Matter matter, Gravity gravity) {
		double verticalVelocity = matter.getVerticalVelocity();
		double velocityDecay = gravity.getVelocityDecay();
		return verticalVelocity - velocityDecay;
	}

	private static double getNewHeight(Matter matter) {
		double height = matter.getHeight();
		double verticalVelocity = matter.getVerticalVelocity();
		return height + verticalVelocity;
	}

	private static void performHitGroundPhysics(Matter matter, double groundBounciness) {
		matter.setHeight(matter.getHeight() * groundBounciness);
		matter.setVerticalVelocity(matter.getVerticalVelocity() * groundBounciness);
	}
}

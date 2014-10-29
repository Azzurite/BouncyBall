package name.azzu.bouncyballsimulation.simulation;

import name.azzu.bouncyballsimulation.universe.Ball;
import name.azzu.bouncyballsimulation.universe.Matter;
import name.azzu.bouncyballsimulation.universe.Planet;

/**
 * The bouncy ball simulation simulates a ball bouncing on a planet.
 */
public class BouncyBallSimulation extends HitGroundNotifier implements HitGroundListener {

	private final Planet simulatedPlanet;

	private final Ball bouncyBall;

	private int ballHitGroundCounter = 0;

	private double prevBallHeight = Double.NaN;

	private boolean isBallResting;

	private int currentSecond = 0;

	/**
	 * Creates a new simulation.
	 *
	 * @param simulatedPlanet
	 * @param bouncyBall
	 */
	public BouncyBallSimulation(Planet simulatedPlanet, Ball bouncyBall) {
		if (simulatedPlanet == null) {
			throw new IllegalArgumentException("The simulated planet can not be missing!");
		}
		if (bouncyBall == null) {
			throw new IllegalArgumentException("The bouncy ball can not be missing!");
		}
		bouncyBall.addHitGroundListener(this);

		this.simulatedPlanet = simulatedPlanet;
		this.bouncyBall = bouncyBall;
		isBallResting = bouncyBall.getHeight() == 0 && bouncyBall.getVerticalVelocity() == 0;
	}

	/**
	 * Advance one second in the simulation.
	 */
	public void advanceSecond() {
		bouncyBall.secondPassed(simulatedPlanet);
		++currentSecond;

		setBallRestingStatus();
		prevBallHeight = bouncyBall.getHeight();
	}

	/**
	 * @return how often the ball hit the ground.
	 */
	public int getBallHitGroundCounter() {
		return ballHitGroundCounter;
	}

	/**
	 * @return the ball being simulated
	 */
	public Ball getBouncyBall() {
		return bouncyBall;
	}

	/**
	 * @return the last simulated second.
	 */
	public int getCurrentSecond() {
		return currentSecond;
	}

	/**
	 * @return the simulated planet
	 */
	public Planet getSimulatedPlanet() {
		return simulatedPlanet;
	}

	@Override
	public void hitGround(Matter m) {
		++ballHitGroundCounter;
		notifyListeners(m);
	}

	/**
	 * @return if the ball is resting.
	 */
	public boolean isBallResting() {
		return isBallResting;
	}

	private void setBallRestingStatus() {
		isBallResting = bouncyBall.getHeight() == 0 && prevBallHeight == 0;
	}
}

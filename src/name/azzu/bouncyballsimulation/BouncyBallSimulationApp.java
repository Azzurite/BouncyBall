package name.azzu.bouncyballsimulation;

import java.util.ArrayList;
import java.util.List;

import name.azzu.bouncyballsimulation.simulation.BouncyBallSimulation;
import name.azzu.bouncyballsimulation.universe.Ball;
import name.azzu.bouncyballsimulation.universe.Gravity;
import name.azzu.bouncyballsimulation.universe.Planet;
import name.azzu.bouncyballsimulation.view.UserInterface;

public class BouncyBallSimulationApp implements Runnable {

	public static void main(String[] args) {
		new BouncyBallSimulationApp().run();
	}

	private static boolean shouldStop(BouncyBallSimulation simulation) {
		return simulation.isBallResting() || simulation.getBallHitGroundCounter() >= 5;
	}

	private static final List<Planet> SIMULATION_PLANETS;

	static {
		final List<Planet> planets = new ArrayList<>();

		final Planet earth = new Planet("Earth", new Gravity(32), -0.5);
		final Planet discWorld = new Planet("Discworld", new Gravity(6), -3);
		final Planet krypton = new Planet("Krypton", new Gravity(74), -0.22);

		planets.add(earth);
		planets.add(discWorld);
		planets.add(krypton);
		SIMULATION_PLANETS = planets;
	}

	private final UserInterface ui;

	/**
	 * Creates and initializes the bouncy ball simulation app.
	 */
	public BouncyBallSimulationApp() {
		ui = new UserInterface();
	}

	@Override
	public void run() {
		while (true) {
			final Planet selectedPlanet = UserInterface.selectPlanet(SIMULATION_PLANETS);
			if (selectedPlanet == null) {
				return;
			}
			final double initialVelocity = UserInterface.selectVelocity();

			final BouncyBallSimulation simulation = new BouncyBallSimulation(selectedPlanet, new Ball(initialVelocity));
			simulation.addHitGroundListener(matter -> UserInterface.displayBounce());

			while (!shouldStop(simulation)) {
				simulation.advanceSecond();
				UserInterface.displaySimulationState(simulation);
			}

			System.out.println("The simulation has finished. Try another one.");
		}
	}
}

package name.azzu.bouncyballsimulation.view;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import name.azzu.bouncyballsimulation.simulation.BouncyBallSimulation;
import name.azzu.bouncyballsimulation.universe.Planet;

/**
 * User interface for the Bouncy Ball Simulation app.
 */
public class UserInterface {

	public static void displayBounce(BouncyBallSimulation simulation, double processedTime) {
		System.out.println("Bounce at " + (simulation.getCurrentSecond() + processedTime) + " seconds!");
	}

	public static void displaySimulationState(BouncyBallSimulation simulation) {
		System.out.println("Time: " + simulation.getCurrentSecond() + " Height: "
				+ simulation.getBouncyBall().getHeight() + " Ball bounces: " + simulation.getBallHitGroundCounter());
	}

	private static String getStringFromUser(String request) {
		System.out.print(request + ": ");
		final String userEntered = SYSTEM_IN.next();
		System.out.println();
		return userEntered;
	}

	public static Planet selectPlanet(List<Planet> simulationPlanets) {
		return showMenu("Planet Menu", simulationPlanets, planet -> planet.toString());
	}

	private static <T> T showMenu(String menuName, List<T> options, Function<T, String> toString) {
		while (true) {
			try {
				int selection;
				System.out.println("*** " + menuName + " ***");
				for (int i = 0; i < options.size(); ++i) {
					System.out.println((i + 1) + ".) " + toString.apply(options.get(i)));
				}
				System.out.println("0.) Quit");
				System.out.println("********************");
				final String selectionString = getStringFromUser("Enter your option");
				selection = Integer.parseInt(selectionString);

				final boolean isSelectionAccepted = selection == 0 || selection <= options.size();
				if (!isSelectionAccepted) {
					System.out.println("Your selection " + selection
							+ " is not a supported option. Please choose one of the following numbers.");
					continue;
				}

				return selection == 0 ? null : options.get(selection - 1);
			} catch (final Exception e) {
				System.err.println("The selection was not a number! Please enter one of the following numbers.");
				continue;
			}
		}
	}

	public static double selectVelocity() {
		double velocity;
		while (true) {
			try {
				final String velocityString = getStringFromUser("Initial velocity");
				velocity = Double.parseDouble(velocityString);
				break;
			} catch (final Exception e) {
				System.err.println("The velocity was not a number! Try again.");
				continue;
			}
		}
		return velocity;
	}

	private static final Scanner SYSTEM_IN = new Scanner(System.in);

	public static String selectPhysics(List<String> physics) {
		return showMenu("Select physics implementation", physics, String::toString);
	}

}

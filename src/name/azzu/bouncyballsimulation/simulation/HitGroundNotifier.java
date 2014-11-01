package name.azzu.bouncyballsimulation.simulation;

import java.util.ArrayList;
import java.util.List;

import name.azzu.bouncyballsimulation.universe.Matter;

/**
 * Implementing classes can notify other classes that matter hit the ground.
 */
public abstract class HitGroundNotifier {

	private final List<HitGroundListener> listeners = new ArrayList<>();

	/**
	 * Adds a listener for hit ground notifications.
	 *
	 * @param listener
	 */
	public void addHitGroundListener(HitGroundListener listener) {
		listeners.add(listener);
	}

	/**
	 * Notifies all listeners that the matter hit the ground.
	 *
	 * @param matter
	 * @param processedTime
	 */
	public void notifyListeners(Matter matter, double processedTime) {
		for (final HitGroundListener listener : listeners) {
			listener.hitGround(matter, processedTime);
		}
	}
}

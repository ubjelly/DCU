package org.dcu.util;

/**
 * A simple class for keeping track of events in seconds.
 * @author Stephen Andrews
 */
public class Timer {

	/**
	 * The starting time of the event.
	 */
	private long startTime;
	
	/**
	 * How long the event should last in seconds.
	 */
	private int duration;
	
	/**
	 * Constructs a timer object.
	 * @param duration How long the event should last in seconds.
	 */
	public Timer(int duration) {
		startTime = System.currentTimeMillis();
		this.duration = duration;
	}
	
	/**
	 * Gets how long the event has been running in seconds.
	 */
	public int getEventDuration() {
		return (int) (System.currentTimeMillis() - startTime)/1000;
	}
	
	/**
	 * Gets the remaining seconds for the event.
	 */
	public int getRemainingSeconds() {
		return duration - getEventDuration();
	}
}

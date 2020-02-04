package simulation.main;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		/*
		 * Environment e = new Environment(); e.addSensorsToLocation(0);
		 * e.addSensorsToLocation(1); e.addSensorsToLocation(2);/
		 **/
		// ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor)
		// Executors.newScheduledThreadPool(1);
		// Periodictask task = new Periodictask(e);
		// executor.scheduleWithFixedDelay(task, 2, 2, TimeUnit.SECONDS);
		try (Scanner sc = new Scanner(System.in)) {
			App app = new App(sc);
			app.Intiate();
		}
	}

}

package simulation.main;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import simulation.envrionment.Environment;

public class App {

	private Environment environment;
	Scanner sc;

	public App(Scanner sc) {
		this.environment = new Environment();
		this.sc = sc;
	}

	public void Intiate() {

		System.out.println("Welcome: Starting the Initialization\n===============================================");
		System.out.println("\n1- Environmet varible init");
		// int line = sc.nextInt();
		// System.out.println("Enter + to add location\n" + "Enter s to configure
		// sensors\n"
		// + "Enter a to activate sensor\n" + "Enter d to dectivate sensor\n");
		// System.out.println("To end calibration please Enter e");
		System.out.println("==>A for Add loc\n==>C for change range\n==>P for set period" + "\n==>E for exit config");
		int P = 10;
		while (true) {
			String S = sc.nextLine();
			if (S.equals("A"))
				handleConfigSensor();
			else if (S.equals("C"))
				handleConfigRange();
			else if (S.equals("P"))
				P = getIntValue("pleas enter int");
			else if (S.equals("E"))
				break;
		}
		this.environment.pritn();
		superLoop(P);
	}

	private void handleConfigRange() {
		while (true) {
			try {
				System.out.println("Loc type min max");
				String S = sc.nextLine();
				String[] arrSplit_2 = S.split(" ");
				int point = Integer.parseInt(arrSplit_2[0]);
				float min = Float.parseFloat(arrSplit_2[2]);
				float max = Float.parseFloat(arrSplit_2[3]);
				String sensortype = arrSplit_2[1];
				this.environment.setSensorsRange(point, sensortype, min, max);
				return;
			} catch (Exception e) {
				System.out.println("please enter correct value");
			}
		}
	}

	private void handleConfigSensor() {
		while (true) {
			try {
				System.out.println("Loc type value");
				String S = sc.nextLine();
				String[] arrSplit_2 = S.split(" ");
				int point = Integer.parseInt(arrSplit_2[0]);
				float value = Float.parseFloat(arrSplit_2[2]);
				String sensortype = arrSplit_2[1];
				this.environment.setSensorsValue(point, sensortype, value);
				return;
			} catch (Exception e) {
				System.out.println("please enter correct value");
			}
		}
	}

	private void handleActivate() {
		// System.out.println("Loc");
		int point = this.getIntValue("Loc");
		this.environment.removesensorsfromLocation(point);
	}

	private final int getIntValue(String printer) {
		System.out.println("::>> " + printer);
		while (true) {
			try {
				return sc.nextInt();
			} catch (Exception e) {
				sc.next();
				System.out.println("please inter intger value");
			}
		}
	}

	private final String getStringValue(String printer) {
		System.out.println("::>> " + printer);
		while (true) {
			try {
				return sc.next();
			} catch (Exception e) {
				sc.next();
				System.out.println("please inter String value");
			}
		}
	}

	public void superLoop(int P) {
		// Environment e = new Environment(); e.addSensorsToLocation(0);
		// e.addSensorsToLocation(1); e.addSensorsToLocation(2);
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		Periodictask task = new Periodictask(this.environment);
		executor.scheduleWithFixedDelay(task, P, P, TimeUnit.SECONDS);

		while (true) {
			String S = sc.nextLine();
			if (S.equals("C"))
				handleConfigRange();
			else if (S.equals("N"))
				this.environment.ReadValue();
			else if (S.equals("D"))
				handleActivate();
		}
	}

}

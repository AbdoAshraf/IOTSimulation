package simulation.main;

import java.util.Scanner;

import simulation.envrionment.Environment;
import simulation.envrionment.dto.LightSensorDTO;
import simulation.envrionment.dto.LigthSensorMeasurementsDto;
import simulation.envrionment.dto.TempSensorDTO;

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
				addLocationHandler();
			else if (S.equals("SA"))
				sensorConfigHandler();
			else if (S.equals("SD"))
				sensorConfigHandler();
			else if (S.equals("AD"))
				activationHandler();
			else if (S.equals("E"))
				break;
			
		}
		SuperLoop();
	}

	private void activationHandler() {
		String m = this.getStringValue("a for activation d for deactivation");
		if (m.equals("a")) {
			while (true) {
				try {
					System.out.println("loc sensor");
					String S = sc.nextLine();
					String[] arrSplit = S.split(" ");
					int point = Integer.parseInt(arrSplit[0]);
					String SensorType = arrSplit[1];
					activateSensor(point,SensorType);
					return;
				} catch (Exception e) {
					System.out.println("please enter correct value");
				}
			}
		}
		else if (m.equals("d")) {
			while (true) {
				try {
					System.out.println("loc sensor");
					String S = sc.nextLine();
					String[] arrSplit = S.split(" ");
					int point = Integer.parseInt(arrSplit[0]);
					String SensorType = arrSplit[1];
					detivateSensor(point,SensorType);
					return;
				} catch (Exception e) {
					System.out.println("please enter correct value");
				}
			}
		}
			
	}

	private void detivateSensor(int point, String sensorType) {
		switch (sensorType) {
		case "temp":
			this.environment.deactivateTempSensor(point);
			break;
		case "light":
			this.environment.deactivateLightSensor(point);
			break;
		default:
			break;
		}
	}

	private void activateSensor(int point, String sensorType) {
		switch (sensorType) {
		case "temp":
			this.environment.activateTempSensor(point);
			break;
		case "light":
			this.environment.activateLigrtSensor(point);
			break;
		default:
			break;
		}

	}

	private void SuperLoop() {
		
		
	}

	private void sensorConfigHandler() {
		String sensorType = this.getStringValue("enter Sensor type ");
		switch (sensorType) {
		case "temp":
			initTempSensor();
			break;
		case "light":
			initLightSensor();
			break;

		default:
			break;
		}
	}

	private void initLightSensor() {
		while (true) {
			try {
				System.out.println("point timeInterval radiometry, luminous minRadiometry minLuminous maxRadiometry maxLuminous");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				float radiometry = Float.parseFloat(arrSplit[1]);
				float luminous = Float.parseFloat(arrSplit[2]);
				int timeInterval = Integer.parseInt(arrSplit[3]);
				float minRadiometry = Float.parseFloat(arrSplit[4]);
				float minLuminous = Float.parseFloat(arrSplit[5]);
				float maxRadiometry = Float.parseFloat(arrSplit[6]);
				float maxLuminous = Float.parseFloat(arrSplit[7]);
				LigthSensorMeasurementsDto m = new LigthSensorMeasurementsDto(radiometry,luminous,
						minRadiometry,minLuminous,maxRadiometry,maxLuminous);
				LightSensorDTO l = new LightSensorDTO(timeInterval,m);
				this.environment.initSensor(point, l);/**/
				return;
			} catch (Exception e) {
				System.out.println("please enter correct value" );
				e.printStackTrace();
			}
		}
		
	}

	private void initTempSensor() {
		while (true) {
			try {
				System.out.println("Loc value timeInterval min max");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				float value = Float.parseFloat(arrSplit[1]);
				int timeInterval = Integer.parseInt(arrSplit[2]);
				float min = Float.parseFloat(arrSplit[3]);
				float max = Float.parseFloat(arrSplit[4]);
				TempSensorDTO temp = new TempSensorDTO(value,timeInterval,min,max);
				this.environment.initSensor(point,temp);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("please enter correct value");
			}
		}
	}

	private void addLocationHandler() {
		// System.out.println("please enter location with int value");
		int point = getIntValue("please enter location with int value");
		this.environment.addLocation(point);
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

}
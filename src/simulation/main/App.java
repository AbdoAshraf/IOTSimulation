package simulation.main;

import java.util.Scanner;

import simulation.envrionment.Environment;
import simulation.envrionment.dto.HumiditySensorDTO;
import simulation.envrionment.dto.HumiditySensorMeasurementsDTO;
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
		System.out.println("\n==> Environmet init");
		while (true) {
			System.out.println("L for Add loc\n" + "S for add sensor\n" + "R for change range\n"
					+ "A for activate deactivate\n" + "E for exit configuration\n" + "T for change time interval"
					+ "\nM for change message fomrmat" + "\nI for sensor info");
			String S = sc.nextLine();
			if (S.equals("L"))
				addLocationHandler();
			else if (S.equals("S"))
				sensorConfigHandler();
			else if (S.equals("R"))
				sensorConfigHandler();
			else if (S.equals("A"))
				activationHandler();
			else if (S.equals("T"))
				changePeriodHandler();
			else if (S.equals("T"))
				sensorRangeHandler();
			else if (S.equals("M"))
				changemessageFormat();
			else if (S.equals("I"))
				sensorInfo();
			else if (S.equals("E"))
				break;
		}
	}

	private void sensorInfo() {
		while (true) {
			try {
				System.out.println("point sensor");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				String sensorType = arrSplit[1];
				switch (sensorType) {
				case "temp":
					this.environment.getTempSnsorInfo(point);
					break;
				case "light":
					this.environment.getLightSnsorInfo(point);
					break;
				case "hum":
					this.environment.getHumiditySnsorInfo(point);
				default:
					break;
				}
				return;
			} catch (Exception e) {
				System.out.println("point sensor");
				// e.printStackTrace();
			}
		}
	}

	private void changemessageFormat() {
		while (true) {
			try {
				System.out.println("point sensor (email sms)");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				String sensorType = arrSplit[1];
				String messageFormat = arrSplit[2];
				switch (sensorType) {
				case "temp":
					this.environment.changeTempSensorMessage(point, messageFormat);
					break;
				case "light":
					this.environment.changelightSensorMessage(point, messageFormat);
					break;
				case "hum":
					this.environment.changeHumiditySensorMessage(point, messageFormat);
				default:
					break;
				}
				return;
			} catch (Exception e) {
				System.out.println("please enter correct value");
				// e.printStackTrace();
			}
		}
	}

	private void changePeriodHandler() {
		while (true) {
			try {
				// System.out.println("point sensor time");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				String sensorType = arrSplit[1];
				int timeInterval = Integer.parseInt(arrSplit[2]);
				switch (sensorType) {
				case "temp":
					this.environment.changeTempSensorPeriod(point, timeInterval);
					break;
				case "light":
					this.environment.changeLightSensorPeriod(point, timeInterval);
					break;
				case "hum":
					this.environment.changeHumiditySensorPeriod(point, timeInterval);
				default:
					break;
				}
				return;
			} catch (Exception e) {
				System.out.println("point sensor time");
				// e.printStackTrace();
			}
		}
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
					activateSensor(point, SensorType);
					return;
				} catch (Exception e) {
					System.out.println("please enter correct value");
				}
			}
		} else if (m.equals("d")) {
			while (true) {
				try {
					// System.out.println();
					String S = sc.nextLine();
					String[] arrSplit = S.split(" ");
					int point = Integer.parseInt(arrSplit[0]);
					String SensorType = arrSplit[1];
					detivateSensor(point, SensorType);
					return;
				} catch (Exception e) {
					System.out.println("loc sensor");
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
		case "hum":
			this.environment.deactivateHumiditySensor(point);
			// this.environment.activateLigrtSensor(point);
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
		case "hum":
			this.environment.activateHumiditySensor(point);
		default:
			break;
		}

	}

	private void sensorConfigHandler() {
		String sensorType = this.getStringValue("enter Sensor type (temp light hum)");
		switch (sensorType) {
		case "temp":
			initTempSensor();
			break;
		case "light":
			initLightSensor();
			break;
		case "hum":
			initHumiditySensor();
			break;

		default:
			break;
		}
	}

	private void sensorRangeHandler() {
		String sensorType = this.getStringValue("enter Sensor type (temp light hum)");
		switch (sensorType) {
		case "temp":
			changeRangeTempSensor();
			break;
		case "light":
			changeRangeLightSensor();
			break;
		case "hum":
			changeRangeHumiditySensor();
			break;

		default:
			break;
		}
	}

	private void changeRangeLightSensor() {
		while (true) {
			try {
				System.out.println("point luminous minRadiometry minLuminous maxRadiometry maxLuminous");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				float minRadiometry = Float.parseFloat(arrSplit[1]);
				float minLuminous = Float.parseFloat(arrSplit[2]);
				float maxRadiometry = Float.parseFloat(arrSplit[3]);
				float maxLuminous = Float.parseFloat(arrSplit[4]);
				this.environment.changeLightSensorRang(point, minRadiometry, minLuminous, maxRadiometry, maxLuminous);
				return;
			} catch (Exception e) {
				System.out.println("please enter correct value");
				// e.printStackTrace();
			}
		}
	}

	private void changeRangeHumiditySensor() {
		while (true) {
			try {
				System.out.println("point minAbsHumidity inRelHumidity maxAbsHumidity maxRelHumidity");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				float minAbsHumidity = Float.parseFloat(arrSplit[1]);
				float minRelHumidity = Float.parseFloat(arrSplit[2]);
				float maxAbsHumidity = Float.parseFloat(arrSplit[3]);
				float maxRelHumidity = Float.parseFloat(arrSplit[4]);
				this.environment.changeHumiditytSensorRang(point, minAbsHumidity, minRelHumidity, maxAbsHumidity,
						maxRelHumidity);
				return;
			} catch (Exception e) {
				System.out.println("please enter correct value");
				// e.printStackTrace();
			}
		}
	}

	private void changeRangeTempSensor() {
		while (true) {
			try {
				// System.out.println();
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				float min = Float.parseFloat(arrSplit[1]);
				float max = Float.parseFloat(arrSplit[2]);
				this.environment.changetempSensorRang(point, min, max);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Loc value timeInterval min max");
			}
		}
	}

	private void initHumiditySensor() {
		while (true) {
			try {
				// System.out.println(
				// );
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				float absHumidity = Float.parseFloat(arrSplit[1]);
				float relHumidity = Float.parseFloat(arrSplit[2]);
				int timeInterval = Integer.parseInt(arrSplit[3]);
				float minAbsHumidity = Float.parseFloat(arrSplit[4]);
				float minRelHumidity = Float.parseFloat(arrSplit[5]);
				float maxAbsHumidity = Float.parseFloat(arrSplit[6]);
				float maxRelHumidity = Float.parseFloat(arrSplit[7]);
				HumiditySensorMeasurementsDTO m = new HumiditySensorMeasurementsDTO(absHumidity, relHumidity,
						minAbsHumidity, minRelHumidity, maxAbsHumidity, maxRelHumidity);
				HumiditySensorDTO humiditySensorDTO = new HumiditySensorDTO(timeInterval, m);
				// LightSensorDTO l = new LightSensorDTO(timeInterval,m);
				this.environment.initSensor(point, humiditySensorDTO);/**/
				return;
			} catch (Exception e) {
				System.out.println(
						"point absHumidity relHumidity time minAbsHumidity minRelHumidity  maxAbsHumidity maxRelHumidity");
				// e.printStackTrace();
			}
		}

	}

	private void initLightSensor() {
		while (true) {
			try {
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
				LigthSensorMeasurementsDto m = new LigthSensorMeasurementsDto(radiometry, luminous, minRadiometry,
						minLuminous, maxRadiometry, maxLuminous);
				LightSensorDTO l = new LightSensorDTO(timeInterval, m);
				this.environment.initSensor(point, l);/**/
				return;
			} catch (Exception e) {
				System.out.println(
						"point timeInterval radiometry, luminous minRadiometry minLuminous maxRadiometry maxLuminous");
				// e.printStackTrace();
			}
		}

	}

	private void initTempSensor() {
		while (true) {
			try {
				// System.out.println();
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				float value = Float.parseFloat(arrSplit[1]);
				int timeInterval = Integer.parseInt(arrSplit[2]);
				float min = Float.parseFloat(arrSplit[3]);
				float max = Float.parseFloat(arrSplit[4]);
				TempSensorDTO temp = new TempSensorDTO(value, timeInterval, min, max);
				this.environment.initSensor(point, temp);
				return;
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("Loc value timeInterval min max");
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
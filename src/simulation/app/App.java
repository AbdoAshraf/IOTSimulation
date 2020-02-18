package simulation.app;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import simulation.environment.Environment;
import simulation.sensor_control.LocationControl;
import simulation.sensor_control.control.MessageType;
import simulation.sensor_control.sensors.HumiditySensor;
import simulation.sensor_control.sensors.HumiditySensorMeasurements;
import simulation.sensor_control.sensors.LightSensor;
import simulation.sensor_control.sensors.LigthSensorMeasurements;
import simulation.sensor_control.sensors.TempSensor;

public class App {
	private Environment environment;
	private LocationControl locationControl;
	Scanner sc;

	public App(Scanner sc) {
		this.environment = new Environment();
		this.locationControl = new LocationControl();
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
			else if (S.equals("A"))
				activationHandler();
			else if (S.equals("T"))
				changePeriodHandler();
			else if (S.equals("R"))
				sensorRangeHandler();
			else if (S.equals("M"))
				changemessageFormat();
			else if (S.equals("C"))
				changeEnvironmentVaribles();
			else if (S.equals("E"))
				break;
		}
	}

	private void changeEnvironmentVaribles() {
		//System.out.println();
		//String state = this.getStringValue("please enter the state");
		while (true) {
			try {
				System.out.println("point state");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				String state = arrSplit[1];
				this.environment.setState(point, state);
				return;
			} catch (Exception e) {
				System.out.println("please enter correct value");
				e.printStackTrace();
			}
		}
	}

	private void sensorInfo() {
		/*
		 * while (true) { try { System.out.println("point sensor"); String S =
		 * sc.nextLine(); String[] arrSplit = S.split(" "); int point =
		 * Integer.parseInt(arrSplit[0]); String sensorType = arrSplit[1]; switch
		 * (sensorType) { case "temp": this.environment.getTempSnsorInfo(point); break;
		 * case "light": this.environment.getLightSnsorInfo(point); break; case "hum":
		 * this.environment.getHumiditySnsorInfo(point); default: break; } return; }
		 * catch (Exception e) { System.out.println("point sensor"); //
		 * e.printStackTrace(); } }/
		 **/
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
					this.locationControl.changeSensorPeriod(point, new TempSensor(), timeInterval);
					break;
				case "light":
					this.locationControl.changeSensorPeriod(point, new LightSensor(), timeInterval);
					break;
				case "hum":
					this.locationControl.changeSensorPeriod(point, new HumiditySensor(), timeInterval);
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
			this.locationControl.activateSensor(point, new TempSensor());
			break;
		case "light":
			this.locationControl.activateSensor(point, new LightSensor());
			break;
		case "hum":
			this.locationControl.activateSensor(point, new HumiditySensor());
			// this.environment.activateLigrtSensor(point);
		default:
			break;
		}
	}

	private void activateSensor(int point, String sensorType) {
		switch (sensorType) {
		case "temp":
			this.locationControl.deactivateSensor(point, new TempSensor());
			break;
		case "light":
			this.locationControl.deactivateSensor(point, new LightSensor());
			break;
		case "hum":
			this.locationControl.deactivateSensor(point, new HumiditySensor());
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

	private void initHumiditySensor() {
		while (true) {
			try {
				// System.out.println(
				// );
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				int timeInterval = Integer.parseInt(arrSplit[1]);
				float minAbsHumidity = Float.parseFloat(arrSplit[2]);
				float minRelHumidity = Float.parseFloat(arrSplit[3]);
				float maxAbsHumidity = Float.parseFloat(arrSplit[4]);
				float maxRelHumidity = Float.parseFloat(arrSplit[5]);
				HumiditySensorMeasurements m = new HumiditySensorMeasurements(minAbsHumidity,
						minRelHumidity, maxAbsHumidity, maxRelHumidity);
				HumiditySensor humiditySensor = new HumiditySensor(timeInterval, m);
				// LightSensorDTO l = new LightSensorDTO(timeInterval,m);
				this.locationControl.initSensor(point, humiditySensor);/**/
				return;
			} catch (Exception e) {
				System.out.println("point time minAbsHumidity minRelHumidity  maxAbsHumidity maxRelHumidity");
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
				int timeInterval = Integer.parseInt(arrSplit[1]);
				float minRadiometry = Float.parseFloat(arrSplit[2]);
				float minLuminous = Float.parseFloat(arrSplit[3]);
				float maxRadiometry = Float.parseFloat(arrSplit[4]);
				float maxLuminous = Float.parseFloat(arrSplit[5]);
				LigthSensorMeasurements m = new LigthSensorMeasurements(minRadiometry, minLuminous, maxRadiometry,
						maxLuminous);
				LightSensor l = new LightSensor(timeInterval, m);
				this.locationControl.initSensor(point, l);/**/
				return;
			} catch (Exception e) {
				System.out.println("point timeInterval minRadiometry minLuminous maxRadiometry maxLuminous");
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
				int timeInterval = Integer.parseInt(arrSplit[1]);
				float min = Float.parseFloat(arrSplit[2]);
				float max = Float.parseFloat(arrSplit[3]);
				TempSensor temp = new TempSensor(timeInterval, min, max);
				this.locationControl.initSensor(point, temp);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Loc timeInterval min max");
			}
		}
	}

	private void addLocationHandler() {
		while (true) {
			try {
				// System.out.println();
				System.out.println("Loc fileName state");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				String fileName = arrSplit[1];
				String state = arrSplit[2];
				ConcurrentHashMap<String, Float> varibles = new ConcurrentHashMap<String, Float>();
				this.environment.addLocation(point, varibles);
				this.environment.setFileName(point, fileName);
				this.environment.setState(point, state);
				this.locationControl.addLocation(point, varibles);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Loc fileName status");
			}
		}
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

	private void changemessageFormat() {
		while (true) {
			try {
				System.out.println("point sensor (email sms)");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				String messageFormat = arrSplit[1];
				switch (messageFormat) {
				case "email":
					this.locationControl.changeMessaType(point, MessageType.EMAIL);
					break;
				case "sms":
					this.locationControl.changeMessaType(point, MessageType.SMS);
					break;
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
				System.out.println("point minRadiometry minLuminous maxRadiometry maxLuminous");
				String S = sc.nextLine();
				String[] arrSplit = S.split(" ");
				int point = Integer.parseInt(arrSplit[0]);
				float minRadiometry = Float.parseFloat(arrSplit[1]);
				float minLuminous = Float.parseFloat(arrSplit[2]);
				float maxRadiometry = Float.parseFloat(arrSplit[3]);
				float maxLuminous = Float.parseFloat(arrSplit[4]);
				LigthSensorMeasurements m = new LigthSensorMeasurements(minRadiometry, minLuminous, maxRadiometry,
						maxLuminous);
				LightSensor l = new LightSensor( m);	
				this.locationControl.changeSensroRange(point, l);
				return;
			} catch (Exception e) {
				System.out.println("please enter correct value");
				e.printStackTrace();
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
				HumiditySensorMeasurements m = new HumiditySensorMeasurements(minAbsHumidity,
						minRelHumidity, maxAbsHumidity, maxRelHumidity);
				HumiditySensor humiditySensor = new HumiditySensor(m);
				// LightSensorDTO l = new LightSensorDTO(timeInterval,m);
				this.locationControl.changeSensroRange(point, humiditySensor);

				this.locationControl.initSensor(point, humiditySensor);/**/
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
				TempSensor temp = new TempSensor(min, max);
				this.locationControl.changeSensroRange(point, temp);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Loc value timeInterval min max");
			}
		}
	}

	/*
	 * private void changemessageFormat() { while (true) { try {
	 * System.out.println("point sensor (email sms)"); String S = sc.nextLine();
	 * String[] arrSplit = S.split(" "); int point = Integer.parseInt(arrSplit[0]);
	 * String sensorType = arrSplit[1]; String messageFormat = arrSplit[2]; switch
	 * (sensorType) { case "temp": this.environment.changeTempSensorMessage(point,
	 * messageFormat); break; case "light":
	 * this.environment.changelightSensorMessage(point, messageFormat); break; case
	 * "hum": this.environment.changeHumiditySensorMessage(point, messageFormat);
	 * default: break; } return; } catch (Exception e) {
	 * System.out.println("please enter correct value"); // e.printStackTrace(); } }
	 * }/
	 **/

	/*
	 * private void changeRangeLightSensor() { while (true) { try { System.out.
	 * println("point luminous minRadiometry minLuminous maxRadiometry maxLuminous"
	 * ); String S = sc.nextLine(); String[] arrSplit = S.split(" "); int point =
	 * Integer.parseInt(arrSplit[0]); float minRadiometry =
	 * Float.parseFloat(arrSplit[1]); float minLuminous =
	 * Float.parseFloat(arrSplit[2]); float maxRadiometry =
	 * Float.parseFloat(arrSplit[3]); float maxLuminous =
	 * Float.parseFloat(arrSplit[4]); this.environment.changeLightSensorRang(point,
	 * minRadiometry, minLuminous, maxRadiometry, maxLuminous); return; } catch
	 * (Exception e) { System.out.println("please enter correct value"); //
	 * e.printStackTrace(); } } }/
	 **/
	/*
	 * private void sensorRangeHandler() { String sensorType =
	 * this.getStringValue("enter Sensor type (temp light hum)"); switch
	 * (sensorType) { case "temp": changeRangeTempSensor(); break; case "light":
	 * changeRangeLightSensor(); break; case "hum": changeRangeHumiditySensor();
	 * break;
	 * 
	 * default: break; } } private void changeRangeHumiditySensor() { while (true) {
	 * try { System.out.
	 * println("point minAbsHumidity inRelHumidity maxAbsHumidity maxRelHumidity");
	 * String S = sc.nextLine(); String[] arrSplit = S.split(" "); int point =
	 * Integer.parseInt(arrSplit[0]); float minAbsHumidity =
	 * Float.parseFloat(arrSplit[1]); float minRelHumidity =
	 * Float.parseFloat(arrSplit[2]); float maxAbsHumidity =
	 * Float.parseFloat(arrSplit[3]); float maxRelHumidity =
	 * Float.parseFloat(arrSplit[4]);
	 * this.environment.changeHumiditytSensorRang(point, minAbsHumidity,
	 * minRelHumidity, maxAbsHumidity, maxRelHumidity); return; } catch (Exception
	 * e) { System.out.println("please enter correct value"); //
	 * e.printStackTrace(); } } }
	 * 
	 * private void changeRangeTempSensor() { while (true) { try { //
	 * System.out.println(); String S = sc.nextLine(); String[] arrSplit =
	 * S.split(" "); int point = Integer.parseInt(arrSplit[0]); float min =
	 * Float.parseFloat(arrSplit[1]); float max = Float.parseFloat(arrSplit[2]);
	 * this.environment.changetempSensorRang(point, min, max); return; } catch
	 * (Exception e) { e.printStackTrace();
	 * System.out.println("Loc value timeInterval min max"); } } }/
	 **/

}

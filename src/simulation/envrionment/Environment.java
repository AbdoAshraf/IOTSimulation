package simulation.envrionment;

import java.util.HashMap;
import java.util.Map;

import simulation.envrionment.dto.HumiditySensorDTO;
import simulation.envrionment.dto.LightSensorDTO;
import simulation.envrionment.dto.TempSensorDTO;
import simulation.envrionment.sensors.HumiditySensor;
import simulation.envrionment.sensors.HumiditySensorMeasurements;
import simulation.envrionment.sensors.LigthSensorMeasurements;
import simulation.envrionment.sensors.MessageType;
import simulation.envrionment.sensors.Sensor;
import simulation.envrionment.sensors.TempSensor;
import simulation.envrionment.sensors.LightSensor;

public class Environment {
	Map<Integer, Location> environment;

	public Environment() {
		environment = new HashMap<Integer, Location>();
	}

	public void addLocation(int point) {
		String locName = "loc" + String.valueOf(point);
		Location loc = new Location(locName);
		environment.putIfAbsent(point, loc);
	}

	public void initSensor(int point, TempSensorDTO tempSensor) {
		Location loc = environment.get(point);
		// TempSensor s = loc.getTempSensor();
		loc.initSensor(tempSensor);
		loc.activateTemptSensor();
	}

	public void initSensor(int point, LightSensorDTO lightSensorDTO) {
		Location loc = environment.get(point);
		// TempSensor s = loc.getTempSensor();
		loc.initSensor(lightSensorDTO);
	}

	public void initSensor(int point, HumiditySensorDTO humiditySensorDTO) {
		Location loc = environment.get(point);
		// TempSensor s = loc.getTempSensor();
		loc.initSensor(humiditySensorDTO);
	}

	public void activateLigrtSensor(int point) {
		Location loc = environment.get(point);
		loc.activateLightSensor();
	}

	public void activateTempSensor(int point) {
		Location loc = environment.get(point);
		loc.activateTemptSensor();
	}

	public void activateHumiditySensor(int point) {
		Location loc = environment.get(point);
		loc.activateHumiditySensor();
	}

	public void deactivateLightSensor(int point) {
		Location loc = environment.get(point);
		loc.deactivateLightSensor();
	}

	public void deactivateTempSensor(int point) {
		Location loc = environment.get(point);
		loc.deactivateTempSensor();
	}

	public void deactivateHumiditySensor(int point) {
		Location loc = environment.get(point);
		loc.deactivateHumiditySensor();
	}

	public void changetempSensorRang(int point, float min, float max) {
		Location loc = environment.get(point);
		TempSensor s = loc.getTempSensor();
		s.setMin(min);
		s.setMax(max);
		// loc.adjustSensorRange(s);
	}

	public void changeLightSensorRang(int point, float minRadiometry, float minLuminous, float maxRadiometry,
			float maxLuminous) {
		Location loc = environment.get(point);
		LightSensor s = loc.getLightSensor();
		LigthSensorMeasurements m = s.getLigthSensorMeasurements();
		m.setLuminous(minLuminous);
		m.setMinRadiometry(minRadiometry);
		m.setMaxLuminous(maxLuminous);
		m.setMaxRadiometry(maxRadiometry);
	}

	public void changeHumiditytSensorRang(int point, float minAbsHumidity, float minRelHumidity, float maxAbsHumidity,
			float maxRelHumidity) {
		Location loc = environment.get(point);
		HumiditySensor s = loc.getHumiditySensor();
		HumiditySensorMeasurements m = s.getHumiditySensorMeasurements();
		m.setMinAbsHumidity(minAbsHumidity);
		m.setMinRelHumidity(minRelHumidity);
		m.setMaxAbsHumidity(maxAbsHumidity);
		m.setMaxRelHumidity(maxRelHumidity);
	}

	public void changeLightSensorPeriod(int point, int timeInterval) {
		Location loc = environment.get(point);
		LightSensor s = loc.getLightSensor();
		s.setTimeInterval(timeInterval);
	}

	public void changeTempSensorPeriod(int point, int timeInterval) {
		Location loc = environment.get(point);
		TempSensor s = loc.getTempSensor();
		s.setTimeInterval(timeInterval);
	}

	public void changeHumiditySensorPeriod(int point, int timeInterval) {
		Location loc = environment.get(point);
		HumiditySensor s = loc.getHumiditySensor();
		s.setTimeInterval(timeInterval);
	}

	public void changeTempSensorMessage(int point, String messageFormat) {
		Location loc = environment.get(point);
		TempSensor s = loc.getTempSensor();
		changeMeassageFormat(s, messageFormat);
	}

	private void changeMeassageFormat(Sensor s, String messageFormat) {
		switch (messageFormat) {
		case "sms":
			s.setMessageType(MessageType.SMS);
			break;
		case "email":
			s.setMessageType(MessageType.EMAIL);
			break;
		}
	}

	public void changelightSensorMessage(int point, String messageFormat) {
		Location loc = environment.get(point);
		LightSensor s = loc.getLightSensor();
		changeMeassageFormat(s, messageFormat);
	}

	public void changeHumiditySensorMessage(int point, String messageFormat) {
		Location loc = environment.get(point);
		HumiditySensor s = loc.getHumiditySensor();
		changeMeassageFormat(s, messageFormat);
	}
	
	public void getLightSnsorInfo(int point) {
		Location loc = environment.get(point);
		Sensor S = loc.getLightSensor();
		System.out.println(S.toString());
	}
	
	public void getTempSnsorInfo(int point) {
		Location loc = environment.get(point);
		Sensor S = loc.getTempSensor();
		System.out.println(S.toString());
	}
	
	public void getHumiditySnsorInfo(int point) {
		Location loc = environment.get(point);
		Sensor S = loc.getHumiditySensor();
		System.out.println(S.toString());
	}
	
	

}

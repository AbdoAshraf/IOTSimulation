package simulation.envrionment;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import simulation.envrionment.dto.LightSensorDTO;
import simulation.envrionment.dto.TempSensorDTO;
import simulation.envrionment.sensors.LightSensor;
import simulation.envrionment.sensors.LigthSensorMeasurements;
import simulation.envrionment.sensors.Location;
import simulation.envrionment.sensors.Sensor;
import simulation.envrionment.sensors.TempSensor;

public class Environment {
	Map<Integer, Location> environment;

	public Environment() {
		environment = new HashMap<Integer, Location>();
	}

	public void addLocation(int point) {
		Location loc = new Location();
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

	public void activateLigrtSensor(int point) {
		Location loc = environment.get(point);
		loc.activateLightSensor();
	}

	public void activateTempSensor(int point) {
		Location loc = environment.get(point);
		loc.activateTemptSensor();
	}

	public void deactivateLightSensor(int point) {
		Location loc = environment.get(point);
		loc.deactivateLightSensor();
	}

	public void deactivateTempSensor(int point) {
		Location loc = environment.get(point);
		loc.deactivateTempSensor();
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

}

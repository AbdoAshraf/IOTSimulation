package simulation.sensor_control;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import simulation.sensor_control.control.MessageType;
import simulation.sensor_control.sensors.Sensor;

public class LocationControl {
	Map<Integer, Location> locationMap;
	ConcurrentHashMap<String, Float> varibles;

	public LocationControl() {
		locationMap = new HashMap<Integer, Location>();
		varibles = new ConcurrentHashMap<String, Float>();
	}

	public void addLocation(int point, ConcurrentHashMap<String, Float> varibles) {
		String locName = "loc" + String.valueOf(point);
		Location loc = new Location(locName, varibles);
		locationMap.putIfAbsent(point, loc);
	}

	public void initSensor(int point, Sensor S) {
		locationMap.get(point).initSensor(S);
	}

	public void activateSensor(int point, Sensor S) {
		locationMap.get(point).activateSensor(S);
	}

	public void deactivateSensor(int point, Sensor S) {
		locationMap.get(point).deactivateSensor(S);
	}

	public void changeSensorPeriod(int point, Sensor S, int timeInterval) {
		locationMap.get(point).changeperiod(S, timeInterval);
	}

	public void changeSensorMessage(int point, Sensor s, MessageType m) {
		// locationMap.get(point).changeMessageType(s, m);
	}

	public void changeMessaType(int point, MessageType m) {
		locationMap.get(point).changeMessageType(m);
	}
	
	public void changeSensroRange(int point , Sensor s) {
		locationMap.get(point).changeSensorRange(s);
	}

	/*
	 * public void initSensor(int point, TempSensorDTO tempSensor) { Location loc =
	 * locationMap.get(point); // TempSensor s = loc.getTempSensor();
	 * loc.initSensor(tempSensor); loc.activateTemptSensor(); }
	 * 
	 * public void initSensor(int point, LightSensorDTO lightSensorDTO) { Location
	 * loc = locationMap.get(point); // TempSensor s = loc.getTempSensor();
	 * loc.initSensor(lightSensorDTO); }
	 * 
	 * public void initSensor(int point, HumiditySensorDTO humiditySensorDTO) {
	 * Location loc = locationMap.get(point); // TempSensor s = loc.getTempSensor();
	 * loc.initSensor(humiditySensorDTO); }
	 * 
	 * public void activateLigrtSensor(int point) { Location loc =
	 * locationMap.get(point); loc.activateLightSensor(); }
	 * 
	 * public void activateTempSensor(int point) { Location loc =
	 * locationMap.get(point); loc.activateTemptSensor(); }
	 * 
	 * public void activateHumiditySensor(int point) { Location loc =
	 * locationMap.get(point); loc.activateHumiditySensor(); }
	 * 
	 * public void deactivateLightSensor(int point) { Location loc =
	 * locationMap.get(point); loc.deactivateLightSensor(); }
	 * 
	 * public void deactivateTempSensor(int point) { Location loc =
	 * locationMap.get(point); loc.deactivateTempSensor(); }
	 * 
	 * public void deactivateHumiditySensor(int point) { Location loc =
	 * locationMap.get(point); loc.deactivateHumiditySensor(); }
	 * 
	 * public void changetempSensorRang(int point, float min, float max) { Location
	 * loc = locationMap.get(point); TempSensor s = loc.getTempSensor();
	 * s.setMin(min); s.setMax(max); // loc.adjustSensorRange(s); }
	 * 
	 * public void changeLightSensorRang(int point, float minRadiometry, float
	 * minLuminous, float maxRadiometry, float maxLuminous) { Location loc =
	 * locationMap.get(point); LightSensor s = loc.getLightSensor();
	 * LigthSensorMeasurements m = s.getLigthSensorMeasurements();
	 * m.setLuminous(minLuminous); m.setMinRadiometry(minRadiometry);
	 * m.setMaxLuminous(maxLuminous); m.setMaxRadiometry(maxRadiometry); }
	 * 
	 * public void changeHumiditytSensorRang(int point, float minAbsHumidity, float
	 * minRelHumidity, float maxAbsHumidity, float maxRelHumidity) { Location loc =
	 * locationMap.get(point); HumiditySensor s = loc.getHumiditySensor();
	 * HumiditySensorMeasurements m = s.getHumiditySensorMeasurements();
	 * m.setMinAbsHumidity(minAbsHumidity); m.setMinRelHumidity(minRelHumidity);
	 * m.setMaxAbsHumidity(maxAbsHumidity); m.setMaxRelHumidity(maxRelHumidity); }
	 * 
	 * public void changeLightSensorPeriod(int point, int timeInterval) { Location
	 * loc = locationMap.get(point); LightSensor s = loc.getLightSensor();
	 * s.setTimeInterval(timeInterval); }
	 * 
	 * public void changeTempSensorPeriod(int point, int timeInterval) { Location
	 * loc = locationMap.get(point); TempSensor s = loc.getTempSensor();
	 * s.setTimeInterval(timeInterval); }
	 * 
	 * public void changeHumiditySensorPeriod(int point, int timeInterval) {
	 * Location loc = locationMap.get(point); HumiditySensor s =
	 * loc.getHumiditySensor(); s.setTimeInterval(timeInterval); }
	 * 
	 * public void changeTempSensorMessage(int point, String messageFormat) {
	 * Location loc = locationMap.get(point); TempSensor s = loc.getTempSensor();
	 * changeMeassageFormat(s, messageFormat); }
	 * 
	 * private void changeMeassageFormat(Sensor s, String messageFormat) { switch
	 * (messageFormat) { case "sms": s.setMessageType(MessageType.SMS); break; case
	 * "email": s.setMessageType(MessageType.EMAIL); break; } }
	 * 
	 * public void changelightSensorMessage(int point, String messageFormat) {
	 * Location loc = locationMap.get(point); LightSensor s = loc.getLightSensor();
	 * changeMeassageFormat(s, messageFormat); }
	 * 
	 * public void changeHumiditySensorMessage(int point, String messageFormat) {
	 * Location loc = locationMap.get(point); HumiditySensor s =
	 * loc.getHumiditySensor(); changeMeassageFormat(s, messageFormat); }
	 * 
	 * public void getLightSnsorInfo(int point) { Location loc =
	 * locationMap.get(point); Sensor S = loc.getLightSensor();
	 * System.out.println(S.toString()); }
	 * 
	 * public void getTempSnsorInfo(int point) { Location loc =
	 * locationMap.get(point); Sensor S = loc.getTempSensor();
	 * System.out.println(S.toString()); }
	 * 
	 * public void getHumiditySnsorInfo(int point) { Location loc =
	 * locationMap.get(point); Sensor S = loc.getHumiditySensor();
	 * System.out.println(S.toString()); }/
	 **/

}

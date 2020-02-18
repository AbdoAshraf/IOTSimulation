package simulation.environment.location;

import java.util.concurrent.ConcurrentHashMap;

import simulation.sensor_control.sensors.SensorResults;

public class LocationView {
	ConcurrentHashMap<String, Float> varibles;
	public LocationView(ConcurrentHashMap<String, Float> varibles) {
		this.varibles = varibles;
	}
	public void updateView(float absHumidity, float relHumidity, float luminous, float radiometry, float temp) {
		varibles.put("absHumidity", absHumidity);
		varibles.put("relHumidity", relHumidity);
		varibles.put("radiometry", radiometry);
		varibles.put("luminous", luminous);
		varibles.put("temp", temp);
		//System.out.println(absHumidity + " "+ temp);
	}
	
}

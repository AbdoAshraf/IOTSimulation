package simulation.envrionment;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import simulation.envrionment.sensors.LightSensor;
import simulation.envrionment.sensors.Sensor;

public class Environment {
	Map<Integer, HashMap<String, Sensor>> environment;

	public Environment() {
		environment = new HashMap<Integer, HashMap<String, Sensor>>();
	}

	public void addSensorsToLocation(int point) {
		Sensor light = new LightSensor("Light", 0, 0, 0);
		Sensor temp = new LightSensor("Temp", 0, 0, 0);
		Sensor Humidity = new LightSensor("Humidity", 0, 0, 0);
		HashMap<String, Sensor> sensorsMap = new HashMap<String, Sensor>();
		sensorsMap.putIfAbsent("Light", light);
		sensorsMap.putIfAbsent("Temp", temp);
		sensorsMap.putIfAbsent("Humidity", Humidity);
		environment.putIfAbsent(point, sensorsMap);
	}

	public void deactivateSensor(int point, String type, boolean value) {
		HashMap<String, Sensor> sensorsMap = environment.get(point);
		if (!sensorsMap.containsKey(type)) {
			System.out.println("invalid input");
			return;
		}
		Sensor s = sensorsMap.get(type);
		s.setActive(value);
	}

	public void removesensorsfromLocation(int point) {
		for (Entry<Integer, HashMap<String, Sensor>> entry : environment.entrySet()) {
			for (Entry<String, Sensor> subentry : entry.getValue().entrySet()) {
				subentry.getValue().setActive(false);
			}
		}
	}

	public void setSensorsValue(int point, String sensortype, float val) {
		addSensorsToLocation(point);
		HashMap<String, Sensor> sensorsMap = environment.get(point);
		Sensor s = sensorsMap.get(sensortype);
		s.setValue(val);
	}

	public float getSensorsValue(int point, String sensortype) {
		HashMap<String, Sensor> sensorsMap = environment.get(point);
		Sensor s = sensorsMap.get(sensortype);
		return s.getValue();
	}

	public void setSensorsRange(int point, String sensortype, float from, float to) {
		HashMap<String, Sensor> sensorsMap = environment.get(point);
		Sensor s = sensorsMap.get(sensortype);
		s.changeRange(from, to);
	}

	private String sensorFusion(int point) {
		StringBuilder message = new StringBuilder("normal");
		HashMap<String, Sensor> sensorsMap = environment.get(point);
		Sensor Light = sensorsMap.get("Light");
		Sensor temp = sensorsMap.get("Temp");
		Sensor Humidity = sensorsMap.get("Humidity");

		if (Light.getMax() < Light.getValue() && temp.getMax() > temp.getValue()) {
			message = new StringBuilder("abnormal temp");
			temp.sendMessage("abnormal temp");
		}
		if (Humidity.getMax() < Humidity.getValue() && temp.getMax() > temp.getValue()) {
			message.append("\nabnormal humidity");
			Humidity.sendMessage("\nabnormal humidity");
		}

		if (Light.getMax() < Light.getValue()) {
			message.append("\nabnormal Light");
			Light.sendMessage("\nabnormal Light");

		}

		if (Light.getMin() < Light.getValue() || temp.getMin() < temp.getValue()
				|| Humidity.getMin() < Humidity.getValue()) {
			message.append("\nabnormal case");
			Light.sendMessage("abnormal case");
		}
		return message.toString();
	}

	public void ReadValue() {
		for (Entry<Integer, HashMap<String, Sensor>> entry : environment.entrySet()) {
			int point = entry.getKey();
			HashMap<String, Sensor> sensorsMap = environment.get(point);
			Sensor Light = sensorsMap.get("Light");
			Sensor temp = sensorsMap.get("Temp");
			Sensor Humidity = sensorsMap.get("Humidity");
			if (Light.isActive())
				Light.simulateAction();
			if (temp.isActive())
				temp.simulateAction();
			if (Humidity.isActive())
				Humidity.simulateAction();
			// System.out.println("point: "+point+" "+(sensorFusion(point))+);
			sensorFusion(point);
		}
	}

	public void pritn() {
		for (Entry<Integer, HashMap<String, Sensor>> entry : environment.entrySet()) {
			System.out.println(entry.getKey());
			for (Entry<String, Sensor> subentry : entry.getValue().entrySet()) {
				System.out.println(subentry.getKey() + subentry.getValue());
			}
		}

	}

}

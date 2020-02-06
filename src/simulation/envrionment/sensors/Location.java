package simulation.envrionment.sensors;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
	private LightSensor lightSensor;
	private HumiditySensor humiditySensor;
	private TempSensor tempSensor;

	ConcurrentHashMap<String, SensorResults> map;

	public Location() {
		super();
		map = new ConcurrentHashMap<>();
		map.put("Ligth", SensorResults.Inactive);
		map.put("Temp", SensorResults.Inactive);
		map.put("Humidity", SensorResults.Inactive);
	}

	public void activateLightSensor(int timeInterval, float radiometry, float luminous, float minRadiometry,
			float minLuminous, float maxRadiometry, float maxLuminous) {
		if (this.lightSensor == null||this.lightSensor.getState() == Thread.State.TERMINATED) {
			// this.lightSensor = new LightSensor(value,map,timeInterval,min,max);
			LigthSensorMeasurements m = new LigthSensorMeasurements(radiometry, luminous, minRadiometry, minLuminous,
					maxRadiometry, maxLuminous);

			this.lightSensor = new LightSensor(timeInterval, map, m);
			this.lightSensor.start();
			return;
		}
		System.out.println("Already Activated");
	}

	public void activateHumiditySensor(float value, int timeInterval, int min, int max) {
		if (this.humiditySensor.getState() == Thread.State.TERMINATED || this.humiditySensor == null) {
			// this.humiditySensor = new HumiditySensor(timeInterval, map, value, min, max);
			return;
		}
		System.out.println("Already Activated");
	}

	public void activateTempSensor(float value, int timeInterval, int min, int max) {
		if (this.tempSensor == null || this.tempSensor.getState() == Thread.State.TERMINATED) {
			this.tempSensor = new TempSensor(timeInterval, map, value, min, max);
			tempSensor.start();
			// (int timeInterval, ConcurrentHashMap<String, SensorResults> map, float value,
			// int min, int max)
			return;
		}
		System.out.println("Already Activated");
	}

	public void adjustSensor(Sensor S, int i) {
		S.setTimeInterval(i);
	}

	public void adjustLightSensor(float radiometry, float luminous) {
		this.lightSensor.adjust(radiometry, luminous);
	}

	public void adjustSensorRange(LightSensor s) {
		this.lightSensor.adjustRange(s);
	}

	public void adjustSensorRange(TempSensor s) {
		this.tempSensor.adjustRange(s);
		
	}

	public void changeTimeInterval(LightSensor s, int time) {
		this.lightSensor.setTimeInterval(time);
	}

	public void changeTimeInterval(TempSensor s, int time) {
		this.tempSensor.setTimeInterval(time);
	}

	public void changemessageFormat(LightSensor s) {
		this.lightSensor.setMessageFormat(s.getMessageFormat());
	}
	public void changemessageFormat(TempSensor s) {
		this.tempSensor.setMessageFormat(s.getMessageFormat());
	}

}

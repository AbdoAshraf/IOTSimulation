package simulation.sensor_control.sensors;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumiditySensor extends Sensor {
	private HumiditySensorMeasurements humiditySensorMeasurements;

	public HumiditySensor() {
		super();
	}



	public HumiditySensor(int timeInterval, ConcurrentHashMap<String, SensorResults> map,
			HumiditySensorMeasurements humiditySensorResults) {
		super(timeInterval, map);
		this.humiditySensorMeasurements = humiditySensorResults;
	}

	public HumiditySensor(int timeInterval, HumiditySensorMeasurements m) {
		this.humiditySensorMeasurements = m;
		this.timeInterval=timeInterval;

	}



	public HumiditySensor(HumiditySensorMeasurements m) {
		this.humiditySensorMeasurements = m ;
	}



	@Override
	void performReading() {
		// SensorResults light = this.humiditySensorResults.measurements();
	//	SensorResults temp = map.get("Temp");
		this.humiditySensorMeasurements.setAbsHumidity(this.varibles.get("absHumidity"));
		this.humiditySensorMeasurements.setRelHumidity(this.varibles.get("relHumidity"));
		SensorResults hum = this.humiditySensorMeasurements.measurements();
	
	/*if (hum == SensorResults.HIGH && temp == SensorResults.HIGH) {
			// System.out.println("abnormal temp");
			this.creatMessage("abnormal hight Humidity");
		}
		if (hum == SensorResults.LOW) {
			// System.out.println("abnormal Light");
			this.creatMessage("abnormal low Humidity");
		}/**/
		map.put("Light", hum);
		this.observer.update();
	}
	public String toString() {
		StringBuilder S = new StringBuilder();
		S.append(this.humiditySensorMeasurements.toString());
		S.append("\nperiod=" + this.timeInterval);
		return S.toString();
	}
}
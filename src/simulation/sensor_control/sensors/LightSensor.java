package simulation.sensor_control.sensors;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightSensor extends Sensor {
	private LigthSensorMeasurements ligthSensorMeasurements;

	public LightSensor() {
		super();
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.performReading();
				Thread.sleep(timeInterval * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public LightSensor(int timeInterval, ConcurrentHashMap<String, SensorResults> map,
			LigthSensorMeasurements ligthSensorMeasurements) {
		super(timeInterval, map);
		this.ligthSensorMeasurements = ligthSensorMeasurements;
	}

	public LightSensor(int timeInterval, LigthSensorMeasurements m) {
		//super(timeInterval);
		this.ligthSensorMeasurements = m;
		this.timeInterval=timeInterval;
	}

	public LightSensor(LigthSensorMeasurements m) {
		this.ligthSensorMeasurements = m;
	}

	@Override
	void performReading() {
		this.ligthSensorMeasurements.setLuminous(this.varibles.get("luminous"));
		this.ligthSensorMeasurements.setMaxRadiometry(this.varibles.get("radiometry"));
		SensorResults light = this.ligthSensorMeasurements.measurements();
		//System.out.println(this.ligthSensorMeasurements.toString());
	/*	if (light == SensorResults.HIGH) {
			// System.out.println("abnormal temp");
			this.creatMessage("abnormal hight Light");
		}
		if (light == SensorResults.HIGH) {
			// System.out.println("abnormal Light");
			this.creatMessage("abnormal low Light");

		}/**/
		
		map.put("Light", light);
		this.observer.update();
	}
	public String toString() {
		StringBuilder S = new StringBuilder();
		S.append(this.ligthSensorMeasurements.toString());
		S.append("\nperiod=" + this.timeInterval);
		return S.toString();
	}
	
}
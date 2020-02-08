package simulation.envrionment.sensors;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;
import simulation.envrionment.sensors.LigthSensorMeasurements;
import simulation.envrionment.sensors.Sensor;
import simulation.envrionment.sensors.SensorResults;

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

	@Override
	void performReading() {
		SensorResults light = this.ligthSensorMeasurements.measurements();
		if (light == SensorResults.HIGH) {
			// System.out.println("abnormal temp");
			this.creatMessage("abnormal hight Light");
		}
		if (light == SensorResults.HIGH) {
			// System.out.println("abnormal Light");
			this.creatMessage("abnormal low Light");

		}
		map.put("Light", light);
	}
}

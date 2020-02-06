package simulation.envrionment.sensors;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightSensor extends Sensor {
	private LigthSensorMeasurements ligthSensorMeasurements;

	@Override
	public void run() {
		while (true) {
			System.out.println("Ligt");
			SensorResults light = this.ligthSensorMeasurements.measurements();
			SensorResults temp = map.get("Temp");
			if (light == SensorResults.HIGH && temp == SensorResults.HIGH) {
				System.out.println("abnormal temp");
			}
			if (light == SensorResults.HIGH) {
				System.out.println("abnormal Light");
			}
			
			map.put("Light",light);
			try {
				Thread.sleep(timeInterval);
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

	public void adjust(float radiometry, float luminous) {
		this.ligthSensorMeasurements.setRadiometry(radiometry);
		this.ligthSensorMeasurements.setLuminous(luminous);
	}

}

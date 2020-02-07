package simulation.envrionment.sensors;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightSensor extends Sensor {
	public LightSensor() {
		super();
	}
	private LigthSensorMeasurements ligthSensorMeasurements;
    
	@Override
	public void run() {
		while (true) {
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

	public void adjustRange(LightSensor s) {
		this.ligthSensorMeasurements.setLuminous(s.ligthSensorMeasurements.getMaxLuminous());
		this.ligthSensorMeasurements.setMaxRadiometry(s.ligthSensorMeasurements.getMaxRadiometry());
	}

	@Override
	void performReading() {
		SensorResults light = this.ligthSensorMeasurements.measurements();
		SensorResults temp = map.get("Temp");
		if (light == SensorResults.HIGH && temp == SensorResults.HIGH) {
			//System.out.println("abnormal temp");
			this.creatMessage("abnormal Light");
		}
		if (light == SensorResults.HIGH) {
			//System.out.println("abnormal Light");
			this.creatMessage("abnormal Light");

		}
		map.put("Light",light);		
	}
}	

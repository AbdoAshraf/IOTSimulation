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
	public LightSensor() {
		super();
	}
	private LigthSensorMeasurements ligthSensorMeasurements;
    
	@Override
	public void run() {
		while (true) {
			try {
				this.performReading();
				Thread.sleep(timeInterval*1000);
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
		if (light == SensorResults.HIGH ) {
			//System.out.println("abnormal temp");
			this.creatMessage("abnormal hight Light");
		}
		if (light == SensorResults.HIGH) {
			//System.out.println("abnormal Light");
			this.creatMessage("abnormal low Light");

		}
		map.put("Light",light);		
	}
}	

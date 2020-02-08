package simulation.envrionment.sensors;

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
	@Override
	public void run() {
		while (true) {
			this.performReading();
			try {
				Thread.sleep(timeInterval*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public HumiditySensor(int timeInterval, ConcurrentHashMap<String, SensorResults> map,
			HumiditySensorMeasurements humiditySensorResults) {
		super(timeInterval, map);
		this.humiditySensorMeasurements = humiditySensorResults;
	}

	@Override
	void performReading() {
		//SensorResults light = this.humiditySensorResults.measurements();
		SensorResults temp = map.get("Temp");
		SensorResults hum = this.humiditySensorMeasurements.measurements();
		if (hum == SensorResults.HIGH && temp == SensorResults.HIGH) {
			//System.out.println("abnormal temp");
			this.creatMessage("abnormal hight Humidity");
		}
		if (hum == SensorResults.LOW) {
			//System.out.println("abnormal Light");
			this.creatMessage("abnormal low Humidity");
		}
		map.put("Light",hum);		
	}
}

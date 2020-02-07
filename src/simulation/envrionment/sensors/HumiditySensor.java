package simulation.envrionment.sensors;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumiditySensor extends Sensor {
	HumiditySensorResults humiditySensorResults;
	@Override
	public void run() {
		System.out.println("Hum");

	}

	public HumiditySensor(int timeInterval, ConcurrentHashMap<String, SensorResults> map,
			HumiditySensorResults humiditySensorResults) {
		super(timeInterval, map);
		this.humiditySensorResults = humiditySensorResults;
	}

	@Override
	void performReading() {
	}
}

package simulation.envrionment.sensors;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TempSensor extends Sensor {
	private float value;
	private float min;
	private float max;

	public TempSensor(int timeInterval, ConcurrentHashMap<String, SensorResults> map, float value, float min,
			float max) {
		super(timeInterval, map);
		this.value = value;
		this.min = min;
		this.max = max;
	}

	public TempSensor() {
		super();
	}

	private SensorResults TempSensorMeasurement() {
		// System.out.println(this.timeInterval);
		if (value > max) {
			// System.out.println("abnormal temp");
			return SensorResults.HIGH;
		}
		if (value < min) {
			return SensorResults.LOW;
		}

		return SensorResults.Normal;
	}

	public void adjust(int value) {
		this.value = value;
	}

	@Override
	public void run() {
		while (true) {
			this.performReading();
			try {
				Thread.sleep(timeInterval * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void adjustRange(TempSensor s) {
		this.max = s.max;
		this.min = s.min;
	}

	@Override
	void performReading() {
		SensorResults temp = TempSensorMeasurement();
		SensorResults light = map.get("Light");
		if (light == SensorResults.HIGH) {
			this.creatMessage("abnormal high temp");
		}
		if (temp == SensorResults.LOW) {
			this.creatMessage("abnormal low temp");
		}
		map.put("Temp", temp);
	}
	
	public String toString() {
		StringBuilder S = new StringBuilder();
		S.append("value=" + this.min);
		S.append("\nmax range=" + this.max);
		S.append("\nmin range=" + this.timeInterval);
		return S.toString();
	}
}

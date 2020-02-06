package simulation.envrionment.sensors;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempSensor extends Sensor {
	private float value;
	private int min;
	private int max;
	
	public TempSensor(int timeInterval, ConcurrentHashMap<String, SensorResults> map, float value, int min, int max) {
		super(timeInterval, map);
		this.value = value;
		this.min = min;
		this.max = max;
	}
	
	private SensorResults TempSensorMeasurement() {
		if(value > max ) {
			return SensorResults.HIGH;
		}
		if(value < min ) {
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
			System.out.println("Temp");
			SensorResults temp = TempSensorMeasurement();
			SensorResults light = map.get("Light");
			if(light == SensorResults.HIGH && temp == SensorResults.HIGH) {
				System.out.println("abnormal temp");
			}
			if (temp == SensorResults.LOW)
				System.out.println("abnormal temp");
			
			map.put("Temp",temp);
            
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void adjustRange(TempSensor s) {
		this.max=s.max;
		this.min=s.min;
	}

}

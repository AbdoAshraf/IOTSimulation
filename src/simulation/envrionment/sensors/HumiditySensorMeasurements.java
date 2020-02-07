package simulation.envrionment.sensors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumiditySensorMeasurements {
	float absHumidity;
	float relHumidity;
	float minAbsHumidity;
	float minRelHumidity;
	float maxAbsHumidity;
	float maxRelHumidity;
	
	public HumiditySensorMeasurements(float absHumidity, float relHumidity, float minAbsHumidity, 
			float minRelHumidity,
			float maxAbsHumidity, float maxRelHumidity) {
		super();
		this.absHumidity = absHumidity;
		this.relHumidity = relHumidity;
		this.minAbsHumidity = minAbsHumidity;
		this.minRelHumidity = minRelHumidity;
		this.maxAbsHumidity = maxAbsHumidity;
		this.maxRelHumidity = maxRelHumidity;
	}

	public SensorResults measurements() {
		if (this.absHumidity > this.maxAbsHumidity || this.relHumidity > this.maxAbsHumidity)
			return SensorResults.HIGH;
		if (this.absHumidity < this.minAbsHumidity || this.relHumidity < this.minRelHumidity)
			return SensorResults.LOW;
		return SensorResults.Normal;
	}
}




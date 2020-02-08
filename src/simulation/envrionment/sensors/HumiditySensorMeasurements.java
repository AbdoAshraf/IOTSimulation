package simulation.envrionment.sensors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HumiditySensorMeasurements {
	private float absHumidity;
	private float relHumidity;
	private float minAbsHumidity;
	private float minRelHumidity;
	private float maxAbsHumidity;
	private float maxRelHumidity;

	public HumiditySensorMeasurements(float absHumidity, float relHumidity, float minAbsHumidity, float minRelHumidity,
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

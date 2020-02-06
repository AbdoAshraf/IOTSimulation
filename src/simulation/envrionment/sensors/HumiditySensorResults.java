package simulation.envrionment.sensors;

public class HumiditySensorResults {
	float absHumidity;
	float relHumidity;
	float minAbsHumidity;
	float minRelHumidity;
	float maxAbsHumidity;
	float maxRelHumidity;

	public HumiditySensorResults(float radiometry, float luminous, float minRadiometry, float minLuminous,
			float maxRadiometry, float maxLuminous) {
		super();
		this.absHumidity = radiometry;
		this.relHumidity = luminous;
		this.minAbsHumidity = minRadiometry;
		this.minRelHumidity = minLuminous;
		this.maxAbsHumidity = maxRadiometry;
		this.maxRelHumidity = maxLuminous;
	}

	public SensorResults measurements(boolean temp, boolean humidity) {
		if (this.absHumidity > this.maxAbsHumidity || this.relHumidity > this.maxAbsHumidity)
			return SensorResults.HIGH;
		if (this.absHumidity < this.minAbsHumidity || this.relHumidity < this.minRelHumidity)
			return SensorResults.LOW;
		return SensorResults.Normal;
	}
}




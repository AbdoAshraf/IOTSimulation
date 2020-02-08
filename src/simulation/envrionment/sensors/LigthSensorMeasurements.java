package simulation.envrionment.sensors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LigthSensorMeasurements {
	private float radiometry;
	private float luminous;
	private float minRadiometry;
	private float minLuminous;
	private float maxRadiometry;
	private float maxLuminous;

	public LigthSensorMeasurements(float radiometry, float luminous, float minRadiometry, float minLuminous,
			float maxRadiometry, float maxLuminous) {
		super();
		this.radiometry = radiometry;
		this.luminous = luminous;
		this.minRadiometry = minRadiometry;
		this.minLuminous = minLuminous;
		this.maxRadiometry = maxRadiometry;
		this.maxLuminous = maxLuminous;
	}

	public SensorResults measurements() {
		if (this.radiometry > this.maxRadiometry || this.luminous > this.maxRadiometry)
			return SensorResults.HIGH;
		if (this.radiometry < this.minRadiometry || this.luminous < this.minLuminous)
			return SensorResults.LOW;
		return SensorResults.Normal;
	}
}

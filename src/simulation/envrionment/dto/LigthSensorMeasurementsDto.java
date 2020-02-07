package simulation.envrionment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LigthSensorMeasurementsDto {
	private float radiometry;
	private float luminous;
	private float minRadiometry;
	private float minLuminous;
	private float maxRadiometry;
	private float maxLuminous;
	

}

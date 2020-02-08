package simulation.envrionment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LightSensorDTO {
	// private int point;
	private int timeInterval;
	LigthSensorMeasurementsDto ligthSensorMeasurements;

}

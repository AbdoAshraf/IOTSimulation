package simulation.envrionment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HumiditySensorDTO {
	private int timeInterval;
	HumiditySensorMeasurementsDTO HumiditiySensorMeasurementsdto;
}

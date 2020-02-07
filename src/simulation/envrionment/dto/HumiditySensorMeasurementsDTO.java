package simulation.envrionment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HumiditySensorMeasurementsDTO {
	private float absHumidity;
	private float relHumidity;
	private float minAbsHumidity;
	private float minRelHumidity;
	private float maxAbsHumidity;
	private float maxRelHumidity;
	 

}

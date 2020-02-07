package simulation.envrionment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TempSensorDTO {
	//private int point;
	private float value;
	private int timeInterval;
	private float min;
	private float max;
}

package simulation.envrionment.dto;

import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import simulation.envrionment.sensors.SensorResults;

@Getter
@Setter
@AllArgsConstructor
public class LightSensorDTO {
	//private int point;
	private int timeInterval;
	LigthSensorMeasurementsDto ligthSensorMeasurements;

}

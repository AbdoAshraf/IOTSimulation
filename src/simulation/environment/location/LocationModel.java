package simulation.environment.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationModel {
	private String filName;
	private String state;
	private float absHumidity;
	private float relHumidity;
	private float radiometry;
	private float luminous;
	private float temp;
}

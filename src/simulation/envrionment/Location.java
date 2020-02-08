package simulation.envrionment;

import java.util.concurrent.ConcurrentHashMap;

import org.modelmapper.ModelMapper;

import lombok.Getter;
import lombok.Setter;
import simulation.envrionment.dto.HumiditySensorDTO;
import simulation.envrionment.dto.LightSensorDTO;
import simulation.envrionment.dto.TempSensorDTO;
import simulation.envrionment.sensors.HumiditySensor;
import simulation.envrionment.sensors.LightSensor;
import simulation.envrionment.sensors.SensorResults;
import simulation.envrionment.sensors.TempSensor;

@Getter
@Setter
public class Location {
	private String name;
	private LightSensor lightSensor;
	private HumiditySensor humiditySensor;
	private TempSensor tempSensor;
	ConcurrentHashMap<String, SensorResults> map;

	public Location(String name) {
		super();
		map = new ConcurrentHashMap<>();
		map.put("Ligth", SensorResults.Inactive);
		map.put("Temp", SensorResults.Inactive);
		map.put("Humidity", SensorResults.Inactive);
		this.name = name;
	}

	public void initSensor(TempSensorDTO tempSensor) {
		ModelMapper modelMapper = new ModelMapper();
		this.tempSensor = modelMapper.map(tempSensor, TempSensor.class);
		this.tempSensor.setMap(map);
		StringBuilder name = new StringBuilder("temp Sensor at");
		name.append(this.name);
		this.tempSensor.setName(name.toString());
		this.tempSensor.start();
	}

	public void initSensor(LightSensorDTO lightSensorDTO) {
		ModelMapper modelMapper = new ModelMapper();
		this.lightSensor = modelMapper.map(lightSensorDTO, LightSensor.class);
		this.lightSensor.setMap(map);
		StringBuilder name = new StringBuilder("light Sensor at");
		name.append(this.name);
		this.lightSensor.setName(name.toString());
		this.lightSensor.start();
	}

	public void initSensor(HumiditySensorDTO humiditySensorDTO) {
		ModelMapper modelMapper = new ModelMapper();
		this.humiditySensor = modelMapper.map(humiditySensorDTO, HumiditySensor.class);
		this.humiditySensor.setMap(map);
		StringBuilder name = new StringBuilder("humidity Sensor at");
		name.append(this.name);
		this.humiditySensor.setName(name.toString());
		this.humiditySensor.start();
	}

	@SuppressWarnings("deprecation")
	public void deactivateLightSensor() {
		try {
			this.lightSensor.suspend();
		} catch (Exception e) {
			System.out.println("invalid operation " + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public void activateLightSensor() {
		try {
			if (this.lightSensor.getState() == Thread.State.NEW)
				this.lightSensor.start();
			else
				this.lightSensor.resume();

		} catch (Exception e) {
			System.out.println("invalid operation " + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public void deactivateTempSensor() {
		try {
			this.tempSensor.suspend();
		} catch (Exception e) {
			System.out.println("invalid operation " + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public void activateTemptSensor() {
		try {
			if (this.tempSensor.getState() == Thread.State.NEW)
				this.tempSensor.start();
			else
				this.tempSensor.resume();
		} catch (Exception e) {
			System.out.println("invalid operation " + e.getMessage());
		}
	}

	public void deactivateHumiditySensor() {
		try {
			this.humiditySensor.suspend();
		} catch (Exception e) {
			System.out.println("invalid operation " + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public void activateHumiditySensor() {
		try {
			if (this.humiditySensor.getState() == Thread.State.NEW)
				this.humiditySensor.start();
			else
				this.humiditySensor.resume();
		} catch (Exception e) {
			System.out.println("invalid operation " + e.getMessage());
		}
	}
}

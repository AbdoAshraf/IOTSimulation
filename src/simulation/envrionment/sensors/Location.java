package simulation.envrionment.sensors;

import java.util.concurrent.ConcurrentHashMap;

import org.modelmapper.ModelMapper;

import lombok.Getter;
import lombok.Setter;
import simulation.envrionment.dto.HumiditySensorDTO;
import simulation.envrionment.dto.LightSensorDTO;
import simulation.envrionment.dto.TempSensorDTO;

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

	/*
	 * public void initLightSensor(int timeInterval, float radiometry, float
	 * luminous, float minRadiometry, float minLuminous, float maxRadiometry, float
	 * maxLuminous) { if (this.lightSensor == null) { // this.lightSensor = new
	 * LightSensor(value,map,timeInterval,min,max); LigthSensorMeasurements m = new
	 * LigthSensorMeasurements(radiometry, luminous, minRadiometry, minLuminous,
	 * maxRadiometry, maxLuminous); this.lightSensor = new LightSensor(timeInterval,
	 * map, m); return; } //System.out.println("Already Activated"); }
	 * 
	 * public void initHumiditySensor(float value, int timeInterval, int min, int
	 * max) { if (this.humiditySensor.getState() == Thread.State.TERMINATED ||
	 * this.humiditySensor == null) { // this.humiditySensor = new
	 * HumiditySensor(timeInterval, map, value, min, max); return; }
	 * System.out.println("Already Activated"); }
	 * 
	 * public void initTempSensor(float value, int timeInterval, float min, float
	 * max) { if (this.tempSensor == null) { this.tempSensor = new
	 * TempSensor(timeInterval, map, value, min, max); return; }
	 * System.out.println("Already Activated"); }
	 * 
	 * public void initLightSensor(LightSensorDTO lightSensroDTO) {
	 * 
	 * }
	 * 
	 * /
	 **/
	public void initSensor(TempSensorDTO tempSensor) {
		ModelMapper modelMapper = new ModelMapper();
		this.tempSensor = modelMapper.map(tempSensor, TempSensor.class);
		this.tempSensor.setMap(map);
		StringBuilder name = new StringBuilder("temp Sensor at loc");
		name.append(this.name);
		this.tempSensor.setName(name.toString());
	}

	public void initSensor(LightSensorDTO lightSensorDTO) {
		ModelMapper modelMapper = new ModelMapper();
		this.lightSensor = modelMapper.map(lightSensorDTO, LightSensor.class);
		this.lightSensor.setMap(map);
		StringBuilder name = new StringBuilder("light Sensor at loc");
		name.append(this.name);
		this.tempSensor.setName(name.toString());
	}
	
	
	public void initSensor(HumiditySensorDTO humiditySensorDTO) {
		ModelMapper modelMapper = new ModelMapper();
		this.humiditySensor= modelMapper.map(humiditySensorDTO, HumiditySensor.class);
		this.humiditySensor.setMap(map);
		StringBuilder name = new StringBuilder("humidity Sensor at loc");
		name.append(this.name);
		this.tempSensor.setName(name.toString());
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
	
	
	/*
	 * public void adjustSensor(Sensor S, int i) { S.setTimeInterval(i); }
	 * 
	 * public void adjustLightSensor(float radiometry, float luminous) {
	 * this.lightSensor.adjust(radiometry, luminous); }
	 * 
	 * public void adjustSensorRange(LightSensor s) {
	 * this.lightSensor.adjustRange(s); }
	 * 
	 * public void adjustSensorRange(TempSensor s) { this.tempSensor.adjustRange(s);
	 * 
	 * }
	 * 
	 * public void changeTimeInterval(LightSensor s, int time) {
	 * this.lightSensor.setTimeInterval(time); }
	 * 
	 * public void changeTimeInterval(TempSensor s, int time) {
	 * this.tempSensor.setTimeInterval(time); }
	 * 
	 * public void changemessageFormat(LightSensor s) {
	 * this.lightSensor.setMessageFormat(s.getMessageFormat()); } public void
	 * changemessageFormat(TempSensor s) {
	 * this.tempSensor.setMessageFormat(s.getMessageFormat()); }/
	 **/

}

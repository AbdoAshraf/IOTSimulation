package simulation.sensor_control;


import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;
import simulation.sensor_control.control.Actuator;
import simulation.sensor_control.control.MessageType;
import simulation.sensor_control.sensors.HumiditySensor;
import simulation.sensor_control.sensors.LightSensor;
import simulation.sensor_control.sensors.Sensor;
import simulation.sensor_control.sensors.SensorResults;
import simulation.sensor_control.sensors.TempSensor;

@Getter
@Setter
public class Location {
	private String name;
	private LightSensor lightSensor;
	private HumiditySensor humiditySensor;
	private TempSensor tempSensor;
	private Actuator actuator;
	ConcurrentHashMap<String, SensorResults> map;
	ConcurrentHashMap<String, Float> varibles;
	public Location(String name) {
		super();
		map = new ConcurrentHashMap<>();
		map.put("Ligth", SensorResults.Inactive);
		map.put("Temp", SensorResults.Inactive);
		map.put("Humidity", SensorResults.Inactive);
		this.name = name;
	}

	public Location(String locName, ConcurrentHashMap<String, Float> varibles) {
		this.name = locName;
		this.tempSensor = new TempSensor();
		this.humiditySensor = new HumiditySensor();
		this.lightSensor= new LightSensor();
		this.varibles = varibles;
		this.map = new ConcurrentHashMap<>();
		this.map.put("Light", SensorResults.Inactive);
		this.map.put("Temp", SensorResults.Inactive);
		this.map.put("Humidity", SensorResults.Inactive);
		this.actuator = new Actuator(this.map,locName);
	}

	private Sensor getTargetSensor(Sensor S) {

		if (S instanceof TempSensor)
			return this.tempSensor;
		else if (S instanceof LightSensor)
			return this.lightSensor;
		else if (S instanceof HumiditySensor)
			return this.humiditySensor;
		return null;
	}

	public void initSensor(Sensor S) {
		Sensor target = this.getTargetSensor(S);
		target =S;
		target.setMap(map);
		target.setVaribles(this.varibles);
		target.attach(actuator);
		target.start();
	}

	@SuppressWarnings("deprecation")
	public void activateSensor(Sensor S) {
		try {
			if (this.getTargetSensor(S).getState() == Thread.State.NEW)
				this.getTargetSensor(S).start();
			else
				this.getTargetSensor(S).resume();
		} catch (Exception e) {
			System.out.println("invalid operation " + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public void deactivateSensor(Sensor S) {
		try {
			this.getTargetSensor(S).suspend();
		} catch (Exception e) {
			System.out.println("invalid operation " + e.getMessage());
		}
	}
	
	public void changeperiod(Sensor S,int timeInterval) {
		this.getTargetSensor(S).setTimeInterval(timeInterval);
	}
	
	public void changeMessageType( MessageType messageType) {
		this.actuator.setMessageType(messageType);
	}
	
	public void changeSensorRange(Sensor s) {
		Sensor target = this.getTargetSensor(s);
		s.setTimeInterval(target.getTimeInterval());
		s.setMap(target.getMap());
		s.setVaribles(target.getVaribles());
		target=s;
		s.start();
	}
	
	/*
	 * public void initSensor(TempSensorDTO tempSensor) { ModelMapper modelMapper =
	 * new ModelMapper(); this.tempSensor = modelMapper.map(tempSensor,
	 * TempSensor.class); this.tempSensor.setMap(map); StringBuilder name = new
	 * StringBuilder("temp Sensor at"); name.append(this.name);
	 * this.tempSensor.setName(name.toString()); this.tempSensor.start(); }
	 * 
	 * public void initSensor(LightSensorDTO lightSensorDTO) { ModelMapper
	 * modelMapper = new ModelMapper(); this.lightSensor =
	 * modelMapper.map(lightSensorDTO, LightSensor.class);
	 * this.lightSensor.setMap(map); StringBuilder name = new
	 * StringBuilder("light Sensor at"); name.append(this.name);
	 * this.lightSensor.setName(name.toString()); this.lightSensor.start(); }
	 * 
	 * public void initSensor(HumiditySensorDTO humiditySensorDTO) { ModelMapper
	 * modelMapper = new ModelMapper(); this.humiditySensor =
	 * modelMapper.map(humiditySensorDTO, HumiditySensor.class);
	 * this.humiditySensor.setMap(map); StringBuilder name = new
	 * StringBuilder("humidity Sensor at"); name.append(this.name);
	 * this.humiditySensor.setName(name.toString()); this.humiditySensor.start(); }
	 * 
	 * @SuppressWarnings("deprecation") public void deactivateLightSensor() { try {
	 * this.lightSensor.suspend(); } catch (Exception e) {
	 * System.out.println("invalid operation " + e.getMessage()); } }
	 * 
	 * @SuppressWarnings("deprecation") public void activateLightSensor() { try { if
	 * (this.lightSensor.getState() == Thread.State.NEW) this.lightSensor.start();
	 * else this.lightSensor.resume();
	 * 
	 * } catch (Exception e) { System.out.println("invalid operation " +
	 * e.getMessage()); } }
	 * 
	 * @SuppressWarnings("deprecation") public void deactivateTempSensor() { try {
	 * this.tempSensor.suspend(); } catch (Exception e) {
	 * System.out.println("invalid operation " + e.getMessage()); } }
	 * 
	 * @SuppressWarnings("deprecation") public void activateTemptSensor() { try { if
	 * (this.tempSensor.getState() == Thread.State.NEW) this.tempSensor.start();
	 * else this.tempSensor.resume(); } catch (Exception e) {
	 * System.out.println("invalid operation " + e.getMessage()); } }
	 * 
	 * public void deactivateHumiditySensor() { try { this.humiditySensor.suspend();
	 * } catch (Exception e) { System.out.println("invalid operation " +
	 * e.getMessage()); } }
	 * 
	 * @SuppressWarnings("deprecation") public void activateHumiditySensor() { try {
	 * if (this.humiditySensor.getState() == Thread.State.NEW)
	 * this.humiditySensor.start(); else this.humiditySensor.resume(); } catch
	 * (Exception e) { System.out.println("invalid operation " + e.getMessage()); }
	 * }/
	 **/
}

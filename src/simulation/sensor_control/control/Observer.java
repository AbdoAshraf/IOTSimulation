package simulation.sensor_control.control;

import java.util.concurrent.ConcurrentHashMap;

import simulation.sensor_control.sensors.SensorResults;

public abstract class Observer {
	   protected ConcurrentHashMap<String, SensorResults> map;
	   public abstract void update();
	}
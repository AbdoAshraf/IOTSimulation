package simulation.environment;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import lombok.NoArgsConstructor;
import simulation.environment.location.LocationService;


public class Environment {
	HashMap<Integer, LocationService> locationMap;
	
	public Environment(){
		this.locationMap = new HashMap<Integer, LocationService>();
	}

	public void addLocation(int point, ConcurrentHashMap<String, Float> varibles) {
		LocationService locationService = new LocationService(varibles);
		locationMap.putIfAbsent(point, locationService);
		//locationMap.get(point).start();
	}

	public void removeLocation(int point) {
		locationMap.remove(point);
	}

	public void setFileName(int point, String f) {
		locationMap.get(point).setFileName(f);
	}

	public void setState(int point, String change) {
		locationMap.get(point).setState(change);
		locationMap.get(point).updateView();;

		//locationMap.get(point).start();
	}
	
	
}

package simulation.environment.location;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class LocationService extends Thread {
	private LocationModel locationModel;
	private LocationView locatinView;

	public LocationService(ConcurrentHashMap<String, Float> varibles) {
		locationModel = new LocationModel();
		locatinView = new LocationView(varibles);
	}

	public void updateView() {
		this.start();
		locatinView.updateView(this.locationModel.getAbsHumidity(), this.locationModel.getRelHumidity(),
				this.locationModel.getLuminous(), this.locationModel.getRadiometry(), this.locationModel.getTemp());
	}

	@Override
	public void run() {
		try {
			this.environmentChangings();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void environmentChangings() throws FileNotFoundException {
		File file = new File("./data/"+this.locationModel.getFilName()+".txt");
		try (Scanner input = new Scanner(file)) {
			// int count = 0;
			String word = input.next();
			///word = input.next();
			//System.out.println(word);
			if (word.equals(this.locationModel.getState())) {
				this.locationModel.setAbsHumidity(input.nextFloat());
				this.locationModel.setRelHumidity(input.nextFloat());
				this.locationModel.setLuminous(input.nextFloat());
				this.locationModel.setRadiometry(input.nextFloat());
				this.locationModel.setTemp(input.nextFloat());

			}
		}
		//this.updateView();
	}

	public void setFileName(String fileName) {
		this.locationModel.setFilName(fileName);
	}

	public void setState(String state) {
		this.locationModel.setState(state);
		//this.updateView();
	}

}

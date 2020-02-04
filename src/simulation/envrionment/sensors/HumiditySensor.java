package simulation.envrionment.sensors;

public class HumiditySensor extends Sensor {

	public HumiditySensor() {
		super();
	}

	public HumiditySensor(String type, float min, float max, float value) {
		super(type, min, max, value);
	}

	@Override
	public void simulateAction() {
		float low=max+10;
		float hiegh=min-10;
		float res = (float) (Math.random() * (hiegh - low)) + low;
		this.setValue(res);
	}

}

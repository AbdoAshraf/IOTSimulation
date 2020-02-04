package simulation.envrionment.sensors;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class Sensor {

	protected String type;
	protected float min;
	protected float max;
	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	protected float value;
    boolean Active;
	enum message {
		LOW, MEDIUM
	};

	public Sensor(String type, float min, float max, float value) {
		super();
		this.type = type;
		this.min = min;
		this.max = max;
		this.value = value;
		this.Active=true;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	Sensor() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	abstract public void simulateAction();

	void printResults() {
		// System.out.println(simulateAction());
	}

	public void sendMessage(String SMS) {
		log(SMS);
	}

	void setRange(float start, float offset) {

	}

	boolean activate(int sensorId) {
		return false;
	}

	boolean deactivate(int sensorId) {
		return false;
	}

	/*boolean setTimeInterval(int period) {
		return false;
	}/**/
   
	public void changeRange(float from, float to) {
		this.min = from;
		this.max = to;
	}
	
	 private void log(String s) {
	    	Logger logger = Logger.getLogger("MyLog");  
	        FileHandler fh;  

	        try {  

	            // This block configure the logger with handler and formatter  
	            fh = new FileHandler("./text");  
	            logger.addHandler(fh);
	            logger.setUseParentHandlers(false);
	            SimpleFormatter formatter = new SimpleFormatter();  
	            fh.setFormatter(formatter);  
	            
	            // the following statement is used to log any messages  
	            logger.info(s);  
	            fh.flush();

	            fh.close();
	        } catch (SecurityException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  

	       // logger.info("Hi How r u?");  
	    }

}

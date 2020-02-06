package simulation.envrionment.sensors;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class Sensor extends Thread {
	protected int timeInterval;
	ConcurrentHashMap<String, SensorResults> map;
    private String messageFormat;    
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


	public Sensor(int timeInterval, ConcurrentHashMap<String, SensorResults> map) {
		super();
		this.timeInterval = timeInterval;
		this.map = map;
	}



}

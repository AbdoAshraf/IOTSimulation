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
	protected ConcurrentHashMap<String, SensorResults> map;
	protected MessageType messageType;

	protected void log(String s) {
		FileHandler fh;
		try {
			// This block configure the logger with handler and formatter
			Logger logger = Logger.getLogger("MyLog");
			fh = new FileHandler("./text.log", true);
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
		this.messageType = MessageType.SMS;
	}

	public Sensor() {
		this.messageType = MessageType.SMS;
	}

	abstract void performReading();
	// abstract void creatMessage();

	void creatMessage(String S) {
		StringBuilder message = new StringBuilder();
		if (this.messageType == MessageType.SMS) {
			message.append("sender" + this.getName());
			message.append("\n");
			message.append(S);

		} else if (this.messageType == MessageType.EMAIL) {
			message.append("title: sensor alarm\n");
			message.append(this.getName());
			message.append("\n");
			message.append(S);
		}
		this.log(message.toString());
	}
	
	

}

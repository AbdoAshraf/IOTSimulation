package simulation.sensor_control.control;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import simulation.sensor_control.sensors.SensorResults;

@NoArgsConstructor
@Getter
@Setter
public class Actuator extends Observer {
	// private ConcurrentHashMap<String, SensorResults> map;
	private MessageType messageType;
	private String info;

	public Actuator(ConcurrentHashMap<String, SensorResults> m, String info) {
		this.map = m;
		this.info = info;
		this.messageType = MessageType.SMS;
	}

	@Override
	public void update() {
		SensorResults light = this.map.get("Light");
		SensorResults hum = this.map.get("Humidity");
		SensorResults temp = this.map.get("Temp");
		// System.out.println(light);
		if (light == SensorResults.HIGH && temp == SensorResults.HIGH) {
			// System.out.println("abnormal temp");
			this.creatMessage("hight temp");
		}
		if (hum == SensorResults.HIGH && temp == SensorResults.HIGH) {
			// System.out.println("hight hum");
			this.creatMessage("hight humidity");
		}

		if (light == SensorResults.HIGH) {
			// System.out.println("hight temp");
			this.creatMessage("hight light");

		}
		if (light == SensorResults.LOW) {
			this.creatMessage("low light");
		}
		if (hum == SensorResults.LOW) {
			this.creatMessage("low humidity");
		}
		if (temp == SensorResults.LOW) {
			this.creatMessage("low temp");
		}

	}

	void creatMessage(String S) {
		StringBuilder message = new StringBuilder();
		if (this.messageType == MessageType.SMS) {
			message.append("sender" + this.info);
			message.append("\n");
			message.append(S);

		} else if (this.messageType == MessageType.EMAIL) {
			message.append("title: sensor alarm\n");
			message.append(this.info);
			message.append("\n");
			message.append(S);
		}
		this.log(message.toString());
	}

	private void log(String s) {
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

}

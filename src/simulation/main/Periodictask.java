package simulation.main;

import simulation.envrionment.Environment;

public class Periodictask implements Runnable {
	Environment evnvironment;
	boolean runner;

	public Periodictask(Environment e) {
		this.evnvironment = e;
	}

	@Override
	public void run() {
		this.evnvironment.ReadValue();
	}
}

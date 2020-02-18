package simulation.app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			App app = new App(sc);
			app.Intiate();
		}

	}

}

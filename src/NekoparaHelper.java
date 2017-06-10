import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NekoparaHelper {

	public static void main(String[] args) throws Exception {
		// Tell weaboo how to use the application
		System.out.println(
				"Open Nekopara, after Nekopara is open type the amount of times you would like P to be pressed a second.");

		// Create robot needed to press the P button
		Robot inputMaster = new Robot();

		// Determine when to press the P button
		long lastPress = 0;
		int timer = -1;
		BufferedReader textInput = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			// Take input when possible
			if (textInput.ready()) {
				try {
					// Parse string as an integer
					int pressCount = Integer.parseInt(textInput.readLine());
					if (pressCount > 1000) {
						// If they go overboard we limit their urges
						System.err.println("Sorry, but 1000 is the highest you can go you sick pervert.");
						pressCount = 1000;
					} else if (pressCount < 0) {
						// If the press count is less than 0 exit the program
						textInput.close();
						System.exit(69);
					}
					timer = 1000 / pressCount;
				} catch (NumberFormatException e) {
					// The idiot didn't even type a number
					System.err.println("That isn't a number you nerd. Keeping PPS at " + (timer * 1000));
				}
			}

			// Determine if it is possible to press the P button
			if (System.currentTimeMillis() - lastPress > timer && timer >= 0) {
				inputMaster.keyPress(KeyEvent.VK_P);
				lastPress = System.currentTimeMillis();
			}

		}
	}

}

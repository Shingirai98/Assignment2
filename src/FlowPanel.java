/**
 * <h1>Flow Panel!</h1>
 * The Flow Panel Program extends  implements Runnable.
 * This then allows us to override the run method which will be executed by
 * different threads
 * <p>
 * The Flow Panel also extends the JPanel which would not have been possible
 * had we extended the Thread class.
 * This inheritance allows us to ovveride the paintComponent methods</p>
 * <p>
 * @Author Denver Maburutse & James Gain
 * @version 1.0
 * @since 2020-08-11
 * </p>
 */

import java.awt.*;
import javax.swing.*;


public class FlowPanel extends JPanel implements Runnable {

	Terrain land;
	Water waterObj;
	Graphics g;
	Flow flowObj;

	FlowPanel(Terrain terrain) {
		land=terrain;
	}

	/**
	 *<p>This is an overriden method from the JPanel class.
	 * It assigns the the width and height of the class.
	 * It assigns the water and land terrain imagery
	 */
	@Override
	protected void paintComponent(Graphics g) {//Override the paintComponent method from JPanel
		int width = getWidth();
		int height = getHeight();

		super.paintComponent(g);

		// draw the landscape in greyscale as an image
		if (land.getImage() != null){
			g.drawImage(land.getImage(), 0, 0, null);
		}

		if (land.getWaterImage()!= null){
			g.drawImage(land.getWaterImage(),0, 0, null);
		}


	}
	/**
	 * This is an overriden method from the Runnable interface
	 * It is executed by every thread created and repaints the image after every time step
	 */
	public void run() {
		// display loop here
		// to do: this should be controlled by the GUI
		// to allow stopping and starting
		while(true){
			while(Flow.play) {
				//flowObj.timer.setText(" " + flowObj.timeListen);
				Flow.player(land);
				repaint();
			}
		}


	}
}

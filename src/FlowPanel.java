//package FlowSkeleton;

import java.awt.*;
import javax.swing.*;
//import java.util.LinkedList;
//import java.util.List;


public class FlowPanel extends JPanel implements Runnable {
	Terrain land;
	Water waterObj;
	Graphics g;
	Flow flowObj;
	//int xMax = land.getDimX();
	//int yMax = land.getDimY();
	//private List<Rect> rects = new LinkedList<Rect>();
	FlowPanel(Terrain terrain) {
		land=terrain;
	}

	// responsible for painting the terrain and water
	// as images
	/*public void addRect(Rect rect){
		rects.add(rect);
		this.repaint();
	}*/

	/*public void remRect(){
		//rects.clear();
		for(Rect r : rects){
			//Flow f = new Flow();
			r.undraw(g);
			this.repaint();
		}

		rects.clear();
		//paintComponent(g);
	}*/
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

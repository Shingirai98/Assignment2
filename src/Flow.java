//package FlowSkeleton;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;


public class Flow {
	static long startTime = 0;//start time of program
	static int frameX; //x dimension of frame
	static int frameY; //y dimension of frame
	static FlowPanel fp; //variable for flow panel object
	static JFrame frame;
	static int xPos;
	static int yPos;
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}

	// stop timer, return time elapsed in seconds
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f;
	}

	//method to setup the GUI
	public static void setupGUI(int frameX,int frameY,Terrain landdata) {

		Dimension fsize = new Dimension(800, 800);//Instantiate frame size object
    	frame = new JFrame("Waterflow");//Instantiate frame object
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set the x button to close properly
    	frame.getContentPane().setLayout(new BorderLayout());

      	JPanel panelObj = new JPanel(); //Instantiate a panel object
        panelObj.setLayout(new BoxLayout(panelObj, BoxLayout.PAGE_AXIS));//Set panel layout with a page setup with a box layout

		fp = new FlowPanel(landdata);// make a new flow panel object called fp, this is where you are going to make a thread
		fp.setPreferredSize(new Dimension(frameX,frameY)); // set size of flow panel depending on the input grid size
		panelObj.add(fp);//add fp object to the JPanel object


		// to do: add a MouseListener, buttons and ActionListeners on those buttons




		//Buttons and ActionListeners for the buttons
		JPanel panelObj2 = new JPanel();//Make a JPanel at the bottom of page for buttons
	    panelObj2.setLayout(new BoxLayout(panelObj2, BoxLayout.LINE_AXIS));//put/lay the panel as a small line
		JButton endB = new JButton("End");// create a JButton written 'End'
		JButton resetB = new JButton("Reset");// create a JButton written 'Reset'
		JButton pauseB = new JButton("Pause");// create a JButton written 'Pause'
		JButton playB = new JButton("Play");// create a JButton written 'Play'

		// add the listener to the jbutton to handle the "pressed" event
		endB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// to do ask threads to stop
				frame.dispose();
			}
		});

		resetB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				//Add function to clear the page
				for(int y = 0; y < landdata.getDimY(); y++){
					for(int x = 0; x < landdata.getDimX(); x++){
						landdata.w.wDepth[x][y] = 0;
					}
				}
				landdata.waterImageDer();
				fp.getGraphics().drawImage(landdata.getWaterImage(), 0, 0, null);
				fp.repaint();

				//w.remWater();
				//fp.remRect();
				//fp.repaint();
			}
		});

		pauseB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b){
				//Add function to stop the timer and the water flow
			}
		});
		playB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent c){
				//Add function to continue timer and water flow
			}
		});
		panelObj2.add(endB);//add the end button to the lower panel
		panelObj2.add(resetB);//add the reset button to the lower panel
		panelObj2.add(pauseB);//add the pause button to the lower panel
		panelObj2.add(playB);//add the play button to the lower panel
		panelObj.add(panelObj2); // Add the lower panel to the big panel

		frame.setSize(frameX, frameY+50);	// a little extra space at the bottom for buttons
      	frame.setLocationRelativeTo(null);  // center window on screen
      	frame.add(panelObj); //add body panel to frame
        frame.setContentPane(panelObj);

		frame.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//Add flow object to frame with mouse listening feature
				//System.out.println("X:" +e.getX()+ " Y:" + e.getY());
				int x = e.getX()-2;
				int y = e.getY()-27;

					for (int i = -3; i <= 3; i++) {
						for (int j = -3; j <= 3; j++) {
							try {
								landdata.w.wDepth[x + i][y + j] += 0.03f;
							}
							catch (ArrayIndexOutOfBoundsException l){
								continue;
						}
					}
				}


				landdata.waterImageDer();
				fp.getGraphics().drawImage(landdata.getWaterImage(), 0, 0, null);
				fp.repaint();
				//panelObj.add(frame.setBackground(Color.blue));
				//fp.addRect(new Rect(e.getX() -5, e.getY() -30));//, Color.blue));
//				w.initWater(e.getX()-5, e.getY()-30);
//				System.out.println(w.getWDepth(e.getX(), e.getY()-30));
				/*xPos = e.getX();
				yPos = e.getY()-25;
				frame.setBackground(Color.blue);*/
				//in

			}


		});

		frame.setVisible(true);
        Thread fpt = new Thread(fp);//Make a new Thread
        fpt.start();// Run the run method with thread fpt


	}


	public static void main(String[] args) {
		Terrain landdata = new Terrain();//Makes a terrain object called land data

		// check that number of command line arguments is correct
		if(args.length != 1)
		{
			System.out.println("Incorrect number of command line arguments. Should have form: java -jar flow.java intputfilename");
			System.exit(0);
		}

		// landscape information from file supplied as argument
		//
		landdata.readData(args[0]);//read the input file name

		frameX = landdata.getDimX();//Get the x dimension as the first number
		frameY = landdata.getDimY();//Get the y dimension as the second number
		SwingUtilities.invokeLater(()->setupGUI(frameX, frameY, landdata));

		// to do: initialise and start simulation
	}
}

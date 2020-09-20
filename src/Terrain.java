/**
 * <h1>Terrain!</h1>
 * This is the terrain class
 * It reads the data file that is an argument of the main methods
 * This data is then converted to show certain colours depending on the height value.
 * <p>
 * The Terrain class derives both the land images and the water images
 * </p>
 * @Author Denver Maburutse & James Gain
 * @version 1.0
 * @since 2020-08-11
 */

import java.util.Locale;
import java.io.File;
import java.awt.image.*;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terrain {

	public float [][] height; // regular grid of height values
	int dimx, dimy; // data dimensions
	BufferedImage img; // greyscale image for displaying the terrain top-down
	Water w;
	BufferedImage waterImg;
	ArrayList<Integer> permute;	// permuted list of integers in range [0, dimx*dimy)

	// overall number of elements in the height grid
	int dim(){
		return dimx*dimy;
	}

	// get x-dimensions (number of columns)
	int getDimX(){
		return dimx;
	}

	// get y-dimensions (number of rows)
	int getDimY(){
		return dimy;
	}



	// get greyscale image
	public BufferedImage getImage() {
		return img;
	}

	public BufferedImage getWaterImage() {
		return waterImg;
	}

	/**
	 * <p>The deriveImage method normalizes different terrain heights and create a greyscale
	 *	image that will be used to make a colour with different shades from black to white</p>
	 */
	// convert height values to greyscale colour and populate an image
	void deriveImage()
	{
		img = new BufferedImage(dimy, dimx, BufferedImage.TYPE_INT_ARGB);
		float maxh = -10000.0f, minh = 10000.0f;

		// determine range of heights
		for(int x=0; x < dimx; x++)
			for(int y=0; y < dimy; y++) {
				float h = height[x][y];
				if(h > maxh)
					maxh = h;
				if(h < minh)
					minh = h;
			}

		for(int x=0; x < dimx; x++)
			for(int y=0; y < dimy; y++) {
				// find normalized height value in range
				float val = (height[x][y] - minh) / (maxh - minh);
				Color col = new Color(val, val, val, 1.0f);
				img.setRGB(x, y, col.getRGB());
			}
	}

	/**
	 * <p>The waterImageDer method derives the water image by checking the value of water depth</p>
	 */
	void waterImageDer(){
		waterImg = new BufferedImage(dimx, dimy, BufferedImage.TYPE_INT_ARGB);

		for(int x=0; x < dimx; x++)
			for(int y=0; y < dimy; y++) {
				if (w.getWDepth(x,y) > 0){
					waterImg.setRGB(x, y, Color.blue.getRGB());
				}

			}
	}

	/**
	 * <p> genPermute generates a permuted list of linear index positions to allows
	 * random traversal over the terrain</p>
	 */
	void genPermute() {
		permute = new ArrayList<Integer>();
		for(int idx = 0; idx < dim(); idx++)
			permute.add(idx);
		java.util.Collections.shuffle(permute);
	}

	/**
	 * <p> getPermute finds permuted 2D location from a linear index in the range of
	 * zero to the total number of grid values ie. xDimensions * yDimensions</p>
	 * @param  i     : linear index
	 * @param  loc : array containing x and y value
	 */
	void getPermute(int i, int [] loc) {
		locate(permute.get(i), loc);
	}

	/**
	 * <p>locate method locates the grid position given a linear index and a length two array</p>
	 * @param  pos   : linear index
	 * @param  ind : array containing x and y value potentially
	 */
	void locate(int pos, int [] ind)
	{
		ind[0] = (int) pos / dimy; // x
		ind[1] = pos % dimy; // y
	}

	/**
	 * <p>readData method takes in a text file document
	 * It derives the x dimensions and y xDimensions
	 * It then assigns the values to 2D array height and assigns the water object altitudes to these values
	 * The water depth values are also initiated at the same time</p>
	 * @param fileName : filename containing terrain values
	 */
	// read in terrain from file
	void readData(String fileName){
		try{
			Scanner sc = new Scanner(new File(fileName));
			sc.useLocale(Locale.US);
			// read grid dimensions
			// x and y correpond to columns and rows, respectively.
			// Using image coordinate system where top left is (0, 0).
			dimy = sc.nextInt();
			dimx = sc.nextInt();

			// populate height grid
			height = new float[dimx][dimy];
			w = new Water(dimx, dimy);
			for(int y = 0; y < dimy; y++){
				for(int x = 0; x < dimx; x++){
					height[x][y] = sc.nextFloat();
					w.altitudes[x][y] = height[x][y];
					w.wDepth[x][y] = 0;

				}
			}

			//transverse through the edges and assign altitude to zero
			for (int r = 0; r < dimx; r++){
				w.altitudes[r][0] = 0;
				w.altitudes[r][dimy-1] = 0;
				w.altitudes[0][r] = 0;
				w.altitudes[dimx-1][r] = 0;
			}
			w.updateWSurface();
			//w.getT(height);
			sc.close();

			// create randomly permuted list of indices for traversal
			genPermute();

			// generate greyscale heightfield image
			deriveImage();
		}
		catch (IOException e){
			System.out.println("Unable to open input file "+fileName);
			e.printStackTrace();
		}
		catch (java.util.InputMismatchException e){
			System.out.println("Malformed input file "+fileName);
			e.printStackTrace();
		}
	}
}

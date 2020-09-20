/**
 * <h1>Water!</h1>
 * <p>The Water Program makes all the water functionalities and properties concerning the water on the GUI</p>
 * @Author Denver Maburutse
 * @version 1.0
 * @since 2020-08-11
 */

import java.util.Arrays;

public class Water {
    private int xDim;
    private int yDim;
    public float[][] wDepth;//array to store the water depth
    public float[][] wSurface;//array to store the total altitude
    public float[][] altitudes;
    float[] v = new float[9];
    //public Terrain land;



    //Water constructor with grid coordinates as parameters
    /**
     * <p>Water constructor </p>
     * @param xDim : x dimensions
     * @param yDim : y dimensions
     */
    public Water(int xDim, int yDim) {
        wDepth = new float[xDim][yDim];
        wSurface = new float[xDim][yDim];
        altitudes = new float[xDim][yDim];
        this.xDim = xDim;
        this.yDim = yDim;
    }




    //Getter methods for x and y
    /**
     * <p>Getter methods of dimensions</p>
     * @return the dimension
     */
    public int getxDim() {
        return xDim;
    }

    public int getyDim() {
        return yDim;
    }
    /**
     * <p>getWDepth returns the water depth at the specified coordinate</p>
     * @param x : x dimensions
     * @param y : y dimensions
     * @return the water depth
     */
    public float getWDepth(int x, int y) {
        return wDepth[x][y];
    }

    /**
     * <p>getWSurface returns the water depth at the specified coordinate</p>
     * @param x : x dimensions
     * @param y : y dimensions
     * @return the water surface
     */
    public float getWSurface(int x, int y) {
        return wDepth[x][y] + altitudes[x][y];
    }

    /**
     * <p>updateWSurface method traverses throughout the grid and updates the water surface
     * by adding the altitude values and water depth</p>
     */
    public void  updateWSurface(){

        for (int i = 0; i < getxDim(); i++) {
            wDepth[i][0] = 0;
            wDepth[0][i] = 0;
            wDepth[i][yDim-1] = 0;
            wDepth[xDim-1][i] = 0;
            for (int j = 0; j < getyDim(); j++) {
                try {
                    wSurface[i][j] = wDepth[i][j] + altitudes[i][j];
                }
                catch(ArrayIndexOutOfBoundsException e){
                    continue;
                }
            }
        }
    }


    /**
     * <p> The lowestN method evaluates the lowest neighbouring position in relation to the current grid position
     * </p>
     * @param  x  : current x position
     * @param  y  : current y position
     * @return o : position of steepest gradient to current position
     */
    public int lowestN(int x, int y){

        //get the value of the difference in water surface altitude and store in an array
        int d =0;
        for (int q = -1; q <= 1; q++) {
            for (int u = -1; u <= 1; u++) {
                v[d] = wSurface[x][y] - wSurface[x + q][y + u];
                d++;
            }

        }
        //find the max difference
        int a = 0;
        float maxValue =v[0];
        for(a=1;a < v.length;a++){
            if(v[a] > maxValue){
                maxValue = v[a];
            }
        }
        int o = 0;
        while (o < v.length) {

            // if the i-th element is t
            // then return the index
            if (v[o] == maxValue) {
                return o;
            } else {
                o = o + 1;
            }
        }
        return o;

    }
    /**
     *<p> addWater method increments water at given position by one unit ie. 0.01</p>
     * @param  x: current x position
     * @param  y: current y position
     */

    public void addWater(int x, int y) {
        wDepth[x][y] += 0.01f;

    }
    /**
     *<p>removeWater method decrements water at given position by one unit ie. 0.01</p>
     * @param x: current x position
     * @param y: current y position
     */
    public void removeWater(int x, int y){
        wDepth[x][y] = wDepth[x][y]-0.01f;
    }
    /**
     *<p> addInitWater method increments water by 3 units during a mouse click ie. 0.03</p>
     * @param  x: current x position
     * @param  y: current y position
     */
    public void addInitWater(int x, int y){
        wDepth[x][y] += 0.03f;
    }



}

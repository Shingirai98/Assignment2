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
    public Water(int xDim, int yDim) {
        wDepth = new float[xDim][yDim];
        wSurface = new float[xDim][yDim];
        altitudes = new float[xDim][yDim];
        this.xDim = xDim;
        this.yDim = yDim;
    }


   /* public Water(Terrain land){
        this.land = land;
    }*/

    //Getter methods for x and y
    public int getxDim() {
        return xDim;
    }

    public int getyDim() {
        return yDim;
    }

    //public void getT(float[][] height) {
        //altitudes = height;
    //}

    public float getWDepth(int x, int y) {
        return wDepth[x][y];
    }

    public float getWSurface(int x, int y) {
        return wDepth[x][y] + altitudes[x][y];
    }
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




    public int lowestN(int x, int y){

        //get the value of the difference in water surface altitude and store in an array
            int d =0;
            for (int q = -1; q <= 1; q++) {
                for (int u = -1; u <= 1; u++) {
                   // for (int d = 0; d <= v.length; d++){
                        v[d] = wSurface[x][y] - wSurface[x + q][y + u];
                        d++;
                //}
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

    //method to add water on grid coordinates by adding the array value at that point by 0.01
    public void addWater(int x, int y) {
        wDepth[x][y] += 0.01f;

    }

    public void removeWater(int x, int y){
        wDepth[x][y] = wDepth[x][y]-0.01f;
    }
    public void addInitWater(int x, int y){
        wDepth[x][y] += 0.03f;
    }

    public void remWater() {
        for (int i = 0; i <= getxDim(); i++) {
            for (int j = 0; j <= getyDim(); j++) {
                if (wDepth[i][j] >= 0) {
                    wDepth[i][j] = 0;
                }
            }
        }
    }
}


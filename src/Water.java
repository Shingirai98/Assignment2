public class Water {
    private int x;
    private int y;
    public float[][] wDepth;//array to store the water depth
    public float[][] wSurface;//array to store the total altitude
    public Terrain land;


    //Water constructor with grid coordinates as parameters
    public Water(int xDim, int yDim) {
        wDepth = new float[xDim][yDim];
        wSurface = new float[xDim][yDim];

    }

    //Getter methods for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getT(int x, int y) {
        return land.height[x][y];
    }

    public float getWDepth(int x, int y) {
        return wDepth[x][y];
    }

    public float getWSurface(int x, int y) {
        return wDepth[x][y] + land.height[x][y];
    }

    //method to add water on grid coordinates by adding the array value at that point by 0.01
    public void addWater(int x, int y) {
        wDepth[x][y] += 0.01f;

    }

    public void remWater() {
        for (int i = 0; i <= land.getDimX(); i++) {
            for (int j = 0; j <= land.getDimY(); j++) {
                if (wDepth[i][j] >= 0) {
                    wDepth[i][j] = 0;
                }
            }
        }
    }
}


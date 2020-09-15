public class Water{
    private int x;
    private int y;
    public float[][] wDepth;//array to store the water depth
    public float[][] wSurface;//array to store the total altitude
    Terrain t = new Terrain();

    //Water constructor with grid coordinates as parameters
    public Water(int x, int y){
        this.x = x;
        this.y = y;

    }

    //Getter methods for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getT(int x, int y) {
        return t.height[x][y];
    }

    public float getWDepth(int x, int y) {
        return wDepth[x][y];
    }

    public float getWSurface(int x, int y) {
        return getWDepth(x,y) + getT(x,y);
    }

    //method to add water on grid coordinates by adding the array value at that point by 0.01
    public void addWater(int x, int y){
        wDepth[x][y] += 0.01f;

    }

    //method to add water on a mouse click, nested for loop is for covering up the square
    public void initWater(int x, int y){
        for(int i = -3; i<= 3; i++){
            for (int j = -3; j<=3; j++){
                addWater(x+i, y+j);
            }
        }
    }
}
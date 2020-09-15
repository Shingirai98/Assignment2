import java.awt.*;

//Rect class that stores the square locations of mouse clicks

public class Rect {
    private int x;
    private int y;
    //private Color color;

    public Rect(int x, int y){
        this.x = x;
        this.y = y;
        //this.color = color;
    }
    //Getter methods
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
   /* public Color getColor(){
        return color;
    }*/
    //Function to draw a rectangle with 10x10 dimensions with colour blue
    //parameter of type graphics
    public void draw(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x, y , 10, 10);
    }
    public void undraw(Graphics g){
        g.clearRect(x, y, 10,10);
    }
}


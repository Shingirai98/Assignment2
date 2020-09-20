import java.awt.*;

//Rect class that stores the square locations of mouse clicks

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Rect extends RecursiveAction{
    Flow f ;
    static Terrain landData;
    static Water wa;
    private static int THRESHOLD;
    public ArrayList<Integer> sub;
    public int startx;
    public int starty;
    public int stopx;
    public int stopy;

    public Rect(Terrain landData){
        this.landData = landData;
        this.wa = wa;
    }
    public Rect(ArrayList<Integer> sub){
        this.sub = sub;
    }
    public void Kick(){
        ForkJoinPool pool = new ForkJoinPool();
        Rect one = new Rect(landData);
        pool.invoke(one);

    }
    @Override
    protected void compute() {
        THRESHOLD = 256* 256/4;
        if(landData.dim() > THRESHOLD ){
            //Divide
            int mid = 256*256/2;
            Rect first = new Rect(landData);
            Rect second = new Rect(landData);

            first.fork();
            second.compute();
            first.join();
        }
        else{
            //Sequential
            startx = 0;
            starty = 0;
            stopx = 256;
            stopy = 256;
            //Flow.player(landData, startx, starty, stopx, stopy);


        }

    }
}
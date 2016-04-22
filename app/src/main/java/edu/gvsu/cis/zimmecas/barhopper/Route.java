package edu.gvsu.cis.zimmecas.barhopper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Casey on 4/21/2016.
 */
public class Route implements Serializable {
    ArrayList<Bar> route;
    String name;

    public Route(String n){
        name = n;
        route = new ArrayList<>();
    }

    public void addBar(Bar b){
        route.add(b);
    }

    public ArrayList<Bar> getRoute(){
        return route;
    }

    private void moveUp(int index){
        Bar temp;
        if (index == 0){
            return;
        } else {
            temp = route.get(index -1);
            route.add(index - 1, route.get(index));
            route.add(index, temp);
        }
    }

    private void moveDown(int index){
        Bar temp;
        if (index == route.size() -1){

        }
    }


}

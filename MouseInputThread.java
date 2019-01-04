package sample;

import java.awt.*;
import java.util.ArrayList;

import static sample.Controller.coordinates;

public class MouseInputThread extends Thread {

    public void run(){

        while(true) {
            PointerInfo placeholder = MouseInfo.getPointerInfo();
            //should check for duplicates
            coordinates.add(placeholder);
        }
    }

}

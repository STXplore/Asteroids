/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;
import java.awt.*;
/**
 *
 * @author gannis013
 */
public class Asteroid extends Polygohn {
    
    public Asteroid() {
        super(new Point[] { new Point(-5, -5), new Point(-5, 5), new Point(5, 5), new Point(5, -5) }, new Point(Math.random() * 800, Math.random() *600), Math.random()*360);
        
        accelX = Math.random()*3 - 1.5;
        accelY = Math.random()*3 - 1.5;
    }
    public void paint(Graphics brush) {
        Point[] points = getPoints();
        int[] x = new int[points.length];
        int[] y = new int[points.length];
        
        for(int i = 0; i < points.length; i++) {
            x[i] = (int)points[i].x;
            y[i] = (int)points[i].y;
        }
        brush.drawPolygon(x, y, points.length);
    }
    
    public void move() {
        position.x+= accelX;
        position.y+= accelY;
        
        if(position.x > 805) {
            position.x = -5;
        }
        if(position.x < -5) {
            position.x = 805;
        }
        
        if(position.y > 600) {
            position.y = 0;
        }
        if(position.y < 0) {
            position.y = 600;
        }
        
        if((int)(Math.random()*1000) + 1 == 1) {
            accelX = Math.random()*3 - 1.5;
            accelY = Math.random()*3 - 1.5;
        }
    }
    
    public boolean checkTouch(Ship ship) {
        Point[] p = ship.getPoints();
        
        Point[] m = getPoints();
        
        for(Point g : p) {
            if(contains(g)) {
                return true;
            }
        }
        for(Point g : m) {
            if(ship.contains(g)) {
                return true;
            }
        }

        return false;
    }
}

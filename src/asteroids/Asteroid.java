/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;
import java.awt.*;
import java.util.*;
/**
 *
 * @author gannis013
 */
public class Asteroid extends Polygon {
    private boolean broken;
    
    public Asteroid() {
        super(new Point[] { new Point(-10, -10), new Point(-10, 10), new Point(10, 10), new Point(10, -10) }, new Point(Math.random() * 800, Math.random() *600), Math.random()*360);
       
        accelX = Math.random()*3 - 1.5;
        accelY = Math.random()*3 - 1.5;
        
        broken = false;
    }
    public Asteroid(Asteroid a) {
        super(new Point[] { new Point(-6, -6), new Point(-6, 6), new Point(6, 6), new Point(6, -6) }, new Point(a.findCenter().x + Math.random()*5, a.findCenter().y + Math.random()*5), Math.random()*360);
       
        accelX = Math.random()*3 - 1.5;
        accelY = Math.random()*3 - 1.5;
        
        broken = true;
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
    }
    
    //Asteroid/Ship collision
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
    
    public boolean contains(Bullet b) {
        if(contains(new Point(b.x, b.y))) {
            return true;
        } else {
            return false;
        }
    }
    
    //Asteroid/Bullet collison
    public boolean checkTouch(ArrayList<Bullet> bullets) {
        for(Bullet b : bullets) {
            if(contains(b)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean wasBroken() { return broken; }
}

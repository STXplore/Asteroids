package asteroids;

/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Asteroids extends Game {
    private Ship ship;
    private ArrayList<Asteroid> asteroids;
    private final int numAsteroids = 16;
    private final int numStars = 50;
    private final int bulletDelay = 120;
    private ArrayList<Bullet> bullets;
    private ArrayList<Star> stars;
    private long lastFire;
    
  public Asteroids() {
    super("A Steroids!",800,600);
    
    //Initialize stuff fully
    asteroids = new ArrayList<Asteroid>();
    
    bullets = new ArrayList<Bullet>();
    
    stars = new ArrayList<Star>();
    
    ship = new Ship();
    
    lastFire = System.currentTimeMillis();
    
    this.addKeyListener(ship);
    
    //Generate asteroids
    for(int i = 0; i < numAsteroids; i++) {
        asteroids.add(new Asteroid());
    }
    
    for(int i = 0; i < numStars; i++) {
        stars.add(new Star());
    }
  }
  
  public void paint(Graphics brush) {
    brush.setColor(Color.black);
    brush.fillRect(0,0,width,height);

    brush.setColor(Color.white);
    
    //Anything ship related
    if(ship !=  null) {
        
        ship.paint(brush);
        ship.move();
        
        //Ship tries to shoot, checks delay
        if(ship.shoot()) {
            if(System.currentTimeMillis() - lastFire > bulletDelay) {
                bullets.add(new Bullet(ship));
                lastFire = System.currentTimeMillis();
            }
        }
    }
    
    if(bullets !=  null) {
        for(int i =0; i < bullets.size(); i++) {
            bullets.get(i).move();
            bullets.get(i).paint(brush);
            
            if(bullets.get(i).x > width || bullets.get(i).x < 0 || bullets.get(i).y > height || bullets.get(i).y < 0) {
                bullets.remove(i);
                i--;
            }
        }
    }
    
    if(asteroids != null) {
        for(int i = 0; i < asteroids.size(); i++) {
            if(asteroids.get(i) != null) {
                asteroids.get(i).move();
                asteroids.get(i).paint(brush);
                
                //IMPORTANT
                if(i < asteroids.size()) {
                    if(asteroids.get(i).checkTouch(ship)) {
                        
                        //This is what happens when an asteroid touches the ship
                    
                        
                        
                        asteroids.remove(i);
                        i--;
                        ship.lives--;
                        //ship = null;
                    }
                }
                if(i < asteroids.size()) {
                    if(asteroids.get(i).checkTouch(bullets)) {
                        
                        //If an asteroid touches ANY bullet
                    
                        asteroids.remove(i);
                        i--;
                        System.gc();
                        break;
                    }
                }
            }
        }
    }
    
    if(stars != null) {
        for(Star s : stars) {
            s.paint(brush);
        }
    }
    
    brush.fillRect(10, 10, 20, 20);
    this.repaint();
  }
  
  public static void main (String[] args) {
    new Asteroids();
  }
}
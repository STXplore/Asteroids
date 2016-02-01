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
    private final int numAsteroids = 6;
    private final int numStars = 60;
    private final int bulletDelay = 150;
    private ArrayList<Bullet> bullets;
    private ArrayList<Star> stars;
    private long lastFire, timeCleared;
    
    private int level;
    
  public Asteroids() {
    super("A Steroids!",800,600);
    
    //Initialize stuff fully
    asteroids = new ArrayList<Asteroid>();
    
    bullets = new ArrayList<Bullet>();
    
    stars = new ArrayList<Star>();
    
    ship = new Ship(3);
    
    lastFire = System.currentTimeMillis();
    
    level = 1;
    
    this.addKeyListener(ship);
    
    //Generate asteroids
    generate();
    
    for(int i = 0; i < numStars; i++) {
        stars.add(new Star());
    }
  }
  
  public void paint(Graphics brush) {
    brush.setColor(Color.black);
    brush.fillRect(0,0,width,height);

    brush.setColor(Color.white);
    brush.drawString("Level : " + level, 50, 525);
    
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
    
    //Otherwise when you hit one life everything is red
    brush.setColor(Color.WHITE);
    
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
            if(i < asteroids.size()) {
            if(asteroids.get(i) != null) {
                asteroids.get(i).move();
                asteroids.get(i).paint(brush);
                
                //IMPORTANT
                if(i < asteroids.size() && i >= -1) {
                    if(asteroids.get(i).checkTouch(ship)) {
                        
                        //This is what happens when an asteroid touches the ship
                    
                        //Asteroids will not break when touching the ship because it instantly destroys the ship
                        asteroids.remove(i);
                        i--;
                        ship.lives--;
                        //ship = null;
                    }
                }
                if(i < asteroids.size() && i >= 0) {
                    if(asteroids.get(i).checkTouch(bullets)) {
                        
                        //If an asteroid touches ANY bullet
                    
                        if(asteroids.get(i).wasBroken() == false && level*Math.random() / 2 > 2) {
                            asteroids.add(new Asteroid(asteroids.get(i)));
                            asteroids.add(new Asteroid(asteroids.get(i)));
                        }
                        
                        asteroids.remove(i);
                        i--;
                        System.gc();
                        break;
                    }
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
    
    if(asteroids.isEmpty()) {
        level++;
        timeCleared = System.currentTimeMillis();
        ship.reset();
        
        //doesnt work properly
        clearBullets();
        generate();
    }
    if(System.currentTimeMillis() - 2000 < timeCleared) {
        brush.drawString("Level " + (level - 1) + " Clear", 350, 400);
    }
    this.repaint();
  }
  
  public static void main (String[] args) {
    new Asteroids();
  }
  
  //Makes asteroids based on base and level
  public void generate() {
      for(int i = 0; i < numAsteroids + level*2; i++) {
        asteroids.add(new Asteroid());
    }
  }
  
  public void clearBullets() {
      bullets.clear();
  }
}
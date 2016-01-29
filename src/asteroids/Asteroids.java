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
    private Asteroid[] asteroids;
    private final int numAsteroids = 12;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
  public Asteroids() {
    super("A Steroids!",800,600);
    
    asteroids = new Asteroid[numAsteroids];
    
    ship = new Ship();
    
    this.addKeyListener(ship);
    
    //Generate asteroids
    for(int i = 0; i < numAsteroids; i++) {
        asteroids[i] = new Asteroid();
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
        ship.shoot(brush);
    }
    
    if(asteroids != null) {
        for(Asteroid a : asteroids) {
            if(a != null) {
                a.move();
                a.paint(brush);
                if(a.checkTouch(ship)) {
                    //IMPORTANT
                    
                    for(int i = 50; i <= 750; i += 50) {
                        for(int j = 50; j <= 550; j += 50) {
                            brush.setColor(Color.WHITE);
                            brush.drawString("LOSE", i, j);
                        }
                    }
                    
                    
                    //ship = null;
                }
            }
        }
    }
    
    brush.fillRect(10, 10, 20, 20);
    this.repaint();
  }
  
  public static void main (String[] args) {
    new Asteroids();
  }
}
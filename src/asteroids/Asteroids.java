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
    private final int numStars = 560;
    private final int bulletDelay = 170;
    private ArrayList<Bullet> bullets;
    private ArrayList<Star> stars;
    private long lastFire, timeCleared;
    private int level, score;
    private SoundEffect sound;
    
  public Asteroids() {
    super("Asteroids - Griffin Annis",800,600);
    
    //Initialize stuff fully
    asteroids = new ArrayList<Asteroid>();
    
    bullets = new ArrayList<Bullet>();
    
    stars = new ArrayList<Star>();
    
    ship = new Ship(2); //starts at 3 because asteroids starts at level 1 after 0
    
    lastFire = System.currentTimeMillis();
    
    level = 0;
    
    //sound.THEME.play();
    
    this.addKeyListener(ship);
    
    //Generate asteroids
    generate();
    
    for(int i = 0; i < numStars; i++) {
        stars.add(new Star());
    }
  }
  
  public void paint(Graphics brush) {
    brush.setColor(Color.DARK_GRAY);
    brush.fillRect(0,0,width,height);
    if(ship != null && ship.shotgun) {
        for(int y = (int)(Math.random()*15); y < 600; y+= 40*Math.random()) {
            Color color = new Color((int)(Math.random()*255 + 1), (int)(Math.random()*255 + 1), (int)(Math.random()*255 + 1));
            for(int i = (int)(-15*Math.random()); i < 800; i+=70*Math.random()) {
                brush.setColor(color);
                brush.drawString("CASUL", i, y);
            }
        }
    }
    if(stars != null) {
        for(Star s : stars) {
            s.move(ship);
            s.paint(brush);
        }
    }

    brush.setColor(Color.white);
    brush.drawString("Level : " + level, 50, 525);
    brush.drawString("Points : " + score, 200, 550);
    brush.drawString("Controls: WASD, E to shoot, Q to brake", 150, 525);
    //Anything ship related
    if(ship !=  null) {
        
        ship.paint(brush);
        ship.move();
        
        //Ship tries to shoot, checks delay
        if(ship.shoot()) {
            if(System.currentTimeMillis() - lastFire > bulletDelay) {
                bullets.add(new Bullet(ship));
                sound.SHOOT.play();
                lastFire = System.currentTimeMillis();
                ship.accelX -= ship.posMove().x*1.8;
                ship.accelY -= ship.posMove().y*1.8;
                if(ship.shotgun) {
                    bullets.add(new Bullet(ship, 0 , 1, Color.RED));
                    bullets.add(new Bullet(ship, 0 , -1, Color.BLUE));
                    bullets.add(new Bullet(ship, 1 , 0, Color.GREEN));
                    bullets.add(new Bullet(ship, -1 , 0, Color.YELLOW));
                    ship.accelX -= ship.posMove().x*6;
                    ship.accelY -= ship.posMove().y*6;
                }
            }
        }
    }
    
    //sound.THEME.loop();
    
    //Otherwise when you hit one life everything is red
    brush.setColor(Color.WHITE);
    
    if(bullets !=  null) {
        for(int i =0; i < bullets.size(); i++) {
            bullets.get(i).move();
            bullets.get(i).paint(brush);
            
            if(bullets.get(i).x > width || bullets.get(i).x < 0 || bullets.get(i).y > height || bullets.get(i).y < 0) {
                if(!bullets.get(i).crossed) {
                    if(bullets.get(i).x > 805) {
                        bullets.get(i).x = 0;
                        bullets.get(i).crossed = true;
                    }
                    if(bullets.get(i).x < -5) {
                        bullets.get(i).x = 800;
                        bullets.get(i).crossed = true;
                    }
        
                    if(bullets.get(i).y > 600) {
                        bullets.get(i).y = 0;
                        bullets.get(i).crossed = true;
                    }
                    if(bullets.get(i).y < 0) {
                        bullets.get(i).y = 600;
                        bullets.get(i).crossed = true;
                    }
                } else {
                        bullets.remove(i);
                        i--;
                    }
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
                        sound.EXPLODE.play();
                        ship.lives--;
                        score--;
                        //ship = null;
                    }
                }
                if((i < asteroids.size() || i == 0) && i >= 0) {
                    if(asteroids.get(i).checkTouch(bullets)) {
                        
                        //If an asteroid touches ANY bullet
                    
                        if(asteroids.get(i).wasBroken() == false && level*Math.random() / 2 > 2) {
                            asteroids.add(new Asteroid(asteroids.get(i)));
                            asteroids.add(new Asteroid(asteroids.get(i)));
                        }
                        sound.EXPLODE.play();
                        asteroids.remove(i);
                        score++;
                        i--;
                    }
                }
            }
        }
        }
    }
    
    if(asteroids.isEmpty()) {
        System.gc();
        level++;
        timeCleared = System.currentTimeMillis();
        
        //doesnt work properly
        clearBullets();
        generate();
    }
    if(System.currentTimeMillis() - 2000 < timeCleared) {
        if(level > 1) brush.drawString("Level " + (level - 1) + " Clear", 350, 400);
    }
    
    if(ship.lives > 0) {
        ship.notLost = true;
    }
    
    //sound.THEME.loop();
    this.repaint();
  }
  
  public static void main (String[] args) {
    new Asteroids();
  }
  
  //Makes asteroids based on base and level
  public final int distance = 100;
  public void generate() {
    if(level != 0) {    
        for(int i = 0; i < numAsteroids + level*2; i++) {
            asteroids.add(new Asteroid());
            
            //Asteriods cannot next to ship
            while((asteroids.get(i).position.x < (ship.position.x + distance) && asteroids.get(i).position.x > (ship.position.x - distance)) && (asteroids.get(i).position. y < (ship.position.y + distance) && asteroids.get(i).position.y > (ship.position.y - distance))) {
                asteroids.get(i).position.x = Math.random() * 800;
                asteroids.get(i).position.y = Math.random() *600;
            }
        }
    }
  }
  
  public void clearBullets() {
      bullets.clear();
  }
}
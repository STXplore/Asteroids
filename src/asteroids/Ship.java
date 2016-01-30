package asteroids;

import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author gannis013
 */
public class Ship extends Polygon implements KeyListener {
    private boolean w, a, s, d, q, shoot;
    
    private final double maxAcc = 10;
    
    private final double accelRate = .05;
    
    private final int rotateSpeed = 3;
    
    public int lives;
    
    public Ship() {
        super(new Point[] { new Point(0, 10), new Point(5, 0), new Point(0, -10), new Point(30, 0)} , new Point(400, 300), 0.0);
        accelX = 0;
        accelY = 0;
        lives = 3;
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
        brush.drawString("MPH : " + Math.round(Math.sqrt(Math.pow(accelX, 2) + Math.pow(accelY, 2)) * 50), 50, 550);
        brush.drawString("Lives : " + lives, 150, 550);
    }
    public void move() {
        
        if(a == true) {
            rotation-= rotateSpeed;
            if(rotation < 0) {
                rotation += 360;
            }
        }
        if(d == true) {
            rotation+= rotateSpeed;
            if(rotation > 360) {
                rotation -= 360;
            }
        }
        if(s == true) {
        //Rewrite max accel for x and y (both s and w) to account for rotation    
        accelX -= posMove().x;
        accelY -= posMove().y;
        if(accelX > maxAcc) accelX = maxAcc;
        if(accelY > maxAcc) accelY = maxAcc;
        
        if(accelX < -maxAcc) accelX = -maxAcc;
        if(accelY < -maxAcc) accelY = -maxAcc;
        }
        if(w == true) {
        accelX += posMove().x;
        accelY += posMove().y;
        if(accelX > maxAcc) accelX = maxAcc;
        if(accelY > maxAcc) accelY = maxAcc;
        
        if(accelX < -maxAcc) accelX = -maxAcc;
        if(accelY < -maxAcc) accelY = -maxAcc;
        }
        if(q == true) {
            accelX /= 1.03;
            accelY /= 1.03;
            if(accelX < .3 && accelX > -.3) { accelX = 0; }
            if(accelY < .3 && accelY > -.3) { accelY = 0; }
        }
        
        
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

        //position.x+=pull.x - position.x;
        //position.y+=pull.y - position.y;
        position.x+= accelX;
        position.y-= accelY;
        
        
    }
    //Supports wasd, WASD
    public void keyTyped(KeyEvent ke) {
        if(ke.getKeyChar() == 'w' || ke.getKeyChar() == 'W') {
            w = true;
        }
        if(ke.getKeyChar() == 'a' || ke.getKeyChar() == 'A') {
            a = true;
        }
        if(ke.getKeyChar() == 's' || ke.getKeyChar() == 'S') {
            s = true;
        }
        if(ke.getKeyChar() == 'd' || ke.getKeyChar() == 'D') {
            d = true;
        }
        if(ke.getKeyChar() == 'q' || ke.getKeyChar() == 'Q') {
            q = true;
        }
        if(ke.getKeyChar() == 'e' || ke.getKeyChar() == 'E') {
            shoot = true;
        }
    }

    public void keyPressed(KeyEvent ke) {
        
    }

    public void keyReleased(KeyEvent ke) {
        if(ke.getKeyChar() == 'w' || ke.getKeyChar() == 'W') {
            w = false;
        }
        if(ke.getKeyChar() == 'a' || ke.getKeyChar() == 'A') {
            a = false;
        }
        if(ke.getKeyChar() == 's' || ke.getKeyChar() == 'S') {
            s = false;
        }
        if(ke.getKeyChar() == 'd' || ke.getKeyChar() == 'D') {
            d = false;
        }
        if(ke.getKeyChar() == 'q' || ke.getKeyChar() == 'Q') {
            q = false;
        }
        if(ke.getKeyChar() == 'e' || ke.getKeyChar() == 'E') {
            shoot = false;
        }
    }
    
    public Point posMove() {
        return new Point(Math.cos(rotation * (Math.PI / 180))*accelRate, -Math.sin(rotation * (Math.PI / 180))*accelRate);
        
    }
    
    public boolean shoot() {
       return shoot;
    }
    
}

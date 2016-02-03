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
public class Bullet extends Circle {
    public final double speed = 50;
    
    public Bullet(Ship ship) {
        super(ship.position.x, ship.position.y, 3);
        this.x = ship.position.x;
        this.y = ship.position.y;
        Point point = ship.posMove();
        this.accelX = point.x*speed;
        this.accelY = point.y*speed;
        //base bullets don't cross
        crossed = true;
    }
    public Bullet(Ship ship, int x, int y, Color c) {
        super(ship.position.x, ship.position.y, 3);
        this.x = ship.position.x;
        this.y = ship.position.y;
        Point point = ship.posMove();
        this.accelX = point.x*speed + x;
        this.accelY = point.y*speed + y;
        this.color = c;
        //casul bullets cross once
        crossed = false;
    }
}

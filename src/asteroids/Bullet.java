/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

/**
 *
 * @author gannis013
 */
public class Bullet extends Circle {
    public double x, y;
    
    public Bullet(Ship ship) {
        super(ship.position.x, ship.position.y, 3);
        this.x = ship.position.x;
        this.y = ship.position.y;
        Point point = ship.posMove();
        this.accelX = point.x;
        this.accelY = point.y;
    }
}

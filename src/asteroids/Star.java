/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

/**
 *
 * @author Griffin
 */
public class Star extends Circle {

    public Star() {
        super(Math.random() * 1000 - 100, Math.random() * 800 - 100, 1);
    }
    
    public void move(Ship ship) {
        accelX = -ship.accelX/7;
        accelY = -ship.accelY/7;
        super.move();
        if (x > 900) {
                x = -100;
            }
            if (x < -100) {
                x = 900;
            }

            if (y > 750) {
                y = -100;
            }
            if (y < -100) {
                y = 750;
            }
    }
    
}

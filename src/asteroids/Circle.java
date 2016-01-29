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
public abstract class Circle {
    public int x, y, r;
    
    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r =r;
    }
    
    public void paint(Graphics brush) {
        brush.drawOval(x, y, r, r);
    }
}

package asteroids;
import java.awt.*;
/**
 *
 * @author gannis013
 */
public class Circle {
    public double x, y, r;
    public double accelX, accelY;
    public boolean crossed;
    
    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        crossed = false;
    }
    
    public void paint(Graphics brush) {
        brush.drawOval((int)(x + r), (int)(y + r), (int)r, (int)r);
    }
    
    public void move() {
        //Only used in bullet but whatever
        x+=accelX;
        y-=accelY;
        
    }
}

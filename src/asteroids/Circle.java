package asteroids;
import java.awt.*;
/**
 *
 * @author gannis013
 */
public abstract class Circle {
    public double x, y, r;
    public double accelX, accelY;
    
    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r =r;
    }
    
    public void paint(Graphics brush) {
        brush.drawOval((int)x, (int)y, (int)r, (int)r);
    }
    
    public void move() {
        
        x+=accelX;
        y+=accelY;
        
    }
}

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
    public Color color;
    
    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        crossed = false;
        color = Color.WHITE;
    }
    
    public void paint(Graphics brush) {
        brush.setColor(color);
        brush.drawOval((int)(x + r), (int)(y + r), (int)r, (int)r);
        brush.setColor(Color.WHITE);
    }
    
    public void move() {
        //Only used in bullet but whatever
        x+=accelX;
        y-=accelY;
        
    }
}

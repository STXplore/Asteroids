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
        super(Math.random() * 800, Math.random() * 600, 1);
    }
    
}

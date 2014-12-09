

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import java.awt.geom.AffineTransform;

public class Craft {

    private String craft = "voiture.png";

    private int dx, ddx;
    private int dy, ddy;
    private int x;
    private int y;
    private Image image;

    public Craft() {
        ImageIcon ii = new ImageIcon("C:\\voiture.png");
        image = ii.getImage();
        x = 0;
        y = 0;
        dx=0;
        dy=0;        
    }


    public void move() {    	
    	dx+= ddx;
    	if (dx>2) {
    		dx=2;
    	}
    	if (dx<-2){
    		dx=-2;
    	}    	
    	dy+= ddy;
    	if (dy>2){
    		dy=2;
    	}
    	if (dy<-2){
    		dy=-2;
    	}

        x += dx;
        y += dy;
        

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            ddx = -1;            
        }

        if (key == KeyEvent.VK_RIGHT) {
            ddx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            ddy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            ddy = 1;
        }
        
        if (key == KeyEvent.VK_R){
        	x=10;
        	y=10;
        	dx=0;
        	dy=0;
        	ddx=0;
        	ddy=0;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            ddx = 0;
            dx=0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            ddx = 0;
            dx=0;
        }

        if (key == KeyEvent.VK_UP) {
            ddy = 0;
            dy=0;
        }

        if (key == KeyEvent.VK_DOWN) {
            ddy = 0;
            dy=0;
        }
    }
}
package Craft;



import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import java.awt.geom.AffineTransform;

public class Craft {

    private String craft = "C://voiture_h.png";

    private int dx, ddx;
    private int dy, ddy;
    private int vmax=5; int a=1; 
    private int x;
    private int y;
    private Image image;

    public Craft() {
        ImageIcon ii = new ImageIcon("C://voiture_h.png");
        image = ii.getImage();
     // The initial place of the car
        x = 550;
        y = 250;
        // The initial speed
        dx=0;
        dy=0;        
    }


    public void move() {  
    	
    	if (ddx==1)
    		dx += a;
    	
    	if (ddx==0 && dx>0)
    		dx -= a;
    	
    	if (ddx==-1)
    		dx -=a;
    	
    	if (ddx==0 && dx<0)
    		dx +=a;
    	
    	if (ddy==1)
    		dy +=a;
    	
    	if (ddy==0 && dy>0)
    		dy -=a;
    	
    	if (ddy==-1)
    		dy -=a;
    	
    	if (ddy==0 && dy<0)
    		dy +=a;	
    	
    	
    	
    	if (dx>vmax) 
    		dx=vmax;
    	
    	if (dx<-vmax)
    		dx=-vmax;
    	    	
    	if (dy>vmax)
    		dy=vmax;
    	
    	if (dy<-vmax)
    		dy=-vmax;
    	
    	
        x += dx;
        y += dy;
        
        if (dx>0){
        	if(dy>0){
        		ImageIcon ii = new ImageIcon("C://voiture_bd.png");
        		image = ii.getImage();
        	}
        	if(dy<0){
        		ImageIcon ii = new ImageIcon("C://voiture_hd.png");
        		image = ii.getImage();
        	}
        	if(dy==0){
        		ImageIcon ii = new ImageIcon("C://voiture_d.png");
        		image = ii.getImage();
        	}
        }
        
        if (dx<0){
        	if(dy>0){
        		ImageIcon ii = new ImageIcon("C://voiture_bg.png");
        		image = ii.getImage();
        	}
        	if(dy<0){
        		ImageIcon ii = new ImageIcon("C://voiture_hg.png");
        		image = ii.getImage();
        	}
        	if(dy==0){
        		ImageIcon ii = new ImageIcon("C://voiture_g.png");
        		image = ii.getImage();
        	}
        }
        
        if (dx==0){
        	if(dy>0){
        		ImageIcon ii = new ImageIcon("C://voiture_b.png");
        		image = ii.getImage();
        	}
        	if(dy<0){
        		ImageIcon ii = new ImageIcon("C://voiture_h.png");
        		image = ii.getImage();
        	}        	
        } 
        
        if (x<=0 || x>=1300){
        	if(dy>0){
        		ImageIcon ii = new ImageIcon("C://voiture_b.png");
        		image = ii.getImage();
        	}
        	if(dy<0){
        		ImageIcon ii = new ImageIcon("C://voiture_h.png");
        		image = ii.getImage();
        	}        	
        }
        

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
        	x=550;
        	y=250;
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
        }

        if (key == KeyEvent.VK_RIGHT) {
            ddx = 0;            
        }

        if (key == KeyEvent.VK_UP) {
            ddy = 0;            
        }

        if (key == KeyEvent.VK_DOWN) {
            ddy = 0;            
        }
    }
}
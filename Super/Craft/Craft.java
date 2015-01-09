package Craft;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.ImageIcon;


public class Craft {

    private String craft = "C://voiture_h.png";

    private int dx, ddx;
    private int dy, ddy;
    private int vmax=5; int a=1; 
    private int x;
    private int y;
    private Image image;
    
//    private int listenDecesion;

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
    	
//    	setListenDecesion(1);
    	
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
        
        // To keep the car within the limit of board. 
        x=(x>1200?1200:x);
        x=(x<100?100:x);
        y=(y<100?100:y);
        y=(y>500?500:y);
       
        // These codes have same functions like above, we keep it here just in case of future usage
        /*
        if (x>1200){
        	x=1200;
        	dx=0;
        	ddx=0;
        }
       if (x<100){
        	x=100;
        	dx=0;
        	ddx=0;
        }
       if (y>500){
       	y=500;
       	dy=0;
       	ddy=0;
       }
       
      if (y<100){
       	y=100;
       	dy=0;
       	ddy=0;
       }
       
       */
        	
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    /* These functions are used to stop the action of key listener. 
     * we keep it here because after changer our strategy of controlling car, we may reuse it.
     * 
    private void setListenDecesion(int e)
    {
    	listenDecesion = e;
    }

    public int getListenDecesion()
    {
    	return listenDecesion;
    	
    }
    
    */
    
    
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

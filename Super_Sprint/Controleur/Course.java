package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import Modele.Car;
import Modele.Circuit;
import Vue.Window;




public class Course implements ActionListener{
	
	private Timer timer;
	private Car car;
	private Circuit circuit;
	private Window window;	
	private int ddx; int ddy;
	
	
	public static void main(String[] args) {
		new Window();
		new Course();
	}

	public Course() {
		timer = new Timer(5,this);
        timer.start();
	}

	
	public void actionPerformed(ActionEvent e) {		
		car.move(ddx,ddy);
        window.repaint();         
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
	        	car.setPosition(550,250);	        	
	        	car.setSpeed(0,0);
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

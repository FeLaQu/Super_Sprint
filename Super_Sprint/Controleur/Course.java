package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Modele.Car;
import Modele.Circuit;
import Vue.Window;




public class Course implements ActionListener {
	
	private Timer timer;
	private Circuit circuit;
	private static Car car;
	private Window window;	
	private int  ddx; int ddy;
	
	
	public static void main(String[] args) {
		new Course();		
	}

	public Course() {
		car= new Car(1, new int[] {300,300});
		window= new Window(car, this);			
		
		timer = new Timer(5,this);
        timer.start();
	}

	
	public void actionPerformed(ActionEvent evt) {
	          car.move(ddx,ddy);
	          window.repaint();
	}
	 
	public void setddx(int x){
		ddx = x;
	}
	
	public void setddy(int y){
		ddy = y;
	}	 

}

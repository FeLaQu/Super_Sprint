package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Modele.Car;
import Modele.Circuit;
import Vue.VCar;
import Vue.Window;




public class Course implements ActionListener {
	
	private Timer timer;
	private static Circuit circuit;
	private static Car car;
	private Window window;	
	private int  ddx; int ddy;
	
	
	public static void main(String[] args) {
		new Course();				
	}

	public Course() {
		car= new Car(1, new int[] {600,120});
		circuit= new Circuit(1);
		window= new Window(car, circuit, this);			
		
		timer = new Timer(5,this);
        timer.start();
	}

	
	public void actionPerformed(ActionEvent evt) {		
		VCar view_car= window.getBoard().getVCar();
		int w = view_car.getWidth();
		int h = view_car.getHeight(); 
		
		car.move(ddx,ddy, circuit, w, h);	// car.move uses the width and the height of view_car to check if it gets out of the circuit	
        window.repaint();
	}
	 
	public void setddx(int x){
		ddx = x;
	}
	
	public void setddy(int y){
		ddy = y;
	}	
	 

}

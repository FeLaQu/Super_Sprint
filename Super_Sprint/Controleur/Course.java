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
    
    private long time_init=System.currentTimeMillis(); // initial time of the race
    private long current_time= System.currentTimeMillis();
    private boolean checkpoint=false; // a checkpoint to check if the car has done the whole lap
    private int lap=0; // number of laps done

    private int dt;
    int dn; // results of the keyboard listening
	    // dt: a move parallel to the speed vector
	    // dn: a move orthogonal to the speed vector

    public static void main(String[] args) {
	new Course();
    }

    public Course() {
		car = new Car(2, new int[] { 600, 120 });
		circuit = new Circuit(1);
		window = new Window(car, circuit, this);
	
		timer = new Timer(1, this);
		timer.start();
    }

    public void actionPerformed(ActionEvent evt) {
		VCar view_car = window.getBoard().getVCar();
		int w = view_car.getWidth();
		int h = view_car.getHeight();
	
		car.move(dt, dn, circuit, w, h); // car.move uses the width and height
						 // of view_car to check if the car
						 // gets out of the circuit
		window.repaint();		
		
		if (car.getPosition()[0]>595 && car.getPosition()[0]<605){ 
			 if (checkpoint && car.getPosition()[1]<350){ // if we cross the start line and we crossed the checkpoint				 
				 checkpoint=false;
				 lap++;
				 long lap_time=System.currentTimeMillis()- current_time;				 
				 System.out.println("Lap "+ lap + ":" + lap_time + "ms");
				 current_time=System.currentTimeMillis();
				 if (lap==3){ // we stop the race after 3 laps
					 timer.stop();
					 window.dispose();
					 long total_time=current_time-time_init;
					 System.out.println("Total time: "+ total_time+ "ms");					 
				 }
			 }
			 else if (car.getPosition()[1]>350){ // if we cross the checkpoint
				 checkpoint=true;
			 }			
		}
    }

    public void setdt(int x) {
    	dt = x;
    }

    public void setdn(int y) {
    	dn = y;
    }
    
    public void initTime(){
    	time_init=System.currentTimeMillis();
    	current_time= System.currentTimeMillis();
    	lap=0;
    	checkpoint=false;
    }

}

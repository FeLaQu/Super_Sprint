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
    private int nbr_player=0; //number of players (1 or 2)
    private static Car[] cars; // a vector with one or two cars
    private Window window;
    
    private long time_init=System.currentTimeMillis(); // initial time of the race
    private long current_time= System.currentTimeMillis();
    private boolean checkpoint=false; // a checkpoint to check if the car has done the whole lap
    private int lap=0; // number of laps done

    private int dt0; int dt1;
    int dn0; int dn1; // results of the keyboard listening
	    // dt: a move parallel to the speed vector
	    // dn: a move orthogonal to the speed vector

    public static void main(String[] args) {
    	Menu menu = new Menu();
    	while (menu.isActive()){}
    	int nbr_player=menu.getNbrPlayer();
    	new Course(1,nbr_player);
    }

    public Course(int  circuit_id, int nbr_player) {     	
    	    	   	
    	this.nbr_player=nbr_player;
    	cars= new Car[nbr_player];
    	
    	if (nbr_player==1){    		
    		cars[0] = new Car(1, new int[] { 600, 100 });
    	}
    	
    	else if (nbr_player==2){    		
    		cars[0]=new Car(1, new int[] { 600, 100 });
    		cars[1]=new Car(2, new int[] { 600, 140 });
    	}
		
		circuit = new Circuit(circuit_id);
		window = new Window(cars, circuit, this);
	
		timer = new Timer(10, this);
		timer.start();
    }

    public void actionPerformed(ActionEvent evt) {
		VCar[] view_cars = window.getBoard().getVCar();
		
		if (nbr_player==1){
			int w = view_cars[0].getWidth();
			int h = view_cars[0].getHeight();
			
			cars[0].move(dt0, dn0, circuit, w, h); // car.move uses the width and height
						 // of view_car to check if the car
						 // gets out of the circuit
				
			if (cars[0].getPosition()[0]>595 && cars[0].getPosition()[0]<605){ 
				 if (checkpoint && cars[0].getPosition()[1]<350){ // if we cross the start line and we crossed the checkpoint				 
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
				 else if (cars[0].getPosition()[1]>350){ // if we cross the checkpoint
					 checkpoint=true;
				 }			
			}
		}
		
		else if (nbr_player==2){
			int w0 = view_cars[0].getWidth();
			int h0 = view_cars[0].getHeight();			
			int w1 = view_cars[1].getWidth();
			int h1 = view_cars[1].getHeight();
			
			cars[0].move(dt0, dn0, circuit, w0, h0);
			cars[1].move(dt1, dn1, circuit, w1, h1);
			
		}			
			
		
		window.repaint();
    }

    public void setdt0(int x) {
    	dt0 = x;
    }

    public void setdn0(int y) {
    	dn0 = y;
    }
    
    public void setdt1(int x) {
    	dt1= x;
    }

    public void setdn1(int y) {
    	dn1 = y;
    }
    
    public void initTime(){
    	time_init=System.currentTimeMillis();
    	current_time= System.currentTimeMillis();
    	lap=0;
    	checkpoint=false;
    }
    
    public int getNbrPlayer(){
    	return nbr_player;
    }    
    
}

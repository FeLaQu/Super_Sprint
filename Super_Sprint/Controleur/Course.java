package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Modele.Car;
import Modele.Circuit;
import Vue.VCar;
import Vue.Window;
/**
 * This is the main class of the program. The "Course" method creates the cars, the window
 * and the timer. The "actionPerformed" method makes the cars move at each iteration (in the model) 
 * and repaint the window (in the view).
 */
public class Course implements ActionListener {

	private Timer timer;
	private static Circuit circuit;
	private int nbr_player=0; //number of players (1 or 2 or 4)
	private static Car[] cars; // a vector with one or two cars or four cars
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
		int circuit_id= menu.getCircuitId();
		int car_ID_onep=menu.getCarIDoneP();
		int carone_ID_twop=menu.getCarIDPonetwoP();	
		int cartwo_ID_twop=menu.getCarIDPtwotwoP();
		new Course(circuit_id,nbr_player,car_ID_onep,carone_ID_twop,cartwo_ID_twop);
	}

	public Course(int  circuit_id, int nbr_player, int car_ID_onep,int carone_ID_twop,int cartwo_ID_twop) {     	

		this.nbr_player=nbr_player;
		circuit = new Circuit(circuit_id);
		cars= new Car[nbr_player];

		if (nbr_player==1){    		
			cars[0] = new Car(car_ID_onep, circuit.getInit_Position()[0]);
		}

		else if (nbr_player==2){    		
			cars[0]=new Car(carone_ID_twop, circuit.getInit_Position()[0]);
			cars[1]=new Car(cartwo_ID_twop, circuit.getInit_Position()[1]);
		}

		else if (nbr_player==4){
			cars[0]=new Car(1, circuit.getInit_Position()[0]);
			cars[1]=new Car(2, circuit.getInit_Position()[1]);
			cars[2]=new Car(3, circuit.getInit_Position()[2]);
			cars[3]=new Car(4, circuit.getInit_Position()[3]);
		}

		window = new Window(cars, circuit, this);

		timer = new Timer(10, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent evt) {
		VCar[] view_cars = window.getBoard().getVCar();

		if (nbr_player==1){
			int w = view_cars[0].getWidth();
			int h = view_cars[0].getHeight();

			cars[0].move(dt0, dn0, circuit, w, h,null, 0, 0); // car.move uses the width and height
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

			cars[0].move(dt0, dn0, circuit, w0, h0, cars[1], w1, h1);
			cars[1].move(dt1, dn1, circuit, w1, h1, cars[0], w0, h0);

		}			

		else if (nbr_player==4){
		//	int w0 = view_cars[0].getWidth();
			//int h0 = view_cars[0].getHeight();			
			//int w1 = view_cars[1].getWidth();
			//int h1 = view_cars[1].getHeight();
			//int w2 = view_cars[2].getWidth();
			//int h2 = view_cars[2].getHeight();
			//int w3 = view_cars[3].getWidth();
			//int h3 = view_cars[3].getHeight();

			//cars[0].move(dt0, dn0, circuit, w0, h0);
			//cars[1].move(dt1, dn1, circuit, w1, h1);
			//		cars[2].move(dt2, dn2, circuit, w2, h2);
			//		cars[3].move(dt3, dn3, circuit, w3, h3);

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
	
	public Car[] getCars(){
		return this.cars ;
	}

}

package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Modele.Car;
import Modele.Circuit;
import Vue.VCar;
import Vue.Window;

/**
 * This is the main class of the program. The "Course" method creates the cars, the window and the timer. The
 * "actionPerformed" method makes the cars move at each iteration (in the model) and repaint the window (in the view).
 */
public class Course implements ActionListener {

    private Timer timer;
    private static Circuit circuit;
    private int nbr_player = 0; // number of players (1 or 2 or 4)
    private static Car[] cars; // a vector with one or two cars or four cars
    private Window window;

    private long time_init = System.currentTimeMillis(); // initial time of the race
    private long current_time = System.currentTimeMillis();
    private boolean checkpoint = false; // a checkpoint to check if the car has done the whole lap
    private int lap = 0; // number of laps done
    
    private int [] car_lap;

    public int[][] initPosition;
    public int x_init;
    public int y_init;
    public int[] symPosition;
    public boolean[] checkinitPoint;

    private int dt0;
    int dt1;
    int dn0;
    int dn1; // results of the keyboard listening

    // dt: a move parallel to the speed vector
    // dn: a move orthogonal to the speed vector

    /**
     * The main method launches the menu, gets the parameters chosen by the player in the menu and then create a Course
     * object with these parameters. After that, all the work is done by Course.
     */
    public static void main(String[] args) {
	Menu menu = new Menu();
	while (menu.isActive()) {
	}
	int nbr_player = menu.getNbrPlayer();
	int circuit_id = menu.getCircuitId();
	int car_ID_onep = menu.getCarIDoneP();
	int carone_ID_twop = menu.getCarIDPonetwoP();
	int cartwo_ID_twop = menu.getCarIDPtwotwoP();
	new Course(circuit_id, nbr_player, car_ID_onep, carone_ID_twop, cartwo_ID_twop);
    }

    /**
     * The Course constructor creates the cars, the circuit. It then creates a Timer and starts it.
     */

    public Course(int circuit_id, int nbr_player, int car_ID_onep, int carone_ID_twop, int cartwo_ID_twop) {

	this.nbr_player = nbr_player;
	circuit = new Circuit(circuit_id);
	cars = new Car[nbr_player];

	if (nbr_player == 1) {
	    cars[0] = new Car(car_ID_onep, circuit.getInit_Position()[0]);
	}

	else if (nbr_player == 2) {
	    cars[0] = new Car(carone_ID_twop, circuit.getInit_Position()[0]);
	    cars[1] = new Car(cartwo_ID_twop, circuit.getInit_Position()[1]);
	}

	else if (nbr_player == 4) {
	    cars[0] = new Car(1, circuit.getInit_Position()[0]);
	    cars[1] = new Car(2, circuit.getInit_Position()[1]);
	    cars[2] = new Car(3, circuit.getInit_Position()[2]);
	    cars[3] = new Car(4, circuit.getInit_Position()[3]);
	}

	initPosition = circuit.getInit_Position();
	
	checkinitPoint = new boolean[nbr_player];
	car_lap=new int [nbr_player];
	
	x_init=initPosition[0][0];
	y_init=initPosition[0][1];
	
	symPosition = new int[] { (1200 - x_init), (700 - y_init) };
	for (int i = 0; i < nbr_player; i++) {
	    checkinitPoint[i] = false;
	    car_lap[i] =0;
	}


	
	
	window = new Window(cars, circuit, this);

	timer = new Timer(10, this);
	timer.start();

    }

    /**
     * The "actionPerformed" method is called every 10ms by the Timer. It is the core of the program. At each call, it
     * modifies the cars in the model with the method "move" and then call a window.repaint() to update the graphics.
     * 
     * This method has another goal. It is to force the driver to run complete laps to win the game. 
     * It is kind of an anti-cheating system which works with checkpoints.
     */

    public void actionPerformed(ActionEvent evt) {
	VCar[] view_cars = window.getBoard().getVCar();

	if (nbr_player == 1) {
	    int w = view_cars[0].getWidth();
	    int h = view_cars[0].getHeight();

	    cars[0].move(dt0, dn0, circuit, w, h, null, 0, 0);
	    /*
	     * We define two checkpoints for each circuit. 
	     * One is the initial place of a car the other will be the middle point of Symmetry
	     * 
	     * For example, if the initial point is (642,90) then the other is (1200-642, 700-90)
	     * 
	     */
	    if (cars[0].getPosition()[0] < x_init) {
		if (checkinitPoint[0] && cars[0].getPosition()[1] < (y_init + 100)
			&& cars[0].getPosition()[1] > (y_init - 100)) { // if we cross the start line and
									// we crossed the checkpoint
		    checkinitPoint[0] = false;
		    lap++;
		    double lap_time = (System.currentTimeMillis() - current_time)/1000;
		    System.out.println("Lap " + lap + ":" + lap_time + "s");
		    current_time = System.currentTimeMillis();
		    if (lap == 2) { // we stop the race after 2 laps
			timer.stop();
			window.dispose();
			long total_time = (current_time - time_init)/1000;
			System.out.println("Total time: " + total_time + "s");
		    }
		}
	    }

	    if (cars[0].getPosition()[0] > symPosition[0]) { // if we cross the checkpoint
		if (cars[0].getPosition()[1] < (symPosition[1] + 100)
			&& cars[0].getPosition()[1] > (symPosition[1] - 100)) { // if we cross the start line and we //										// crossed the checkpoint
		    checkinitPoint[0] = true;
		}
	    }
	}

	else if (nbr_player == 2) {
	    
	    int w0 = view_cars[0].getWidth();
		int h0 = view_cars[0].getHeight();			
		int w1 = view_cars[1].getWidth();
		int h1 = view_cars[1].getHeight();

		cars[0].move(dt0, dn0, circuit, w0, h0, cars[1], w1, h1);
		cars[1].move(dt1, dn1, circuit, w1, h1, cars[0], w0, h0);

	    for (int p = 0; p < nbr_player; p++) { // executed 2 times here
		
		if (cars[p].getPosition()[0] < x_init) {

		    if (checkinitPoint[p] && cars[p].getPosition()[1] < (y_init + 100)
			    && cars[p].getPosition()[1] > (y_init - 100)) { // if we cross the start line and
									    // we crossed the checkpoint
			checkinitPoint[p] = false;
			car_lap[p]++;
			long lap_time = (System.currentTimeMillis() - current_time)/1000;
			System.out.println("Car"+p+" Lap " + car_lap[p] + ":" + lap_time + "s");
			current_time = System.currentTimeMillis();
			if (car_lap[p] == 2) { // we stop the race after 3 laps
			    timer.stop();
			    window.dispose();
			    long total_time = (current_time - time_init)/1000;
			    System.out.println("Car "+p+" has won the race and its total time is: " + total_time + "s");
			}
		    }

		}

		if (cars[p].getPosition()[0] > symPosition[0]) { // if we cross the checkpoint
		    if (cars[p].getPosition()[1] < (symPosition[1] + 100)
			    && cars[p].getPosition()[1] > (symPosition[1] - 100)) { // if we cross the start line and we
			  						    // crossed the checkpoint
			checkinitPoint[p] = true;
		    }
		}

	    }

	}

	else if (nbr_player == 4) {

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
	dt1 = x;
    }

    public void setdn1(int y) {
	dn1 = y;
    }
       
    
    

    public void initTime() {
	time_init = System.currentTimeMillis();
	current_time = System.currentTimeMillis();
	lap = 0;
	checkpoint = false;
    }

    public int getNbrPlayer() {
	return nbr_player;
    }

}

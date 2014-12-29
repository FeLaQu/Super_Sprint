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

    private int dt;
    int dn; // results of the keyboard listening
	    // dt: a move parallel to the speed vector
	    // dn: a move orthogonal to the speed vector

    public static void main(String[] args) {
	new Course();
    }

    public Course() {
	car = new Car(1, new int[] { 600, 120 });
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
    }

    public void setdt(int x) {
	dt = x;
    }

    public void setdn(int y) {
	dn = y;
    }

}

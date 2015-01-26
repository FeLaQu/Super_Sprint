package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import Controleur.Course;
import Modele.Car;
import Modele.Circuit;

/**
 * This class is the JPanel object. It is created by "Window" and 
 * creates the VCar and VCircuit objects.
 * This class contains the method "paint" which is responsible for refreshing the
 * window at each iteration.
 * It also contains the "TAdapter" class which reads the keyboard entries and send it
 * to "Course".
 */

public class JBoard extends JPanel {

	private VCar[] view_cars; // a vector with the same length as "cars"
	private VCircuit view_circuit;
	private Course course;
	private Car[] cars;
	private int nbr_player;

	public JBoard(Car[] cars, Circuit circuit, Course course) {

		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);     

		this.nbr_player= course.getNbrPlayer();        
		this.view_cars= new VCar[nbr_player];

		if (nbr_player==1){
			view_cars[0]= new VCar(cars[0]);
		}
		if (nbr_player==2){
			view_cars[0]= new VCar(cars[0]);
			view_cars[1]= new VCar(cars[1]);
		}


		this.view_circuit= new VCircuit(circuit);
		this.course=course;
		this.cars=cars;
	}


	/**
	 * The following method is the implementation of the "paint" method.
	 */

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D)g;

		g2d.drawImage(view_circuit.getImage(), 0,0, null);

		if (nbr_player==1){        
			this.view_cars[0].pivote();        
			g2d.drawImage(view_cars[0].getImage(), view_cars[0].getX(), view_cars[0].getY(),this);  
		}

		else if (nbr_player==2){
			this.view_cars[0].pivote();  
			this.view_cars[1].pivote();  
			g2d.drawImage(view_cars[0].getImage(), view_cars[0].getX(), view_cars[0].getY(),this);  
			g2d.drawImage(view_cars[1].getImage(), view_cars[1].getX(), view_cars[1].getY(),this);  
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}    

	/**
	 * The following class reads the keyboard entries. 
	 */

	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				course.setdn0(0);            
			}

			if (key == KeyEvent.VK_RIGHT) {
				course.setdn0(0);            
			}

			if (key == KeyEvent.VK_UP) {
				course.setdt0(0);            
			}

			if (key == KeyEvent.VK_DOWN) {
				course.setdt0(0);         
			}

			if (key == KeyEvent.VK_S) {
				course.setdn1(0);            
			}

			if (key == KeyEvent.VK_F) {
				course.setdn1(0);            
			}

			if (key == KeyEvent.VK_E) {
				course.setdt1(0);            
			}

			if (key == KeyEvent.VK_D) {
				course.setdt1(0);         
			}
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				course.setdn0(-1);          
			}

			if (key == KeyEvent.VK_RIGHT) {
				course.setdn0(1);
			}

			if (key == KeyEvent.VK_UP) {
				course.setdt0(1);
			}

			if (key == KeyEvent.VK_DOWN) {
				course.setdt0(-1);
			}

			if (key == KeyEvent.VK_R){            	
				cars[0].setInit();
				if (nbr_player==2){
					cars[1].setInit();
				}            		
				course.initTime();            	
			}

			if (key == KeyEvent.VK_S) {
				course.setdn1(-1);            
			}

			if (key == KeyEvent.VK_F) {
				course.setdn1(1);            
			}

			if (key == KeyEvent.VK_E) {
				course.setdt1(1);            
			}

			if (key == KeyEvent.VK_D) {
				course.setdt1(-1);         
			}


		}
	}

	public VCar[] getVCar(){
		return this.view_cars;
	}

}

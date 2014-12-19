package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Controleur.Course;
import Modele.Car;
import Modele.Circuit;


public class JBoard extends JPanel {
	
	private VCar view_car;
	private VCircuit view_circuit;
	private Course course;
	private Car car;
	
    public JBoard(Car car, Circuit circuit, Course course) {
    	
    	addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);     
              
           
        this.view_car= new VCar(car);
        this.view_circuit= new VCircuit(circuit);
        this.course=course;
        this.car=car;
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(view_circuit.getImage(), 0,0, null);
        
        this.view_car.rotate();        
        g2d.drawImage(view_car.getImage(), view_car.getX(), view_car.getY(),this);                                        

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }    
    
    
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT) {
	            course.setddx(0);            
	        }
	
	        if (key == KeyEvent.VK_RIGHT) {
	            course.setddx(0);            
	        }
	
	        if (key == KeyEvent.VK_UP) {
	            course.setddy(0);            
	        }
	
	        if (key == KeyEvent.VK_DOWN) {
	            course.setddy(0);         
	        }
        }

        public void keyPressed(KeyEvent e) {
        	int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                course.setddx(-1);          
            }

            if (key == KeyEvent.VK_RIGHT) {
                course.setddx(1);
            }

            if (key == KeyEvent.VK_UP) {
                course.setddy(-1);
            }

            if (key == KeyEvent.VK_DOWN) {
                course.setddy(1);
            }
            
            if (key == KeyEvent.VK_R){
            	car.setPosition(600,120);	        	
            	car.setSpeed(0,0);
            	course.setddx(0);
            	course.setddy(0);
            	view_car.setImage(new ImageIcon("C://voiture_g.png").getImage());
            }
        }
    }

}

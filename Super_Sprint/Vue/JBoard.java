package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;


public class JBoard extends JPanel {
	
	private VCar view_car;
	private VCircuit view_circuit;
	
    public JBoard() {
    	
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);

        this.view_car= new VCar();
        this.view_circuit= new VCircuit();        
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(view_circuit.getImage(), 0,0, null);
        
        g2d.drawImage(view_car.getImage(), view_car.getX(), view_car.getY(),this);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }

}

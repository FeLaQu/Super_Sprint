package Vue;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import Controleur.Course;
import Modele.Car;
import Modele.Circuit;


public class Window extends JFrame {
	
	private JBoard  board;

	public Window(Car car, Circuit circuit, Course course) {
		
		board = new JBoard(car, circuit, course);
		add(board);
				
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setTitle("Le jeu qui tue, le vrai");
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(false);
	}
	
	public void repaint(Graphics g){
		board.paint(g);
	}
	
}

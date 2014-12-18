package Vue;


import java.awt.Graphics;

import javax.swing.JFrame;

import Controleur.Course;
import Modele.Car;


public class Window extends JFrame {
	
	private JBoard  board;

	public Window(Car car, Course course) {
		
		board = new JBoard(car, course);
		add(board);

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

package Vue;


import javax.swing.JFrame ;


public class Window extends JFrame {

	public Window() {
		add(new JBoard());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setTitle("Le jeu qui tue, le vrai");
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(false);
	}

	public static void main(String[] args) {
		new Window();        

	}

}

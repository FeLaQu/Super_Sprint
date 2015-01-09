package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{
	
	private Course course;
	private JPanel pan= new JPanel();
	private JButton player_1= new JButton("1 player");
	private JButton player_2= new JButton("2 players");
	private int nbr_player=0;
	
	public Menu(){		
		
		setTitle("Menu");
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(300,100);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pan.add(player_1);
		pan.add(player_2);
		setContentPane(pan);
		
		player_1.addActionListener(this);
		player_2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){	
		
		if (e.getSource()==player_1){
			nbr_player=1;
			dispose();			
		}
		
		if (e.getSource()==player_2){
			nbr_player=2;
			dispose();			
		}
	}
	
	public int getNbrPlayer(){
		return nbr_player;
	}
}

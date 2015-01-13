package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{
	
	private JPanel pan= new JPanel();
	private JPanel pan2= new JPanel();
	private JButton player_1= new JButton("1 player");
	private JButton player_2= new JButton("2 players");
	private JButton player_4= new JButton("4 players");
	private JButton circuit_1= new JButton("Circuit 1");
	private JButton circuit_2= new JButton("Circuit 2");
	private int nbr_player=0;
	private int circuit_id=0;
	
	public Menu(){		
		
		setTitle("Menu");
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(300,100);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pan.add(player_1);
		pan.add(player_2);
		pan.add(player_4);
		getContentPane().add(pan);
		
		player_1.addActionListener(this);
		player_2.addActionListener(this);
		player_4.addActionListener(this);
		
		pan2.add(circuit_1);
		pan2.add(circuit_2);
		circuit_1.addActionListener(this);
		circuit_2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){	
		
		if (e.getSource()==player_1){
			nbr_player=1;
			getContentPane().removeAll();
			getContentPane().add(pan2);	
			validate();
		}
		
		if (e.getSource()==player_2){
			nbr_player=2;
			getContentPane().removeAll();
			getContentPane().add(pan2);
			validate();
		}
		
		if (e.getSource()==player_4){
			nbr_player=4;
			getContentPane().removeAll();
			getContentPane().add(pan2);
			validate();
		}
		
		if (e.getSource()==circuit_1){
			circuit_id=1;
			dispose();			
		}
		
		if (e.getSource()==circuit_2){
			circuit_id=2;
			dispose();			
		}
	}
	
	public int getNbrPlayer(){
		return nbr_player;
	}
	
	public int getCircuitId(){
		return circuit_id;
	}
}

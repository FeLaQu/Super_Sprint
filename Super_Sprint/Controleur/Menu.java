package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{

	private JPanel pan= new JPanel(); // 1, 2 or 4 players
	private JPanel pan2= new JPanel(); // choice of map
	private JPanel pan3= new JPanel(); // choice of car for 1P mode
	private JPanel pan4= new JPanel(); // choice of car for 1P for 2P mode
	private JPanel pan5= new JPanel(); // choice of car for 2P for 2P mode
	private JButton player_1= new JButton("1 player");
	private JButton player_2= new JButton("2 players");
	private JButton player_4= new JButton("4 players");
	private JButton circuit_1= new JButton("Circuit 1");
	private JButton circuit_2= new JButton("Circuit 2");
	private JButton onep_voiture_rouge = new JButton("Voiture rouge");
	private JButton onep_voiture_jaune = new JButton("Voiture Jaune");
	private JButton onep_avion = new JButton("Avion");
	private JButton onep_fusee = new JButton("Fusée");
	private JButton twop_pone_voiture_rouge = new JButton("Voiture rouge");
	private JButton twop_pone_voiture_jaune = new JButton("Voiture Jaune");
	private JButton twop_pone_avion = new JButton("Avion");
	private JButton twop_pone_fusee = new JButton("Fusée");
	private JButton twop_ptwo_voiture_rouge = new JButton("Voiture rouge");
	private JButton twop_ptwo_voiture_jaune = new JButton("Voiture Jaune");
	private JButton twop_ptwo_avion = new JButton("Avion");
	private JButton twop_ptwo_fusee = new JButton("Fusée");
	private int nbr_player=0;
	private int circuit_id=0; 	// The map that is chosen
	private int car_ID_onep=0;	// The ID of the chosen car for 1Player
	private int carone_ID_twop=0;	// The ID of the car chosen by player 1 in 2P
	private int cartwo_ID_twop=0;	// The ID of the car chosen by player 2 in 2P

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

		pan3.add(onep_voiture_rouge);
		pan3.add(onep_voiture_jaune);
		pan3.add(onep_avion);
		pan3.add(onep_fusee);

		onep_voiture_rouge.addActionListener(this);
		onep_voiture_jaune.addActionListener(this);
		onep_avion.addActionListener(this);
		onep_fusee.addActionListener(this);

		pan4.add(twop_pone_voiture_rouge);
		pan4.add(twop_pone_voiture_jaune);
		pan4.add(twop_pone_avion);
		pan4.add(twop_pone_fusee);

		twop_pone_voiture_rouge.addActionListener(this);
		twop_pone_voiture_jaune.addActionListener(this);
		twop_pone_avion.addActionListener(this);
		twop_pone_fusee.addActionListener(this);

		pan5.add(twop_ptwo_voiture_rouge);
		pan5.add(twop_ptwo_voiture_jaune);
		pan5.add(twop_ptwo_avion);
		pan5.add(twop_ptwo_fusee);

		twop_ptwo_voiture_rouge.addActionListener(this);
		twop_ptwo_voiture_jaune.addActionListener(this);
		twop_ptwo_avion.addActionListener(this);
		twop_ptwo_fusee.addActionListener(this);


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
			if (nbr_player==1){
				getContentPane().removeAll();
				getContentPane().add(pan3);
			}
			else if (nbr_player==2){
				getContentPane().removeAll();
				getContentPane().add(pan4);
			}
			else if (nbr_player==4){
				dispose();
			}
			validate();
		}

		if (e.getSource()==circuit_2){
			circuit_id=2;
			if (nbr_player==1){
				getContentPane().removeAll();
				getContentPane().add(pan3);
			}
			else if (nbr_player==2){
				getContentPane().removeAll();
				getContentPane().add(pan4);
			}		
			validate();
		}

		if(e.getSource()==onep_voiture_rouge){
			car_ID_onep=1;
			dispose();
		}
		if(e.getSource()==onep_voiture_jaune){
			car_ID_onep=2;
			dispose();
		}
		if(e.getSource()==onep_avion){
			car_ID_onep=3;
			dispose();
		}
		if(e.getSource()==onep_fusee){
			car_ID_onep=4;
			dispose();
		}

		if(e.getSource()==twop_pone_voiture_rouge){
			carone_ID_twop = 1;
			getContentPane().removeAll();
			getContentPane().add(pan5);
			validate();
		}
		if(e.getSource()==twop_pone_voiture_jaune){
			carone_ID_twop = 2;
			getContentPane().removeAll();
			getContentPane().add(pan5);
			validate();
		}
		if(e.getSource()==twop_pone_avion){
			carone_ID_twop = 3;
			getContentPane().removeAll();
			getContentPane().add(pan5);
			validate();
		}
		if(e.getSource()==twop_pone_fusee){
			carone_ID_twop = 4;
			getContentPane().removeAll();
			getContentPane().add(pan5);
			validate();
		}

		if(e.getSource()==twop_ptwo_voiture_rouge){
			cartwo_ID_twop = 1;
			dispose();
		}
		if(e.getSource()==twop_ptwo_voiture_jaune){
			cartwo_ID_twop = 2;
			dispose();
		}
		if(e.getSource()==twop_ptwo_avion){
			cartwo_ID_twop = 3;
			dispose();
		}
		if(e.getSource()==twop_ptwo_fusee){
			cartwo_ID_twop = 4;
			dispose();
		}

	}

	public int getNbrPlayer(){
		return nbr_player;
	}

	public int getCircuitId(){
		return circuit_id;
	}
	
	public int getCarIDoneP(){
		return car_ID_onep;	// The ID of the chosen car for 1Player
	}
	
	public int getCarIDPonetwoP(){
		return carone_ID_twop;	// The ID of the car chosen by player 1 in 2P
	}
	
	public int getCarIDPtwotwoP(){
		return cartwo_ID_twop ;
	}
}

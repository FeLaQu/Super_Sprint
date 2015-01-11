package Modele;

import java.io.File;
import java.util.Scanner;

public class Circuit {

	private int ID;
	private int[][] matrice= new int[1200][700];// x between 0 and 1199 (from left to
		// right)
		// y between 0 and 699 (from up to
		// down)
	
	private int[][] init_position; // A 2X2 matrix for the initial position of the two cars

	public Circuit(int id) {
		
		if (id == 1) { // we import here the matrix for the circuit number 1
			// import matrix of graylevel circuit
			try {
				Scanner img = new Scanner(new File("C://out.pgm"));
				img.next(); // P2
				img.next(); // 1200
				img.next(); // 700
				img.next(); // 255
				for (int j = 0; j < 700; j++) {
					for (int i = 0; i < 1200; i++) {
						int num = Integer.parseInt(img.next());
						if (num == 255) {
							matrice[i][j] = 1;
						} else {
							matrice[i][j] = 0;
						}
					}
				}
				img.close();
			} catch (Exception yolo) {
				System.out.println("Ficher non trouve");
			}
			
			init_position= new int[][] {{625,100},{625,140}};
		}

		else if (id == 2) { // we import here the matrix for the circuit number 2
			// import matrix of graylevel circuit
			try {
				Scanner img = new Scanner(new File("C://cuircuit8.pgm"));
				img.next(); // P2
				img.next(); // 1200
				img.next(); // 700
				img.next(); // 255
				for (int j = 0; j < 700; j++) {
					for (int i = 0; i < 1200; i++) {
						int num = Integer.parseInt(img.next());
						if (num == 255) {
							matrice[i][j] = 1;
						} else {
							matrice[i][j] = 0;
						}
					}
				}
				img.close();
			} catch (Exception yolo2) {
				System.out.println("Ficher 2 non trouve");
			}
			init_position= new int[][] {{600,30},{600,70}};
		}


		this.ID = id;		
	}

	public int getValue(int i, int j) {
		return this.matrice[i][j];
	}

	public int getID() {
		return this.ID;
	}

	public int[][] getInit_Position(){
		return this.init_position;
	}
}
package Modele;

import java.io.File;
import java.util.Scanner;

/**
 * This class codes the model of the circuit. A circuit is represented by a 1200*700 matrix,
 * each point on the speed way is 0 and each point out is 1. The constructor reads a pgm file
 * to build the matrix with 0 and 1.
 */

public class Circuit {

	private int ID;
	private int[][] matrice= new int[1200][700];// x between 0 and 1199 (from left to
	// right)
	// y between 0 and 699 (from up to down)

	private int[][] init_position; // A 2X2 matrix for the initial position of the two cars

	public Circuit(int id) {

		if (id == 1) { // we import here the matrix for the circuit number 1
			// import matrix of graylevel circuit
			try {
				Scanner img = new Scanner(new File("out.pgm"));
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

			init_position= new int[][] {{642,90},{642,150}};
		}

		else if (id == 2) { // we import here the matrix for the circuit number 2
			// import matrix of graylevel circuit
			try {
				Scanner img = new Scanner(new File("circuit8.pgm"));
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
			init_position= new int[][] {{510,30},{510,70}};
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
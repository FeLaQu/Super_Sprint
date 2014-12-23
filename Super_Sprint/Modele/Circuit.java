package Modele;

import java.io.File;
import java.util.Scanner;



public class Circuit{

	private int ID ;
	private int[][] matrice ;


	public Circuit(int id) {

		int[][] mat = new int[1200][700]; // x between 0 and 1199 (from left to right)
										// y between 0 and 699 (from up to down)

		if (id==1){			// we import here the matrix for the circuit number 1
			               // import matrix of graylevel circuit
			try{
				Scanner img = new Scanner(new File("C://out.pgm"));
				img.next(); // P2
				img.next(); // 1200
				img.next(); //700
				img.next(); //255
				for (int j = 0 ; j < 700 ; j++){
					for (int i=0 ; i < 1200 ; i++){					
						int num = Integer.parseInt(img.next());
						if (num == 255){
							mat[i][j] = 1 ;
						}
						else {
							mat[i][j] = 0 ;
						}
					}
				}
				img.close();
			} catch (Exception yolo){
				System.out.println("Ficher non trouve");
			}
		}
		this.ID = id ;
		this.matrice = mat ;
	}


	public int getValue(int i , int j){
		return this.matrice[i][j] ;
	}
	
	public int getID(){
		return this.ID ;
	}
	
	
}
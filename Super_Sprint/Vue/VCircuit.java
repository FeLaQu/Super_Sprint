package Vue;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Modele.Circuit;


public class VCircuit {
	
	private Image image;
	private String link;
	
	public Image getImage(){
		return image;
	}

	public VCircuit(Circuit circuit) {
		if (circuit.getID()==1){
			link="out.png";
		}
		else if (circuit.getID()==2){
			link="cuircuit8.png";
		}
		
		try {	         
			image = ImageIO.read(new File(link));
	        } catch (IOException ex) {             
	            System.out.println(ex);
	        }
	}

}

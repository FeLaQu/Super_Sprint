package Vue;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Modele.Circuit;


public class VCircuit {
	
	private Image image;
	
	public Image getImage(){
		return image;
	}

	public VCircuit(Circuit circuit) {
		
		try { 
	         
			image = ImageIO.read(new File("C://out.png"));
	        } catch (IOException ex) {             
	            System.out.println(ex);
	        }
	}

}

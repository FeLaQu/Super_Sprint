package Vue;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Modele.Circuit;
/**
 * This class is responsible for the visual presentation of the map.
 * It just looks for the map's ID, and get it's png image.
 * This is the wallpaper of this map, and is loaded once, at the opening of the window.
 * @author Laurent
 *
 */

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
			link="circuit8.png";
		}
		
		/**
		 * First we right the name of the file in the String "link", and then we load
		 * it  thanks to Image.IO.read : image contains the png picture.
		 */
		
		try {	         
			image = ImageIO.read(new File(link));
	        } catch (IOException ex) {             
	            System.out.println(ex);
	        }
	}

}

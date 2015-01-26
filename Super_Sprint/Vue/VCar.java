package Vue;


import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Modele.Car;

/**
 * Same as for VCircuit, this class is responsible for the visual aspect of the cars.
 * It uses the same method, getting the car's ID and the linking it to a png file.
 * @author Laurent
 *
 */

public class VCar {

	private Car car;
	private Image image;	
	private String link;

	public VCar(Car car){
		this.car=car;
		if (car.getID()==1){
			link= "Red_car.png";
		}
		else if (car.getID()==2){
			link="Yellow_car.png";
		}
		else if (car.getID()==3){
			link="avion.png";
		}
		else if (car.getID()==4){
			link="fusee.png";
		}
		try { 	         
			image = ImageIO.read(new File(link));
		} catch (IOException ex) {             
			System.out.println("Ficher image voiture non trouvé");
		}			
	}


	public Image getImage(){
		return image;
	}

	public int getWidth(){
		try {
			Image img = (Image) ImageIO.read(new File(link));
			return img.getWidth(null);
		} catch (IOException e) {			
			e.printStackTrace();
			return 0;
		}
	}

	public int getHeight(){
		try {
			Image img = (Image) ImageIO.read(new File(link));
			return img.getHeight(null);
		} catch (IOException e) {			
			e.printStackTrace();
			return 0;
		}
	}

	public void setImage(Image image){
		this.image=image;
	}

	public int getX(){		
		return car.getPosition()[0] - image.getWidth(null)/2;	// to center the view of the car around its position	
	}

	public int getY(){		
		return car.getPosition()[1]- image.getHeight(null)/2; // to center the view of the car around its position
	}

	/**
	 * The following method rotate the image of a car d'un certain angle.
	 */

	public static BufferedImage rotate(BufferedImage image, double angle) {
		double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
		int w = image.getWidth(), h = image.getHeight();
		int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
		GraphicsConfiguration gc = getDefaultConfiguration();
		BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
		Graphics2D g = result.createGraphics();
		g.translate((neww-w)/2, (newh-h)/2);
		g.rotate(angle, w/2, h/2);
		g.drawRenderedImage(image, null);
		g.dispose();
		return result;
	}

	public static GraphicsConfiguration getDefaultConfiguration() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		return gd.getDefaultConfiguration();
	}

	/**
	 * The method "pivote" updates the image by aligning it with the orientation vector.
	 */
	public void pivote(){
		try { 	         
			BufferedImage img = ImageIO.read(new File(link));
			double angle=-car.getBeta();			

			BufferedImage img1= rotate(img, angle);
			image= (Image)img1;
		} catch (IOException ex) {             
			System.out.println(ex);
		}    	
	} 


}

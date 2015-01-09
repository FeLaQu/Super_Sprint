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



public class VCar {
	
	private Car car;
	private Image image;	
	private String link;
	
	public VCar(Car car){
		this.car=car;
		if (car.getID()==1){
			link= "C:\\Red_car.png";
		}
		else if (car.getID()==2){
			link="C:\\Yellow_car.png";
		}
		try { 	         
			image = ImageIO.read(new File(link));
	        } catch (IOException ex) {             
	            System.out.println(ex);
	        }			
	}
	
	
	public Image getImage(){
		return image;
	}
	
	public int getWidth(){
		return image.getWidth(null);
	}
	
	public int getHeight(){
		return image.getHeight(null);
	}
	
	public void setImage(Image image){
		this.image=image;
	}
	
	public int getX(){		
		return car.getPosition()[0] - getWidth()/2;	// to center the view of the car around its position	
	}
	
	public int getY(){		
		return car.getPosition()[1]- getHeight()/2; // to center the view of the car around its position
	}
	
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

package Vue;

import java.awt.Image;

import javax.swing.ImageIcon;

import Modele.Car;



public class VCar {
	
	private Car car;
	private Image image;
	private int width;
	private int height;
	
	public VCar(Car car){
		this.car=car;
		ImageIcon ii = new ImageIcon("C://voiture_g.png");
		image = ii.getImage();
		width= image.getWidth(null);
		height= image.getHeight(null);
	}
	
	
	public Image getImage(){
		return image;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setImage(Image image){
		this.image=image;
	}
	
	public int getX(){		
		return car.getPosition()[0] - width/2;	// to center the view of the car around its position	
	}
	
	public int getY(){		
		return car.getPosition()[1]- height/2; // to center the view of the car around its position
	}
	
	public void rotate(){ // depends on the sign of speed[0] and speed[1]
		if (car.getSpeed()[0]>0){ 
        	if(car.getSpeed()[1]>0){
        		ImageIcon ii = new ImageIcon("C://voiture_bd.png");
        		image = ii.getImage();
        	}
        	if(car.getSpeed()[1]<0){
        		ImageIcon ii = new ImageIcon("C://voiture_hd.png");
        		image = ii.getImage();
        	}
        	if(car.getSpeed()[1]==0){
        		ImageIcon ii = new ImageIcon("C://voiture_d.png");
        		image = ii.getImage();
        	}
        }
        
        if (car.getSpeed()[0]<0){
        	if(car.getSpeed()[1]>0){
        		ImageIcon ii = new ImageIcon("C://voiture_bg.png");
        		image = ii.getImage();
        	}
        	if(car.getSpeed()[1]<0){
        		ImageIcon ii = new ImageIcon("C://voiture_hg.png");
        		image = ii.getImage();
        	}
        	if(car.getSpeed()[1]==0){
        		ImageIcon ii = new ImageIcon("C://voiture_g.png");
        		image = ii.getImage();
        	}
        }
        
        if (car.getSpeed()[0]==0){
        	if(car.getSpeed()[1]>0){
        		ImageIcon ii = new ImageIcon("C://voiture_b.png");
        		image = ii.getImage();
        	}
        	if(car.getSpeed()[1]<0){
        		ImageIcon ii = new ImageIcon("C://voiture_h.png");
        		image = ii.getImage();
        	}        	
        }
	}

}

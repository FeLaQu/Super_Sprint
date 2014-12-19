package Vue;

import java.awt.Image;

import javax.swing.ImageIcon;

import Modele.Car;



public class VCar {
	
	private Car car;
	private Image image;
	
	public VCar(Car car){
		this.car=car;
		ImageIcon ii = new ImageIcon("C://voiture_g.png");
		image = ii.getImage(); 
	}
	
	
	public Image getImage(){
		return image;
	}
	
	
	public void setImage(Image image){
		this.image=image;
	}
	
	public int getX(){
		return car.getPosition()[0];
	}
	
	public int getY(){
		return car.getPosition()[1];
	}
	
	public void rotate(){
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

package Modele;



import Vue.VCar;



public class Car extends VCar{

	private int ID ;		//Identification of the car
	private int[] position ; //A 2x1 vector for the current position
	private int[] speed ;  //IDEM 
	private int[] acceleration ; // IDEM
	private int max_speed ;		// Characteristic of the car
	private int max_acceleration ;	//IDEM
	private float maniability ; //IDEM
	
	
	public Car(int id, int[] position) {		
		this.ID = id ;
		this.position = position ;
		this.speed = new int[] {0,0} ;
		this.acceleration = new int[] {0,0} ;
		// Two cars for now, just for the idea
		if (id == 1){
			this.max_speed = 5;
			this.max_acceleration = 1;
			this.maniability = 1;
		}
		if (id == 2){
			this.max_speed = 4;
			this.max_acceleration = 2;
			this.maniability = 2;
		}

		
	}
	
	public int getID(){
		return this.ID ;
	}
	
	public int[] getPosition(){
		return this.position ;
	}
	
	public int[] getSpeed(){
		return this.speed ;
	}
	
	public int[] getAcceleration(){
		return this.acceleration ;
	}
	
	public int getMax_speed(){
		return this.max_speed ;
	}
	
	public int getMax_acceleration(){
		return this.max_acceleration ;
	}

	public float getManiability(){
		return this.maniability ;
	}
	
	public void setPosition(int x, int y){
		position[1]=x;
		position[2]=y;
	}
	
	public void setSpeed(int x, int y){
		speed[1]=x;
		speed[2]=y;
	}

	public void move(int ddx, int ddy){
		if (ddx==1)
    		speed[1] += max_acceleration;
    	
    	if (ddx==0 && speed[1]>0)
    		speed[1] -= max_acceleration;
    	
    	if (ddx==-1)
    		speed[1] -= max_acceleration;
    	
    	if (ddx==0 && speed[1]<0)
    		speed[1] += max_acceleration;
    	
    	if (ddy==1)
    		speed[2] += max_acceleration;
    	
    	if (ddy==0 && speed[2]>0)
    		speed[2] -= max_acceleration;
    	
    	if (ddy==-1)
    		speed[2] -= max_acceleration;
    	
    	if (ddy==0 && speed[2]<0)
    		speed[2] += max_acceleration;	
    	
    	if (speed[1]>max_speed) 
    		speed[1]=max_speed;
    	
    	if (speed[1]<-max_speed)
    		speed[1]=-max_speed;
    	    	
    	if (speed[2]>max_speed)
    		speed[2]=max_speed;
    	
    	if (speed[2]<-max_speed)
    		speed[2]=-max_speed;
    	
    	position[1] += speed[1];
        position[2] += speed[2];
	}      
	
	
}

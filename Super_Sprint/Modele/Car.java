package Modele;



public class Car {

	private int ID ;		//Identification of the car
	
	private int[] position ; //A 2x1 vector for the current position
	private double[] speed ;  // A 2x1 vector for the current speed 	
	private double[] orientation; // A 2x1 vector for the current orientation of the car 
	private int orient; // if speed=0:0, if reverse gear= -1, else 1
								
	
	private double max_speed ;		// The maximum speed the car can go
	private double acceleration ;	// The acceleration of the car (how fast the car speeds up)
	private double maneuvrability ; // The maneuverability of the car (how fast the car can turn)
	
	private boolean flag= false;
	
	public Car(int id, int[] position) {		
		this.ID = id ;
		this.position = position ;
		this.speed = new double[] {0,0} ;	
		this.orientation = new double[]{-1,0};
		this.orient=0;
		// Two cars for now, just for the idea
		if (id == 1){
			this.max_speed = 5; 
			this.acceleration = 0.1;
			this.maneuvrability = 1.5; // between 1 and 2
		}
		if (id == 2){
			this.max_speed = 4;
			this.acceleration = 2;
			this.maneuvrability = 2;
		}		
	}
	
	public int getID(){
		return this.ID ;
	}
	
	public int[] getPosition(){
		return this.position ;
	}
	
	public double[] getSpeed(){
		return this.speed ;
	}		
	
	
	public double getSpeed_a(){ // modulus of the speed vector
		return Math.sqrt(speed[0]*speed[0]+speed[1]*speed[1]);
	}	
	
	public double getBeta(){ // angle of the orientation of the car
		double beta;	
		
		if (orientation[0]==0){
			if(orientation[1]>0)
				beta=-Math.PI/2;
			else
				beta=Math.PI/2;
		}
		else
			beta=Math.atan(-this.orientation[1]/this.orientation[0]);
			if (orientation[0]<0){
				if (orientation[1]<0)
					beta += Math.PI;			
				else
					beta -= Math.PI;
			}		
		
		return beta;
	}
	
		
	
	public void setPosition(int x, int y){
		position[0]=x;
		position[1]=y;
	}
	
	public void setSpeed(int x, int y){
		speed[0]=x;
		speed[1]=y;
	}
	
	public void setOrientation(int x, int y){
		orientation[0]=x;
		orientation[1]=y;
	}
	
	
	public void setInit(){
		setPosition(600,120);	        	
    	setSpeed(0,0);
    	setOrientation(-1,0);  
    	orient=0;    	
	}
	
	public void rotate(double alpha){ //alpha is in radiant
		double sp = this.getSpeed_a();
		double beta = this.getBeta();
		
		speed[0] = orient*Math.cos(beta+alpha)*sp;
		speed[1] = -orient*Math.sin(beta+alpha)*sp;
		orientation[0]=orient*speed[0];
		orientation[1]=orient*speed[1];
		
	}
	
	public void speed_up(double accel, int or){
		double sp = this.getSpeed_a();
		double beta = this.getBeta();	
				
		if (sp==0){
			this.orient=or;
			speed[0]=orient*orientation[0]*accel;
			speed[1]=orient*orientation[1]*accel;			
		}
		else if ((or==1&&sp+accel<= max_speed)||(or==-1&&sp+accel<= 2)){
			speed[0]=orient*Math.cos(beta)*(sp+accel);
			speed[1]=-orient*Math.sin(beta)*(sp+accel);
			orientation[0]=orient*speed[0];
			orientation[1]=orient*speed[1];
		}	
		
	}
	
	public void decelerate(double decel){
		double sp = this.getSpeed_a();
		double beta = this.getBeta();
		
		if (sp-decel >0){
			speed[0]=orient*Math.cos(beta)*(sp-decel);
			speed[1]=-orient*Math.sin(beta)*(sp-decel);
			orientation[0]=orient*speed[0];
			orientation[1]=orient*speed[1];
			
		}
		else if(sp-decel <=0){
			orientation[0]=orient*speed[0];
			orientation[1]=orient*speed[1];
			speed[0]=0;
			speed[1]=0;
			orient=0;
		}		
	}	
		
	public void update_speed(int dt, int dn){			
		
		if((dn==1 && orient==1)||(dn==-1 && orient==-1)){
			rotate(-Math.PI*maneuvrability/120);			
		}
		else if ((dn==-1 && orient==1)||(dn==1 && orient==-1)){
			rotate(Math.PI*maneuvrability/120);			
		}		
		
		
		if(dt==1 && orient !=-1){
			speed_up(acceleration,1);			
		}
		
		else if(dt==-1 && orient !=1){
			speed_up(acceleration,-1);		
		}
		
		if (dt==0 && getSpeed_a()>0)
			decelerate(0.1);	
					
	}
	
	public void move(int dt, int dn, Circuit circuit, int w, int h){	
    	
    	this.update_speed(dt,dn);         	  	
    	    	
    	int spX=(int)Math.round(speed[0]);
    	int spY=(int)Math.round(speed[1]);
    	   	   	
    	int new_posX= position[0] + spX;
        int new_posY= position[1] + spY;  
        // the new position of the car before we check if the car gets out of the circuit          
        
        
        if (!flag &&(circuit.getValue(new_posX+w/2, new_posY+h/2)==1 || // we check if one of the corner of the car gets out of the circuit
        		circuit.getValue(new_posX+w/2, new_posY-h/2)==1||
        		circuit.getValue(new_posX-w/2, new_posY+h/2)==1||
        		circuit.getValue(new_posX-w/2, new_posY-h/2)==1)){
        	orientation[0]=orient*speed[0];
			orientation[1]=orient*speed[1];
        	speed[0]=0; 
        	speed[1]=0;
        	orient=0;
        	flag=true;
        	// if the car gets out: speed=0 and we register the orientation        	
        }
        else{
        	if (new_posX!=position[0]||new_posY!=position[1]){
        		flag=false;
        	}
        	position[0]=new_posX; // if not: we move the car to the new position
        	position[1]=new_posY;        	
        }
        	
	}      
	
	
}

package Modele;

/**
 * This class "Car" is one of the most important of all. It codes how the car move at each iteration
 *  with respect to the keyboard entries. The method "move" is called by the class Course.
 */
public class Car {
	
	private int ID; // Identification of the car
	private int[] position; // A 2x1 vector for the current position
	private double[] speed; // A 2x1 vector for the current speed	
	private double[] orientation; 
	// A 2x1 vector for the current orientation of the car
	private int orient; // if speed=0:0, else if reverse gear: -1, else forward: 1
	
	/**
	 * The "speed" vector is the speed of the car, which is added to the position vector at 
	 * each iteration to move the car. The "orientation" vector is the direction of the car. 
	 * It is different from the "speed" vector in 2 cases: in reverse gear (it is the opposite) 
	 * and when speed is zero (it keeps the last direction of the car before stopping)
	 */
	
	
	private double max_speed; // The maximum speed the car can go
	private double acceleration; 
	// The acceleration of the car (how fast the car speeds up)
	
	private double maneuverability; 
	// The maneuverability of the car (how fast the car can turn) // between 1 and 2
	
	private boolean flag=false; // a boolean to prevent the orientation to go to (0,0)
	// when speed is (0,0)
	private int[] init_position; // initial position of the car
	// we can go back to initial positions by pressing "R"


	public Car(int id, int[] position) {
		this.ID = id;	
		this.init_position= new int[] {position[0],position[1]};	
		this.position = position;	
		this.speed = new double[] { 0, 0 };
		this.orientation = new double[] { -1, 0 };
		this.orient = 0;
		// 4 different types of "cars" with different skills.
		if (id == 1) {
			this.max_speed = 5;
			this.acceleration = 0.1;
			this.maneuverability = 1.5; 
		}
		if (id == 2) {
			this.max_speed = 4.5;
			this.acceleration = 0.5;
			this.maneuverability = 1.8;
		}
		if (id==3){
			this.max_speed = 5.1 ;
			this.acceleration = 0.05 ;
			this.maneuverability = 1.3 ;
		}
		if (id==4){
			this.max_speed = 4.7 ;
			this.acceleration = 0.3 ;
			this.maneuverability = 1.6 ;
		}
	}

	public int getID() {
		return this.ID;
	}

	public int[] getPosition() {
		return this.position;
	}
	
	public double getManeuverability(){
		return maneuverability;
	}

	public double[] getSpeed() {
		return this.speed;
	}

	public double getSpeed_a() { // modulus of the speed vector
		return Math.sqrt(speed[0] * speed[0] + speed[1] * speed[1]);
	}

	
	public double getBeta() { // argument of the speed vector between -Pi and Pi
		double beta;

		if (orientation[0] == 0) {
			if (orientation[1] > 0)
				beta = -Math.PI / 2;
			else
				beta = Math.PI / 2;
		} else
			beta = Math.atan(-this.orientation[1] / this.orientation[0]);
		if (orientation[0] < 0) {
			if (orientation[1] < 0)
				beta += Math.PI;
			else
				beta -= Math.PI;
		}

		return beta;
	}

	public void setPosition(int x, int y) {
		position[0] = x;
		position[1] = y;
	}

	public void setSpeed(double x, double y) {
		speed[0] = x;
		speed[1] = y;
	}

	public void setOrientation(double x, double y) {
		orientation[0] = x;
		orientation[1] = y;
	}
	
	public void setOrient(int x){
		orient=x;
	}

	public void setInit() {	// set the car at its initial position
		setPosition(init_position[0], init_position[1]);		
		setSpeed(0, 0);
		setOrientation(-1, 0);
		orient = 0;
	}
	
	/**
	 * The three following methods "rotate", "speed_up" and "decelerate" are called by the
	 * method "update_speed". They modify the speed vector (and the orientation vector).
	 */

	public void rotate(double alpha) { // alpha is the angle of the rotation in radiant
		double sp = this.getSpeed_a();
		double beta = this.getBeta();

		speed[0] = orient * Math.cos(beta + alpha) * sp;
		speed[1] = -orient * Math.sin(beta + alpha) * sp;
		orientation[0] = orient * speed[0];
		orientation[1] = orient * speed[1];

	}

	public void speed_up(double accel, int or) { //accel is the acceleration added to the speed
		double sp = this.getSpeed_a();
		double beta = this.getBeta();

		if (sp == 0) {
			this.orient = or;
			speed[0] = orient * orientation[0] * accel;
			speed[1] = orient * orientation[1] * accel;
		} else if ((or == 1 && sp + accel <= max_speed) || (or == -1 && sp + accel <= 2)) {
			speed[0] = orient * Math.cos(beta) * (sp + accel);
			speed[1] = -orient * Math.sin(beta) * (sp + accel);
			orientation[0] = orient * speed[0];
			orientation[1] = orient * speed[1];
		}

	}

	public void decelerate(double decel) { // decel is the deceleration subtracted from the speed
		double sp = this.getSpeed_a();
		double beta = this.getBeta();

		if (sp - decel > 0) {
			speed[0] = orient * Math.cos(beta) * (sp - decel);
			speed[1] = -orient * Math.sin(beta) * (sp - decel);
			orientation[0] = orient * speed[0];
			orientation[1] = orient * speed[1];

		} else if (sp - decel <= 0) {
			orientation[0] = orient * speed[0];
			orientation[1] = orient * speed[1];
			speed[0] = 0;
			speed[1] = 0;
			orient = 0;
		}
	}

	/**
	 * This method is the link between the keyboard entries "dt", "dn" and the speed of the car.
	 * It rotates, speed_up or decelerate the car with respect to "dt" and "dn".
	 * dt=1 to go forward and dt=-1 to decelerate of go reverse gear
	 * dn=1 to rotate right and dn=-1 to rotate left	 
	 */
	public void update_speed(int dt, int dn) {

		if ((dn == 1 && orient == 1) || (dn == -1 && orient == -1)) {
			rotate(-Math.PI * maneuverability / 120);
		} else if ((dn == -1 && orient == 1) || (dn == 1 && orient == -1)) {
			rotate(Math.PI * maneuverability / 120);
		}

		if (dt == 1 && orient != -1) {
			speed_up(acceleration, 1);
		}

		else if (dt == -1 && orient != 1) {
			speed_up(acceleration, -1);
		}

		else if (dt == 0 && getSpeed_a() > 0)
			decelerate(0.1);
		
		else if ((dt==1 && orient ==-1) ||(dt==-1 && orient==1)){
			decelerate(0.2);
		}
		

	}

	/**
	 * This method moves the car within the restriction of the speed way
	 * and prevent possible collisions with other cars.
	 * 
	 * @param dt : a move parallel to the speed vector
	 * @param dn : a move orthogonal to the speed vector
	 * @param circuit 
	 * @param w : the car's width
	 * @param h : the car's height	 
	 */

	public void move(int dt, int dn, Circuit circuit, int w, int h, Car the_other_car, int w1, int h1) {

		
		this.update_speed(dt, dn);	// we update the speed vector by using the previous methods			
		
		//"speed" is double whereas "position" must be int
		int spX = (int) Math.round(speed[0]);
		int spY = (int) Math.round(speed[1]);

		// New possible position of the car		
		int new_posX = position[0] + spX;
		int new_posY = position[1] + spY;

		double cos = Math.abs(Math.cos(getBeta()));
		double sin = Math.abs(Math.sin(getBeta()));

		// Here we calculate the position of the four corners of the car with "w", "h"
		// by using some trigonometry formula. The "position" vector is the point at the center of the car.
		int[] top_left= new int[] {new_posX-(int)((w*cos+h*sin)/2)+(int)(w*cos), new_posY-(int)((h*cos+w*sin)/2)}; 
		int[] top_right= new int[] {new_posX+(int)((w*cos+h*sin)/2), new_posY-(int)((h*cos+w*sin)/2)+(int)(h*cos)};
		int[] bottom_left= new int[] {new_posX-(int)((w*cos+h*sin)/2), new_posY-(int)((h*cos+w*sin)/2)+(int)(w*sin)};
		int[] bottom_right= new int[] {new_posX-(int)((w*cos+h*sin)/2)-(int)(w*cos), new_posY+(int)((h*cos+w*sin)/2)};			

		// car_inside_window equals true if the four corners are inside the window (1200,700)
		boolean car_inside_window = top_left[0] >0 && top_left[0]<1200 && bottom_left[0]>0 && bottom_left[0]<1200 &&
				top_right[0] >0 && top_right[0]<1200 && bottom_right[0]>0 && bottom_right[0]<1200 &&
				top_left[1]>0 && top_left[1]<700 && bottom_left[1]>0 && bottom_left[1]<700 &&
				top_right[1]>0 && top_right[1]<700 && bottom_right[1]>0 && bottom_right[1]<700;


		if (car_inside_window) {		

			// Here we find the value of the circuit at the four corners	
			int Circuit_left_top = circuit.getValue(top_left[0], top_left[1]);
			int Circuit_right_top = circuit.getValue(top_right[0], top_right[1]);
			int Circuit_left_bottom = circuit.getValue(bottom_left[0], bottom_left[1]);
			int Circuit_right_bottom = circuit.getValue(bottom_right[0], bottom_right[1]);

			// If the car's four corners are in the speed way, it changes the car's position
			if ((Circuit_left_top == 0 && Circuit_right_top == 0 && Circuit_left_bottom == 0 && Circuit_right_bottom == 0)) {						
				
				// Collisions between cars controls
				// If one of the 4 corners of "the_other_car" cross one of the edges of this car there is a collision	
				// We modify both cars's speed
				if (the_other_car != null){ // so it is two players mode
					
					double cos1 = Math.abs(Math.cos(the_other_car.getBeta()));
					double sin1 = Math.abs(Math.sin(the_other_car.getBeta()));
					
					int[] pos_other = the_other_car.getPosition();
					
					int[] top_left1= new int[] {pos_other[0]-(int)((w1*cos1+h1*sin1)/2)+(int)(w1*cos1), pos_other[1]-(int)((h1*cos1+w1*sin1)/2)}; 
					int[] top_right1= new int[] {pos_other[0]+(int)((w1*cos1+h1*sin1)/2), pos_other[1]-(int)((h1*cos1+w1*sin1)/2)+(int)(h1*cos1)};
					int[] bottom_left1= new int[] {pos_other[0]-(int)((w1*cos1+h1*sin1)/2), pos_other[1]-(int)((h1*cos1+w1*sin1)/2)+(int)(w1*sin1)};
					int[] bottom_right1= new int[] {pos_other[0]-(int)((w1*cos1+h1*sin1)/2)-(int)(w1*cos1), pos_other[1]+(int)((h1*cos1+w1*sin1)/2)};
					
					boolean collision = inside_car(top_left1, top_left, top_right, bottom_right, bottom_left) 
									|| inside_car(top_right1, top_left, top_right, bottom_right, bottom_left)
									|| inside_car(bottom_left1, top_left, top_right, bottom_right, bottom_left)
									|| inside_car(bottom_right1, top_left, top_right, bottom_right, bottom_left);
									
					if (!flag && collision){
						
						// "the_other_car" bounces back 
						// we modify its speed
						double[] sp1=new double[] {the_other_car.getSpeed()[0],the_other_car.getSpeed()[1]};
						double man = the_other_car.getManeuverability();
						the_other_car.setSpeed(-sp1[0]*man/2, -sp1[1]*man/2);
						the_other_car.setOrient(-1);
						if (the_other_car.getSpeed_a()==0){
							the_other_car.setSpeed(speed[0]*man/2, speed[1]*man/2);							
							the_other_car.setOrient(1);
						}
						
						// this car is pushed away
						// we also modify its speed
						speed[0]+= sp1[0]*maneuverability/2;
						speed[1]+= sp1[1]*maneuverability/2;
						orient=1;
						orientation[0] = orient * speed[0];
						orientation[1] = orient * speed[1];
						flag=true;
						
					}
					else{
						position[0] = new_posX;
						position[1] = new_posY;
						flag=false;
					}
				}
											
				
				
			}

			// Else the car is not on the speed way
			else {
			// flag is used in order that orientation does not go to (0,0)
				if (!flag) {						
					speed[0] = -speed[0]*maneuverability/2;
					speed[1] = -speed[1]*maneuverability/2;
					orientation[0]=orientation[0]*maneuverability/2;
					orientation[1]=orientation[1]*maneuverability/2;									
					orient = -1;							
					flag=true;
				}
				}	
		
		// Else the car is about to go out of the window	
		}else{
			if(!flag){
				speed[0] = -speed[0]*maneuverability/2;
				speed[1] = -speed[1]*maneuverability/2;
				orientation[0]=orientation[0]*maneuverability/2;
				orientation[1]=orientation[1]*maneuverability/2;									
				orient = -1;							
				flag=true;
			}
			
		}


		
	}

	/**
	 * The following method returns true if the point defined by "coords"
	 * is inside the car defined by "top_left", "top_right", "bottom_right", "bottom_left" 
	 * We use the four equations which define the four edges of the car. The inside of the car is defined
	 * by a system of 4 inequalities.
	*/
	
	public boolean inside_car (int[] coords, int[] top_left, int[] top_right, int[] bottom_right, int[] bottom_left){
		int Fa= (coords[1]-top_left[1])*(top_right[0]-top_left[0])-(coords[0]-top_left[0])*(top_right[1]-top_left[1]);
		int Fb= (coords[1]-top_right[1])*(bottom_right[0]-top_right[0])-(coords[0]-top_right[0])*(bottom_right[1]-top_right[1]);
		int Fc= (coords[1]-bottom_right[1])*(bottom_left[0]-bottom_right[0])-(coords[0]-bottom_right[0])*(bottom_left[1]-bottom_right[1]);
		int Fd= (coords[1]-bottom_left[1])*(top_left[0]-bottom_left[0])-(coords[0]-bottom_left[0])*(top_left[1]-bottom_left[1]);
		
		return Fa>=0 && Fb>=0 && Fc>=0 && Fd>=0;
	}
	
	
	
}

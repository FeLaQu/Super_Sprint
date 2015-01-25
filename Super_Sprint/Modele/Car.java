package Modele;

public class Car {


	private int ID; // Identification of the car
	private int[] position; // A 2x1 vector for the current position
	private double[] speed; // A 2x1 vector for the current speed
	private double[] orientation; // A 2x1 vector for the current orientation of
	// the car
	private int orient; // if speed=0:0, if reverse gear= -1, else 1
	private double max_speed; // The maximum speed the car can go
	private double acceleration; // The acceleration of the car (how fast the
	// car speeds up)
	private double maneuverability; // The maneuverability of the car (how fast
	// the car can turn) // between 1 and 2
	private boolean flag = false;
	private int[] init_position;


	public Car(int id, int[] position) {
		this.ID = id;	
		this.init_position= new int[] {position[0],position[1]};	
		this.position = position;	
		this.speed = new double[] { 0, 0 };
		this.orientation = new double[] { -1, 0 };
		this.orient = 0;
		// Two cars for now, just for the idea
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

	public double[] getSpeed() {
		return this.speed;
	}

	public double getSpeed_a() { // modulus of the speed vector
		return Math.sqrt(speed[0] * speed[0] + speed[1] * speed[1]);
	}

	public double getBeta() { // angle of the orientation of the car
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

	public void setSpeed(int x, int y) {
		speed[0] = x;
		speed[1] = y;
	}

	public void setOrientation(int x, int y) {
		orientation[0] = x;
		orientation[1] = y;
	}

	public void setInit() {	
		setPosition(init_position[0], init_position[1]);		
		setSpeed(0, 0);
		setOrientation(-1, 0);
		orient = 0;
	}

	public void rotate(double alpha) { // alpha is in radiant
		double sp = this.getSpeed_a();
		double beta = this.getBeta();

		speed[0] = orient * Math.cos(beta + alpha) * sp;
		speed[1] = -orient * Math.sin(beta + alpha) * sp;
		orientation[0] = orient * speed[0];
		orientation[1] = orient * speed[1];

	}

	public void speed_up(double accel, int or) {
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

	public void decelerate(double decel) {
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

		if (dt == 0 && getSpeed_a() > 0)
			decelerate(0.1);

	}

	/**
	 * This method tells us how the car can move within the restriction of a Speed way. 
	 * 
	 * @param dt : a move orthogonal to the speed vector
	 * @param dn : a move parallel to the speed vector
	 * @param circuit 
	 * @param w : the car's width
	 * @param h : the car's height
	 * 
	 * @author : Quentin, Chengang 
	 */

	public void move(int dt, int dn, Circuit circuit, int w, int h, Car theothercar) {

		this.update_speed(dt, dn);	

		int spX = (int) Math.round(speed[0]);
		int spY = (int) Math.round(speed[1]);

		// New position of the car
		int new_posX = position[0] + spX;
		int new_posY = position[1] + spY;

		double cos = Math.abs(Math.cos(getBeta()));
		double sin = Math.abs(Math.sin(getBeta()));

		// Here we calculate the position of the four corners of the car
		int[] top_left= new int[] {new_posX-(int)((w*cos+h*sin)/2)+(int)(w*cos), new_posY-(int)((h*cos+w*sin)/2)}; 
		int[] top_right= new int[] {new_posX+(int)((w*cos+h*sin)/2), new_posY-(int)((h*cos+w*sin)/2)+(int)(h*cos)};
		int[] bottom_left= new int[] {new_posX-(int)((w*cos+h*sin)/2), new_posY-(int)((h*cos+w*sin)/2)+(int)(w*sin)};
		int[] bottom_right= new int[] {new_posX-(int)((w*cos+h*sin)/2)-(int)(w*cos), new_posY+(int)((h*cos+w*sin)/2)};			

		// car_inside is a boolean to know if the car is inside the window
		boolean car_inside = top_left[0] >0 && top_left[0]<1200 && bottom_left[0]>0 && bottom_left[0]<1200 &&
				top_right[0] >0 && top_right[0]<1200 && bottom_right[0]>0 && bottom_right[0]<1200 &&
				top_left[1]>0 && top_left[1]<700 && bottom_left[1]>0 && bottom_left[1]<700 &&
				top_right[1]>0 && top_right[1]<700 && bottom_right[1]>0 && bottom_right[1]<700;


				if (car_inside) {		

					// Here we find the value of the circuit at the four corners	
					int Circuit_left_top = circuit.getValue(top_left[0], top_left[1]);
					int Circuit_right_top = circuit.getValue(top_right[0], top_right[1]);
					int Circuit_left_bottom = circuit.getValue(bottom_left[0], bottom_left[1]);
					int Circuit_right_bottom = circuit.getValue(bottom_right[0], bottom_right[1]);

					// If the car's four corners are in the speed way, it changes the car's position
					if ((Circuit_left_top == 0 && Circuit_right_top == 0 && Circuit_left_bottom == 0 && Circuit_right_bottom == 0)) {
						flag = false;
						position[0] = new_posX;
						position[1] = new_posY;
					}

					// If not, speed=0 and it register the orientation
				} else {
					// flag is used in order that orientation does not go to (0,0)
					if (!flag) {
						orientation[0] = orient * speed[0];
						orientation[1] = orient * speed[1];
						speed[0] = 0;
						speed[1] = 0;
						orient = 0;
						flag = true;
					}
				}

				// Accidents between cars controls
				//si un des quatres angles est dans le rectangle d'une autre voiture -> vitesse modifiée
				// for now, just the angles and the centre touch the center of the other
				if (theothercar != null){ // so it is two players mode
					boolean car_touching_others = ((top_left == theothercar.getPosition()) ||
							(top_right == theothercar.getPosition()) ||
							(bottom_left == theothercar.getPosition()) ||
							(bottom_right == theothercar.getPosition()) ||
							(this.getPosition() == theothercar.getPosition())) ;
					if (car_touching_others){
						this.setSpeed((int)((-1)* this.getSpeed()[0]), (int)((-1)*this.getSpeed()[1]));
					}
				}
	}

	public int[][][] getAllPositionsOccupyed(){
		return null ;
	}
	
}

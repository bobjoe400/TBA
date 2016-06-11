package util;

import java.io.Serializable;

public class Location implements Serializable {
	private int x;
	private int y;
	
	public Location(){
		x = 0;
		y = 0;
	}
	
	public Location(int x, int y){
		this.x = x; 
		this.y = y;
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(Location other){
		this.x = other.getX();
		this.y = other.getY();
	}
	
	public Location getLocation(){
		return this;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}

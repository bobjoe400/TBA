package util;

import java.io.Serializable;

public class Location implements Serializable {
	private int x;
	private int y;
	
	public Location(){
		x = 23;
		y = 23;
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
	
	public boolean moveLeft(){
		if(x-1 <= 0){
			System.out.println("You can't go there.");
			return false;
		}else{
			x--;
			return true;
		}
	}
	
	public boolean moveRight(){
		if(x+1 == 47){
			System.out.println("You can't go there.");
			return false;
		}else{
			x++;
			return true;
		}
	}
	
	public boolean moveUp(){
		if(x+1 == 47){
			System.out.println("You can't go there.");
			return false;
		}else{
			x++;
			return true;
		}
	}
	
	public boolean moveDown(){
		if(x-1 == 0){
			System.out.println("You can't go there.");
			return false;
		}else{
			x--;
			return true;
		}
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

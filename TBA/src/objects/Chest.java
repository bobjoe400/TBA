package objects;

import interfaces.Entity;
import interfaces.Item;

import java.io.Serializable;
import java.util.ArrayList;

import world.Room;

public class Chest implements Entity, Serializable {
	private String name;
	private String type; //Material
	private Room room;
	public ArrayList<Item> inventory;
	
	public Chest(String name, Room room){
		this.name = name;
		this.room = room;
		inventory = new ArrayList<Item>();
	}
	
	public Chest(){
		this(null, new Room());
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setRoom(Room room){
		this.room = room;
	}
	
	public void addItem(Item item){
		inventory.add(item);
	}
	
	public Room getRoom(){
		return room;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String toString(){
		return("Name: "+name
				+"\nRoom: "+room.getName()
				+"\nItems in this chest: "+inventory);
	}
}

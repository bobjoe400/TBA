package world;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Entity;
import interfaces.Item;

public class Room implements Serializable{
	private String name;
	private String abbrev; 
	private String desc;
	private ArrayList<Area> loc;

	private ArrayList<Entity> entities;
	private ArrayList<Item> items;

	public Room(String name, String abbrev, String desc, ArrayList<String>	loc ) {
		this.name = name;
		this.desc = desc;
		this.abbrev = abbrev;
		this.loc = new ArrayList<Area>();
		for(String s: loc){
			Area a = util.typeCheck.checkArea(s);
			if(a != null){
				this.loc.add(a);
			}
		}
		entities = new ArrayList<Entity>();
		items = new ArrayList<Item>();
	}

	public Room() {
		this(null, null, null, new ArrayList<String>());
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void addItem(Item it) {
		items.add(it);
	}

	public String getName() {
		return name;
	}

	public String getDescript() {
		return desc;
	}
	
	public String getAbbv() {
		return abbrev; 
	}
	
	public ArrayList<Area> getLoc(){
		return loc;
	}

	public String toString() {
		return ("Room: " + name + "\nDescription: " + desc
				+ "\nEntites in this room: " + entities
				+ "\nItems in this room: " + items);
	}
}

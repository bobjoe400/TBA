package world;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Entity;
import interfaces.Item;

public class Room implements Serializable{
	private String name;
	private String descript;

	private ArrayList<Entity> entities;
	private ArrayList<Item> items;

	public Room(String name, String descript) {
		this.name = name;
		this.descript = descript;
		entities = new ArrayList<Entity>();
		items = new ArrayList<Item>();
	}

	public Room() {
		this(null, null);
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
		return descript;
	}

	public String toString() {
		return ("Room: " + name + "\nDescription: " + descript
				+ "\nEntites in this room: " + entities
				+ "\nItems in this room: " + items);
	}
}

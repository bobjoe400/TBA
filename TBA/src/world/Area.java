package world;

import java.io.Serializable;
import java.util.ArrayList;

public class Area implements Serializable {
	private String name;
	private String desc;
	private String type;

	public ArrayList<Room> rooms;

	public Area(String name, String desc, String type) {
		this.type = type;
		this.name = name;
		this.desc = desc;
		rooms = new ArrayList<Room>();
	}

	public Area() {
		this(null, null, null);
		rooms = new ArrayList<Room>();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return desc;
	}
	
	public String getType() {
		return type;
	}

	public void addRoom(Room rm) {
		rooms.add(rm);
	}

	public String toString() {
		String s = "";
		for (Room rm : rooms) {
			s += rm.getName() + " ";
		}
		return "Area name: " + name + "\nDescription: " + desc
				+ "\nRooms in area: " + s;
	}
}

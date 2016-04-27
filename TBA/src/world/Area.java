package world;

import java.util.ArrayList;

public class Area {
	private String name;
	private String description;

	public ArrayList<Room> rooms;

	public Area(String name, String description) {
		this.name = name;
		this.description = description;
		rooms = new ArrayList<Room>();
	}

	public Area() {
		this(null, null);
		rooms = new ArrayList<Room>();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void addRoom(Room rm) {
		rooms.add(rm);
	}

	public String toString() {
		String s = "";
		for (Room rm : rooms) {
			s += rm.getName() + " ";
		}
		return "Area name: " + name + "\nDescription: " + description
				+ "\nRooms in area: " + s;
	}
}

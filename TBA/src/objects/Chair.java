package objects;

import world.Room;
import interfaces.Entity;

public class Chair implements Entity {
	private String name;
	private String type;
	private Room room;
	
	public Chair(String name, Room room){
		this.name = name;
		this.room = room;
	}
	
	public Chair(){
		this(null,new Room());
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Room getRoom() {
		return room;
	}

	public String toString() {
		return "Chair [name=" + name + ",\nroom=" + room.getName()
				+ "]";
	}
	
}

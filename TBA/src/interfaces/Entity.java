package interfaces;

import world.Room;

public interface Entity {

	public void setName(String name);

	public void setType(String type);

	public void setRoom(Room room);

	public String getName();

	public String getType();

	public Room getRoom();

	public String toString();
}

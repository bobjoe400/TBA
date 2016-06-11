package interfaces;

import java.util.ArrayList;

import world.Room;

public interface Item {

	public void setName(String name);

	public void setType(String type);

	public void setAttack(int att);

	public void setDefense(int def);

	public void setWeight(double w);

	public void addRoomAllowed(Room room);

	public String getName();

	public String getType();

	public int getAttack();

	public int getDefense();

	public double getWeight();

	public ArrayList<Room> getRoomsAllowed();

	public String toString();
}

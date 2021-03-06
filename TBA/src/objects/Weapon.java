package objects;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Item;
import world.Room;

public class Weapon implements Item, Serializable {

	private String name;
	private String type;
	private int attack;
	private int defense;
	private double weight;
	private ArrayList<Room> roomsAllowed;

	public Weapon(String name, String type, int attack, int defense,
			double weight) {
		this.name = name;
		this.type = type;
		this.attack = attack;
		this.defense = defense;
		this.weight = weight;
		this.roomsAllowed = new ArrayList<Room>();
	}

	public Weapon() {
		this(null, null, 0, 0, 0);
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		this.type = type;
	}

	@Override
	public void setAttack(int att) {
		// TODO Auto-generated method stub
		this.attack = att;
	}

	@Override
	public void setDefense(int def) {
		// TODO Auto-generated method stub
		this.defense = def;
	}

	@Override
	public void setWeight(double w) {
		// TODO Auto-generated method stub
		this.weight = w;
	}

	@Override
	public void addRoomAllowed(Room room) {
		// TODO Auto-generated method stub
		roomsAllowed.add(room);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public int getAttack() {
		// TODO Auto-generated method stub
		return attack;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return defense;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

	@Override
	public ArrayList<Room> getRoomsAllowed() {
		// TODO Auto-generated method stub
		return roomsAllowed;
	}

	public String toString() {
		return ("Name: " + name + "\nType: " + type + "\nAttack: " + attack
				+ "\nDefense: " + defense + "\nWeight: " + weight + " lbs");
	}

}

package objects;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Item;
import world.Room;

public class Armor implements Item, Serializable {

	public String name;
	public String type;
	public int attack;
	public int defense;
	public double weight;
	public ArrayList<Room> roomsAllowed;

	public Armor(String name, String type, int attack, int defense, double weight) {
		this.name = name;
		this.type = type;
		this.attack = attack;
		this.defense = defense;
		this.weight = weight;
		roomsAllowed = new ArrayList<Room>();
	}

	public Armor() {
		this(null, null, 0, 0, 0.0);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAttack(int att) {
		attack = att;
	}

	public void setDefense(int def) {
		defense = def;
	}

	public void setWeight(double w) {
		weight = w;
	}

	public void addRoomAllowed(Room room) {
		roomsAllowed.add(room);
	}

	public String getName() {

		return name;
	}

	public String getType() {

		return type;
	}

	public int getAttack() {

		return attack;
	}

	public int getDefense() {

		return defense;
	}

	public double getWeight() {

		return weight;
	}

	public ArrayList<Room> getRoomsAllowed() {

		return roomsAllowed;
	}

	public String toString() {
		return ("Name: " + name + "\nType: " + type + "\nAttack: " + attack + "\nDefense: " + defense + "\nWeight: "
				+ weight + " lbs");
	}

}

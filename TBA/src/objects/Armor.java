package objects;

import world.Room;
import interfaces.Item;

public class Armor implements Item {

	public String name;
	public String type;
	public int attack;
	public int defense;
	public double weight;
	public Room room;

	public Armor(String name, String type, int attack, int defense,
			double weight, Room room) {
		this.name = name;
		this.type = type;
		this.attack = attack;
		this.defense = defense;
		this.weight = weight;
		this.room = room;
	}

	public Armor() {
		this(null, null, 0, 0, 0.0, new Room());
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

	public void setRoom(Room room) {
		this.room = room;
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

	public Room getRoom() {

		return room;
	}

	public String toString() {
		return ("Name: " + name + "\nType: " + type + "\nAttack: " + attack
				+ "\nDefense: " + defense + "\nWeight: " + weight + " lbs");
	}

}

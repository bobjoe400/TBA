package main;

import objects.Armor;
import objects.Chair;
import objects.Chest;
import objects.Weapon;
import world.Room;

public class EntityCreate {
	public static Chest chestCreate(String name, Room room) {
		Chest temp = new Chest(name, room);
		return temp;
	}

	public static Chair chairCreate(String name, Room room) {
		Chair temp = new Chair(name, room);
		return temp;
	}

	public static Weapon weaponCreate(String name, String type, int attack, int defense,
			double weight, Room room) {
		Weapon temp = new Weapon(name, type, attack, defense, weight, room);
		return temp;
	}

	public static Armor armorCreate(String name, String type, int attack, int defense,
			double weight, Room room) {
		Armor temp = new Armor(name, type, attack, defense, weight, room);
		return temp;
	}
}

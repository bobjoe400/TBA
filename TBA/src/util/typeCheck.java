package util;

import interfaces.Item;
import main.Game;
import world.Room;

public class typeCheck {

	public static boolean isInteger(String s, int range) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number.");
			System.out.println();
			return false;
		}
		int x = Integer.parseInt(s);
		System.out.println(x);
		if (x == -1)
			return true;
		if (range == -1)
			return true;
		if (x > range || x < 1) {
			System.out.println("Please enter a valid number. (1-" + range + ")");
			System.out.println();
			return false;
		}
		return true;
	}

	public synchronized static Room checkRoom(String s) {
		// System.out.println("cr"+s);
		for (Room rm : Game.roomList) {
			if (s.equalsIgnoreCase(rm.getName())) {
				return rm;
			}
		}
		System.out.println();
		return null;
	}

	public synchronized static Item checkItem(String s) {
		// while(itemList == null){}
		for (Item it : Game.itemList) {
			if (s.equalsIgnoreCase(it.getName())) {
				return it;
			}
		}
		return null;
	}
}

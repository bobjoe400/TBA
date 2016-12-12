package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import interfaces.Entity;
import interfaces.Item;
import objects.Chest;
import util.Location;
import world.Area;
import world.Room;

public class Main {

	private static Scanner in = new Scanner(System.in);
	private static Random randGen = new Random();
	private static Object _lock = new Object();

	public static String slash = System.getProperty("file.separator");
	public static List<Item> itemList;
	public static List<Entity> entityList;
	public static List<Room> roomList;
	public static List<Area> areaList;
	public static Map map;
	public static Set<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
	public static Player user;

	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

	private class LoadThread implements Runnable {
		public void run() {
			synchronized (_lock) {
				System.out.println("Started Room Initialization");
				roomInit();
				System.out.println("Finished Room Initialization");
			}
			// synchronized (_lock) {
			// areaInit();
			// percent += 17;
			// }
			synchronized (_lock) {
				System.out.println("Started Item Initialization");
				itemInit();
				System.out.println("Finished Item Initialization");
			}
			synchronized (_lock) {
				System.out.println("Started Entity Initialization");
				entityInit();
				System.out.println("Finished Entity Initialization");
			}
			synchronized (_lock) {
				System.out.println("Started Map Initialization");
				mapInit();
				System.out.println("Finished Map Initialization");
			}
			// synchronized (_lock) {
			// entityPlace();
			// percent += 17;
			// }
			// synchronized (_lock) {
			// itemPlace();
			// percent = 100;
			// }
			// threadMessage("all loaded");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		boolean newgame = true;
		if (new File("Resources/Save.tbes").exists()) {
			System.out.print("A save file was found. Would you like to continue? >");
			if (in.nextLine().toLowerCase().charAt(0) != 'n') {
				newgame = false;
			} else {
				new File("Resources/Save.tbes").delete();
			}
		}

		System.out.print("Would you like to go on a magical adventure? > ");
		if (in.nextLine().equalsIgnoreCase("no"))
			System.exit(1);
		/*
		 * Thread area = new Thread(new AreaThread()); Thread room = new
		 * Thread(new RoomThread()); Thread entity = new Thread(new
		 * EntityThread()); Thread item = new Thread(new ItemThread());
		 * room.start(); area.start(); item.start(); entity.start();
		 * entity.join();
		 */
		Main main = new Main();
		main.load();
		if (main.charCreate(newgame)) {
			boolean go = false;
			while (!go) {
				go = main.attribSet();
			}
		}
		main.wakeUp("You wake up to see a dark room.", false);
		in.close();
	}

	public void load() throws InterruptedException {
		Thread load = new Thread(new LoadThread());
		load.start();
		/*
		 * while (load.isAlive()) { System.out.print("Loading");
		 * System.out.print(".");//wait(100) System.out.println(); }
		 */
		load.join();
		System.out.println("Loading Complete!");
	}

	public boolean charCreate(boolean newgame) {
		user = new Player();
		if (!newgame) {
			user = user.loadState();
			return false;
		}
		System.out.print("What do you want your name to be? > ");
		String s = in.nextLine();
		if (s.equals("Teh Overlord Zenu")) {
			user = new Player(s, "Diete", true, 69, 100, 100, 100, 100, new Location());
			System.out.println(skillCheck());
			return false;
		}
		if (s.equalsIgnoreCase("yolo")) {
			int x = randGen.nextInt(1001);
			int y = randGen.nextInt(2);
			boolean b = true;
			if (y == 1)
				b = false;
			user = new Player("Yolo", "Swaggot", b, x, randStats(420), new Location());
			System.out.println(skillCheck());
			return false;
		}
		if (s.equalsIgnoreCase("cooper")) {
			user = new Player("Cooper", "Human", true, 18, 2, 2, 3, 3, new Location());
			System.out.println(skillCheck());
			return false;
		}
		user.setName(s);
		do {
			s = null;
			System.out.print("What would you like your age to be? > ");
			s = in.nextLine();
		} while (!isInteger(s, -1));
		user.setAge(Integer.parseInt(s));
		System.out.print("What would you like your race to be? > ");
		user.setRace(in.nextLine());
		boolean go = false;
		do {
			s = null;
			go = true;
			System.out.print("What would you like your gender to be? >");
			s = in.nextLine();
			if (!s.equalsIgnoreCase("male") && !s.equalsIgnoreCase("female")) {
				System.out.println("Please enter \"Male\" or \"Female\".");
				go = false;
			}
		} while (!go);
		user.setGender(s);
		return true;
	}

	public boolean attribSet() {
		String s = null;
		user.resetStats();
		System.out.println("You have 10 points to spend, use them wisely");
		levelUp(10);
		boolean go = false;
		while (!go) {
			go = true;
			System.out.println("Do you want these to be your skill points?");
			System.out.println(user.displaySkills());
			s = in.nextLine();
			if (s.startsWith("y") || s.startsWith("Y")) {
				break;
			}
			if (s.startsWith("n") || s.startsWith("N")) {
				return false;
			} else {
				System.out.println("Please enter \"yes\" or \"no\"");
				go = false;
			}
		}
		System.out.println(skillCheck());
		return true;
	}

	public void levelUp(int total) {
		String s;
		do {
			do {
				System.out.println(user.displaySkills());
				System.out.print("What skill would you like to change? (Please choose 1,2,3, or 4) >");
				s = in.nextLine();
			} while (!isInteger(s, 4));
			int x = Integer.parseInt(s);
			switch (x) {
			case 1:
				user.setIntel(user.getIntel() + 1);
				total--;
				break;
			case 2:
				user.setAgil(user.getAgil() + 1);
				total--;
				break;
			case 3:
				user.setAtt(user.getAtt() + 1);
				total--;
				break;
			case 4:
				user.setDef(user.getDef() + 1);
				total--;
				break;
			}
			System.out.println("You have " + total + " points left to spend.");
		} while (total > 0);
	}

	public void wakeUp(String s, boolean secTime) {
		// System.out.println(user);
		// user.setCurRoom(checkRoom("Prison Cell"), false);
		// user.setPrevRoom(checkRoom("Prison Cell"));
		switch (choice(new String[] { "Stay here and do nothing.", "Look around the room, there might be something.",
				"Go through the door without looking around." })) {
		case 1:
			System.out.println("You starved to death and died of dysentery.");
			System.exit(1);
			break;
		case 2:
			System.out.println(user.getCurRoom().getDescript());
			user.addToInventory("Iron Sword");
			user.addToInventory("Leather Jacket");
			user.addToInventory(randGenItem());
			hallway("You enter a hallway", false);
			break;
		case 3:
			System.out.println(user.getCurRoom().getDescript());
			hallway("You enter a hallway", false);
			break;
		}
	}

	public void hallway(String s, boolean secTime) {
		System.out.println(s);
		// user.setCurRoom(checkRoom("Hallway"), true);
		switch (choice(new String[] { "Check your inventory.", "Look around." })) {
		case 1:
			user.displayInventory();
			hallway("You're still in the hallway", true);
			break;
		case 2:
			System.out.println(user.getCurRoom().getDescript());
			break;
		}
	}

	public int choice(String[] choices) {
		System.out.println("Heres what you can do");
		for (int i = 0; i < choices.length; i++) {
			System.out.println((i + 1) + ". " + choices[i]);
		}
		String s;
		do {
			System.out.println("What would you like to do?");
			System.out.println("You can type -1 at any time to quit.");
			s = in.nextLine();
		} while (!isInteger(s, choices.length));
		if (Integer.parseInt(s) == -1) {
			System.out.println("Saving game.");
			user.saveState();
			System.exit(1);
		}
		return Integer.parseInt(s);
	}

	public Item randGenItem() {
		boolean go;
		Item x = null;
		do {
			go = true;
			for (int i = 0; i < itemList.size(); i++) {
				String s = itemList.get(i).getName();
				if (user.hasItem(s)) {
					go = false;
					break;
				}
				x = itemList.get(i);
			}
		} while (!go);
		if (x.equals(null))
			randGenItem();
		return x;
	}

	public int[] randStats(int total) {
		int[] stats = new int[4];
		for (int i = 0; i < stats.length; i++) {
			stats[i] = 0;
		}
		for (int i = 0; i < stats.length; i++) {
			int x = randGen.nextInt(total + 1);
			if (x > total) {
				i--;
				continue;
			}
			total -= x;
			stats[i] = x;
			if (total > 0 && stats[3] == 0)
				stats[3] = total;
		}
		return stats;
	}

	public boolean isInteger(String s, int range) {
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

	public synchronized Room checkRoom(String s) {
		// System.out.println("cr"+s);
		for (Room rm : roomList) {
			if (s.equalsIgnoreCase(rm.getName())) {
				return rm;
			}
		}
		System.out.println();
		return null;
	}

	public static Room checkRoomStatic(String s) {
		for (Room rm : roomList) {
			if (s.equalsIgnoreCase(rm.getName())) {
				return rm;
			}
		}
		System.out.println();
		return null;
	}

	public synchronized Item checkItem(String s) {
		// while(itemList == null){}
		for (Item it : itemList) {
			if (s.equalsIgnoreCase(it.getName())) {
				return it;
			}
		}
		return null;
	}

	public synchronized String skillCheck() {
		String s = "Heres what skills you have: ";
		if (user.getAgil() >= 5)
			s += "\nPower of Moonwalking";
		if (user.getIntel() >= 5)
			s += "\nPower of Calculus";
		if (user.getAtt() >= 5)
			s += "\nPower of Breaksword";
		if (user.getDef() >= 5)
			s += "\nPower of Parry";
		return s;
	}

	public synchronized void itemInit() {
		ArrayList<Item> temp = new ArrayList<Item>();

		String fileName = null;
		/*
		 * if (System.getProperty("os.name").toLowerCase().contains("windows"))
		 * { fileName = "G:" + slash + "JavaPrograms" + slash + "Eclipse" +
		 * slash + "Game" + slash + "Resources" + slash + "Items.txt"; } else {
		 * fileName = slash + "Volumes" + slash + "KINGSTON" + slash +
		 * "JavaPrograms" + slash + "Eclipse" + slash + "Game" + slash +
		 * "Resources" + slash + "Items.txt"; }
		 */
		fileName = "Resources" + slash + "Items.tbe";
		try {
			Scanner file = new Scanner(new File(fileName));
			Item tomp = null;
			String[] rooms = null;
			while (file.hasNextLine()) {
				String line = file.nextLine();
				if (line.startsWith("#")) {
					continue;
				}
				char i = line.charAt(0);
				switch (i) {
				case 'W':
					tomp = EntityCreate.weaponCreate(file.nextLine(), file.nextLine(),
							Integer.parseInt(file.nextLine()), Integer.parseInt(file.nextLine()),
							Double.parseDouble(file.nextLine()));
					rooms = file.nextLine().split(",");
					for (String s : rooms) {
						tomp.addRoomAllowed(checkRoom(s));
					}
					break;
				case 'A':
					tomp = EntityCreate.armorCreate(file.nextLine(), file.nextLine(), Integer.parseInt(file.nextLine()),
							Integer.parseInt(file.nextLine()), Double.parseDouble(file.nextLine()));
					rooms = file.nextLine().split(",");
					for (String s : rooms) {
						tomp.addRoomAllowed(checkRoom(s));
					}
					break;
				}
				temp.add(tomp);
			}
			file.close();
			itemList = temp;
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		itemList = null;
		return;
	}

	public synchronized void entityInit() {
		ArrayList<Entity> temp = new ArrayList<Entity>();
		String fileName = null;
		/*
		 * if (System.getProperty("os.name").toLowerCase().contains("windows"))
		 * { fileName = "G:" + slash + "JavaPrograms" + slash + "Eclipse" +
		 * slash + "Game" + slash + "Resources" + slash + "Entities.txt"; } else
		 * { fileName = slash + "Volumes" + slash + "KINGSTON" + slash +
		 * "JavaPrograms" + slash + "Eclipse" + slash + "Game" + slash +
		 * "Resources" + slash + "Entities.txt"; }
		 */

		fileName = "Resources" + slash + "Entities.tbe";

		try {
			Scanner file = new Scanner(new File(fileName));
			while (file.hasNextLine()) {
				String line = file.nextLine();
				if (line.startsWith("#")) {
					continue;
				}
				Entity tomp;
				int type = Integer.parseInt(line);
				switch (type) {
				case 0:
					tomp = new Chest(file.nextLine(), checkRoom(file.nextLine()));
					// System.out.println("ec"+tomp.getName());
					String[] timp = file.nextLine().split(", ");
					// System.out.println("ec2"+timp[0]);
					for (int i = 0; i < timp.length; i++) {
						((Chest) tomp).addItem(checkItem(timp[i]));
					}
					break;
				case 1:
					tomp = EntityCreate.chairCreate(file.nextLine(), checkRoom(file.nextLine()));
					break;
				default:
					System.out.println("IT DIDN'T WORK");
					entityList = null;
					return;
				}
				temp.add(tomp);
			}
			file.close();
			entityList = temp;
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		entityList = null;
		return;
	}

	public synchronized void roomInit() {
		ArrayList<Room> temp = new ArrayList<Room>();

		String fileName = null;/*
								 * sh + "JavaPrograms" + slash + "Eclipse" +
								 * slash + "Game" + slash + "Resources" + slash
								 * + "Rooms.tx if
								 * (System.getProperty("os.name").
								 * toLowerCase(). contains("windows")) {
								 * fileName = "G:" + slat
								 * "; } else { fileName = slash + "Volumes" +
								 * slash + "KINGSTON" + slash + "JavaPrograms" +
								 * slash + "Eclipse" + slash + "Game" + slash +
								 * "Resources" + slash + "Rooms.txt"; }
								 */
		fileName = "Resources" + slash + "Rooms.tbe";
		try {
			Scanner file = new Scanner(new File(fileName));

			while (file.hasNextLine()) {
				String line = file.nextLine();
				if (line.startsWith("#")) {
					continue;
				}
				String line2 = file.nextLine();
				Room tomp = new Room(line, line2);
				temp.add(tomp);
			}
			file.close();
			roomList = temp;
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		roomList = null;
		return;
	}

	public synchronized void mapInit() {
		map = new Map();
	}

	// public synchronized void areaInit() {
	// ArrayList<Area> temp = new ArrayList<Area>();
	// String fileName = null;
	// /*
	// * if (System.getProperty("os.name").toLowerCase().contains("windows"))
	// * { fileName = "G:" + slash + "JavaPrograms" + slash + "Eclipse" +
	// * slash + "Game" + slash + "Resources" + slash + "Areas.txt"; } else {
	// * fileName = slash + "Volumes" + slash + "KINGSTON" + slash +
	// * "JavaPrograms" + slash + "Eclipse" + slash + "Game" + slash +
	// * "Resources" + slash + "Areas.txt"; }
	// */
	// fileName = "Resources" + slash + "Areas.tbe";
	// try {
	// Scanner file = new Scanner(new File(fileName));
	//
	// while (file.hasNextLine()) {
	// if (file.nextLine().startsWith("#")) {
	// continue;
	// }
	// Area tomp = new Area(file.nextLine(), file.nextLine());
	// String[] s = file.nextLine().split(", ");
	// for (int i = 0; i < s.length; i++) {
	// tomp.addRoom(checkRoom(s[i]));
	// }
	// temp.add(tomp);
	// }
	// file.close();
	// areaList = temp;
	// return;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// areaList = null;
	// return;
	// }

	/*
	 * public synchronized void entityPlace() { // while(entityList == null){}
	 * for (Entity e : entityList) { if (e != null) { for (int i = 0; i <
	 * roomList.size(); i++) { if (roomList.get(i) != null) { if
	 * (roomList.get(i).getName().equalsIgnoreCase(e.getRoom().getName())) {
	 * roomList.get(i).addEntity(e); } } } } } }
	 */

	public synchronized void itemPlace() {

	}
}

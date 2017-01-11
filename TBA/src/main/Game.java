package main;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import interfaces.Entity;
import interfaces.Item;
import world.Area;
import world.Map;
import world.Room;




public class Game {

	public static List<Item> itemList;
	public static List<Entity> entityList;
	public static List<Room> roomList;
	public static List<Area> areaList;
	public static Map map;

	public static Player user;
	private static Object _lock = new Object();
	private static Scanner in = new Scanner(System.in);

	private class LoadThread implements Runnable {
		public void run() {
			synchronized (_lock) {
				System.out.println("Started Room Initialization");
				Game.roomList = (List<Room>) Init.room(Game.this);
				System.out.println("Finished Room Initialization");
			}
			// synchronized (_lock) {
			// areaInit();
			// percent += 17;
			// }
			synchronized (_lock) {
				System.out.println("Started Item Initialization");
				Game.itemList = (List<Item>) Init.item(Game.this);
				System.out.println("Finished Item Initialization");
			}
			synchronized (_lock) {
				System.out.println("Started Entity Initialization");
				Game.entityList = (List<Entity>) Init.entity(Game.this);
				System.out.println("Finished Entity Initialization");
			}
			synchronized (_lock) {
				System.out.println("Started Map Initialization");
				Game.map = (Map) Init.map(Game.this);
				System.out.println("Finished Map Initialization");
			}
			// synchronized (_lock) {
			// entityPlace();
			// percent += 17;
			// }
			synchronized (_lock) {
				System.out.println("Starting to Place Items");
				Game.map = (Map) Init.itemPlace(Game.this);
				System.out.println("Finished Placing Items");
			}
			// threadMessage("all loaded");
		}
	}

	public Game() {
		try {
			boolean newgame = true;
			if (new File("Resources/Save.tbes").exists()) {
				System.out.print("A save file was found. Would you like to continue? >");
				if (in.nextLine().toLowerCase().charAt(0) != 'n') {
					newgame = false;
				} else {
					new File("Resources/Save.tbes").delete();
				}
			}
			/*
			 * Thread area = new Thread(new AreaThread()); Thread room = new
			 * Thread(new RoomThread()); Thread entity = new Thread(new
			 * EntityThread()); Thread item = new Thread(new ItemThread());
			 * room.start(); area.start(); item.start(); entity.start();
			 * entity.join();
			 */
			load();
			CharacterCreate cc = new CharacterCreate();
			user = cc.create(newgame);
			wakeUp("You wake up to a dark room.", false);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void wakeUp(String s, boolean secTime) {
		// System.out.println(user);
		// user.setCurRoom(checkRoom("Prison Cell"), false);
		// user.setPrevRoom(checkRoom("Prison Cell"));
		System.out.println(s);
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
		} while (!util.typeCheck.isInteger(s, choices.length));
		if (Integer.parseInt(s) == -1) {
			System.out.println("Saving game.");
			user.saveState();
			System.exit(1);
		}
		return Integer.parseInt(s);
	}

	public static Item randGenItem() {
		Random rand = new Random();
		return itemList.get(rand.nextInt(itemList.size()));
	}
}

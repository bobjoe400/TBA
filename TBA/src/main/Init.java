package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import interfaces.Entity;
import interfaces.Item;
import objects.Chest;
import util.Generate;
import util.Location;
import world.Map;
import world.Room;

public class Init {
	public static String slash = System.getProperty("file.separator");
	
	public synchronized static ArrayList<Item> item(Game g) {
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
						tomp.addRoomAllowed(util.typeCheck.checkRoom(s));
					}
					break;
				case 'A':
					tomp = EntityCreate.armorCreate(file.nextLine(), file.nextLine(), Integer.parseInt(file.nextLine()),
							Integer.parseInt(file.nextLine()), Double.parseDouble(file.nextLine()));
					rooms = file.nextLine().split(",");
					for (String s : rooms) {
						tomp.addRoomAllowed(util.typeCheck.checkRoom(s));
					}
					break;
				}
				temp.add(tomp);
			}
			file.close();
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized static  ArrayList<Entity> entity(Game g) {
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
					tomp = new Chest(file.nextLine(), util.typeCheck.checkRoom(file.nextLine()));
					// System.out.println("ec"+tomp.getName());
					String[] timp = file.nextLine().split(", ");
					// System.out.println("ec2"+timp[0]);
					for (int i = 0; i < timp.length; i++) {
						((Chest) tomp).addItem(util.typeCheck.checkItem(timp[i]));
					}
					break;
				case 1:
					tomp = EntityCreate.chairCreate(file.nextLine(), util.typeCheck.checkRoom(file.nextLine()));
					break;
				default:
					System.out.println("IT DIDN'T WORK");
					return null;
				}
				temp.add(tomp);
			}
			file.close();
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized static ArrayList<Room> room(Game g) {
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
				String line3 = file.nextLine();
				Room tomp = new Room(line, line2, line3);
				temp.add(tomp);
			}
			file.close();
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	
	public synchronized static Map itemPlace(Game g) {
		Map temp = Game.map;
		for (int i = 0; i < 48; i++) {
			for (int j = 0; j < 48; j++) {
				if (i == 22 && j == 22) {
					continue;
				}
				Room tomp = temp.getMap()[i][j];
				tomp.addItem(Generate.item());
				temp.setRoom(new Location(i, j), tomp);
			}
		}
		return temp;
	}

	public synchronized static Map map(Game g) {
		return new Map();
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
}

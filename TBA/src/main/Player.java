package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import interfaces.Item;
import util.Location;
import world.Room;

public class Player implements Serializable {

	private String name;
	private String race;
	private String direction;
	private boolean isMale;
	private int age;
	private int intel;
	private int agil;
	private int att;
	private int def;
	private Room currentRoom;
	private Room prevRoom;
	private Location loc;
	public ArrayList<Item> inventory;
	public ArrayList<Room> discoveredRooms;

	public Player(String name, String race, boolean isMale, int age, int intel, int agil, int att, int def,
			/* Room room, Room proom */Location loc) {
		this.name = name;
		this.race = race;
		direction = "North";
		this.isMale = isMale;
		this.age = age;
		this.intel = intel;
		this.agil = agil;
		this.att = att;
		this.def = def;
		this.loc = loc;
		inventory = new ArrayList<Item>();
		discoveredRooms = new ArrayList<Room>();
	}

	public Player(String name, String race, boolean isMale, int age, int[] stats, Location loc) {
		this(name, race, isMale, age, stats[0], stats[1], stats[2], stats[3], loc);
	}

	public Player() {
		this(null, null, false, 0, 0, 0, 0, 0, new Location());
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public void setGender(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			isMale = true;
			return;
		}
		isMale = false;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setIntel(int intel) {
		this.intel = intel;
	}

	public void setAgil(int agil) {
		this.agil = agil;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void setLocation(int x, int y) {
		loc = new Location(x, y);
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}

	/*
	 * public void setCurRoom(Room room, boolean b) { this.currentRoom = room;
	 * discoveredRooms.add(room); if (b) { System.out.println(
	 * "You discovered the " + room.getName()); } }
	 * 
	 * public void setPrevRoom(Room proom) { this.prevRoom = proom; }
	 */

	/*
	 * public void setDirection(String s) { direction = s; }
	 */

	public void resetStats() {
		intel = 0;
		agil = 0;
		att = 0;
		def = 0;
	}

	public void addToInventory(String item) {
		for (Item it : Game.itemList) {
			if (it.getName().equalsIgnoreCase(item)) {
				inventory.add(it);
				if (Main.vowels.contains(Character.toLowerCase(it.getName().charAt(0)))) {
					System.out.println("You found an " + it.getName());
					return;
				}
				System.out.println("You found a " + it.getName());
			}
		}
	}

	public void addToInventory(Item it) {
		inventory.add(it);
		if (Main.vowels.contains(Character.toLowerCase(it.getName().charAt(0)))) {
			System.out.println("You found an " + it.getName());
			return;
		}
		System.out.println("You found a " + it.getName());
	}

	public String getName() {
		return name;
	}

	public String getRace() {
		return race;
	}

	public String getGenderString() {
		if (isMale)
			return "Male";
		return "Female";
	}

	public boolean getGenderBool() {
		return isMale;
	}

	public int getAge() {
		return age;
	}

	public int getIntel() {
		return intel;
	}

	public int getAgil() {
		return agil;
	}

	public int getAtt() {
		return att;
	}

	public int getDef() {
		return def;
	}

	public Room getCurRoom() {
		return Game.map.getRoom(loc);
	}

	public boolean hasItem(String item) {
		if (inventory.contains(item))
			return true;
		return false;
	}

	public String toString() {
		String s = ("These are your stats:\n" + "\nName: " + name + "\nRace: " + race + "\nGender: " + getGenderString()
				+ "\nAge: " + age + "\nIntelligence: " + intel + "\nAgility: " + agil + "\nAttack: " + att
				+ "\nDefense: " + def);
		try {
			if (currentRoom.getName() != null) {
				s += "\nCurrent Room: " + currentRoom.getName();
			}
			if (prevRoom.getName() != null) {
				s += "\nPreviousRoom: " + prevRoom.getName();
			}
		} catch (NullPointerException e) {
			System.out.println("It dun wurk");
		}
		return s;
	}

	public String displayStats() {
		return ("Here's the skills:" + "\n1. Intelligence: " + intel + "\n2. Agility: " + agil + "\n3. Attack: " + att
				+ "\n4. Defense: " + def);
	}

	public String displaySkills() {
		String s = "Heres what skills you have: ";
		if (agil >= 5)
			s += "\nPower of Moonwalking";
		if (intel >= 5)
			s += "\nPower of Calculus";
		if (att >= 5)
			s += "\nPower of Breaksword";
		if (def >= 5)
			s += "\nPower of Parry";
		return s;
	}

	public void displayInventory() {
		System.out.println("Heres what you've got");
		for (Item it : inventory) {
			System.out.println("--------------");
			System.out.println(it);
		}
		System.out.println("---------------");
	}

	public void levelUp(int total) {
		Scanner in = new Scanner(System.in);
		String s;
		do {
			do {
				System.out.println(displayStats());
				System.out.print("What skill would you like to change? (Please choose 1,2,3, or 4) >");
				s = in.nextLine();
			} while (!util.typeCheck.isInteger(s, 4));
			int x = Integer.parseInt(s);
			switch (x) {
			case 1:
				intel++;
				total--;
				break;
			case 2:
				agil++;
				total--;
				break;
			case 3:
				att++;
				total--;
				break;
			case 4:
				def++;
				total--;
				break;
			}
			System.out.println("You have " + total + " points left to spend.");
		} while (total > 0);
		in.close();
	}

	public void saveState() {
		try {
			System.out.println(getClass());
			File file = new File("Resources/Save.tbes");
			file.createNewFile();
			FileOutputStream fstream = new FileOutputStream(file);
			ObjectOutputStream ostream = new ObjectOutputStream(fstream);
			ostream.writeObject(this);
			ostream.close();
			System.out.println("Game saved");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println();
			e.printStackTrace();
		}
	}

	public Player loadState() {
		Player oPlayer;
		try {
			FileInputStream fstream = new FileInputStream(new File("Resources/Save.tbes"));
			ObjectInputStream ostream = new ObjectInputStream(fstream);
			oPlayer = (Player) ostream.readObject();
			ostream.close();
			return oPlayer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public void saveState() { String slash = Main.slash; String fileName =
	 * null; if
	 * (System.getProperty("os.name").toLowerCase().contains("windows")) {
	 * fileName = "G:" + slash + "JavaPrograms" + slash + "Eclipse" + slash +
	 * "Game" + slash + "Resources" + slash + "Save.txt"; } else { fileName =
	 * slash + "Volumes" + slash + "KINGSTON" + slash + "JavaPrograms" + slash +
	 * "Eclipse" + slash + "Game" + slash + "Resources" + slash + "Save.tbes"; }
	 * 
	 * PrintWriter writer; try { writer = new PrintWriter(fileName, "UTF-8");
	 * writer.println("10"); writer.print("#"); writer.print(this);
	 * writer.close(); System.out.println("Saving...."); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (UnsupportedEncodingException e) { e.printStackTrace(); } }
	 */

	/*
	 * public void loadState() { String slash = Main.slash; String fileName =
	 * null; /*if
	 * (System.getProperty("os.name").toLowerCase().contains("windows")) {
	 * fileName = "G:" + slash + "JavaPrograms" + slash + "Eclipse" + slash +
	 * "Game" + slash + "Resources" + slash + "Save.txt"; } else { fileName =
	 * slash + "Volumes" + slash + "KINGSTON" + slash + "JavaPrograms" + slash +
	 * "Eclipse" + slash + "Game" + slash + "Resources" + slash + "Save.txt";
	 * }// fileName = "D:"
	 * +slash+"Downloads"+slash+"Programs"+slash+"Programs"+slash+"eclipse"+
	 * "Game Mac"+slash+ "Resources"+slash+"Save.txt"; try { Scanner file = new
	 * Scanner(new File(fileName));
	 * 
	 * int x = file.nextInt(); file.nextLine();
	 * 
	 * Player temp = new Player(); String[] tomp; int y;
	 * 
	 * while (file.hasNextLine()) { if (file.nextLine().startsWith("#")) {
	 * continue; } String s = null; for (int i = 0; i < x; i++) { switch (i) {
	 * case 0: s = file.nextLine(); tomp = s.split(": "); temp.setName(tomp[1]);
	 * break; case 1: s = file.nextLine(); tomp = s.split(": ");
	 * temp.setRace(tomp[1]); break; case 2: s = file.nextLine(); tomp =
	 * s.split(": "); temp.setGender(tomp[1]); break; case 3: s =
	 * file.nextLine(); tomp = s.split(": "); y = Integer.parseInt(tomp[1]);
	 * temp.setAge(y); break; case 4: s = file.nextLine(); tomp = s.split(": ");
	 * y = Integer.parseInt(tomp[1]); temp.setIntel(y); break; case 5: s =
	 * file.nextLine(); tomp = s.split(": "); y = Integer.parseInt(tomp[1]);
	 * temp.setAgil(y); break; case 6: s = file.nextLine(); tomp = s.split(": "
	 * ); y = Integer.parseInt(tomp[1]); temp.setAtt(y); break; case 7: s =
	 * file.nextLine(); tomp = s.split(": "); y = Integer.parseInt(tomp[1]);
	 * temp.setDef(y); break; case 8: s = file.nextLine(); tomp = s.split(": ");
	 * temp.setCurRoom(Main.checkRoomStatic(tomp[1]), false); break; case 9: s =
	 * file.nextLine(); tomp = s.split(": ");
	 * temp.setPrevRoom(Main.checkRoomStatic(tomp[1])); break; } } }
	 * file.close(); Main.user = temp; System.out.println("Loading..."); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */
}

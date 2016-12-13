package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

import util.Generate;
import util.Location;
import util.typeCheck;

public class Map {
	private Room[][] map;

	public Map() {
		map = new Room[48][48];

		for (int i = 0; i < 48; i++) {
			for (int j = 0; j < 48; j++) {
				if (i == 23 && j == 23) {
					Room temp = new Room("Prison Cell", "SA", "A damp dark room.");
					map[i][j] = temp;
				} else {
					Random rand = new Random();
					Room temp;
					if(rand.nextInt(100) < 25){temp = new Room("Wall", "##", "It's a wall");
					}else{
						temp = Generate.room();
					}
					map[i][j] = temp;
				}
			}
		}
		map = startEval(0, map);
		try {
			File file = new File("Resources/Map.tbe");
			PrintWriter p = new PrintWriter(file);
			for (int i = 0; i < 48; i++) {
				for (int j = 0; j < 48; j++) {
					p.print("|" + map[i][j].getAbbv() + "|");
				}
				p.println();
			}
			p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Room[][] startEval(int runs, Room[][] map) {
		System.out.println(runs);
		if (runs == 10) {
			return map;
		}
		Room[][] newMap = new Room[48][48];
		for (int i = 1; i < 47; i++) {
			for (int j = 1; j < 47; j++) {
				Room[] neighbors = new Room[] { map[i - 1][j - 1], map[i - 1][j], map[i - 1][j + 1], map[i][j - 1],
						map[i][j + 1], map[i + 1][j - 1], map[i + 1][j], map[i + 1][j + 1] };
				newMap[i][j] = newRoom(map[i][j], neighbors);
			}
		}
		for (int i = 1; i < 47; i++) {
			for (int j = 1; j < 47; j++) {
				map[i][j] = newMap[i][j];
			}
		}
		runs++;
		return startEval(runs, map);
	}

	private Room newRoom(Room base, Room[] neighbors) {
		if (base.getAbbv().equals("SA")) {
			return base;
		}
		int total = 0;
		//Room most;
		// System.out.println(neighbors);
		for (Room r : neighbors) {
			// System.out.println(r.getName());
//			if (r.getAbbv().equals("SA")) {
//				total = -1;
//				break;
//			}
			if (r.getAbbv().equals("##")){
				total++;
			}
		}
//		HashMap<Room, Integer> hm = new HashMap<Room, Integer>();
//		int max = 1;
//		Room temp = new Room();
//		for (int i = 0; i < neighbors.length; i++) {
//			if (hm.get(neighbors[i]) != null) {
//				int count = hm.get(neighbors[i]);
//				count = count + 1;
//				hm.put(neighbors[i], count);
//				if (count > max) {
//					max = count;
//					temp = neighbors[i];
//				}
//			} else {
//				hm.put(neighbors[i], 1);
//			}
//		}
//		most = temp;
		if(total >= 5 || total == 0){
			return new Room("Wall","##","It's a wall");
		}else{
			return base;
		}
	}

	public Map(char[][] map) {

	}

	public void LoadMap() {

	}

	public void saveMap() {
		try {
			System.out.println(getClass());
			File file = new File("Resources/Map.tbes");
			file.createNewFile();
			FileOutputStream fstream = new FileOutputStream(file);
			ObjectOutputStream ostream = new ObjectOutputStream(fstream);
			ostream.writeObject(this);
			ostream.close();
			System.out.println("Map saved");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println();
			e.printStackTrace();
		}
	}

	public Room getRoom(Location loc) {
		return map[loc.getX()][loc.getY()];
	}

	public void setRoom(Location loc, Room rm) {
		map[loc.getX()][loc.getY()] = rm;
	}

	public Room[][] getMap() {
		return map;
	}
}

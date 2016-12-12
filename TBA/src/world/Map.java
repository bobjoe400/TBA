package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Random;

import util.Location;

public class Map {
	private Room[][] map;

	public Map() {
		map = new Room[48][48];
		try {
			File file = new File("Resources/Map.tbe");
			PrintWriter p = new PrintWriter(file);
			for (int i = 0; i < 48; i++) {
				for (int j = 0; j < 48; j++) {
					if (i == 0 || i == 47 || j == 0 || j == 47) {
						p.print("#");
						map[i][j] = new Room("Wall", "It's a wall.");
					} else if(i == 22 && j == 22){
						p.print("P");
						map[i][j] = util.typeCheck.checkRoom("Prison Cell");
					} else {
						int x = Math.abs(new Random().nextInt(1000));
						if (0 <= x && x < 250) {
							p.print("D");
							map[i][j] = util.typeCheck.checkRoom("Dungeon");
						} else if (x >= 250 && x < 500) {
							p.print("H");
							map[i][j] = util.typeCheck.checkRoom("Hallway");
						} else if (x >= 500 && x < 750) {
							p.print("P");
							map[i][j] = util.typeCheck.checkRoom("Prison Cell");
						} else if (x >= 750 && x < 1000) {
							p.print("J");
							map[i][j] = util.typeCheck.checkRoom("Hallway");
						}
					}
				}
				p.println();
			}
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
	
	public void setRoom(Location loc, Room rm){
		map[loc.getX()][loc.getY()] = rm;
	}

	public Room[][] getMap() {
		return map;
	}
}

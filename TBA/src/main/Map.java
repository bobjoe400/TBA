package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import util.Location;
import world.Room;

public class Map {
	private Room[][] map;
	
	
	public Map(){
		
	}
	
	public Map(char[][] map){
		
	}
	
	public void saveMap(){
		try{
			System.out.println(getClass());
			File file = new File("Resources/Map.tbes");
			file.createNewFile();
			FileOutputStream fstream = new FileOutputStream(file);
			ObjectOutputStream ostream = new ObjectOutputStream(fstream);
			ostream.writeObject(this);
			ostream.close();
			System.out.println("Map saved"); 
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			System.out.println();
			e.printStackTrace();
		}
	}
	
	public Room getRoom(Location loc){
		return map[loc.getX()][loc.getY()];
	}
	
	public Room[][] getMap(){
		return map;
	}
}

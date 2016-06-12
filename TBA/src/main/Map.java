package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Random;

import util.Location;
import world.Room;

public class Map {
	private Room[][] map;
	
	
	public Map(){
		try{
			File file = new File("Resources/Map.tbe");
			PrintWriter p = new PrintWriter(file);
			for(int i = 0; i < 48; i ++){
				for(int j = 0; j<48; j++){
					if(i == 0 || i == 47 || j == 0 || j == 47){
						p.print("#");
					}else{
						int x = Math.abs(new Random().nextInt(1000));
						if(0 <= x && x < 250){
							p.print("D");
						}else if(x >= 250 && x < 500){
							p.print("H");
						}else if(x >= 500 && x < 750){
							p.print("P");
						}else if(x >= 750 && x < 1000){
							p.print("J");
						}
					}
				}
				p.println();
			}
			p.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public Map(char[][] map){
		
	}
	
	public void LoadMap(){
		
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

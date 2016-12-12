package util;

import java.util.Random;

import interfaces.Item;
import main.Game;
import world.Room;

public class Generate {
	
	public static Room room(){
		Random randGen = new Random();
		return Game.roomList.get(randGen.nextInt(Game.roomList.size()));
	}
	
	public static Item item(){
		Random randGen = new Random();
		return Game.itemList.get(randGen.nextInt(Game.roomList.size()));
	}
}

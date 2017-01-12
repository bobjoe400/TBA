package testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import util.RoomDeserializer;
import world.Area;
import world.Room;


public class test {
	public static ArrayList<Area> meme;
	public static void main(String[] args){
		
		try {
			Gson gson = new Gson();
			BufferedReader br = new BufferedReader(new FileReader("Resources\\obj\\Areas.json"));
			Area[] areas = gson.fromJson(br, Area[].class);
			br.close();
			ArrayList<Area> memes = new ArrayList<Area>(Arrays.asList(areas));
			meme = memes;
			System.out.println(memes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("failed on areas");
			e.printStackTrace();
		}
		try {
			Gson gson = new GsonBuilder().registerTypeAdapter(Room.class, new RoomDeserializer()).create();
			BufferedReader br = new BufferedReader(new FileReader("Resources\\obj\\Rooms.json"));
			Room[] rooms = gson.fromJson(br, Room[].class);
			br.close();
			ArrayList<Room> memes = new ArrayList<Room>(Arrays.asList(rooms));
			System.out.println(memes);
		}catch (Exception e){
			System.out.println("failed on rooms");
			e.printStackTrace();
		}
		
	}
}

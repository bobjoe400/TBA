package main;

import java.util.Random;
import java.util.Scanner;

import util.Location;

public class CharacterCreate {
	
	private Player user;
	private Scanner in = new Scanner(System.in);
	private Random randGen = new Random();
	
	public Player create(boolean newgame) {
		user = new Player();
		if (!newgame) {
			user = user.loadState();
			return user;
		}
		System.out.print("What do you want your name to be? > ");
		String s = in.nextLine();
		if (s.equals("Teh Overlord Zenu")) {
			user = new Player(s, "Diete", true, 69, 100, 100, 100, 100, new Location());
			
			System.out.println(Main.skillCheck(user));
			return user;
		}
		if (s.equalsIgnoreCase("yolo")) {
			int x = randGen.nextInt(1001);
			int y = randGen.nextInt(2);
			boolean b = true;
			if (y == 1)
				b = false;
			user = new Player("Yolo", "Swaggot", b, x, randStats(420), new Location());
			System.out.println(Main.skillCheck(user));
			return user;
		}
		if (s.equalsIgnoreCase("cooper")) {
			user = new Player("Cooper", "Human", true, 18, 2, 2, 3, 3, new Location());
			System.out.println(Main.skillCheck(user));
			return user;
		}
		if(s.equalsIgnoreCase("debug")){
			
		}
		user.setName(s);
		do {
			s = null;
			System.out.print("What would you like your age to be? > ");
			s = in.nextLine();
		} while (!util.typeCheck.isInteger(s, -1));
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
		while(!attribSet()){}
		return user;
	}

	public boolean attribSet() {
		String s = null;
		user.resetStats();
		System.out.println("You have 10 points to spend, use them wisely");
		user.levelUp(10);
		boolean go = false;
		while (!go) {
			go = true;
			System.out.println("Do you want these to be your skill points?");
			System.out.println(user.displayStats());
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
		System.out.println(Main.skillCheck(user));
		return true;
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
}

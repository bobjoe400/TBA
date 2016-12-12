package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static Set<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Would you like to go on a magical adventure? > ");
		if (in.nextLine().toLowerCase().startsWith("y")){
			new Game();
		}
		in.close();
	}

}

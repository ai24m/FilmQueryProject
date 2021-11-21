package com.skilldistillery.filmquery.app;

import java.util.*;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	private static Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test(); for test purpose ONLY 
		app.launch();
	}

//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

	private void launch() {
		printWelcomeMenu();
		startUserInterface();
		kb.close();
	}

	private void startUserInterface() {
		int input = 0;
		
		try {
			printMenu();
			System.out.println("Enter Selection: ");
			input = kb.nextInt();
			
			switch (input) {
			case 1:
				filmByID();
				break;
			case 2:
				filmByKeyword();
				break;
			case 3:
				System.out.println("Have a lovely day.");
				break;
			default:
				System.err.println("Invalid input. Try again!\n");
				startUserInterface();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("Invalid input. Try again!\n");
			kb.next();
			startUserInterface();
		}
	}

	private void filmByID() {
		System.out.println("Please enter ID: ");
		try {
			int filmID = kb.nextInt();
			System.out.print("Searching . . . ");
			Film film = db.findFilmByID(filmID);
			
			if (film != null) { // if ID matches existing ID
				System.out.println(film.toStringFilmBySearch()); 
			} else if (film == null){ // if no matches found
				System.out.println("\nNo matches for ID: " + filmID);
			}
			promptAgain();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input, try again!");
			filmByID(); // re-prompts of ID
		} 
	}

	private void filmByKeyword() {
		System.out.println("Please enter keyword: ");
		int counter = 0;
		try {
			String keyword = kb.next();
			System.out.print("Searching . . . ");
			List<Film> film = db.findFilmByKeyword(keyword);
			
			if (film.size() != 0) { // may be more than one match
				for (Film key : film) {
					System.out.println(key.toStringFilmBySearch() + "\n");
					counter++;
				} System.out.println("\nNumber of matches found: " + counter); 
			} else { // if no matches found
				System.out.println("No matches for Keyword: " + keyword);
			} promptAgain();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input, try again!");
			filmByID(); // re-prompts of ID
		}
	}

	public void printMenu() {
		System.out.println("☾ ⋆*･ﾟ:⋆*･ﾟ:⠀ *⋆.*:･ﾟ .: ⋆*･ﾟ: .⋆");
		System.out.println("    Menu Selection               ");
		System.out.println("  (1) Look up film by ID         ");
		System.out.println("  (2) Look up film by keyword    ");
		System.out.println("  (3) Exit                       ");
		System.out.println("☾ ⋆*･ﾟ:⋆*･ﾟ:⠀ *⋆.*:･ﾟ .: ⋆*･ﾟ: .⋆");
	}

	public void printWelcomeMenu() {
		System.out.println("╔═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══╗");
		System.out.println("     Welcome Loyal Customer!");
		System.out.println("╚═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══╝");
	}
	
	public void promptAgain() {
		System.out.println("☾ ⋆*･ﾟ:⋆*･ﾟ:⠀ *⋆.*:･ﾟ .: ⋆*･ﾟ: .⋆");
		System.out.println("Would you like to search again? (Yes) or (No)"); 
		String input = kb.next();
		if (input.equalsIgnoreCase("y") | input.equalsIgnoreCase("yes")) {
			startUserInterface();
		} else {
			System.out.println("☾ ⋆*･ﾟ:⋆*･ﾟ:⠀ *⋆.*:･ﾟ .: ⋆*･ﾟ: .⋆");
			System.out.println("\nHave a lovely day!");
		}
	}
}

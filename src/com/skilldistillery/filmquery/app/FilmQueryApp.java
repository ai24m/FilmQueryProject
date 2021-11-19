package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner kb = new Scanner(System.in);

		startUserInterface(kb);

		kb.close();
	}

	private void startUserInterface(Scanner kb) {
		int input = 0;
		printMenu();

		switch (input) {

		}
	}

	public void printMenu() {
		System.out.println("┌───────────────────┐ ");
		System.out.println("|  Welcome, user :  | ");
		System.out.println("|  Please select    | ");
		System.out.println("|  (1) Look up film by ID       | ");
		System.out.println("|  (2) Look up film by keyword  | ");
		System.out.println("|  (3) Exit  | ");
		System.out.println("└─────┘ ");
		System.out.println("Please select: ");
		System.out.println("(1) to Begin");
		System.out.println("(2) for Rules");
		System.out.println("(3) to Exit");
		String playerInput = kb.nextLine();
	}

}

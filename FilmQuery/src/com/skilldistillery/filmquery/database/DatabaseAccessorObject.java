package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.*;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
//No string returns :-)
public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "root";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmByID(int filmID) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT DISTINCT film.id, film.title, description, release_year, language_id, rental_duration," 
					+ "rental_rate, length, replacement_cost, rating, special_features, language.name "
					+ " FROM film JOIN language ON film.language_id = language.id"
					+ " WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film();
				film.setfilmID(rs.getInt("film.id"));
				film.setTitle(rs.getString("film.title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getShort("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setLanguage(rs.getString("language.name"));		
				film.setActors(findActorsByFilmID(rs.getInt("film.id")));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	
	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> filmByKeyword = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT DISTINCT film.id, title, description, release_year, language_id, rental_duration," 
					+ "rental_rate, length, replacement_cost, rating, special_features, language.name"
					+ " FROM film JOIN language ON film.language_id = language.id"
					+ " JOIN film_actor ON film.id = film_actor.film_id"
					+ " WHERE film.title LIKE ? OR film.description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setfilmID(rs.getInt("film.id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getShort("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setLanguage(rs.getString("language.name"));
				film.setActors(findActorsByFilmID(rs.getInt("film.id")));
				filmByKeyword.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmByKeyword;
	}

	@Override
	public List<Actor> findActorsByFilmID(int filmID) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT actor.id, actor.first_name, actor.last_name "
					+ " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
					+ " JOIN film ON film.id = film_actor.film_id "
					+ " WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				int counter = rs.getInt("film.id");
//				while (counter-- > 1) {
					Actor actor = new Actor();
					actor.setId(rs.getInt("actor.id"));
					actor.setFirstName(rs.getString("actor.first_name"));
					actor.setLastName(rs.getString("actor.last_name"));
					actors.add(actor);
//				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}
	
	@Override
	public Actor findActorByID(int actorID) {
		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first name"));
				actor.setLastName(rs.getString("last name"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}
}

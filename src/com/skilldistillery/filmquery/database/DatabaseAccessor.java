package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;


// what database is doing 
public interface DatabaseAccessor {
  public Film findFilmByID(int filmID);
  public List<Film> findFilmByKeyword(String keyword);
  public List<Actor> findActorsByFilmID(int filmID);
  public Actor findActorByID(int actorID);
}

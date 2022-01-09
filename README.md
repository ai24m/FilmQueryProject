# FilmQuery Project


https://user-images.githubusercontent.com/89150394/148664910-76d15f4a-e429-4ce0-b7a7-740960af367c.mov

## Description
The FilmQuery Project was a command-line application that utilized ORM (Object-Related Mapping) with JDBC (Java Database Connectivity). The idea was to prompt a user with a menu that asked whether they would like to:
(1) conduct a search for a film through its unique "ID"
(2) by a "keyword" that matched a film title or description.
Every instance that the ID or keyword finds a match, then the user is given the corresponding film with information of its title, year, rating, description, language, and list of starring actors.

To build the application, the Database Accessor interface was constructed, which held methods that returned objects, such as Film, Actor, and List<Actor> that would later be extracted from a mySQL database. There were two classes Film and Actor that retrieved certain attributes from columns in various tables of a mySQL database. Afterwards, both were implemented into the DatabaseAccessor(DBA) interface. This allowed for the main application class FilmQueryApp to call for information via the DBA.

## Challenges
Having never worked with mySQL, implementing DBA and calling for the correct information with the proper JDBC code was very challenging. There were many failed attempts in calling the wrong information, or not populating a list of actors per film. Tinkering with the DBA methods, specifically the driver that bridged DBMS to JDBC types. However, with prepared statements and the application pieced together, it allowed an accessible range to both the Actor and Film methods that were called to retrieve data from mySQL. 

## Resources
*Eclipse
  
*Atom
  
*SkillDistillery class resources
  
*MySQL with MAMP
  
*Maven Dependencies
  
  

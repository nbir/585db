Name:                   Nibir Bora
USC ID:                 1469975864
email:                  nbora@usc.edu
Blackboard user ID:     nbora



IMPORTANT
============================================================
	This project was built on a Mac using MySQL as the 
	back-end database.


DATABASE
============================================================
	Default MySQL settings are hard-coded in the source-code, 
	but can be overridden by specifying the settings in the 
	``db-settings.txt`` file. The program will runs fine 
	even without the ``db-settings.txt`` file.

	The default settings connect to a MySQL Server hosted on 
	public domain, so can be accessed from the WWW. Also, 
	the default database has all data loaded into it, so the 
	complete project can be run using the default settings.


COMPILE
============================================================

javac hw2.java


EXECUTE
============================================================

java hw2 query_type [object_type other_parameters | demo number]


	For successful execution, MySQL-JDBC driver needs to be 
	in the classpath.
    	OR
	Place the ``com`` folder in the running directory of 
	``hw2.class``. The ``con`` folder contains all 
	MySQL-JDBC driver classes.


EXECUTABLE .jar
============================================================
	In case of any error in the above execution process, 
	``hw2.jar`` executable can be used instead. The JAR 
	contains all required drivers and files. It can be 
	executed similar class execution, just by adding the 
	"-jar" directive.

java -jar hw2.jar query_type [object_type other_parameters | demo number]



FILE STRUCTURE
============================================================
.-- |
	|--	hw2.java
	|--	db-settings.txt
	|--	map.jpg
	|
	|--	/com/..
	|
	|--	hw2.jar
	|
	|--	createdb.sql
	|--	dropdb.sql
	|
	|--	readme.txt



============================================================
NOTE: Executing without any parameters simply shows the GUI 
	  with the map and all building polygons and hydrant 
	  locations.
	  
			java hw2
				OR
			java -jar hw2.jar
				OR
			Simply double-click ``hw2.jar``



P.S.- ALL ABOVE MENTIOND EXECUTION TECHNIQUES AND MYSQL 
	  DATABASE HAD BEEN TESTED AND FOUND WORKING ON MAC OSX 
	  10.8 AND MICROSOFT WINDOWS 8 OPERATING SYSTEMS. THE GUI
	  INTERFACE MAY BE RENDERED INCORRECTLY IN WINDOWS.

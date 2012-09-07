game_of_life
============

Game of Life: Initial draft

1. This project was developed JDK1.6. So, kindly use JDK1.6 or above for compiling and building.
2. This project utilizes Apache Ant 1.8.4 for compiling & building the package.
3. This project uses JUnit4 for executing the unit test cases of the project.
4. Ant build task has Clean, Compile & Export to Jar functionality. By default ANT will perform all operations and export to jar.
5. Input for the project has to be given in a text file with following assumptions:
	* Alive cells in the universe is considered & represented as "1" (instead of X)
	* Dead cells in the universe is considered & represented as "0" (instead of -)
So, please provide input file with 1's & 0's with tab spaced and multi line values.
6. Sample text input file is provided along with this package, along with some predefined input patterns (Boat, Blinker, Block, Toad pattern).
7. To execute the project, either export project to JAR using ANT build & run
	java -jar <jar_file_path> <text_input_pattern_file_path>
or compile the project using ANT Compile and run
	java com.game.GameofLife <text_input_pattern_file_path>
8. Kindly let me know through mail, if you have any problem in accessing this package.


Karthikeyan.R
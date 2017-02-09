# Toy Robot Test  

## Prerequisites  
- Java (JDK or JRE) 7 or higher  
- Gradle (see here: https://docs.gradle.org/current/userguide/installation.html)  

## Testing
1. Via bash/sh etc, 'cd' to the project root  
2. Execute 'gradle test'  

- Test results in HTML format will be found at 'build/reports/tests/test/index.html'  
- Test results in XML format will be found at 'build/test-results/test/'  


## Build  
1. Via bash/sh etc, 'cd' to the project root  
2. Execute 'gradle -i clean jar'  

- A runnable jar file called robot.jar will be found at 'build/libs/robot.jar'  
- Note: This jar will be roughly 7MB as it includes the Groovy runtime as well  

## Run
### Via CLI with manual input  
1. Navigate to where the robot.jar file is located  
2. Execute 'java -jar robot.jar'  
3. Issue commands to the app as per acceptable commands (type "help" for more info)  

### Piping input from a file  
1. Navigate to where the robot.jar file is located  
2. Execute 'java -jar robot.jar < file.txt' where 'file.txt' is the file containing command you wish to enter into the app  

## Example Usage

### Via stdin
$ java -jar robot.jar  
help  
Application accepts the input commands [PLACE, MOVE, LEFT, RIGHT, REPORT, HELP, EXIT, BYE].  
These can be issued in lowercase or uppercase.  
A PLACE command must first be issued before any further commands.  
  
PLACE X,Y,F  
	- Places a robot on table at coordinates specified in X,Y,  
	  facing the direction specified in F which can be one of [NORTH, SOUTH, EAST, WEST].  
MOVE  
	- Moves robot by one coordinate in the direction its currently facing,  
	  unless it is at the edge of the table.  
LEFT  
	- Turns robot left.  
RIGHT  
	- Turns robot right.  
REPORT  
	- Prints current coordinate of robot and the direction it is facing.  
HELP  
	- Prints this message  
EXIT  
	- Closes this app  
place 2,1,NORTH  
report  
2,1,NORTH  
right  
move  
report  
3,1,EAST  
MOVE  
MOVE  
Specified coordinate is not within the bounds of the table  
LEFT  
move  
report  
4,2,NORTH  
exit  

### Via piped file
$ java -jar robot.jar < a.txt  
0,1,NORTH  
  
## Example Compilation Output  
$ gradle -i clean jar  
Initialized native services in: /Users/philipwindeyer/.gradle/native  
Starting Build  
Selected primary task 'clean' from project :  
Selected primary task 'jar' from project :  
Tasks to be executed: [task ':clean', task ':compileJava', task ':compileGroovy', task ':processResources', task ':classes', task ':jar']  
:clean (Thread[Daemon worker Thread 9,5,main]) started.  
:compileJava (Thread[Daemon worker Thread 9,5,main]) started.  
:compileGroovy (Thread[Daemon worker Thread 9,5,main]) started.  
  
BUILD SUCCESSFUL  
  
Total time: 4.091 secs  
Stopped 1 compiler daemon(s).  

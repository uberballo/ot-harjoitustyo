# Dungeon crawler-like 

Dungeon crawler-like game. Game where the player has to navigate trough procedurally generated map. Avoiding obstacles and collecting coins along the way. You have to think quickly and find the stair to the next floor quickly, as the time is running out and every step costs more time as you move on. Coins give you more time, but is it worth to collect all of them?  

## How to use  
Start by installing the software by using command line functions found below or by running the jar file.  
From the starting screen, when you're ready to start playing, press the start button. You control your character by using WASD-keys. After the time runs out, you have choice to save your score or not.  

## Documentation 
[Timesheet](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/timesheet.md)  
[Software requirements specification](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/SoftwareRequirementsSpecifications.md)  
[Architecture](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/Architecture.md)
[Test documentation](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/Testing.md)

## Releases
[Final week](https://github.com/uberballo/ot-harjoitustyo/releases/tag/FinalWeek)  
[Week 6](https://github.com/uberballo/ot-harjoitustyo/releases/tag/Viikko6)  
[Week 5](https://github.com/uberballo/ot-harjoitustyo/releases/tag/viikko5)  

## Command line functions
### Running the software
The game can be run by using the following command in the directory, where the game is located
```
mvn compile exec:java -Dexec.mainClass=ui.DungeonCrawlerUi
```

### Testing

Tests can be ran with 

```
mvn test
```

Test coverage report can be made with

```
mvn jacoco:report
```
Test coverage report can be found from target/site/jacoco/index.html  

Checkstyle report can be generated with

```
mvn jxr:jxr checkstyle:checkstyle
```

### Generating .jar file

command
```
mvn package
```
generates executable .jar file to the _target_ folder. You may have to give permission to execute the file.   

Run the file by using:  
```
java -jar DungeonCrawler-1.1-SNAPSHOT.jar
```


## Javadoc  
Javadoc can be made with 
```
mvn javadoc:javadoc
```
Javadoc can be found from the previously mentioned _target_ folder.

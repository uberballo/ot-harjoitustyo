# Dungeon crawler-like 

Dungeon crawler-like game. Game where the player has to navigate trough procedurally generated map. Avoiding obstacles and collecting prizes along the way.

## Documentation 
[Timesheet](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/timesheet.md)  
[Software requirements specification](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/SoftwareRequirementsSpecifications.md)  
[Architecture](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/Architecture.md)

## Releases
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

## Javadoc  
Javadoc can be made with 
```
mvn javadoc:javadoc
```
Javadoc can be found from the previously mentioned _target_ folder.

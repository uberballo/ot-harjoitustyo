# Dungeon crawler-like 

Dungeon crawler-like game. Game where the player has to navigate trough procedurally generated map. Avoiding obstacles and collecting prizes along the way.

## Documentation 
[Timesheet](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/timesheet.md)  
[Software requirements specification](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/SoftwareRequirementsSpecifications.md)  
[Architecture](https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/pictures/architecture.png)

## Command line functions
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

### Generating .jar file

command
```
mvn package
```
generates executable .jar file to the _target_ folder. You may have to give permission to execute the file.

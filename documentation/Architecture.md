# Architecture description
## Structure
<img src="https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/pictures/architecture.png" >
Game package requires the DungeonCrawler.map, which contains all the map building functions. Ui recieves game object which handles all the movement.  

## Main functionality
### Logging in
Not implemented.
<img src="https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/pictures/userSequenceDiagram.png" >
Users need to log in to save their results. When clicked log in, ui calls the function login. Loginservice fetches the username and returns user if found. If the user is found, loginService returns true and thus, user is set to logged in.

### Character movement
<img src="">  
After the user presses play, the game starts to listen for their actions. If player presses "W", it will call class `game` and moves the character up. When moved, game checks the characters position, if there are anything that would affect the game, for example: a coin that will give you 100 more time. DungeonCrawlerUi checks if the game has ended by using game.checkIfGameIsOver(), and if it receives true, game ends and endScreen() is called. 

### Starting the game
<img src="https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/pictures/gameSequenceDiagram.png" >
When user click play, the new scene is fetched and set as the main scene and showed to the user.

## Database  
`HighScoreDao` controls the highscores made in the game. Database contains table highscores, whichs contains name and score made by the player. Names aren't used.  

### CREATE TABLE-statements
    
    CREATE TABLE IF NOT EXISTS highscore(
		 			 + "id integer PRIMARY KEY,
					 + "name text NOT NULL,
					 + "score integer);
           
### SQL queries
Inserting scores to the table: 
        
        INSERT INTO highscore(name,score)
        VALUES(?,?);
        
Getting scores from the database:
        
        SELECT score FROM highscore ORDER BY score DESC;
        
## 

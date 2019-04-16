#Architecture description
##Structure
<img src="https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/pictures/architecture.png" width="160">
Game package requires the DungeonCrawler.map, which contains all the map building functions. Ui recieves game object which handles all the movement.  

##Main functionality
###Logging in
<img src="https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/pictures/userSequenceDiagram.png" width="160">
Users need to log in to save their results. When clicked log in, ui calls the function login. Loginservice fetches the username and returns user if found. If the user is found, loginService returns true and thus, user is set to logged in.

###Starting the game
<img src="https://github.com/uberballo/ot-harjoitustyo/blob/master/documentation/pictures/gameSequenceDiagram.png" width="160">
When user click play, the new scene is fetched and set as the main scene and showed to the user.

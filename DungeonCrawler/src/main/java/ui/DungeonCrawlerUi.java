/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import map.Map;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author kasper
 */
public class DungeonCrawlerUi extends Application {

	private Map map;
	private int[][] currentMap;
	private Stage stage;
	//Currently doesn't have any functionality. Only initializes a map.

	public static void main(String[] args) {
		launch(args);
	}

	public DungeonCrawlerUi() {
		this.map = new Map(80, 80, 10);
		currentMap = map.getMap();

	}

	public void start(Stage stage) {
		this.stage = stage;
		Scene gameScene = gameScreen();
		Scene startScene = startScreen(gameScene);

		stage.setTitle("Dungeon Crawler");
		stage.setScene(startScene);

		ArrayList<String> input = new ArrayList<String>();
		
		gameScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
					System.out.println(code);
 
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });
 
        gameScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
					System.out.println(code);
                }
            });
		stage.show();
	}

	public Scene startScreen(Scene gameScene) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid, 1280, 800);

		Text title = new Text("Dungeon Crawler");
		title.setFont(Font.font("Comic Sansa", FontWeight.NORMAL, 20));
		grid.add(title, 0, 0, 2, 1);

		Button button = new Button("Play");
		grid.add(button, 1, 1);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				stage.setScene(gameScene);
			}
		});

		return scene;
	}

	public Scene gameScreen() {
		Group root = new Group();
		Scene scene = new Scene(root);

		Canvas canvas = new Canvas(1280, 800);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		System.out.println(System.getProperty("user.dir"));

		Image floor = new Image(new File("src/main/resources/floor.png").toURI().toString());
		Image wall = new Image(new File("src/main/resources/wall.png").toURI().toString());
		Image character = new Image(new File("src/main/resources/character.png").toURI().toString());
 

		//currently dimensions aren't correct. But still gives a view of the map.
		for (int i = 0; i < map.getY(); i++) {
			for (int j = 0; j < map.getX(); j++) {
				if (currentMap[i][j] == 1) {
					gc.drawImage(floor, i * 16, j * 10);
				} else {
					gc.drawImage(wall, i * 16, j * 10);
				}

			}
		}

		gc.drawImage(character, 40 * 16, 40 * 10);

		return scene;

	}
}

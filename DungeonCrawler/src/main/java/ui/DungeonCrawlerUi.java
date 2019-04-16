/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import game.Game;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
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
import javafx.scene.input.KeyCode;
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

	private int[][] currentMap;
	private Stage stage;
	private Game game;
	private int mapHeight;
	private int mapWidth;
	private Scene gameScene;
	private Group root;

	private Image floor = new Image("floor.png");
	private	Image wall = new Image("wall.png");
	private	Image character = new Image("character.png");

	//Currently very ugly code. Needs to be refactored

	public static void main(String[] args) {
		launch(args);
	}

	public DungeonCrawlerUi() {
		this.root = new Group();
		this.mapHeight = 80;
		this.mapWidth = 80;
		int rooms = 10;
		int startY = 40;
		int startX = 40;
		this.game = new Game(this.mapHeight, this.mapWidth, rooms, startY, startX);
		this.currentMap = game.getMap();
	}

	public void start(Stage stage) {
		this.stage = stage;
		gameScreen();
		Scene startScene = startScreen(gameScene);

		stage.setTitle("Dungeon Crawler");
		stage.setScene(startScene);

		ArrayList<String> input = new ArrayList<>();

		gameScene.setOnKeyPressed(event -> {
			String code = event.getCode().toString();

			if (!input.contains(code)) {
				input.add(code);
			}
		});

		gameScene.setOnKeyReleased(event -> {
			String code = event.getCode().toString();
			input.remove(code);
		});

		new AnimationTimer() {

			@Override
			public void handle(long nykyhetki) {
				System.out.println(input.toString());
				if (input.contains("W")) {
					game.moveCharacterUp();
					drawScreen();
					input.remove(("W"));
				} else if (input.contains("A")) {
					game.moveCharacterLeft();
					drawScreen();
					input.remove(("A"));
				} else if (input.contains("S")) {
					game.moveCharacterDown();
					drawScreen();
					input.remove(("S"));
				} else if (input.contains("D")) {
					game.moveCharacterRight();
					drawScreen();
					input.remove(("D"));
				}
				/*
				if (input.contains("W")) {
					game.moveCharacterUp();
					stage.setScene(gameScreen());
					input.remove(("W"));
				} else if (input.contains("A")){
					game.moveCharacterLeft();
					stage.setScene(gameScreen());
					input.remove(("A"));
				} else if (input.contains("S")){
					game.moveCharacterDown();
					stage.setScene(gameScreen());
				} else if (input.contains("D")){
					game.moveCharacterRight();
					stage.setScene(gameScreen());
				} else {
					game.printLocation();
				}
				 */
			}
		}.start();

		stage.show();
	}

	public void drawScreen() {

		Canvas canvas = new Canvas(1280, 800);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		//System.out.println(System.getProperty("user.dir"));


		//currently dimensions aren't correct. But still gives a view of the map.
		for (int i = 0; i < this.mapHeight; i++) {
			for (int j = 0; j < this.mapWidth; j++) {
				if (currentMap[i][j] == 1) {
					gc.drawImage(floor, i * 16, j * 10);
				} else if (currentMap[i][j] == 2) {
					gc.drawImage(floor, i * 16, j * 10);
					gc.drawImage(character, i * 16, j * 10);
				} else {
					gc.drawImage(wall, i * 16, j * 10);
				}

			}
		}
		this.root.getChildren().clear();
		this.root.getChildren().add(canvas);
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

	public void gameScreen() {
		Scene scene = new Scene(this.root);

		Canvas canvas = new Canvas(1280, 800);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		/*
		Image floor = new Image("floor.png");
		Image wall = new Image("wall.png");
		Image character = new Image("character.png");
*/

		//System.out.println(System.getProperty("user.dir"));
		//Image floor = new Image(new File("main/images/floor.png").toURI().toString());
		//Image wall = new Image(new File("src/main/resources/wall.png").toURI().toString());
		//Image character = new Image(new File("src/main/resources/character.png").toURI().toString());
		//currently dimensions aren't correct. But still gives a view of the map.
		for (int i = 0; i < this.mapHeight; i++) {
			for (int j = 0; j < this.mapWidth; j++) {
				if (currentMap[i][j] == 1) {
					gc.drawImage(floor, i * 16, j * 10);
				} else if (currentMap[i][j] == 2) {
					gc.drawImage(floor, i * 16, j * 10);
					gc.drawImage(character, i * 16, j * 10);
				} else {
					gc.drawImage(wall, i * 16, j * 10);
				}

			}
		}

		this.gameScene = scene;
	}
}

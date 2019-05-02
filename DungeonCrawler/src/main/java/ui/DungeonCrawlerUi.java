/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.HighScoreDao;
import game.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
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
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author kasper
 */
public class DungeonCrawlerUi extends Application {

	private Stage stage;
	private Game game;
	private int mapHeight;
	private int mapWidth;
	private boolean gameIsRunning = false;
	private Scene gameScene;
	private Group root;

	//The images used by the game. Images are contained in the resources- folder.
	private Image floor = new Image("floor.png");
	private Image wall = new Image("wall.png");
	private Image character = new Image("character.png");
	private Image coin = new Image("coin.png");
	private Image stairs = new Image("stairs.png");

	private Timer timer;
	private HighScoreDao highScoreDao;

	public static void main(String[] args) {
		launch(args);
	}

	public DungeonCrawlerUi() {
		this.highScoreDao = new HighScoreDao();
		this.root = new Group();
		this.mapHeight = 80;
		this.mapWidth = 80;
		int rooms = 1;
		int startY = 40;
		int startX = 40;
		this.game = new Game(this.mapHeight, this.mapWidth, rooms, startY, startX);

		//Decreases the score every second. 
		this.timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (gameIsRunning) {
					game.decreaseTime();
				}
			}
		}, 1000, 1000);

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
				for (String i : input){
					System.out.println(i);
				}
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
				} else {
					drawScreen();
				}
				if (game.checkIfGameIsOver()) {
					gameIsRunning = false;
					endScreen();
				}
			}

		}.start();

		stage.show();
	}

	public Scene startScreen(Scene gameScene) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		Scene scene = new Scene(grid, 1280, 800);

		Text title = new Text("Dungeon Crawler");
		title.setFont(Font.font("Comic Sansa", FontWeight.NORMAL, 20));
		grid.add(title, 0, 0, 2, 1);

		grid.add((new Text("W,A,S,D to move")), 1, 10, 3, 3);

		Button button = new Button("Play");
		grid.add(button, 1, 1);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				gameIsRunning = true;
				stage.setScene(gameScene);
			}
		});

		return scene;
	}

	public void gameScreen() {
		Scene scene = new Scene(this.root);
		this.gameScene = scene;
		drawScreen();
	}

	public void endScreen() {
		this.game.setTime(100000);
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		Label gameOverText = new Label();
		gameOverText.setText("Game over");
		gameOverText.setFont(new Font("Comic sans", 35));

		Label score = new Label();
		score.setText("Rooms completed: " + (game.getRoomNumber() - 1));

		grid.add(gameOverText, 0, 0);
		grid.add(score, 0, 1);

		HBox buttons = new HBox();

		Button playAgainButton = new Button("Play again");

		playAgainButton.setOnAction(e -> {
			this.game = new Game();
			this.stage.setScene(this.gameScene);
			gameIsRunning = true;
			this.game.setTime(1000);
			start(this.stage);
			
		});

		Button saveScoreButton = new Button("Save your score");

		saveScoreButton.setOnAction(e -> {
			this.highScoreDao.insert("temp", game.getRoomNumber() - 1);
			showScoresScreen();
		});

		buttons.getChildren().addAll(playAgainButton, saveScoreButton);
		buttons.setSpacing(5);

		grid.add(buttons, 0, 2);

		Scene scene = new Scene(grid, 1280, 800);
		this.stage.setScene(scene);
	}

	public void showScoresScreen() {
		ArrayList<Integer> list = highScoreDao.getScores();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		Label title = new Label();
		title.setText("Scores");
		grid.add(title, 0, 0);
		int index = 1;
		for (int score : list) {
			Label text = new Label();
			text.setText(index + ". floor: " + score);
			grid.add(text, 0, index);
			index++;
			if (index > 10) {
				break;
			}
		}

		Button playAgainButton = new Button("Play again");

		playAgainButton.setOnAction(e -> {
			this.game = new Game();
			this.stage.setScene(this.gameScene);
			gameIsRunning = true;
			this.game.setTime(1000);
		});

		grid.add(playAgainButton, 0, index);

		Scene scene = new Scene(grid, 1280, 800);
		this.stage.setScene(scene);
	}

	public void drawScreen() {
		int[][] currentMap = game.getMap();

		Canvas canvas = new Canvas(1280, 800);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		for (int i = 0; i < this.mapHeight; i++) {
			for (int j = 0; j < this.mapWidth; j++) {
				if (currentMap[i][j] == 1) {
					gc.drawImage(floor, i * 16, j * 10);

				} else if (currentMap[i][j] == 2) {
					gc.drawImage(floor, i * 16, j * 10);
					gc.drawImage(character, i * 16, j * 10);

				} else if (currentMap[i][j] == 3) {
					gc.drawImage(floor, i * 16, j * 10);
					gc.drawImage(coin, i * 16, j * 10);

				} else if (currentMap[i][j] == 4) {
					gc.drawImage(floor, i * 16, j * 10);
					gc.drawImage(stairs, i * 16, j * 10);

				} else {
					gc.drawImage(wall, i * 16, j * 10);
				}
			}
		}

		gc.setTextAlign(TextAlignment.LEFT);
		gc.setFont(new Font("Comic sans", 25));
		gc.setFill(Color.WHITE);
		gc.fillText("Time: " + game.getTime(), 10, 40);
		gc.fillText("Floor: " + game.getRoomNumber(), 10, 70);

		this.root.getChildren().clear();
		this.root.getChildren().add(canvas);
	}

	//When the window is closed, we turn of the timer.
	@Override
	public void stop() {
		this.timer.cancel();
	}

}

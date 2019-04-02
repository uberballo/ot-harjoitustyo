/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.Ui;

import dungeoncrawler.Map.Map;
import java.io.File;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
/**
 *
 * @author kasper
 */
public class DungeonCrawlerUi extends Application{
	
		private Map map;
		private int[][] currentMap;
		//Currently doesn't have any functionality. Only initializes a map.
		
		 public static void main(String[] args) {
        launch(args);
    }
	
		public DungeonCrawlerUi(){
			this.map = new Map(160,100);
			currentMap = map.getMap();
			for (int i =0;i<map.getY();i++){
				for (int j = 0; j<map.getX();j++){
					System.out.print(currentMap[i][j]);
				}
				System.out.println("");
			} 

		}

		public void start(Stage stage){
			Group root = new Group();
			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.setTitle("Dungeon crawler");

			Canvas canvas = new Canvas(1280,800);
			root.getChildren().add(canvas);

			GraphicsContext gc = canvas.getGraphicsContext2D();
			
			System.out.println(System.getProperty("user.dir"));
			
			Image seina = new Image(new File("src/main/resources/seina.png").toURI().toString());
			
			for (int i =0;i<map.getY();i++){
				for (int j = 0; j<map.getX();j++){
					if(currentMap[i][j]==1)
					gc.drawImage(seina, i*7, j*7);
					
					
				}
			} 
			//gc.drawImage(seina, 180, 100);
			
			
			stage.show();

				
		}
}

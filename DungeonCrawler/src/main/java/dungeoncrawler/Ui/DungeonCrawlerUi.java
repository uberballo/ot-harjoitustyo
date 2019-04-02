/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeoncrawler.Ui;

import dungeoncrawler.Map.Map;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 *
 * @author kasper
 */
public class DungeonCrawlerUi extends Application{
	
		//Currently doesn't have any functionality. Only initializes a map.
		
		 public static void main(String[] args) {
        launch(args);
    }
	
		public DungeonCrawlerUi(){
			Map map = new Map();
			int[][] currentMap = map.getMap();
			for (int i =0;i<map.getY();i++){
				for (int j = 0; j<map.getX();j++){
					System.out.print(currentMap[i][j]);
				}
				System.out.println("");
			} 

		}

		public void start(Stage stage){

			stage.setTitle("Dungeon crawler");
			stage.show();

				
		}
}

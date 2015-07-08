package application;
	
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {
	Player player;
	FileChooser fileChooser;
	
	public void start(final Stage primaryStage) {
		
		//add menu items
		MenuItem open = new MenuItem("Open");
		Menu file = new Menu("File");
		MenuBar menu = new MenuBar();
		file.getItems().add(open);
		menu.getMenus().add(file);
		
		//add functionality to "Open" menu item
		fileChooser = new FileChooser();
		open.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				player.player.pause();
				File file = fileChooser.showOpenDialog(primaryStage);
				if(file != null){
					try {
						player = new Player(file.toURI().toURL().toExternalForm());
						Scene scene = new Scene(player, 854, 540, Color.BLACK);
						primaryStage.setScene(scene);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		player = new Player("file:///C:/starwars7.mp4");
		player.setTop(menu);
		Scene scene = new Scene(player, 854, 540, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

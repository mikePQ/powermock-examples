package examples.library.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LibraryApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override public void start(Stage primaryStage) {
		final String appName = "Library App";
		try {
			FXMLLoader loader = new FXMLLoader(
					LibraryApp.class.getResource("/fxml/libraryMainView.fxml"));
			AnchorPane pane = loader.load();

			LibraryController libraryController = loader.getController();
			libraryController.setPrimaryStage(primaryStage);

			Scene scene = new Scene(pane);
			primaryStage.setTitle(appName);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

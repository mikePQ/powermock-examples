package examples.library.ui;

import examples.library.ui.controllers.LibraryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibraryApp extends Application {

	private static final Logger logger = LoggerFactory.getLogger(LibraryApp.class);

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
			logger.warn(e.getMessage(), e);
		}
	}
}

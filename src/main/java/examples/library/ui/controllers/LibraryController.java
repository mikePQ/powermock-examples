package examples.library.ui.controllers;

import examples.database.api.DatabaseActionResult;
import examples.database.api.DatabaseActionResultWithValue;
import examples.library.ui.model.Book;
import examples.library.ui.model.BookDatabase;
import examples.library.ui.LibraryApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
	private static final Logger logger = LoggerFactory.getLogger(LibraryController
			.class);

	private Stage primaryStage;

	@FXML private TableView<Book> booksTable;
	@FXML private TableColumn<Book, String> titleColumn;
	@FXML private TableColumn<Book, String> authorColumn;
	@FXML private TableColumn<Book, String> publisherColumn;
	@FXML private TableColumn<Book, Integer> yearColumn;

	@Override public void initialize(URL location, ResourceBundle resources) {
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

		refreshTable();
	}

	public void addBook() {
		try {
			FXMLLoader loader = new FXMLLoader(
					LibraryApp.class.getResource("/fxml/addBookView.fxml"));
			AnchorPane pane = loader.load();

			Scene scene = new Scene(pane);
			Stage secondaryStage = new Stage();

			AddBookController addBookController = loader.getController();
			addBookController.setStage(secondaryStage);
			addBookController.setParentController(this);

			secondaryStage.initOwner(primaryStage);
			secondaryStage.initModality(Modality.WINDOW_MODAL);

			secondaryStage.setTitle("Add book");
			secondaryStage.setScene(scene);
			secondaryStage.setResizable(false);
			secondaryStage.show();

		}
		catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
	}

	public void deleteBook() {
		Book selectedItem = booksTable.getSelectionModel()
				.getSelectedItem();

		DatabaseActionResult deleteResult = BookDatabase.get()
				.delete(selectedItem.getId());

		if (!deleteResult.isSuccessful()) {
			logger.warn(deleteResult.getErrorMessage());
		}

		refreshTable();
	}

	void refreshTable() {
		BookDatabase database = BookDatabase.get();
		DatabaseActionResultWithValue<Collection<Book>> getBooksResult = database
				.getByPredicate(
				book -> true);

		if (getBooksResult.isSuccessful()) {
			Collection<Book> booksCollection = getBooksResult.getValue();
			booksTable.setItems(FXCollections.observableArrayList(booksCollection));
		}
		else {
			logger.warn(getBooksResult.getErrorMessage());
		}
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}

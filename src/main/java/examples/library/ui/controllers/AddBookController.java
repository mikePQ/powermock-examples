package examples.library.ui.controllers;

import examples.library.ui.model.Book;
import examples.library.ui.model.BookDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.UUID;

public class AddBookController {
	private Stage stage;
	private LibraryController parentController;

	@FXML private TextField titleField;
	@FXML private TextField authorField;
	@FXML private TextField publisherField;
	@FXML private TextField yearField;

	private static String getFieldValue(TextField field) {
		return field.getText() != null ? field.getText() : "";
	}

	private static Integer getFieldIntegerValue(TextField field) {
		try {
			return Integer.valueOf(getFieldValue(field));
		}
		catch (NumberFormatException e) {
			return LocalDate.now()
					.getYear();
		}
	}

	public void handleOk() {
		Book book = new Book(UUID.randomUUID()
				.toString(), getFieldValue(titleField), getFieldValue(authorField),
				getFieldValue(publisherField), getFieldIntegerValue(yearField));

		BookDatabase.get()
				.add(book);

		parentController.refreshTable();
		stage.close();
	}

	public void handleCancel() {
		stage.close();
	}

	void setStage(Stage stage) {
		this.stage = stage;
	}

	void setParentController(LibraryController parentController) {
		this.parentController = parentController;
	}
}

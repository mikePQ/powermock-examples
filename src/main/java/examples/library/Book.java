package examples.library;

import examples.database.api.Identifiable;

public class Book implements Identifiable {
	private final String id;
	private final String title;
	private final String author;
	private final String publisher;
	private final Integer year;

	public Book(String id, String title, String author, String publisher, Integer year) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
	}

	@Override public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public Integer getYear() {
		return year;
	}
}

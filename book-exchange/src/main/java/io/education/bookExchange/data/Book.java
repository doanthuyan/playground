package io.education.bookExchange.data;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Book implements Serializable,Comparable<Book> {
    private static final long serialVersionUID = 1L;
    private static final Comparator<Book> NATURAL_ORDER_COMPARATOR =     
            Comparator.comparing(Book::getTitle)
                      .thenComparing(Book::getAuthor)
                      .thenComparing(Book::getYear)
                      .thenComparing(Book::getLanguage);
    
    @Id @GeneratedValue @Column(name="bookId")
    private long id;
	
    

	private String author, country, imageLink, language, link, title;
	private int pages;
	private int year;
	private int amountInStock;
	
	public Book() {
		super();
	}
	public Book(String author, String country, String imageLink, String language, String link, String title, int pages,
			int year, int amountInStock) {
		super();
		this.author = author;
		this.country = country;
		this.imageLink = imageLink;
		this.language = language;
		this.link = link;
		this.title = title;
		this.pages = pages;
		this.year = year;
		this.amountInStock = amountInStock;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getAmountInStock() {
		return amountInStock;
	}
	public void setAmountInStock(int amountInStock) {
		this.amountInStock = amountInStock;
	}
	@Override
	public String toString() {
		return "Book [author=" + author + ", country=" + country + ", imageLink=" + imageLink + ", language=" + language
				+ ", link=" + link + ", title=" + title + ", pages=" + pages + ", year=" + year + ", amountInStock="
				+ amountInStock + "]";
	}
	@Override
	public int compareTo(Book other) {
		
		return NATURAL_ORDER_COMPARATOR.compare(this, other);
	}
	
    
}

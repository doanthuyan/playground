package io.education.bookExchange.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    private long id;

    private Book book;
    private int amount;
    private double price;
	
	public Item() {
		super();
	}
	public Item(Book book, int amount, double price) {
		super();
		this.book = book;
		this.amount = amount;
		this.price = price;
	}
	
	@OneToOne(optional=false, mappedBy="customerRecord")
	public Book getBook() {
		return book;
	}
	public int getAmount() {
		return amount;
	}
	public double getPrice() {
		return price;
	}
}

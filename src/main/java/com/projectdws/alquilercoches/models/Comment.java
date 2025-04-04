package com.projectdws.alquilercoches.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID = 0;

    private int numberStars;
    private String message;
	@ManyToOne
	private User author;
	@ManyToOne
	private Car carCommented;

    public Comment() {}

	public Comment(User author, int numberStars, String message, Car carCommented) {
		this.author = author;
        this.numberStars = numberStars;
		this.message = message;
		this.carCommented = carCommented;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

    public int getNumberStars() {
		return numberStars;
	}

	public void setNumberStars(int numberStars) {
		this.numberStars = numberStars;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
    
    public Car getCarCommented() {
		return carCommented;
	}

	public void setCarCommented(Car carCommented) {
		this.carCommented = carCommented;
	}	

	@Override
	public String toString() {
		return "Comment [ID: " + ID + ", Author: " + author.getName() + "NumberStars: " + numberStars + ", Message: " + message + "]";
	}
}

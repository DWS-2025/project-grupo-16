package com.projectdws.alquilercoches.models;

public class Comment {

    private int ID;
    private int numberStars;
    private String message;
	private User author;
	private Car carCommented;
    

    protected Comment() {}

	public Comment(int numberStars, String message) {
        this.numberStars = numberStars;
		this.message = message;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
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

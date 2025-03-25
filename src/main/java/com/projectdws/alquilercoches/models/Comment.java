package com.projectdws.alquilercoches.models;

public class Comment {

    private long ID = 0;
    private int numberStars;
    private String message;
	private User author;
	private Car carCommented;
    

    public Comment() {}

	public Comment(User author, int numberStars, String message) {
		this.author = author;
        this.numberStars = numberStars;
		this.message = message;
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

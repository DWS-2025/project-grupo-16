package com.projectdws.alquilercoches.models;

public class Comment {

    private int ID_comment;
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
		return ID_comment;
	}

	public void setID(int ID_comment) {
		this.ID_comment = ID_comment;
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
		return "Comment [ID_comment: " + ID_comment + ", Author: " + author.getName() + "NumberStars: " + numberStars + ", Message: " + message + "]";
	}
}

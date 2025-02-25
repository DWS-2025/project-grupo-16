package main.java.com.classes;

public class comment {

    private int ID_comment;
    private int numberStars;
    private String message;
    private user author;
    private car carCommented;

    protected comment() {}

	public comment(int numberStars, String message) {
        this.numberStars = numberStars;
		this.message = message;
	}

	public int getID() {
		return ID_comment;
	}

	public void setID(int id) {
		this.id = ID_comment;
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

	public user getAuthor() {
		return author;
	}

	public void setAuthor(user author) {
		this.author = author;
	}
    
    public car getCarCommented() {
		return carCommented;
	}

	public void setCarCommented(car carCommented) {
		this.carCommented = carCommented;
	}	

	@Override
	public String toString() {
		return "Comment [ID_comment: " + ID_comment + ", Author: " + author.getName() + "NumberStars: " numberStars + ", Message: " + message + "]";
	}
}

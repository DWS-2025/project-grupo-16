import java.util.ArrayList;
import java.util.List;

public class user {

    private int ID_user = 00;
    private String name;
    private String email;
    private String tlf;
    private List <Comment> comments = new ArrayList <> ();

    public user(){}

    public User(String name, String email, String tlf) {
        this.name = name;
        this.email = email;
        this.tlf = tlf;
    }

    public long getID() {
        return ID;
    }

    public void setID(long id) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    
}

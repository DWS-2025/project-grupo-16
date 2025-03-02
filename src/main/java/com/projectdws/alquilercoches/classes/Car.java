import java.util.ArrayList;
import java.util.List;

public class Car {
    private String name_car;
    private String car_image;
    private int licensePlate;
    private int kmsPerHour;
    private int year;
    private int price;
    private List<comment> comments = new ArrayList<>();

    public Car(String name_car, int licensePlate, int kmsPerHour, int year, int price) {
        this.name_car = name_car;
        this.car_image=car_image;
        this.licensePlate = licensePlate;
        this.kmsPerHour = kmsPerHour;
        this.year = year;
        this.price = price;
    }

    public String getName_car() {
        return name_car;
    }

    public void setName_car(String name_car) {
        this.name_car = name_car;
    }

    public void setCar_image(String car_image){
        this.car_image=car_image;
    }

    public String getCar_image(){
        return car_image;
    }

    public int getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(int licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getKmsPerHour() {
        return kmsPerHour;
    }

    public void setKmsPerHour(int kmsPerHour) {
        this.kmsPerHour = kmsPerHour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<comment> getComments() {
        return comments;
    }

    public void setComments(List<comment> comments) {
        this.comments = comments;
    }
}
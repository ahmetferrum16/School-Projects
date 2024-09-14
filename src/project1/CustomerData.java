package project1;

public class CustomerData {
    private String name;
    private String surname;
    private String country;
    private String city;
    private String occupation;

    //Default Constructor
    public CustomerData(){
        name = "No name entered yet.";
        surname = "No surname entered yet.";
        country = "No country entered yet.";
        city = "No city entered yet.";
        occupation = "No occupation entered yet.";
    }

    //Constructor with parameters
    public CustomerData(String name, String surname, String country, String city, String occupation){
        setName(name);
        setSurname(surname);
        setCountry(country);
        setCity(city);
        setOccupation(occupation);
    }

    //Copy Constructor
    public CustomerData(CustomerData other) {
        setName(other.name);
        setSurname(other.surname);
        setCountry(other.country);
        setCity(other.city);
        setOccupation(other.occupation);
    }

    //Getter and Setters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }


}
package project1;

public class Node {
    private int customerNumber;
    private CustomerData customerData;
    private int[] ratings;
    private Node next;


    //Constructor with parameters
    public Node(int customerNumber, CustomerData customerData, int[] ratings){
        this.customerNumber = customerNumber;
        this.customerData = customerData;
        this.ratings = ratings;
        this.next=null;
    }

    //Getter and Setters
    public CustomerData getCustomerData() {
        return new CustomerData(customerData);
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public Node getNext() {
        return next;
    }

    public int[] getRatings() {
        return ratings;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setRatings(int[] ratings) {
        this.ratings = ratings;
    }
}
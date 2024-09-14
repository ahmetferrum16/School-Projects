package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class LinkedList {
    private Node head;
    private int[][] allRatings;
    private int number;
    private String[] productsNames;

    //Constructor of LinkedList
    public LinkedList(){
        head=null;
        number = 0;
    }
    
    // Method to enter data into the linked list in sorted order
    public void enterData(int customerNumber, CustomerData customerData, int[] ratings) {
        Node newNode = new Node(customerNumber, customerData, ratings);

        if (head == null || customerNumber < head.getCustomerNumber()) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null && current.getNext().getCustomerNumber() < customerNumber) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }
    
    // Method to display the data in the linked list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.println("Customer Number: " + current.getCustomerNumber());
            System.out.println("Customer Data: Name - " + current.getCustomerData().getName() + ", Surname - " + current.getCustomerData().getSurname()+ ", Country - " + current.getCustomerData().getCountry() +
                    ", City - " + current.getCustomerData().getCity() + ", Occupation - " + current.getCustomerData().getOccupation());
            System.out.println("Ratings: ");
            for (int rating : current.getRatings()) {
                System.out.print(rating + " ");
            }
            System.out.println();
            current = current.getNext();
            System.out.println();
        }
    }

    // Method to read data from a file and fill the linked list and ratings array
    public void readingFromFile() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("Firma.txt"));
        String line;

        String[] productData = br.readLine().split(",");
        int totalProduct = Integer.parseInt(productData[0]);
        productsNames = new String[totalProduct];
        
        allRatings = new int[200][totalProduct+1];  //We recreated the allRatings variable inside the method because we have access to the totalProduct variable there.

        for (int i = 0; i < totalProduct; i++) {
            productsNames[i] = productData[i+1];
        }



        while ((line = br.readLine()) != null) {

            int customerNumber = Integer.parseInt(line.split(",")[0]);

            // Read customer data
            String[] customerInfo = line.split(",");
            String name = customerInfo[1];
            String surname = customerInfo[2];
            String country = customerInfo[3];
            String city = customerInfo[4];
            String occupation = customerInfo[5];
            CustomerData customerData = new CustomerData(name, surname, country, city, occupation);

            // Read ratings
            line = br.readLine();
            String[] ratingStr = line.split(",");
            int[] ratings = new int[allRatings[0].length-1]; //Stores the ratings

            for (int i = 0; i < ratings.length; i++) {
                ratings[i] = Integer.parseInt(ratingStr[i]);
            }

            enterData(customerNumber, customerData, ratings); // Call the enterData to enter data to the linked list.
            addToArray(allRatings, ratings, customerNumber); // Call the addToArray to store the array.


        }
        br.close();
    }
    
    // Method to enter data from the keyboard
    public void enterFromKeyboard() {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Customer Number:");
        int customerNumber = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline character
        int[] thisCustomerRating = new int[allRatings[0].length-2]; //for storing the ratings[]'s data except the last rating

        //Asking for user input
        System.out.println("Enter Customer Name:");
        String name = keyboard.nextLine();

        System.out.println("Enter Customer Surname:");
        String surname = keyboard.nextLine();

        System.out.println("Enter Customer Country:");
        String country = keyboard.nextLine();

        System.out.println("Enter Customer City:");
        String city = keyboard.nextLine();

        System.out.println("Enter Customer Occupation:");
        String occupation = keyboard.nextLine();

        CustomerData keyboardData = new CustomerData(name, surname, country, city, occupation);
        
        int[] ratings = new int[allRatings[0].length-1]; // Recreate the ratings array here to store the ratings for the current customer
        
        //Asks users ratings
        System.out.println("Enter Ratings:");
        for (int i = 0; i < ratings.length-1; i++) {
            System.out.println("Enter the rating for category " + (i+1) + ":");
            ratings[i] = keyboard.nextInt(); //Stores the ratings
            thisCustomerRating[i] = ratings[i]; //Stores the ratings except last rating
        }

        ratings[allRatings[0].length-2] = lastRating(allRatings,thisCustomerRating); //Takes the last rating from the lastRating method

        enterData(customerNumber, keyboardData, ratings); // Call the enterData to enter data to the linked list.
        addToArray(allRatings, ratings, customerNumber); // Call the addToArray to store the array
    }

    // Method to display the ratings array
    public void displayArray(LinkedList list){
        System.out.print("    ");
        for (int i = 0; i < productsNames.length; i++){
            System.out.print(productsNames[i]+" ");
        }
        System.out.println("");
        for (int i = 0; i < list.allRatings.length && list.allRatings[i][0] > 0; i++) {
            for (int j = 0; j < list.allRatings[0].length; j++){
                System.out.print(list.allRatings[i][j] + " ");
            }
            System.out.println("");
        }

    }

    // Method to add ratings to the ratings array
    public void addToArray(int[][] _allRatings, int[] _ratings, int cusNum) {
        _allRatings[number][0] = cusNum; // First element is customers number
        for (int i = 0; i < _ratings.length; i++) {
            _allRatings[number][i+1] = _ratings[i]; // We used _allRatings[number][i+1] instead of an _allRatings[number][i] because first element is customers number
        }
        number++;
    }

    
    // Method to calculate the last rating based on the similarity of previous customers' ratings
    public int lastRating(int[][] _allRatings, int[] _thisCustomerRating) {
        int maxSimilarity = Integer.MAX_VALUE; // Use max number for comparison
        int totalRating = 0;
        int similarCount = 0; // For situations where there are multiple similarity values that are the same

        for (int i = 0; i < _allRatings.length; i++) {
            if (_allRatings[i][0] == 0) break; // Stop if there are no more ratings

            int similarity = 0; // For finding all of the customers similarity, it should turn 0 everytime.
            for (int j = 0; j < _thisCustomerRating.length; j++) {
                similarity += Math.abs(_allRatings[i][j+1] - _thisCustomerRating[j]);
            }

            if (similarity < maxSimilarity) { 
                maxSimilarity = similarity;
                totalRating = _allRatings[i][_allRatings[i].length - 1]; // Total rating equals the last rating of the customer.
                similarCount = 1; 
            } else if (similarity == maxSimilarity) {
                totalRating += _allRatings[i][_allRatings[i].length - 1];
                similarCount++;
            }
        }

        return totalRating / similarCount; // Returns last Rating
    }

    // Method to calculate and display average ratings for all products
    public void average(LinkedList list) {
        double[] averages = new double[productsNames.length];
        int[] counters = new int[productsNames.length];

        for (int i = 0; i < list.allRatings.length && list.allRatings[i][0] > 0; i++) {
            for (int j = 1; j <= productsNames.length; j++) {
                averages[j - 1] += list.allRatings[i][j];
                counters[j - 1]++;
            }
        }
        
        // Print average ratings for each product
        for (int i = 0; i < productsNames.length; i++) {
            double average = (counters[i] > 0) ? averages[i] / counters[i] : 0;
            System.out.printf(productsNames[i] + " average = %.2f",average);
            System.out.println();
        }
    }
    
    // Method to calculate and display average ratings for some situations
    public void averageRatingsForTurkey(LinkedList list) {
        double[] sums = new double[productsNames.length];
        int[] counts = new int[productsNames.length];

        Node current = list.head;
        while (current != null) {
            if ("Turkey".equalsIgnoreCase(current.getCustomerData().getCountry())) {
                int[] ratings = current.getRatings();
                for (int i = 0; i < productsNames.length; i++) {
                    sums[i] += ratings[i];
                    counts[i]++;
                }
            }
            current = current.getNext();
        }

        // Print average ratings for each product
        for (int i = 0; i < productsNames.length; i++) {
            double average = counts[i] > 0 ? sums[i] / counts[i] : 0;
            System.out.printf(productsNames[i] + " average rating for Turkey = %.2f", average);
            System.out.println();
        }
    }
    public void averageRatingsExceptTurkey(LinkedList list){
        double[] sums = new double[productsNames.length];
        int[] counts = new int[productsNames.length];

        Node current = list.head;
        while(current != null){
            if (!"Turkey".equalsIgnoreCase(current.getCustomerData().getCountry())){
                int[] ratings = current.getRatings();
                for (int i = 0; i < productsNames.length; i++){
                    sums[i] += ratings[i];
                    counts[i]++;
                }
            }
            current = current.getNext();
        }

        // Print average ratings for each product
        for (int i = 0; i < productsNames.length; i++){
            double average = counts[i] > 0 ? sums[i]/counts[i]:0;
            System.out.printf(productsNames[i]+" average rating for countries except Turkey = %.2f", average);
            System.out.println();
        }
    }
    public void averageRatingsForDoctors(LinkedList list){
        double[] sums = new double[productsNames.length];
        int[] counts = new int[productsNames.length];

        Node current = list.head;
        while(current != null){
            if ("Doctor".equalsIgnoreCase(current.getCustomerData().getOccupation())){
                int[] ratings = current.getRatings();
                for (int i = 0; i < productsNames.length; i++){
                    sums[i] += ratings[i];
                    counts[i]++;
                }
            }
            current = current.getNext();
        }
        
        // Print average ratings for each product
        for (int i = 0; i < productsNames.length; i++){
            double average = counts[i] > 0 ? sums[i]/counts[i]:0;
            System.out.printf(productsNames[i]+" average rating for Doctors = %.2f", average);
            System.out.println();
        }

    }





}
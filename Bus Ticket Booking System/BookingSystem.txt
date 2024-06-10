import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

class Login{
    String message="";
    String message1="";
    String message2="";
    String username;
    int choice1;

        {message+="*************************************";
        message+="\n***Welcome To Aman Sdn Bhd***";
        message+="\n*************************************";}

        void chooseLogin(){
            String[] options = {"Login with existing account", "Create a new account"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome to Sdn Bhd"+"\nChoose an option:", "WELCOME!!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                login();
                break;
            case 1:
                createAccount();
                chooseLogin();
                break;
            default:
                JOptionPane.showMessageDialog(null,"No option selected.");
                System.exit(0);
                break;
        }
        }
        void login(){
            boolean loggedIn = false;
    
            while (!loggedIn) {
                username = JOptionPane.showInputDialog(null, "Enter username: ");
                String password = JOptionPane.showInputDialog(null, "Enter password for '"+ (username) +"' : ");

                // Validate login credentials
                try {
                    if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Username and password cannot be empty. Please try again.");
                    } else if (validateLogin(username, password)) {
                        JOptionPane.showMessageDialog(null,"Login successful!");
                        message+= "\n⚠ Logged in as: "+(username);
                        message+="\n*************************************";
                        loggedIn = true;
                    } else {
                        JOptionPane.showMessageDialog(null,"Invalid username or password. \nPlease try again OR Contact the AUTHORITY");
                        System.exit(0);
                        break;
                    }
                } catch (IOException e) {
                    System.err.println("Error reading user data: " + e.getMessage());
                    JOptionPane.showMessageDialog(null,"Error reading user data.\n Please Contact the AUTHORITY");
                    }
                }
            }
        // Method to validate login credentials
        private boolean validateLogin(String username, String password) throws IOException  {
            try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        return true;
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading user data: " + e.getMessage());
            }
            return false;
        }
        
        void createAccount() {
            message2="";
            message2= message;
            boolean accountCreated = false;
    
            while (!accountCreated) {
                String username = JOptionPane.showInputDialog(null, "Enter New Username: ");
                String password = JOptionPane.showInputDialog(null, "Enter New Password for '" + username + "' : ");
    
                if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username and password cannot be empty. Please try again.");
                } else {
                    try (FileWriter writer = new FileWriter("user_data.txt", true)) {
                        writer.write("\n" + username + "," + password);
                        message2+="\nAccount Successfully Created!";
                        message2+="\n";
                        message2+="\nUsername: "+(username);
                        message2+="\nPassword: "+(password);
                        accountCreated = true;
                    } catch (IOException e) {
                        System.err.println("Error appending data to file: " + e.getMessage());
                        JOptionPane.showMessageDialog(null, "An error occurred. Please try again.");
                    }
                    JOptionPane.showMessageDialog(null,message2);
                }
            }
        }
        void logout() {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Logout", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null,"Logged out successfully!");
                System.exit(0);
            }
        }
}

class Company extends Login{
    String h;
    String companyName;
    void chooseCompany(){
        message1="";
        String[] options ={"Sani Express","Adam Express","TransNasional","Adik Beradik","Company"};
        companyName= (String) JOptionPane.showInputDialog(null,"Select Bus Company","WELCOME!!",JOptionPane.INFORMATION_MESSAGE,null,options,options[4]);
        String DestinationName= JOptionPane.showInputDialog(null,"Enter Destination");
        message1+="\nBus Company Name: "+(companyName);
        message1+= "\nDestination: "+(DestinationName);
        if (companyName==options[0]) {h= "seats,sani.txt";}
        else if (companyName==options[1]) {h= "seats,adam.txt";}
        else if (companyName==options[2]) {h= "seats,transN.txt";}
        else if (companyName==options[3]) {h= "seats,Adik.txt";}
    }
}

class ChooseTime extends Company{
    List<String> dates = new ArrayList<>();
    String selectedDate;

    void chooseOption(){
        String[] options = {"View Available Dates", "Choose Date for Booking"};
    int choice = JOptionPane.showOptionDialog(null, "⚠ Logged in as '"+(username)+"' \nChoose an option:", "WELCOME!!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    switch (choice) {
        case 0:
            viewDates();
            chooseOption();
            break;
        case 1:
            chooseDate();
            break;
        default:
            JOptionPane.showMessageDialog(null,"No option selected.");
            break;
    }
    }
    void viewDates() {
        dates.clear(); // Clear the dates list before displaying them
        try (BufferedReader reader = new BufferedReader(new FileReader("dates.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dates.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading dates data: " + e.getMessage());
        }
        StringBuilder dateM = new StringBuilder("Available Dates:\n");
        for (String date : dates) {
            dateM.append(date).append("\n");
        }
        JOptionPane.showMessageDialog(null, dateM);
        dates.clear(); // Clear the dates list after displaying them
    }
    void chooseDate() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dates.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dates.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading dates data: " + e.getMessage());
        }

        String[] datesArray = dates.toArray(new String[0]);
        selectedDate = (String) JOptionPane.showInputDialog(null, "Select date to Book:", "Book Date", JOptionPane.QUESTION_MESSAGE, null, datesArray, datesArray[0]);
        message1+= "\nDate for booking: "+(selectedDate);
    }
    void deleteDate(){
        if (selectedDate != null) {
            dates.remove(selectedDate);
            try (PrintWriter writer = new PrintWriter(new FileWriter("dates.txt"))) {
                for (String date : dates) {
                    writer.println(date);
                }
            } catch (IOException e) {
                System.err.println("Error writing dates data: " + e.getMessage());
            }
        }
    }
    void confirm() {
        choice1 = JOptionPane.showConfirmDialog(null, "Current Company Selected: "+(companyName)+"\nCurrent Date Selected: "+(selectedDate)+"\nDo you want to Confirm your order?", "Confirm", JOptionPane.YES_NO_OPTION);
    if (choice1==JOptionPane.YES_OPTION) {message=message+message1;  
    }
    }
}
class Quantity extends ChooseTime {
    List<String> selectedSeatsList = new ArrayList<>();
    List<String> seats = new ArrayList<>();
    int quantitySeat;
    void chooseSeats() {
        boolean selectingSeats = true;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(h))) {
            String line;
            while ((line = reader.readLine()) != null) {
                seats.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading Seats data: " + e.getMessage());
        }
        String[] seatsArray = seats.toArray(new String[0]);
        while (selectingSeats) {

            String selectedSeat = (String) JOptionPane.showInputDialog(null, "1 Seat = 40 RM \nSelect Seat to Book:", "Book Seat", JOptionPane.QUESTION_MESSAGE, null, seatsArray, seatsArray[0]);
            if (selectedSeat != null) {
                selectedSeatsList.add(selectedSeat);
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to Book Another Seat?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION) {
                    selectingSeats = false;
                }
            } else {
                selectingSeats = false;
            }
        }
        for (String seat : selectedSeatsList) {
            message += "\nSeat for booking: " + seat;
        }
        quantitySeat = selectedSeatsList.size();
    }

    void deleteSeat() {
        for (String selectedSeat : selectedSeatsList) {
            seats.remove(selectedSeat);
        }
        selectedSeatsList.clear(); // Clear the list after deletion

        try (PrintWriter writer = new PrintWriter(new FileWriter(h))) {
            for (String seat : seats) {
                writer.println(seat);
            }
        } catch (IOException e) {
            System.err.println("Error writing seats data: " + e.getMessage());
        }
    }
}


class InsertData extends Quantity {
    void insertData(){
        String customerName= (String) JOptionPane.showInputDialog(null,"Enter Your Name");
        String customerPhoneNumber= JOptionPane.showInputDialog(null,"Enter Your PhoneNumber");
        message+="\n*************************************";
        message+="\nCustomer Name: "+(customerName);
        message+="\nCustomer PhoneNumber: "+(customerPhoneNumber);
    }
}


class Payment extends InsertData {
    void payment(){
        String[] options ={"Credit Card","Online Banking","Cash","Debit Card","Payment"};
        String paymentName= (String) JOptionPane.showInputDialog(null,"Select Your Payment","WELCOME!!",JOptionPane.INFORMATION_MESSAGE,null,options,options[4]);
        int Price= quantitySeat*40;
        String referenceID= JOptionPane.showInputDialog(null,"Do Payment Here 👇 (total Price:)"+(Price)+ "\n163037394403\n" + "Ahmad Haziq irsyad\n"+"Maybank"+"\nEnter Your Reference Id");
        message+="\nPayment Option: "+(paymentName);
        message+="\nReference ID: "+(referenceID);
        message+= "\nTotal Price: "+(Price);
    }
}

class BookingSystem{
    public static void main(String[] args) throws IOException
    {
        Payment i= new Payment();
        PrintWriter out = new PrintWriter("Data.txt");
        boolean continueBooking = true;
        
        i.chooseLogin();
        while (continueBooking) { 
            i.chooseCompany();
            i.logout();
            i.chooseOption();
            i.confirm();

            if (i.choice1 == JOptionPane.NO_OPTION) {
                continueBooking = true;
            }
            else{continueBooking=false;}
        }
        i.deleteDate();
        i.chooseSeats();
        i.deleteSeat();
        i.insertData();
        i.payment();

        out.print(i.message);
        JOptionPane.showMessageDialog(null,i.message);

        out.close();
        System.exit(0);
    }
}
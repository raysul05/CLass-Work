import javax.swing.*;

class RentalDetails {
    String name;
    String address;
    String phoneNo;
    float deposit;

    RentalDetails(String name, String address, String phoneNo, float deposit) {
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.deposit = deposit;
    }

    void print() {
        JOptionPane.showMessageDialog(null, "Name: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNo
                + "\nDeposit: " + deposit);
    }
}

class RentProcess {
    public static void main(String[] args) {
        RentalDetails[] rentals = new RentalDetails[3];

        for (int i = 0; i < 3; i++) {
            String name = JOptionPane.showInputDialog("Enter Name:");
            String address = JOptionPane.showInputDialog("Enter Address:");
            String phoneNo = JOptionPane.showInputDialog("Enter Phone Number:");
            float deposit = Float.parseFloat(JOptionPane.showInputDialog("Enter Deposit:"));

            rentals[i] = new RentalDetails(name, address, phoneNo, deposit);
        }

        for (int i = 0; i < 3; i++) {
            rentals[i].print();
        }
    }
}

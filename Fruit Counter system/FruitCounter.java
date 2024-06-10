import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.*;

class FruitDetails
{
    String name;
    String fruit;
    float price;
    float fprice;
    float weight;
    float totalprice;

    FruitDetails(){}
}

class FruitCounter
{
    public static void main(String[]args) throws IOException
{
    JFrame f=new JFrame();

    FruitDetails[] fd=new FruitDetails[3];
    FruitDetails d=new FruitDetails();
    String[] options ={"Mango","Grapes","Oranges","Choose Fruit!"};
    PrintWriter out=new PrintWriter("Fruit Recept.txt");

    d.name= JOptionPane.showInputDialog(f,"Enter Your Name:");
    for(int i=0;i<3;i++){
    fd[i]= new FruitDetails();
    fd[i].fruit=(String)JOptionPane.showInputDialog(f,"Choose Your Fruit","Fruit",JOptionPane.INFORMATION_MESSAGE,null,options,options[3]);
    if (fd[i].fruit.equals(options[0])){
        fd[i].weight=Float.parseFloat(JOptionPane.showInputDialog(f,"Enter Your Amount(KG) for"+fd[i].fruit));
        fd[i].price=23;
        fd[i].fprice=fd[i].weight*fd[i].price;
        fd[i].totalprice=fd[i].totalprice+fd[i].fprice;}
    else if (fd[i].fruit.equals(options[1])){
        fd[i].weight=Float.parseFloat(JOptionPane.showInputDialog(f,"Enter Your Amount(KG) for "+fd[i].fruit));
        fd[i].price=20;
        fd[i].fprice=fd[i].weight*fd[i].price;
        fd[i].totalprice=fd[i].totalprice+fd[i].fprice;}
    else if (fd[i].fruit.equals(options[2])){
        fd[i].weight=Float.parseFloat(JOptionPane.showInputDialog(f,"Enter Your Amount(KG) for "+fd[i].fruit));
        fd[i].price=10;
        fd[i].fprice=fd[i].weight*fd[i].price;
        fd[i].totalprice=fd[i].totalprice+fd[i].fprice;}
    }

    String message = "";
        message += "|********************|\n";
        message += "  Rainbow FruitStall\n";
        message += "|********************|\n";
        message += "|*NAME:**" + d.name + "*******|\n";
        message += "|********************|\n";

        message += "|----------------------------------------------------------------------|\n";
        message += String.format("| %-10s | %-20s | %-12s | %-12s |\n","Fruit Name","Fruit Price per KG","Fruit Ammount(KG)","Fruit Price");
        message += "|----------------------------------------------------------------------|\n";

        for (int i = 0; i < 3; i++) {
            message += String.format("| %-10s | %-20s | %-18s | %-12s |\n", fd[i].fruit, fd[i].price, fd[i].weight,
                    fd[i].fprice);
            message += "|----------------------------------------------------------------------|\n";
        }

        JOptionPane.showMessageDialog(f, message);

        out.println("|********************|");
        out.println("  Rainbow FruitStall");
        out.println("|********************|");
        out.println("|*NAME:**" + d.name + "*******|");
        out.println("|********************|");

        out.printf("|----------------------------------------------------------------------|%n");
        out.printf("| %-10s | %-20s | %-12s | %-12s |%n", "Fruit Name", "Fruit Price per KG", "Fruit Amount(KG)",
                "Fruit Price");
        out.printf("|----------------------------------------------------------------------|%n");

        for (int i = 0; i < 3; i++) {
            out.printf("| %-10s | %-20s | %-18s | %-12s |%n", fd[i].fruit, fd[i].price, fd[i].weight, fd[i].fprice);
            out.printf("|----------------------------------------------------------------------|%n");
        }
        out.close();
        System.exit(0);
    }
}
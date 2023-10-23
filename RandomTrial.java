package New_class_book.Randomness;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Scanner;
public class RandomTrial {
    public static void main(String[] args) {
        Random randomNumbers = new Random();
        char choice = 'y';
//        while (choice == 'y'){
//            int dice = randomNumbers.nextInt(1,7);
//            JOptionPane.showMessageDialog(null,String.format("""
//                    the Dice face is: %d
//
//                    """, dice));
//            choice = JOptionPane.showInputDialog("Do you wanna Roll the dice again(y = yes, n = no").toLowerCase().charAt(0);
//        }
        Scanner keyboard = new Scanner(System.in);
        int totalNumHead = 0;
        int track = 0;
        while (choice == 'y'){
            track += 1;
            System.out.println("""
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    
                    \t\tTossing coin 110 times ...
                    
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    """);
            int totalHead = 0;
            for (int count = 1; count <= 110; count++){
                int value = randomNumbers.nextInt(2);
                switch (value){
                    case 1->{
                        System.out.println(count + ". Head");
                        totalHead += 1;
                    }case 0 ->{
                        System.out.println(count + ". Tail");
                    }
                }
            }totalNumHead += totalHead;
            System.out.printf("we got %d Head %d Tail", totalHead, 110 - totalHead);
            System.out.print("\nDo you wanna toss again.(y = yes, n = no)  ");
            choice = keyboard.nextLine().charAt(0);

        }
        System.out.printf("""
                    
                    
                    you have tossed a total of %d dosses and
                    got %d Head with %.3f probability
                    and %d Tail with %.3f probability
                    """, track, totalNumHead,
                (double)totalNumHead/(track *110),track*110 -totalNumHead,(double)(track*110 -totalNumHead)/(110 * track));
        System.exit(0);
    }
}

package New_class_book.Randomness;
import javax.swing.JOptionPane;
import java.util.*;

public class SlotMachine {
    final static double ONEROUNDFEE = 12.99;
    final static double ONESIMILARCOMPENSATION = 0.69; //This will be used to calculate if 2 only similar
    final static double TWOSIMILARCOMPENSATION = 2;
    final static double ZEROSIMILARCOMPENSATION = 0;
    final static double TENROUNDFEE = 103.99;
    static int oneRoundOutput(HashMap<Integer,String> playable){
        Random randomness = new Random();
        ArrayList<Integer> valueList = new ArrayList<>();
        int value;
        for(int count = 1; count <=3; count++){
            value = randomness.nextInt(6);
            String x = playable.get(value);
            System.out.printf( """
                    
                    \t\t\t:: %s
                    
                    """, x);
            valueList.add(value);
        }
        System.out.println("""
                \n
                """);
        int similar = 0;
        int track ;
        for(int count = 0 ; count < 3; count++){
            track = 0;
            for (int val : valueList){
                if (val == valueList.get(count))
                    track += 1;
            }
            if (track > similar){
                similar = track;
            }
        }
        System.out.println(" ");
        similar--;
        return similar;
    }
    static double money (int similarity){
        if (similarity == 1 ){
            return ONEROUNDFEE * ONESIMILARCOMPENSATION;
        } else if (similarity == 2) {
            return ONEROUNDFEE * TWOSIMILARCOMPENSATION;
        }else {
            return ONEROUNDFEE * ZEROSIMILARCOMPENSATION;
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String welcome = """
                \t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                
                \t\t\t\t\t\t\t\tWelcome to Slot Machine
                
                \t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                """;
        double money ; // This will be used to deal Entered Money of player
        HashMap<Integer  ,String  > playable = new HashMap<>();
        ArrayList<String> stored = new ArrayList<String>() ;
        stored.add("Orange"); stored.add("Apple"); stored.add("Seven");
        stored.add("Pineapple"); stored.add("Mango"); stored.add("Banana");
        int count = 0;
        for (String name : stored){
                playable.put(count, name);
                count++;
        }
        String play = "To play Deposit money";
        System.out.println(welcome);
        int similar ;
        String playOption = """
                
                              Which One do you Wanna play?
                   
                              1. One Draw ---------$12.99
                
                              2. Ten Draw ---------$103.99
                
                
                """;
        String mainOption = """
                
                                1. Cash in 
                                2. play
                                3. Withdraw Money
                                4. Balance 
                                5. Admin
                                6. Exit
                                
                
                """;
        money = 0;
        int oneRoundPlayData = 0;
        int tenRoundPlayData = 0;
        ArrayList<Integer> wonDataOneDraw = new ArrayList<>();
        ArrayList<Integer> wonDataTenDraw = new ArrayList<>();
       for (int counted = 0; counted<3; counted++){
           wonDataTenDraw.add(0);
           wonDataOneDraw.add(0);
       }
        int choice;
        String pass;
        while (true){
            
            System.out.println(mainOption);
            System.out.print("Enter your choice: ");
            choice = keyboard.nextInt();
            switch (choice){
                case 1 ->{
                    System.out.print("\nEnter the amount: ");
                    money += keyboard.nextDouble();
                }case 2 ->{
                    if (money <= ONEROUNDFEE){
                        System.out.print(" Your Balance is low. Enter some money: ");
                        double newMoney = keyboard.nextDouble();
                        money += newMoney;
                    }
                    System.out.println(playOption);
                    System.out.print("Enter your choice: ");
                    choice = keyboard.nextInt();
                    switch (choice) {
                        case 1 -> {
                            money -= ONEROUNDFEE;
                            similar = oneRoundOutput(playable);
                            wonDataOneDraw.set(similar, wonDataOneDraw.get(similar) + 1);
                            money += money(similar);
                            oneRoundPlayData += 1;
                        }
                        case 2 -> {
                            if (money < TENROUNDFEE) {
                                System.out.print("""
                                        Your balance is low
                                        Enter the amount to cash in or -1 to pass: """);
                                double cash = keyboard.nextDouble();
                                money = cash >= 0 ? money + cash : money;
                                if (cash < 0) {
                                    break;
                                }
                            }
                            for (count = 0; count < 10; count++) {
                                similar = oneRoundOutput(playable);
                                money += money(similar);
                                wonDataTenDraw.set(similar, wonDataTenDraw.get(similar) + 1);
                            }
                            tenRoundPlayData += 1;
                        }default -> {
                            System.out.println("Invalid Input!! try again.");
                        }

                    }
                } case 3 -> {
                    System.out.printf("your current Balance is $%.3f\n", money);
                    System.out.print("How much do you wanna withdraw: ");
                    double withdraw = keyboard.nextDouble();
                    if (withdraw > money) {
                        System.out.println("You don't have that much balance , try again.");
                    } else {
                        money -= withdraw;
                        System.out.printf("""
                                                    $%,.3f has been successfully withdrawn from you balance. 
                                                    
                                                    you have $%,.3f left in your balance.
                                        """, withdraw, money);
                    }

                }
                case 4 -> {
                    System.out.printf("""
                                    your Balance is $%,.3f.
                                                                        
                                    """, money);
                }case 5 ->{
                    keyboard.nextLine();
                    String password = "1234asdf";
                    System.out.print("Enter the password to proceed: ");
                    pass = keyboard.nextLine();
                    if (password.equals(pass)){
                        System.out.printf("""
                                        
                                            Total of %d player has played One draw spin and 
                                             a total of %d players has played Ten draw spin
                                        
                                        
                                                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                                    player with 0 similarity: %d
                                                    
                                                    Total gain: $%,.3f  
                                                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                                
                                                
                                                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                                    player with 1 similarity: %d
                                                    
                                                    Total gain: $%,.3f  
                                                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                        
                                                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                                    player with 2 similarity: %d
                                                    
                                                    Total lose: $%,.3f  
                                                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                                
                                                
                                                 OVERALL NetData        =   %,.3f     
                                                =================       =
                                        """,oneRoundPlayData,tenRoundPlayData,wonDataTenDraw.get(0)+wonDataOneDraw.get(0)
                                , ONEROUNDFEE * wonDataOneDraw.get(0) + (TENROUNDFEE/ 10 ) *  wonDataTenDraw.get(0)
                                , wonDataOneDraw.get(1) + wonDataTenDraw.get(1)
                                ,ONEROUNDFEE * wonDataOneDraw.get(1)*(1-ONESIMILARCOMPENSATION) + (TENROUNDFEE/ 10 ) *  wonDataTenDraw.get(1)*(1-ONESIMILARCOMPENSATION)
                                , wonDataOneDraw.get(2) + wonDataTenDraw.get(2),-wonDataOneDraw.get(2) * ONEROUNDFEE - wonDataTenDraw.get(2)*ONEROUNDFEE,
                                ONEROUNDFEE * wonDataOneDraw.get(0) + (TENROUNDFEE/ 10 ) *  wonDataTenDraw.get(0) + ONEROUNDFEE * wonDataOneDraw.get(1)*(1-ONESIMILARCOMPENSATION) + (TENROUNDFEE/ 10 ) *  wonDataTenDraw.get(1)*(1-ONESIMILARCOMPENSATION)
                                        + (-wonDataOneDraw.get(2) * ONEROUNDFEE - wonDataTenDraw.get(2)*ONEROUNDFEE) );

                    }
                    else System.out.println("Invalid Password. try again" +
                            " ");

                }case 6 ->{
                    System.exit(0);
                }
                default ->  {
                    System.out.println("Invalid Input");
                }
            }
            
        }
        




    }
}

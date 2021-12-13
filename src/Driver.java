import java.util.Scanner;

public class Driver {
    private static final Boolean revealCode = false;

    public static void main(String[] args) {
        String[] colors = new String[]{"G","B","O","P","R","Y"};
        GameConfiguration one = new GameConfiguration(12, colors,4);
        SecretCodeGenerator secretCodeObj1 = new SecretCodeGenerator(one);
        start(revealCode,one,secretCodeObj1);
    }

    public static void start(Boolean isTesting, GameConfiguration config, SecretCodeGenerator generator) {
        // TODO: complete this method
		// We will call this method from our JUnit test cases.
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to Mastermind.");
        System.out.println("Do you want to play a new game? (Y/N):");
        String playAgain = userInput.nextLine();
        while(playAgain.equals("Y")){
            String code = generator.getNewSecretCode();
            Game newGame = new Game(isTesting,userInput,code,config);
            newGame.runGame();
            System.out.println("Do you want to play a new game? (Y/N):");
            playAgain = userInput.nextLine();                              //update the playAgain variable via the same scanner for each iteration
        }


    }
}

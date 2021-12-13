import java.util.Scanner;

public class Game {
    Scanner userInp;
    boolean isTesting;
    String secretCode;
    int remainingGuesses;
    String[] history;
    GameConfiguration configs;


    public Game(boolean test, Scanner m, String code, GameConfiguration config) {
        isTesting = test;
        userInp = m;
        secretCode=code;
        remainingGuesses = config.guessNumber;      //start with the amount of guesses allowed by the configuration
        configs = config;
        history= new String[config.guessNumber];       //need as many elements as guesses allowed
    }

    public void runGame(){
        if(this.isTesting){
            System.out.println("Secret Code: " + secretCode );
        }

        while(remainingGuesses>0) {
            System.out.println();
            System.out.println("You have " + remainingGuesses + " guess(es) left.");
            System.out.println("Enter guess:");
            String userGuess = userInp.nextLine();
            Guess newGuess = new Guess(userGuess, configs.pegNumber);
            if(newGuess.isValid(configs)){                  // if the guess is valid,
                if(newGuess.guess.equals("HISTORY")){       // check if its "HISTORY" first. If so, print everything stored in history array
                    for (int i=0; i< history.length-remainingGuesses;i++)
                        System.out.println(history[i]);
                }
                else                                      //otherwise, process the guess and print out the result
                {
                    String pegResult = newGuess.getPegs(secretCode);
                    history[history.length-remainingGuesses]= newGuess.guess + " -> " + pegResult;
                    remainingGuesses--;
                    System.out.println(newGuess.guess + " -> " + pegResult);

                    if(newGuess.pegs.blackPegs==configs.pegNumber){   //After a guess is processed, check if we won using the recent guess' pegs. If not,
                        System.out.println("You win!");
                        System.out.println();
                        break;
                    }
                    else if(remainingGuesses<=0){                       //then check if the game is over. If not, continue. If so, break out of the game.
                        System.out.println("You lose! The pattern was " + secretCode);
                        System.out.println();
                        break;
                    }


                }
            }
            else{
                System.out.println("INVALID_GUESS");
                System.out.println();
            }
        }

    }



}

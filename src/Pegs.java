import java.util.Arrays;

public class Pegs {
    int blackPegs;
    int whitePegs;
    boolean[] processedUserPegs;
    boolean[] processedSecretPegs;

    public Pegs(int totalPegs){
        blackPegs=0;
        whitePegs=0;                                                //generate a set of 0 black and 0 white pegs
        processedUserPegs = new boolean[totalPegs];
        processedSecretPegs = new boolean[totalPegs];
        Arrays.fill(processedUserPegs,Boolean.FALSE);
        Arrays.fill(processedSecretPegs,Boolean.FALSE);
    }

    public String getPegString(String userGuess, String secretCode){
        this.process(userGuess,secretCode); // to get the pegs portion of the string, process how many black and white pegs there are for a given response
        return blackPegs + "b_" + whitePegs + "w";
    }

    public void process(String userGuess, String secretCode) {
        for (int i = 0; i < userGuess.length(); i++) {
            if (userGuess.charAt(i) == secretCode.charAt(i)) {  //processing: if the color and position of one letter in a guess is correct, +1 black peg
                blackPegs++;
                processedUserPegs[i] = true;             //and we keep track of which pegs have already been processed so we dont "reprocess them" once we start doing white pegs
                processedSecretPegs[i] = true;
            }
        }

        for(int j=0; j<userGuess.length();j++) {          //to process white pegs:
            if(processedUserPegs[j])              //if a certain letter from the userGuess has already been processed, check the next letter from the userGuess
                continue;
            for(int m=0; m<secretCode.length();m++){
                if(processedSecretPegs[m])          //if a certain letter from the secretCode has already been processed, check the next letter from the secretCode
                    continue;
               if(userGuess.charAt(j) == secretCode.charAt(m)) {
                   whitePegs++;
                   processedSecretPegs[m] = true;         //if we find a match, then also update that this secretPeg has already been used so duplicates dont break the program
                   break;
               }
            }
        }

    }




}

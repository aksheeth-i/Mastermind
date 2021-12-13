public class Guess {
    String guess;
    Pegs pegs;

    public Guess(String guess, int numPegs){
        this.guess = guess;                //default constructor to initialize a guess
        pegs = new Pegs(numPegs);
    }

    public boolean isValid(GameConfiguration configs) {
        if(guess.equals("HISTORY"))
            return true;

        if (guess.length() != configs.pegNumber)
            return false;                      //check if the length of the guess matches the appropriate length first.

        for(int i =0; i<guess.length();i++){
            String curr = Character.toString(guess.charAt(i));
            for(int j=0; j<configs.colors.length;j++) {
                if(curr.equals(configs.colors[j]))       //if this character is in the colors array, then look at the next char
                    break;
                if(j == configs.colors.length - 1)     //if we reach the end of the colors array, then it's an invalid guess
                    return false;
            }
        }

        return true;
    }

    public String getPegs(String code){
        return pegs.getPegString(guess,code);
    }    //return the result of how many pegs this specific guess generates



}

import java.io.*;
import java.util.ArrayList;
class GameHelper {
    String input;
    String getUserInput(String prompt) {
        System.out.print(prompt + ":  ");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            input  = in.readLine();
        } catch(IOException e) {
            System.out.println("IOException " + e);
        }
        return input;
    }
}
class DotCom {
    ArrayList<Integer> sinkdots = new ArrayList<Integer>();

    void setSinkDots(ArrayList<Integer> sinkdots) {
        this.sinkdots = sinkdots;
    }

    String guessYourSelf (int guess) {
        int index = sinkdots.indexOf(guess);
        if (index >= 0) {
            sinkdots.remove(index);
            if (sinkdots.isEmpty()) return "kill";
            else return "hit";
        }
        return "miss";
    }
}
class Startgame {
    public static void main(String[] args) {
        String userGuess;

        GameHelper helper = new GameHelper();

        DotCom game = new DotCom();

        ArrayList <Integer> dotcoms = new ArrayList <Integer>();
        
        int guesses = 0;
        dotcoms.add((int) (Math.random() * 10));
        dotcoms.add((int) (Math.random() * 10));
        dotcoms.add((int) (Math.random() * 10));
        game.setSinkDots(dotcoms);
        while (true) {
            userGuess = helper.getUserInput("Enter a Guess");
            String result = game.guessYourSelf(Integer.parseInt(userGuess));
            System.out.println(result);
            if (result == "kill") {
                break;
            }
            guesses++;
        }
        System.out.println("You took " + guesses + " guesses");
    }
}
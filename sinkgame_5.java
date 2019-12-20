import java.io.*;
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
    int []sinkdots = new int[3];

    void setSinkDots(int []sinkdots) {
        this.sinkdots = sinkdots;
    }

    String guessYourSelf (int guess) {
        int count = 0;
        for (int dot: this.sinkdots) {
            if (dot == guess) {
                this.sinkdots[count] = -1;
                return "hit";
            }
            count++;
        }
        return "miss";
    }
}
class Startgame {
    public static void main(String[] args) {
        String userGuess;
        GameHelper helper = new GameHelper();
        DotCom game = new DotCom();
        int []dotcoms = new int[3];
        int hits = 0;
        dotcoms[0] = (int) (Math.random() * 10);
        dotcoms[1] = (int) (Math.random() * 10);
        dotcoms[2] = (int) (Math.random() * 10);
        // System.out.println(dotcoms[0] + " " + dotcoms[1] + " " + dotcoms[2]);
        game.setSinkDots(dotcoms);
        while (hits < 3) {
            userGuess = helper.getUserInput("Enter a Guess");
            String result = game.guessYourSelf(Integer.parseInt(userGuess));
            System.out.println(result);
            if (result == "hit") {
                hits++;
            }
        }
        System.out.println("kill");
    }
}
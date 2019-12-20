class Player {
    int num;
    public void guessNumber() {
        this.num =  (int) (Math.random() * 10);
    }
}
class GuessGame {
    int guessNumber;
    Player p1, p2, p3;
    boolean isP1Guessed, isP2Guessed, isP3Guessed;
    GuessGame() {
        this.guessNumber = (int) (Math.random() * 10);
    }
    public void startGuessingGame() {
        while(true) {
            p1 = new Player();
            p2 = new Player();
            p3 = new Player();

            p1.guessNumber();
            p2.guessNumber();
            p3.guessNumber();

            isP1Guessed = false;
            isP2Guessed = false;
            isP3Guessed = false;

            if (p1.num == this.guessNumber) {
                System.out.println("Player1 Guessed the number");
                isP1Guessed = true;
            }
            if (p2.num == this.guessNumber) {
                System.out.println("Player2 Guessed the number");
                isP2Guessed = true;
            }
            if (p3.num == this.guessNumber) {
                System.out.println("Player3 Guessed the number");
                isP3Guessed = true;
            }
            if (isP1Guessed || isP2Guessed || isP3Guessed) {
                System.out.println("GAME OVER!");
                break;
            } else {
                System.out.println("Guess Again");
            }
        }
    }
}
class StartGuessingGame {
    public static void main(String[] args) {
        GuessGame game = new GuessGame();
        game.startGuessingGame();
    }
}
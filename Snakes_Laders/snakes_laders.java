import java.util.*;

class Lader {
    String name;
    int start;
    int end;

    Lader(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

class Snake {
    String name;
    int start;
    int end;

    Snake(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

class GameBoard {
    List<Lader> laders;
    List<Snake> snakes;

    GameBoard(List<Lader> laders, List<Snake> snakes) {
        this.laders = laders;
        this.snakes = snakes;
    }

    Snake checkForTheSnakeInTheBox(int box) {
        for (Snake snake : this.snakes) {
            if (box == snake.start) {
                return snake;
            }
        }
        return null;
    }

    Lader checkForTheLaderInTheBox(int box) {
        for (Lader lader : this.laders) {
            if (box == lader.start) {
                return lader;
            }
        }
        return null;
    }
}
class Player {
    String name;
    int position;
    boolean won;

    Player(String name, int position, boolean won) {
        this.name = name;
        this.position = position;
        this.won = won;
    }

    void moveThePosition(int num) {
        int diff = 100 - this.position;
        if (num <= diff) {
            this.position = this.position + num;
        }
    }

    void setThePosition(int position) {
        this.position = position;
    }

    int rollTheDice() {
        int valueOfDice = (int)(Math.random() * 6);
        return valueOfDice + 1;
    }
}

class SnakesLadersGame {
    
    List<Snake> initializeSnakes() {
        List<Snake> snakes = new ArrayList<Snake>();
        snakes.add(new Snake("Snake1", 16, 8));
        snakes.add(new Snake("Snake2", 52, 28));
        snakes.add(new Snake("Snake3", 78, 25));
        snakes.add(new Snake("Snake4", 93, 89));
        snakes.add(new Snake("Snake5", 95, 75));
        snakes.add(new Snake("Snake6", 99, 21));
        return snakes;
    }

    List<Lader> initializeLaders() {
        List<Lader> laders = new ArrayList<Lader>();
        laders.add(new Lader("Lader1", 2, 45));
        laders.add(new Lader("Lader2", 4, 27));
        laders.add(new Lader("Lader3", 9, 31));
        laders.add(new Lader("Lader4", 47, 84));
        laders.add(new Lader("Lader5", 70, 87));
        laders.add(new Lader("Lader6", 71, 91));
        return laders;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        SnakesLadersGame game = new SnakesLadersGame();

        List<Lader> laders = game.initializeLaders();
        List<Snake> snakes = game.initializeSnakes();

        GameBoard gameBoard = new GameBoard(laders, snakes);

        System.out.print("Enter no.of players: ");
        int noOfPlayers = in.nextInt();
        int noOfWinners = 0;

        Player players[] = new Player[noOfPlayers];

        for (int i=0; i<noOfPlayers; i++) {
            System.out.print("Enter Player" + (i+1) + " name: ");
            String player_name = in.next();
            players[i] = new Player(player_name, 0, false);

        }

        int currentPlayerIndex = 0;
        Player currentPlayer = players[currentPlayerIndex];
        int currentPosition;

        String str = in.nextLine();

        while(true) {
            System.out.println(currentPlayer.name + " Press Enter to roll the dice");
            str = in.nextLine();
            if (str.equals("")) {
                int numOnDice = currentPlayer.rollTheDice();
                System.out.println("Your number on dice: " + numOnDice);

                currentPosition = currentPlayer.position;
                currentPlayer.moveThePosition(numOnDice);
                
                Lader lader = gameBoard.checkForTheLaderInTheBox(currentPlayer.position);
                if (lader != null) {
                    currentPlayer.setThePosition(lader.end);
                    System.out.format("%s ,You climbed a ladder from %d to %d\n",currentPlayer.name, lader.start, lader.end);
                    continue;
                }
                
                Snake snake = gameBoard.checkForTheSnakeInTheBox(currentPlayer.position);
                if (snake != null) {
                    currentPlayer.setThePosition(snake.end);
                    System.out.format("%s ,You have eaten by a snake from %d to %d\n",currentPlayer.name, snake.start, snake.end);
                }

                System.out.format("%s Your current position: %d\n", currentPlayer.name, currentPlayer.position);

                if (numOnDice == 6 && currentPosition != currentPlayer.position) {
                    continue;
                }

                if (currentPlayer.position == 100) {
                    System.out.println(currentPlayer.name + " Won the match!");
                    noOfWinners++;
                    currentPlayer.won = true;
                    if (noOfWinners == noOfPlayers - 1) {
                        break;
                    }
                }
                
            } else {
                continue;
            }

            if(currentPlayerIndex == noOfPlayers - 1) {
                currentPlayerIndex = 0;
            } else {
                currentPlayerIndex++;
            }
            while (players[currentPlayerIndex].won) {
                currentPlayerIndex++;
                if (currentPlayerIndex == noOfPlayers) {
                    currentPlayerIndex = 0;
                }
            }
            currentPlayer = players[currentPlayerIndex];
        }
        

    }

}
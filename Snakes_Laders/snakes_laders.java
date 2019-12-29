import java.util.*;

class Coordinate {
    int x;
    int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Lader {
    String name;
    Coordinate start;
    Coordinate end;

    Lader(String name, Coordinate start, Coordinate end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

class Snake {
    String name;
    Coordinate start;
    Coordinate end;

    Snake(String name, Coordinate start, Coordinate end) {
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

    Snake checkForTheSnakeInTheBox(Coordinate box) {
        for (Snake snake : this.snakes) {
            if (box.x == snake.start.x && box.y == snake.start.y) {
                return snake;
            }
        }
        return null;
    }

    Lader checkForTheLaderInTheBox(Coordinate box) {
        for (Lader lader : this.laders) {
            if (box.x == lader.start.x && box.y == lader.start.y) {
                return lader;
            }
        }
        return null;
    }
}
class Player {
    String name;
    Coordinate position;

    Player(String name, Coordinate position) {
        this.name = name;
        this.position = position;
    }

    void setThePosition(int num) {
        int x = this.position.x;
        int y = this.position.y;
        if (this.position.x % 2 == 0) {
            int diff = 9 - this.position.y;
            if (num > diff) {
                this.position.y  += diff;
                num -= diff;
                this.position.x++;
                num--;
                if (num > 0) {
                    this.position.y -= num;
                }
            } else {
                this.position.y += num;
            }
        } else {
            if (num > this.position.y) {
                int diff = num - this.position.y;
                this.position.y = 0;
                num -= diff;
                this.position.x++;
                num--;
                if (num > 0) {
                    this.position.y += num;
                }
            } else {
                this.position.y -= num;
            }
        }
        if (this.position.x >= 10) {
            this.position.x = x;
            this.position.y = y;
        }
    }

    void setThePosition(Coordinate position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    int rollTheDice() {
        int valueOfDice = (int)(Math.random() * 6);
        return valueOfDice + 1;
    }
}

class SnakesLadersGame {
    
    List<Snake> initializeSnakes() {
        List<Snake> snakes = new ArrayList<Snake>();
        snakes.add(new Snake("Snake1",new Coordinate(1, 4), new Coordinate(0, 7)));
        snakes.add(new Snake("Snake2", new Coordinate(5, 8), new Coordinate(2, 7)));
        snakes.add(new Snake("Snake3", new Coordinate(7, 2), new Coordinate(2, 4)));
        snakes.add(new Snake("Snake4", new Coordinate(9, 7), new Coordinate(8, 8)));
        snakes.add(new Snake("Snake5", new Coordinate(9, 5), new Coordinate(7, 5)));
        snakes.add(new Snake("Snake6", new Coordinate(9, 1), new Coordinate(2, 0)));
        return snakes;
    }

    List<Lader> initializeLaders() {
        List<Lader> laders = new ArrayList<Lader>();
        laders.add(new Lader("Lader1", new Coordinate(0, 1), new Coordinate(3, 4)));
        laders.add(new Lader("Lader2", new Coordinate(0, 3), new Coordinate(2, 6)));
        laders.add(new Lader("Lader3", new Coordinate(0, 8), new Coordinate(3, 9)));
        laders.add(new Lader("Lader4", new Coordinate(4, 6), new Coordinate(8, 3)));
        laders.add(new Lader("Lader5", new Coordinate(6, 9), new Coordinate(8, 6)));
        laders.add(new Lader("Lader6", new Coordinate(7, 9), new Coordinate(9, 9)));
        return laders;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        SnakesLadersGame game = new SnakesLadersGame();

        List<Lader> laders = game.initializeLaders();
        List<Snake> snakes = game.initializeSnakes();

        GameBoard gameBoard = new GameBoard(laders, snakes);

        System.out.print("Enter Player1 name: ");
        String player1_name = in.next();
        System.out.print("Enter Player2 name: ");
        String player2_name = in.next();

        Player player1 = new Player(player1_name, new Coordinate(0, 0));
        Player player2 = new Player(player2_name, new Coordinate(0, 0));

        Player currentPlayer = player1;
        int currentPosition_x, currentPosition_y;

        String str = in.nextLine();

        while(true) {
            System.out.println(currentPlayer.name + " Press Enter to roll the dice");
            str = in.nextLine();
            if (str.equals("")) {
                int numOnDice = currentPlayer.rollTheDice();
                System.out.println("Your number on dice: " + numOnDice);

                currentPosition_x = currentPlayer.position.x;
                currentPosition_y = currentPlayer.position.y;
                currentPlayer.setThePosition(numOnDice);
                
                Lader lader = gameBoard.checkForTheLaderInTheBox(currentPlayer.position);
                if (lader != null) {
                    currentPlayer.setThePosition(lader.end);
                    System.out.println("You have climbed a ladder from (" + lader.start.x + "," + lader.start.y + ") TO (" + lader.end.x + "," + lader.end.y + ")");
                    continue;
                }
                
                Snake snake = gameBoard.checkForTheSnakeInTheBox(currentPlayer.position);
                if (snake != null) {
                    currentPlayer.setThePosition(snake.end);
                    System.out.println("You have eaten by a snake from (" + snake.start.x + "," + snake.start.y + ") TO (" + snake.end.x + "," + snake.end.y + ")");
                }

                System.out.println("Your current position: (" + currentPlayer.position.x + "," + currentPlayer.position.y + ")");

                if (numOnDice == 6 && (currentPosition_x != currentPlayer.position.x || currentPosition_y != currentPlayer.position.y)) {
                    continue;
                }

                if (currentPlayer.position.x == 9 && currentPlayer.position.y == 0) {
                    System.out.println(currentPlayer.name + " Won the match!");
                    break;
                }
                
            } else {
                continue;
            }

            if(currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }
        

    }

}
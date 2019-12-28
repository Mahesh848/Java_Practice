import java.util.Scanner;

class GridException extends Exception {
    public GridException(String msg) {
        super(msg);
    }
}

class GameBoard {
    char[][] grid = new char[3][3];

    void fillTheBoardPosition(int x, int y, char player) {
        this.grid[x][y] = player;
    }

    boolean checkForTheGameHoriZontally(char player) {
        int count;
        for (int i = 0; i < 3; i++) {
            count = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == player) {
                    count++;
                }
            }
            if (count == 3)
                return true;
        }
        return false;
    }

    boolean checkForTheGameVertically(char player) {
        int count;
        for (int j = 0; j < 3; j++) {
            count = 0;
            for (int i = 0; i < 3; i++) {
                if (grid[i][j] == player) {
                    count++;
                }
            }
            if (count == 3)
                return true;
        }
        return false;
    }

    boolean checkForTheGameDiagonally(char player) {
        int count = 0;
        for (int i = 0, j = 0; i < 3 && j < 3; i++, j++) {
            if (grid[i][j] == player) {
                count++;
            }
        }
        if (count == 3)
            return true;
        count = 0;
        for (int i = 0, j = 2; i < 3 && j >= 0; i++, j--) {
            if (grid[i][j] == player) {
                count++;
            }
        }
        if (count == 3)
            return true;
        return false;
    }

    boolean isTheBoardFilled() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.grid[i][j] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    void printTheBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" ");
                if (this.grid[i][j] != '\u0000') {
                    System.out.print(this.grid[i][j]);
                } else {
                    System.out.print(" ");
                }
                if (j < 2)
                    System.out.print(" | ");
            }
            System.out.println();
            if (i < 2)
                System.out.println("-------------");
        }
    }
}

class Player {
    String name;
    char letter;

    void enterThePositionToFill(GameBoard board) throws GridException {
        Scanner in = new Scanner(System.in);
        int arr[] = new int[2];
        arr[0] = in.nextInt();
        arr[1] = in.nextInt();
        if ((arr[0] == 1 || arr[0] == 2 || arr[0] == 3) && (arr[1] == 1 || arr[1] == 2 || arr[1] == 3)) {
            if (board.grid[arr[0] - 1][arr[1] - 1] != '\u0000') {
                throw new GridException("You entered the position which is not empty");
            }
            board.fillTheBoardPosition(arr[0] - 1, arr[1] - 1, this.letter);
        } else {
            throw new GridException("You entered the position which is out of the game board");
        }
    }
}

class Game {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        GameBoard board = new GameBoard();
        boolean isBoardFilled;
        Player currentPlayer, player1, player2;
        player1 = new Player();
        player2 = new Player();

        System.out.print("Enter player1 name: ");
        player1.name = in.next();

        System.out.print("Enter player1 Char: ");
        player1.letter = in.next().charAt(0);

        System.out.print("Enter player2 name: ");
        player2.name = in.next();

        System.out.print("Enter player2 Char: ");
        player2.letter = in.next().charAt(0);

        currentPlayer = player1;

        while (true) {
            try {
                System.out.print(currentPlayer.name + " Enter your position:  ");
                currentPlayer.enterThePositionToFill(board);
                board.printTheBoard();
                if (board.checkForTheGameHoriZontally(currentPlayer.letter)
                        || board.checkForTheGameVertically(currentPlayer.letter)
                        || board.checkForTheGameDiagonally(currentPlayer.letter)) {
                    System.out.println(currentPlayer.name + " won the game...!");
                    break;
                }
                isBoardFilled = board.isTheBoardFilled();
                if (isBoardFilled) {
                    System.out.println("Game is tied");
                    break;
                }
            } catch (GridException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }
    }
}
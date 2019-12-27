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
        for (int i = 2, j = 2; i >= 0 && j >= 0; i--, j--) {
            if (grid[i][j] == player) {
                count++;
            }
        }
        if (count == 3)
            return true;
        return false;
    }

    boolean printTheBoard() {
        int noOfFilledBoxes = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" ");
                if (this.grid[i][j] != '\u0000') {
                    noOfFilledBoxes++;
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
        if (noOfFilledBoxes == 9) {
            return true;
        }
        return false;
    }
}

class Player {
    String name;

    Player(String name) {
        this.name = name;
    }

    int[] enterThePositionToFill(GameBoard board) throws GridException {
        Scanner in = new Scanner(System.in);
        int arr[] = new int[2];
        arr[0] = in.nextInt();
        arr[1] = in.nextInt();
        if ((arr[0] == 1 || arr[0] == 2 || arr[0] == 3) && (arr[1] == 1 || arr[1] == 2 || arr[1] == 3)) {
            if (board.grid[arr[0] - 1][arr[1] - 1] != '\u0000') {
                throw new GridException("You entered the position which is not empty");
            }
            return arr;
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
        Player current, p1, p2;
        String name1, name2;

        System.out.print("Enter player1 name: ");
        name1 = in.next();
        System.out.print("Enter player2 name: ");
        name2 = in.next();

        p1 = new Player(name1);
        p2 = new Player(name2);

        current = p1;
        char currentChar = 'X';

        while (true) {
            try {
                System.out.print(current.name + " Enter your position:  ");
                int arr[] = current.enterThePositionToFill(board);
                board.fillTheBoardPosition(arr[0] - 1, arr[1] - 1, currentChar);
                isBoardFilled = board.printTheBoard();
                if (board.checkForTheGameHoriZontally(currentChar) || board.checkForTheGameVertically(currentChar)
                        || board.checkForTheGameDiagonally(currentChar)) {
                    System.out.println(current.name + " won the game...!");
                    break;
                }
                if (isBoardFilled) {
                    System.out.println("Game is tied");
                    break;
                }
            } catch (GridException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (current == p1) {
                current = p2;
                currentChar = 'O';
            } else {
                current = p1;
                currentChar = 'X';
            }
        }
    }
}
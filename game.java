class Grid {
    int grid[][] = new int[10][10];

    void assignTheBallLocation(int x, int y) {
        this.grid[x][y] = 1;
    }
}

class Person {
    int grid[][];
    int path[][] = new int[100][2];
    int length = 99999999;

    Person(Grid grid) {
        this.grid = grid.grid;
    }

    void findPath(int x, int y, int path[][], int path_len) {
        path[path_len][0] = x;
        path[path_len][1] = y;
        if (x < 10 && y < 10 && this.grid[x][y] == 1) {
            if (path_len < length) {
                length = path_len;
                for (int i = 0; i < path_len; i++) {
                    this.path[i][0] = path[i][0];
                    this.path[i][1] = path[i][1];
                }
            }
            return;
        }
        if (x < 10) {
            findPath(x + 1, y, path, path_len + 1);
        }
        if (y < 10) {
            findPath(x, y + 1, path, path_len + 1);
        }
        path[path_len][0] = -1;
        path[path_len][1] = -1;
    }
}

class Ball {
    int x, y;

    void generateRandomCoordinates() {
        this.x = (int) (Math.random() * 10);
        this.y = (int) (Math.random() * 10);
    }
}

class Game {
    public static void main(String[] args) {
        Ball ball = new Ball();
        ball.generateRandomCoordinates();

        Grid grid = new Grid();
        grid.assignTheBallLocation(ball.x, ball.y);

        Person person = new Person(grid);
        person.findPath(0, 0, new int[100][2], 0);

        int[][] path = person.path;
        int length = person.length;
        for (int i = 0; i < length; i++) {
            System.out.print(path[i][0] + "," + path[i][1] + "  ");
        }
        System.out.println(ball.x + "," + ball.y);
    }
}
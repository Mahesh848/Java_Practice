class Grid {
    int grid[][] = new int[10][10];
    Ball ball;

    void setTheBall(Ball ball) {
        this.ball = ball;
    }
}

class Person {
    int path[][] = new int[100][2];
    int path_length = 99999999;

    void findPath(Grid grid, int x, int y, int path[][], int path_len) {
        path[path_len][0] = x;
        path[path_len][1] = y;
        if (x < 10 && y < 10 && grid.ball.x == x && grid.ball.y == y) {
            if (path_len < path_length) {
                path_length = path_len;
                for (int i = 0; i < path_len; i++) {
                    this.path[i][0] = path[i][0];
                    this.path[i][1] = path[i][1];
                }
            }
            return;
        }
        if (x < 10) {
            findPath(grid, x + 1, y, path, path_len + 1);
        }
        if (y < 10) {
            findPath(grid, x, y + 1, path, path_len + 1);
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
        grid.setTheBall(ball);

        Person person = new Person();
        person.findPath(grid, 0, 0, new int[100][2], 0);

        int[][] path = person.path;
        int path_length = person.path_length;
        for (int i = 0; i < path_length; i++) {
            System.out.print(path[i][0] + "," + path[i][1] + "  ");
        }
        System.out.println(ball.x + "," + ball.y);
    }
}
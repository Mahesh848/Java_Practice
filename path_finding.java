class Grid {
    int grid[][] = new int[10][10];
    int path[][] = new int[100][2];
    int length;
    void assignTheBallLocation(int x, int y) {
        this.grid[x][y] = 1;
    }
    void findPath(int x, int y, int path[][], int path_len) {
        path[path_len][0] = x;
        path[path_len][1] = y;
        if (x < 10 && y < 10 && grid[x][y] == 1) {
            if (length < path_len) {
                length = path_len;
                for (int i=0; i<path_len; i++) {
                    this.path[i][0] = path[i][0];
                    this.path[i][1] = path[i][1];
                }
            }
            return;
        }
        if (x < 10){
            findPath(x+1, y, path, path_len+1);
        }
        if (y < 10) {
            findPath(x, y+1, path, path_len+1);
        }
        path[path_len][0] = -1;
        path[path_len][1] = -1;  
    }
    int[][] getPath() {
        return this.path;
    }

    int getPathLength() {
        return this.length;
    }
}
class Test {
    public static void main(String[] args) {
        Grid g = new Grid();
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        g.assignTheBallLocation(x, y);
        g.findPath(0, 0, new int[100][2], 0);
        int [][]path = g.getPath();
        int length = g.getPathLength();
        for (int i=0; i<length; i++) {
            System.out.print(path[i][0]+","+path[i][1]+"  ");
        }
        System.out.println(x+","+y);
    }
}
package lifeGame1;

import java.util.Scanner;

public class Cell {
    static final int maxLength=10;
    static final int maxWidth=10;
    private static int[][] grid; //一个数据代表一个细胞,0代表死，1代表活
    
    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
        System.out.println("生命游戏开始: ");
        //二维空间中放置细胞初始化数量
        Cell();
        randomCell();
        //显示初始状态
        showCell();
        //是否进行下一次演化
        String flag;
        do {
            //开始生命游戏
        	update();
            System.out.println("是否进行下一次演化: y/n");
            flag = input.next();
            showCell();
        } while (flag.equalsIgnoreCase("y"));
    }

    public static void Cell() {
    	int row,col;
        grid = new int[maxLength + 2][maxWidth + 2];
        for (row = 0; row <= maxLength + 1; row++) {
            for (col = 0; col <= maxWidth + 1; col++)
                grid[row][col] = 0;
        }
    }

    //随机初始化细胞
    public static void randomCell(){
        for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                grid[i][j] = Math.random()>0.5?1:0;
    }

    //繁衍
    public static void update() {
        int[][] newGrid = new int[maxLength + 2][maxWidth + 2];
        for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                switch (getNeighborCount(i, j)) {
                    case 2:
                        newGrid[i][j] = grid[i][j]; //细胞状态保持不变
                        break;
                    case 3:
                        newGrid[i][j] = 1; // Cell is alive.
                        break;
                    default:
                        newGrid[i][j] = 0; // Cell is dead.
                }
        for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                grid[i][j] = newGrid[i][j];
    }

    //获取细胞的邻居数量
    static int getNeighborCount(int i1, int j1) {
        int count = 0;
        for (int i = i1 - 1; i <= i1 + 1; i++)
            for (int j = j1 - 1; j <= j1 + 1; j++)
                count += grid[i][j]; //如果邻居还活着，邻居数便会+1
        count -= grid[i1][j1]; //每个细胞不是自己的邻居，则如果细胞还活着，邻居数-1.

        return count;
    }
    
    static void showCell() {
    	int row, col;
        System.out.printf("\n细胞状态\n");

        for (col = 0; col < maxWidth; col++) {
        	System.out.printf(" ———");
        }
        System.out.println();//换行
        for (row = 0; row < maxLength; row++) {
            System.out.printf("|");//行的最左边的线
            for (col = 0; col < maxWidth; col++) {//各单元格细胞的生成状态
                switch (grid[row][col]) {
                    case 1:
                        System.out.printf(" ● |");
                        break;
                    case 0:
                        System.out.printf(" ○ |");
                        break;
                }
            }
            System.out.println();//换行
            for (col = 0; col < maxWidth; col++) {
            	System.out.printf(" ———");
            }
            System.out.println();//换行
        }
    }
    
    static int checkCell() {
    	int cellCount=0;
    	for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++) {
            	if(grid[i][j]==1)
            		cellCount++;
            }
    	return cellCount;
    }
}

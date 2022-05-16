package lifeGame1;

import java.util.Scanner;

public class Cell {
    static final int maxLength=10;
    static final int maxWidth=10;
    private static int[][] grid; //һ�����ݴ���һ��ϸ��,0��������1�����
    
    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
        System.out.println("������Ϸ��ʼ: ");
        //��ά�ռ��з���ϸ����ʼ������
        Cell();
        randomCell();
        //��ʾ��ʼ״̬
        showCell();
        //�Ƿ������һ���ݻ�
        String flag;
        do {
            //��ʼ������Ϸ
        	update();
            System.out.println("�Ƿ������һ���ݻ�: y/n");
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

    //�����ʼ��ϸ��
    public static void randomCell(){
        for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                grid[i][j] = Math.random()>0.5?1:0;
    }

    //����
    public static void update() {
        int[][] newGrid = new int[maxLength + 2][maxWidth + 2];
        for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                switch (getNeighborCount(i, j)) {
                    case 2:
                        newGrid[i][j] = grid[i][j]; //ϸ��״̬���ֲ���
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

    //��ȡϸ�����ھ�����
    static int getNeighborCount(int i1, int j1) {
        int count = 0;
        for (int i = i1 - 1; i <= i1 + 1; i++)
            for (int j = j1 - 1; j <= j1 + 1; j++)
                count += grid[i][j]; //����ھӻ����ţ��ھ������+1
        count -= grid[i1][j1]; //ÿ��ϸ�������Լ����ھӣ������ϸ�������ţ��ھ���-1.

        return count;
    }
    
    static void showCell() {
    	int row, col;
        System.out.printf("\nϸ��״̬\n");

        for (col = 0; col < maxWidth; col++) {
        	System.out.printf(" ������");
        }
        System.out.println();//����
        for (row = 0; row < maxLength; row++) {
            System.out.printf("|");//�е�����ߵ���
            for (col = 0; col < maxWidth; col++) {//����Ԫ��ϸ��������״̬
                switch (grid[row][col]) {
                    case 1:
                        System.out.printf(" �� |");
                        break;
                    case 0:
                        System.out.printf(" �� |");
                        break;
                }
            }
            System.out.println();//����
            for (col = 0; col < maxWidth; col++) {
            	System.out.printf(" ������");
            }
            System.out.println();//����
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

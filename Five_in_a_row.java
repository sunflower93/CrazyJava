import java.util.Scanner;

/**
 * Description:
 * Five in a row game on the console
 * Created by ShaoBin on 2015/8/24.
 * version 1.0
 */
public class Five_in_a_row {
    int N = 15;
    private static char[][] array = new char[16][16];
    static void display(){
        /**
         * Description:
         * 绘制棋盘函数
         * 展示现在棋盘和棋况
         * @author ShaoBin
         */
        for (int i = 0;i < 16;i++) {
            for (int j = 0; j < 16; j++) {
                if (i == 0)System.out.print(j);
                else if (j == 0)System.out.print(i);
                else System.out.print(array[i][j]);
                if ((i == 0 && j < 10) || (j == 0 && i < 10))System.out.print("    ");
                else System.out.print("   ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void start(){
        /**
         * Deacription:
         * 棋盘初始化程序
         * @author ShaoBin
         */
        for (int i = 0;i < 16;i++) {
            for (int j = 0; j < 16; j++) {
                if (i == 0 || j == 0) array[i][j] = 0;
                else array[i][j] = '┼';
            }
        }
        display();
        System.out.println("黑棋先走！");
        System.out.print("请输入要下棋的位置，以x y格式输入：");
    }
    static boolean fun(int last_x,int last_y){
        /**
         * Description:
         * 获胜检查函数
         * 检查是否获胜，获胜条件为在一条直线上有五个自己的棋子
         * 循环1、2检查横向是否获胜
         * 循环3、4检查纵向是否获胜
         * 循环5、6检查右斜向（\）是否获胜
         * 循环7、8检查左斜向（/）是否获胜
         * @author ShaoBin
         * @param 当前下棋位置
         * @return Bool值，true为获胜，false则未获胜
         */
        int row,line,slash_right,slash_left;                              //横向、纵向、右斜向、左斜向计数
        row = line = slash_right = slash_left = 1;
        int decrement = 1;                                                //设置检查所用减量
        while (array[last_x][last_y] == array[last_x][last_y - decrement]){                        //1
            row++;
            if (row >= 5)return true;
            decrement++;
        }
        decrement = 1;
        while (array[last_x][last_y] == array[last_x][last_y + decrement]){                        //2
            row++;
            if (row >= 5)return true;
            decrement++;
        }
        decrement = 1;
        while (array[last_x][last_y] == array[last_x - decrement][last_y]){                        //3
            line++;
            if (line >= 5)return true;
            decrement++;
        }
        decrement = 1;
        while (array[last_x][last_y] == array[last_x + decrement][last_y]){                        //4
            line++;
            if (line >= 5)return true;
            decrement++;
        }
        decrement = 1;
        while (array[last_x][last_y] == array[last_x - decrement][last_y - decrement]){            //5
            slash_right++;
            if (slash_right >= 5)return true;
            decrement++;
        }
        decrement = 1;
        while (array[last_x][last_y] == array[last_x + decrement][last_y + decrement]){            //6
            slash_right++;
            if (slash_right >= 5)return true;
            decrement++;
        }
        decrement = 1;
        while (array[last_x][last_y] == array[last_x - decrement][last_y + decrement]){            //7
            slash_left++;
            if (slash_left >= 5)return true;
            decrement++;
        }
        decrement = 1;
        while (array[last_x][last_y] == array[last_x + decrement][last_y - decrement]){            //8
            slash_left++;
            if (slash_left >= 5)return true;
            decrement++;
        }
        return false;
    }
    public static void main(String[] args) {
        start();                                                               //初始化棋盘
        Scanner input = new Scanner(System.in);
//        boolean win = false;
        boolean flag = true;
        int x, y;                                                               //下棋坐标
        while (true) {
            x = y = 0;
            x = input.nextInt();
            y = input.nextInt();
            if (flag) {
                if (array[x][y] == '●' || array[x][y] == '○') {
                    System.out.println("输入错误！！！此处已有棋子，请重新输入！");
                    System.out.print("请黑棋输入要下棋的位置，以x y格式输入：");
                    flag = true;
                } else {
                    array[x][y] = '●';
                    display();
                    if (fun(x, y)) {
                        System.out.println('●' + "获胜，恭喜您！");
                        break;
                    }
                    System.out.print("请白棋输入要下棋的位置，以x y格式输入：");
                    flag = false;
                }
            }
            else {
                if (array[x][y] == '●' || array[x][y] == '○') {
                    System.out.println("输入错误！！！此处已有棋子，请重新输入！");
                    System.out.print("请白棋输入要下棋的位置，以x y格式输入：");
                    flag = false;
                } else {
                    array[x][y] = '○';
                    display();
                    if (fun(x, y)) {
                        System.out.println('○' + "获胜，恭喜您！");
                        break;
                    }
                    System.out.print("请黑棋输入要下棋的位置，以x y格式输入：");
                    flag = true;
                }
            }
        }
    }
}

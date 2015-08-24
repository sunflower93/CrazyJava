import java.util.Scanner;

/**
 * Description:
 * Created by Shaobin on 2015/8/24.
 * Change a float number into RMB
 * It's a small program to practice Java.
 * @version 1.0
 */
public class Float_RMB {
    static String fun(int x){
        /**
         * Description:
         * 将一个个位数转换成一位中文大写数字
         * @author Shaobin
         * @param 需要转换的一位数字
         * @return 返回转换后的中文大写一位数字
         */
        switch(x){
            case 0 : return "零";
            case 1 : return "壹";
            case 2 : return "贰";
            case 3 : return "叁";
            case 4 : return "肆";
            case 5 : return "伍";
            case 6 : return "陆";
            case 7 : return "柒";
            case 8 : return "捌";
            case 9 : return "玖";
        }
        return "Wrong";
    }
    static int getlength(long number){
        /**
         * Description:
         * 确定一个整型数字的位数
         * @author Shaobin
         * @param 一个未知位数的整形数字
         * @return 返回整型数的位数
         */
        String numberString = String.valueOf(number);
        return numberString.length();
    }
    static String fun_unit(int n){
        /**
         * Description:
         * 确定当前数字单位
         * @author ShaoBin
         * @param 计数变量n
         * @return 返回当前单位
         */
        String tt = "";
        while (n >= 4){
            if (n / 8 != 0){
                n = n - 8;
            }
            else if (n / 4 != 0) {
                n = n - 4;
            }
        }
        switch (n){
            case 1 : tt = "拾" + tt;break;
            case 2 : tt = "佰" + tt;break;
            case 3 : tt = "仟" + tt;break;
            default : tt = tt;
        }
        return tt;
    }
    public static void main(String[] args){
        System.out.print("Please input a float number:");
        Scanner input = new Scanner(System.in);
        double fl_in = input.nextDouble();                                       //输入的原始数字
    //对原始数字进行预处理
        double fl_dec;
        long fl_int;
        int fl_dec_j = 0;
        int fl_dec_f = 0;
        fl_int = (long)fl_in;                                                    //整数部分
        fl_dec = fl_in - fl_int;                                                 //小数部分
        System.out.println("int:"+fl_int+",dec:"+fl_dec);
        fl_dec_j = (int)(fl_dec * 10);                                           //第一位小数
        fl_dec_f = (int)(fl_dec * 100) % 10;                                     //第二位小数
//        String[] money = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
//        String[] unit = {"分","角","元","拾","佰","仟","万","亿"};
        String answer = "";                                                      //结果字符串
        if (fl_dec_f != 0){                                                      //小数最低位不为0的处理
            answer = "分";
            String t = fun(fl_dec_f);
            answer = t + answer;
            if (fl_dec_j != 0){
                t = fun(fl_dec_j);
                answer = t + "角" + answer;
            }
        }
        else if (fl_dec_j != 0){                                                 //小数最高位不为0的处理
            answer = "角";
            String t = fun(fl_dec_j);
            answer = t + answer;
        }
        answer = "元" + answer;
        int fl_int_len = getlength(fl_int);                                      //获得整数部分位数
        int fl_int_low = 0;                                                      //最低一位
        long fl_int_high = fl_int;                                                //其余高位部分
        int zeronumber = 0;                                                      //连续0的个数
        for (int i = 0;i < fl_int_len;i++){
            if (i != 0 && i % 4 == 0 && i % 8 != 0)answer = "万" + answer;
            else if (i != 0 && i % 8 == 0)answer = "亿" +answer;
            fl_int_low = (int)(fl_int_high % 10);
            fl_int_high = fl_int_high / 10;
            if (fl_int_low == 0){                                                //当前位为0的处理
                if (zeronumber == 0)answer = "零" + answer;
                zeronumber++;
            }
            else {                                                               //当前位不为0的处理
                if (zeronumber != 0 )zeronumber = 0;
                answer = fun(fl_int_low) + fun_unit(i) + answer;
            }
        }
        System.out.println("RMB: "+answer);
    }
}

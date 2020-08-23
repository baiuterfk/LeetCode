package leetcode0679_24点游戏;


/**
 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。

 示例 1:

 输入: [4, 1, 8, 7]
 输出: True
 解释: (8-4) * (7-1) = 24
 示例 2:

 输入: [1, 2, 1, 2]
 输出: False
 注意:

 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/24-game
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    /**
     * 解析：
     * 要做回文子串，就要先知道什么是回文串。
     * 回文串，就是一个字符串正着读和反着读是一样的字符序列
     * 子串的意思，就是一个字符串，包含了多少个可以回文的字符串
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution2 a = new Solution2();
//        int [] s = {4,1,8,7};
//        int [] s = {1,9,1,2};
        int [] s = {5,6,7,8};

        boolean r = a.judgePoint24(s);
        System.out.println(r);
    }

    public boolean 这个不对judgePoint24(int[] nums) {
        double [] numbers = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numbers[i] = (double)nums[i];
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                for (int k = 0; k < numbers.length; k++) {
                    for (int l = 0; l < numbers.length; l++) {
                        if (i==j || i==k || i==l || j==k || j==l || k==l)
                            continue;
                        if(judge(numbers[i],numbers[j],numbers[k],numbers[l]))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean judge(double number0, double number1, double number2, double number3) {
        System.out.println("进入："+number0+"--"+number1+"--"+number2+"--"+number3);
        return judge(number0+number1,number2,number3)
            || judge(number0-number1,number2,number3)
            || judge(number1-number0,number2,number3)
            || judge(number0*number1,number2,number3)
            || judge(number0/number1,number2,number3)
            || judge(number1/number0,number2,number3);
    }
    private boolean judge(double number0, double number1, double number2){
        return judge(number0+number1,number2)
                || judge(number0-number1,number2)
                || judge(number1-number0,number2)
                || judge(number0*number1,number2)
                || judge(number0/number1,number2)
                || judge(number1/number0,number2);
    }

    private boolean judge(double number0, double number1){
        return Math.abs(number0+number1-TAGET)<WUCHA
                || Math.abs(number0-number1-TAGET)<WUCHA
                || Math.abs(number1-number0-TAGET)<WUCHA
                || Math.abs(number0*number1-TAGET)<WUCHA
                || Math.abs(number0/number1-TAGET)<WUCHA
                || Math.abs(number1/number0-TAGET)<WUCHA;
    }

    private final double TAGET = 24d;
    private final double WUCHA = 0.0001d;
}
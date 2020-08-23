package leetcode201_数字范围按位与;

public class Solution {
    public static void main(String[] args) {
        Solution a = new Solution();
        int m = 20000;
        int n = 2147483647;
//        int m = 0;
//        int n = 1;
        int r = a.rangeBitwiseAnd(m,n);
        System.out.println(r);
//        System.out.println();
    }
    public int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            offset++;
        }
        return m << offset;
    }
}
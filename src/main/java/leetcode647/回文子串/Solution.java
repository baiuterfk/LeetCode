package leetcode647.回文子串;


/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 解题思路：
 * 先判定一个字符串是不是回文串(力扣125题. 验证回文串)，
 * 再把所有子串循环放进去判断，true就+1
 * 返回答案
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
        Solution a = new Solution();
        String s = "abc";
        s = "aaa";

        int r = a.countSubstrings(s);
        System.out.println(r);
    }

    //这是提交答案
    public int countSubstrings(String s) {
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String a = s.substring(i,j);
                if (isPalindrome(a)){
                    k++;
                }
            }
        }
        return k;
    }

    public boolean isPalindrome(String str) {
        if (str.length()>500){
            System.out.println(str);
            return false;
        }
        int right = str.length() - 1;
        int left = 0;

        while (left < right) {
            if (!Character.isLetterOrDigit(str.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(str.charAt(right))) {
                right--;
                continue;
            }
            if (Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }



    //========================不懂回文的时候瞎写！=======================
//    public int countSubstrings(String s) {
//        Map<String,Integer> map = getMap(s);
////        System.out.println(map.size());
//        return map.size();
//    }
//
//    public Map<String,Integer> getMap(String s){
//        Map<String, Integer> result = new HashMap<String, Integer>();
//
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = i + 1; j <= s.length(); j++) {
//                String key = s.substring(i, j);
//                Integer value = result.get(key);
////                System.out.println(key+":"+value);
//                if (value == null){
//                    result.put(key,1);
//                }else{
//                    result.put(key,value+1);
//                }
//            }
//        }
//        return result;
//    }
}
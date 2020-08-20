package leetcode125.验证回文串;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 */

/**
 * 解题思路：
 *      两头对折，都一样就行
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
        String s = "abab";
        s = "A man, a plan, a canal: Panama";
        s = "Damosel, a poem? A carol? Or a cameo pale? (So mad!)";

        boolean flag = a.isPalindrome(s);
        System.out.println(flag);
    }



    //力扣125. 验证回文串
    public boolean isPalindrome(String str) {
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

    public boolean 第一次isPalindrome(String str) {

        String s = washString(str);
        System.out.println(s);
        for (int i = 0, j = s.length(); i <= j; i++, j--) {
            if (!s.substring(i, i + 1).equals(s.substring(j - 1, j))) {
                return false;
            }
        }
        return true;
    }

    //清洗字符串：仅保留英文字母
    public String washString(String str) {
        return str.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    }
}

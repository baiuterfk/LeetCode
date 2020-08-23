package leetcode0647.回文子串;


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
        s = "abba";
//        s = "ababa";
//        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        int r = a.countSubstrings(s);
        System.out.println(r);
    }

    //这是正式答案2   	1 ms	37.6 MB
    public int countSubstrings(String s) {
        /**
         * 问题，一共有多少个回文子串，
         * 思路：自己为中心向两边找 + 自己和下一个字符一起向两边找
         */
        char[] cc = s.toCharArray();
        int answer = 0;
        int left, right;

        for (int i = 0; i < cc.length; i++) {
            left = i; right = i;
            while ( left>=0 && right<cc.length && cc[left]==cc[right]){ answer++;left--;right++; }
            left = i; right = i+1;
            while ( left>=0 && right<cc.length && cc[left]==cc[right]){ answer++;left--;right++; }
        }
        return answer;
    }
    //这是正式答案1   7 ms	38.1 MB
    public int 答案1countSubstrings(String s) {
        int n = s.length();
        int answer = 0;

        //一共2n-1个回文中心
        for (int i = 0; i < 2*n-1; i++) {
            int left = i/2;
            int right = left + i%2;
            while (left >=0 && right<n && s.charAt(left)==s.charAt(right)){
                answer++;
                left--;right++; //继续向两边找
            }
        }

        return answer;
    }

    public int 第四次countSubstrings(String s) {   //理解的有点啰嗦
        /**
         * 上一次我的思路是，每前进一个字符，就在那个位置/2找到center，然后根据奇偶，确认left和right，
         * 但出现的问题是，结果老是缺！abc缺c，aaaa缺a，ababa缺ba…… 就是缺一半~
         * 问题就出现在循环是从i到s的长度n，但，应该要指到s的最后一个字符才是全解啊。
         * 于是：   循环到2n就行
         *
         * 那他左右递进的时候不就超出n了？  不会。因为左右一起走，当走到最后一个字符的时候，因为不能再向右走了，所以也不会向左。
         */
        int n = s.length();
        int answer = 0;
        for (int i = 0; i < 2*n-1; i++) {
            int left = i/2;
            int right = i/2+i%2;  //再强调一遍，i=0时，有1个字符，所以i%2=0的时候，是奇个字符;而%2=1的时候是偶个字符。
                                  //有奇个字符时（例如i=0），i/2=0为left位置，i%2=0，right=i/2+i%2=0，这时他的中心是（0,0）一个字符
                                  //有偶个字符时（例如i=1），i/2=0为left位置，i%2=1，right=i/2+i%2=1，这时他的中心是（0,1）两个字符
                                  //上次错就在这里！应该继续循环，找到单纯第2个字符，即（1,1）。
                                  //很简单，循环2倍就行，就像”ab__“，假装有接着循环”第3个字符“：
                                  //        i=2,则 left=i/2=1, right=i/2+i%2=1+0, 3个字符，中心字符（1,1）只有一个
                                  //   不需要再循环假装出来的第4个了，没有意义，你看：
                                  //        i=3,  left=1,      right=1+1=2,      4个字符, 中心字符应该有两个（1,2），但是没有下标2
                                  //   所以，再看看for吧，为什么要2N-1就是这个意思。  换个想法：
                                  //                有N位字符，要想每个字符都当一次中心，就要循环2N-1次！
            // 回文的中心一共有几个？
            // 答：length+length-1个
            // 为什么？
            // 因为…… 每个字符自己都要做一个回文中心
            //       自己和下一个字符，两个人再做一次回文中心
            //       最后那个字符没有下一个字符配对，减掉1
                                  //    1位，循环2N-1 = 1次      "a",中心：a
                                  //    2位，循环2N-1 = 3次      "ab",中心：a,ab,b
                                  //    3位，循环2N-1 = 5次      "abc",中心：a,ab,b,bc,c
                                  //    4位，循环2N-1 = 7次      "abcd",中心：a,ab,b,bc,c,cd,d
                                  //    ……           还想不明白吗？以”abcd“来说：
                                  //                        i=0,字串为”a",中心为:a
                                  //                        i=1,字串为”ab",中心为:ab
                                  //                        i=2,字串为”abc",中心为:b
                                  //                        i=3,字串为”abcd",中心为:bc
                                  //                        i=4,字串为”abcd_",中心为:c
                                  //                        i=5,字串为”abcd__",中心为:cd
                                  //                        i=6,字串为”abcd___",中心为:d
                                  //         i=6,即已经执行了7次，所以不会出现，(即便你2n不减1，也不会出事，因为while里有right<n就限制了)：
                                  //                 (不需要)i=7,字串为”abcd____",中心为:d_
            while ( left >=0 && right < n && s.charAt(left) == s.charAt(right)){
                answer++;
                left--;
                right++;
            }
        }
        return answer;
    }

    public int 第三次countSubstrings(String s) {
        /*
         * 这次的思路是，每前进一个字符，就所在位置/2，然后在那个位置，作为到当前i的距离（ 这时就会出现奇偶的情况 ）
         * 开始逐层展开，左右一致（ 其实，如果是奇数串则以一个字符左右对称；如果是偶数串则先判断中心两个是不是一致 ）
         */
        int answer = 0;
        int n = s.length();


        for (int i = 0; i < n; i++) {
//            int center = i/2;
//            int wei = i%2; //奇偶判断位,i%2==0,偶。     这里有问题：%2=0是偶没错。但i是从0开始的，所以i=0时，是有1个字符。
//                                                              所以，i%2==0，则为奇数个字符！
//            //以下找到左右位置，奇偶都可以处理了    这是错误的
//            int left = center+wei-1;
//            int right = center;
            //把上面四句整理一下：
//            int left = i/2+i%2-1;
//            int right = i/2;


            //这是正确的
//            int center = i/2;
//            int wei = i%2;  //=1，为偶个字符
//            int left = center;
//            int right = center+wei;
            int left = i/2;
            int right = i/2+i%2;

            System.out.println("第"+i+"次，left="+left+"，right="+right);

            while( left >= 0 && right < n
                    && s.charAt(left) == s.charAt(right) ){
                System.out.println("当前");
                answer++;

                left--;
                right++;
            }
        }


        /**
         * 输入："abc"
         * 输出：
         * 第0次，left=0，right=0
         * 当前
         * 第1次，left=0，right=1
         * 第2次，left=1，right=1
         * 当前
         * 2
         *
         * 少了尾巴的c！！什么原因？见正确答案了。
         */

        return answer;
    }
    public int 第二次countSubstrings(String s) {

        //   参考 https://leetcode-cn.com/problems/palindromic-substrings/solution/hui-wen-zi-chuan-by-leetcode-solution/
        /*
         * 这次的思路是：
         *    字串的每个字符往前走一步，以此为中心，向前找一个就向后找一个，如果对上了就是一个回文，如果没对上，那就继续走一步
         * 这次结果：
         *     这样做，无法保证全部情况。（1）上一个一样的，例如aa，也是回文，如果用上一位下一位就不对。（2）aaaa呢，也没法。
         * 所以问题出现在：
         *      偶数个时怎么找。
         * 哦：
         *      /2  就是这个意思：      奇数的时候/2，偶数的时候/2就是为了解决偶数时出错的这个情况
         * 再试一次（见第三次）。
         */
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            ans++;
            if ( i>0 && s.charAt(i)==s.charAt(i-1)){
                ans++;
            }
            for (int j = i-1; (j >= 0 && i+(i-j)<n ); j--) {
                //向前找
//                int left = j;
//                int right = i+(i-j);
                if (s.charAt(j)==s.charAt(i+(i-j))){
                    ans++;
                }else{
                    break;
                }
            }
        }

        return ans;
    }

    /**
     * 这次提交，传入参数：
     *
     * aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
     *
     * 时，便会超时！
     * 一定有更好的方法
     *
     * @param s
     * @return
     */
    public int 第一次countSubstrings(String s) {
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
package leetcode0001_两数之和;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int [] nums = {2,7,11,15};
        int target = 23;

        int result [] = s.twoSum(nums,target);
        for (int n: result) {
            System.out.print(n+",   ");
        }
    }

    //106 ms	39.8 MB
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length-1; j > i; j--) {
                if(target-nums[i]==nums[j])
                    return new int[]{i,j};
            }
        }
        return null;
    }
    //93 ms	39.8 MB
    public int[] 第一次twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}

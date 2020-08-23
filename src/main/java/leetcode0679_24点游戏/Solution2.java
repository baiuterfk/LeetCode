package leetcode0679_24点游戏;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    //	4 ms	39.2 MB
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<Double>(4);
        for (int num : nums) {
            list.add((double) num);
        }
        return solve(list);
    }
    boolean solve(List<Double> nums){
        if(nums.size() == 1){
            return Math.abs(nums.get(0) - 24) <= 0.00001;
        }
        for(int i = 0; i < nums.size(); i++){
            for(int j = i + 1; j < nums.size(); j++){
                List<Double> copy = new ArrayList<Double>(nums);
                double b = copy.remove(j), a = copy.remove(i);
                boolean valid = false;
                copy.add(a + b);
                valid |= solve(copy);
                copy.set(copy.size() - 1, a - b);
                valid |= solve(copy);
                copy.set(copy.size() - 1, a * b);
                valid |= solve(copy);
                copy.set(copy.size() - 1, a / b);
                valid |= solve(copy);
                copy.set(copy.size() - 1, b - a);
                valid |= solve(copy);
                copy.set(copy.size() - 1, b / a);
                valid |= solve(copy);
                if(valid) return true;
            }
        }
        return false;
    }
}

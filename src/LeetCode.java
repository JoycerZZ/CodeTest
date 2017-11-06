import java.util.HashMap;
import java.util.Map;

class Solution{
	
    public int[] twoSum(int[] nums, int target) {
    	int[] result = new int[2];
    	Map<Integer , Integer> hash = new HashMap<>();
    	for(int i = 0 ; i < nums.length ; i++){
    		if(hash.containsKey(target - nums[i])){
    			result[1] = i;
    			result[0] = hash.get(target - nums[i]);
    			break;
    		}
    		hash.put(nums[i] , i);
    	}
		return result;
    }
}

public class LeetCode {
	
	public static void main(String[] args) {
		int[] nums = {2 , 3 , 4};
		Solution one = new Solution();
		System.out.println(one.twoSum(nums, 6)[0]);
	}
}

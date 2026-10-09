1class Solution {
2    public int[] twoSum(int[] nums, int target) {
3        
4        Map<Integer, Integer>pair= new HashMap<>();
5        for(int i=0;i<nums.length;i++){
6            int num=nums[i];
7            if(pair.containsKey(target-num)){
8                return new int[]{ i,pair.get(target-num)};
9            }
10            else{
11                pair.put(num,i);
12            }
13
14        }
15        return new int[]{};
16    }
17}
1class Solution {
2    public int countPairs(List<Integer> nums, int target) {
3        int count=0;
4        for(int i=0;i<nums.size()-1;i++){
5            for(int j=i+1;j<nums.size();j++){
6                if((nums.get(i)+nums.get(j)) < target){
7                    count++;
8                }
9
10            }
11        }
12        return count ;
13    }
14}
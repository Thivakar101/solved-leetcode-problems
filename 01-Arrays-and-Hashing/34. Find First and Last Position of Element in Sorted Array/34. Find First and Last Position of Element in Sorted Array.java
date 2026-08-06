1class Solution {
2    private int binarysearch(int[]nums,int target,boolean leftbias){
3        int l=0;
4        int r=nums.length-1;
5        int i=-1;
6        while(l<=r){
7            int mid=(l+r)/2;
8            if(target<nums[mid]){
9                r=mid-1;
10            }
11            else if(target>nums[mid]){
12                l=mid+1;
13            }
14            else{
15                i=mid;
16                if(leftbias){
17                    r=mid-1;
18                }
19                else{
20                    l=mid+1;
21                }
22            }
23        }
24        return i;
25    }
26    public int[] searchRange(int[] nums, int target) {
27        int left=binarysearch(nums,target,true);
28        int right=binarysearch(nums,target,false);
29        int arr[]=new int[2];
30        arr[0]=left;
31        arr[1]=right;
32        return arr;
33        
34}
35}
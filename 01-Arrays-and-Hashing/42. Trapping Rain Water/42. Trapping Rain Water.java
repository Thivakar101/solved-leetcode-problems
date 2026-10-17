1class Solution {
2    public int trap(int[] height) {
3        int n=height.length;
4        int[]leftmax=new int[n];
5        int[]rightmax=new int[n];
6        leftmax[0]=height[0];
7        rightmax[n-1]=height[n-1];
8        for(int i=1;i<n;i++){
9            leftmax[i]=Math.max(leftmax[i-1],height[i]);
10        }
11        for(int i=n-2;i>=0;i--){
12            rightmax[i]=Math.max(rightmax[i+1],height[i]);
13        }
14        int tw=0;
15        for(int i=0;i<n;i++){
16            tw+=Math.min(rightmax[i],leftmax[i])-height[i];
17        }
18        return tw;
19
20    }
21}
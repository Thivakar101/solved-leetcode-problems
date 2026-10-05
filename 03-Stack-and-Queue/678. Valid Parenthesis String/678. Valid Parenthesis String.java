1class Solution {
2    public boolean checkValidString(String s) {
3        int low=0;
4        int high=0;
5        for(Character ch : s.toCharArray()){
6            if(ch=='('){
7                low++;
8                high++;
9            }
10            if(ch==')'){
11                low--;
12                high--;
13            }
14            if(ch=='*'){
15                low--;
16                high++;
17            }
18            if(low<=-1){
19                low=0;
20            }
21            if(high<0){
22                return false;
23            }
24        }
25        return low==0;
26    }
27}
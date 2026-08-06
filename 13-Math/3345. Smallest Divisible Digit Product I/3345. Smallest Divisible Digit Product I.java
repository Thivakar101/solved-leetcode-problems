1class Solution {
2    public boolean check(int n,int t){
3        int pro=1;
4        while(n>0){
5            int temp=n%10;
6            pro=pro*temp;
7            n=n/10;
8        }
9       if(pro%t==0){
10        return true;
11       }
12       return false;
13
14    }
15    public int smallestNumber(int n, int t) {
16        while(!check(n,t)){
17            n++;
18        }
19        return n;
20        
21    }
22}
1class Solution {
2    public boolean isPalindrome(int x) {
3        int temp=x;
4        if(x<0){
5            return false;
6        }
7        int count=0;
8        while(x>0){
9            count=count*10;
10            count=count + (x%10);
11            x=x/10;
12        }
13        if(count==temp){
14            return true ;
15        }
16        else{
17            return false;
18        }
19        
20    }
21}
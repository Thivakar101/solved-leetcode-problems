1class Solution {
2    public String longestCommonPrefix(String[] strs) {
3        Arrays.sort(strs);
4        StringBuilder sb=new StringBuilder();
5        char firstword[]=strs[0].toCharArray();
6        char lastword[]=strs[strs.length-1].toCharArray();
7        for(int i=0;i<firstword.length;i++){
8            if(firstword[i]!=lastword[i]){
9                break;
10            }
11            sb.append(firstword[i]);
12        }
13        return sb.toString();
14        
15    }
16}
1class Solution {
2    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
3        while(tx>sx && ty>sy){
4            if(tx>ty){
5                tx%=ty;
6            }
7            else{
8                ty%=tx;
9            }
10        }
11        if(tx==sx && ty==sy){
12            return true;
13        }
14        if(tx==sx ){
15            return ty>sy && (ty-sy)%sx==0;
16        }
17        if(ty==sy){
18            return tx>sx&&(tx-sx)%sy==0;
19        }
20        return false;
21    }
22}
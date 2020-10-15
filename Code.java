class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map =new HashMap<>();
        
        for(int i=0; i<s.length();i++){
            map.put(s.charAt(i),0);
        }
        for(int i=0; i<t.length();i++){
            if(map.containsKey(t.charAt(i)))
                map.put(t.charAt(i),map.get(t.charAt(i))+1);
            else
                return "";
        }
        
       
        int start =0;
        int end =0;
        int counter = t.length();
        int minstart = 0;
        int minLen = Integer.MAX_VALUE;
        
        
        while(end<s.length()){
            char ch = s.charAt(end);
            if(map.get(ch)>0){
                counter--;
            }
            map.put(ch, map.get(ch)-1);
            end++;
            
            while(counter==0){
                if(end-start<minLen){
                    minstart = start;
                    minLen = end-start;
                }
                char st = s.charAt(start);
                map.put(st, map.get(st)+1);
                if(map.get(st)>0){
                    counter++;
                }
                start++;
            }
            
            
            
        }
        
        
        if(minLen !=Integer.MAX_VALUE){
            return s.substring(minstart,minstart+minLen);
        }
        return "";
    }
}

--------------------------------------------------------------------------------------------------
The code of solving this problem is below. It might be the shortest among all solutions provided in Discuss.

string minWindow(string s, string t) {
        vector<int> map(128,0);
        for(auto c: t) map[c]++;
        int counter=t.size(), begin=0, end=0, d=INT_MAX, head=0;
        while(end<s.size()){
            if(map[s[end++]]-->0) counter--; //in t
            while(counter==0){ //valid
                if(end-begin<d)  d=end-(head=begin);
                if(map[s[begin++]]++==0) counter++;  //make it invalid
            }  
        }
        return d==INT_MAX? "":s.substr(head, d);
    }
Here comes the template.

For most substring problem, we are given a string and need to find a substring of it which satisfy some restrictions. A general way is to use a hashmap assisted with two pointers. The template is given below.

int findSubstring(string s){
        vector<int> map(128,0);
        int counter; // check whether the substring is valid
        int begin=0, end=0; //two pointers, one point to tail and one  head
        int d; //the length of substring

        for() { /* initialize the hash map here */ }

        while(end<s.size()){

            if(map[s[end++]]-- ?){  /* modify counter here */ }

            while(/* counter condition */){ 
                 
                 /* update d here if finding minimum*/

                //increase begin to make it invalid/valid again
                
                if(map[s[begin++]]++ ?){ /*modify counter here*/ }
            }  

            /* update d here if finding maximum*/
        }
        return d;
  }
One thing needs to be mentioned is that when asked to find maximum substring, we should update maximum after the inner while loop to guarantee that the substring is valid. On the other hand, when asked to find minimum substring, we should update minimum inside the inner while loop.

The code of solving Longest Substring with At Most Two Distinct Characters is below:

int lengthOfLongestSubstringTwoDistinct(string s) {
        vector<int> map(128, 0);
        int counter=0, begin=0, end=0, d=0; 
        while(end<s.size()){
            if(map[s[end++]]++==0) counter++;
            while(counter>2) if(map[s[begin++]]--==1) counter--;
            d=max(d, end-begin);
        }
        return d;
    }
The code of solving Longest Substring Without Repeating Characters is below:

int lengthOfLongestSubstring(string s) {
        vector<int> map(128,0);
        int counter=0, begin=0, end=0, d=0; 
        while(end<s.size()){
            if(map[s[end++]]++>0) counter++; 
            while(counter>0) if(map[s[begin++]]-->1) counter--;
            d=max(d, end-begin); //while valid, update d
        }
        return d;
    }

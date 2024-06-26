/**
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
//Runtime 1 ms beats 37.73% of users with java
//Memory 41.30 MB beats 76.04% of user with java
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs){

        if(strs == null ||strs.length == 0){
            return "";
        }
        int max = Integer.MAX_VALUE;
        for(String  str : strs){
            if(str.isEmpty()) return "";
            max = Math.min(max, str.length());
        }

        for(int i = 0 ; i < max ; i++){
            for(int j = 1 ; j < strs.length ; j++){
                if(i == strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) return strs[0].substring(0, i);
            }
        }
        return strs[0].substring(0,max);
    }
}


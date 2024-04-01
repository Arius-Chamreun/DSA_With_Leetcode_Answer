

/**
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
//Runtime 14 ms beats 37.73% of users with java
//Memory 45 MB of beats 22.36 of users with java
public class Palindrome {
    public static boolean isPalindrome(String s) {

        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim();
        char[] charArray = s.toCharArray();
        int start = 0 ;
        int end = charArray.length -1 ;
        while(start < end){
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start++;
            end--;
        }
        return s.equals(String.valueOf(charArray));
    }
    }


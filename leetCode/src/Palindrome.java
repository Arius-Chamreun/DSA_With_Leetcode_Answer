

/**
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
//Runtime 14 ms beats 37.73% of users with java
//Memory 45 MB of beats 22.36 of users with java
public class Palindrome {
    public boolean isPalindrome(String s) {

        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim();

        StringBuffer stringBuffer = new StringBuffer(s);
        String newStr =  stringBuffer.reverse().toString();
        return s.equals(newStr);

    }
    }


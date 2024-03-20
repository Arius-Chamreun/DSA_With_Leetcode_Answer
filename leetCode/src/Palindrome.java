public class Palindrome {
    public static boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim();
//        char newChar;
//        String newStr= "";
//        for(int i =   s.length() - 1 ; i >=0  ; i--){
//            newChar = s.charAt(i);
//            newStr += newChar;
//        }
//        System.out.println(s);
//        System.out.println(newStr);
//        return s.equals(newStr);

        StringBuffer stringBuffer = new StringBuffer(s);
        String newStr =  stringBuffer.reverse().toString();
        System.out.println(stringBuffer);
        return s.equals(newStr.trim());
    }
    }


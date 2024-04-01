/**
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
//Runtime 2 ms beats 100% of users with java
//Memory 44.50 MB beats 80.96% of users with java

public class RomanToInteger {
   public int romanToInt(String s) {
        int value = 0;
        for(int i= 0 ; i < s.length()  ; i++){
            switch (s.charAt(i)){
                case 'I' -> value += 1;

                case 'V' -> {
                    value += 5;
                    if(i> 0&& s.charAt(i-1) == 'I') value-=2;
                }
                case 'X' -> {
                    value += 10;
                    if(i> 0&& s.charAt(i-1) == 'I') value-=2;
                }
                case 'L' -> {
                    value += 50;
                    if(i> 0&& s.charAt(i-1) == 'X') value-=20;
                }
                case 'C' -> {
                    value += 100;
                    if(i> 0&& s.charAt(i-1) == 'X') value-=20;
                }
                case 'D' -> {
                    value += 500;
                    if(i> 0 && s.charAt(i-1) == 'C') value-=200;
                }
                case 'M' -> {
                    value += 1000;
                    if(i> 0&& s.charAt(i-1) == 'C') value-=200;
                }
            }
        }
        return value;
    }

}

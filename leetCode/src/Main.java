public class Main {
    public static void main(String[] args) {
//Palindrome palin = new Palindrome();
//        System.out.println(palin.isPalindrome("race a car"));
//        System.out.println(palin.isPalindrome("A man, a plan, a canal: Panama"));
//
//        TwoSum twoSum = new TwoSum();
//        int[] num = {1,5,6,8,3};
//        System.out.println(twoSum.isTwoSum(num,9));
//
//        RomanToSum romanToSum = new RomanToSum();
//
//        System.out.println(
//                romanToSum.IsRomanToSum("MCXC")
//        );
        String[] uncommon  = {"float", "double","small"};
        String[] common  = {"fl05", "fl","flunk"};
        String[] rare = {"","a"};
        String[] same = {"flowera","flowera","flowera","flowera","flowera"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.commonPrefix(rare));
        System.out.println(longestCommonPrefix.commonPrefix(uncommon));
        System.out.println(longestCommonPrefix.commonPrefix(common));
        System.out.println(longestCommonPrefix.commonPrefix(same));
    }
    }

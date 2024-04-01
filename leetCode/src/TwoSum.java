/**
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
//Runtime 44 ms beats 40.75% of users with java
//Memory 45.02 MB beats 17.65% of users with java
public class TwoSum {
    public int[] twoSum(int[] num, int target) {

        for (int i = 0; i < num.length; i++) {

            for (int j = i + 1; j < num.length; j++) {
                if (num[i] + num[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];

    }
}

import java.util.Stack;

/**
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
//Runtime 1 ms beats 98.62% of users with java
//Memory 41.31 MB beats 62.48% of user with java
public class ValidParenthesis {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char character : s.toCharArray()){

            if(character == '(' || character == '{' || character =='[') stack.push(character);
            else if(stack.isEmpty()) return false;
            else {
                char top = stack.pop();
                if ((character == ')' && top != '(') || (character == '}'&& top != '{')
                        || (character == ']'&& top != '[')) return false;
            }
        }
        return stack.isEmpty();
    }
}


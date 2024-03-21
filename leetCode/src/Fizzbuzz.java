import java.util.ArrayList;
import java.util.List;

/**
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */

public class Fizzbuzz {
    public List<String> fizzBuzz(int n){
        List<String> array = new ArrayList<>();
        for(int i = 1 ; i <=n ; i++){
            if(i % 3 == 0 && i % 5 ==0){
                array.add("FizzBuzz");
            }
            else if(i % 3 == 0){
                array.add("Fizz");
            } else if (i% 5 == 0) {
                array.add("Buzz");
            }else{
                String j = Integer.toString(i);
                array.add(j);
            }
        }
        return array;
    }
}

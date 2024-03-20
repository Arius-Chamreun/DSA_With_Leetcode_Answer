public class RomanToSum {
    public int IsRomanToSum(String string){
        int value = 0;
        for(int i = 0 ; i < string.length()  ; i++){
            switch (string.charAt(i)){
                case 'I' -> value += 1;
                case 'V' -> value += 5;
                case 'X' -> value += 10;
                case 'L' -> value += 50;
                case 'C' -> value += 100;
                case 'D' -> value += 500;
                case 'M' -> value += 1000;
            }
    for(int j = i + 1; j <= string.length(); j++){
    switch (string.substring(i,j)){
        case "IV" -> value -= 2;
        case "IX" -> value -= 2;
        case "XL" -> value -= 20;
        case "XC" -> value -= 20;
        case "CD" -> value -= 200;
        case "CM" -> value -= 200;
    }

        }
    }
        return value;
}
}

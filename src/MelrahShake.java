import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/22/2016.
 */
public class MelrahShake {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String str = console.nextLine();
        String pattern = console.nextLine();

        while (true){
            int patternLength = pattern.length();
            int strLength = str.length();

            if (strLength < 2 * patternLength || patternLength <= 0){
                System.out.println("No shake.");
                System.out.println(str);
                return;
            }

            int firstIndex = str.indexOf(pattern);
            int lastindex = str.lastIndexOf(pattern);

            StringBuilder sb = new StringBuilder();

            if (firstIndex != -1 && lastindex != -1 && lastindex >= firstIndex + patternLength){

                for (int i = 0; i < str.length(); i++) {
                    if ((i < firstIndex || i >= firstIndex + patternLength) &&
                            (i < lastindex || i >= lastindex + patternLength)){
                        sb.append(str.charAt(i));
                    }
                }
                str = sb.toString();

                int index = pattern.length() / 2 ;
                StringBuilder sb1 = new StringBuilder();

                for (int i = 0; i < pattern.length(); i++) {
                    char ch = pattern.charAt(i);
                    if (i != index){
                        sb1.append(ch);
                    }
                }
                pattern = sb1.toString();

                System.out.println("Shaked it.");
            }else {
                System.out.println("No shake.");
                System.out.println(str);
                return;
            }
        }
    }
}
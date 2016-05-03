import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/22/2016.
 */
public class MelrahShake1 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String str = console.nextLine();
        String pattern = console.nextLine();

        boolean shackeFound = false;

        while (true){
            int patternLength = pattern.length();
            int strLength = str.length();

            if (strLength < 2 * patternLength || patternLength <= 0 || strLength <= 0){
                System.out.println("No shake.");
                System.out.println(str);
                return;
            }

            StringBuilder text = new StringBuilder();

            boolean firstFound = false;
            boolean lastFound = false;
            int lastIndex = 0;
            int index = 0;
            int firstIndex = 0;

            for (int i = 0; i <= strLength - patternLength; i++) {
                String subStr = str.substring(i, i + patternLength);

                if (subStr.equals(pattern)){
                    if (firstFound == false){
                        firstFound = true;
                        firstIndex = i;
                    }else{
                        lastFound = true;
                        index = i;

                        if (index > lastIndex){
                            lastIndex = index;
                        }
                    }
                }
            }

            if (firstFound && lastFound){
                shackeFound = true;
                System.out.println("Shaked it.");
                text.append(str.substring(0, firstIndex));
                text.append(str.substring(firstIndex + patternLength, lastIndex));
                text.append(str.substring(lastIndex + patternLength));

                str = text.toString();

                int indexToRemove = patternLength / 2;
                StringBuilder text2 = new StringBuilder();

                for (int i = 0; i < patternLength; i++) {

                    if (i != indexToRemove)
                   text2.append(pattern.charAt(i));
                }

                pattern = text2.toString();
            } else {
                System.out.println("No shake.");
                System.out.println(str);
                return;
            }
        }
    }
}

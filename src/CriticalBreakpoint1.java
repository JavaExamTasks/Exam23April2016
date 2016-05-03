import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by pc on 4/28/2016.
 */
public class CriticalBreakpoint1 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        BigInteger previousCriticalRatio = new BigInteger("0");

        ArrayList<ArrayList<String>> inputLines = new ArrayList<>();
        boolean criticalRatioFound = false;

        String input = console.nextLine();

        while (!input.equals("Break it.")){
            String[] inputArgs = input.split("\\s+");
            int x1 = Integer.parseInt(inputArgs[0]);
            int y1 = Integer.parseInt(inputArgs[1]);
            int x2 = Integer.parseInt(inputArgs[2]);
            int y2 = Integer.parseInt(inputArgs[3]);

            inputLines.add(new ArrayList<String>(Arrays.asList(inputArgs)));

            BigInteger criticalRatio = new BigInteger("0");
            criticalRatio = criticalRatio.add(BigInteger.valueOf(x2 + y2))
                    .subtract(BigInteger.valueOf(x1 + y1))
                    .abs();

            if (criticalRatioFound){
                if (!criticalRatio.equals(previousCriticalRatio) &&
                        !criticalRatio.equals(BigInteger.valueOf(0))){
                    System.out.println("Critical breakpoint does not exist.");
                    return;
                }
            }else {
                if (!criticalRatio.equals(BigInteger.valueOf(0))){
                    previousCriticalRatio = criticalRatio;
                    criticalRatioFound = true;
                }
            }

            input = console.nextLine();
        }

        for (ArrayList<String> inputLine : inputLines) {
            System.out.printf("Line: %s%n", inputLine);
        }

        int countOfLines = inputLines.size();

        BigInteger criticalBreakPoint = new BigInteger(previousCriticalRatio.toString());
        criticalBreakPoint = criticalBreakPoint.pow(countOfLines);
        criticalBreakPoint = criticalBreakPoint.remainder(new BigInteger(Integer.valueOf(countOfLines).toString()));

        System.out.printf("Critical Breakpoint: %s", criticalBreakPoint);

    }
}

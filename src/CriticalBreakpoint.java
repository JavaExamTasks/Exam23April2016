import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by pc on 4/22/2016.
 */
public class CriticalBreakpoint {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        BigInteger oldCriticalRatio = new BigInteger("0");
        int countOfLines = 0;
        boolean firstRasioFound = false;
        ArrayList<Integer[]> matrix = new ArrayList<>();

        String input = console.nextLine();
        while (!input.equals("Break it.")){
            String[] inputArgs = input.split("\\s+");
            int x1 = Integer.parseInt(inputArgs[0]);
            int y1 = Integer.parseInt(inputArgs[1]);
            int x2 = Integer.parseInt(inputArgs[2]);
            int y2 = Integer.parseInt(inputArgs[3]);
            matrix.add(new Integer[]{x1, y1, x2, y2});

            BigInteger criticalRatio = new BigInteger("0");
            criticalRatio = criticalRatio.add(BigInteger.valueOf(x2 + y2))
                    .subtract(BigInteger.valueOf(x1 + y1))
                    .abs();

            if (firstRasioFound){
                if (!oldCriticalRatio.equals(criticalRatio) && !criticalRatio.equals(BigInteger.valueOf(0))){
                    System.out.println("Critical breakpoint does not exist.");
                    return;
                }
            } else {
                if (!criticalRatio.equals(BigInteger.valueOf(0))){
                    oldCriticalRatio = criticalRatio;
                    firstRasioFound = true;
                }
            }

            countOfLines++;
            input = console.nextLine();
        }

        for (int r = 0; r < matrix.size(); r++) {
            System.out.print("Line: ");
            Integer []output = matrix.get(r);
            System.out.println(Arrays.asList(output));
        }

        BigInteger criticalBreakpoint = new BigInteger(oldCriticalRatio.toString());
        criticalBreakpoint = criticalBreakpoint.pow(countOfLines);
        criticalBreakpoint = criticalBreakpoint.remainder(
                new BigInteger(Integer.valueOf(countOfLines).toString()));

        System.out.printf("Critical Breakpoint: %s", criticalBreakpoint);
    }
}

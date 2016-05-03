import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by pc on 4/22/2016.
 */
public class Crossfire {
    static ArrayList<ArrayList<Integer>> matrix;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String[] inputArgs = console.nextLine().split("\\s+");
        int rows = Integer.parseInt(inputArgs[0]);
        int cols = Integer.parseInt(inputArgs[1]);

        int fillerCount = 1;
        matrix = new ArrayList<ArrayList<Integer>>();

        for (int r = 0; r < rows; r++) {
            matrix.add(new ArrayList<Integer>());
            for (int c = 0; c < cols; c++) {
                matrix.get(r).add(fillerCount);
                fillerCount++;
            }
        }

        String input = console.nextLine();

        while (!input.equals("Nuke it from orbit")){
            String[]line = input.split("\\s+");
            long row = Long.parseLong(line[0]);
            long col = Long.parseLong(line[1]);
            long radius = Long.parseLong(line[2]);

            int matrixSize = matrix.size();

            long minRow = row - radius;
            long maxRow = row + radius;

            minRow = Math.max(0, minRow);
            maxRow= Math.min(maxRow, matrixSize);

            for (int i = (int)minRow; i <= (int)maxRow; i++) {
                if (i >= 0 && i < matrix.size() && col >= 0 && col < matrix.get(i).size()){
                    matrix.get(i).set((int)col, -1);
                }
            }

                long minCol = col - radius;
                long maxCol = col + radius;
                minCol = Math.max(0, minCol);
                maxCol = Math.min(cols, maxCol);

                for (int i = (int)minCol; i <= (int)maxCol; i++) {
                    if (row >= 0 && row < matrix.size() && i >= 0 && i < matrix.get((int)row).size()){
                        matrix.get((int)row).set(i, -1);
                    }
                }


            for (int r = 0; r < matrix.size(); r++) {
                matrix.get(r).removeIf(i -> i == -1);
            }

            for (int r = 0; r < matrix.size(); r++) {
                matrix.removeIf(i -> i.size() == 0);
            }

            input = console.nextLine();
        }

        printMatrix();

    }

    private static void printMatrix() {
        for (int r = 0; r < matrix.size(); r++) {
            for (int c = 0; c < matrix.get(r).size(); c++) {
                System.out.print(matrix.get(r).get(c));
                System.out.print(" ");
            }

            System.out.println();
        }
    }
}

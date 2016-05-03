import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/28/2016.
 */
public class GUnitTwoMaps2 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        TreeMap<String, TreeMap<String, TreeSet<String>>> classes = new TreeMap<>();
        HashMap<String, int[]> counts = new HashMap<>();

        while (!input.equals("It's testing time!")) {
            Pattern pattern = Pattern.compile("^([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+)$");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String className = matcher.group(1);
                String method = matcher.group(2);
                String test = matcher.group(3);

                if (!classes.containsKey(className)){
                    classes.put(className, new TreeMap<>());
                    counts.put(className, new int[2]);
                    counts.get(className)[0] = 1;
                    counts.get(className)[1] = 1;
                }

                if (!classes.get(className).containsKey(method)){
                    classes.get(className).put(method, new TreeSet<>());
                    counts.get(className)[0] ++;
                }

                if (!classes.get(className).get(method).contains(test)){
                    classes.get(className).get(method).add(test);
                    counts.get(className)[1] ++;
                }
            }

            input = console.nextLine();
        }

        classes.entrySet().stream()
                .sorted((p1, p2) -> Integer.compare(counts.get(p1.getKey())[0], counts.get(p2.getKey())[0]))
                .sorted((p1, p2) -> Integer.compare(counts.get(p2.getKey())[1], counts.get(p1.getKey())[1]))
                .forEach(p -> {
                            System.out.println(p.getKey() + ":");
                            TreeMap<String, TreeSet<String>> methods = p.getValue();
                            methods.entrySet().stream()
                                    .sorted((p1, p2) -> Integer.compare(p2.getValue().size(), p1.getValue().size()))
                                    .forEach(e -> {
                                        System.out.println("##" + e.getKey());
                                        e.getValue().stream()
                                                .sorted((p1, p2) -> Integer.compare(p1.length(), p2.length()))
                                                .forEach(t -> System.out.println("####" + t));
                                    });
                        }
                );
    }
}

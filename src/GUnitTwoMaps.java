import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/28/2016.
 */
public class GUnitTwoMaps {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        TreeMap<String, TreeMap<String, TreeSet<String>>> classes = new TreeMap<>();
        HashMap<String, Integer> testCount = new HashMap<>();

        while (!input.equals("It's testing time!")) {
            Pattern pattern = Pattern.compile("^([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+)$");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String className = matcher.group(1);
                String method = matcher.group(2);
                String test = matcher.group(3);

                if (!classes.containsKey(className)){
                    classes.put(className, new TreeMap<>());
                    testCount.put(className, 1);
                }

                if (!classes.get(className).containsKey(method)){
                    classes.get(className).put(method, new TreeSet<>());
                }

                if (!classes.get(className).get(method).contains(test)) {
                    classes.get(className).get(method).add(test);
                    testCount.put(className, testCount.get(className) + 1);
                }

            }

            input = console.nextLine();
        }

        classes.entrySet().stream()
                .sorted((p1, p2) -> Integer.compare(p1.getValue().size(), p2.getValue().size()))
                .sorted((t1, t2) -> Integer.compare(testCount.get(t2.getKey()), testCount.get(t1.getKey())))
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

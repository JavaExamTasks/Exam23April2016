import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/27/2016.
 */
public class GUnitOneTreeMap {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        TreeMap<String, TreeMap<String, TreeSet<String>>> classes = new TreeMap<>();

        while (!input.equals("It's testing time!")) {
            Pattern pattern = Pattern.compile("^([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+)$");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String className = matcher.group(1);
                String method = matcher.group(2);
                String test = matcher.group(3);

                if (!classes.containsKey(className)){
                    classes.put(className, new TreeMap<>());
                }

                if (!classes.get(className).containsKey(method)){
                    classes.get(className).put(method, new TreeSet<>());
                }

                classes.get(className).get(method).add(test);
            }

            input = console.nextLine();
        }

        classes.entrySet().stream()
                .sorted((p1, p2) -> Integer.compare(p1.getValue().size(), p2.getValue().size()))
                .sorted((p1, p2) -> {
                    int tests1 = 0;
                    for (Map.Entry<String,TreeSet<String>> stringTreeSetEntry : p1.getValue().entrySet()) {
                        tests1 += stringTreeSetEntry.getValue().size();
                    }

                    int tests2 = 0;
                    for (Map.Entry<String,TreeSet<String>> stringTreeSetEntry2 : p2.getValue().entrySet()) {
                        tests2 += stringTreeSetEntry2.getValue().size();
                    }

                    return Integer.compare(tests2, tests1);
                        
                })
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

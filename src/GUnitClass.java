import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/22/2016.
 */
public class GUnitClass {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        TreeMap<String, Class> classes = new TreeMap<>();

        String input = console.nextLine();
        Pattern pattern = Pattern.compile("^([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+)$");

        while (!input.equals("It's testing time!")){
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()){
                String className = matcher.group(1).trim();
                String method = matcher.group(2).trim();
                String test = matcher.group(3).trim();

                if (!classes.containsKey(className)) {
                    classes.put(className, new Class());
                }

                if (classes.get(className).methods.get(method) == null) {
                    classes.get(className).methods.put(method, new TreeSet<String>());
                }

                classes.get(className).methods.get(method).add(test);

            }

            input = console.nextLine();
        }

        classes.entrySet().stream().sorted((p1, p2) ->
                Integer.compare(p1.getValue().getCountMethod(), p2.getValue().getCountMethod()))
                .sorted((e1, e2) ->
                Integer.compare(e2.getValue().getCountTest(), e1.getValue().getCountTest()))
                .forEach(pair -> {
                    System.out.print(pair.getKey());
                    System.out.println(":");

                    StringBuilder sb = new StringBuilder();
                    TreeMap<String, TreeSet<String>> sorted = pair.getValue().methods;
                    sorted.entrySet().stream().sorted((p1, p2) ->
                            Integer.compare(p2.getValue().size(), p1.getValue().size())).
                            forEach(entry -> {
                                System.out.printf("##%s%n", entry.getKey());
                                TreeSet<String> sortedTests = entry.getValue();
                                List<String> list = new ArrayList<String>(sortedTests);

                                list.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
                                for (String msg : list) {
                                    System.out.printf("####%s%n", msg);
                                }
                            });
                });
    }
    static class Class implements Comparable {
        public TreeMap<String, TreeSet<String>> methods;
        public int countMethod;
        public int countTest;
        public String name;

        public Class() {
            methods = new TreeMap<String, TreeSet<String>>();
            int countMethod = 0;
            int testMethod = 0;
        }

        public int getCountMethod(){
            return methods.size();
        }

        public int getCountTest(){

            int count = 0;
            for (Map.Entry<String, TreeSet<String>> pair : methods.entrySet()) {
                count += pair.getValue().size();
            }
            return count;
        }

        @Override
        public int compareTo(Object o) {
            Class other = (Class) o;
            return Integer.compare(this.countTest, other.countTest);
        }
    }
}


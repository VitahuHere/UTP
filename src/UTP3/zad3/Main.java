/**
 * @author Vu Cong Minh S25206
 */

package UTP3.zad3;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
//        Function<String, List<String>> flines = (String path) -> {
//            ArrayList<String> lines = new ArrayList<>();
//            try {
//                BufferedReader reader = new BufferedReader(new FileReader(path));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    lines.add(line);
//                }
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return lines;
//        };
        Thrower<String, List<String>> flines = (String path) -> {
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            return lines;
        };


        Function<List<String>, String> join = (List<String> lines) -> {
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line);
            }
            return sb.toString();
        };

//        Thrower<List<String>, String> join = (List<String> lines) -> {
//            StringBuilder sb = new StringBuilder();
//            for (String line : lines) {
//                sb.append(line);
//            }
//            return sb.toString();
//        };

        Function<String, List<Integer>> collectInts = (String s) -> {
            Pattern pattern = Pattern.compile("-?\\d+");
            Matcher matcher = pattern.matcher(s);

            ArrayList<Integer> integerList = new ArrayList<>();
            while (matcher.find()) {
                integerList.add(Integer.parseInt(matcher.group()));
            }

            return integerList;
        };
//        Thrower<String, List<Integer>> collectInts = (String s) -> {
//            Pattern pattern = Pattern.compile("-?\\d+");
//            Matcher matcher = pattern.matcher(s);
//
//            ArrayList<Integer> integerList = new ArrayList<>();
//            while (matcher.find()) {
//                integerList.add(Integer.parseInt(matcher.group()));
//            }
//
//            return integerList;
//        };

        Function<List<Integer>, Integer> sum = (List<Integer> numbers) -> {
            int s = 0;
            for (Integer number : numbers) {
                s += number;
            }
            return s;
        };
//        Thrower<List<Integer>, Integer> sum = (List<Integer> numbers) -> {
//            int s = 0;
//            for (Integer number : numbers) {
//                s += number;
//            }
//            return s;
//        };

        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);

        // Zadania badawcze:
        // Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
        // Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
        // Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
        // I wobec tego musimy pisać w definicji flines try { } catch { }
        // Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
        // zadziałała klauzula throws metody main
    }
}

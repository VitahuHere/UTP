/**
 * @author Vu Cong Minh S25206
 */

package UTP7.zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Magazyn magazyn = new Magazyn();
        service.execute(() -> {
            List<String> lines = null;
            try {
                lines = Files.readAllLines(Paths.get("./src/UTP7/Towary.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert lines != null;
            for (String line : lines) {
                String[] split = line.split(" ");
                Towar towar = new Towar(Integer.parseInt(split[0]), Double.parseDouble(split[1]));
                magazyn.add(towar);
                if(magazyn.getSize() % 200 == 0 && magazyn.getSize() != 0){
                    System.out.println("utworzono " + magazyn.getSize() + " obiektów");
                }
            }
            magazyn.setFinished();
        });
        service.execute(() -> {
            double weight = 0;
            int size = 0;
            do {
                if (size != magazyn.getSize()){
                    ArrayList<Towar> towary = magazyn.getTowary(size, magazyn.getSize());
                    for (Towar towar : towary) {
                        weight += towar.getWeight();
                        size++;
                        if (size % 100 == 0 && size != 0){
                            System.out.println("policzono wage " + size + " towarów");
                        }
                    }
                }
            } while(!magazyn.isFinished() || size != magazyn.getSize());
            System.out.println(weight);
        });
        service.shutdown();
    }
}

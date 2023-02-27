/**
 * @author Vu Cong Minh S25206
 */

package UTP7.zad1;

import java.util.ArrayList;

public class Magazyn {
    private final ArrayList<Towar> towary;
    private boolean finished = false;
    public Magazyn(){
        towary = new ArrayList<>();
    }

    public synchronized void add(Towar towar){
        towary.add(towar);
    }

    public synchronized int getSize(){
        return towary.size();
    }

    public synchronized void setFinished(){
        finished = true;
    }

    public synchronized boolean isFinished(){
        return finished;
    }

    public synchronized ArrayList<Towar> getTowary(int start, int stop) {
        return new ArrayList<>(towary.subList(start, stop));
    }
}

/**
 * @author Vu Cong Minh S25206
 */

package UTP7.zad1;

public class Towar {
    private final int id;
    private final double weight;

    public Towar(int id, double weight) {
        this.id = id;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }
}

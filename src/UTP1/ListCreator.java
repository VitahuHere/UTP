/**
 *
 *  @author Vu Cong Minh S25206
 *
 */

package UTP1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> extends ArrayList<T> {
    public ListCreator(List<T> src) {
        this.addAll(src);
    }

    public static <T> ListCreator<T> collectFrom(List<T> src) {
        return new ListCreator<>(src);
    }

    public ListCreator<T> when(Selector<T> sel) {
        List<T> newList = new ArrayList<>();
        for (T t : this) {
            if (sel.select(t)) {
                newList.add(t);
            }
        }
        this.removeAll(this);
        this.addAll(newList);
        return this;
    }

    public <U> ListCreator<U> mapEvery(Mapper<T, U> map) {
        List<U> newList = new ArrayList<>();
        for (T t : this) {
            newList.add(map.map(t));
        }
        return new ListCreator<>(newList);
    }
}

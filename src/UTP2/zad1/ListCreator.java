package UTP2.zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ListCreator<T> extends ArrayList<T> {
    public ListCreator(List<T> src) {
        this.addAll(src);
    }

    public static <T> ListCreator<T> collectFrom(List<T> src) {
        return new ListCreator<>(src);
    }

    public ListCreator<T> when(Function<T, Boolean> sel) {
        List<T> newList = new ArrayList<>();
        for (T t : this) {
            if (sel.apply(t)) {
                newList.add(t);
            }
        }
        this.removeAll(this);
        this.addAll(newList);
        return this;
    }

    public <U> ListCreator<U> mapEvery(Function<T, U> map) {
        List<U> newList = new ArrayList<>();
        for (T t : this) {
            newList.add(map.apply(t));
        }
        return new ListCreator<>(newList);
    }
}
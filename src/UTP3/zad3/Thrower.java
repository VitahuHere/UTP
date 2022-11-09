package UTP3.zad3;

import java.io.IOException;
import java.util.function.Function;

@FunctionalInterface
public interface Thrower<T, U> extends Function<T, U> {
    @Override
    default U apply(T t) {
        try {
            return applyThrows(t);
        } catch (IOException e) {
            return null;
        }
    }

    U applyThrows(T t) throws IOException;
}

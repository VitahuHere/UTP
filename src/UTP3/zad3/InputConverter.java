/**
 * @author Vu Cong Minh S25206
 */

package UTP3.zad3;

import java.util.function.Function;

public class InputConverter<T> {
    private T input;

    public InputConverter(T t) {
        this.input = t;
    }

    public <R> R convertBy(Function... converter) {
        Object input = this.input, result = null;
        for (Function f : converter) {
            result = f.apply(input);
            input = result;
        }
        return (R) result;
    }
}

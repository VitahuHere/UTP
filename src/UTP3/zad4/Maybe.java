package UTP3.zad4;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    private T value;

    public Maybe(T value) {
        this.value = value;
    }

    public static <T> Maybe<T> of(T value) {
        return new Maybe<>(value);
    }

    public void ifPresent(Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public <R> Maybe<R> map(Function<T, R> function) {
        if (value != null) {
            return new Maybe<>(function.apply(value));
        } else {
            return new Maybe<>(null);
        }
    }

    public T get() {
        if (value != null) {
            return value;
        } else {
            throw new NoSuchElementException("maybe is empty");
        }
    }

    public boolean isPresent() {
        return value != null;
    }

    public T orElse(T other) {
        if (value != null) {
            return value;
        } else {
            return other;
        }
    }

    public Maybe<T> filter(Predicate<T> pred) {
        if (value == null || pred.test(value)) {
            return this;
        } else {
            return new Maybe<>(null);
        }
    }

    @Override
    public String toString() {
        if (value != null) {
            return "Maybe has value " + value;
        } else {
            return "Maybe is empty";
        }
    }
}


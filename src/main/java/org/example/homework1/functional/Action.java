package main.java.org.example.homework1.functional;
@FunctionalInterface
public interface Action<T> {
    void perform(T element);
}

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Memoizer {

    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        Map<T, R> cache = new ConcurrentHashMap<>();
        return input -> cache.computeIfAbsent(input, function);
    }

    public static void main(String[] args) {

        // Fibonacci memoized (version correcte)
        Function<Integer, Long>[] fibRef = new Function[1];
        fibRef[0] = memoize(n -> {
            if (n <= 1) return (long) n;
            return fibRef[0].apply(n - 1) + fibRef[0].apply(n - 2);
        });
        Function<Integer, Long> fibonacciMemoized = fibRef[0];

        System.out.println("Version memoized:");
        long start = System.currentTimeMillis();
        System.out.println("fibonacci(40) = " + fibonacciMemoized.apply(40));
        System.out.println("Temps: " + (System.currentTimeMillis() - start) + "ms");

        System.out.println("\nDeuxième appel memoized:");
        start = System.currentTimeMillis();
        System.out.println("fibonacci(40) = " + fibonacciMemoized.apply(40));
        System.out.println("Temps: " + (System.currentTimeMillis() - start) + "ms");
    }
}

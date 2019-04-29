import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        // StringComparison stringComparison = new StringComparison();
        BenchmarkWithoutInterning withoutInterning = new BenchmarkWithoutInterning();
        BenchmarkWithInterning withInterning = new BenchmarkWithInterning();

        System.out.println("\nNow running Java String interning benchmark");

        withoutInterning.run();
        withInterning.run();

        System.out.println("\nEnd of benchmarking.");
    }

}

class StringComparison {
    String str1 = "Compare";
    String str2 = "Compare";
    String str3 = new String("Compare");
    final String str4 = str3.intern();

    public void compare() {
        // using == is faster than .equals because it doesn't do a byte-by-byte
        // comparison of string and with interning, it becomes even better
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str3 == str4);
        System.out.println(str1 == str3);
        System.out.println(str1 == str4);

        System.out.println(str1.equals(str2));
        System.out.println(str2.equals(str3));
        System.out.println(str3.equals(str4));
        System.out.println(str1.equals(str4));
        System.out.println(str1.equals(str3));
    }

}

class BenchmarkWithoutInterning {
    private static final int MAX = 40000000;

    public void run() {
        long t = System.currentTimeMillis();
        String[] arr = new String[MAX];
        System.out.println("\nMaking 40 million string computations without interning. \nPlease wait...");
        for (int i = 0; i < MAX; i++) {
            int index = ThreadLocalRandom.current().nextInt(5);
            arr[i] = new String(Database.names[index]);
        }
        System.out.println("TOOK: " + (System.currentTimeMillis() - t) + "ms");
        System.gc();
    }
}

class BenchmarkWithInterning {
    private static final int MAX = 40000000;

    public void run() {
        long t = System.currentTimeMillis();
        String[] arr = new String[MAX];
        System.out.println("\nNow making 40 million string computations with interning. \nPlease wait...");
        for (int i = 0; i < MAX; i++) {
            int index = ThreadLocalRandom.current().nextInt(5);
            arr[i] = new String(Database.names[index]).intern();
        }
        System.out.println("TOOK: " + (System.currentTimeMillis() - t) + "ms");
        System.gc();
    }
}
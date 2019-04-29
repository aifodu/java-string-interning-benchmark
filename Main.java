import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        BenchmarkWithoutInterning withoutInterning = new BenchmarkWithoutInterning();
        BenchmarkWithInterning withInterning = new BenchmarkWithInterning();

        System.out.println("\nNow running Java String interning benchmark");

        withoutInterning.run();
        withInterning.run();

        System.out.println("\nEnd of benchmarking.");
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTest {

    @Test
    void addToEndTest() {
        final int count = 2_000_000;

        System.out.println();
        System.out.println("Add to end: ArrayList with capacity");
        measure(10, () -> addToEnd(new ArrayList<>(count), count));

        System.out.println();
        System.out.println("Add to end: ArrayList without capacity");
        measure(10, () -> addToEnd(new ArrayList<>(), count));

        System.out.println();
        System.out.println("Add to end: LinkedList");
        measure(10, () -> addToEnd(new LinkedList<>(), count));

        System.out.println();
        System.out.println("Add to end: Vector without capacity");
        measure(10, () -> addToEnd(new Vector<>(), count));

        System.out.println();
        System.out.println("Add to end: Vector with capacity");
        measure(10, () -> addToEnd(new Vector<>(count), count));

        System.out.println();
        System.out.println("Add to end: ArrayDeque without capacity");
        measure(10, () -> addToEnd(new ArrayDeque<>(), count));

        System.out.println();
        System.out.println("Add to end: ArrayDeque with capacity");
        measure(10, () -> addToEnd(new ArrayDeque<>(count), count));

        System.out.println();
        System.out.println("Add to end: HashSet");
        measure(10, () -> addToEnd(new HashSet<>(count), count));

        System.out.println();
        System.out.println("Add to end: LinkedHashSet");
        measure(10, () -> addToEnd(new LinkedHashSet<>(count), count));

        System.out.println();
        System.out.println("Add to end: TreeSet");
        measure(10, () -> addToEnd(new TreeSet<>(), count));
    }

    @Test
    void addToStartTest() {
        final int count = 250_000;

        System.out.println();
        System.out.println("Add to start: ArrayList with capacity");
        measure(10, () -> addToStart(new ArrayList<>(count), count));

        System.out.println();
        System.out.println("Add to start: ArrayList without capacity");
        measure(10, () -> addToStart(new ArrayList<>(), count));

        System.out.println();
        System.out.println("Add to start: Vector with capacity");
        measure(10, () -> addToStart(new Vector<>(count), count));

        System.out.println();
        System.out.println("Add to start: Vector without capacity");
        measure(10, () -> addToStart(new Vector<>(), count));

        System.out.println();
        System.out.println("Add to start: LinkedList");
        measure(10, () -> addToStart(new LinkedList<>(), count));
    }

    @Test
    void createFromCollectionTest() {
        final int count = 2_000_000;
        final List<Integer> collection = new ArrayList<>(count);
        addToEnd(collection, count);
        Collections.shuffle(collection);

        System.out.println();
        System.out.println("Create from collection: ArrayList with capacity");
        measure(10, () -> new ArrayList<>(collection));

        System.out.println();
        System.out.println("Create from collection: LinkedList");
        measure(10, () -> new LinkedList<>(collection));

        System.out.println();
        System.out.println("Create from collection: Vector");
        measure(10, () -> new Vector<>(collection));

        System.out.println();
        System.out.println("Create from collection: ArrayDeque");
        measure(10, () -> new ArrayDeque<>(collection));

        System.out.println();
        System.out.println("Create from collection: HashSet");
        measure(10, () -> new HashSet<>(collection));

        System.out.println();
        System.out.println("Create from collection: LinkedHashSet");
        measure(10, () -> new LinkedHashSet<>(collection));

        System.out.println();
        System.out.println("Create from collection: TreeSet");
        measure(10, () -> new TreeSet<>(collection));
    }

    void measure(final int times, Runnable action) {
        final long[] res = new long[times];

        for (int i = 0; i < times; ++i) {
            final long start = System.currentTimeMillis();
            action.run();
            res[i] = System.currentTimeMillis() - start;
            System.out.println("Run #" + i + " took " + res[i] + " ms");
        }

        long min = Long.MAX_VALUE, max = 0, sum = 0;
        for (int i = 0; i < times; ++i) {
            final long d = res[i];
            if (min > d) min = d;
            if (max < d) max = d;
            sum += d;
        }

        System.out.println("MIN = " + min + "\tMAX = " + max + "\tAVG=" + ((float) sum / times));
        System.gc();
    }

    private void addToEnd(Collection<Integer> collection, final int n) {
        for (int i = 0; i < n; ++i) {
            collection.add(i);
        }
    }

    private void addToStart(List<Integer> list, final int n) {
        for (int i = 0; i < n; ++i) {
            list.add(0, i);
        }
    }
}

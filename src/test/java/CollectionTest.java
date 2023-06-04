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
    void addToCenterTest() {
        final int count = 100_000;

        System.out.println();
        System.out.println("Add to center: ArrayList with capacity");
        measure(10, () -> addToCenter(new ArrayList<>(count), count));

        System.out.println();
        System.out.println("Add to center: ArrayList without capacity");
        measure(10, () -> addToCenter(new ArrayList<>(), count));

        System.out.println();
        System.out.println("Add to center: Vector with capacity");
        measure(10, () -> addToCenter(new Vector<>(count), count));

        System.out.println();
        System.out.println("Add to center: Vector without capacity");
        measure(10, () -> addToCenter(new Vector<>(), count));

        System.out.println();
        System.out.println("Add to center: LinkedList");
        measure(10, () -> addToCenter(new LinkedList<>(), count));
    }

    @Test
    void getByIndexTest() {
        final int count = 100_000;
        final ArrayList<Integer> arrayList = new ArrayList<>(count);
        addToEnd(arrayList, count);

        System.out.println();
        System.out.println("Get by index: ArrayList");
        measure(10, () -> getByIndex(arrayList, count));

        final Vector<Integer> vector = new Vector<>(arrayList);
        System.out.println();
        System.out.println("Get by index: Vector");
        measure(10, () -> getByIndex(vector, count));

        final LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
        System.out.println();
        System.out.println("Get by index: LinkedList");
        measure(10, () -> getByIndex(linkedList, count));
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

    @Test
    void containsEachTest() {
        final int count = 100_000;
        final ArrayList<Integer> arrayList = new ArrayList<>(count);
        addToEnd(arrayList, count);
        Collections.shuffle(arrayList);

        System.out.println();
        System.out.println("Contains each: ArrayList");
        measure(5, () -> containsEach(arrayList, count));

        final LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
        System.out.println();
        System.out.println("Contains each: LinkedList");
        measure(5, () -> containsEach(linkedList, count));

        final Vector<Integer> vector = new Vector<>(arrayList);
        System.out.println();
        System.out.println("Contains each: Vector");
        measure(5, () -> containsEach(vector, count));

        final ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(arrayList);
        System.out.println();
        System.out.println("Contains each: ArrayDeque");
        measure(5, () -> containsEach(arrayDeque, count));

        final HashSet<Integer> hashSet = new HashSet<>(arrayList);
        System.out.println();
        System.out.println("Contains each: HashSet");
        measure(10, () -> containsEach(hashSet, count));

        final LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(arrayList);
        System.out.println();
        System.out.println("Contains each: LinkedHashSet");
        measure(10, () -> containsEach(linkedHashSet, count));

        final TreeSet<Integer> treeSet = new TreeSet<>(arrayList);
        System.out.println();
        System.out.println("Contains each: TreeSet");
        measure(10, () -> containsEach(treeSet, count));
    }

    @Test
    void containsAtEndTest() {
        final int size = 100_000;
        final int iterations = 50_000;
        final int n = 99_990;
        final ArrayList<Integer> arrayList = new ArrayList<>(size);
        addToEnd(arrayList, size);

        System.out.println();
        System.out.println("Contains at end: ArrayList");
        measure(5, () -> contains(arrayList, iterations, n));

        final LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
        System.out.println();
        System.out.println("Contains at end: LinkedList");
        measure(5, () -> contains(linkedList, iterations, n));

        final Vector<Integer> vector = new Vector<>(arrayList);
        System.out.println();
        System.out.println("Contains at end: Vector");
        measure(5, () -> contains(vector, iterations, n));

        final ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(arrayList);
        System.out.println();
        System.out.println("Contains at end: ArrayDeque");
        measure(5, () -> contains(arrayDeque, iterations, n));

        final HashSet<Integer> hashSet = new HashSet<>(arrayList);
        System.out.println();
        System.out.println("Contains at end: HashSet");
        measure(10, () -> contains(hashSet, iterations, n));

        final LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(arrayList);
        System.out.println();
        System.out.println("Contains at end: LinkedHashSet");
        measure(10, () -> contains(linkedHashSet, iterations, n));

        final TreeSet<Integer> treeSet = new TreeSet<>(arrayList);
        System.out.println();
        System.out.println("Contains at end: TreeSet");
        measure(10, () -> contains(treeSet, iterations, n));
    }

    @Test
    void containsAtStartTest() {
        final int size = 100_000;
        final int iterations = 300_000;
        final int n = 10;
        final ArrayList<Integer> arrayList = new ArrayList<>(size);
        addToEnd(arrayList, size);

        System.out.println();
        System.out.println("Contains at start: ArrayList");
        measure(10, () -> contains(arrayList, iterations, n));

        final LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
        System.out.println();
        System.out.println("Contains at start: LinkedList");
        measure(10, () -> contains(linkedList, iterations, n));

        final Vector<Integer> vector = new Vector<>(arrayList);
        System.out.println();
        System.out.println("Contains at start: Vector");
        measure(10, () -> contains(vector, iterations, n));

        final ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(arrayList);
        System.out.println();
        System.out.println("Contains at start: ArrayDeque");
        measure(10, () -> contains(arrayDeque, iterations, n));

        final HashSet<Integer> hashSet = new HashSet<>(arrayList);
        System.out.println();
        System.out.println("Contains at start: HashSet");
        measure(10, () -> contains(hashSet, iterations, n));

        final LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(arrayList);
        System.out.println();
        System.out.println("Contains at start: LinkedHashSet");
        measure(10, () -> contains(linkedHashSet, iterations, n));

        final TreeSet<Integer> treeSet = new TreeSet<>(arrayList);
        System.out.println();
        System.out.println("Contains at start: TreeSet");
        measure(10, () -> contains(treeSet, iterations, n));
    }

    @Test
    void containsNotPresentTest() {
        final int size = 100_000;
        final int iterations = 50_000;
        final int n = -1;
        final ArrayList<Integer> arrayList = new ArrayList<>(size);
        addToEnd(arrayList, size);

        System.out.println();
        System.out.println("contains not present: ArrayList");
        measure(5, () -> contains(arrayList, iterations, n));

        final LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
        System.out.println();
        System.out.println("contains not present: LinkedList");
        measure(5, () -> contains(linkedList, iterations, n));

        final Vector<Integer> vector = new Vector<>(arrayList);
        System.out.println();
        System.out.println("contains not present: Vector");
        measure(5, () -> contains(vector, iterations, n));

        final ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(arrayList);
        System.out.println();
        System.out.println("contains not present: ArrayDeque");
        measure(5, () -> contains(arrayDeque, iterations, n));

        final HashSet<Integer> hashSet = new HashSet<>(arrayList);
        System.out.println();
        System.out.println("contains not present: HashSet");
        measure(10, () -> contains(hashSet, iterations, n));

        final LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(arrayList);
        System.out.println();
        System.out.println("contains not present: LinkedHashSet");
        measure(10, () -> contains(linkedHashSet, iterations, n));

        final TreeSet<Integer> treeSet = new TreeSet<>(arrayList);
        System.out.println();
        System.out.println("contains not present: TreeSet");
        measure(10, () -> contains(treeSet, iterations, n));
    }

    void measure(final int times, final Runnable action) {
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

    private void addToEnd(final Collection<Integer> collection, final int n) {
        for (int i = 0; i < n; ++i) {
            collection.add(i);
        }
    }

    private void addToStart(final List<Integer> list, final int n) {
        for (int i = 0; i < n; ++i) {
            list.add(0, i);
        }
    }
    
    private void addToCenter(final List<Integer> list, final int n) {
        for (int i = 0; i < n; ++i) {
            list.add(list.size() / 2, i);
        }
    }

    private void getByIndex(final List<Integer> list, final int n) {
        for (int i = 0; i < n; ++i) {
            list.get(i);
        }
    }

    private void containsEach(final Collection<Integer> collection, final int n) {
        for (int i = 0; i < n; ++i) {
            collection.contains(i);
        }
    }

    private void contains(final Collection<Integer> collection, final int iterations, final int n) {
        for (int i = 0; i < iterations; ++i)
            collection.contains(n);
    }
}

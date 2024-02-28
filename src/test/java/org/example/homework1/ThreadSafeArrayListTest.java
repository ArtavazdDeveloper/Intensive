package test.java.org.example.homework1;

import main.java.org.example.homework1.ThreadSafeArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadSafeArrayListTest {

    private ThreadSafeArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ThreadSafeArrayList<>();
    }

    @Test
    void addAndGetElement() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void addElementAtIndex() {
        list.add(0, 1);
        list.add(1, 2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void removeElementAtIndex() {
        list.add(1);
        list.add(2);
        list.remove(0);
        assertEquals(2, list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void clearList() {
        list.add(1);
        list.add(2);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void containsElement() {
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test
    void checkEmptyList() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void checkSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    void checkIndexOfElement() {
        list.add(1);
        list.add(2);
        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(3));
    }

    @Test
    void checkLastIndexOfElement() {
        list.add(1);
        list.add(2);
        list.add(1);
        assertEquals(2, list.lastIndexOf(1));
        assertEquals(1, list.lastIndexOf(2));
        assertEquals(-1, list.lastIndexOf(3));
    }

    @Test
    void checkTrimToSize() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        list.trimToSize();
        assertEquals(20, list.size());
        assertEquals(20, list.toArray().length);
    }

    @Test
    void checkToArray() {
        list.add(1);
        list.add(2);
        Object[] array = list.toArray();
        assertEquals(2, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    @Test
    void checkToList() {
        list.add(1);
        list.add(2);
        ThreadSafeArrayList<Integer> newList = list.toList();
        assertEquals(2, newList.size());
        assertEquals(1, newList.get(0));
        assertEquals(2, newList.get(1));
    }

    @Test
    void checkForEach() {
        list.add(1);
        list.add(2);
        StringBuilder sb = new StringBuilder();
        list.forEach(e -> sb.append(e).append(","));
        assertEquals("1,2,", sb.toString());
    }
}

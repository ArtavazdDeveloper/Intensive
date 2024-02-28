package org.example.homework1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests for the ThreadSafeArrayList class.
 */
public class ThreadSafeArrayListTest {

    private ThreadSafeArrayList<Integer> list;
    /**
     * Initializes a new ThreadSafeArrayList before each test.
     */
    @BeforeEach
    void setUp() {
        list = new ThreadSafeArrayList<>();
    }

    /**
     * Tests the add and get methods.
     */
    @Test
    void addAndGetElement() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    /**
     * Tests the add method with index.
     */
    @Test
    void addElementAtIndex() {
        list.add(0, 1);
        list.add(1, 2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    /**
     * Tests the remove method.
     */
    @Test
    void removeElementAtIndex() {
        list.add(1);
        list.add(2);
        list.remove(0);
        assertEquals(2, list.get(0));
        assertEquals(1, list.size());
    }

    /**
     * Tests the clear method.
     */
    @Test
    void clearList() {
        list.add(1);
        list.add(2);
        list.clear();
        assertTrue(list.isEmpty());
    }

    /**
     * Tests the contains method.
     */
    @Test
    void containsElement() {
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    /**
     * Tests the isEmpty method.
     */
    @Test
    void checkEmptyList() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    /**
     * Tests the size method.
     */
    @Test
    void checkSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.remove(0);
        assertEquals(0, list.size());
    }

    /**
     * Tests the indexOf method.
     */
    @Test
    void checkIndexOfElement() {
        list.add(1);
        list.add(2);
        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(3));
    }

    /**
     * Tests the lastIndexOf method.
     */
    @Test
    void checkLastIndexOfElement() {
        list.add(1);
        list.add(2);
        list.add(1);
        assertEquals(2, list.lastIndexOf(1));
        assertEquals(1, list.lastIndexOf(2));
        assertEquals(-1, list.lastIndexOf(3));
    }

    /**
     * Tests the trimToSize method.
     */
    @Test
    void checkTrimToSize() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        list.trimToSize();
        assertEquals(20, list.size());
        assertEquals(20, list.toArray().length);
    }

    /**
     * Tests the toArray method.
     */
    @Test
    void checkToArray() {
        list.add(1);
        list.add(2);
        Object[] array = list.toArray();
        assertEquals(2, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    /**
     * Tests the toList method.
     */
    @Test
    void checkToList() {
        list.add(1);
        list.add(2);
        ThreadSafeArrayList<Integer> newList = list.toList();
        assertEquals(2, newList.size());
        assertEquals(1, newList.get(0));
        assertEquals(2, newList.get(1));
    }

    /**
     * Tests the forEach method.
     */
    @Test
    void checkForEach() {
        list.add(1);
        list.add(2);
        StringBuilder sb = new StringBuilder();
        list.forEach(e -> sb.append(e).append(","));
        assertEquals("1,2,", sb.toString());
    }
}

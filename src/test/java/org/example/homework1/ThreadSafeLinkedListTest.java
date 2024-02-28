package org.example.homework1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Class containing tests for the ThreadSafeLinkedList class.
 */
public class ThreadSafeLinkedListTest {

    /**
     * Tests the add and get methods.
     */
    @Test
    public void testAddAndGet() {
        ThreadSafeLinkedList<Integer> list = new ThreadSafeLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    /**
     * Tests the add method with index.
     */
    @Test
    public void testAddAtIndex() {
        ThreadSafeLinkedList<String> list = new ThreadSafeLinkedList<>();
        list.add("a");
        list.add("c");
        list.add(1, "b");
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    /**
     * Tests the remove method.
     */
    @Test
    public void testRemove() {
        ThreadSafeLinkedList<String> list = new ThreadSafeLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals(3, list.size());
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals("a", list.get(0));
        assertEquals("c", list.get(1));
    }

    /**
     * Tests the clear method.
     */
    @Test
    public void testClear() {
        ThreadSafeLinkedList<Integer> list = new ThreadSafeLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        list.clear();
        assertTrue(list.isEmpty());
    }

    /**
     * Tests the contains method.
     */
    @Test
    public void testContains() {
        ThreadSafeLinkedList<String> list = new ThreadSafeLinkedList<>();
        list.add("apple");
        list.add("banana");
        list.add("orange");
        assertTrue(list.contains("banana"));
        assertFalse(list.contains("grape"));
    }

    @Test
    public void testFirstAndLast() {
        ThreadSafeLinkedList<Integer> list = new ThreadSafeLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, list.first());
        assertEquals(3, list.last());
    }

    /**
     * Tests the toArray method.
     */
    @Test
    public void testToArray() {
        ThreadSafeLinkedList<Integer> list = new ThreadSafeLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Object[] array = list.toArray();
        assertEquals(3, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
    }
}

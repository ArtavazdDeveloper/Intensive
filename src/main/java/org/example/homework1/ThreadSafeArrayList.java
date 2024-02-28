package main.java.org.example.homework1;

import main.java.org.example.homework1.functional.Action;


/**
 * Array-based thread-safe list.
 */
public class ThreadSafeArrayList<T> {
    private Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Creates a new empty thread-safe list based on an array with the default capacity.
     */
    public ThreadSafeArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Increases the capacity of the list, if necessary, so that it can contain the specified number of elements.
     * @param minCapacity the minimum required capacity of the list
     */
    private synchronized void ensureCapacity(int minCapacity) {
        int oldCapacity = array.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = Math.max(oldCapacity * 2, minCapacity);
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    /**
     * Adds an element to the end of the list.
     * @param element element to add
     */
    public synchronized void add(T element) {
        ensureCapacity(size + 1);
        array[size++] = element;
    }

    /**
     * Adds an element at the specified index.
     * @param index index at which to add the element
     * @param element element to add
     * @throws IndexOutOfBoundsException if the index goes beyond the bounds of the list
     */
    public synchronized void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }

    /**
     * Gets the element at the specified index.
     * @param index element index
     * @return the list element at the specified index
     * @throws IndexOutOfBoundsException if the index goes beyond the bounds of the list
     */
    public synchronized T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }

    /**
     * Removes the element at the specified index.
     * @param index index of the element to remove
     * @throws IndexOutOfBoundsException if the index goes beyond the bounds of the list
     */
    public synchronized void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
    }

    /**
     * Clears a list by removing all its elements.
     */
    public synchronized void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Sorts the list in the natural order of the elements.
     */
    public synchronized void sort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                Comparable<T> current = (Comparable<T>) array[j];
                Comparable<T> next = (Comparable<T>) array[j + 1];
                if (current.compareTo((T) next) > 0) {
                    T temp = (T) array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */
    public synchronized int size() {
        return size;
    }

    /**
     * Checks whether the list contains the specified element.
     * @param element element to check
     * @return true if the list contains the specified element, false otherwise
     */
    public synchronized boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the first occurrence of the specified element from the list, if present.
     * @param element element to remove
     * @return true if the element was removed, false otherwise
     */
    public synchronized boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list, or -1 if the element is not found.
     * @param element element to search
     * @return the index of the first occurrence of the element in the list, or -1 if the element is not found
     */
    public synchronized int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in the list, or -1 if the element is not found.
     * @param element element to search
     * @return the index of the last occurrence of the element in the list, or -1 if the element was not found
     */
    public synchronized int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if the list is empty, false otherwise.
     * @return true if the list is empty, false otherwise
     */
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    /**
     * Trims the capacity of this list to be the list's current size.
     */
    public synchronized void trimToSize() {
        if (size < array.length) {
            Object[] newArray = new Object[size];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    /**
     * Converts this list to an array.
     * @return an array containing all of the elements in this list in proper sequence
     */
    public synchronized Object[] toArray() {
        Object[] newArray = new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    /**
     * Converts this list to a regular ArrayList.
     * @return a new ArrayList containing all of the elements in this list in proper sequence
     */
    public synchronized ThreadSafeArrayList<T> toList() {
        ThreadSafeArrayList<T> list = new ThreadSafeArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((T) array[i]);
        }
        return list;
    }

    /**
     * Performs the given action for each element of the list until all elements have been processed or the action throws an exception.
     * @param action the action to be performed for each element
     */
    public synchronized void forEach(Action<T> action) {
        for (int i = 0; i < size; i++) {
            action.perform((T) array[i]);
        }
    }

    /**
     * Returns a string representation of this list.
     * @return a string representation of this list
     */
    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}

package org.example.homework1;

/**
 * Thread-safe list based on linked lists.
 */
public class ThreadSafeLinkedList<T> {
    private Node<T> head;
    private int size;

    /**
     * Creates a new empty thread-safe list based on linked lists.
     */
    public ThreadSafeLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Adds an element to the end of the list.
     * @param element element to add
     */
    public synchronized void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
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
        if (index == 0) {
            Node<T> newNode = new Node<>(element);
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> newNode = new Node<>(element);
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                if (current == null) {
                    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
                }
                current = current.next;
            }
            if (current == null) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            newNode.next = current.next;
            current.next = newNode;
        }
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
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            current = current.next;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return current.data;
    }

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */
    public synchronized int size() {
        return size;
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
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    /**
     * Clears the list by removing all its elements.
     */
    public synchronized void clear() {
        head = null;
        size = 0;
    }

    /**
     * Checks whether the list contains the specified element.
     * @param element element to check
     * @return true if the list contains the specified element, false otherwise
     */
    public synchronized boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks whether the list is empty.
     * @return true if the list is empty, false otherwise
     */
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the first element of the list.
     * @return the first element of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    public synchronized T first() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return head.data;
    }

    /**
     * Gets the last element of the list.
     * @return the last element of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    public synchronized T last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Returns a new array containing all the elements in this list in proper sequence.
     * @return an array containing all the elements in this list in proper sequence
     */
    public synchronized Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}

package homework1;

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

    private static class Node<T> {
        private T data;
        private Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}

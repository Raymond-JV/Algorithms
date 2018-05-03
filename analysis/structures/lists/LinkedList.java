package structures.lists;

import java.util.NoSuchElementException;

public class LinkedList<T> {

    private Node<T> head = null;

    public LinkedList() {
    }

    public void addFirst(T element) {
        Node<T> newHead = new Node<>(element);
        newHead.next = head;
        head = newHead;
    }

    public void addLast(T element) {
        if (head == null)
            head = new Node<>(element);
        else
            append(element);
    }

    private void append(T element) {
        Node<T> cur = head;
        while (cur.next != null)
            cur = cur.next;
        cur.next = new Node<>(element);
    }

    private void check_empty(Node<T> element) {
        if (head == null)
            throw new NoSuchElementException("List is empty!");
    }

    public T getFirst() {
        check_empty(head);
        return head.data;
    }

    public T getLast() {
        check_empty(head);
        Node<T> tmp = head;
        while (tmp.next != null)
            tmp = tmp.next;
        return tmp.data;
    }

    public T get(int index) {
        verify_index(head);

        Node<T> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
            verify_index(tmp);
        }
        return tmp.data;
    }

    private T verify_index(Node<T> index) {
        if (index == null)
            throw new IndexOutOfBoundsException("Index cannot exceed size of list minus 1!");
        return index.data;
    }

    private class Node<V> {
        V data;
        Node<V> next;

        private Node(V data) {
            this.data = data;
        }
    }
}



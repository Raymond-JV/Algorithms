import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    void add(T data) {
        if (root == null)
            root = new Node<>(data);

        Node<T> current = root;

        while (true) {
            int comparison = data.compareTo(current.data);
            if (comparison == 0)
                return;

            if (comparison < 0) {
                if (current.left == null) {
                    current.left = new Node<>(data);
                    return;
                } else
                    current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node<>(data);
                    return;
                } else
                    current = current.right;
            }
        }
    }

    void removeIter(T data) {

        Node<T> current = root;
        Node<T> prev = root;
        while (data.compareTo(current.data) != 0) {
            prev = current;
            int comparison = data.compareTo(current.data);
            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                throw new NoSuchElementException(String.format("Error: the value %s does not exist.", data.toString()));
            }
        }

        //Base Case
        if (current == root && current.left == null && current.right == null) {
            root = null;
        }

        //Case 1: No Children
        else if (current.left == null && current.right == null) {
            if (prev.left == current) {
                prev.left = null;
            } else
                prev.right = null;
        }

        //Case 2: One Child

        else if (current.left == null) {
            if (prev.left == current)
                prev.left = current.right;
            else
                prev.right = current.right;
        } else if (current.right == null) {
            if (prev.left == current)
                prev.left = current.left;
            else
                prev.right = current.left;
        }

        //Case 3: Two Children
        else {
            Node<T> newParent = deleteBiggest(current);
            current.data = newParent.data;
        }
    }

    //recursive
    public void removeRecursive(T data) {
        removeNode(root, data);
    }

    private Node<T> removeNode(Node<T> current, T data) {
        int comparison = data.compareTo(current.data);

        if (comparison < 0) {
            current.left = removeNode(current.left, data);
        } else if (comparison > 0) {
            current.right = removeNode(current.right, data);
        } else {
            if (current.left == null && current.right == null)
                return null;

            else if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } else {
                Node<T> newParent = deleteBiggest(current.left);
                current.data = newParent.data;
                current.left = newParent.left;
            }
        }
        return current;
    }

    private Node<T> deleteBiggest(Node<T> current) {
        Node<T> previous = current;
        while (current.right != null) {
            previous = current;
            current = current.right;
        }
        previous.right = current.left;
        return current;
    }

    void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<T> element) {
        if (element == null)
            return;

        inOrder(element.left);
        T current = element.data;
        T left = (element.left == null) ? null : element.left.data;
        T right = (element.right == null) ? null : element.right.data;
        System.out.printf("Child: %s, Left: %s, Right: %s%n", current, left, right);
        inOrder(element.right);
    }

    public class Node<V> {

        V data;
        Node<V> left;
        Node<V> right;

        private Node(V data) {
            this.data = data;
        }
    }
}

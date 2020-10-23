import java.util.Scanner;

/**
 * A class with the implementation of the functionality of an array (list): adding, removing, getting.
 */
public class LinkedList {
    private
    Node root;
    static int elements = 0;
    public LinkedList()
    {
        root = null;
        elements = 1;
    }

    /**
     * Checking an array (list) for emptiness.
     * @return 1 if the array (list) is empty, 0 if not empty.
     */
    boolean isEmpty()
    {
        return root == null;
    }

    /**
     * Adding an element to the end of the array (list). If empty, then the value is added to the root of the list,
     * if not, then it is added one more element.
     */
    void add()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число:");
        int in = sc.nextInt();
        Node temp = new Node(in);
        if (isEmpty()) {
            root = temp;
            root.setNext(null);
        }
        else {
            Node temp1 = new Node(0);
            temp1 = root;
            while (temp1.getNext() != null) temp1 = temp1.getNext();
            temp1.setNext(temp);
            elements++;
        }
    }

    /**
     * Removing an element from an array (list) by index.
     * First, the list's checked for emptiness, then it's checked if the requested index is outside the array,
     * and then the element is deleted if all conditions are met.
     * @param index Actually, the index of the element.
     */
    void pop(int index)
    {
        if (isEmpty()) {
            System.out.println("Массив пуст.");
            return;
        }
        if (index >= elements) {
            System.out.println("Элемента под номером " + index + " в массиве нет.");
            return;
        }
        Node temp = new Node(0);
        temp = root;
        if (index == 0)
        {
            root = temp.getNext();
            return;
        }

        int counter = 0;
        --index;
        while (temp.getNext() != null)
        {
            if (counter == index)
            {
                Node delNode = new Node(0);
                delNode = temp.getNext();

                temp.setNext(delNode.getNext());
                return;
            }

            ++counter;
            temp = temp.getNext();
        }
    }

    /**
     * Displaying an array (list) on the screen in a line separated by a space.
     */
    void out() {
        if (isEmpty()) System.out.println("Массив пуст.");
        else {
            Node temp = new Node(0);
            temp = root;
            System.out.print("Элементы: ");
            while (temp != null) {
                System.out.print(temp.value + " ");
                temp = temp.getNext();
            }
            System.out.println();
        }
    }
}

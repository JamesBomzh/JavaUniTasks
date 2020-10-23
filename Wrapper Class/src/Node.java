/**
 * A class that implements a separate list node.
 */
public class Node {
    private
    Node next;
    int value;
    public Node(int number)
    {
        value = number;
    }

    /**
     * A method that sets a value in a node.
     * @param num The number that the node will contain.
     */
    void setInfo(int num)
    {
        value = num;
    }

    /**
     * A method that returns a value in a node.
     * @return Value in a node.
     */
    int getInfo()
    {
        return value;
    }

    /**
     * A method that sets a link to the next node.
     * @param node Next node.
     */
    void setNext(Node node)
    {
        next = node;
    }

    /**
     * A method that returns a link to the next node.
     * @return Next node.
     */
    Node getNext()
    {
        return next;
    }

}

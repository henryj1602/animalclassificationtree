package p1;

import java.util.ArrayList;

public class Node {
    private String name;
    private Node parent;
    private Node leftChild;
    private Node rightChild;


    private int tag;
    //private ArrayList<Node> children;

    public Node(String name, int tag) {
        this.tag = tag;
        this.name = name;
        leftChild = null;
        rightChild = null;

    }

    public Node(String name){
        this.name = name;
        leftChild = null;
        rightChild = null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name){this.name = name;}

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setLeftChild(Node leftChild){this.leftChild = leftChild;}

    public void setRightChild(Node rightChild){
        this.rightChild = rightChild;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }


}

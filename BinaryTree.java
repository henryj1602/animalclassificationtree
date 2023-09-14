package p1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree {

    private Node root;
    private Node current;
    private int value = 0;

    //commented out constructor that contains hardcoded tree
/*
    public BinaryTree(){
        root = new Node("furry");
        current = root;
        Node leftChild = new Node("dog");
        Node rightChild = new Node("snake");
        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);
        root.getLeftChild().setParent(root);
        root.getRightChild().setParent(root);

    }

 */
    //blank constructor for use when loading from file
    public BinaryTree(){

    }
    public void goHome() {
        current = root;
    }

    //prints out questions for user based on location in the tree
    public void printQuestion(){
        if(current.getLeftChild()== null && current.getRightChild() == null){
            System.out.println("Is this animal a " + current.getName() + " ? (Y/N)> ");
        }
        else {
            System.out.println("Is this animal " + current.getName() + " ? (Y/N)> ");
        }
    }


    //method used to reorganize tree when new animal is added
    public void insert(String name1, String name2){
        Node leftChild = new Node(name1);
        Node rightChild = new Node(current.getName());

        current.setLeftChild(leftChild);
        current.setRightChild(rightChild);
        current.getLeftChild().setParent(current);
        current.getRightChild().setParent(current);
        current.setName(name2);
    }

    //handles user input and tree navigation
    public void identifyAnimal() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        current=root;

        while (current.getLeftChild()!=null) {

            printQuestion();
            userInput = scanner.next();

            if (userInput.equalsIgnoreCase("Y")){

                current = current.getLeftChild();

            } else if (userInput.equalsIgnoreCase("N")){
                current = current.getRightChild();
            }
            if (current.getLeftChild()==null){
                printQuestion();
                userInput=scanner.next();
                if (userInput.equalsIgnoreCase("Y")){
                    System.out.println("Good.");

                } else {
                    System.out.println(getPath());
                    System.out.println("What is the new animal?\n");
                    String userInput1 = scanner.next();
                    System.out.println("What characteristics does a " + userInput1 + " have that a "+current.getName()+" does not?\n");
                    String userInput2 = scanner.next();
                    insert(userInput1, userInput2);

                }
                goHome();
                break;
            }

        }
    }

    public String getPath(){
        Node node = current;
        //initializes the path string
        String path = "I don't know any ";
        //stack to store path through tree
        Stack<String> animalStack = new Stack<>();

        while(node.getParent() != null){
            //checks if the current node is the left child or right child of its parent and applies appropriate syntax
            if(node.getParent().getRightChild().equals(node)){
                animalStack.push("not " + node.getParent().getName());
            }
            else if(node.getParent().getLeftChild().equals(node)){
                animalStack.push(node.getParent().getName());
            }
            //ascends the tree
            node = node.getParent();
        }
        //concatenates the stack onto the path string with appropriate commas
        while(!animalStack.isEmpty()){
            if(animalStack.size() == 1){
                path += animalStack.pop();
            }
            else{
                path += animalStack.pop() + ", ";
            }
        }
        //adds the end of the path string and returns it
        path += " animals that aren't a " + current.getName();
        return path;
    }

    //inserts nodes in tree based on the int value associated with each node
    public void loadInsert(String name, int newValue) {
        if (root == null) {
            root = new Node(name, newValue);
        } else {
            Node currentNode = root;
            boolean placed = false;
            while (!placed) {
                if (newValue == currentNode.getTag()) {
                    placed = true;
                } else if (newValue < currentNode.getTag()) {
                    if (currentNode.getLeftChild() == null) {
                        currentNode.setLeftChild(new Node(name,newValue));
                        currentNode.getLeftChild().setParent(currentNode);
                        placed = true;
                    } else {
                        currentNode = currentNode.getLeftChild();
                    }
                } else {
                    if (currentNode.getRightChild() == null) {
                        currentNode.setRightChild(new Node(name,newValue));
                        currentNode.getRightChild().setParent(currentNode);
                        placed = true;
                    } else {
                        currentNode = currentNode.getRightChild();
                    }
                }
            }
        }
    }

    //method for calling recursive method
    public void reNumber() {
        reNumber(root);
    }

    //recursive method that does an in-order depth first traversal and numbers each node
    public void reNumber(Node n){
        if(n != null){
            reNumber(n.getLeftChild());
            n.setTag(value);
            value++;
            reNumber(n.getRightChild());
        }
    }

    //turns tree into a string and writes it to file
    public void writeToFile() {
        try {
            PrintWriter outFile = new PrintWriter("tree.txt");

            String tree = "";
            Queue<Node> queue = new LinkedList<>();
            reNumber();
            if (root != null){
                queue.add(root);
                while(!queue.isEmpty()){
                    Node node = queue.remove();
                    tree += node.getTag() + "-" + node.getName() + ",";
                    if (node.getLeftChild()!=null){
                        queue.add(node.getLeftChild());
                    }
                    if (node.getRightChild()!=null){
                        queue.add(node.getRightChild());
                    }
                }
            }

            outFile.println(tree);
            outFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    //reads string from file and converts it into correct data types to be inserted into the tree
    public void loadFromFile() {
        try {
            FileReader file = new FileReader("tree.txt");
            Scanner inFile = new Scanner(file);
            String tree = inFile.nextLine();
            String[] splitTree = tree.split(",");
            int tag;
            String name;

            for(String string : splitTree){
                String[] splitInfo = string.split("-");
                tag = Integer.parseInt(splitInfo[0]);
                name = splitInfo[1];
                loadInsert(name, tag);
            }
            System.out.println("Loaded: " + tree);

            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}

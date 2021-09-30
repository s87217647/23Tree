import java.util.ArrayList;
import java.util.Collections;

/**
 This class implements a binary search tree whose
 nodes hold objects that implement the Comparable
 interface.
 */
public class Tree
{
    private Node root;
    //private int size;

    /**
     Constructs an empty tree.
     */
    public Tree()
    {
        root = null;
    }

    public boolean insert(Comparable key){
        // Corner case: zero element insertion
        if (root == null){
            root = new Node();
            root.addKey(key);
            return true;
        }

        if (find(root, key) != null){
            return false;
        }

        Node targetLeaf = leafNode(root, key);
        targetLeaf.addKey(key);

        return true;
    }

     private Node leafNode(Node root, Comparable  key){
        if (root == null){
            return null;
        }

        if (root.isLeaf())
            return root;
        if (key.compareTo(root.kthKey(1)) < 0){ // ----First Section----|----|-----
            return leafNode(root.kthChild(1), key);
        }
        else if (key.compareTo(root.kthKey(2)) > 0) {// -----|-----|----3rd  Section-----
            return leafNode(root.kthChild(3),key);
        }
        else return leafNode(root.kthChild(2), key);
     }

     private Node find(Node root, Comparable key){
        if(root.contains(key))
            return root;

        for(Node child : root.children)
            find(child, key);

         return null;
     }

    public int size(){return 0;} // Or , traverse the entire tree to find the size?


    public int size(int key){
        // It will involve 2 parts 1. find the existing nodee
        // 2. Get the subtree size.

        Node currentNode = find(root, key);

        if (currentNode == null)
            return 0;

        return subtreeSize(root);
    }
    public int subtreeSize(Node root){
        if (root == null)
            return 0;

        return (root.size() + subtreeSize(root.kthChild(1)) + subtreeSize(root.kthChild(2)) + subtreeSize(root.kthChild(3)));
    }

    //Todo: Fix this method
    public Comparable get(int x){
        // this should be base casse
        return root.keys.get(x);

    }




    /**
     A node of a tree stores a data item and references
     to the left and right child nodes.
     */
    //Todo: Modify this private class have to make it at least 2
    class Node
    {
        public ArrayList<Comparable> keys;
        public ArrayList<Node> children;

        public Node(){
            keys = new ArrayList<Comparable>();
            children = new ArrayList<Node>();
        }

        public void addKey(Comparable key){
            keys.add(key);
            Collections.sort(keys);
            if (keys.size() == 3){
                split();
            }
        }

        public boolean contains(Comparable key){
            return keys.contains(key);
        }

        public int size(){
            return keys.size();
        }

        public void split(){
            return;
        }

        public boolean isLeaf(){
            return children.size() == 0;
        }

        public Comparable kthKey(int k){
            return keys.get(k - 1);
        }

        public Node kthChild(int k) {
            if (k <= children.size())
                return children.get(k - 1);
            else
                return null;
        }


        }
}



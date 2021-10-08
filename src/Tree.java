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
//        if (root == null){
//            root = new Node();
//            root.addKey(key);
//            return true;
//        }
//
//        if (find(root, key) != null){
//            return false;
//        }
//
//        Node targetLeaf = leafNode(root, key);
//        targetLeaf.addKey(key);
//
//        return true;
        if (root == null)
            root = new Node();
        return root.insert(key);
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
        return root.size(key);

    }
    public int subtreeSize(Node root, int sizeCount){
        if (root == null)
            return 0;

        for (Node child : root.children)
            sizeCount += subtreeSize(child , sizeCount);

        return sizeCount + root.keys.size();
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
        private final int keyNum = 2;
        private final int childNum = 3;
        private ArrayList<Comparable> keys;
        private ArrayList<Node> children;

        public Node(){

            keys = new ArrayList<Comparable>();
            children = new ArrayList<Node>();
        }

        public boolean insert(Comparable key){
            if (keys.contains(key))
                return false;

            if (!isLeaf()){
                int targetChildIndex = 0;
                for (Comparable curKey : keys){
                    if (curKey.compareTo(key) < 0)
                        targetChildIndex  ++;
                }
                 return (children.get(targetChildIndex).insert(key));
            }
            else{
                keys.add(key);
                if (keys.size() >= keyNum){
                    split(this);
                }

            }


            return true;
        }

        public void split(Node parent){
            return;
        }

        public boolean contains(Comparable key){
            return keys.contains(key);
        }

        public int size(int key){
            Node found = find(this, key);

            if (found == null)
                return 0;
            return subtreeSize(found, 0);
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



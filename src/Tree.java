import javax.swing.plaf.ComponentInputMapUIResource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 This class implements a binary search tree whose
 nodes hold objects that implement the Comparable
 interface.
 */
public class Tree
{

    public final int childrenNum = 3;
    public final int keysNum = 2;

    private Node root;
    //private int size;

    /**
     Constructs an empty tree.
     */
    public Tree()
    {
        root = null;
    }

    public boolean insert(int key){
        if (root == null){
            root = new Node(key);
            return true;
        }
        return root.insert(key);
    }

    public int size(){return root.size();}


    public int size(int key){
        return root.size(key);
    }

    //Get back to this after fully test the method
    public Comparable get(int x){
        //After you finish above ones, come back for this.

        return root.get(x);
    }




    /**
     A node of a tree stores a data item and references
     to the left and right child nodes.
     */
    //Todo: Modify this private class have to make it at least 2
    class Node
    {
        public Node parent;
        public ArrayList<Integer> keys;
        public ArrayList<Node> children;


        public Node(){
            parent = null;
            keys = new ArrayList<Integer>();
            children = new ArrayList<Node>();
        }

        public Node(int key){
            parent = null;
            keys = new ArrayList<Integer>();
            children = new ArrayList<Node>();
            keys.add(key);
        }

        public boolean insert(int key){
            if (keys.contains(key)){
                return false;
            }
            // not leaf
            if (!isLeaf()){
                int targetChild = 0;
                for(int localKey: keys) {
                    if (key > localKey) {
                        targetChild++;
                    }
                }
                return children.get(targetChild).insert(key);

            }

            // leaf
            keys.add(key);
            Collections.sort(keys);
            // Wut about split
            if (keys.size() > keysNum)
                this.split();
            return true;
        }


//        private void split(){
//            if(isLeaf()){
//                if(parent == null){
//                    parent = new Node(keys.get(1));
//                }
//                else{
//                    parent.keys.add(keys.get(1));
//                    Collections.sort(parent.keys);
//                }
//
//
//                parent.children.add(new Node(keys.get(2)));
//                keys.remove(2);
//                keys.remove(1);
//
//                if (parent.keys.size() > keysNum){
//                    parent.split();
//                }
//
//            }
//            else{
//                Node left = new Node(keys.get(0));
//                Node right = new Node(keys.get(2));
//
//
//                // abstraction and make it a loop
//
//                left.children.add(children.get(0));
//                left.children.add(children.get(1));
//                children.get(0).parent = left;
//                children.get(1).parent = left;
//
//
//                right.children.add(children.get(2));
//                right.children.add(children.get(3));
//                children.get(2).parent = right;
//                children.get(3).parent = right;
//
//                if(parent == null){
//                    parent = new Node(keys.get(1));
//                }
//                else{
//                    parent.keys.add(keys.get(1));
//                }
//                parent.children.add(left);
//                parent.children.add(right);
//                right.parent = parent;
//                left.parent = parent;
//
//            }
//
//        }

        public void split(){
            if (parent == null){
                parent = new Node();
                root = parent;
            }
            parent.addKey(keys.get(1));
            keys.remove(1); // This level I have two key left

            Node newLeft = new Node(keys.get(0));
            Node newRight = new Node(keys.get(1));
            parent.addChild(newLeft);
            parent.addChild(newRight);

            if (!isLeaf()){
                newLeft.addChild(children.get(0));
                newLeft.addChild(children.get(1));
                newRight.addChild(children.get(2));
                newRight.addChild(children.get(3));

            }
            if (parent.keys.size() > keysNum)
                parent.split();
        }

        private void addChild(Node child){
            children.add(child);
            child.parent = this;
        }
        private void addChild(ArrayList<Node> children){
            return;
        }

        private void addKey(int key){
            keys.add(key);
            Collections.sort(keys);
        }

        public boolean isLeaf(){
            return children.size() == 0;
        }

        public int size(){
            if (isLeaf())
                return keys.size();

            int count = keys.size();
            for (Node child: children)
                count += child.size();

            return count;
        }

        public int size( int key){
            if (keys.contains(key)){
                return this.size();
            }

            if(!isLeaf()) {
                int targetChild = 0;
                for (int localKey : keys) {
                    if (key > localKey) {
                        targetChild++;
                    }
                }

                return children.get(targetChild).size(key);
            }

            return 0;
        }

        public int get(int x){
            return 0;
        }

    }
}



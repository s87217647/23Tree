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

    public int get(int x){
        if (x < 0 || x > size() - 1){
            return -1;
        }

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
            addKey(key);

            if (keys.size() > keysNum)
                this.split();
            return true;
        }


        public void split(){
            if (parent == null){
                parent = new Node(keys.get(1));
                root = parent;
                parent.addChild(this);
            }
            else {
                parent.addKey(keys.get(1));
            }


            keys.remove(1);
            Node rightPortion = new Node(keys.get(1));
            keys.remove(1);
            if (parent.children.get(0) == this) {
                parent.children.add(1, rightPortion);
                rightPortion.parent = parent;
            }
            else{
                parent.addChild(rightPortion);
            }


            if (!isLeaf()){
                rightPortion.addChild(children.get(2));
                rightPortion.addChild(children.get(3));
                children.remove(2);
                children.remove(2);
            }
            if (parent.keys.size() > keysNum)
                parent.split();
        }

        private void addChild(Node child){
            children.add(child);
            child.parent = this;
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
            int keyCount = 0;
            for (Node child : children){

                if (x < child.size()){
                    return child.get(x);
                }
                if (x == child.size()){
                    return keys.get(keyCount);
                }

                if (x > child.size() ){
                    x -= (child.size());
                    x--;
                }
                keyCount++;

            }
            if (x <= keys.size()){
                return keys.get(x);
            }

            return -1;
        }

    }
}



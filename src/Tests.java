import static org.junit.Assert.*;

import org.junit.Test;


public class Tests
{

    @org.junit.Test
    public void singleNodeTree()
    {
        // Don't know if should test sections of nodes before testing insertion

        Tree t = new Tree();
        t.insert(9);

        assertEquals(1, t.size(9));
        assertEquals(0, t.size(8));
        assertEquals(0, t.size(10));

        t.insert(15);
        assertEquals(2, t.size(9));
        assertEquals(0, t.size(8));
        assertEquals(0, t.size(10));
        assertEquals(2, t.size(15));
        assertEquals(0, t.size(18));

        t = new Tree();
        t.insert(15);
        t.insert(9);
        assertEquals(2, t.size(9));
        assertEquals(0, t.size(8));
        assertEquals(0, t.size(10));
        assertEquals(2, t.size(15));
        assertEquals(0, t.size(18));

        assertEquals(9, t.get(0)); // left most traverse
        assertEquals(15, t.get(1));


    }

    @org.junit.Test
    public void oneSplitLeft()
    {
        Tree t = new Tree();
        t.insert(9);
        t.insert(15);
        t.insert(1);

        assertEquals(3, t.size(9));
        assertEquals(1, t.size(15));
        assertEquals(0, t.size(17));
        assertEquals(0, t.size(11));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        // Todo: implement get()
        assertEquals(1, t.get(0));
        assertEquals(9, t.get(1));
        assertEquals(15, t.get(2));

        assertEquals(3,t.size());
    }

    @org.junit.Test
    public void insertionTest(){
        Tree t = new Tree();
        int[] nums = {5,10,20,8,7,15,30,11};

        for (int i = 0; i < nums.length; i++) {
            t.insert(nums[i]);
        }



        System.out.println("This is the end, hold your breathe and count to ten");



    }

    @org.junit.Test
    public void oneSplitRight()
    {
        Tree t = new Tree();
        t.insert(1);
        t.insert(9);
        t.insert(15);

        assertEquals(3, t.size(9));
        assertEquals(1, t.size(15));
        assertEquals(0, t.size(17));
        assertEquals(0, t.size(11));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(1, t.get(0));
        assertEquals(9, t.get(1));
        assertEquals(15, t.get(2));
        assertEquals(3,t.size());


    }

    @org.junit.Test
    public void oneSplitMiddle()
    {
        Tree t = new Tree();
        t.insert(1);
        t.insert(15);
        t.insert(9);

        assertEquals(3, t.size(9));
        assertEquals(1, t.size(15));
        assertEquals(0, t.size(17));
        assertEquals(0, t.size(11));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(1, t.get(0));
        assertEquals(9, t.get(1));
        assertEquals(15, t.get(2));
        assertEquals(3,t.size());


    }


    @org.junit.Test
    public void testDuplicates()
    {
        Tree t = new Tree();
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(4);
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(4);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(4);

        assertEquals(7, t.size(9));
        assertEquals(3, t.size(4));
        assertEquals(3, t.size(15));

        assertEquals(0, t.size(12));
        assertEquals(1, t.size(13));
        assertEquals(0, t.size(14));
        assertEquals(0, t.size(19));
        assertEquals(1, t.size(20));
        assertEquals(0, t.size(21));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(0, t.size(6));
        assertEquals(1, t.size(7));
        assertEquals(0, t.size(8));

        assertEquals(1, t.get(0));
        assertEquals(4, t.get(1));
        assertEquals(7, t.get(2));
        assertEquals(9, t.get(3));
        assertEquals(13, t.get(4));
        assertEquals(15, t.get(5));
        assertEquals(20, t.get(6));
        assertEquals(7,t.size());



    }


}
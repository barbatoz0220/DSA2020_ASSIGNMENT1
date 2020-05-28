/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacknqueue;

import java.sql.SQLOutput;
import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.Timeout;

/**
 *
 * @author LTSACH
 */


class IntComparator implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
    
}
public class PriorityQueueTest {
    
    public PriorityQueueTest() {
    }
    @Rule
    public Timeout globalTimeout = new Timeout(2000); 
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Performing the testing for class: PriorityQueue");
        System.out.println(new String(new char[80]).replace('\0', '='));
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void size() {
        System.out.println("testing ... size()");
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        assertEquals(0, queue.size());
        
        int[] array = {15, 17, 24, 6, 8};
        for(int item: array) queue.push(item);
        assertEquals(array.length, queue.size());
        queue.pop();
        assertEquals(array.length -1, queue.size());
    }
    @Test
    public void pop() {
        System.out.println("testing ... pop()");
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        assertEquals(0, queue.size());
        
        int[] array = {15, 17, 24, 6, 8};
        for(int item: array) queue.push(item);
        assertEquals(array.length, queue.size());
        queue.pop();
        assertEquals(array.length -1, queue.size());
        queue.pop();queue.pop();queue.pop();queue.pop();
        assertEquals(0, queue.size());
    }
    @Test
    public void empty() {
        System.out.println("testing ... empty()");
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        assertEquals(0, queue.size());
        
        int[] array = {15, 17, 24, 6, 8};
        for(int item: array) queue.push(item);
        assertEquals(array.length, queue.size());
        queue.pop();
        assertEquals(array.length -1, queue.size());
        queue.pop();queue.pop();queue.pop();queue.pop();
        assertEquals(0, queue.size());
        assertEquals(true, queue.empty());
    }
    @Test
    public void peek_minheap() {
        System.out.println("testing ... peek() from minheap");
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        assertEquals(0, queue.size());
        
        int[] array = {15, 17, 24, 6, 8};
        for(int item: array) queue.push(item);
        assertEquals(array.length, queue.size());
        int[] expected = {6, 8,15,17,24}; //ascending
        System.out.println(queue.toString());
        int index = 0;
        while(!queue.empty()){
            int item = queue.pop();
            assertEquals(expected[index], item);
            index += 1;
        }
    }
    @Test
    public void peek_maxheap() {
        System.out.println("testing ... peek() from maxheap");
        PriorityQueue<Integer> queue = new PriorityQueue<>(new IntComparator());
        assertEquals(0, queue.size());
        
        int[] array = {15, 17, 24, 6, 8};
        for(int item: array) queue.push(item);
        assertEquals(array.length, queue.size());
        int[] expected = {24, 17, 15, 8, 6}; //ascending
        int index = 0;
        System.out.print("Start:");
        System.out.println(queue);
        while(!queue.empty()){
            int item = queue.pop();
            assertEquals(expected[index], item);
            index += 1;
        }
    }
}

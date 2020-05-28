/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacknqueue;

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
public class QueueTest {
    
    public QueueTest() {
    }
    @Rule
    public Timeout globalTimeout = new Timeout(2000); 
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Performing the testing for class: Queue");
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
        Queue<Integer> queue = new Queue<>();
        int[] array = {0,1,2,3,0,1};
        for(int item: array) queue.push(item);
        assertEquals(array.length, queue.size());
        queue.pop();
        assertEquals(array.length -1, queue.size());
    }
    @Test
    public void pop() {
        System.out.println("testing ... pop()");
        Queue<Integer> queue = new Queue<>();
        int[] array = {0,1,2,3,0,1};
        for(int item: array) queue.push(item);
        assertEquals(array.length, queue.size());
        queue.pop();
        assertEquals(array.length-1, queue.size());
        queue.pop();queue.pop();queue.pop();queue.pop();queue.pop();
        assertEquals(0, queue.size());
    }
    @Test
    public void empty() {
        System.out.println("testing ... empty()");
        Queue<Integer> queue = new Queue<>();
        assertEquals(true, queue.empty());
        
        int[] array = {0,1,2,3,0,1};
        for(int item: array) queue.push(item);
        assertEquals(array.length, queue.size());
        queue.pop();
        assertEquals(array.length-1, queue.size());
        queue.pop();queue.pop();queue.pop();queue.pop();queue.pop();
        assertEquals(0, queue.size());
        assertEquals(true, queue.empty());
    }
    @Test
    public void peek() {
        System.out.println("testing ... peek()");
        Queue<Integer> queue = new Queue<>();
        int[] array = {0,1,2,3,0,1};
        for(int item: array) queue.push(item);
        assertEquals(array.length, queue.size());
        assertEquals((int)0, (int)queue.peek());
        queue.pop();queue.pop();
        assertEquals((int)2, (int)queue.peek());
    }
}

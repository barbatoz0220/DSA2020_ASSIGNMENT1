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
public class StackTest {
    
    public StackTest() {
    }
    @Rule
    public Timeout globalTimeout = new Timeout(2000); 
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Performing the testing for class: Stack");
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
        Stack<Integer> stack = new Stack<>();
        int[] array = {0,1,2,3,0,1};
        for(int item: array) stack.push(item);
        assertEquals(array.length, stack.size());
        stack.pop();
        assertEquals(array.length -1, stack.size());
    }
    @Test
    public void pop() {
        System.out.println("testing ... pop()");
        Stack<Integer> stack = new Stack<>();
        int[] array = {0,1,2,3,0,1};
        for(int item: array) stack.push(item);
        assertEquals(array.length, stack.size());
        stack.pop();
        assertEquals(array.length-1, stack.size());
        stack.pop();stack.pop();stack.pop();stack.pop();stack.pop();
        assertEquals(0, stack.size());
    }
    @Test
    public void empty() {
        System.out.println("testing ... empty()");
        Stack<Integer> stack = new Stack<>();
        assertEquals(true, stack.empty());
        
        int[] array = {0,1,2,3,0,1};
        for(int item: array) stack.push(item);
        assertEquals(array.length, stack.size());
        stack.pop();
        assertEquals(array.length-1, stack.size());
        stack.pop();stack.pop();stack.pop();stack.pop();stack.pop();
        assertEquals(0, stack.size());
        assertEquals(true, stack.empty());
    }
    @Test
    public void peek() {
        System.out.println("testing ... peek()");
        Stack<Integer> stack = new Stack<>();
        int[] array = {0,1,2,3,0,1};
        for(int item: array) stack.push(item);
        assertEquals(array.length, stack.size());
        assertEquals((int)1, (int)stack.peek());
        stack.pop();stack.pop();
        assertEquals((int)3, (int)stack.peek());
    }
}

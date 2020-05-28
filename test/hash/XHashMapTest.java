/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

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

public class XHashMapTest {
    
    public XHashMapTest() {
    }
    @Rule
    public Timeout globalTimeout = new Timeout(2000); 
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Performing the testing for class: XHashMapTest");
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
        XHashMap<Integer, Integer> map = new XHashMap<>(20, 0.75f);
        assertEquals(0, map.size());
        
        int[] array = {0, 20, 40, 60, 1, 21, 41, 3, 23, 43, 63, 83};
        for(int item: array) map.put(item, item);
        assertEquals(array.length, map.size());
                
        assertEquals(4, map.numEntryAt(0));
        assertEquals(3, map.numEntryAt(1));
        assertEquals(0, map.numEntryAt(2));
        assertEquals(5, map.numEntryAt(3));
        for(int idx=4; idx < 20; idx++) assertEquals(0, map.numEntryAt(idx));
    }
    @Test
    public void get() {
        System.out.println("testing ... get(key)");
        XHashMap<Integer, Integer> map = new XHashMap<>(20, 0.75f);
        assertEquals(0, map.size());
        
        int[] array = {0, 20, 40, 60, 1, 21, 41, 3, 23, 43, 63, 83};
        for(int item: array) map.put(item, item);
        for(int item: array) assertEquals(item, (int)map.get(item));
        assertEquals(null, map.get(15));
    }
    @Test
    public void remove_key() {
        System.out.println("testing ... remove(key)");
        XHashMap<Integer, Integer> map = new XHashMap<>(20, 0.75f);
        assertEquals(0, map.size());
        
        int[] array = {0, 20, 40, 60, 1, 21, 41, 3, 23, 43, 63, 83};
        for(int item: array) map.put(item, item);
        for(int key: array) {
            int value = map.remove(key);
            assertEquals(key, value);
        }
        assertEquals(true, map.empty());
        assertEquals(0, map.size());
    }
    @Test
    public void remove_key_value() {
        System.out.println("testing ... remove(key, value)");
        XHashMap<Integer, Integer> map = new XHashMap<>(20, 0.75f);
        assertEquals(0, map.size());
        
        int[] array = {0, 20, 40, 60, 1, 21, 41, 3, 23, 43, 63, 83};
        for(int item: array) map.put(item, item);
        for(int item: array) assertEquals(true, map.remove(item, item));
        
        assertEquals(true, map.empty());
        assertEquals(0, map.size());
    }
    @Test
    public void contains_key() {
        System.out.println("testing ... containsKey(key)");
        XHashMap<Integer, Integer> map = new XHashMap<>(20, 0.75f);
        assertEquals(0, map.size());
        
        int[] array = {0, 20, 40, 60, 1, 21, 41, 3, 23, 43, 63, 83};
        for(int item: array) map.put(item, item);
        for(int key: array) assertEquals(true, map.containsKey(key));
        
        assertEquals(false, map.containsKey(12));
    }
    @Test
    public void contains_value() {
        System.out.println("testing ... containsValue(value)");
        XHashMap<Integer, Integer> map = new XHashMap<>(20, 0.75f);
        assertEquals(0, map.size());
        
        int[] array = {0, 20, 40, 60, 1, 21, 41, 3, 23, 43, 63, 83};
        for(int item: array) map.put(item, item);
        for(int value: array) assertEquals(true, map.containsValue(value));
        
        assertEquals(false, map.containsValue(12));
    }
    
}

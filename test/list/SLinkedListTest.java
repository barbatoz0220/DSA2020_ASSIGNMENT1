/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.Timeout;
/**
 *
 * @author LTSACH
 */
public class SLinkedListTest {
    public SLinkedListTest() {
    }
    
    @Rule
    public Timeout globalTimeout = new Timeout(2000); 
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Performing the testing for class: SLinkedList");
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
        List<String> list = new SLinkedList<>();
        String[] array = {"0","1","2","3","0","1"};
        for(String item: array) list.add(item);
        assertEquals(array.length, list.size());
        list.remove(1);
        assertEquals(array.length -1, list.size());
    }
    @Test
    public void contains() {
        System.out.println("testing ... contains()");
        List<String> list = new SLinkedList<>();
        String[] array = {"0","1","2","3","0","1"};
        for(String item: array) list.add(item);
        list.remove("1");
        list.add("4");
        list.remove("0");
        list.remove("2");
       
        assertEquals(4, list.size());
        assertEquals(true, list.contains("0"));
        assertEquals(true, list.contains("3"));
        assertEquals(true, list.contains("1"));
        assertEquals(false, list.contains("2"));
    }
    
    @Test
    public void inndexOf() {
        System.out.println("testing ... indexOf()");
        List<String> list = new SLinkedList<>();
        String[] array = {"0","1","2","3","0","1", "5"};
        for(String item: array) list.add(item);
        assertEquals(-1, list.indexOf("6"));
        assertEquals(0, list.indexOf("0"));
        assertEquals(6, list.indexOf("5"));
        assertEquals(1, list.indexOf("1"));
    }
    @Test
    public void lastIndexOf() {
        System.out.println("testing ... lastIndexOf()");
        List<String> list = new SLinkedList<>();
        String[] array = {"0","1","2","3","0","1", "5"};
        for(String item: array) list.add(item);
        assertEquals(-1, list.lastIndexOf("8"));
        assertEquals(6, list.lastIndexOf("5"));
        assertEquals(4, list.lastIndexOf("0"));
        assertEquals(2, list.lastIndexOf("2"));
    }
    @Test public void remove_int(){
        System.out.println("testing ... remove(int)");
        List<String> list = new SLinkedList<>();
        try{
            list.remove(0);
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t empty case: work as expectation");
        }
        
        list.add("0");
        list.add("1");
        try{
            list.remove(4);
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t IndexOutOfBounds (>=size): work as expectation");
        }
        
        try{
            list.remove(-1);
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t IndexOutOfBounds (<0): work as expectation");
        } 
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        list.remove(1);
        assertEquals(3, list.size());
        assertEquals(false, list.contains("1"));
        assertEquals("2", list.get(1));
    }
    @Test public void remove_object(){
        System.out.println("testing ... remove(Object)");
        List<String> list = new SLinkedList<>();
        
        assertEquals(false, list.remove("10"));
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        assertEquals(false, list.remove("10"));
        assertEquals(4, list.size());
        assertEquals(true, list.remove("0"));
        assertEquals(3, list.size());
        assertEquals(true, list.remove("3"));
        assertEquals(2, list.size());
        assertEquals(true, list.remove("1"));
        assertEquals(1, list.size());
        assertEquals(true, list.remove("2"));
        assertEquals(0, list.size());
    }
    @Test public void clear(){
        System.out.println("testing ... clear()");
        List<String> list = new SLinkedList<>();
        
        assertEquals(0, list.size());
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
    }
    
    @Test 
    public void get_int(){
        System.out.println("testing ... get(int index)");
        List<String> list = new SLinkedList<>();
        try{
            list.get(0);
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t empty case: work as expectation");
        }
        
        list.add("0");
        list.add("1");
        try{
            list.get(4);
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t IndexOutOfBounds (>=size): work as expectation");
        }
        
        try{
            list.get(-1);
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t IndexOutOfBounds (<0): work as expectation");
        } 
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        assertEquals("2", list.get(2));
    }
    
    @Test 
    public void set_int_object(){
        System.out.println("testing ... set(int index, Object)");
        List<String> list = new SLinkedList<>();
        try{
            list.set(0, "2");
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t empty case: work as expectation");
        }
        
        list.add("0");
        list.add("1");
        try{
            list.set(3, "2");
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t IndexOutOfBounds (>=size): work as expectation");
        }
        
        try{
            list.set(-1, "2");
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t IndexOutOfBounds (<0): work as expectation");
        } 
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        list.set(2, "5");
        assertEquals("5", list.get(2));
    }
    @Test 
    public void add_int_object(){
        System.out.println("testing ... add(int index, Object)");
        List<String> list = new SLinkedList<>();
        try{
            list.add(1, "2");
        }
        catch(IndexOutOfBoundsException exp){
            assertEquals(true, true);
            System.out.println("\t empty case: work as expectation");
        }
        
        list.add(0, "10");
        assertEquals(1, list.size());
        list.add("0");
        assertEquals("10", list.get(0));        
        assertEquals("0", list.get(1));
        assertEquals(2, list.size());
        
        list.add(1, "20");
        assertEquals(3, list.size());
        
        assertEquals("10", list.get(0));
        assertEquals("20", list.get(1));
        assertEquals("0", list.get(2));
        
        try{
            list.set(3, "5");
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (>=size): work as expectation");
        }
        try{
            list.add(-1, "2");
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (<0): work as expectation");
        } 
    }
    @Test public void add_object(){
        System.out.println("testing ... add(Object)");
        List<String> list = new SLinkedList<>();
        
        assertEquals(0, list.size());
        assertEquals(true, list.isEmpty());
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
    }
    @Test public void iterator_remove(){
        System.out.println("testing ... iterator.remove");
        List<String> list = new SLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String item = it.next();
            if(Integer.parseInt(item)%2 != 0) //remove odds
                it.remove();
        }
        assertEquals(5, list.size());
        assertEquals("2", list.get(1));
        assertEquals("8", list.get(4));
    }
    @Test public void listIterator_remove(){
        System.out.println("testing ... listIterator.remove");
        List<String> list = new SLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        ListIterator<String> it = list.listIterator();
        while(it.hasNext()){
            String item = it.next();
            if(Integer.parseInt(item)%2 != 0) //remove odds
                it.remove();
        }
        assertEquals(5, list.size());
        assertEquals("2", list.get(1));
        assertEquals("8", list.get(4));
    }
    @Test public void listIterator_set_next(){
        System.out.println("testing ... listIterator.set with next()");
        List<String> list = new SLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        ListIterator<String> it = list.listIterator();
        while(it.hasNext()){
            String item = it.next();
            if(Integer.parseInt(item)%2 != 0){ //odd item replaced with 2*item
                String new_item =  String.format("%d", Integer.parseInt(item)*2);
                it.set(new_item);
            }
        }
        assertEquals(10, list.size());
        assertEquals("0", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("2", list.get(2));
        assertEquals("6", list.get(3));
        assertEquals("18", list.get(9));
    }
    
    @Test
    public void listIterator_set_prev(){
        System.out.println("testing ... listIterator.set with prev");
        List<String> list = new SLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        ListIterator<String> it = list.listIterator(list.size());
        while(it.hasPrevious()){
            String item = it.previous();
            if(Integer.parseInt(item)%2 != 0){ //odd item replaced with 2*item
                String new_item =  String.format("%d", Integer.parseInt(item)*2);
                it.set(new_item);
            }
        }
        assertEquals(10, list.size());
        assertEquals("0", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("2", list.get(2));
        assertEquals("6", list.get(3));
        assertEquals("18", list.get(9));
    }
    
    @Test public void listIterator_add_next(){
        System.out.println("testing ... listIterator.add with next()");
        List<String> list = new SLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        
        ListIterator<String> it = list.listIterator();
        while(it.hasNext()){
            String item = it.next();
            if(Integer.parseInt(item)%2 != 0){//add 2*item at odd item
                String new_item =  String.format("%d", Integer.parseInt(item)*2);
                it.add(new_item);
            }
        }
        assertEquals(15, list.size());

        assertEquals("0", list.get(0));
        assertEquals("2", list.get(1)); //added 1*2 = "2"
        assertEquals("1", list.get(2)); //old "1"
        assertEquals("2", list.get(3));
        assertEquals("18", list.get(13)); //added 9*2 = "18"
        assertEquals("9", list.get(14)); //old "9"
    }
    @Test public void listIterator_add_prev(){
        System.out.println("testing ... listIterator.add with previous()");
        List<String> list = new SLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        
        ListIterator<String> it = list.listIterator(list.size());
        while(it.hasPrevious()){
            String item = it.previous();
            if(Integer.parseInt(item)%2 != 0){
                String new_item =  String.format("%d", Integer.parseInt(item)*2);
                it.add(new_item);
            }
        }
        assertEquals(15, list.size());

        assertEquals("0", list.get(0));
        assertEquals("2", list.get(1)); //added 1*2 = "2"
        assertEquals("1", list.get(2)); //old "1"
        assertEquals("2", list.get(3));
        assertEquals("18", list.get(13)); //added 9*2 = "18"
        assertEquals("9", list.get(14)); //old "9"
    }
    @Test public void listIterator_remove_with_next(){
        System.out.println("testing ... listIterator.remove with next()");
        List<Integer> list = new SLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(idx);
        }
        assertEquals(10, list.size());
        ListIterator<Integer> it = list.listIterator();
        while(it.hasNext()){
            int item = it.next();
            if(item >= 5) it.remove();
        }
        assertEquals(5, list.size());
        assertEquals(0, (int)list.get(0));
        assertEquals(4, (int)list.get(4));
        
        it = list.listIterator();
        while(it.hasNext()){
            int item = it.next();
            if(item <= 2) it.remove();
        }
        assertEquals(2, list.size());
        assertEquals(3, (int)list.get(0));
        assertEquals(4, (int)list.get(1));
    }
    @Test public void listIterator_remove_with_prev(){
        System.out.println("testing ... listIterator.remove with previous()");
        List<Integer> list = new SLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(idx);
        }
        assertEquals(10, list.size());
        ListIterator<Integer> it = list.listIterator(list.size());
        while(it.hasPrevious()){
            int item = it.previous();
            if(item >= 5) it.remove();
        }

        assertEquals(5, list.size());
        assertEquals(0, (int)list.get(0));
        assertEquals(4, (int)list.get(4));
        
        it = list.listIterator(list.size());
        while(it.hasPrevious()){
            int item = it.previous();
            if(item <= 2) it.remove();
        }
        assertEquals(2, list.size());
        assertEquals(3, (int)list.get(0));
        assertEquals(4, (int)list.get(1));
    }
}

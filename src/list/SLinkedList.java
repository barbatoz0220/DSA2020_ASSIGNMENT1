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

/**
 *
 * @author LTSACH
 */
public class SLinkedList<E> implements java.util.List<E>{
    private MyListIterator listIterator;
    private MyIterator myIterator;

    private static enum MoveType {NEXT, PREV}
    private final Node<E> head, tail;
    private int size;

    public SLinkedList(){
        myIterator = new MyIterator();
        listIterator = new MyListIterator(0);
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.next = head;
        size = 0;
    }

    private void checkValidIndex(int index){
        if((index < 0) || (index >= size) ){
            String message = String.format("Invalid index (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }
    }

    private Node<E> getDataNode(int index) {
        checkValidIndex(index);
        Node<E> curNode = head.next;
        int runIndex = 0;
        while (curNode != tail) {
            if (index == runIndex) break;
            runIndex += 1;
            curNode = curNode.next;
        }
        return curNode;
    }

    //getNode: can return head
    private Node<E> getNode(int index) {
        if ((index < -1) || (index >= size)) {
            String message = String.format("Invalid index (including head) = %d)", index);
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> curNode = head;
        int runIndex = -1;
        while (curNode != tail) {
            if (index == runIndex) break;
            runIndex += 1;
            curNode = curNode.next;
        }
        return curNode;
    }

    private void addAfter(Node<E> afterThis, Node<E> newNode){
        newNode.next = afterThis.next;
        afterThis.next = newNode;
        if(newNode.next == tail) tail.next = newNode;
        size += 1;
    }


    private Node<E> removeAfter(Node<E> afterThis){
        Node<E> removedNode = afterThis.next;
        afterThis.next = removedNode.next;
        if(removedNode.next == tail) tail.next = afterThis;
        removedNode.next = null;
        size -= 1;
        return removedNode;
    }

    //Group-1: read list’s properties
    public int size() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }

    @Override
    public boolean contains(Object o){return indexOf(o) != -1;}

    @Override
    public Object[] toArray() {
        /*IMPLEMENTATION: NOT REQUIRED*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        /*IMPLEMENTATION: NOT REQUIRED*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<E>(null, e);
        Node<E> lastNode = tail.next;
        addAfter(lastNode, newNode);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> prevNode = head;
        Node<E> curNode = head.next;
        boolean found = false;
        while(curNode != tail){
            if(curNode.element.equals(o)){
                found = true;
                removeAfter(prevNode);
                break;
            }
            curNode = curNode.next;
            prevNode = prevNode.next;
        }
        return found;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        /*IMPLEMENTATION: NOT REQUIRED*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        /*IMPLEMENTATION: NOT REQUIRED*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        /*IMPLEMENTATION: NOT REQUIRED*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        /*IMPLEMENTATION: NOT REQUIRED*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        /*IMPLEMENTATION: NOT REQUIRED*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear(){
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.element = null;
            x.next = null;
            x = next;
        }
        head.next = tail;
        tail.next = head;
        size = 0;
    }
    

    @Override
    public E get(int index){return getDataNode(index).element;}
    @Override
    public E set(int index, E element){
        checkValidIndex(index);
        Node<E> x = getDataNode(index);
        E oldVal = x.element;
        x.element = element;
        return oldVal;
    }

    @Override
    public void add(int index, E element) {
        Node<E> prevNode = getNode(index - 1);
        Node<E> newNode = new Node<>(null, element);
        addAfter(prevNode, newNode);
    }

    @Override
    public E remove(int index) {
        if(size == 0){
            String message = String.format("Remove at %d, but the list is empty", index);
            throw new IndexOutOfBoundsException(message);
        }
        Node<E> prevNode = getNode(index-1);
        Node<E> curNode = prevNode.next;
        removeAfter(prevNode);
        return curNode.element;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> curNode = head.next;
        int foundIdx = -1;
        int index = 0;
        while(curNode != tail){
            if(curNode.element.equals(o)){
                foundIdx = index;
                break;
            }
            index += 1;
            curNode = curNode.next;
        }
        return foundIdx;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> curNode = head.next;
        int foundIdx = -1;
        int index = 0;
        while(curNode != tail){
            if(curNode.element.equals(o)){
                foundIdx = index;
                //continue to find, instead of break here
            }
            index += 1;
            curNode = curNode.next;
        }
        return foundIdx;
    }

    @Override
    public Iterator<E> iterator(){return listIterator();}
    public ListIterator<E> listIterator(){return new MyListIterator(0);}
    @Override
    public ListIterator<E> listIterator(int index){return new MyListIterator(index);}
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        /*IMPLEMENTATION: NOT REQUIRED*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String toString(){
        String desc = "[";
        Iterator<E> it = this.iterator();
        while(it.hasNext()){
            E e = it.next();
            desc += String.format("%s,", e);
        }
        if(desc.endsWith(",")) desc = desc.substring(0, desc.length() - 1);
        desc += "]";
        return desc;
    }

    private class Node<E>{
        E element;
        Node<E> next;
        Node(Node<E> next, E element){
            this.next = next;
            this.element = element;
        }
        void update(Node<E> next, E element){
            this.next = next;
            this.element = element;
        }
    }//End of Node

    public class MyIterator implements Iterator<E> {
        int cursor = 0;
        MoveType moveType = MoveType.NEXT;
        boolean afterMove = false;

        @Override
        public boolean hasNext() {
            return this.cursor != SLinkedList.this.size;
        }

        @Override
        /* Move cursor to next + return previous element*/
        public E next() {
            cursor += 1;
            moveType = MoveType.NEXT;
            afterMove = true;
            return SLinkedList.this.get(cursor - 1);
        }

        @Override
        public void remove() {
            if (!afterMove) return;
            SLinkedList.this.remove(cursor - 1);
            cursor -= 1;
            afterMove = false;
        }
    }

    public class MyListIterator extends MyIterator implements ListIterator<E> {
        // When the list iterator is created, set cursor to 0
        public MyListIterator(int index) {
            cursor = index;
        }
        // For next index, return the cursor only (because the cursor stands before the real current index)
        @Override
        public int nextIndex() {
            return cursor;
        }
        // For previous index, return cursor - 1
        @Override
        public int previousIndex() {
            return cursor - 1;
        }
        // Return true if cursor != 0, false if cursor == 0 (because cursor is at the head of the list)
        @Override
        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        @Override
        public E previous() {
            cursor -= 1;
            moveType = MoveType.PREV;
            afterMove = true;
            return SLinkedList.this.get(cursor);
        }

        @Override
        public void remove() {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT) super.remove();
            else {
                SLinkedList.this.remove(cursor);
                afterMove = false;
            }
        }

        @Override
        public void set(E e) {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT)
                SLinkedList.this.set(cursor - 1, e);
            else
                SLinkedList.this.set(cursor, e);
        }

        @Override
        public void add(E e) {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT)
                SLinkedList.this.add(cursor - 1, e);
            else
                SLinkedList.this.add(cursor, e);
            cursor += 1;
            afterMove = false;
        }
    }
} //End of SLinkedList

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import java.util.*;

/**
 *
 * @author LTSACH
 */
public class DLinkedList<E> implements List<E>{
    private static enum MoveType{ NEXT, PREV}
    private Node<E> head;
    private Node<E> tail;
    private int size;
    public DLinkedList(){
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, null, null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    private void checkValidIndex(int index){
        if((index < 0) || (index > size) ){
            String message = String.format("Invalid index (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }
    }

    private Node<E> getDataNode(int index) {
        checkValidIndex(index);
        if (index < (size >> 1)) {
            Node<E> curNode = head.next;
            int runIndex = 0;
            while(curNode != tail){
                if(index == runIndex) break;
                runIndex += 1;
                curNode = curNode.next;
            }
            return curNode;
        }

        Node<E> curNode = tail.prev;
        int runIndex = size - 1;
        while (curNode != head) {
            if (index == runIndex) break;
            runIndex -= 1;
            curNode = curNode.prev;
        }
        return curNode;
    }

    private Node<E> getNode(int index) {
        if((index < -1) || (index > size) ){
            String message = String.format("Invalid index (including head) (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }
        if (index == size)
            return tail;

        if (index < (size >> 1)) {
            Node<E> curNode = head;
            int runIndex = -1;
            while(curNode != tail){
                if(index == runIndex) break;
                runIndex += 1;
                curNode = curNode.next;
            }
            return curNode;
        }

        Node<E> curNode = tail;
        int runIndex = size;
        while (curNode != head) {
            if (index == runIndex) break;
            runIndex -= 1;
            curNode = curNode.prev;
        }
        return curNode;
    }
    /*
    insertLnewR(Node<E> left, Node<E> newNode, Node<E> right):
    insert newNode to the double-linked list.
    after insertion: left<->newNode <->right
    */
    private void insertLnewR(Node<E> left, Node<E> newNode, Node<E> right) {
        newNode.prev = left;
        newNode.next = right;
        left.next = newNode;
        right.prev = newNode;
        size += 1;
    }
    /*
    removeNode(Node<E> removedNode):
    remove "removedNode" from the double-linked list.
    */
    private void removeNode(Node<E> removedNode){
        Node<E> beforeNode = removedNode.prev;
        beforeNode.next = removedNode.next;
        removedNode.next.prev = beforeNode;
        removedNode.next = null;
        removedNode.prev = null;
        size -= 1;
    }
    
    //////////////////////////////////////////////////////////////////////////
    /////////////////// API of Doubble-Linked List         ///////////////////
    ////////////////////////////////////////////////////////////////////////// 
    //Group 1: read properties
    public int size() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }
    @Override
    public Iterator<E> iterator() { return new FBWDIterator();}

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
        Node<E> newNode = new Node<E>(null, null, e);
        Node<E> lastNode = tail.prev;
        addAfter(lastNode, newNode);
        return true;
    }
    

    @Override
    public boolean remove(Object o) {
        Node<E> curNode = head.next;
        boolean found = false;
        while(curNode != tail){
            if(curNode.element.equals(o)){
                found = true;
                removeNode(curNode);
                break;
            }
            curNode = curNode.next;
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
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.element = null;
            x.next = null;
            x = next;
        }
        head.next = tail;
        tail.prev = head;
        size = 0;
    }



    @Override
    public E get(int index){
        checkValidIndex(index);
        return getDataNode(index).element;
    }

    @Override
    public E set(int index, E element){
        checkValidIndex(index);
        Node<E> x = getNode(index);
        E oldVal = x.element;
        x.element = element;
        return oldVal;
    }

    private void addAfter(Node<E> afterThis, Node<E> newNode){
        afterThis.next.prev = newNode;
        newNode.next = afterThis.next;
        newNode.prev = afterThis;
        afterThis.next = newNode;
        size += 1;
    }

    @Override
    public void add(int index, E element) {
        checkValidIndex(index);
        Node<E> prevNode = getNode(index-1);
        Node<E> newNode = new Node<E>(null, null, element);
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
        removeNode(curNode);
        return curNode.element;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> curNode = head; curNode != tail; curNode = curNode.next) {
                if (curNode.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> curNode = head; curNode != tail; curNode = curNode.next) {
                if (o.equals(curNode.element))
                    return index-1;
                index++;
            }
        }
        System.out.println("Object doesn't exist");
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastPos = size;
        if (o == null) {
            for (Node<E> curNode = tail; curNode != null; curNode = curNode.prev) {
                if (curNode.element == null)
                    return lastPos;
                lastPos--;
            }
        } else {
            for (Node<E> curNode = tail; curNode != null; curNode = curNode.prev) {
                if (o.equals(curNode.element))
                    return lastPos;
                lastPos--;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() { return new FBWDIterator(); }

    @Override
    public ListIterator<E> listIterator(int index) { return new FBWDIterator(index); }

    @Override
    public List<E> subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException("Not supported yet."); }
    
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

    // Inner Classes
    private class Node<E>{
        E element;
        Node<E> next;
        Node<E> prev;
        Node(Node<E> previous, Node<E> next, E element){
            this.prev = previous;
            this.next = next;
            this.element = element;
        }
        void update(Node<E> previous, Node<E> next, E element){
            this.prev = previous;
            this.next = next;
            this.element = element;
        }
    }
    
    private class FWDIterator implements Iterator<E>{
        Node<E> curNode;
        boolean afterMove;
        FWDIterator(){
            curNode = DLinkedList.this.head.next;
            afterMove = false;
        }
        @Override
        public boolean hasNext() {
            return curNode !=  DLinkedList.this.tail;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = curNode.element;
            curNode = curNode.next;
            afterMove = true;
            return element;
        }

        @Override
        public void remove() {
            if(!afterMove) return;
            Node<E> prevNode = curNode.prev;
            removeNode(prevNode);
            afterMove = false;
        }
    }
    
    private class FBWDIterator extends FWDIterator implements ListIterator<E>{
        int curIndex;
        MoveType moveType;
        FBWDIterator() {
            super();
            curIndex = 0;
            moveType = MoveType.NEXT; //default
            afterMove = false;
        }
        FBWDIterator(int index){
            moveType = MoveType.NEXT; 
            
            if((index < 0) || (index > DLinkedList.this.size)){
                String message = String.format("Invalid index (=%d)", index);
                throw new IndexOutOfBoundsException(message);
            }
            //Assign values to curIndex and curNode
            if((DLinkedList.this.size - index ) < DLinkedList.this.size/2 ){
                curNode = DLinkedList.this.tail;
                curIndex = DLinkedList.this.size;
                while(curIndex > index){
                    curIndex -= 1;
                    curNode = curNode.prev;
                }
            } else{
                curNode = DLinkedList.this.head;
                curIndex = -1;
                while(curIndex < index){
                    curIndex += 1;
                    curNode = curNode.next;
                }
            } 
        }

        @Override
        public E next() {
            E e = super.next();
            curIndex += 1;
            moveType = MoveType.NEXT; 
            return e;
        }
        @Override
        public void remove() {
            if(!afterMove) return;
            Node<E> removedNode;
            if(moveType == MoveType.NEXT){
                removedNode = curNode.prev;
            }
            else{
                removedNode = curNode;
                curNode = curNode.next;
            }
            removeNode(removedNode);
            afterMove = false;
        }
        
        @Override
        public boolean hasPrevious() {
            return curNode.prev != DLinkedList.this.head;
        }

        @Override
        public E previous() {
            curNode = curNode.prev;
            curIndex -= 1;
            moveType = MoveType.PREV; 
            afterMove = true;
            return curNode.element;
        }

        @Override
        public int nextIndex() {
            return this.curIndex;
        }

        @Override
        public int previousIndex() {
            return curIndex -1;
        }

        @Override
        public void set(E e) {
            if(!afterMove) return;
            if(moveType == MoveType.NEXT){
                Node<E> prevNode = curNode.prev;
                prevNode.element = e;
            } else {
                curNode.element = e;
            }
        }

        @Override
        public void add(E e) {
            if(!afterMove) return;
            if(moveType == MoveType.NEXT){
                Node<E> prevNode = curNode.prev; //go to prev
                Node<E> newNode = new Node<>(null, null, e);
                insertLnewR(prevNode.prev, newNode, prevNode);
            } else {
                Node<E> newNode = new Node<>(null, null, e);
                insertLnewR(curNode.prev, newNode, curNode);
                curNode = curNode.prev; // to new node
            }
        }
    }
}

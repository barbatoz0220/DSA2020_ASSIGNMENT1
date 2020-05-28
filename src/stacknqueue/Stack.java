/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacknqueue;

import java.util.EmptyStackException;
import java.util.List;
import list.DLinkedList;

public class Stack<T> {
    private DLinkedList<T> list;
    public Stack() {
        this.list = new DLinkedList<>();
    }

    public void push(T item) {
        list.add(item);
    }

    public T pop() {
        int len = list.size();
        T item = peek();
        list.remove(len - 1);
        return item;
    }

    public T peek() {
        int len = list.size();
        if (len == 0)
            throw new EmptyStackException();
        return list.get(len-1);
    }

    public boolean remove(T item) {
        if(list.contains(item)) {
            list.remove(item);
            return true;
        }
        return false; //should remove this line
    }

    public boolean contains(T item) {
        return list.contains(item);
    }

    public boolean empty() {
        return this.list.isEmpty();
    }

    public int size() {
        return this.list.size();
    }
}
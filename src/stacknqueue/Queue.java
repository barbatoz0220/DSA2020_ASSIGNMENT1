/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacknqueue;
import list.DLinkedList;

import java.util.EmptyStackException;
import java.util.List;

/**
 *
 * @author LTSACH
 */
public class Queue<T>{
    private DLinkedList<T> list;
    public Queue(){
        this.list = new DLinkedList<>();
    }

    public void push(T item){
        list.add(item);
    }

    public T pop(){
        T item = peek();
        list.remove(0);
        return item;
    }

    public T peek(){
        if (size() == 0)
            throw new EmptyStackException();
        return list.get(0);
    }

    public boolean contains(T item){
        return list.contains(item);
    }

    public boolean empty(){
        return this.list.isEmpty();
    }

    public int size(){
        return this.list.size();
    }
}
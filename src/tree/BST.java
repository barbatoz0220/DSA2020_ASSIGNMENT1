/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import list.MyArrayList;
import stacknqueue.Queue;
import stacknqueue.Stack;

/**
 *
 * @author LTSACH
 */
public class BST<T> implements IBinarySearchTree<T>, ITreeWalker<T> {
    private int size;
    Node<T> root;
    
    public BST(){
        this.size = 0;
        this.root = null;
    }

    ///
    private int key(T item){
        return item.hashCode();
    }
    private Node<T> add(Node<T> root, T item){
        if(root == null) return new Node<>(item, null, null);
        if(key(item) < key(root.item))
            root.left = this.add(root.left, item);
        else
            root.right = this.add(root.right, item);
        return root;
    }
    ///
    @Override
    public void add(T item) {
        /*YOUR CODE HERE*/
    }

    private Node<T> remove(Node<T> root, Object key) {
        if(root == null) return null;
        if(key.hashCode() < key(root.item)) {
            root.left = remove(root.left, key);
            return root;
        }
        else if(key.hashCode() > key(root.item)){
            root.right = remove(root.right, key);
            return root;
        }
        else{
            /*YOUR CODE HERE*/
            //Remember to decrease size
            return null;//remove this line
        }
    }
    @Override
    public void remove(Object key) {
        remove(root, key);
    }

    private T search(Node<T> root, Object key) {
        if(root == null) return null;
        
        /*YOUR CODE HERE*/
        return null;//remove this line
    }
    @Override
    public T search(Object key) {
        return search(this.root, key);
    }

    @Override
    public int size() {
        return this.size;
    }
    
    public String toString(){
        return this.root.toString();
    }
    public void println(){
        System.out.println(this.toString());
    }

    private void ascendingList(Node<T> root, List<T> list) {
        if(root == null) return;
        ascendingList(root.left, list);
        list.add(root.item);
        ascendingList(root.right, list);
    }
    @Override
    public List<T> ascendingList() {
       /*YOUR CODE HERE*/
       return null; //remove this line
    }

    private void descendingList(Node<T> root, List<T> list) {
        /*YOUR CODE HERE*/
    }
    @Override
    public List<T> descendingList() {
        List<T> list = new LinkedList<>();
        descendingList(root, list);
        return list;
    }

    @Override
    public List<T> dfs() {
        List<T> list = new LinkedList<>();
        
        /*YOUR CODE HERE*/
        return list;
    }

    @Override
    public List<T> bfs() {
        List<T> list = new LinkedList<>();
        
        /*YOUR CODE HERE*/
        return list;
    }

    private void nlr(Node<T> root, List<T> list) {
        /*YOUR CODE HERE*/
    }
    private void lrn(Node<T> root, List<T> list) {
        /*YOUR CODE HERE*/
    }
    private void lnr(Node<T> root, List<T> list) {
        /*YOUR CODE HERE*/
    }
    @Override
    public List<T> nlr() {
       /*YOUR CODE HERE*/
       return null; //remove this line
    }

    @Override
    public List<T> lrn() {
        /*YOUR CODE HERE*/
       return null; //remove this line
    }

    @Override
    public List<T> lnr() {
        /*YOUR CODE HERE*/
       return null; //remove this line
    }
    //
    public class Node<T>{
        T item;
        Node<T> left, right;
        public Node(T data, Node<T> left, Node<T> right){
            this.item = data;
            this.left = left;
            this.right = right;
        }
       
        public String toString(){
            String desc = "";
            if(this.left == null && this.right == null) 
                desc = String.format("(%s)", item);
            if(this.left == null && this.right != null)
                desc = String.format("(%s () %s)", item, this.right.toString());
            if(this.left != null && this.right == null)
                desc = String.format("(%s %s ())", item, this.left.toString());
            if(this.left != null && this.right != null)
                desc = String.format("(%s %s %s)", item, 
                        this.left.toString(), this.right.toString());
            return desc;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

/**
 *
 * @author LTSACH
 */
public interface IMap<K,V> {
    /*
    put(K key, V value): 
    if key is not in the map: 
        + add a mapping key->value to the map
        + return null
    else:
        + associate key with the new value 
        + return the old value
    */
    public V put(K key, V value);
    /*
    get(K key):
    if key in the map: return the associated value
    else: return null
    */
    public V get(K key);
    /*
    remove(K key):
    if key is in the map: remove it from the map, and return its value
    else: return null
    */
    public V remove(K key);
    /*
    remove(K key, V value):
    if maping key->value in the map: remove it and return true
    else: return false
    */
    public boolean remove(K key, V value);
    /*
    containsKey(K key):
    if key is in the map: return true
    else: return false
    */
    public boolean containsKey(K key);
    /*
    containsKey(V value):
    if value is in the map: return true
    else: return false
    */
    public boolean containsValue(V value);
    
    /*
    isEmpty():
    return true if the map is empty
    else: return false
    */
    public boolean empty();
    /*
    size():
    return num of pairs key->value
    */
    public int size();
}

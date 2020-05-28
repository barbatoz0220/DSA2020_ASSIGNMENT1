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
public class HashDemo {
    public static void main(String[] args){
        XHashMap<Integer, Integer> map = new XHashMap<>(20, 0.75f);
        for(int idx=0; idx < 5; idx++){
            map.put(idx, idx);
        }
        map.put(23, 7); map.put(43, 8); map.put(63, 12);
        map.println();
        map.remove(3,3); map.remove(63, 2); map.remove(2);
        map.println();
        System.out.printf("Value:%d is %s.\n", 4, map.containsValue(4)?"found":"not found");
        System.out.printf("Value:%d is %s.\n", 7, map.containsValue(7)?"found":"not found");
        System.out.printf("Value:%d is %s.\n", 14, map.containsValue(14)?"found":"not found");
    }
}

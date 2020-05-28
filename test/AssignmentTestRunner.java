

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.notification.Failure;
/**
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LTSACH
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   list.DLinkedListTest.class,
   list.SLinkedListTest.class,
   list.MyArrayListTest.class,
   stacknqueue.StackTest.class,
   stacknqueue.QueueTest.class,
   stacknqueue.PriorityQueueTest.class,
   hash.XHashMapTest.class
})
class AssignmentTestSuite {   
}  

public class AssignmentTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AssignmentTestSuite.class);
        System.out.println(new String(new char[80]).replace('\0', '-'));
        if(result.wasSuccessful()){
            System.out.println("Congratulation, all the tests are successful!");
        }
        else{
            System.out.println("There are some unsuccessful tests:");
            for (Failure failure : result.getFailures()) {
               System.out.println(failure.toString());
            }
        }
   }
}

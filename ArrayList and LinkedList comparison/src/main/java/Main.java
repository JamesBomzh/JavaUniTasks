import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This program checks the execution time of the main methods of LinkedList and ArrayList.
 */
public class Main {

    public static void main(String[] args) {
        arrayListAddTime obj1 = new arrayListAddTime();
        arrayListRemoveTime obj2 = new arrayListRemoveTime();
        arrayListGetTime obj3 = new arrayListGetTime();
        linkedListAddTime obj4 = new linkedListAddTime();
        linkedListRemoveTime obj5 = new linkedListRemoveTime();
        linkedListGetTime obj6 = new linkedListGetTime();

        obj1.start();
        obj2.start();
        obj3.start();
        obj4.start();
        obj5.start();
        obj6.start();
    }

    /**
     * This thread counts and prints the time it took for the add method of ArrayList to execute 2000 times.
     */
    static class arrayListAddTime extends Thread {

        @Override
        public void run() {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 2000; i++) {
                arr.add(444);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Время выполнения метода add для ArrayList 2000 раз: " + (endTime - startTime));
        }
    }

    /**
     * This thread counts and prints the time it took for the remove method of ArrayList to execute 2000 times.
     */
    static class arrayListRemoveTime extends Thread {
        @Override
        public void run() {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < 2000; i++) {
                arr.add(444);
            }
            long startTime = System.currentTimeMillis();
            for (int i = 1999; i >= 0; i--) {
                arr.remove(i);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Время выполнения метода remove для ArrayList 2000 раз: " + (endTime - startTime));
        }
    }

    /**
     * This thread counts and prints the time it took for the get method of ArrayList to execute 2000 times.
     */
    static class arrayListGetTime extends Thread {
        @Override
        public void run() {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            arr.add(444);
            int temp = 0;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 2000; i++) {
                temp = arr.get(0);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Время выполнения метода get для ArrayList 2000 раз: " + (endTime - startTime));
        }
    }

    /**
     * This thread counts and prints the time it took for the add method of LinkedList to execute 2000 times.
     */
    static class linkedListAddTime extends Thread {
        @Override
        public void run() {
            LinkedList<Integer> list = new LinkedList<Integer>();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 2000; i++) {
                list.add(444);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Время выполнения метода add для LinkedList 2000 раз: " + (endTime - startTime));
        }
    }

    /**
     * This thread counts and prints the time it took for the remove method of LinkedList to execute 2000 times.
     */
    static class linkedListRemoveTime extends Thread {
        @Override
        public void run() {
            LinkedList<Integer> list = new LinkedList<Integer>();
            for (int i = 0; i < 2000; i++) {
                list.add(444);
            }
            long startTime = System.currentTimeMillis();
            for (int i = 1999; i >= 0; i--) {
                list.remove(i);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Время выполнения метода remove для LinkedList 2000 раз: " + (endTime - startTime));
        }
    }

    /**
     * This thread counts and prints the time it took for the get method of LinkedList to execute 2000 times.
     */
    static class linkedListGetTime extends Thread {
        @Override
        public void run() {
            LinkedList<Integer> list = new LinkedList<Integer>();
            list.add(444);
            int temp = 0;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 2000; i++) {
                temp = list.get(0);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Время выполнения метода get для LinkedList 2000 раз: " + (endTime - startTime));
        }
    }
}

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Jake Imyak
 * Quadruplet Sum Assignment for CSE 2331.
 * Readme pdf file for instructions on running in stdlinux
 * 
 */
public class QuadrupletSum {
    //Static class to represent a pair of values 
    static class Pair {
        public int x;
        public int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean quadSum(ArrayList<Integer> arr, int length, int sum) {
        //Hash table implementation of a map containing an integer as a key and a List of pairs as a value
        Map<Integer, List<Pair>> map = new HashMap<>();
        //Traversing through the data set
        for(int i = 0; i < length -1; i++) {
            for(int j = i + 1; j < length; j++) {
                //subtracting the target sum from indexes i and j to see if another pair exists to add to the sum
                int value = sum - (arr.get(i) + arr.get(j));
                //checking if the hash table has the value 
                if(map.containsKey(value)) {
                    for(Pair pair : map.get(value)) {
                        int x = pair.x;
                        int y = pair.y;
                        //checking whether they are not duplicates 
                        if((x != i && x !=j) && (y != i && y != j)) {
                            //prints and returns true
                            System.out.print("Sum is: " + arr.get(i) + "," + arr.get(j) + "," + arr.get(x) + "," + arr.get(y));
                            return true;
                        } 
                    }
                }
                //if no value is mapped to key will add an empty arraylist 
                map.putIfAbsent(arr.get(i) + arr.get(j), new ArrayList<>());
                //adds the pair to the hash table
                map.get(arr.get(i) + arr.get(j)).add(new Pair(i,j)); 
            }
        }
        return false; 
    }

    public static void main(String[] args) {
        int sum;
        //creates a new file object from the QuadSampleData folder and the zeroth command line argument 
        File file = new File("QuadSampleData/" + args[0]);   
       //uses scanner to read in the file
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
        //adds the contents of the data file to an arraylsit 
        ArrayList<Integer> integers = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                integers.add(scanner.nextInt());
            } 
            else {
                scanner.next();
            }
        }
        //gets the target sum from the first command line argument 
        sum = Integer.parseInt(args[1]);
        //calls quadSum
        if(!quadSum(integers, integers.size(), sum)) { 
            System.out.println("The quadruplet sum does not exist.");
        }
        //closes input stream 
        scanner.close();
    }

}
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.io.FileNotFoundException;

public class QuadrupletSum {

    static class Pair {
        public int x;
        public int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean quadSum(ArrayList<Integer> arr, int length, int sum) {

        Map<Integer, List<Pair>> map = new HashMap<>();

        for(int i = 0; i < length -1; i++) {
            for(int j = i + 1; j < length; j++) {
                int val = sum - (arr.get(i) + arr.get(j));

                if(map.containsKey(val)) {
                    for(Pair pair : map.get(val)) {
                        int x = pair.x;
                        int y = pair.y;

                        if((x != i && x !=j) && (y != i && y != j)) {
                            System.out.print("Sum is: " + arr.get(i) + "," + arr.get(j) + "," + arr.get(x) + "," + arr.get(y));
                            return true;
                        } 
                    }
                }
                map.putIfAbsent(arr.get(i) + arr.get(j), new ArrayList<>());
                map.get(arr.get(i) + arr.get(j)).add(new Pair(i,j)); 
            }
        }
        return false; 
    }

    public static void main(String[] args) {
        int[] A = {3, 6, 8, 2, 9, 2, 1, 9, 5};
        int sum = 15;
     
        File file = new File("QuadSampleData/" + args[0]);   
       
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
        ArrayList<Integer> integers = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                integers.add(scanner.nextInt());
            } 
            else {
                scanner.next();
            }
        }

        sum = Integer.parseInt(args[1]);
        if(!quadSum(integers, integers.size(), sum)) { 
            System.out.println("The quadruplet sum does not exist.");
        }
        scanner.close();
    }

}
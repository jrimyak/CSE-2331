import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * kth order statistic project for CSE 2331
 * @author Jake Imyak
 */
public class kthOrder {

    public static class TreeNode {
        int val;
        int count;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    /**
     * 
     * @param root the root of the tree
     * @param data the value associated with the node to be inserted 
     * @return the root of the tree with the inserted node
     */
    public static TreeNode insert(TreeNode root, int data) {
        //creating nodes 
        TreeNode currentNode = root;
        TreeNode parentNode = null;
       
        //go through the tree to find parent node of the data inputted
        while(currentNode != null) {
            //update the parent to the current node
            parentNode = currentNode;

            //if the data is less than the current node go left and update left count,
            //else go to the right 
            if(data < currentNode.val) {
                currentNode.count++;
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        //creates and sets the root if the tree has no elements 
        if(root == null) {
            return new TreeNode(data);
        }

        //makes a new node and gives it the correct parent node
        if(data < parentNode.val) {
            parentNode.left = new TreeNode(data);
        }
        else {
            parentNode.right = new TreeNode(data);
        }
        return root;
    }

    /**
     * 
     * @param root the root of the tree
     * @param k the kth order statistic we are trying to find 
     * @return the value of the kth order statistic 
     */
    public static int kStatistic(TreeNode root, int k) {
        //go through the tree 
        while(root != null) {
            //the root is the kth order statistic 
            if(k == root.count + 1) {
                return root.val;
            //the kth order statistic is in the left subtree
            } else if(k <= root.count) {
                root = root.left;
            //the kth order statistic is in the right subtree 
            } else {
                k = k - root.count -1;
                root = root.right;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        File file1 = new File("KthOrderSample/" + args[0]); 
        File file2 = new File("KthOrderSample/" + args[1]);   
        //uses scanner to read in the file
         Scanner scanner = null;
         Scanner scanner2 = null;
         //assigning scanners to the input files 
         try {
             scanner = new Scanner(file1);
           
         } catch(FileNotFoundException e) {
             System.out.println(e);
         }
         try {
          
            scanner2 = new Scanner(file2);
        
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
        
         //adds the contents of the data file to an array list  
         ArrayList<Integer> data = new ArrayList<>();
         while (scanner.hasNext()) {
             if (scanner.hasNextInt()) {
                 data.add(scanner.nextInt());
             } 
             else {
                 scanner.next();
             }
         }
        //adds the positions to an array list 
         ArrayList<Integer> position = new ArrayList<>();
         while(scanner2.hasNext()) {
             if(scanner2.hasNextInt()) {
                 position.add(scanner2.nextInt());
             }
             else {
                 scanner2.next();
             }
         }
         //inserts the data into a binary search tree
         TreeNode root = null;
         for(int key : data) {
             root = insert(root, key);
         }
         //find the kth order statisitcs and then prints them out 
         int[] answers = new int[position.size()];
         int i = 0;
         for(int pos : position) {
            answers[i] = kStatistic(root, pos);
            i++;
         }
         for(int j=0 ; j< i; j++) {
             System.out.println("" + answers[j]);
            
         }
    }
}
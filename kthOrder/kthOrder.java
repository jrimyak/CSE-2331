import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


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
     * @param root
     * @param key
     * @return
     */
    public static TreeNode insert(TreeNode root, int key) {
        TreeNode curr = root;
        TreeNode parent = null;
        //TreeNode in = new TreeNode(key);
       

        while(curr != null) {
            parent = curr;

            if(key < curr.val) {
                curr.count++;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if(root == null) {
            return new TreeNode(key);
        }

        if( key < parent.val) {
            parent.left = new TreeNode(key);
        }
        else {
            parent.right = new TreeNode(key);
        }
        return root;
    }

    /**
     * 
     * @param root
     * @param k
     * @return
     */
    public static int kStatistic(TreeNode root, int k) {
        while(root != null) {
            if(k == root.count + 1) {
                return root.val;
            } else if(k <= root.count) {
                root = root.left;
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
        
         //adds the contents of the data file to an arraylsit 
         ArrayList<Integer> data = new ArrayList<>();
         while (scanner.hasNext()) {
             if (scanner.hasNextInt()) {
                 data.add(scanner.nextInt());
             } 
             else {
                 scanner.next();
             }
         }

         ArrayList<Integer> position = new ArrayList<>();
         while(scanner2.hasNext()) {
             if(scanner2.hasNextInt()) {
                 position.add(scanner2.nextInt());
             }
             else {
                 scanner2.next();
             }
         }

         TreeNode root = null;
         for(int key : data) {
             root = insert(root, key);
         }
         int[] answers = new int[position.size()];
         int i = 0;
         for(int pos : position) {
            answers[i] = kStatistic(root, pos);
            i++;
         }
      //   System.out.println(position.get(0));
         for(int j=0 ; j< i; j++) {
             System.out.println("" + answers[j]);
            
         }
    }
}
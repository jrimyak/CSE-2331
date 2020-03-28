package kthOrder;

import java.util.LinkedList;
import java.util.*;
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

    public static TreeNode insert(TreeNode root, int key) {
        TreeNode curr = root;
        TreeNode parent = null;

        if(root == null) {
            return new TreeNode(key);
        }

        while(curr != null) {
            parent = curr;

            if(key < curr.val) {
                curr.count++;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if( key < parent.val) {
            parent = parent.left;
        }
        else {
            parent = parent.right;
        }
        return root;
    }

    public int kStatistic(TreeNode root, int k) {
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
        
    }
}
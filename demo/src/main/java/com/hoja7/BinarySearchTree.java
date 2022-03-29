package com.hoja7;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * HDT7 - Data Structures
 * E-Reference: https://stackoverflow.com/questions/11263244/java-how-do-i-implement-a-generic-binary-search-tree
 */

public class BinarySearchTree< T extends Comparable<Association>>  {
    public class TNode<T extends Comparable<T>> {
        T data;
        public TNode<T> left;
        public TNode<T> right;

        public TNode(T data){
            this.data = data;
        }
    }

    private TNode root;

    public TNode getRoot() {
        return this.root;
    }

    public void add(Association data) {
        TNode newNode = new TNode(data);
        if (root == null) {
            root = newNode;
        } else {
            TNode tempNode = root;
            TNode prev = null;
            while (tempNode != null) {
                prev = tempNode;
                if (data.compareTo((Association) tempNode.data) > 0) {
                    tempNode = tempNode.right;
                } else {
                    tempNode = tempNode.left;
                }
            }
            if (data.compareTo((Association) prev.data) < 0) {
                prev.left = (TNode) newNode;
            } else {
                prev.right = (TNode) newNode;
            }

        }
    }
    public void traverseInOrder(TNode root, List<Association> storageList) {
        if (root != null) {
            traverseInOrder(root.left, storageList);
            storageList.add((Association) root.data);
            traverseInOrder(root.right, storageList);
        }

    }

    public void printList(List<Association> list) {
        for (Association item : list) {
            System.out.println(item.key + " - " + item.value);
        }
    }

}

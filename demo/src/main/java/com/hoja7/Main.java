package com.hoja7;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
//http://www.cs.williams.edu/JavaStructures/doc/structure5/structure5/Association.html
//https://stackoverflow.com/questions/52641366/how-to-make-an-object-of-association-comparable-by-only-one-generic-parameter-ty

class Association<K,V>
        extends java.lang.Object
        implements Map.Entry<K,V>, Comparable<Association> {

    K key;
    V value;

    public Association(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        return this.value = value;
    }

    @Override
    public int compareTo(Association o) {
        return key.toString().compareToIgnoreCase(o.key.toString());
    }
}
public class Main {
    public static void main(String[] args) {
        ArrayList<Association> dictionary = new ArrayList<>();
        ArrayList<String> words = readDictionary();
        ArrayList inOrder = new ArrayList();
        String[] divider;
        String tempWord;
        String key = "";
        String value = "";
        Scanner readLine = new Scanner(System.in);

        for (int i = 0; i < words.size(); i++) {
            tempWord = words.get(i);
            tempWord = tempWord.replaceAll("\\(", "");
            tempWord = tempWord.replaceAll("\\)", "");
            divider = tempWord.split(",");
            key = divider[0];
            value = divider[1];
            dictionary.add(new Association(key.toUpperCase(), value.toUpperCase()));
        }
        /*
        (house, casa) (dog, perro) (homework, tarea) (woman, mujer) (town, pueblo) (yes,si)
         */

        BinarySearchTree bTree = new BinarySearchTree<>();
        for (int i = 0; i < dictionary.size(); i++) {
            bTree.add(dictionary.get(i));
        }

        //=============In order traversing===============
        bTree.traverseInOrder(bTree.getRoot(),inOrder);
        bTree.printList(inOrder);
        //===============================================
         
        String text = readText();
        boolean isInDictionary = false;
        String[] splittedText = text.split(" ");
        String newText = "";

        for (int i = 0; i < splittedText.length; i++) {
            isInDictionary = false;
            for (int j = 0; j < dictionary.size(); j++) {
                if (splittedText[i].equals(dictionary.get(j).key)) {
                    newText += dictionary.get(j).value + " ";
                    isInDictionary = true;
                }
            }
            if (isInDictionary == false) {
                newText += "*" + splittedText[i] + "*" + " ";
            }
        }

        System.out.println("\n" + newText);

        /*
        System.out.println("#### Inorder Traversal ####");
        List<Integer> inOrderList = new ArrayList<>();
        bTree.traverseInOrder(bTree.getRoot(), inOrderList);
        bTree.printList(inOrderList);*/
    }

    //E-Reference:
    // https://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
    //https://www.mkyong.com/java8/java-8-foreach-examples/
    public static ArrayList<String> readDictionary() {
        String path = "./Dictionary.txt";
        ArrayList<String> words = new ArrayList<>();

        try (Stream<String> fileStream = Files.lines(Paths.get(path))) {
            fileStream.forEach(item -> {
                words.add(item.toUpperCase());
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static String readText() {
        String path = "./Text.txt";
        String words =  "";
        ArrayList<String> text = new ArrayList<>();

        try (Stream<String> fileStream = Files.lines(Paths.get(path))) {
            fileStream.forEach(item -> {
                text.add(item.toUpperCase());
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < text.size(); i++) {
            words += text.get(i) + " ";
        }
        return words;
    }

}

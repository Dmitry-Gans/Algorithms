package Seminar4;

import java.util.Random;

public class MainBinary {
    public static void main(String[] args) {
        
        BinaryTree<Integer> tree = new BinaryTree<>();

        for (int i = 0; i < 10; i++) {
            tree.add(new Random().nextInt(20));
        }

        tree.print();
    }
}
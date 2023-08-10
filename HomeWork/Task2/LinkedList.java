package HomeWork.Task2;

import java.util.ArrayList;
import java.util.Stack;

public class LinkedList<T extends Comparable<T>> {
    private Node root;
    private int size;

    public void reversArray() {
        reversArray(0, size - 1);
    }

    private void reversArray(int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        T pivot1 = getValue(leftBorder);
        T pivot2 = getValue(rightBorder);
        while (leftMarker <= rightMarker){
            while(getValue(leftMarker).compareTo(pivot1) < 0)
                leftMarker++;
            while(getValue(rightMarker).compareTo(pivot2) > 0)
                rightMarker--;
            if (leftMarker <= rightMarker)
                swap(leftMarker, rightMarker);
                leftMarker++;
                rightMarker--;
        }
    }
    public void add(T value) {
        if (root == null) {
            root = new Node(value);
            size = 1;
            return;
        }
        Node currenntNode = root;
        while (currenntNode.next != null)
            currenntNode = currenntNode.next;
        currenntNode.next = new Node(value);
        size++;
    }
    private Node getNode(int index) {
        if(index < 0 || index >=size)
            throw new IndexOutOfBoundsException("Вы выщли за рамки массива!");
        Node currentNode = root;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode;
    }

    public T getValue(int index) {
        return this.getNode(index).value;
    }
    public void swap(int index1, int index2) {
        if (index1 == index2)
            return;
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size)
            return;
        Node currentNode = root;
        Node node1 = null;
        Node node2 = null;
        for (int i = 0; currentNode != null; i++) {
            if (i == index1)
                node1 = currentNode;
            else if (i == index2)
                node2 = currentNode;
            if (node2 != null && node1 != null)
                break;
            currentNode = currentNode.next;            
        }
        T temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }
    public void quickSort() {
        quickSort(0, size -1);
    }
    private void quickSort(int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        T pivot = getValue((leftMarker + rightMarker) / 2);
        while (leftMarker <= rightMarker) {
            while (getValue(leftMarker).compareTo(pivot) < 0)
                leftMarker++;
            while (getValue(rightMarker).compareTo(pivot) > 0)
                rightMarker--;
            if (leftMarker <= rightMarker) {
                swap(leftMarker, rightMarker);
                leftMarker++;
                rightMarker--;
            }
        }

    if (leftMarker < rightBorder)
        quickSort(leftMarker, rightBorder);
    if (leftBorder < rightMarker)
        quickSort(leftBorder, rightMarker);
    }

    public void print() {
        Node currenntNode = root;
        System.out.print("[ ");
        while (currenntNode != null) {
            System.out.print(currenntNode.value + " ");
            currenntNode = currenntNode.next;
        }
        System.out.println("] Размер: " + size);
    }

    private class Node {
        T value;
        Node next;

        Node() {

        }

        Node(T value) {
            this.value = value;
        }
    }
}
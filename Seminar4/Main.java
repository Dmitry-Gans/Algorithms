package Seminar4;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<>();
        table.add("D", 5);
        table.add("A", 2);
        table.add("F", 9);
        table.add("I", 0);
        table.add("T", 1);
        table.add("Y", 8);
        table.add("K", 3);

        table.print();
    }
    
}

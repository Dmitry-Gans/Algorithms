package HomeWork;

import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
        int[] array = new int[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(10);
        }
        print(array);
        sortPiramid(array);
        print(array);

    }

    // Метод самой пирамидальной сортировки
    public static void sortPiramid(int arr[]) {
        int n = arr.length;
        
        // Находим Родителя
        for (int i = n / 2 - 1; i >= 0; i--)
        // находим для него наследников
            findingСhildren(arr, n, i);
        // i - Самый последний элемент
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0]; // arr[0] - самый главный родитель
            // Меняем родителя с элементом arr[i] в массиве 
            arr[0] = arr[i];
            arr[i] = temp;

            findingСhildren(arr, i, 0);
        }
    }
// Метод нахождения наследников и вычисление Родителя 
// n - длина, i - от какого элемента начинаем(Родитель) 
    public static void findingСhildren(int arr[], int n, int i) { 
        int largest = i; // Инициализируем наибольший элемент как Родитель
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 
        // Если левый наследник существует и больше родителя
        if (left < n && arr[left] > arr[largest])
        // то делаем его Родителем
            largest = left;
        // Если правый наследник существует и больше родителя
        if (right < n && arr[right] > arr[largest])
        // то делаем его Родителем
            largest = right;
        // Если Родитель не равен с наибольшим, то меняем их местами в пирамиде
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            findingСhildren(arr, n, largest);
        }
    }
// Метод выводящий массив
    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

}

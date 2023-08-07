import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[7];
        for (int i = 0; i < array.length; i++)
            array[i] = new Random().nextInt(10);
        int[] array2 = Arrays.copyOf(array, array.length);
        int[] array3 = new int[]{9, 2, 1, 5, 7, 8, 6};
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = array[(leftMarker + rightMarker) / 2]; // Задаем центр(Якорь)
        while (leftMarker <= rightMarker) { // Обязательно задаем условие
            // иначе правый счетчик перейдет налевую сторону, а левый на правую
            while (array[leftMarker] < pivot) // Пока элемент меньше якоря, то 
            // переходим к следующему и ищем тот, который бдует больше(левая часть)
                leftMarker++;
            while (array[rightMarker] > pivot)// Пока элемент больше якоря, то 
            // переходим к следующему и ищем тот, который бдует меньше(правая часть)
                rightMarker--;
            if (leftMarker <= rightMarker) { // Задаем заново проверку
                // чтобы счетчики не перешли свои границы 
                print(array);
                if (leftMarker != rightMarker) { // Если счетчики неравны,
                    // то меняем их местами
                    int temp = array[leftMarker];
                    array[leftMarker] = array[rightMarker];
                    array[rightMarker] = temp;
                }
                // На случаай, если счетчики остановятся на якоре, то будет
                // бесконечная цикличность, поэтому заново смещаем на один,
                // каждый счетчик
                leftMarker++;
                rightMarker--;
        // После того как перестало выполняться условие на 32 строке, опускамся
        // в рекурсию. Так как мы разбили массив на две части, левая(числа
        // меньше якоря) и правая(числа больше якоря)
            }
        }
        // Рекурсивно работаем с левой частью, без якоря 
        if (leftBorder < rightMarker)
            quickSort(array, leftBorder, rightMarker);
        // Рекурсивно работаем с правой частью, без якоря 
        if (leftMarker < rightBorder)
            quickSort(array, leftMarker, rightBorder);
    }

    public static void insertionSort(int[] array) {
        for (int current = 1; current < array.length; current++) {
            int value = array[current];
            int i = current - 1;
            for (; i >= 0; i--) {
                if (value < array[i])
                    array[i + 1] = array[i];
                else
                    break;
            }
            array[i + 1] = value;
        }
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

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }
}
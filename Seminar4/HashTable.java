package Seminar4;

public class HashTable<K, V> {
    // Заполняем массив не более чем на 75%
    private static final double load_size = 0.75;
    // Количество заполненных ячеек
    private int size;
    // Создаем пустой массив из связного списка
    private Bucket<K, V>[] buckets;

    HashTable() {
        // Первое создание рандомное, так как нам не известен хэш код
        // Чем шире спписок, тем меньше шансов, что он пойдет в глубину
        // в одну ячейку 
        buckets = new Bucket[8];
    }
// Метод высчитывания индекса по хэш коду 
    private int calculateIndex(K key) {
        // Чтобы хэш код не был отрицательным, ограничиваем его размером
        // массива
        return Math.abs(key.hashCode() % buckets.length);
    }
// Метод добавлящий ключ и значение
    public boolean add(K key, V value) {
        // Если привышаем заполненость 75%, то запускаем метод
        // изменяющий размер
        if (buckets.length * load_size <= size)
            resize();
        // Высчитываем хеш и его ячейку
        int index = calculateIndex(key);
        // Берем эту самую ячейку и помещаем ее в новый список
        Bucket<K, V> bucket = buckets[index];
        // Если новый список равен null
        if (bucket == null) {
            // То создаем новую ячейку
            bucket = new Bucket<>();
            // И в ту ячейку которая у нас была, закидываем новосозданную 
            buckets[index] = bucket;
        }
        // Иначе добавляем новый ключ и значение
        boolean result = bucket.add(key, value);
        // Если result равен True
        if (result)
            // То size увеличваем на единицу
            size++;
        return result;
    }

    public boolean remove(K key, V value) {
        int index = calculateIndex(key);
        Bucket<K, V> bucket = buckets[index];
        if (bucket == null)
            return false;
        boolean result = bucket.remove(key);
        if (result)
            size--;
        return result;
    }

    public void print() {
        for (var item : buckets) {
            if (item != null) {
                item.print();
                System.out.println();
            } else
                System.out.println("----");
        }
    }
// Метод меняющий размер
    private void resize() {
        // Создаем новый массив и его нужно презаполнить
        // Он сохранит в себе все значения из старого массива
        Bucket<K, V>[] old = buckets;
        // А в самом buckets создаем новый массив, но умножаем
        // его размер на два
        buckets = new Bucket[old.length * 2];
        // Теперь перебираем все элементы в старом массиве и гладем в новый
        for (int i = 0; i < old.length; i++) {
            Bucket<K, V> bucket = old[i];
            // Проверяем каждую ячейку, на пустоту 
            if (bucket == null)
                continue;
            // Обращаемся к Node через backet
            Bucket.Node currentNode = bucket.root;
            // Если корень не пустой
            while (currentNode != null) {
                // Добавляем пару(ключ, значение) через принудительное 
                // преобразование в новый масив
                this.add((K) currentNode.pair.key, (V) currentNode.pair.value);
                // Переходим к следующему элементу 
                currentNode = currentNode.next;
            }
            // После того как прошлиссь по всем элементам, текущий элемент 
            // удаляем
            old[i] = null;
        }
        // Так же удаляем старый массив 
        old = null;
    }
// Приватный метод заполняющий Линкед лист, перебирая все занчения
    private class Bucket<K, V> {
        Node root;
        // Его публичная часть, добавляющая корень и перебирающая 
        // все занчения
        public boolean add(K key, V value) {
            if (root == null) {
                root = new Node(key, value);
                return true;
            }
            Node currentNode = root;
            while (currentNode.next != null)
            // Добавляем проверку, если текущая пара(ключ, значение)
            // равняется тому, который хотим добавить, то выдаем ошибку,
            // чтобы небыло дубликатов
                if (currentNode.pair.key == key)
                    return false;
                else
                    currentNode = currentNode.next;
            currentNode.next = new Node(key, value);
            return true;
        }
        // Удаляющий метод по ключу 
        public boolean remove(K key) {
            if (root == null)
                return false;
            if (root.pair.key == key) {
                root = root.next;
                return true;
            }
            Node currentNode = root;
            while (currentNode.next != null) {
                if (currentNode.next.pair.key == key) {
                    currentNode.next = currentNode.next.next;
                    return true;
                }
                currentNode = currentNode.next;
            }
            return false;
        }
        // Метод нахождения значения по ключу
        public V getValue(K key) {
            if (root == null)
                return null;
            Node currentNode = root;
            while (currentNode != null)
                if (currentNode.pair.key == key)
                    return currentNode.pair.value;
                else
                    currentNode = currentNode.next;
            return null;
        }
        // Метод вывода ответа
        public void print() {
            Node currentNode = root;
            while (currentNode != null) {
                System.out.print("[" + currentNode.pair.key + ";" + currentNode.pair.value + "]");
                currentNode = currentNode.next;
            }
        }
// Создаем класс ноду, с полями pair(класс в котором пара: ключ и 
// значение) и next 
        private class Node {
            Pair pair;
            Node next;
            // Пустой конструктор
            Node() {
            }
            // Конструткор принимающий класс с парой, он может быть пустой
            Node(Pair pair) {
                this.pair = pair;
            }
            // Конструтор принимающий два значения, обьединяя не в пустую пару
            Node(K key, V value) {
                pair = new Pair(key, value);
            }
        }
// Класс обединяющий ключ, значение в пару
        private class Pair {
            K key;
            V value;
            // Пустой конструктор
            Pair() {
            }
            // Конструктор с двумя значениями
            Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }
} 
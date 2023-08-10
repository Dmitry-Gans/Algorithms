package Seminar3;

public class LinkedList<T extends Comparable<T>> {
    private Node root; // Старт/Голова
    private int size;
// Добавляем новую Node - элемент имеющий в себе ссылку на следующий элемент
    public void add(T value) {
        // Проверяем, пустой ли список, чтобы найти хвост
        if (root == null) {
            // Если список пустой, то создаем новую Node со значением
            // передаваемого в аргументе
            root = new Node(value);
            size = 1;
            return;
        }
        // Если список не пустой, то присваеиваем его в текущий Node
        // и начинаем двигаться от него
        Node currentNode = root;
        // Пока следующий элемент не пустой
        while (currentNode.next != null)
        // Делаем его текущим Node(переходим к следующему элементу)
            currentNode = currentNode.next;
        // Если while не срабатывает, то создаем следующий элемент 
        // со значением из аргумента(следующего добавленного)
        currentNode.next = new Node(value);
        size++;
    }
// Метод добавляет значение по индексу
    public void addAt(int index, T value) {
        if (index == 0) {
            Node newNode = new Node(value);
            // Перый элемент смещаем вправо
            newNode.next = root;
            // И на первое место записываем новое значение 
            root = newNode;
            size++;
            return;
        }
        // Если обращаемся не к первому элементу, то нужно найти придедущий
        // С помощью метода getNode обращаемся к предыдущему индексу
        Node prev = getNode(index - 1);
        Node newNode = new Node(value);
        // Делаем подмену значений
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }
// Метод добавляет элементы и сразу сортирует их 
    public void addSorted(T value) {
        // Если список пут
        if (root == null) {
            // То добавляем в него значение из аргумента
            root = new Node(value);
            size++;
            return;
        }
        // Если значение первого элемента >= чем значение аргумента
        if (root.value.compareTo(value) >= 0) {
            // То создаем новую Node с текущим значением 
            Node newNode = new Node(value);
            // И меняем их местами 
            newNode.next = root;
            root = newNode;
            size++;
            return;
        }
    // Если добавляем не в первый элемент, то нужно найти предыдущий элемент
        Node currentNode = root;
        // Пока следующий элемент не пустой 
        while (currentNode.next != null) {
            // и он больше или равен значению из аргумента
            if (currentNode.next.value.compareTo(value) >= 0) {
                Node newNode = new Node(value);
                // Тогда меньший элемент добаляем левее
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                size++;
                return;
            }
            // Если не нашли элемент, переходим на следующий
            currentNode = currentNode.next;
        }
        // Если не нашли элемент во всем списке, то в конец 
        // добавляем значение из аргумента
        currentNode.next = new Node(value);
        size++;
    }
// Метод удаляющий Node(ссылку впереди стоящего)
    public boolean remove(T value) {
        // Если в списке нет значения, выдаем ошибку
        if (root == null)
            return false;
        // Преверяем первый элемент списка, если он ровняется аргументу,
        // то следующий элемент делаем текущим, тем самым удаляя предыдущий
        // и второй элемент становится первым
        if (root.value.compareTo(value) == 0) {
            root = root.next;
            size--;
            return true;
        }
        // Но если хотим удалить не первый элемент, то надо найти 
        // предыдущий элемент от искомого 
        Node currentNode = root;
        // Пока есть следующий элемент
        while (currentNode.next != null) {
            // И он равен заданному в аргументе
            if (currentNode.next.value.compareTo(value) == 0) {
            // То удаляем на него ссылку, перебрасывая ее через один элемент
                currentNode.next = currentNode.next.next;
                size--;
                return true;
            }
            // Если не нашли элемент, то переходим к следующему
            currentNode = currentNode.next;
        }
        // Если перебрали все элементы и не нашли, то выдаем ошибку
        return false;
    }
// Метод удаляющий по индексу
    public void removeAt(int index) {
        // Если индекс равен 0
        if (index == 0) {
            // То второй элемент делаем первым, тем самым удаляя
            // изначальный первый
            root = root.next;
            size--;
            return;
        }
        // Берем основную Node, обращаемся к ее предыдщему индексу
        // и кладем вновую переменную
        Node prevNode = this.getNode(index - 1);
        // перескакиваем ссылкой через элемент, тем самым удаляя его
        prevNode.next = prevNode.next.next;
        size--;
    }
// Метод удаляющий по значению
    public int removeAll(T value) {
        // Задаем переменную, чтобы запомнить старый размер
        int oldSize = size;
        // Проверка на пустоту
        if (root == null) 
            return 0;
        // Пока первый элемент не пустой и значение равно с значением из аргумента
        while (root != null && root.value.compareTo(value) == 0) {
            // то второй элемент ставим на место первого
            root = root.next;
            size--;
        }
        // Если удаляем не первый элемент, то сначала ищем предыдущий
        Node currentNode = root;
        // Пока следующий элемент не пустой
        while (currentNode.next != null) {
            // и его значение равно значению из аргумента
            if (currentNode.next.value.compareTo(value) == 0) {
                // Перескакиеваем через него ссылкой на последующий
                currentNode.next = currentNode.next.next;
                size--;
            } else 
                // Если значение не нашли, то идем к следующему элементу,
                // но только обязательно через else
                currentNode = currentNode.next;
        }
        // Когда удалили нужный элемент, меняем размер и вычитаем новй из
        // сохраненного размера, показывая количество удаленных элементов
        return oldSize - size;
    }
// Приватный метод по индексу ищет саму Node
    private Node getNode(int index) {
        // Проверка на рамки индекса, чтобы не заходить за них.
        if (index < 0 || index >= size) 
        // Если вышли за грани, то выдаем ошибку
            throw new IndexOutOfBoundsException();
        Node currentNode = root;
        // Счетчик цикла поможет нам в поиске значения, в методе getValue
        for (int i = 0; i < index; i++)
        // Переходим к следующему элементу с каждой итерацией
            currentNode = currentNode.next;
        return currentNode;
    }
// Метод поиска ЗНАЧЕНИЙ Node через индекс, чтобы он работал, создаем 
// еще один метод выше getNode
    public T getValue(int index) {
        // Цикл в методе getNode, поможет вывести значение по индексу
        return this.getNode(index).value;
    }
// Метод менябщий значение
    public void setValue(int index, T value) {
        // С помощью метода обращаемся к значению через индекс и кладем в
        // него новое значение
        getNode(index).value = value;
    }
// Метод меняющий местами два индекса
    public void swap(int index1, int index2) {
        // Если индексы равно, делаем ретерн вместо ошибки,  так как 
        // нет смысла их менять местами
        if (index1 == index2) 
            return;
        // Проверка границ для индексов, делаем ретерн вместо ошибки
        // так как метод не очень важный 
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size)
            return;
        // Не оптимизированный вариант, в 2 раза больше шагов,
        // так как первый индекс будет искаться от первой Node и второй тоже 
        // Node node1 = getNode(index1);
        // Node node2 = getNode(index2);

        // Оптимизированный вариант, в два раза меньше шагов, так как после
        // того как нашли первый индекс, второй ищется от позиции первого, 
        // а не от первой Node 
        Node currentNode = root;
        Node node1 = null;
        Node node2 = null;
        for (int i = 0; currentNode != null; i++) {
            // Если i и первый индекс равны
            if (i == index1) 
                // То мы нашли первый индекс
                node1 = currentNode;
            // Если i и второй индекс равны
            else if (i == index2) 
                // То мы нашли второй индекс
                node2 = currentNode;
            // Если мы нашли два индекса, а об этом нам говоритт условие,
            // что переменные не пустые, то нет смысла дальше перебирать 
            // список и останавливаем цикл 
            if (node2 != null && node1 != null) 
                break;
            // Если мы не нашли элемент, то переходим к следующему
            currentNode = currentNode.next;
        }
        // После того как нашли два индекса, меняем их местами
        T temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }
// Публичный метод быстрой сортировки обращающийся к приватному
    public void quickSort() {
        // Задаем границы, левой стороны и правой 
        quickSort(0, size - 1);
    }
// Приватный метод быстрой сортировки
    private void quickSort(int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        // Узнаем якорь, левый индекс + правый индекс / 2
        T pivot = getValue((leftMarker + rightMarker) / 2);
        // Пока левый маркер меньше правого
        while (leftMarker <= rightMarker) {
            // Пока левый маркер меньше якоря
            while (getValue(leftMarker).compareTo(pivot) < 0) 
                leftMarker++;
            // Пока правый маркер больше якоря
            while (getValue(rightMarker).compareTo(pivot) > 0) 
                rightMarker--;
            // Повторное условие, если левый маркер меньше правого
            if (leftMarker <= rightMarker) {
                // Меняем местами с помощью метода swap
                swap(leftMarker, rightMarker);
                leftMarker++;
                rightMarker--;
            }
        }
        // После того как закончили разбивку на две стороны(Левая и правая),
        // Запускаем для каждой стороны рекурсию со своими якорями
        // Для правой стороны
        if (leftMarker < rightBorder) 
            quickSort(leftMarker, rightBorder);
        // Для левой стороны
        if (leftBorder < rightMarker) 
            quickSort(leftBorder, rightMarker);
    }
// Печатающий метод
    public void print() {
        Node currentNode = root;
        System.out.print("[ ");
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
            // Переходим к следующему элементу
            currentNode = currentNode.next;
        }
        System.out.println("]  size: " + size);
    }
// Создаем приватный класс Node
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
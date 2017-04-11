import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;
    
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
//    
//    private Node Node(Item item) {
//        Node node = new Node();
//        node.item = item;
//        return node;
//    }
    
    public Deque() {
        this.first = null;
        this.last = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return (this.first == null);
    }
    
    public int size() {
        return size;
    }
    
    private void addInitialNode(Item item) {
        first = new Node();
        first.item = item;
        this.first.previous = null;
        this.first.next = null;
        this.last = this.first;
        size++;
    }
    
    public void addFirst(Item item) {
        if(item == null) {
            throw new java.lang.NullPointerException();
        }
        
        if(size == 0) {
            this.addInitialNode(item);
        }  
        else {
            Node oldFirst = this.first;
            this.first = new Node();
            this.first.item = item;
            this.first.previous = null;
            this.first.next = oldFirst;
            oldFirst.previous = this.first;
            size++;
        }
    }
    
    public void addLast(Item item) {
        if(item == null) {
            throw new java.lang.NullPointerException();
        }
        
        if(size == 0) {
            this.addInitialNode(item);
        }  
        else {
            Node oldLast = this.last;
            this.last = new Node();
            this.last.item = item;
            this.last.next = null;
            this.last.previous = oldLast;
            oldLast.next = this.last;
            size++;
        }
    }
    
    public Item removeFirst() {
        Node oldFirst = this.first;
        this.first = oldFirst.next;
        Item oldFirstItem = oldFirst.item;
        this.first.previous = null;
        oldFirst = null;
        size--;
        return oldFirstItem;
    }
    
    public Item removeLast() {
        Node oldLast = this.last;
        this.last = oldLast.previous;
        Item oldLastItem = oldLast.item;
        last.next = null;
        oldLast = null;
        size--;
        return oldLastItem;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() { return current != null; }
        public void remove() { }
        public Item next() {
            if(current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("A");
        deque.addFirst("B");
        deque.addLast("C");
        deque.addLast("D");
    }
}
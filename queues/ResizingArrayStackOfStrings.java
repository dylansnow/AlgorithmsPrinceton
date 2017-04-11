/**
 * Auto Generated Java Class.
 */
public class ResizingArrayStackOfStrings {
   private String[] s;
   private int N = 0;
   
   public ResizingArrayStackOfStrings() {
       s = new String[1];
   }
   
   public boolean isEmpty() {
       return N == 0;
   }
   
   public String pop() {
       String item = s[--N];
       s[N] = null;
       // Halve array when quarter full (always between 25 & 100%)
       if (N > 0 && N == s.length / 4)
           resize(s.length/2);
       return item;
   }
   
   public void push(String item) {
       // Double array size when 100% full
       if (N == s.length) resize(2 * s.length);
       s[N++] = item;
   }
   
   private void resize(int capacity) {
       String[] copy = new String[capacity];
       for(int i = 0; i < N; i++)
           copy[i] = s[i];
       s = copy;
   }
}

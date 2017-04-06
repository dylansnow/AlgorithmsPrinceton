import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private edu.princeton.cs.algs4.WeightedQuickUnionUF weightedUnion;
   private int numOfCols; // == numOfRows
   private boolean[] open;
   private boolean[] full;
   
   public Percolation(int n) {
       if (n <= 0)
           throw new java.lang.IllegalArgumentException();
       weightedUnion = new WeightedQuickUnionUF((n * n) + 2);
       open = full = new boolean[(n * n) + 2]; // Automatically set to false
       numOfCols = n;
       open[0] = open[open.length - 1] = true; // Set virtual nodes to open
       full[0] = true;
   }
   
   private int indexOfRowAndCol(int row, int col) {
       if(row > numOfCols || col > numOfCols || row <= 0 || col <= 0)
           return -1;
       return ((row - 1) * numOfCols) + col;
   }
   
//   private int rowFromIndex(int index) {
//       if(index >= this.open.length - 1 || index <= 0)
//           return -1;
//       return ((index - 1) / numOfCols);
//   }
//   
//   private int colFromIndex(int index) {
//       if(index >= this.open.length - 1 || index <= 0)
//           return -1;
//       return ((index - 1) / numOfCols);
//   }
   
   public void open(int row, int col) {
       if(row > numOfCols || col > numOfCols || row <= 0 || col <= 0)
           throw new java.lang.IndexOutOfBoundsException();
       
       int indexToSite = indexOfRowAndCol(row, col);
       int indexToSiteAbove = indexOfRowAndCol(row - 1, col);
       int indexToSiteBelow = indexOfRowAndCol(row + 1, col);
       int indexToSiteLeft = indexOfRowAndCol(row, col - 1);
       int indexToSiteRight = indexOfRowAndCol(row, col + 1);
       
       // Open site
       open[indexToSite] = true;
       
       // Union with site above if exists and open, or virtual top site
       if(row == 1) {
           weightedUnion.union(indexToSite, 0);
           full[indexToSite] = true;
       }
       else if (indexToSiteAbove != -1 && this.isOpen(row - 1, col) && !this.weightedUnion.connected(indexToSite, indexToSiteAbove)) {
           weightedUnion.union(indexToSite, indexToSiteAbove);
           if(this.isFull(row - 1, col)) {
               full[indexToSite] = true;
           }
       }
       
       // Union with site below if exists and open, or virtual bottom site
       if(row == numOfCols) {
           weightedUnion.union(indexToSite, this.open.length - 1);
       }
       else if (indexToSiteBelow != -1 && this.isOpen(row + 1, col) && !this.weightedUnion.connected(indexToSite, indexToSiteBelow)) {
           weightedUnion.union(indexToSite, indexToSiteBelow);
           if(this.isFull(row + 1, col)) {
               full[indexToSite] = true;
           }
           
           // Additional check to say that virtual site below is full if percolated
           if(this.percolates())
               full[full.length - 1] = true;
       }
       
       // Union with site to the left if exists and open
       if (indexToSiteLeft != -1 && this.isOpen(row, col - 1) && col != 1 && !this.weightedUnion.connected(indexToSite, indexToSiteLeft)) {
           weightedUnion.union(indexToSite, indexToSiteLeft);
           if(this.isFull(row , col - 1)) {
               full[indexToSite] = true;
           }
       }
       
       // Union with site to the right if exists and open
       if (indexToSiteRight != -1 && this.isOpen(row, col) && col != 1 && !this.weightedUnion.connected(indexToSite, indexToSiteRight)) {
           weightedUnion.union(indexToSite, indexToSiteRight);
           if(this.isFull(row, col + 1)) {
               full[indexToSite] = true;
           }
       }
   }

   public boolean isOpen(int row, int col) {
       if(row > numOfCols || col > numOfCols || row <= 0 || col <= 0)
           throw new java.lang.IndexOutOfBoundsException();
       
       return open[indexOfRowAndCol(row, col)] ;
   }
   
   public boolean isFull(int row, int col) {
       if(row > numOfCols || col > numOfCols || row <= 0 || col <= 0)
           throw new java.lang.IndexOutOfBoundsException();
       
       return full[indexOfRowAndCol(row, col)] && weightedUnion.connected(indexOfRowAndCol(row, col), 0);
   }
   
   public int numberOfOpenSites() {
       int openCount = 0;
       for (int i = 0; i < open.length; i++) {
           if (open[i]) {
               openCount++;
           }
       }
       
       openCount -= 2; // For the virtual nodes at top and bottom
       return openCount;
   }
   
   public boolean percolates() {
       return weightedUnion.connected(0, open.length - 1);
   }
   
   public static void main(String[] args) {
   
   }
}

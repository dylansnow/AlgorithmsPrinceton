// Integer array id[] of size N
// Interpretation: id[i] is parent of i
// Root of i is id[id[id[...id[i]...]]]
// Trees can get too tall! Find operation expensive
// Initialise: N
// Union: lg N
// Connected: lg N
public class WeightedQuickUnionUF {
 private int[] id;
 private int[] sz; // Number of objects in the tree rooted at i
 
 public WeightedQuickUnionUF(int N) {
  id = new int[N];
  for(int i = 0; i < N; i++) {
   id[i] = i;
   sz[i] = 1;
  }
 }
 
 private int root(int i) {
  // Root is found when the value at that index is the same as the node
  while (i != id[i]) {
   i = id[i];
  }
  return i;
 }
 
 public boolean connected(int p, int q) {
  return root(p) == root(q);
 }
 
 public void union(int p, int q) {
  int i = root(p);
  int j = root(q);
  if(i == j) return;
  if(sz[i] < sz[j]) { 
   id[i] = j;
   sz[j] += sz[i];
  }
  else {
   id[j] = i; 
   sz[i] += sz[j];
  }
 }
}

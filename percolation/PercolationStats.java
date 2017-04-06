import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    double[] percolationThresholds;
    int numOfTrials;
    
    public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException();
        
        percolationThresholds = new double[trials];
        numOfTrials = trials; 
        
        for(int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while(!percolation.percolates()) {
                int row = StdRandom.uniform(n - 1) + 1;
                int col = StdRandom.uniform(n - 1) + 1;
                if(percolation.isFull(row, col))
                    percolation.open(row, col); // Opens a random site uniformly distributed between 1 and n * n
            }
            
            percolationThresholds[i] = (double)percolation.numberOfOpenSites() / (double)(n * n);
        }
    }
    
    public double mean() {
        return StdStats.mean(percolationThresholds);
    }
    public double stddev() {
        return StdStats.stddev(percolationThresholds);
    }
    public double confidenceLo() {
        return this.mean() - ((1.96 * this.stddev()) / Math.sqrt((double)numOfTrials));
    }
    public double confidenceHi() {
        return this.mean() + ((1.96 * this.stddev()) / Math.sqrt((double)numOfTrials));
    }

    public static void main(String[] args) {
        if(args.length != 2)
            return;
        
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }
}
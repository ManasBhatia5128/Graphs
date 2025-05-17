public class CountingGraphs {
    static long count(int n) {
        // code here
        long edges = n * (n - 1) / 2; // since each edge is between two nodes, we are basiclly selecting 2 nodes out
                                      // of n nodes
        return (long) Math.pow(2, edges); // for each edge, it can be there or it cannot be there, there are two
                                          // possiblites for each edge so 2 ^ edges
    }
}

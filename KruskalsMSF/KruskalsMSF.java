
import java.util.Comparator;
import java.io.File;
import java.util.Scanner;
import java.util.PriorityQueue;
/**
 * @author Jake Imyak
 * Implementation of Kruskals Algorithm for CSE 2331
 */
public class KruskalsMSF {

    /**
     * Class for creating the Union Find data structure 
     * 
    */
    private static class UnionFind {
        //arrays to hold the parent and rank of each vertex
        private int[] parent;
        private int[] rank;

        /**
         * Construtor for the Union Find data structure 
         * @param n or the number of vertices  
         */
        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
        } 
        
        /**
         * Create a new set with element vertex
         * @param vertex the vertex to create a new set with 
         */
        public void makeSet(int vertex) {
            this.parent[vertex] = vertex;
        }

        /**
         * Path compression version of find set 
         * @param vertex the set we are trying to find will contain this vertex
         * @return reference to a represenative element of the set containing vertex
         */
        public int findSet(int vertex) {

            int res = vertex;
            if(this.parent[vertex] == vertex) {
                return res;
            } else {
                res = this.findSet(this.parent[vertex]);
                this.parent[vertex] = res;
                return res;
            }

            
        }

        /**
         * The union of the sets containing vertex1 and vertex2 
         * @param vertex1 to find the set containing vertex1
         * @param vertex2 to find the set containing vertex2
         */
        public void unionByHeight(int vertex1, int vertex2) {

            int vertex1Root = this.findSet(vertex1);
            int vertex2Root = this.findSet(vertex2);

            if(this.rank[vertex1Root] > this.rank[vertex2Root]) {
                this.parent[vertex2Root] = vertex1Root;
            } else if(this.rank[vertex1Root] == this.rank[vertex2Root]) {
                this.parent[vertex2Root] = vertex1Root;
                this.rank[vertex1]++;
            } else {
                this.parent[vertex1Root] = vertex2Root;
            }
        }
            
        
    }

    /**
     * Comparator for the weights of the edges on the graph 
     */
    private static class EdgeWeightComparator implements Comparator<String> {
        @Override
        public int compare(String edge1, String edge2) {
            int result = 0;
            //finding the two edges
            int edge1Parsed = edge1.indexOf(":");
            int edge2Pased = edge2.indexOf(":");
            //finding the two weights 
            int weight1 = Integer.parseInt(edge1.substring(edge1Parsed +1));
            int weight2 = Integer.parseInt(edge2.substring(edge2Pased +1));
            //checking the whether the weight is less than or greater than or equal then returning the result
            if (weight1 < weight2){
                result = -1;
            } else if (weight1 > weight2) {
                result = 1;
            }

            return result;

        }
    }

    public static void main(String[] args) {

        try {

            //finding the file from command line
            File graph = new File("sampleGraphs/" + args[0]);
            //Creating a scanner to read the graph and count the edges 
            Scanner graphRead = new Scanner(graph);
            Scanner edgeCounter = new Scanner(graph);


            int numEdges = -1;
            //count the amount of edges in the graphs until you read the bottom of the file
            while(edgeCounter.hasNextLine()) {
                numEdges++;
                edgeCounter.nextLine();
            }

            //the number of vertices 
            int numVertices = graphRead.nextInt();
            //intializing a union find data structure with n number of vertices 
            UnionFind unionFind = new UnionFind(numVertices);
            //make n number of vertices 
            for(int i = 0; i < numVertices; i++) {
                unionFind.makeSet(i);
            }
            //initaliz ethe comparator to compare the edge weights 
            Comparator<String> weightCompare = new EdgeWeightComparator();
            //creating a prioritty queue to hold the min values 
            PriorityQueue<String> minQueue = new PriorityQueue<>(numEdges, weightCompare);

            //Scanner working correctly now since we went from int to string to read input
            graphRead.nextLine();

            //adding all of the edges into the min queue
            while(graphRead.hasNext()) {
                String edge = graphRead.nextLine();
                minQueue.add(edge);
            }

            //add edges into minimum spanning tree using kruskals algorithm until MST has n-1 edges
            int edgeCtr = 0;
            while(!minQueue.isEmpty() && edgeCtr < numVertices-1) {
                //remove the edge 
                String edge = minQueue.remove();
                //find the pos in the string of the comma and colon
                int commaPos = edge.indexOf(',');
                int colonPos = edge.indexOf(':');
                //find the soruce and destination 
                int source = Integer.parseInt(edge.substring(0, commaPos));
                int destination = Integer
                        .parseInt(edge.substring(commaPos + 1, colonPos));
                //check if they are in the same set
                if (unionFind.findSet(source) != unionFind.findSet(destination)) {
                    //print and union the set of source and destination
                    System.out.println(edge);
                    unionFind.unionByHeight(source, destination);
                    //increment the number in the MST
                    edgeCtr++;
                }
            }
            //close the scanners
            graphRead.close();
            edgeCounter.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
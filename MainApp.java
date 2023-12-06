import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("input.txt");
        Scanner in = new Scanner(fr);

        LinkedList<VertexNode> vertexLinkedList = new LinkedList<VertexNode>();
        String buffer = "";
        String key = "";
        String adjacent = "";
        int weight = 0;

        while (in.hasNextLine()) {
            buffer = in.nextLine().trim();
            if (buffer.length() == 1) {
                vertexLinkedList.addLast(new VertexNode(buffer)); 
            } else {
                key = buffer.substring(0, 1);
                adjacent = buffer.substring(2,3);
                weight = Integer.parseInt(buffer.substring(4,buffer.length()));
                //System.out.println(key + " " + adjacent);
                insertAdjacent(key, adjacent, weight, vertexLinkedList);
            }
        }

        printVertices(vertexLinkedList);
        VertexNode start = vertexLinkedList.getFirst();

        dijkstraAlgorithm(start, vertexLinkedList);
        in.close();

        
    }

    public static void insertAdjacent(String key, String adjacent, int weight, LinkedList<VertexNode> vertexLinkedList){
        for (VertexNode vertex1 : vertexLinkedList) {
            if (vertex1.getKey().equals(key)){
                for(VertexNode vertex2: vertexLinkedList){
                    if(vertex2.getKey().equals(adjacent)){
                        vertex1.addAdjacent(new Edge(vertex1, vertex2, weight));
                        break;
                    }
                }
            }
        }
    }

    public static void printVertices(LinkedList<VertexNode> vertexLinkedList){
        for (VertexNode vertex : vertexLinkedList) {
            vertex.printAdjacent();
        }
    }

    public static void dijkstraAlgorithm(VertexNode startingNode, LinkedList<VertexNode> vertexLinkedList){
        int[] distance = new int[vertexLinkedList.size()];
        int previous = 0;
        boolean[] visited = new boolean[vertexLinkedList.size()];
        
        //Default values
        distance[0] = 0;
        visited[0] = true;
        for(int i = 1; i < distance.length; i++){
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        int smallest = 0;
        VertexNode current = startingNode;

        int x =0;
        while(x<8){
            System.out.println("CURRENT VERTEX: " + current.getKey());
            
            int j = 0;
            //VISITING CHECK
            for(VertexNode visitUpdate: vertexLinkedList){
                if (current.getKey().equals(visitUpdate.getKey())){
                    visited[j] = true;
                }
                j++;
            }
            for(int i = 0; i < distance.length; i++){
                System.out.println(vertexLinkedList.get(i).getKey() + " " + visited[i]);
            }

            //UPDATING DISTANCE
            for(Edge edge: current.adjacentNodes){
                int i = 0;
                for(VertexNode node: vertexLinkedList){
                    if(node.getKey().equals(edge.getKey2()) && edge.getWeight() + smallest < distance[i]){
                        distance[i] = previous + edge.getWeight();
                    }
                    i++;
                }
            }
            for(int i = 0; i < distance.length; i++){
                System.out.println(vertexLinkedList.get(i).getKey() + " " + distance[i]);
            }

            int nextRoute = Integer.MAX_VALUE;

            for(int i = 0; i < distance.length; i++){
                if(visited[i] == false && distance[i] < nextRoute){
                    nextRoute = distance[i];
                    current = vertexLinkedList.get(i);
                }
            }
            for(int i = 0; i < visited.length; i++){
                if(current.getKey().equals(vertexLinkedList.get(i).getKey())){
                    previous = distance[i];
                }
            }
            x++;
        }
        
        

        
    }
}
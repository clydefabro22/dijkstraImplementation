import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("input.txt");
        Scanner in = new Scanner(fr);
        Scanner in2 = new Scanner(System.in);

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
                insertAdjacent(key, adjacent, weight, vertexLinkedList);
            }
        }

        System.out.println("DIJKSTRA'S ALGORITHM");
        System.out.println("Pick a node to start on: ");
        String inputNode;
        VertexNode startingNode = null;
        while(true){
            inputNode = in2.nextLine();
            for(VertexNode findingStartingNode: vertexLinkedList){
                if(findingStartingNode.getKey().equals(inputNode)){
                    startingNode = findingStartingNode;
                    break;
                }
            }
            if(startingNode != null){
                break;
            }
        }

        dijkstraAlgorithm(startingNode, vertexLinkedList);
        in.close();
        in2.close();
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
        
        //Default of starting node
        int k = 0;
        for(VertexNode node: vertexLinkedList){
            if(startingNode.getKey().equals(node.getKey())){
                distance[k] = 0;
                visited[k] = true;
                break;
            }
            k++;
        }
        //Default values of everything else
        for(int i = 0; i < distance.length ; i++){
            if(i != k){
                distance[i] = Integer.MAX_VALUE;
                visited[i] = false;
            }
        }

        int smallest = 0;
        VertexNode current = startingNode;
    
        //The Algorithm at Work. Stops when the last node unvisited has distance of infinity
        while(true){
            
            int j = 0;
            //VISITING CHECK
            for(VertexNode visitUpdate: vertexLinkedList){
                if (current.getKey().equals(visitUpdate.getKey())){
                    visited[j] = true;
                }
                j++;
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

            //NEXT ROUTE
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
            if(nextRoute == Integer.MAX_VALUE)
                break;
        }

        //Printing
        int l = 0;
        System.out.println("Starting Vertex: " + startingNode.getKey());
        for(VertexNode node: vertexLinkedList){
            if(!node.getKey().equals(startingNode.getKey())){
                System.out.print(node.getKey() + " ");
                if(distance[l] == Integer.MAX_VALUE){
                    System.out.println("Infinity");
                } else {
                    System.out.println(distance[l]);
                }
            }
            l++;
        }
    }
}
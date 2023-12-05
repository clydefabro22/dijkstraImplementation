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
                System.out.println(key + " " + adjacent);
                insertAdjacent(key, adjacent, weight, vertexLinkedList);
            }
        }

        printVertices(vertexLinkedList);
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

    public static void dijkstraAlgorithm(){

    }
}
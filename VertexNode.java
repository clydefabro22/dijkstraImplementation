import java.util.LinkedList;

public class VertexNode {
    String key;
    protected LinkedList<Edge> adjacentNodes;

    public VertexNode(String key){
        this.key = key;
        this.adjacentNodes = new LinkedList<Edge>();
    }
    
    public void addAdjacent(Edge edge){
        adjacentNodes.addLast(edge);
    }

    public String getKey(){
        return key;
    }

    public void printAdjacent(){
        System.out.print(key + " - ");
        for (Edge edge : adjacentNodes) {
            System.out.print(edge.getKey2() + "/" + edge.getWeight() + ", ");
        }
        System.out.println();
    }

    public Edge getNext(){
        return adjacentNodes.getFirst();
    }
}

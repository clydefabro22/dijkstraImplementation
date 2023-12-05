public class Edge {
    protected VertexNode vertex1;
    protected VertexNode vertex2;
    protected int weight;
    public Edge(VertexNode vertex1, VertexNode vertex2, int weight){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public String getKey1(){
        return vertex1.getKey();
    }
    public String getKey2(){
        return vertex2.getKey();
    }
    public int getWeight(){
        return weight;
    }
}

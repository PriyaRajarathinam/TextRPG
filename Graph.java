
public class Graph{
    List[]neighbour;
    int NIL=-1;
    int[] color,parent,discover,finish,time,size,order;
    public Graph(int order){
        color=new int[order]; parent=new int[order]; disover=new int[order]; finish=new int[order]; time=new int[order]; size=new int[order];
        neighbour=new List[order];
        for(int i=0;i<getOrder();i++){
            neighbour=new List();
        } 
        this.order=order;
    }
    public int getOrder(){
        return order;
    }
    public int getSize(){
        return size;
    }
    public int getParent(int u){
        if(1<=u && u<= getOrder(G)){
            return parent[u];
        }else{
            return NIL;
        }
    }
    public int getDiscover(int u){
        if(1 <=u && u<=getOrder()){
            return discover[u];
        }else{
            return NIL;
        }
    }
    public int getFinish(int u){
        if(1 <=u && u <= getOrder(G)){
            return finish[u];
        }else{
            return NIL;
        }
    }
    void addEdge(int u,int v){

    }
    void addArc(int u,int v){
        if(1 <=u && u<= getOrder() && 1<=v && v<=getOrder()){
            if (neighbour[u].length()==0){
                neighbour[u].append(v);
                size++;
                return;
            }
            neighbour[u].moveFront();
            int j =1;
            while(neighbour[u].index()!=-1 && j<neighbour[u].length()){
                if(v>neighbour[u].get()){
                    neighbour[u].moveNext();
                }
                j++;
            }
            if(v<neighbour[u].front()){
                neighbour[u].prepend(v);
            }else if(neighbour[u].index()==-1){
                neighbour[u].append(v);
            }else{
                if(v>neighbour[u].get()){
                    neighbour[u].insertAfter(v);
                }else{
                    neighbour[u].insertBefore(v);
                }
            }
            size++;
        }
    }
}

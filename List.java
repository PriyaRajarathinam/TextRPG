//  Priya Rajarathinam 
//  prajarat 
//  PA3

public class List{
    Node front,back,cursor;
    int size,index;

    public List(){
        this.front=null;
        this.back=null;
        this.cursor=null;
        this.size=0;
        this.index=-1;
    }

    public int length(){
        return size;
    }

    public int index(){
        if(cursor!=null){
            return index;
        }else{
            return -1;
        }

    }

    public int front(){
        if(length()>0){
            return front.item;        
        }else{
            throw new RuntimeException("List Error: front() called on empty list");
        }
          
    }

    public int back(){
        if(length()>0){
            return back.item;        
        }else{
            throw new RuntimeException("List Error: back() called on empty list");
        }
       
    }

    public int get(){
        if(length()>0 && index>=0){
            return cursor.item;
        }else{
            throw new RuntimeException("List Error: get() called on undefined cursor");
        }
    }

    public boolean equals(List L){
        L.moveFront();
        moveFront();
        if(L.length()!=length()){
            return false;
        }
        for(int i=0;i<length();i++){
            if(get()!=L.get()){
                return false;
            }
            L.moveNext();
            moveNext();
        }
        return true;
    }

    void clear(){
        size=0;
        front=null;
        back=null;
        index=-1;
    }

    void moveFront(){
        if(length()!=0){
            cursor=front;
            index=0;
        }
    }

    void moveBack(){
        if(length()!=0){
            cursor=back;
            index=length()-1;
        }
    }

    void movePrev(){
        if(cursor!=null && index!=0){
            cursor=cursor.prev;
            index--;
        }else if(cursor!=null && index==0){
            cursor=null;
            index=-1;
        }

    }

    void moveNext(){
        if(cursor!=null && index!=length()-1){
            cursor=cursor.next;
            index++;
        }else if(cursor!=null && index==length()-1){
            cursor=null;
            index=-1;
        }  

    }

    void prepend(int data){
        Node n=new Node(data);
        if(length()==0){
            front=n;
            back=n;
        }else{
            front.prev=n;
            n.next=front;
            front=n;
        }
        if(index()!=-1){
            index++;
        }
        size++;
    }
    
    void append(int data){
        Node n=new Node(data);
        if(length()==0){
            front=n;
            back=n;
        }else{
            back.next=n;
            n.next=null;
            n.prev=back;
            back=n;
        }
        size++;
        //System.out.println("last elemt is "+ back);
    }

    void insertBefore(int data){
        if(length()>0 && index()>=0){
            Node n=new Node(data);
            if(index()==0){
                prepend(data);
            }else{
                n.prev=cursor.prev;
                n.next=cursor;
                cursor.prev.next=n;
                cursor.prev=n;
                size++;
                index++;
            }
          
            
        }else{
            throw new RuntimeException("List Error: insertBefore() called on undefined cursor");
        }
       
    }

    void insertAfter(int data){
        if(length()>0 && index()>=0){
            Node n=new Node(data);
            if(index()==length()-1){
                append(data);
            }else{
                n.next=cursor.next;
                n.prev=cursor;
                cursor.next.prev=n;
                cursor.next=n;
                size++;
            }
           
        }else{
            throw new RuntimeException("List Error: insertAfter() called on undefined cursor");
        }
       
    }

    void deleteFront(){
        if(length()>0){
            if(length()==1){
                front=null;
                back=null;
            }else{
                front=front.next;
            }
            if(index()==0){
                index=-1;
                cursor=null;
            }
            if(index()!=-1){
                index--;
            }
        size--;
        }else{
            throw new RuntimeException("List Error: deleteFront() called on empty list");
        }
    }

    void deleteBack(){
        if(length()>0){
            if(length()==1){
                front=null;
                back=null;
            }else{
                back=back.prev;
               // back.next=null;
            }
            if(index()==length()-1){
                index=-1;
                cursor=null;
            }
        size--;   
        }else{
            throw new RuntimeException("List Error: deleteBack() called on empty list");    
        }
    }

    void delete(){
        if(length()>0 && index()>=0){
            if(index()==length()-1){
                deleteBack();
            }else if(index()==0){
                deleteFront();
            }else{
                cursor.prev.next=cursor.next;
                cursor.next.prev=cursor.prev;
                size--;
            }
          cursor=null;
          index=-1;

        }else{
            throw new RuntimeException("List Error: delete() called on undefined cursor");
        }
    }

    public String toString(){
        String s="";
        moveFront();
        for(int i =0;i<length();i++){
            s+=get();
            s+=" ";
            moveNext();
        }
        return s;
    }

    protected class Node{
        int item;
        Node prev;
        Node next;

        Node(Object i){
            this.item=i;
            this.next=null;
            this.prev=null;

        }
        public String toString(){
            return String.valueOf(item);
        }
    }

}
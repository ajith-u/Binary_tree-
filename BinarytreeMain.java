import java.util.*;

class Node{
  private int value;
  private Node left,right; //left-address of min obj,//right-address of max obj

  Node(){ 
    left=null;
    right=null;
  }

  Node(int v){  
    value=v;
    left=null;
    right=null;
  }

  void setleft(Node l){ left=l; }

  void setright(Node r){ right=r; }

  void setvalue(int v){ value=v; }

  int getvalue(){ return value; }

  Node getleft(){ return left; }

  Node getright(){ return right; }
  
}

class Binarytree{
  Node head;

  Binarytree(){
    head = null;
  }

  //insert the value structure of binary
  boolean setter(int v){
    Node tmp = new Node(v); //temporary node creates for add new value to binary tree 
    boolean setted=false; //chect if the value add in the tree and retruns is added
    if(tmp!=null){          //check memory capacity
      if(head==null){      
        head=tmp;     //store first object(center obj of tree)
      }
      else{
        Node tmp2= head;  //to retrive data aldready in binary tree 
        while(!setted){
          if(tmp2.getvalue()<=v){     //check current obj is lesser than or equal to user given value
            if(tmp2.getright()==null){  // check current object right side branch is null
              tmp2.setright(tmp);     // add the new obj in the tree
              setted=true;            // successfully added
            }
            else
              tmp2=tmp2.getright();  //if aldready is there obj then transfer next obj
          }
          else{                        //if  current obj is gretter than to user given value
            if(tmp2.getleft()==null){    // check current object left side branch is null 
              tmp2.setleft(tmp);        // add the new obj in the tree
              setted=true;              // successfully added
            }
            else
              tmp2=tmp2.getleft();      //if aldready is there obj then transfer next obj
          }
        }
      }
    }
    else  System.out.println("Bounding Error!");
    // if(setted)  System.out.println("successfully setted");  
    return setted;
  }
  
  boolean findvalue(int fv){      // find the value in the tree
    Node tmp = head;             // to retrive data in binary tree
    boolean found=false;         // check value is found
    if(tmp!=null){              //atleast tree had one obj
      do{
        int v = tmp.getvalue();   //get the value current obj
        found = (v==fv);         //check user given value is equal to current obj value
        if(!found && v<=fv) tmp=tmp.getright();    //if value is greater then move to right branch in the tree
        else if(!found)     tmp=tmp.getleft();     //if value is lesser then move left branch
      }while((!found && tmp!=null));  //terminate the loop either found the value or reach last branch 
    }
    return found;    //retruns boolean
  }

  // delete node in the binary tree
  boolean deletebranch(int dv){
    boolean deleted = false;
    Node tmp = head;   //store if the dv if found;
    Node tmp2=tmp;     //only for delete_value node left and right is null because of should change before node rightlink to be null
    while(!deleted && tmp!=null){    //not to be deleted and not to be an end
      int v=tmp.getvalue();          
      if(v==dv){                       //current obj == user given value
        Node left_tmp =  tmp.getleft();     //delete_value node left link
        Node right_tmp = tmp.getright();;   // delete_value node right link

        /* below code define:
          Node to delete has no left child.
          Set parent of deleted node to right child of deleted node.
        */
        if(left_tmp==null && right_tmp != null){
          tmp.setvalue(right_tmp.getvalue());
          tmp.setright(right_tmp.getright());
          tmp.setleft(right_tmp.getleft());
        }

        /* below code define:
          Node to delete has no right child.
          Set parent of deleted node to left child of deleted node.
        */
        else if(left_tmp!=null && right_tmp==null){
          tmp.setvalue(left_tmp.getvalue());
          tmp.setright(left_tmp.getright());
          tmp.setleft(left_tmp.getleft());
        }

        /* below code define:
          Node to delete has two children.
          find largest node value in left subtree.
        */
        else if(left_tmp!=null && right_tmp!=null){
          //only this block left_tmp and right_tmp to use for traversing
          left_tmp=tmp.getleft();
          if(left_tmp.getright()==null){    
            tmp.setvalue(left_tmp.getvalue());
            tmp.setleft(left_tmp.getleft());
           }
          else{              
            right_tmp=left_tmp.getright();
            while(right_tmp.getright()!=null) {
              left_tmp=right_tmp;
              right_tmp = right_tmp.getright();
            }
            if(right_tmp.getleft()!=null) left_tmp.setright(right_tmp.getleft());
            else left_tmp.setright(null);
            tmp.setvalue(right_tmp.getvalue());
          }
        }
        else{  //no child so only deleted it
          if(tmp2==tmp) head=null;
          else if(tmp2.getright()==tmp) tmp2.setright(null);
          else tmp2.setleft(null);
         };
        deleted=true;
      }
      else{
        tmp2=tmp;
        if(v<=dv) tmp=tmp.getright();  //user given value more than or equal to current node value 
        else   tmp=tmp.getleft();      //user given value lesser than current node value
      }
    }
    return deleted;
  }
  
  
  void print(Node tmp){ //disaply all values int the tree using recursion
    boolean left=false;  //removes duplicate print
    if(tmp!=null){     
      if(tmp.getleft()!=null){
        print(tmp.getleft());
        System.out.println(tmp.getvalue());
        left = true;
      }
      if(tmp.getright() != null){
        if(!left) System.out.println(tmp.getvalue());
        print(tmp.getright());      
      }
      if(tmp.getleft()==null && tmp.getright()==null){
        System.out.println(tmp.getvalue());
      }
    }
  }
}





public class BinarytreeMain{
  public static void main(String[] args){
    Binarytree link=new Binarytree();  //store the first objectt 
    Scanner kb = new Scanner(System.in); 

    // int[] n ={50,60,65,45,34,33,10,1,20,36,44};
    // int[] n ={50,40,30,45,55,60,65};
    int[] n ={45,34,33,10,1,20,36,44,50,46,45,47,51,55,54,53,100,65};
    for(int i:n){
      link.setter(i);
    }

    link.print(link.head);
    // int k;
    // do{  
    //   System.out.println("enter -999 press terminate");
    //   k = kb.nextInt();
    //   if(findvalue(k)) System.out.println(link.deletebranch(k));
    //   else System.out.println("it is not a memeber of this branch");
    // }while(k != -999);
    
  }
}
#include <iostream>

using namespace std;

class Node{
  int value;
  Node *left,*right; //left-address of min obj,//right-address of max obj

  public:

  Node(){ 
    left=right=NULL;
  }

  Node(int v){  
    value=v;
    left=right=NULL;
  }

  void setleft(Node* l){ left=l; }

  void setright(Node* r){ right=r; }

  void setvalue(int v){ value=v; }

  // void setPosition(int v){ position=p; }

  int getvalue(){ return value; }

  // int getPosition(){ return position; }

  Node* getleft(){ return left; }

  Node* getright(){ return right; }
  
};


class Avl{
  Node* root;

  public:

  Avl(){ root=NULL; }

  Avl(Node *r){ root=r; }

  Node* getroot(){ return root; }

  int insert(int n, Node* tmp){
    int position=0;
    if(tmp->getvalue()<=n){
      if(tmp->getright()==NULL){
        Node* tmp2=new Node(n);
        tmp->setright(tmp2);
        return 3;
      }
      position = insert(n,tmp->getright());
      if(tmp->getleft()!=NULL) return position+1;
    }
    else{
      if(tmp->getleft()==NULL){
        Node* tmp2=new Node(n);
        tmp->setleft(tmp2);
        return 3;
      }
      position = insert(n, tmp->getleft()) + 1;
      if(tmp->getright()!=NULL) return position+1;
    }

    if(position==3){
      Node *temp[3];
      for(int i=0;i<3;i++){
        temp[i]=tmp;
        tmp = (tmp->getright()!=NULL)?tmp->getright():tmp->getleft();
      }

      int v[3]={temp[0]->getvalue(),temp[1]->getvalue(),temp[2]->getvalue()};
      if(v[0]<=v[1] && v[1]<=v[2]){
        temp[0]->setvalue(v[1]);
        temp[1]->setvalue(v[0]);
        temp[0]->setleft(temp[1]);
        temp[0]->setright(temp[2]);
      }
      else if(v[0]<=v[2] && v[2]<=v[1]){
        temp[0]->setvalue(v[2]);
        temp[2]->setvalue(v[0]);
        temp[0]->setleft(temp[2]);
        temp[0]->setright(temp[1]);
      }
      else if(v[0]>v[1] && v[1]>v[2]){
        temp[0]->setvalue(v[1]);
        temp[1]->setvalue(v[0]);
        temp[0]->setleft(temp[2]);
        temp[0]->setright(temp[1]);
      }
      else if(v[0]>v[2] && v[2]>v[1]){
        temp[0]->setvalue(v[2]);
        temp[2]->setvalue(v[0]);
        temp[0]->setleft(temp[1]);
        temp[0]->setright(temp[2]);
      }
      for(int i=1;i<3;i++){
        temp[i]->setleft(NULL);
        temp[i]->setright(NULL);
      }
    }
      
    return position+1;
  }

  void insert(int v){
    Node* tmp=new Node(v);
    root=tmp;
  }


  void print(Node* tmp){ //disaply all values int the tree using recursion
    bool left=false;  //removes duplicate print
    if(tmp != NULL){     
      if(tmp->getleft()!=NULL){
        print(tmp->getleft());
        cout<<tmp->getvalue()<<endl;
        left = true;
      }
      if(tmp->getright() != NULL){
        if(!left) cout<<tmp->getvalue()<<endl;
        print(tmp->getright());      
      }
      if(tmp->getleft()==NULL && tmp->getright()==NULL){
        cout<<tmp->getvalue()<<endl;
      }
    }
  }
  
};


int main() {
  
  Avl tree;
  
  int n[] ={2,1,25,20,30};
  for(int i=0;i<15;i++){
     if(i==0)
      tree.insert(n[0]);
     else
      tree.insert(n[i],tree.getroot());
  }
  tree.print(tree.getroot());

}
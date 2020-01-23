/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tdas;
 
    import java.util.Comparator;

/**
 *
 * @author Victor Alvarado
 */
public class AVLTree<E>{
    private NodeAVL<E> root;
    private final Comparator<E> f;
    private NodeAVL<E> equilibrio; 
    private int size;
    private String cadena="";
    private StringBuilder sb;
    
    public AVLTree(Comparator<E> f) {
        this.root=null;
        this.f = f;
        this.equilibrio=null;
    }
  
    public NodeAVL<E> getRoot() {
        return root;
    }

    
    public boolean isEmpty() {
	return root == null;
    }
  
    
    public boolean add(E data){
        if(data==null){
            return false;
        }
        this.root=add(data,root,null,'c');
        return true;
    }
    
    private NodeAVL<E> add(E data,NodeAVL<E> nodo, NodeAVL<E> padre, char lado){
        if(nodo==null){
            nodo=new NodeAVL<>(data);
            nodo.setParent(padre);
            nodo.setLado(lado);
            this.equilibrio=nodo;
            size++;
        }else if(f.compare(data, nodo.getData())>0){
            nodo.setRigth(add(data,nodo.getRigth(),nodo,'r'));
        
        }else if(f.compare(data, nodo.getData())<0){
            nodo.setLeft(add(data,nodo.getLeft(),nodo,'l'));
        }
        return nodo;
    }
    
   
    public E max(){
        return max(root);
    }
    
    private E max(NodeAVL<E> nodo){
        if(nodo==null){return null;}
        if(nodo.getRigth()==null){return nodo.getData();}
        return max(nodo.getRigth());
    }
    
    public E min(){
        return min(this.root);
    }
    
    private E min(NodeAVL<E> p){
        if(p==null){
            return null;
        }
        if(p.getLeft()==null){
            return p.getData();
        }
        return max(p.getLeft());
    }
    
    public boolean searchNode(E data){
        if(data==null || isEmpty()){return false;}
        return searchNode(data, this.root);
    }
    
    private boolean searchNode(E data, NodeAVL<E> p){
        if(p==null){
            return false; 
        }
        if(f.compare(data, p.getData())>0){
            return searchNode(data, p.getRigth());
        }else if(f.compare(data, p.getData())<0){
            return searchNode(data,p.getLeft());
        }else{
            return true;
        }
    }
    public boolean contains(E data){
        if(data == null)
            return false;
        return contains(data,root);
    }
    
    private boolean contains(E data, NodeAVL<E> p){
        if(p==null)
            return false;
        else if(p.getData().equals(data))
            return true;
        boolean l = contains(data, p.getLeft());
        return (l!=false)? l:contains(data, p.getRigth());
        
    }
    
    public boolean remove(E data){
        if(data==null || isEmpty()){
            return false;
        }
        
        this.root = remove(data, this.root);
        size--;
        return true;
    }
    
    private NodeAVL<E> remove(E data, NodeAVL<E> p){
        if(p==null){return p;
        }else if(f.compare(data,p.getData())>0){
            equilibrio=p;
            p.setRigth(remove(data,p.getRigth()));
        }else if(f.compare(data,p.getData())<0){
            equilibrio=p;
            p.setLeft(remove(data,p.getLeft()));
        }else if(p.getLeft()!=null && p.getRigth()!=null){
            p.setData(min(p.getRigth()));
            equilibrio=p; 
            p.setRigth(remove(p.getData(),p.getRigth()));
        }else if(p.getRigth()!=null){// mod
            p.getRigth().setParent(p.getParent());
            p.getRigth().setLado(p.getLado());
            p.getRigth().setEq(p.getEq());
            p=p.getRigth();
            equilibrio=p;
        }else if(p.getLeft()!=null){//mod
            p.getLeft().setParent(p.getParent());
            p.getLeft().setLado(p.getLado());
            p.getLeft().setEq(p.getEq());
            p=p.getLeft();
            equilibrio=p;
        }else{
            p=null;
        }
        return p;
    }
    
    public int size(){
        return this.size;
    }
    
    public int height(){
        return height(this.root);
    }
    
    private int height(NodeAVL<E> p){
        if(p==null){
            return 0;
        }
        return 1+Integer.max(height(p.getLeft()),height(p.getRigth()));
    }
    
    private void actualizarEq(NodeAVL<E> parent){
        if(parent!=null){
            parent.setEq(height(parent.getRigth())-height(parent.getLeft()));
        }
    }
    
 
    private boolean sameHeight(NodeAVL<E> p){
        if(p==null || p.getParent()==null){
            return false;
        }
        if(p.getLado()=='l'){
            return height(p)==height(p.getParent().getRigth());
        }
        return height(p)==height(p.getParent().getLeft());
    }
       private NodeAVL<E> findNode(){
        return findNode(equilibrio);
    }
    
    private NodeAVL<E> findNode(NodeAVL<E> node){
        if(node==null){return node;}
        if(node.getEq()>1 || node.getEq()<-1){
            return node;
        }
        if(node.getLado()=='l'){
            node.getParent().setEq(node.getParent().getEq()-1);
                
        }else if(node.getLado()=='r'){
            node.getParent().setEq(node.getParent().getEq()+1);
        }
        if(!sameHeight(node)){
            return findNode(node.getParent());
            
        }
        return null;
       
    }
    
    private NodeAVL<E> findUpdate(NodeAVL<E> p){
        if(p==null){return null;}
        actualizarEq(p);
        if(p.getEq()>1 || p.getEq()<-1) {return p;}
        return findUpdate(p.getParent());
        
    }
    
    private void actualizarHijo(NodeAVL<E> p){
        if(p!=null){
            actualizarEq(p);
            actualizarHijo(p.getLeft());
            actualizarHijo(p.getRigth());
        }
    }
    
    private NodeAVL<E> findRemove(){
        actualizarEq(equilibrio);
        return findRemove(equilibrio);
    }
    
    private NodeAVL<E> findRemove(NodeAVL<E> parent){
        if(parent==null)return null;
        else if(parent.getEq()>1 || parent.getEq()<-1){
            return parent;
        }
        else if(parent.getLado()=='r'){
            parent.getParent().setEq(parent.getParent().getEq()-1);
        }
        else if(parent.getLado()=='l'){
            parent.getParent().setEq(parent.getParent().getEq()+1);
        }
        else if(!sameHeight(parent)){
            return findNode(parent.getParent());
            
        }
        return null;
       
    }
    
    

    public boolean balance(NodeAVL<E> sinBalancear){
        NodeAVL<E> p=null;
        NodeAVL<E> q=null;
        sb= new StringBuilder();
        if(sinBalancear == null){
            return true; 
        }
        else if(sinBalancear.getEq()>1){
            p= sinBalancear.getRigth();
            sb.append("r");
            if(p.getEq()<0){
                sb.append("l");
                q= p.getLeft();
            }else{
                sb.append("r");
                q=p.getRigth();
            }
        }else if(sinBalancear.getEq()<-1){
            p=sinBalancear.getLeft();
            sb.append("l");
            if(p.getEq()<=0){
                sb.append("l");
                q=p.getLeft();
            }else{
                sb.append("r");
                q=p.getRigth();
            }
        }
        return balance(sb.toString(), sinBalancear, p, q);
            
    }
 
    private boolean balance(String type, NodeAVL<E> node1, NodeAVL<E> node2, NodeAVL<E> node3) {
        NodeAVL<E> temp = node1;
        if (type.equals("rr")) {
            node2.setParent(node1.getParent());
                        node2.setLado(node1.getLado());
                        if (root == node1) {
                                root = node2;
                        }
                        node1 = node2;
                        if (node1.getLado() == 'r') {
                                node1.getParent().setRigth(node1);
                        } else if (node1.getLado() == 'l') {
                                node1.getParent().setLeft(node1);
                        }
                        temp.setRigth(node1.getLeft());
                        if (node1.getLeft() != null) {
                                node1.getLeft().setLado('r');
                                node1.getLeft().setParent(temp);
                        }
                        node1.setLeft(temp);
                        temp.setParent(node1);
                        temp.setLado('l');
                         actualizarHijo(node1);
                        
        } 
        else if (type.equals("ll")){
                 node2.setParent(node1.getParent());
                        node2.setLado(node1.getLado());
                        if (root == node1) {
                                root = node2;
                        }
                        node1 = node2;
                        if (node1.getLado() == 'l') {
                                node1.getParent().setLeft(node1);
                        } else if (node1.getLado() == 'r') {
                                node1.getParent().setRigth(node1);
                        }
                        temp.setLeft(node1.getRigth());
                        if (node1.getRigth() != null) {
                                node1.getLeft().setLado('l');
                                node1.getLeft().setParent(temp);
                        }
                        node1.setRigth(temp);
                        temp.setParent(node1);
                        temp.setLado('r');
                         actualizarHijo(node1);
                
        }
           
                else if (type.equals("rl")) {
                        node3.setParent(node1.getParent());
                        node3.setLado(node1.getLado());
                        if (root == node1) {
                                root = node3;
                        }
                        node1 = node3;
                        if (node1.getLado() == 'l') {
                                node1.getParent().setLeft(node1);
                        } else if (node1.getLado() == 'r') {
                                node1.getParent().setRigth(node1);
                        }
                        temp.setRigth(node1.getLeft());
                        if (node1.getLeft() != null) {
                                node1.getLeft().setLado('r');
                                node1.getLeft().setParent(temp);
                        }
                        node2.setLeft(node1.getRigth());
                        if (node1.getRigth() != null) {
                                node1.getRigth().setLado('l');
                                node1.getRigth().setParent(node2);
                        }
                        node1.setRigth(node2);
                        node2.setParent(node1);
                        node1.setLeft(temp);
                        temp.setParent(node1);
                        temp.setLado('l');
                         actualizarHijo(node1);
                        }
              
                
                else if (type.equals("lr")) {
                        node3.setParent(node1.getParent());
                        node3.setLado(node1.getLado());
                        if (root == node1) {
                                root = node3;
                        }
                        node1 = node3;
                        if (node1.getLado() == 'r') {
                                node1.getParent().setRigth(node1);
                        } else if (node1.getLado() == 'l') {
                                node1.getParent().setLeft(node1);
                        }
                        temp.setLeft(node1.getRigth());
                        if (node1.getRigth() != null) {
                                node1.getRigth().setLado('l');
                                node1.getRigth().setParent(temp);
                        }
                        node2.setRigth(node1.getLeft());
                        if (node1.getLeft() != null) {
                                node1.getLeft().setLado('r');
                                node1.getLeft().setParent(node2);
                        }
                        node1.setLeft(node2);
                        node2.setParent(node1);
                        node1.setRigth(temp);
                        temp.setParent(node1);
                        temp.setLado('r');
                        actualizarHijo(node1);
        }
        
        return balance(findUpdate(node1));
	}
    
    public boolean balanceRemove(){ //despues de remover balancea el arbol
        NodeAVL<E> sinBalance=findRemove();
        return balance(sinBalance);
        
    }
    
    public boolean balanceAdd(){ //despues de agregar balancea el arbol
        NodeAVL<E> sinBalance=findNode();
        return balance(sinBalance);
        
    }
    
        public String printInOrden(){
        sb=new StringBuilder();
        sb.append("{");
        printInOrden(root);
        cadena=sb.substring(0, sb.length()-1) + "}";
        return cadena;
      
    }
    
    private void printInOrden(NodeAVL<E> nodo){
        if(nodo!=null){
            printInOrden(nodo.getLeft());
            System.out.print(nodo.getData());
            sb.append(nodo.getData()+",");
             printInOrden(nodo.getRigth());
        }
    }
     public String printPreOrden(){
        sb=new StringBuilder();
        sb.append("{");
        printPreOrden(root);
        cadena=sb.substring(0, sb.length()-1) + "}";
        return cadena;
    }
     
    private void printPreOrden(NodeAVL<E> nodo){
        if(nodo!=null){
            System.out.print(nodo.getData());
             sb.append(nodo.getData()+",");
            printPreOrden(nodo.getLeft());
            printPreOrden(nodo.getRigth());
        }
    }
    
    public String printPosOrden(){
        sb=new StringBuilder();
        sb.append("{");
        printPosOrden(root);
        cadena=sb.substring(0, sb.length()-1) + "}";
        return cadena;
    }
    
    private void printPosOrden(NodeAVL<E> nodo){
        if(nodo!=null){
            printPosOrden(nodo.getLeft());            
            printPosOrden(nodo.getRigth());
            System.out.print(nodo.getData());
               sb.append(nodo.getData()+",");
        }
    }
}

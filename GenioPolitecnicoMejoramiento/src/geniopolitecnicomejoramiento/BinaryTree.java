/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

/**
 *
 * @author CltControl
 */
public class BinaryTree <E>{
    NodeBT<E> root;
    
    public BinaryTree(){
        root=null;
    }
    
    public void setRoot(NodeBT<E> root){
        this.root = root;
    }
    
    public boolean isEmpty(){
        return root==null;
    }
    
    public boolean add(E child, E parent){
        NodeBT<E> node= new NodeBT<>(child);
        if (isEmpty() && child!=null && parent==null) {
            root=node;
            return true;
        }
        else if (searchNode(child)==null) {
            NodeBT<E> p=searchNode(parent);
            if (p!=null) {
                 return false;
            }
            else if (p.getLeft()==null) {
                p.setLeft(node);
            }
            else if (p.getRigth()==null) {
                p.setRigth(node);
            }
            else return false;
        }
        return true;  
    }
    
    
    private NodeBT <E> searchNode(E data){
        if (data==null|| isEmpty()) {
            return null;
        }
        return searchNode(data,root);
    }
    
    private NodeBT <E> searchNode(E data,NodeBT<E> p){
        if (p==null) {
            return null;
        }
        else if (p.getData().equals(data)) {
            return p;
        }
        
        NodeBT<E> l=searchNode(data,p.getLeft());
                return (l!=null)? l:searchNode(data,p.getRigth()) ;
    }
    
    public int size(){
        return size(root);
    }
    
    private int size(NodeBT<E> p ){
        if (p==null) {
            return 0;
        }
        return size(p.getLeft())+1+size(p.getRigth());
    }
    
    public boolean remove(E child){
        if (isEmpty() || child==null){
            return false;
        }
        if (root.getData().equals(child)) {
            root=null;
            return true;
        }
        
        NodeBT<E> parent=searchParent(child);
        if (parent==null) {
            return false;
        }
        if (parent.getLeft()!=null && parent.getLeft().getClass().equals(child)) {
            parent.setLeft(null);
        }
        else   {
            parent.setRigth(null);
        }
        return true;
    }
    
    private NodeBT <E> searchParent(E child){
       return   searchParent(child,root);
    }
    
    private NodeBT <E> searchParent(E child,NodeBT<E>node){
        if (node==null ) {
            return null;
        }
        else if(node.getLeft()!=null && node.getLeft().getData().equals(child)|| //verifica izquierda
                node.getRigth()!=null && node.getRigth().getData().equals(child)) {//verifica derecha
            return node;//verifica la rama y sus hijos 
        }
        NodeBT <E> l=searchParent(child,node.getLeft());
        
        return (l!=null)?l:(searchParent(child,node.getRigth()));//retorna el que es diferente de null 
    }   //verdadero:falso

//recorridos de arbol 1) pre orden +ab  2) pos orden ab+  3) en orden  a+b
//1) raiz izq dere
//2) izq  der raiz
//3) izq raiz dere    
    
    public void preOrden() {
        preOrden(root);
    }
    private void preOrden(NodeBT<E> nodo){
        if (nodo!=null) {
            System.out.print(nodo.getData() + "\n");
            preOrden(nodo.getLeft());
            preOrden(nodo.getRigth());
        }
            
    }
    
    public void enOrden() {
        enOrden(root);
    }
    private void enOrden(NodeBT<E> nodo){
        if (nodo!=null) {
            enOrden(nodo.getLeft());
            System.out.print(nodo.getData() + "\n");
            enOrden(nodo.getRigth());
        }
            
    }
    
    public void posOrden() {
        posOrden(root);
    }
    private void posOrden(NodeBT<E> nodo){
        if (nodo!=null) {
            posOrden(nodo.getLeft());
            posOrden(nodo.getRigth());
            System.out.print(nodo.getData());
        }
    }
    
    
    
}
   
    


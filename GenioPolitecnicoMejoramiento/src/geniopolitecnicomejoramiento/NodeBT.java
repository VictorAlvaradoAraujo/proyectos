/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

/**
 *
 * @author Victor
 */
public class NodeBT<E> {
    private E data;
    private  NodeBT<E> left;
    private  NodeBT<E> rigth;
    
    public NodeBT(E data){
        this.data=data;
        left=rigth=null;
    
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public NodeBT<E> getLeft() {
        return left;
    }

    public void setLeft(NodeBT<E> left) {
        this.left = left;
    }

    public NodeBT<E> getRigth() {
        return rigth;
    }

    public void setRigth(NodeBT<E> rigth) {
        this.rigth = rigth;
    }
    
    public boolean isHoja(){
        return this.left == null && this.rigth == null;
    }
     
    
}


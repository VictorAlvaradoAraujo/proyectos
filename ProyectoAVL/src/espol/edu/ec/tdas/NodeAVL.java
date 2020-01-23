 
package espol.edu.ec.tdas;

/**
 *
 * @author Victor Alvarado
 */
public class NodeAVL<E>{
       private int eq; //  equilibrio 
    private NodeAVL<E> parent;
    private NodeAVL<E> izq;  
    private NodeAVL<E> der;  
    private E data;
    private char lado;

    public NodeAVL(E data) {
        eq=0; //equilibrio inicial 0
        parent=izq=der=null;
        this.data = data;
        lado='v'; // r nodo de la derecha, l nodo de la izquierda, v si no tiene lado. sirve para ajustar el factor de equilibrio
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public int getEq() {
        return eq;
    }

    public void setEq(int fe) {
        this.eq = fe;
    }

    public NodeAVL<E> getParent() {
        return parent;
    }

    public void setParent(NodeAVL<E> padre) {
        this.parent = padre;
    }

    public NodeAVL<E> getLeft() {
        return izq;
    }

    public void setLeft(NodeAVL<E> izq) {
        this.izq = izq;
    }

    public NodeAVL<E> getRigth() {
        return der;
    }

    public void setRigth(NodeAVL<E> der) {
        this.der = der;
    }

    public char getLado() {
        return lado;
    }

    public void setLado(char side) {
        this.lado = side;
    }
 
    
    
    
    
    
    
    
    
}

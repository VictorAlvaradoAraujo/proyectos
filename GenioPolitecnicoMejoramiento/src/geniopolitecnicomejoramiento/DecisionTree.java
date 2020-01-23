/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author Jorge Pinargote
 */
public class DecisionTree {

    BinaryTree<DataDST> bt;
    NodeBT<DataDST> lastQuestion;
    NodeBT<DataDST> lastNode;
    
    
    LinkedList<DataDST> ListSave;
    public DecisionTree(){
         this.bt = new  BinaryTree<>();
         this.bt.setRoot(chargeTree(chargeQueue()));
         this.lastQuestion = this.bt.root;
         this.ListSave = new LinkedList<>();
    }
    
    private LinkedList<DataDST> chargeQueue(){
        LinkedList<DataDST> queueDst = new LinkedList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
           br = new BufferedReader(new InputStreamReader(new FileInputStream("datos.txt"),"ISO-8859-1"));
           // Lectura del fichero
           String linea;
           while((linea=br.readLine())!=null){
               queueDst.add(new DataDST(linea));
           }    
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        } 
        
        return queueDst;
    }
    
    private NodeBT<DataDST> chargeTree(LinkedList<DataDST> queue){
        NodeBT<DataDST> nodo = new NodeBT(queue.poll());
        if (nodo.getData().isQuestion()){
            nodo.setLeft(chargeTree(queue));
            nodo.setRigth(chargeTree(queue));
        } 
        return nodo;
    }

    public BinaryTree<DataDST> getBt() {
        return bt;
    }
    
    public NodeBT<DataDST> Si(){
        NodeBT<DataDST> left = lastQuestion.getLeft();
        this.lastNode = left;
        if(left.getData().isQuestion)lastQuestion = left;
        return left;
    }
    
    public NodeBT<DataDST> No(){
        NodeBT<DataDST> rigth = lastQuestion.getRigth();
        this.lastNode = rigth;
        if(rigth.getData().isQuestion)lastQuestion = rigth;
        return rigth;
    }
    
    public void Reiniciar(){
        lastQuestion = bt.root;
        
    }
    
    public void save(String pregunta, String respuesta, String siNo){
        NodeBT<DataDST> Npregunta; 
        NodeBT<DataDST> Nrespuesta;
        
        Npregunta = new NodeBT(new DataDST(pregunta,true));
        Nrespuesta = new NodeBT(new DataDST(respuesta,false));
        
        if(siNo.equalsIgnoreCase("Si")){
            Npregunta.setLeft(Nrespuesta);
            Npregunta.setRigth(this.lastNode);
            
        }else{
            Npregunta.setRigth(Nrespuesta);
            Npregunta.setLeft(this.lastNode);
        }
        
        if(this.lastQuestion.getLeft().equals(this.lastNode)){
            this.lastQuestion.setLeft(Npregunta);
        }
        
        if(this.lastQuestion.getRigth().equals(this.lastNode)){
            this.lastQuestion.setRigth(Npregunta);    
        }
        
        this.bt.preOrden();
    
    }
    
    public void saveOnFile(String file){
        preOrdenSave();
        FileWriter fichero = null;
        PrintWriter pw = null;
        BufferedWriter out = null;
        
        try
        {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "8859_1"));
            //fichero = new FileWriter(file);
            //pw = new PrintWriter(fichero);
            
            for (int i = 0; i < this.ListSave.size(); i++)
                out.write(this.ListSave.get(i).toPrint()+"\n");
            
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              out.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
    
    
        }
    }
    
    
    
    public void preOrdenSave(){
        preOrdenSave(this.bt.root);
    }
    private void preOrdenSave(NodeBT<DataDST> nodo){
        if (nodo!=null) {
            this.ListSave.add(nodo.getData());
            preOrdenSave(nodo.getLeft());
            preOrdenSave(nodo.getRigth());
        }
            
    }
    
    
    
    
    
}

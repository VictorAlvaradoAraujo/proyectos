/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

import java.util.Objects;

/**
 *
 * @author Jorge Pinargote
 */
public class DataDST {
    Boolean isQuestion;
    String text;
    
    public DataDST(String text){
        if(text.length()>3){
            String type = text.substring(0, 2);
            this.text = text.substring(3, text.length());
            isQuestion = false;
            if(type.contains("#P")){
                isQuestion = true;
            }
        }
    }
    
    public DataDST(String text,Boolean isQuestion){
        this.text = text;
        this.isQuestion = isQuestion;
    }

    public Boolean isQuestion() {
        return isQuestion;
    }
    

    @Override
    public String toString() {
        return text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.text);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataDST other = (DataDST) obj;
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }

    
     public String toPrint() {
         
         String type = "#R ";
         if(this.isQuestion){
             type = "#P ";
         }
         
         
        return type + text;
    }
    
    
    
    
    
    
    
}

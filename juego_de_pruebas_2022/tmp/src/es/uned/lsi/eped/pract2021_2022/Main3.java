/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import java.util.Collections;

/**
 *
 * @author Admin
 */
public class Main3 {
    public static void main(String[] args) {
        SparseArrayBTree b = new SparseArrayBTree();
        b.set(9878, "Mundo");
        b.set(8, "Hola");        
        b.set(5, "Ignite");
        b.set(3, "Probando");
        b.set(7, "Putos");
        System.out.println("Tamaño " + b.btree.size());
        IteratorIF iterator = b.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.getNext());
        }
//        b.delete(3);
//        b.delete(6);        
//        System.out.println("luego de eliminar-----------------------------------------------\n");
//        iterator = b.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.getNext());
//        }
//        
//        System.out.println("añadimos---------------------------\n");
//        b.set(3, "probando");
//        System.out.println("Tamaño " + b.btree.size());
//        iterator = b.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.getNext());
//        }
        
    }
}

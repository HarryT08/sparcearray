/*
<<<<<<< HEAD
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uned.lsi.eped.pract2021_2022;

public class main2 {

    public static void main(String[] args) {
        SparseArraySequence sp = new SparseArraySequence();
        sp.set(0, 12);
        sp.set(5, "asda");
        sp.set(2, "lol");
        sp.set(1, 6000);
        
        sp.imprimir();
        
        IndexedPair p = (IndexedPair) sp.get(5);
        System.out.println(p.toString());
        
        sp.delete(1);
        sp.imprimir();
    }
}

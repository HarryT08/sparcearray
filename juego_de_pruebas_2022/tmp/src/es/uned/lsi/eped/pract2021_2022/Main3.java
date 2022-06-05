/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uned.lsi.eped.pract2021_2022;

/**
 *
 * @author Admin
 */
public class Main3 {
    public static void main(String[] args) {
        SparseArrayBTree b = new SparseArrayBTree();
        b.set(6, "Mundo");
        b.set(6, "Putos");
        b.set(7, "Putos");
        b.set(5, "Putos");
        System.out.println(b.size());
    }
}

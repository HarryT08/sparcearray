/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.IteratorIF;

/**
 *
 * @author Admin
 */
public class IteratorIndex<E> implements IteratorIF<E> {

    private NodeIndexedPair<E> cabeza;

    public IteratorIndex(NodeIndexedPair cabeza) {
        this.cabeza = cabeza;
    }

    @Override
    public E getNext() {
        E elem = (E) this.cabeza.getValue();
        this.cabeza = this.cabeza.getNext();
        return elem;
    }

    @Override
    public boolean hasNext() {
        return cabeza != null;
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

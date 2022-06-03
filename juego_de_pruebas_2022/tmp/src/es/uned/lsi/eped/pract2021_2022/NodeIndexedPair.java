/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uned.lsi.eped.pract2021_2022;

/**
 *
 * @author Admin
 */
public class NodeIndexedPair<E> {

    private IndexedPair value;
    private NodeIndexedPair<E> next;

    NodeIndexedPair() {
        this.value = null;
        this.next = null;
    }

    NodeIndexedPair(IndexedPair value) {
        this.value = value;
        this.next = null;
    }

    public IndexedPair getValue() {
        return this.value;
    }

    public void setValue(IndexedPair e) {
        this.value = e;
    }

    public NodeIndexedPair getNext() {
        return this.next;
    }

    public void setNext(NodeIndexedPair n) {
        this.next = n;
    }

}

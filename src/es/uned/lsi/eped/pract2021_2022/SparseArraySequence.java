package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Sequence;

public class SparseArraySequence<E> extends Sequence<E> implements SparseArrayIF<E> {

    protected Sequence sequence;

    public SparseArraySequence() {

    }

    @Override
    public void set(int pos, E elem) {
        IndexedPair pair = new IndexedPair(pos, elem);
        this.insertarAlFinal((E)pair);
    }

    @Override
    public E get(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IteratorIF<Integer> indexIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

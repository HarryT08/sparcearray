package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Sequence;

public class SparseArraySequence<E> extends Sequence<E> implements SparseArrayIF<E> {

    protected Sequence< E > sequence;
    
    public SparseArraySequence() {
        super();
    }
	
    @Override
    public void set(int pos, E elem) {
        this.insertarParesOrdenados(pos, elem);
    }
    public void imprimir(){
        super.imprimir();
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

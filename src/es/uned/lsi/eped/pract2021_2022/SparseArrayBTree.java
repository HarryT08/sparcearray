package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.BTree;
import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;

public class SparseArrayBTree<E> extends Collection<E> implements SparseArrayIF<E> {

    protected BTree<IndexedPair<E>> btree;

    public SparseArrayBTree() {

    }

    /*private Stack<Boolean> num2bin(int n) {
		Stack<Boolean> salida = new Stack<Boolean>();
		if ( n == 0 ) {
			salida.push(false);
		} else {
			while ( n != 0 ) {
				salida.push((n % 2) == 1);
				n = n / 2;
			}
		}
		return salida;
	}*/

    @Override
    public void set(int pos, E elem) {
        System.out.println("");
    }

    @Override
    public E get(int pos) {
        return null;
    }

    @Override
    public void delete(int pos) {
    }

    @Override
    public IteratorIF<Integer> indexIterator() {
        return null;
    }

    @Override
    public boolean contains(E e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IteratorIF<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

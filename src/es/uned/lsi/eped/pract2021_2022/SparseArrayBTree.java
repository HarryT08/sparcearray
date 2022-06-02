package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Sequence;

public class SparseArrayBTree<E> extends Sequence<E> implements SparseArrayIF<E> {

	//protected BTreeIF<IndexedPair<E>> btree;
	
	public SparseArrayBTree() {
		
	}
	
	/*private StackIF<Boolean> num2bin(int n) {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

package es.uned.lsi.eped.pract2021_2022;

<<<<<<< HEAD
=======
import es.uned.lsi.eped.DataStructures.BTree;
>>>>>>> 4b7003bb6c71ddc4a03bbee4b9b51c45cb1eb132
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Sequence;
import es.uned.lsi.eped.DataStructures.Stack;

public class SparseArrayBTree<E> extends Sequence<E> implements SparseArrayIF<E> {

<<<<<<< HEAD
	//protected BTreeIF<IndexedPair<E>> btree;
=======
	protected BTree<IndexedPair<E>> btree;
>>>>>>> 4b7003bb6c71ddc4a03bbee4b9b51c45cb1eb132
	
	public SparseArrayBTree() {
		
	}
	
<<<<<<< HEAD
	/*private StackIF<Boolean> num2bin(int n) {
=======
	private Stack<Boolean> num2bin(int n) {
>>>>>>> 4b7003bb6c71ddc4a03bbee4b9b51c45cb1eb132
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
<<<<<<< HEAD
	}*/

    @Override
    public void set(int pos, E elem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
=======
	}	

    @Override
    public void set(int pos, E elem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> 4b7003bb6c71ddc4a03bbee4b9b51c45cb1eb132
    }

    @Override
    public E get(int pos) {
<<<<<<< HEAD
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> 4b7003bb6c71ddc4a03bbee4b9b51c45cb1eb132
    }

    @Override
    public void delete(int pos) {
<<<<<<< HEAD
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> 4b7003bb6c71ddc4a03bbee4b9b51c45cb1eb132
    }

    @Override
    public IteratorIF<Integer> indexIterator() {
<<<<<<< HEAD
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
=======
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> 4b7003bb6c71ddc4a03bbee4b9b51c45cb1eb132
    }
}

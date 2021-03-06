package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.BTree;
import es.uned.lsi.eped.DataStructures.BTreeIF;
import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.Stack;

public class SparseArrayBTree<E> extends Collection<E> implements SparseArrayIF<E> {

    protected BTree<IndexedPair<E>> btree;

    public SparseArrayBTree() {
        btree = new BTree();
    }

    private Stack<Boolean> num2bin(int n) {
        Stack<Boolean> salida = new Stack<Boolean>();
        if (n == 0) {
            salida.push(false);
        } else {
            while (n != 0) {
                salida.push((n % 2) == 1);
                n = n / 2;
            }
        }
        return salida;
    }

    @Override
    public void set(int pos, E elem) {
        Stack<Boolean> posBin = this.num2bin(pos);
        this.addNode(posBin, btree, pos, elem);
    }

    private void addNode(Stack<Boolean> b, BTreeIF<IndexedPair<E>> root, int pos, E elem) {
        if (b.isEmpty()) {
            if (root.getRoot() == null) {
                this.size++;
            }
            IndexedPair nodo = new IndexedPair(pos, elem);
            root.setRoot(nodo);
            return;
        }
        boolean cmd = b.getTop();
        b.pop();
        if (cmd) {
            if (root.getRightChild() == null) {
                root.setRightChild(new BTree<IndexedPair<E>>());
            }
            this.addNode(b, root.getRightChild(), pos, elem);
        } else {
            if (root.getLeftChild() == null) {
                root.setLeftChild(new BTree<IndexedPair<E>>());
            }
            this.addNode(b, root.getLeftChild(), pos, elem);
        }
    }

    @Override
    public E get(int pos) {
        BTreeIF<IndexedPair<E>> aux = this.buscar(pos);
        if (aux == null) {
            return null;
        } else {
            if (aux.getRoot() == null) {
                return null;
            }
            return aux.getRoot().getValue();
        }
    }

    public BTreeIF<IndexedPair<E>> buscar(int pos) {
        Stack<Boolean> b = this.num2bin(pos);
        return this.buscar(b, btree);
    }

    private BTreeIF<IndexedPair<E>> buscar(Stack<Boolean> b, BTreeIF<IndexedPair<E>> current) {
        if (current == null) {
            return null;
        }
        if (b.size() == 0) {
            return current;
        }
        boolean cmd = b.getTop();
        b.pop();
        if (cmd) {
            return this.buscar(b, current.getRightChild());
        } else {
            return this.buscar(b, current.getLeftChild());
        }
    }

    @Override
    public void delete(int pos) {
        BTreeIF<IndexedPair<E>> r = this.buscar(pos);
        if (r != null && r.getRoot() != null) {
            delete(num2bin(pos), btree);
            this.size--;
        }
    }

    private void delete(Stack<Boolean> b, BTreeIF<IndexedPair<E>> current) {
        if (b.size() == 0) {
            current.setRoot(null);
            return;
        }

        boolean cmd = b.getTop();
        b.pop();
        if (cmd) {
            this.delete(b, current.getRightChild());
            if (current.getRightChild().getRoot() == null && current.getRightChild().getNumChildren() == 0) {
                current.setRightChild(null);
            }

        } else {

            this.delete(b, current.getLeftChild());
            if (current.getLeftChild().getRoot() == null && current.getLeftChild().getNumChildren() == 0) {
                current.setLeftChild(null);
            }

        }
    }

    @Override
    public IteratorIF<Integer> indexIterator() {
        IteratorIF<IndexedPair<E>> elementos = this.btree.iterator(BTreeIF.IteratorModes.BREADTH);
        Queue<Integer> q = new Queue<Integer>();
        return this.getIndexIterator(elementos, q);
    }

    private IteratorIF<Integer> getIndexIterator(IteratorIF<IndexedPair<E>> elementos, Queue<Integer> q) {
        while (elementos.hasNext()) {
            IndexedPair e = elementos.getNext();
            if (e != null) {
                q.enqueue(e.getIndex());
            }
        }
        return q.iterator();
    }

    @Override
    public boolean contains(E e) {
        IteratorIF<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.getNext().equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IteratorIF<E> iterator() {
        IteratorIF<IndexedPair<E>> elementos = this.btree.iterator(BTreeIF.IteratorModes.BREADTH);
        Queue<E> q = new Queue<E>();
        return this.getElementsIterator(elementos, q);
    }

    private IteratorIF<E> getElementsIterator(IteratorIF<IndexedPair<E>> elementos, Queue<E> q) {
        while (elementos.hasNext()) {
            IndexedPair<E> e = elementos.getNext();
            if (e != null) {
                q.enqueue(e.getValue());
            }
        }
        return q.iterator();
    }

    @Override
    public void clear() {
        super.clear();
        this.btree.clear();
    }
}

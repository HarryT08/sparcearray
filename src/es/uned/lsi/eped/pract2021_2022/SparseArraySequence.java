package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;

public class SparseArraySequence<E> extends Collection<E> implements SparseArrayIF<E> {

    private class SparseArrayIterator implements IteratorIF<E> {

        private NodeIndexedPair currentNode;

        SparseArrayIterator() {
            this.currentNode = sequence;
        }

        @Override
        public E getNext() {
            E elem = (E) this.currentNode.getValue().getValue();
            this.currentNode = this.currentNode.getNext();
            return elem;
        }

        @Override
        public boolean hasNext() {
            return this.currentNode != null;
        }

        @Override
        public void reset() {
            this.currentNode = sequence;
        }
    }

    @Override
    public IteratorIF<E> iterator() {
        return (IteratorIF<E>) new SparseArrayIterator();
    }

    @Override
    public IteratorIF<Integer> indexIterator() {
        return new SparseIteratorIndex(this.sequence);
    }

    protected NodeIndexedPair sequence;

    public SparseArraySequence() {
        sequence = null;
    }

    @Override
    public void set(int pos, E elem) {
        this.insertarParesOrdenados(pos, elem);
    }

    private void agregarInicio(IndexedPair element) {
        NodeIndexedPair nuevo = new NodeIndexedPair(element);
        nuevo.setNext(this.sequence);
        this.sequence = nuevo;
        this.size++;
    }

    private void insertarParesOrdenados(int pos, E elem) {
        if (this.size == 0) {
            IndexedPair toInsert = new IndexedPair(pos, elem);
            this.agregarInicio(toInsert);
            return;
        }

        NodeIndexedPair elemento = this.getPair(pos);
        if (elemento == null) {
            IndexedPair aux = new IndexedPair(pos, elem); // ELEMENTO A INSERTAR
            NodeIndexedPair it = this.sequence;
            NodeIndexedPair prev = it;
            IndexedPair x = (IndexedPair) it.getValue();
            while (it != null && pos > x.getIndex()) {
                prev = it;
                it = it.getNext();
                if (it != null) {
                    x = (IndexedPair) it.getValue();
                }
            }
            if (it == prev) {
                this.agregarInicio(aux);
            } else {
                prev.setNext(new NodeIndexedPair(aux));
                prev.getNext().setNext(it);
                this.size++;
            }
        } else {
            elemento.getValue().setValue(elem);
        }
    }

    @Override
    public E get(int pos) {
        for (NodeIndexedPair x = this.sequence; x != null; x = x.getNext()) {
            if (x.getValue().getIndex() == pos) {
                return (E) x.getValue().getValue().toString();
            }
        }
        return null;
    }

    @Override
    public void delete(int pos) {
        if (this.size == 0) {
            return;
        }
        if (this.sequence.getValue().getIndex() == pos) {
            this.sequence = this.sequence.getNext();
            this.size--;
            return;
        }

        NodeIndexedPair actual = this.sequence;
        NodeIndexedPair prev = actual;
        while (actual != null) {
            IndexedPair pair = actual.getValue();
            if (pair.getIndex() == pos) {
                prev.setNext(actual.getNext());
                this.size--;
                return;
            }
            prev = actual;
            actual = actual.getNext();
        }
    }

    @Override
    public boolean contains(E e) {
        for (NodeIndexedPair x = this.sequence; x != null; x = x.getNext()) {
            if (x.getValue().getValue().equals(e)) {
                return true;
            }
        }
        return false;
    }

    private NodeIndexedPair getPair(int pos) {
        for (NodeIndexedPair x = this.sequence; x != null; x = x.getNext()) {
            if (x.getValue().getIndex() == pos) {
                return x;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        super.clear();
        /* Vaciamos la colección */
        this.sequence = null;
        /* La secuencia es vacía */
    }
}

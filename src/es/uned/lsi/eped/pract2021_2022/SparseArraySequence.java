package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;

public class SparseArraySequence<E> extends Collection<E> implements SparseArrayIF<E> {

    protected NodeIndexedPair sequence;

    private class SparseArrayIterator implements IteratorIF {

        private NodeIndexedPair currentNode;

        SparseArrayIterator() {
            this.currentNode = sequence;
        }

        @Override
        public E getNext() {
            E elem = (E) this.currentNode.getValue();
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

    public SparseArraySequence() {
        sequence = null;
    }

    public void set(int pos, E elem) {
        this.insertarParesOrdenados(pos, elem);
    }

    public E get(int pos) {
        SparseArrayIterator it = new SparseArrayIterator();
        while (it.hasNext()) {
            IndexedPair index = (IndexedPair) it.getNext();
            if (index.getIndex() == pos) {
                return (E) index;
            }
        }
        return null;
    }

    public void delete(int pos) {
        if (this.size == 0) {
            return;
        }
        NodeIndexedPair it = this.sequence;
        IndexedPair pair = it.getValue();
        if (pair.getIndex() == pos) {
            this.sequence = this.sequence.getNext();
            this.size--;
            return;
        }

        NodeIndexedPair prev = it;
        while (it != null) {
            pair = it.getValue();
            if (pair.getIndex() == pos) {
                prev.setNext(it.getNext());
                this.size--;
                return;
            }
            prev = it;
            it = it.getNext();
        }
    }

    @Override
    public IteratorIF<Integer> indexIterator() {
        return new IteratorIndex<Integer>();
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public IteratorIF<E> iterator() {
        return new SparseArrayIterator();
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
    }

    public void imprimir() {
        NodeIndexedPair x = this.sequence;
        while (x != null) {
            System.out.println(x.getValue().toString());
            x = x.getNext();
        }
    }
}

package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;

public class SparseArraySequence<E> extends Collection<E> implements SparseArrayIF<E> {

    protected NodeIndexedPair sequence;

    private class SparseArrayIterator implements IteratorIF<String> {

        private NodeIndexedPair currentNode;

        SparseArrayIterator() {
            this.currentNode = sequence;
        }

        @Override
        public String getNext() {
            String elem = (String) this.currentNode.getValue().getValue();
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
    public void clear() {
        super.clear();
        /* Vaciamos la colección */
        this.sequence = null;
        /* La secuencia es vacía */
    }

    public SparseArraySequence() {
        sequence = null;
    }

    @Override
    public void set(int pos, E elem) {
        this.insertarParesOrdenados(pos, elem);
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
        return new IteratorIndex(this.sequence);
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

    @Override
    public IteratorIF<E> iterator() {
        return (IteratorIF<E>) new SparseArrayIterator();
    }

    private NodeIndexedPair getPair(int pos) {
        for (NodeIndexedPair x = this.sequence; x != null; x = x.getNext()) {
            if (x.getValue().getIndex() == pos) {
                return x;
            }
        }
        return null;
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

    public void imprimir() {
        NodeIndexedPair x = this.sequence;
        while (x != null) {
            System.out.println(x.getValue().toString());
            x = x.getNext();
        }
    }
}

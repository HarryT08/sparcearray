package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;

public class SparseArraySequence<E> extends Collection<E> implements SparseArrayIF<E> {
    
    /**
     * Iterador del arreglo sparseArray
     * Permite iterar entre los elementos de la clase SparseArraySequence
     */
    private class SparseArrayIterator implements IteratorIF<E> {

        private NodeIndexedPair currentNode;

        SparseArrayIterator() {
            this.currentNode = sequence;
        }
        
        /**
         * Devuelve el objeto actual y avanza una posición
         * @return E valor del indexedpair
         */
        @Override
        public E getNext() {
            E elem = (E) this.currentNode.getValue().getValue();
            this.currentNode = this.currentNode.getNext();
            return elem;
        }
        
        /**
         * Verifica si el elemento actual es un elemento
         * @return true si hay un elemento false si el elemento es nulo
         */
        @Override
        public boolean hasNext() {
            return this.currentNode != null;
        }
        
        /**
         * Devuelve al inicio del iterador al primer elemento del array
         */
        @Override
        public void reset() {
            this.currentNode = sequence;
        }
    }
    
    /**
     * Devuelve un iterador con los valores de los elementos del SparseArraySequence
     * return IteratorIF<E>
     */
    @Override
    public IteratorIF<E> iterator() {
        return (IteratorIF<E>) new SparseArrayIterator();
    }
    
    /**
     * Devuelve un iterador con los indices de los elementos del SparseArraySequence
     * return IteratorIF<Integer>
     */
    @Override
    public IteratorIF<Integer> indexIterator() {
        return new SparseIteratorIndex(this.sequence);
    }
    
    /**
     * Nodo cabeza del array
     * Cuando se crea el arreglo es null
     */
    protected NodeIndexedPair sequence;
    
    /**
     * Constructor por defecto
     */
    public SparseArraySequence() {
        sequence = null;
    }
    
    /**
     * Método set
     * Este método permite agregar un nuevo elemento al arreglo indexado
     * @param pos indice donde queremos insertar
     * @param elem Información que queremos insertar.
     */
    @Override
    public void set(int pos, E elem) {
        this.insertarParesOrdenados(pos, elem);
    }

    /**
     * Método agregar inicio
     * Permite agregar un nuevo elemento en la cabeza del array
     * @param element IndexedPair con la información del índice o posición y la información insertada.
     */
    private void agregarInicio(IndexedPair element) {
        NodeIndexedPair nuevo = new NodeIndexedPair(element);
        nuevo.setNext(this.sequence);
        this.sequence = nuevo;
        this.size++;
    }
    
    /**
     * Método insertarParesOrdenados
     * Permite agregar un nuevo elemento al array de manera ordenada (Por la posición que llega por parametro)
     * @param pos Posición del elemento.
     * @param elem Información del elemento.
     */
    private void insertarParesOrdenados(int pos, E elem) {
        //Tamaño es 0, lo insertamos al inicio
        if (this.size == 0) {
            IndexedPair toInsert = new IndexedPair(pos, elem);
            this.agregarInicio(toInsert);
            return;
        }
        //Verificamos si existe un elemento en esa posición
        NodeIndexedPair elemento = this.getPair(pos);
        //Si no hay, lo agregamos donde corresponda según la posición
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
        } //Si hay un elemento en esa posición, modificamos el valor.
        else {
            elemento.getValue().setValue(elem);
        }
    }
    
    /**
     * Método get
     * 
     * @param pos Posición del elemento que queremos buscar.
     * @return E Información del indexedPair si la encuentra, de lo contrario retorna null.
     */
    @Override
    public E get(int pos) {
        NodeIndexedPair elemento = this.getPair(pos);
        if(elemento == null){
            return null;
        }else{
            return (E) elemento.getValue().getValue();
        }
//        for (NodeIndexedPair x = this.sequence; x != null; x = x.getNext()) {
//            if (x.getValue().getIndex() == pos) {
//                return (E) x.getValue().getValue().toString();
//            }
//        }
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

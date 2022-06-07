package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;

public class SparseArraySequence<E> extends Collection<E> implements SparseArrayIF<E> {

    /**
     * Iterador del arreglo sparseArray Permite iterar entre los elementos de la
     * clase SparseArraySequence
     */
    private class SparseArrayIterator implements IteratorIF<E> {

        private NodeIndexedPair currentNode;

        SparseArrayIterator() {
            this.currentNode = sequence;
        }

        /**
         * Devuelve el objeto actual y avanza una posición
         *
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
         *
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
     * Iterador de los indices del SparceArraySequence
     */
    private class SparseIteratorIndex implements IteratorIF<Integer> {

        private NodeIndexedPair cabeza;

        SparseIteratorIndex(NodeIndexedPair cabeza) {
            this.cabeza = cabeza;
        }

        @Override
        public Integer getNext() {
            Integer elem = this.cabeza.getValue().getIndex();
            this.cabeza = this.cabeza.getNext();
            return elem;
        }

        @Override
        public boolean hasNext() {
            return cabeza != null;
        }

        @Override
        public void reset() {
            this.cabeza = null;
        }

    }

    /**
     * Devuelve un iterador con los valores de los elementos del
     * SparseArraySequence return IteratorIF<E>
     */
    @Override
    public IteratorIF<E> iterator() {
        return (IteratorIF<E>) new SparseArrayIterator();
    }

    /**
     * Devuelve un iterador con los indices de los elementos del
     * SparseArraySequence return IteratorIF<Integer>
     */
    @Override
    public IteratorIF<Integer> indexIterator() {
        return new SparseIteratorIndex(this.sequence);
    }

    /**
     * Nodo cabeza del array Cuando se crea el arreglo es null
     */
    protected NodeIndexedPair sequence;

    /**
     * Constructor por defecto
     */
    public SparseArraySequence() {
        sequence = null;
    }

    /**
     * Método set Este método permite agregar un nuevo elemento al arreglo
     * indexado
     *
     * @param pos indice donde queremos insertar
     * @param elem Información que queremos insertar.
     */
    @Override
    public void set(int pos, E elem) {
        this.insertarParesOrdenados(pos, elem);
    }

    /**
     * Método agregar inicio Permite agregar un nuevo elemento en la cabeza del
     * array
     *
     * @param element IndexedPair con la información del índice o posición y la
     * información insertada.
     */
    private void agregarInicio(IndexedPair element) {
        NodeIndexedPair nuevo = new NodeIndexedPair(element);
        nuevo.setNext(this.sequence);
        this.sequence = nuevo;
        this.size++;
    }

    /**
     * Método insertarParesOrdenados Permite agregar un nuevo elemento al array
     * de manera ordenada (Por la posición que llega por parametro)
     *
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
     * @return E Información del indexedPair si la encuentra, de lo contrario
     * retorna null.
     */
    @Override
    public E get(int pos) {
        NodeIndexedPair elemento = this.getPair(pos);
        if (elemento == null) {
            return null;
        } else {
            return (E) elemento.getValue().getValue();
        }
    }

    /**
     * Método delete Permite eliminar un elemento del arreglo dada una posición
     *
     * @param pos Posición del elemento a eliminar.
     */
    @Override
    public void delete(int pos) {
        //Si el arreglo es vacío, no elimina
        if (this.size == 0) {
            return;
        }
        //Si el elemento esta en la primera posición, se mueve el puntero (cabeza)
        if (this.sequence.getValue().getIndex() == pos) {
            this.sequence = this.sequence.getNext();
            this.size--;
            return;
        }

        //De lo contrario busca el elemento en el arreglo
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

    /**
     * Método contains Recorre el array verificando si la información pasada por
     * parametro coincide con la de algún elemento
     *
     * @param e Información del elemento a buscar
     * @return true si la información esta en el arreglo, de lo contrario false
     */
    @Override
    public boolean contains(E e) {
        for (NodeIndexedPair x = this.sequence; x != null; x = x.getNext()) {
            if (x.getValue().getValue().equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método getPair Recorre el arreglo en busca del elemento en la posición
     * pasada
     *
     * @param pos Posición del arreglo que intentamos buscar
     * @return NodeIndexedPair devuelve un par indexado si se encuentra la
     * posición, de lo contrario retorna null
     */
    private NodeIndexedPair getPair(int pos) {
        for (NodeIndexedPair x = this.sequence; x != null; x = x.getNext()) {
            if (x.getValue().getIndex() == pos) {
                return x;
            }
        }
        return null;
    }

    /**
     * Método clear Permite vaciar el arreglo y el size será 0
     */
    @Override
    public void clear() {
        super.clear();
        /* Vaciamos la colección */
        this.sequence = null;
        /* La secuencia es vacía */
    }

    /**
     * Clase que modela los nodos para la clase SparseArray Parametro value que
     * guarda la información del nodo Parametro next, que apunta al siguiente
     * elemento
     */
    private class NodeIndexedPair<E> {

        private IndexedPair value;
        private NodeIndexedPair<E> next;

        /**
         * Constructor por defecto
         */
        NodeIndexedPair() {
            this.value = null;
            this.next = null;
        }

        /**
         * Constructor con parametros
         */
        NodeIndexedPair(IndexedPair value) {
            this.value = value;
            //Siempre va a ser nulo, por que solo hay un elemento en este momento.
            this.next = null;
        }

        /**
         * Devuelve el valor del atributo value
         */
        public IndexedPair getValue() {
            return this.value;
        }

        /**
         * Cambia el valor del atributo value
         */
        public void setValue(IndexedPair e) {
            this.value = e;
        }

        /**
         * Obtiene el nodo siguiente.
         */
        public NodeIndexedPair getNext() {
            return this.next;
        }

        /**
         * Modifica el nodo siguiente.
         */
        public void setNext(NodeIndexedPair n) {
            this.next = n;
        }

    }
}

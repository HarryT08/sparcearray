package es.uned.lsi.eped.DataStructures;

import es.uned.lsi.eped.pract2021_2022.IndexedPair;

public abstract class Sequence<E> extends Collection<E> implements SequenceIF<E> {

    /* Clase privada que implementa la estructura de nodos de la secuencia */
    protected class NodeSequence {

        private E value;
        private NodeSequence next;

        NodeSequence() {
            this.value = null;
            this.next = null;
        }

        NodeSequence(E e) {
            this.value = e;
            this.next = null;
        }

        public E getValue() {
            return this.value;
        }

        public void setValue(E e) {
            this.value = e;
        }

        public NodeSequence getNext() {
            return this.next;
        }

        public void setNext(NodeSequence n) {
            this.next = n;
        }

    }

    /* Clase privada que implementa un iterador para la secuencia */
    private class SequenceIterator implements IteratorIF<E> {

        private NodeSequence currentNode;

        SequenceIterator() {
            this.currentNode = firstNode;
        }

        public E getNext() {
            E elem = this.currentNode.getValue();
            this.currentNode = this.currentNode.getNext();
            return elem;
        }

        public boolean hasNext() {
            return this.currentNode != null;
        }

        public void reset() {
            this.currentNode = firstNode;
        }
    }

    protected NodeSequence firstNode;

    /* Devuelve el primer nodo de la secuencia */
    private NodeSequence getFirstNode() {
        return this.firstNode;
    }

    /* Constructor de una secuencia vacía */
    public Sequence() {
        super();
        /* Creamos una colección */
        this.firstNode = null;
        /* La secuencia es vacía */
    }

    /* Constructor por copia */
    public Sequence(Sequence<E> s) {
        this();
        if (!s.isEmpty()) {
            this.size = s.size();
            NodeSequence nodeS = s.getFirstNode();
            NodeSequence pNode = new NodeSequence(nodeS.getValue());
            this.firstNode = pNode;
            while (nodeS.getNext() != null) {
                nodeS = nodeS.getNext();
                NodeSequence newNode = new NodeSequence(nodeS.getValue());
                pNode.setNext(newNode);
                pNode = newNode;
            }
        }
    }

    /* Reimplementación/Especialización de algunos métodos de Collection */

 /* Elimina todos los elementos de la secuencia */
    public void clear() {
        super.clear();
        /* Vaciamos la colección */
        this.firstNode = null;
        /* La secuencia es vacía */
    }

    /* Métodos heredados de CollectionIF */

 /* Comprueba si la secuencia contiene el elemento */
    public boolean contains(E e) {
        NodeSequence node = this.firstNode;
        while (node != null) {
            E next = node.getValue();
            if (next.equals(e)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    /* Devuelve un iterador para la secuencia */
    public IteratorIF<E> iterator() {
        return new SequenceIterator();
    }

    public void imprimir() {
        NodeSequence x = this.firstNode;
        while (x != null) {
            System.out.println(x.getValue().toString());
            x = x.getNext();
        }
    }

    /*Devuelve el nodo i-ésimo de la secuencia        *
	 *@Pre: 1 <= i <= size()                          */
    protected NodeSequence getNode(int i) {
        NodeSequence node = this.firstNode;
        for (int aux = 1; aux < i; aux++) {
            node = node.getNext();
        }
        return node;
    }

    private void agregarInicio(E element) {
        NodeSequence nuevo = new NodeSequence(element);
        nuevo.setNext(this.firstNode);
        this.firstNode = nuevo;
        this.size++;
    }

    /*public void insertarOrdenado(T info){
        if(this.isEmpty())
            this.addBegin(info);
        else{
            Nodo<T> n = this.cab;
            Nodo<T> m = n;
            while(n!=null){
                Comparable c = (Comparable)info;
                int ans=c.compareTo(n.getInfo());
                if(ans<0)
                    break;
                m=n;
                n=n.getSiguiente();
            }
            
            else{
                m.setSiguiente(new Nodo<>(info, n));
                this.tam++;
            }
        }
    }*/
    public void insertarParesOrdenados(int pos, E elem) {
        if(this.size == 0){
            IndexedPair toInsert = new IndexedPair(pos, elem);
            this.agregarInicio((E)toInsert);
            return;
        }
        IndexedPair aux  = new IndexedPair(pos , elem); // ELEMENTO A INSERTAR
        NodeSequence it = this.firstNode;
        NodeSequence prev = it;
        IndexedPair x =  (IndexedPair)it.getValue(); 
        while(it != null && pos  >  x.getIndex()  ){   
            prev = it;
            it = it.getNext();
            if(it != null)
                x = (IndexedPair)it.getValue();    
        }
        E toInsert = (E)aux;
        if(it==prev) this.agregarInicio(toInsert);
        else{
            prev.setNext(new NodeSequence(toInsert));
            prev.getNext().setNext(it);
            this.size++;
        }
    }

    protected void insertarAlFinal(E value) {
        NodeSequence node = new NodeSequence(value);
        NodeSequence cabeza = this.getNode(super.size - 1);
        cabeza.setNext(node);
        super.size++;
    }
}

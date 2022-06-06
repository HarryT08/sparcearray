package es.uned.lsi.eped.pract2021_2022;

/**
 * Clase que modela nodos para la clase SparseArray
 * Parametro value que guarda la información del nodo
 * Parametro next, que apunta al siguiente elemento
 */
public class NodeIndexedPair<E> {

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

package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.BTree;
import es.uned.lsi.eped.DataStructures.BTreeIF;
import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.Stack;

public class SparseArrayBTree<E> extends Collection<E> implements SparseArrayIF<E> {

    /**
     * Nodo inicial del árbol (Contiene subarboles)
     */
    protected BTree<IndexedPair<E>> btree;

    /**
     * Constructor por defecto del arbol
     */
    public SparseArrayBTree() {
        btree = new BTree();
    }

    /**
     * Método que permite convertir un número decimal a binario
     *
     * @param n número entero
     * @return Stack<Boolean> pila con el número descompuesto a binario
     */
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

    /**
     * Método que permite insertar un elemento indexado
     *
     * @param pos posición en la que se desea insertar
     * @param elem Elemento que se desea insertar en esa posición
     */
    @Override
    public void set(int pos, E elem) {
        Stack<Boolean> posBin = this.num2bin(pos);
        this.addNode(posBin, btree, pos, elem);
    }

    /**
     * Método recursivo para añadir un nodo al arbol (o subarbol)
     *
     * @param b Pila con el número convertido a binario
     * @param root Nodo inicial del método
     * @param elem Elemento que se desea insertar
     */
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

    /**
     * Método que devuelve el valor de un nodo en una posición
     *
     * @param pos Posición del elemento que se desea obtener su valor
     * @return E Elemento que contiene ese nodo.
     */
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

    /**
     * Método para buscar un nodo en el árbol
     *
     * @param pos Posición a buscar
     * @return BTreeIF<IndexedPair<E>> Subarbol en la posición pasada por
     * parámetro
     */
    public BTreeIF<IndexedPair<E>> buscar(int pos) {
        Stack<Boolean> b = this.num2bin(pos);
        return this.buscar(b, btree);
    }

    /**
     * Método recursivo para buscar un elemento dada una posición
     *
     * @param b Pila del número convertido a binario
     * @param current Nodo actual o inicial del método recursivo (Avanzará en
     * cada llamada)
     * @return BTreeIF<IndexedPair<E>> nodo en la posición pasada por parametro
     */
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

    /**
     * Método que permite eliminar el nodo en una posición y los nodos no
     * utilizados.
     *
     * @param pos Posición a eliminar
     */
    @Override
    public void delete(int pos) {
        BTreeIF<IndexedPair<E>> r = this.buscar(pos);
        if (r != null && r.getRoot() != null) {
            delete(num2bin(pos), btree);
            this.size--;
        }
    }

    /**
     * Método recursivo para buscar el nodo en una posición, eliminarlo y
     * eliminar nodos innecesarios.
     *
     * @param b Número decimal pasado a binario
     * @param current Nodo inicial o actual que variará en cada llamada.
     */
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

    /**
     * Método que permite iterar los objetos del árbol según el indice
     *
     * @return IteratorIF<Integer> un iterador para acceder a todos los
     * elementos según su indice ordenadamente.
     */
    @Override
    public IteratorIF<Integer> indexIterator() {
        IteratorIF<IndexedPair<E>> elementos = this.btree.iterator(BTreeIF.IteratorModes.BREADTH);
        return this.getIndexIterator(elementos);
    }

    /**
     * Método iterativo para recorrer el arbol y crear una cola con los índices
     *
     * @param elementos Iterador con los elementos del arbol
     * @return IteratorIF<Integer> Iterador con todos los índices de los
     * elementos en el árbol
     */
    private IteratorIF<Integer> getIndexIterator(IteratorIF<IndexedPair<E>> elementos) {
        Queue<Integer> q = new Queue<Integer>();
        while (elementos.hasNext()) {
            IndexedPair e = elementos.getNext();
            if (e != null) {
                q.enqueue(e.getIndex());
            }
        }
        return q.iterator();
    }
    
    /**
     * Método para verificar si el elemento se encuentra en el árbol
     * @return true si lo encuentra, false en caso contrario.
     */
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

    /**
     * Método que permite iterar los objetos del árbol según el elemento
     *
     * @return IteratorIF<Integer> un iterador para acceder a todos los
     * elementos según su elemento ordenadamente.
     */
    @Override
    public IteratorIF<E> iterator() {
        IteratorIF<IndexedPair<E>> elementos = this.btree.iterator(BTreeIF.IteratorModes.BREADTH);

        return this.getElementsIterator(elementos);
    }

    /**
     * Método iterativo para recorrer el arbol y crear una cola con los
     * elementos
     * @param elementos Iterador con los elementos del arbol
     * @return IteratorIF<Integer> Iterador con todos los índices de los
     * elementos en el árbol
     */
    private IteratorIF<E> getElementsIterator(IteratorIF<IndexedPair<E>> elementos) {
        Queue<E> q = new Queue<E>();
        while (elementos.hasNext()) {
            IndexedPair<E> e = elementos.getNext();
            if (e != null) {
                q.enqueue(e.getValue());
            }
        }
        return q.iterator();
    }
    
    /**
     * Método para limpiar el árbol
     */
    @Override
    public void clear() {
        super.clear();
        this.btree.clear();
    }
}

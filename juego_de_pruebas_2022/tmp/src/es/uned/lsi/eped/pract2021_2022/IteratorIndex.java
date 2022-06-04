package es.uned.lsi.eped.pract2021_2022;

import es.uned.lsi.eped.DataStructures.IteratorIF;

/**
 *
 * @author Admin
 */
public class IteratorIndex implements IteratorIF<Integer> {

    private NodeIndexedPair cabeza;

    public IteratorIndex(NodeIndexedPair cabeza) {
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

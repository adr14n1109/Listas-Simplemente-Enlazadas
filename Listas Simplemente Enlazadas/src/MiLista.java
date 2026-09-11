import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MiLista implements ListInterface {
    ListNode cabeza;

    @Override
    public boolean isEmpty() {
        return this.cabeza == null;
    }

    @Override
    public int getSize() {
        if (this.cabeza == null) {
            return 0;
        }
        ListNode iterador = this.cabeza;
        int contador = 1;
        while (iterador.siguiente != null) {
            iterador = iterador.siguiente;
            contador = contador + 1;
        }
        return contador;
    }

    @Override
    public void clear() {
        this.cabeza = null;
    }

    @Override
    public Object getHead() {
        if (this.cabeza == null) {
            return null;
        }
        return this.cabeza.dato;
    }

    @Override
    public Object getTail() {
        if (this.cabeza == null) {
            return null;
        }
        ListNode iterador = this.cabeza;
        while (iterador.siguiente != null) {
            iterador = iterador.siguiente;
        }
        return iterador.dato;
    }

    @Override
    public Object get(ListNode node) {
        ListNode iterador = this.cabeza;
        while (iterador != null) {
            if (iterador == node) {
                return iterador.dato;
            }
            iterador = iterador.siguiente;
        }
        return null;
    }

    @Override
    public Object search(Object object) {
        ListNode iterador = this.cabeza;
        while (iterador != null) {
            if (Objects.equals(iterador.dato, object)) {
                return iterador;
            }
            iterador = iterador.siguiente;
        }
        return null;
    }

    @Override
    public boolean add(Object object) {
        return insertTail(object);
    }

    @Override
    public boolean insert(ListNode node, Object object) {
        if (node == null) {
            return false;
        }
        ListNode iterador = this.cabeza;
        while (iterador != null) {
            if (iterador == node) {
                ListNode nuevoNodo = new ListNode(object);
                nuevoNodo.siguiente = iterador.siguiente;
                iterador.siguiente = nuevoNodo;
                return true;
            }
            iterador = iterador.siguiente;
        }
        return false;
    }

    @Override
    public boolean insert(Object ob, Object object) {
        ListNode nodoEncontrado = (ListNode) search(ob);
        if (nodoEncontrado == null) {
            return false;
        }
        return insert(nodoEncontrado, object);
    }

    @Override
    public boolean insertHead(Object object) {
        try {

            ListNode nuevaCabeza = new ListNode(object);

            nuevaCabeza.siguiente = this.cabeza;

            this.cabeza = nuevaCabeza;
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error");
            return false;
        }
    }

    @Override
    public boolean insertTail(Object object) {
        if (this.cabeza == null) {
            ListNode nuevaCabeza = new ListNode(object);
            this.cabeza = nuevaCabeza;
        } else {
            ListNode nuevaCola = new ListNode(object);
            ListNode iterador = this.cabeza;
            while (iterador.siguiente != null) {
                iterador = iterador.siguiente;
            }
            iterador.siguiente = nuevaCola;
        }
        return true;
    }

    @Override
    public boolean set(ListNode node, Object object) {
        ListNode iterador = this.cabeza;
        while (iterador != null) {
            if (iterador == node) {
                iterador.dato = object;
                return true;
            }
            iterador = iterador.siguiente;
        }
        return false;
    }

    @Override
    public boolean remove(ListNode node) {
        if (node == null || this.cabeza == null) {
            return false;
        }
        if (this.cabeza == node) {
            this.cabeza = this.cabeza.siguiente;
            return true;
        }
        ListNode anterior = this.cabeza;
        ListNode actual = this.cabeza.siguiente;
        while (actual != null) {
            if (actual == node) {
                anterior.siguiente = actual.siguiente;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean contains(Object object) {
        return search(object) != null;
    }

    @Override
    public Iterator<ListNode> iterator() {
        return new Iterator<ListNode>() {
            private ListNode actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public ListNode next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ListNode nodoActual = actual;
                actual = actual.siguiente;
                return nodoActual;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] arreglo = new Object[getSize()];
        ListNode iterador = this.cabeza;
        int i = 0;
        while (iterador != null) {
            arreglo[i] = iterador.dato;
            i++;
            iterador = iterador.siguiente;
        }
        return arreglo;
    }

    @Override
    public Object[] toArray(Object[] object) {
        int tamano = getSize();
        Object[] destino = object;
        if (destino == null || destino.length < tamano) {
            destino = new Object[tamano];
        }
        ListNode iterador = this.cabeza;
        int i = 0;
        while (iterador != null) {
            destino[i] = iterador.dato;
            i++;
            iterador = iterador.siguiente;
        }

        if (destino.length > tamano) {
            destino[tamano] = null;
        }
        return destino;
    }

    @Override
    public Object getBeforeTo() {
        if (this.cabeza == null || this.cabeza.siguiente == null) {
            return null;
        }
        ListNode iterador = this.cabeza;
        while (iterador.siguiente.siguiente != null) {
            iterador = iterador.siguiente;
        }
        return iterador.dato;
    }

    @Override
    public Object getBeforeTo(ListNode node) {
        if (node == null || this.cabeza == null || this.cabeza == node) {
            return null;
        }
        ListNode iterador = this.cabeza;
        while (iterador.siguiente != null) {
            if (iterador.siguiente == node) {
                return iterador.dato;
            }
            iterador = iterador.siguiente;
        }
        return null;
    }

    @Override
    public Object getNextTo() {
        if (this.cabeza == null || this.cabeza.siguiente == null) {
            return null;
        }
        return this.cabeza.siguiente.dato;
    }

    @Override
    public Object getNextTo(ListNode node) {
        ListNode iterador = this.cabeza;
        while (iterador != null) {
            if (iterador == node) {
                return (iterador.siguiente != null) ? iterador.siguiente.dato : null;
            }
            iterador = iterador.siguiente;
        }
        return null;
    }

    @Override
    public MiLista subList(ListNode from, ListNode to) {
        if (from == null || to == null) {
            return null;
        }
        MiLista sublista = new MiLista();
        ListNode iterador = this.cabeza;
        boolean dentro = false;
        while (iterador != null) {
            if (iterador == from) {
                dentro = true;
            }
            if (dentro) {
                sublista.insertTail(iterador.dato);
            }
            if (iterador == to) {
                return dentro ? sublista : null;
            }
            iterador = iterador.siguiente;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MiLista sortList() {
        Object[] datos = toArray();
        try {
            for (int i = 1; i < datos.length; i++) {
                Object clave = datos[i];
                Comparable<Object> claveComparable = (Comparable<Object>) clave;
                int j = i - 1;
                while (j >= 0 && claveComparable.compareTo(datos[j]) < 0) {
                    datos[j + 1] = datos[j];
                    j--;
                }
                datos[j + 1] = clave;
            }
        } catch (ClassCastException e) {
            System.out.println("Los elementos de la lista no son comparables entre sí");
            return null;
        }

        MiLista listaOrdenada = new MiLista();
        for (Object dato : datos) {
            listaOrdenada.insertTail(dato);
        }
        return listaOrdenada;
    }

    public void shuffle() {
    }

    public Object getHeadNode() {
        return null;
    }

    @Override
    public String toString() {
        return "MiLista{" +
                "cabeza=" + cabeza +
                '}';
    }
}

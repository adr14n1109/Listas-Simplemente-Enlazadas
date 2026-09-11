public class Main {

    public static void main(String[] args) {
        pruebaOperacionesBasicas();
        pruebaBusquedaYNavegacion();
        pruebaInsercionModificacion();
        pruebaRecorridoYArreglos();
        pruebaSubListYSort();
        pruebaClear();
    }


    private static void titulo(String texto) {
        System.out.println("\n=== " + texto + " ===");
    }

    private static void mostrar(String etiqueta, MiLista lista) {
        Object[] valores = lista.toArray();
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < valores.length; i++) {
            sb.append(valores[i]);
            if (i < valores.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(etiqueta + ": " + sb);
    }

    private static void mostrar(String etiqueta, Object valor) {
        System.out.println(etiqueta + ": " + valor);
    }


    private static void pruebaOperacionesBasicas() {
        titulo("Operaciones básicas");
        MiLista lista = new MiLista();
        mostrar("Lista vacía", lista);
        mostrar("¿Está vacía?", lista.isEmpty());

        lista.insertHead(0);
        lista.insertHead("Juan");
        lista.insertHead(true);
        lista.insertTail("Angela");

        mostrar("Tras insertHead/insertTail", lista);
        mostrar("Tamaño", lista.getSize());
        mostrar("¿Está vacía?", lista.isEmpty());
        mostrar("Cabeza", lista.getHead());
        mostrar("Cola", lista.getTail());
    }

    private static void pruebaBusquedaYNavegacion() {
        titulo("Búsqueda y navegación");
        MiLista lista = new MiLista();
        lista.insertTail(true);
        lista.insertTail("Juan");
        lista.insertTail(0);
        lista.insertTail("Angela");
        mostrar("Lista", lista);

        ListNode nodoJuan = (ListNode) lista.search("Juan");
        mostrar("¿Contiene 'Angela'?", lista.contains("Angela"));
        mostrar("¿Contiene 'Pedro'?", lista.contains("Pedro"));
        mostrar("get(nodoJuan)", lista.get(nodoJuan));
        mostrar("Anterior a 'Juan'", lista.getBeforeTo(nodoJuan));
        mostrar("Siguiente a 'Juan'", lista.getNextTo(nodoJuan));
        mostrar("getBeforeTo() [penúltimo]", lista.getBeforeTo());
        mostrar("getNextTo() [segundo elemento]", lista.getNextTo());
    }

    private static void pruebaInsercionModificacion() {
        titulo("Insertar, modificar y eliminar");
        MiLista lista = new MiLista();
        lista.insertTail(true);
        lista.insertTail("Juan");
        lista.insertTail(0);
        lista.insertTail("Angela");
        ListNode nodoJuan = (ListNode) lista.search("Juan");

        lista.insert(nodoJuan, "Recién insertado tras Juan");
        mostrar("Tras insert(nodoJuan, ...)", lista);

        lista.insert("Angela", "Recién insertado tras Angela");
        mostrar("Tras insert(\"Angela\", ...)", lista);

        lista.set(nodoJuan, "Juan Carlos");
        mostrar("Tras set(nodoJuan, \"Juan Carlos\")", lista);

        lista.add("Agregado al final con add()");
        mostrar("Tras add(...)", lista);

        lista.remove(nodoJuan);
        mostrar("Tras remove(nodoJuan)", lista);
    }

    private static void pruebaRecorridoYArreglos() {
        titulo("toArray() e iterator()");
        MiLista lista = new MiLista();
        lista.insertTail("a");
        lista.insertTail("b");
        lista.insertTail("c");

        mostrar("toArray()", lista);

        System.out.print("Recorrido con iterator(): ");
        for (java.util.Iterator<ListNode> it = lista.iterator(); it.hasNext(); ) {
            System.out.print(it.next().dato + " ");
        }
        System.out.println();
    }

    private static void pruebaSubListYSort() {
        titulo("subList() y sortList()");
        MiLista lista = new MiLista();
        lista.insertTail(5);
        lista.insertTail(2);
        lista.insertTail(9);
        lista.insertTail(1);
        lista.insertTail(7);
        mostrar("Lista original", lista);

        ListNode desde = (ListNode) lista.search(2);
        ListNode hasta = (ListNode) lista.search(1);
        MiLista sublista = lista.subList(desde, hasta);
        mostrar("subList(2 -> 1)", sublista);

        MiLista ordenada = lista.sortList();
        mostrar("sortList()", ordenada);
        mostrar("Lista original sin cambios", lista);
    }

    private static void pruebaClear() {
        titulo("clear()");
        MiLista lista = new MiLista();
        lista.insertTail(5);
        lista.insertTail(10);
        mostrar("Antes de clear()", lista);

        lista.clear();
        mostrar("Tras clear()", lista);
        mostrar("¿Está vacía?", lista.isEmpty());

        lista.insertTail(5);
        mostrar("Tras insertar de nuevo", lista);
        mostrar("Tamaño", lista.getSize());
    }
}
//Fin
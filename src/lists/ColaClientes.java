package lists;

import entities.Cliente;

public class ColaClientes {
    private Cliente[] datos;
    private int frente;
    private int fin;
    private int tamaño;
    private final int capacidad;

    public ColaClientes(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new Cliente[capacidad];
        this.frente = 0;
        this.fin = -1;
        this.tamaño = 0;
    }

    // Encola al final (manteniendo orden de llegada para desempate)
    public boolean encolar(Cliente c) {
        if (tamaño == capacidad) return false;
        fin = (fin + 1) % capacidad;
        datos[fin] = c;
        tamaño++;
        return true;
    }

    // Vista del siguiente por prioridad (sin remover)
    public Cliente verSiguientePorPrioridad() {
        if (tamaño == 0) return null;
        int max = -1; Cliente candidato = null; int posCandidato = -1;

        for (int i = 0; i < tamaño; i++) {
            int idx = (frente + i) % capacidad;
            Cliente actual = datos[idx];
            if (actual.getPrioridad() > max) {
                max = actual.getPrioridad();
                candidato = actual;
                posCandidato = i; // “distancia” desde el frente para desempate
            }
        }
        return candidato;
    }

    // Desencola por prioridad: remueve el candidato y compacta
    public Cliente desencolarPorPrioridad() {
        if (tamaño == 0) return null;

        int max = -1; int posReal = -1; // índice real en el arreglo
        int mejorDistancia = Integer.MAX_VALUE;

        for (int i = 0; i < tamaño; i++) {
            int idx = (frente + i) % capacidad;
            Cliente actual = datos[idx];
            if (actual.getPrioridad() > max || 
               (actual.getPrioridad() == max && i < mejorDistancia)) {
                max = actual.getPrioridad();
                mejorDistancia = i;
                posReal = idx;
            }
        }

        Cliente atendido = datos[posReal];

        // Compactar desde posReal hacia fin
        int i = posReal;
        while (i != fin) {
            int siguiente = (i + 1) % capacidad;
            datos[i] = datos[siguiente];
            i = siguiente;
        }
        datos[fin] = null;
        fin = (fin - 1 + capacidad) % capacidad;
        tamaño--;

        return atendido;
    }

    public int getTamaño() { return tamaño; }
    public boolean estaVacia() { return tamaño == 0; }

    // Iteración simple para imprimir estado
    public Cliente[] snapshot() {
        Cliente[] arr = new Cliente[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arr[i] = datos[(frente + i) % capacidad];
        }
        return arr;
    }
}

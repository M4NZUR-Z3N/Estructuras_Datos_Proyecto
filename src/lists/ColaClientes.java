package lists;

import entities.Cliente;
import entities.Ubicacion;

public class ColaClientes {
    private Cliente[] datos;
    private int frente;
    private int fin;
    private int tamaño;
    private final int capacidad;
    private GrafoUbicaciones grafo; // Referencia al grafo para agregar ubicaciones

    public ColaClientes(int capacidad, GrafoUbicaciones grafo) {
        this.capacidad = capacidad;
        this.datos = new Cliente[capacidad];
        this.frente = 0;
        this.fin = -1;
        this.tamaño = 0;
        this.grafo = grafo;
    }

    public boolean encolar(Cliente c) {
        if (tamaño == capacidad) return false;
        
        // Agregar ubicación del cliente al grafo automáticamente
        grafo.agregarUbicacion(c.getUbicacion());
        
        fin = (fin + 1) % capacidad;
        datos[fin] = c;
        tamaño++;
        return true;
    }

    public Cliente verSiguientePorPrioridad() {
        if (tamaño == 0) return null;
        int max = -1; 
        Cliente candidato = null; 
        int posCandidato = -1;

        for (int i = 0; i < tamaño; i++) {
            int idx = (frente + i) % capacidad;
            Cliente actual = datos[idx];
            if (actual.getPrioridad() > max) {
                max = actual.getPrioridad();
                candidato = actual;
                posCandidato = i;
            }
        }
        return candidato;
    }

    public Cliente desencolarPorPrioridad() {
        if (tamaño == 0) return null;

        int max = -1; 
        int posReal = -1;
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

    public int getTamaño() { 
        return tamaño; 
    }
    
    public boolean estaVacia() { 
        return tamaño == 0; 
    }

    public Cliente[] snapshot() {
        Cliente[] arr = new Cliente[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arr[i] = datos[(frente + i) % capacidad];
        }
        return arr;
    }
}
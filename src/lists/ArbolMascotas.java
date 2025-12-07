package lists;

import entities.Mascota;
import java.util.ArrayList;

public class ArbolMascotas {
    private NodoArbol raiz;
    private int tamaño;

    private static class NodoArbol {
        private Mascota mascota;
        private NodoArbol hijoIzquierdo;
        private NodoArbol hijoDerecho;

        public NodoArbol(Mascota mascota) {
            this.mascota = mascota;
            this.hijoIzquierdo = null;
            this.hijoDerecho = null;
        }
    }

    public ArbolMascotas() {
        this.raiz = null;
        this.tamaño = 0;
    }

    public boolean insertar(Mascota mascota) {
        if (raiz == null) {
            raiz = new NodoArbol(mascota);
            tamaño++;
            return true;
        }
        return insertarRecursivo(raiz, mascota);
    }

    private boolean insertarRecursivo(NodoArbol actual, Mascota mascota) {
        int comparacion = mascota.getNombre().compareToIgnoreCase(actual.mascota.getNombre());

        if (comparacion == 0) {
            return false;
        } else if (comparacion < 0) {
            if (actual.hijoIzquierdo == null) {
                actual.hijoIzquierdo = new NodoArbol(mascota);
                tamaño++;
                return true;
            } else {
                return insertarRecursivo(actual.hijoIzquierdo, mascota);
            }
        } else {
            if (actual.hijoDerecho == null) {
                actual.hijoDerecho = new NodoArbol(mascota);
                tamaño++;
                return true;
            } else {
                return insertarRecursivo(actual.hijoDerecho, mascota);
            }
        }
    }

    public Mascota buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private Mascota buscarRecursivo(NodoArbol actual, String nombre) {
        if (actual == null) {
            return null;
        }

        int comparacion = nombre.compareToIgnoreCase(actual.mascota.getNombre());

        if (comparacion == 0) {
            return actual.mascota;
        } else if (comparacion < 0) {
            return buscarRecursivo(actual.hijoIzquierdo, nombre);
        } else {
            return buscarRecursivo(actual.hijoDerecho, nombre);
        }
    }

    public void inOrder() {
        System.out.println("=== INVENTARIO ORDENADO (In-Order) ===");
        inOrderRecursivo(raiz);
    }

    private void inOrderRecursivo(NodoArbol actual) {
        if (actual != null) {
            inOrderRecursivo(actual.hijoIzquierdo);
            System.out.println(actual.mascota);
            inOrderRecursivo(actual.hijoDerecho);
        }
    }

    public ArrayList<Mascota> obtenerTodasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        obtenerTodasRecursivo(raiz, mascotas);
        return mascotas;
    }

    private void obtenerTodasRecursivo(NodoArbol actual, ArrayList<Mascota> mascotas) {
        if (actual != null) {
            obtenerTodasRecursivo(actual.hijoIzquierdo, mascotas);
            mascotas.add(actual.mascota);
            obtenerTodasRecursivo(actual.hijoDerecho, mascotas);
        }
    }

    public int getTamaño() { 
        return tamaño; 
    }
    
    public boolean estaVacio() { 
        return raiz == null; 
    }
}
package lists;

import entities.AristaUbicacion;
import entities.Ubicacion;
import java.util.*;

public class GrafoUbicaciones {
    private Map<Ubicacion, List<AristaUbicacion>> listaAdyacencia;

    public GrafoUbicaciones() {
        this.listaAdyacencia = new HashMap<>();
    }

    public void agregarUbicacion(Ubicacion ubicacion) {
        listaAdyacencia.putIfAbsent(ubicacion, new ArrayList<>());
    }

    public void agregarConexion(Ubicacion origen, Ubicacion destino, int distanciaKm) {
        if (!listaAdyacencia.containsKey(origen)) {
            agregarUbicacion(origen);
        }
        if (!listaAdyacencia.containsKey(destino)) {
            agregarUbicacion(destino);
        }

        listaAdyacencia.get(origen).add(new AristaUbicacion(destino, distanciaKm));
        listaAdyacencia.get(destino).add(new AristaUbicacion(origen, distanciaKm));
    }

    public List<AristaUbicacion> getConexiones(Ubicacion ubicacion) {
        return listaAdyacencia.getOrDefault(ubicacion, new ArrayList<>());
    }

    public boolean estaConectada(Ubicacion ubicacion) {
        return listaAdyacencia.containsKey(ubicacion) && 
               !listaAdyacencia.get(ubicacion).isEmpty();
    }

    public Map<Ubicacion, ResultadoDijkstra> dijkstraCompleto(Ubicacion inicio) {
        Map<Ubicacion, Integer> distancias = new HashMap<>();
        Map<Ubicacion, Ubicacion> predecesores = new HashMap<>();
        PriorityQueue<NodoDijkstra> cola = new PriorityQueue<>(Comparator.comparingInt(NodoDijkstra::getDistancia));

        for (Ubicacion u : listaAdyacencia.keySet()) {
            distancias.put(u, Integer.MAX_VALUE);
            predecesores.put(u, null);
        }

        distancias.put(inicio, 0);
        cola.add(new NodoDijkstra(inicio, 0));

        while (!cola.isEmpty()) {
            NodoDijkstra actual = cola.poll();

            for (AristaUbicacion arista : listaAdyacencia.get(actual.getUbicacion())) {
                Ubicacion vecino = arista.getDestino();
                int nuevaDist = distancias.get(actual.getUbicacion()) + arista.getDistanciaKm();

                if (nuevaDist < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDist);
                    predecesores.put(vecino, actual.getUbicacion());
                    cola.add(new NodoDijkstra(vecino, nuevaDist));
                }
            }
        }

        Map<Ubicacion, ResultadoDijkstra> resultado = new HashMap<>();
        for (Ubicacion u : listaAdyacencia.keySet()) {
            resultado.put(u, new ResultadoDijkstra(distancias.get(u), predecesores.get(u)));
        }
        
        return resultado;
    }

    public List<Ubicacion> reconstruirCamino(Ubicacion inicio, Ubicacion destino, Map<Ubicacion, ResultadoDijkstra> resultadoDijkstra) {
        List<Ubicacion> camino = new ArrayList<>();
        
        if (!resultadoDijkstra.containsKey(destino) || 
            resultadoDijkstra.get(destino).getDistancia() == Integer.MAX_VALUE) {
            return camino;
        }
        
        Ubicacion actual = destino;
        while (actual != null) {
            camino.add(actual);
            actual = resultadoDijkstra.get(actual).getPredecesor();
        }
        
        Collections.reverse(camino);
        
        if (!camino.isEmpty() && camino.get(0).equals(inicio)) {
            return camino;
        } else {
            return new ArrayList<>();
        }
    }

    public void mostrarGrafo() {
        System.out.println("=== GRAFO DE UBICACIONES ===");
        for (Map.Entry<Ubicacion, List<AristaUbicacion>> entry : listaAdyacencia.entrySet()) {
            System.out.print(entry.getKey().getNombre() + " -> ");
            for (AristaUbicacion arista : entry.getValue()) {
                System.out.print("(" + arista.getDestino().getNombre() + ", " + arista.getDistanciaKm() + " km) ");
            }
            System.out.println();
        }
    }

    private static class NodoDijkstra {
        private Ubicacion ubicacion;
        private int distancia;

        public NodoDijkstra(Ubicacion ubicacion, int distancia) {
            this.ubicacion = ubicacion;
            this.distancia = distancia;
        }

        public Ubicacion getUbicacion() { 
            return ubicacion; 
        }
        
        public int getDistancia() { 
            return distancia; 
        }
    }

    public static class ResultadoDijkstra {
        private int distancia;
        private Ubicacion predecesor;

        public ResultadoDijkstra(int distancia, Ubicacion predecesor) {
            this.distancia = distancia;
            this.predecesor = predecesor;
        }

        public int getDistancia() { 
            return distancia; 
        }
        
        public Ubicacion getPredecesor() { 
            return predecesor; 
        }
    }
}
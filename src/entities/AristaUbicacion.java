package entities;

public class AristaUbicacion {
    private Ubicacion destino;
    private int distanciaKm;

    public AristaUbicacion(Ubicacion destino, int distanciaKm) {
        this.destino = destino;
        this.distanciaKm = distanciaKm;
    }

    public Ubicacion getDestino() { 
        return destino; 
    }
    
    public int getDistanciaKm() { 
        return distanciaKm; 
    }

    @Override
    public String toString() {
        return "-> " + destino.getNombre() + " (" + distanciaKm + " km)";
    }
}
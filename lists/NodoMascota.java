package lists;
import entities.Mascota;

public class NodoMascota {
    private Mascota mascota;
    private NodoMascota siguiente;
    
    // Constructor
    public NodoMascota(Mascota mascota) {
        this.mascota = mascota;
        this.siguiente = null;
    }
    
    // Getters y Setters
    public Mascota getMascota() {
        return mascota;
    }
    
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
    
    public NodoMascota getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(NodoMascota siguiente) {
        this.siguiente = siguiente;
    }
}
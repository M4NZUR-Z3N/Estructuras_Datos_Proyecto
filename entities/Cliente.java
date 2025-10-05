package entities;

public class Cliente {
    private String nombre;
    private String cedula;
    
    // Constructor
    public Cliente(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
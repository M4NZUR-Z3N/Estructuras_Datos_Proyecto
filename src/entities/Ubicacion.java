package entities;

public class Ubicacion {
    private String nombre;
    private String codigo;

    public Ubicacion(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public String getCodigo() { 
        return codigo; 
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ubicacion that = (Ubicacion) obj;
        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
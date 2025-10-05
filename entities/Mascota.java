package entities;
import java.io.File;
import java.util.ArrayList;

public class Mascota {
    // Atributos (según lo solicitado)
    private String nombre;
    private double precio;
    private String especie;
    private String raza;
    private int edad;
    private String rutaCarpeta; //Guarda la ruta de la carpeta donde iran las imagenes de la mascota
    private ArrayList<String> listaImagenes; //Guarda los nombres delos archivos

    // Constructor
    public Mascota(
        String pNombre,
        double pPrecio,
        String pEspecie,
        String pRaza,
        int pEdad
    ){
        this.nombre = pNombre;
        this.precio = pPrecio;
        this.especie = pEspecie;
        this.raza = pRaza;
        this.edad = pEdad;
        this.rutaCarpeta = "resources/imagenesMascotas/" + pNombre;
        File carpetaDestino = new File(rutaCarpeta);
        carpetaDestino.mkdirs();
        this.listaImagenes = new ArrayList<String>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRuta(){
        return rutaCarpeta;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    // Método para agregar imágenes
    public void agregarImagen(String rutaImagen) {

        this.listaImagenes.add(rutaImagen);
    }
    
    // Método para calcular costo total (precio * cantidad)
    public double calcularCostoTotal(int cantidad) {
        return this.precio * cantidad;
    }
    
    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", especie='" + especie + '\'' +
                ", raza='" + raza + '\'' +
                ", edad=" + edad +
                '}';
    }
}
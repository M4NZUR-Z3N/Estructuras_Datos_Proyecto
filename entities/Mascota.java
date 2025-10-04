package entities;
import java.util.ArrayList;

public class Mascota {
    //Atributos
    private String nombre;
    private double precio;
    private String especie;
    private String raza;
    private int edad;
    private ArrayList<String> listaImagenes;

    //Metodos
    public Mascota(
        String pNombre,
        double pPrecio,
        String pEspecie,
        String pRaza,
        int pEdad
    ){
        nombre = pNombre;
        precio = pPrecio;
        especie = pEspecie;
        raza = pRaza;
        edad = pEdad;
        listaImagenes = new ArrayList<String>();
    }

    
}

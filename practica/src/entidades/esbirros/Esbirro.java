package entidades.esbirros;
import java.util.Scanner;

public abstract class Esbirro implements java.io.Serializable  {
    String nombre;
    int salud;

    public abstract void crearEsbirro(Scanner myOb, Object parent);

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

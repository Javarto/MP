package entidades.equipo;

public abstract class Equipo implements java.io.Serializable {
    String nombre;
    int modificadorAtaque;
    int modificadorDefensa;
    String categoria;

    public Equipo(String nombre, int modificadorAtaque, int modificadorDefensa, String categoria) {
        this.nombre = nombre;
        this.modificadorAtaque = modificadorAtaque;
        this.modificadorDefensa = modificadorDefensa;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getModificadorAtaque() {
        return modificadorAtaque;
    }

    public void setModificadorAtaque(int modificadorAtaque) {
        this.modificadorAtaque = modificadorAtaque;
    }

    public int getModificadorDefensa() {
        return modificadorDefensa;
    }

    public void setModificadorDefensa(int modificadorDefensa) {
        this.modificadorDefensa = modificadorDefensa;
    }
}

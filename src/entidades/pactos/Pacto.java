package entidades.pactos;

import entidades.esbirros.Demonio;

public class Pacto implements java.io.Serializable{
    String descripcion;
    Object owner;
    Demonio esclavo;

    public Pacto(String descripcion, Object owner, Demonio esclavo) {
        this.descripcion = descripcion;
        this.owner = owner;
        this.esclavo = esclavo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public Demonio getEsclavo() {
        return esclavo;
    }

    public void setEsclavo(Demonio esclavo) {
        this.esclavo = esclavo;
    }
}

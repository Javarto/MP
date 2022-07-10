package entidades.equipo.armas;

import entidades.equipo.Equipo;

public class Arma extends Equipo implements java.io.Serializable{
    boolean dosManos;

    public Arma(String nombre, int modificadorAtaque, int modificadorDefensa, String categoria, boolean dosManos) {
        super(nombre, modificadorAtaque, modificadorDefensa, categoria);
        this.dosManos = dosManos;
    }

    public boolean isDosManos() {
        return dosManos;
    }

    public void printArma() {
        System.out.println("Nombre: " + super.getNombre());
        System.out.println("Mod de ataque: " + super.getModificadorAtaque());
        System.out.println("Mod de defensa: " + super.getModificadorDefensa());
        System.out.println("Categoria: " + super.getCategoria());
        System.out.println("Dosmanos: " + this.dosManos);
    }

    @Override
    public String toString() {
        return super.getNombre() + " " + super.getModificadorAtaque() + " " + super.getModificadorDefensa() + " "
                + super.getCategoria() + " " + this.dosManos;
    }
}

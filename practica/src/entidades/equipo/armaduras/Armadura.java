package entidades.equipo.armaduras;

import entidades.equipo.Equipo;

public class Armadura extends Equipo implements java.io.Serializable {
    public Armadura(String nombre, int modificadorAtaque, int modificadorDefensa, String categoria) {
        super(nombre, modificadorAtaque, modificadorDefensa, categoria);
    }

    public void printArmadura() {
        System.out.println("Nombre: " + super.getNombre());
        System.out.println("Mod de ataque: " + super.getModificadorAtaque());
        System.out.println("Mod de defensa: " + super.getModificadorDefensa());
        System.out.println("Categoria: " + super.getCategoria());
    }
    
    @Override
    public String toString() {
        return super.getNombre() + " " + super.getModificadorAtaque() + " " + super.getModificadorDefensa() + " "
                + super.getCategoria();
    }
}

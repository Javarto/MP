package entidades.esbirros;

import java.util.Scanner;

public class Ghoul extends Esbirro{
    int dependencia;

    public void crearEsbirro(Scanner myOb, Object parent) {

        this.dependencia = -1;
        while (this.dependencia < 1 | this.dependencia > 5) {
            System.out.println("\nDependencia: ");
            while (!myOb.hasNextInt()) {
                System.out.println("Por favor introduzca un número dentro del rango (1 - 5)");
                myOb.next();
            }
            this.dependencia = myOb.nextInt();
            myOb.nextLine();
        }
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        this.dependencia = dependencia;
    }
}

package entidades.personajes;

import java.util.Scanner;

public class Licantropo extends Personaje{
    int rabia = -1;

    public void getData(Scanner myOb) {
        System.out.println("\nRabia (0 - 3): ");
        while (this.rabia < 0 | this.rabia > 3) {
            while (!myOb.hasNextInt()) {
                System.out.println("Por favor introduzca un número entero");
                myOb.next();
            }
            this.rabia = myOb.nextInt();
            myOb.nextLine();
        }
    }

    public int getRabia() {
        return rabia;
    }

    public void setRabia(int rabia) {
        this.rabia = rabia;
    }
}

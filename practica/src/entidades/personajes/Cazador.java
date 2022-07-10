package entidades.personajes;

import java.util.Scanner;

public class Cazador extends Personaje {
    int voluntad = -1;

    public void getData(Scanner myOb) {
        System.out.println("\nVoluntad (0 - 3): ");
        while (this.voluntad < 0 | this.voluntad > 3) {
            while (!myOb.hasNextInt()) {
                System.out.println("Por favor introduzca un número entero");
                myOb.next();
            }
            this.voluntad = myOb.nextInt();
            myOb.nextLine();
        }
    }

    public int getVoluntad() {
        return this.voluntad;
    }
    public void setVoluntad(int vol) {
        this.voluntad = vol;
    }
}

package entidades.personajes;

import java.util.Scanner;

public class Vampiro extends Personaje {
    int sangreAcumulada;
    int edad;

    public void getData(Scanner myOb) {
        this.edad = -1;
        System.out.println("\nEdad: ");
        while (this.edad < 0) {
            while (!myOb.hasNextInt()) {
                System.out.println("Por favor introduzca un número entero");
                myOb.next();
            }
            this.edad = myOb.nextInt();
            myOb.nextLine();
        }

        this.sangreAcumulada = -1;
        while (this.sangreAcumulada < 0 | this.sangreAcumulada > 10) {
            System.out.println("\nSangre Acumulada: ");
            while (!myOb.hasNextInt()) {
                System.out.println("Por favor introduzca un número entero");
                myOb.next();
            }
            this.sangreAcumulada = myOb.nextInt();
            myOb.nextLine();
        }
    }

    public int getSangreAcumulada() {
        return sangreAcumulada;
    }

    public void setSangreAcumulada(int sangreAcumulada) {
        this.sangreAcumulada = sangreAcumulada;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}

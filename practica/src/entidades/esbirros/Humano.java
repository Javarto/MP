package entidades.esbirros;

import java.util.Scanner;

public class Humano extends Esbirro{
    String lealtad = "";

    public void crearEsbirro(Scanner myOb, Object parent) {

        while (!lealtad.equals("ALTA") & !lealtad.equals("NORMAL") & !lealtad.equals("BAJA")) {
            System.out.println("Lealtad del humano (ALTA-NORMAL-BAJA)");
            this.lealtad = myOb.nextLine();
        }
    }

    public String getLealtad() {
        return lealtad;
    }

    public void setLealtad(String lealtad) {
        this.lealtad = lealtad;
    }

}

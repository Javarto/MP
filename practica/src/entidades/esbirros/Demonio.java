package entidades.esbirros;

import entidades.pactos.Pacto;
import entidades.personajes.GetEsbirroFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Demonio extends Esbirro {
    ArrayList<Object> esbirros = new ArrayList<>();
    Pacto pacto;

    public void crearEsbirro(Scanner myOb, Object parent) {
        this.pacto = createPacto(myOb, parent);
        boolean stop = false;
        while (!stop) {
            Object esb = createEsbirro(myOb);
            if (esb != null) {
                this.addEsbirro(esb);
            } else {
                stop = true;
            }
        }
    }

    public Pacto createPacto(Scanner myObj, Object parent) {
        System.out.println("\nDescripción del pacto");
        String desc = myObj.nextLine();
        return new Pacto(desc, parent, this);
    }

    public void addEsbirro(Object esbirro){
        esbirros.add(esbirro);
    }

    public ArrayList<Object> getEsbirros() {
        return esbirros;
    }

    public Pacto getPacto() {
        return pacto;
    }

    public void setPacto(Pacto pacto) {
        this.pacto = pacto;
    }

    public Object createEsbirro(Scanner myObj) {
        System.out.println("Creación de Esbirro de Demonio");
        GetEsbirroFactory perFactory = new GetEsbirroFactory();
        String sel = this.selectEsbirro(myObj);
        if (!sel.equals("q")) {
            Esbirro p = perFactory.getEsb(sel);

            System.out.println("Nombre del esbirro");
            String nombre = myObj.nextLine();

            System.out.println("Salud del esbirro (1 - 3)");
            int salud = 0;
            while (salud < 1 | salud > 3) {
                while (!myObj.hasNextInt()) {
                    System.out.println("Por favor introduzca un número dentro del rango (1 - 3)");
                    myObj.next();
                }
                salud = myObj.nextInt();
                myObj.nextLine();
            }
            p.crearEsbirro(myObj, this);
            p.setSalud(salud);
            p.setNombre(nombre);
            return p;
        }
        return null;
    }

    public String selectEsbirro(Scanner myObj) {
        System.out.println("1. Demonio");
        System.out.println("2. Ghoul");
        System.out.println("3. Humano");
        System.out.println("4. Ninguno");
        int opt = 0;
        while (opt < 1 | opt > 4) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un número entero");
                myObj.next();
            }
            opt = myObj.nextInt();
            myObj.nextLine();
        }
        String res = "";
        switch (opt) {
            case (1): {
                res = "Demonio";
                break;
            }
            case (2): {
                res = "Ghoul";
                break;
            }
            case (3): {
                res = "Humano";
                break;
            }
            case (4): {
                res = "q";
                break;
            }
        }
        return res;
    }
}

package entidades.personajes;

import entidades.equipo.armaduras.Armadura;
import entidades.equipo.armas.Arma;
import entidades.esbirros.Demonio;
import entidades.esbirros.Esbirro;
import entidades.esbirros.Humano;
import entidades.oferta.Oferta;

import java.util.ArrayList;
import java.util.Scanner;

import Users.Usuario;



public abstract class Personaje implements java.io.Serializable {
    String name;
    ArrayList<Arma> armas = new ArrayList<>();
    ArrayList<Arma> armasDis = new ArrayList<>();
    ArrayList<Armadura> armaduras = new ArrayList<>();
    ArrayList<Armadura> armadurasDis = new ArrayList<>();
    ArrayList<Object> esbirros = new ArrayList<>();
    ArrayList<Object> esbirrosDis = new ArrayList<>();
    int oro;


    public abstract void getData(Scanner myOb);


    public void setNombre(String name) {
        this.name = name;
    }

    public void delArma(Arma index) {
        this.armas.remove(index);
    }

    public void delArmadura(Armadura index) {
        this.armaduras.remove(index);
    }

    public ArrayList<Object> getEsbirrosSingle(ArrayList<Object> esbs) {
        ArrayList<Object> names = new ArrayList<>();
        for (int i = 0; i < esbs.size(); i++) {
            names.add(esbs.get(i));
            //System.out.println(((Esbirro) esbs.get(i)).getNombre());
            if (esbs.get(i) instanceof Demonio) {
                ArrayList<Object> demonN = getEsbirrosSingle(((Demonio) esbs.get(i)).getEsbirros());
                for (int ii = 0; ii < demonN.size(); ii++) {
                    names.add(demonN.get(ii));
                }
            }
        }
        return names;
    }

    public Object createEsbirro(Scanner myObj, Object per) {
        System.out.println("Añadir Esbirro");
        GetEsbirroFactory perFactory = new GetEsbirroFactory();
        String sel = this.selectEsbirro(myObj, per);
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

    public String selectEsbirro(Scanner myObj, Object per) {
        String res = "";
        if (!(per instanceof Vampiro)) {
            System.out.println("1. Demonio");
            System.out.println("2. Ghoul");
            System.out.println("3. Humano");
            System.out.println("4. Ninguno");
            int opt = 0;
            while (opt < 1 | opt > 4) {
                while (!myObj.hasNextInt()) {
                    System.out.println("Por favor introduzca un número dentro del rango (1 - 4)");
                    myObj.next();
                }
                opt = myObj.nextInt();
                myObj.nextLine();
            }
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
        } else {
            System.out.println("1. Demonio");
            System.out.println("2. Ghoul");
            System.out.println("3. Ninguno");
            int opt = 0;
            while (opt < 1 | opt > 3) {
                while (!myObj.hasNextInt()) {
                    System.out.println("Por favor introduzca un número dentro del rango (1 - 4)");
                    myObj.next();
                }
                opt = myObj.nextInt();
                myObj.nextLine();
            }
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
                    res = "q";
                    break;
                }
            }
        }
        return res;
    }

    public boolean buyOffer(Oferta offer) {
        boolean buyed = false;
        boolean cont = true;
        if (this instanceof Vampiro) {
            for (int i = 0; i < offer.getEsbirrosOffer().size(); i++) {
                if (offer.getEsbirrosOffer().get(i) instanceof Humano) {
                    cont = false;
                    break;
                }
            }
        }
        if ((offer.getPrecio() <= this.getOro()) & (cont)) {
            for (int i = 0; i < offer.getArmasOffer().size(); i++) {
                this.addArma(offer.getArmasOffer().get(i));
            }
            for (int i = 0; i < offer.getArmadurasOffer().size(); i++) {
                this.addArmadura(offer.getArmadurasOffer().get(i));
            }
            for (int i = 0; i < offer.getEsbirrosCompact().size(); i++) {
                this.addEsbirro(offer.getEsbirrosCompact().get(i));
            }
            this.reduceOro(offer.getPrecio());
            //vendedor.addOro(offer.getPrecio());
            System.out.println("Compra realizada con éxito!!");
            buyed = true;
        } else {
            System.out.println("No puede hacer la compra.");
        }
        return buyed;
    }

    public boolean setArma(Scanner myObj) {
        System.out.println("1. Arma 1 mano");
        System.out.println("2. Arma 2 manos");

        boolean dosmanos = false;
        int option = 0;
        while (option < 1 | option > 2) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un número entre (1 - 2)");
                myObj.next();
            }
            option = myObj.nextInt();
            myObj.nextLine();
        }

        if (option == 2) {
            dosmanos = true;
        }
        
        return dosmanos;
    }

    public void deleteEquipo(Scanner myObj) {
        System.out.println("1. Borrar arma");
        System.out.println("2. Borrar armadura");

        int option = 0;
        while (option < 1 | option > 2) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un número entre (1 - 2");
                myObj.next();
            }
            option = myObj.nextInt();
            myObj.nextLine();
        }
        int sel = -1;
        if (option == 1) {
            for (int i = 0; i < this.armas.size(); i++) {
                System.out.println(i + " " + (this.armas.get(i)).toString());
            }
            while(sel < 0 | sel > (this.armas.size() - 1)) {
                while (!myObj.hasNextInt()) {
                    System.out.println("Por favor introduzca un valor válido");
                    myObj.next();
                }
                sel = myObj.nextInt();
                myObj.nextLine();
            }
            if (!this.armasDis.contains(this.armas.get(sel))) {
                System.out.println("Arma " + this.armas.get(sel).getNombre() + " eliminada");
                this.delArma(this.armas.get(sel));
            } else {
                System.out.println("No puede borrar este equipo porque lo ha publicado en una oferta");
            }

        } else if (option == 2) {

            for (int i = 0; i < this.armaduras.size(); i++) {
                System.out.println(i + " " + (this.armaduras.get(i)).toString());
            }
            while (sel < 0 | sel > (this.armaduras.size() - 1)) {
                while (!myObj.hasNextInt()) {
                    System.out.println("Por favor introduzca un valor válido");
                    myObj.next();
                }
                sel = myObj.nextInt();
                myObj.nextLine();
            }
            if (!this.armadurasDis.contains(this.armaduras.get(sel))) {
                System.out.println("Armadura " + this.armaduras.get(sel).getNombre() + " eliminada");
                this.delArmadura(this.armaduras.get(sel));
            } else {
                System.out.println("No puede borrar este equipo porque lo ha publicado en una oferta");
            }
        }
    }

    public Object addEquipo(Scanner myObj) {
        System.out.println("1. Añadir arma");
        System.out.println("2. Añadir armadura");

        int option = 0;
        while (option < 1 | option > 2) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un número entre (1 - 2)");
                myObj.next();
            }
            option = myObj.nextInt();
            myObj.nextLine();
        }

        System.out.println("Nombre del equipo");
        String name = myObj.nextLine();

        String categoria = "";
        while (!categoria.equals("c") & !categoria.equals("r") & !categoria.equals("e") & !categoria.equals("l")) {
            System.out.println("Categoria del equipo (c:Común, r:Raro, e:Épico, l:Legendario)");
            categoria = myObj.nextLine();
        }

        int modA = -1;
        while (modA < 0 | modA > 3) {
            System.out.println("Modificador de ataque (0 - 3)");
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un número entero");
                myObj.next();
            }
            modA = myObj.nextInt();
            myObj.nextLine();
        }

        int modD = -1;
        while (modD < 0 | modD > 3) {
            System.out.println("modificador de defensa (0 - 3)");
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un número entero");
                myObj.next();
            }
            modD = myObj.nextInt();
            myObj.nextLine();
        }

        if (option == 1) {

            boolean dosmanos = this.setArma(myObj);
            return new Arma(name, modA, modD, categoria, dosmanos);
            //this.armas.add(arma);
            //System.out.println("Arma " + arma.getNombre() + " añadida");

        } else {
            return new Armadura(name, modA, modD, categoria);
            //this.armaduras.add(armadura);
            //System.out.println("Armadura " + armadura.getNombre() + " añadida");
        }

    }
    public String getNombre() {
        return name;
    }

    public ArrayList<Arma> getArmas() {
        return armas;
    }

    public void addArma(Arma arma) {
        this.armas.add(arma);
    }

    public ArrayList<Armadura> getArmaduras() {
        return armaduras;
    }

    public void addArmadura(Armadura armadura) {
        this.armaduras.add(armadura);
    }

    public ArrayList<Arma> getArmasDis() {
        return armasDis;
    }

    public void delArmasDis(Arma armasDis) {
        this.armasDis.remove(armasDis);
    }

    public void addArmasDis(Arma armasDis) {
        this.armasDis.add(armasDis);
    }

    public ArrayList<Armadura> getArmadurasDis() {
        return armadurasDis;
    }

    public void delArmadurasDis(Armadura ArmadurasDis) {
        this.armadurasDis.remove(ArmadurasDis);
    }

    public void addArmadurasDis(Armadura ArmadurasDis) {
        this.armadurasDis.add(ArmadurasDis);
    }

    public ArrayList<Object> getEsbirrosDis() {
        return esbirrosDis;
    }

    public void delEsbirrosDis(Object EsbirrosDis) {
        this.esbirrosDis.remove(EsbirrosDis);
    }

    public void addEsbirrosDis(Object EsbirrosDis) {
        this.esbirrosDis.add(EsbirrosDis);
    }


    public ArrayList<Object> getEsbirros() {
        return esbirros;
    }

    public void delEsbirro(Object o) {
        this.esbirros.remove(o);
    }

    public void addEsbirro(Object esbirro) {
        this.esbirros.add(esbirro);
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public void addOro(int cant) {
        this.oro += cant;
    }

    public void reduceOro(int cant) {
        this.oro -= cant;
    }

    public Object selectEquipo(Scanner myObj) {
        Object equipo;
        System.out.println("\nSeleccionar tipo");
        System.out.println("1. Arma");
        System.out.println("2. Armadura");
        int optE = -1;
        while (optE < 1 | optE > 2) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un número dentro del rango (1 - 2)");
                myObj.next();
            }
            optE = myObj.nextInt();
            myObj.nextLine();
        }
        int optE2 = -1;
        if (optE == 1) {
            System.out.println("Nombre ModDaño ModDefensa Cat DosManos");
            for(int i=0; i < this.armas.size();i++) {
                if (!this.armasDis.contains(this.armas.get(i))) {
                    System.out.println(this.armas.get(i));
                }
            }
            System.out.println("Selecciona el arma a añadir");
            while (optE2 < 0 | (optE2 > (this.armas.size() - 1))) {
                while (!myObj.hasNextInt()) {
                    System.out.println("Por favor introduzca un número dentro del rango (0 - "+this.armas.size()+")");
                    myObj.next();
                }
                optE2 = myObj.nextInt();
                myObj.nextLine();
                if (!(optE2 < 0) & !(optE2 > (this.armas.size() - 1))) {
                    if (this.armasDis.contains(this.armas.get(optE2))) {
                        optE2 = -1;
                    }
                }
            }
            equipo = this.armas.get(optE2);
            //this.delArma(optE2);
        } else {
            System.out.println("Nombre ModDaño ModDefensa Cat");
            for (int i = 0; i < this.armaduras.size(); i++) {
                if (!this.armadurasDis.contains(this.armaduras.get(i))) {
                    System.out.println(this.armaduras.get(i));
                }
            }
            System.out.println("Selecciona la armadura a añadir");
            while (optE2 < 0 | (optE2 > (this.armaduras.size() - 1))) {
                while (!myObj.hasNextInt()) {
                    System.out.println("Por favor introduzca un número dentro del rango (0 - " + this.armaduras.size() + ")");
                    myObj.next();
                }
                optE2 = myObj.nextInt();
                myObj.nextLine();
                
                if (!(optE2 < 0) & !(optE2 > (this.armaduras.size() - 1))) {
                    if (this.armadurasDis.contains(this.armaduras.get(optE2))) {
                        optE2 = -1;
                    }
                }
            }
            equipo = this.armaduras.get(optE2);
            //this.delArmadura(optE2);
        }
        return equipo;
    }
    
    public Oferta createOffer(Scanner myObj, Usuario creador) {
        int opt;
        Oferta offer = new Oferta(creador);
        boolean stop = false;
        while (!stop) {
            System.out.println("Crear Oferta:");
            offer.showOferta();
            System.out.println("1. Añadir equipo");
            System.out.println("2. Añadir esbirro");
            System.out.println("3. Modificar precio");
            System.out.println("4. Terminar");
            System.out.println("5. Cancelar");

            opt = -1;
            while (opt < 1 | opt > 5) {
                while (!myObj.hasNextInt()) {
                    System.out.println("Por favor introduzca un número dentro del rango (1 - 5)");
                    myObj.next();
                }
                opt = myObj.nextInt();
                myObj.nextLine();
            }

            switch (opt) {
                case (1): {
                    Object equipo = this.selectEquipo(myObj);
                    if (equipo instanceof Arma) {
                        offer.addArmaOffer((Arma) equipo);
                        this.addArmasDis((Arma) equipo);
                    } else if (equipo instanceof Armadura) {
                        offer.addArmaduraOffer((Armadura) equipo);
                        this.addArmadurasDis((Armadura) equipo);
                    }
                    break;
                } case (2): {
                    for (int i = 0; i < esbirros.size(); i++) {
                        if (!this.esbirrosDis.contains(esbirros.get(i))) {
                            System.out.println(i + " " + esbirros.get(i).getClass().getSimpleName());
                            if (esbirros.get(i) instanceof Demonio) {
                                ArrayList<Object> demonL = this
                                        .getEsbirrosSingle(((Demonio) esbirros.get(i)).getEsbirros());
                                for (int ii = 0; ii < demonL.size(); ii++) {
                                    System.out.println("\t" + demonL.get(ii).getClass().getSimpleName());
                                }
                            }
                        }
                    }
                    System.out.println("Selecciona el esbirro a añadir");
                    int optE2 = -1;
                    while (optE2 < 0 | optE2 > (this.esbirros.size() - 1)) {
                        while (!myObj.hasNextInt()) {
                            System.out.println("Por favor introduzca un número dentro del rango (0 - "
                                    + this.armaduras.size() + ")");
                            myObj.next();
                        }
                        optE2 = myObj.nextInt();
                        myObj.nextLine();
                    }
                    offer.addEsbirroOffer(esbirros.get(optE2));
                    if (esbirros.get(optE2) instanceof Demonio) {
                        ArrayList<Object> demonL = this.getEsbirrosSingle(((Demonio) esbirros.get(optE2)).getEsbirros());
                        for (int ii = 0; ii < demonL.size(); ii++) {
                            offer.addEsbirroOffer(demonL.get(ii));
                        }
                    } else {
                        offer.addEsbirroOffer(esbirros.get(optE2));
                    }

                    offer.addEsbirroCompact(esbirros.get(optE2));
                    this.addEsbirrosDis(esbirros.get(optE2));

                    break;
                } case (3): {
                    int precio = -5;
                    System.out.println("Introduzca el precio de su oferta");
                    while (precio < 0) {
                        while (!myObj.hasNextInt()) {
                            System.out.println("Por favor introduzca un número entero");
                            myObj.next();
                        }
                        precio = myObj.nextInt();
                        myObj.nextLine();
                    }
                    offer.setPrecio(precio);
                    break;
                } case (4): {
                    if (offer.getPrecio() >= 0) {
                        stop = true;
                    } else {
                        System.out.println("Por favor introduzca el precio de la oferta");
                    }
                    break;
                } case (5): {
                    for (int i = 0; i < offer.getArmasOffer().size(); i++) {
                        this.delArmasDis(offer.getArmasOffer().get(i));
                    }
                    for (int i = 0; i < offer.getArmadurasOffer().size(); i++) {
                        this.delArmadurasDis(offer.getArmadurasOffer().get(i));
                    }
                    for (int i = 0; i < offer.getEsbirrosCompact().size(); i++) {
                        this.delEsbirrosDis(offer.getEsbirrosCompact().get(i));
                    }
                    offer = null;
                    stop = true;
                    break;
                }
            }
        }
        return offer;
    }
}
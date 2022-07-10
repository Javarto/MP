package Users;

import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class
import entidades.personajes.*;

import java.util.Observable;
import java.util.Observer;
import entidades.oferta.Oferta;

public class Usuario extends UsuarioGeneral implements Observer {

    private String id;
    private Object personaje;
    private ArrayList<String> subscribedTo = new ArrayList<>();
    private ArrayList<String> notifications = new ArrayList<>();

    public Usuario(String id) {
        this.id = id;

    }

    // Usuario Options
    public int Options(Scanner myObj) {

        System.out.println("1. Añadir oro");
        System.out.println("2. Retirar oro");
        System.out.println("3. Añadir equipo");
        System.out.println("4. Eliminar equipo");
        System.out.println("5. Mostrar equipo");
        System.out.println("6. Crear oferta");
        System.out.println("7. Comprar ofertas");
        System.out.println("8. Subscribirse");
        System.out.println("9. Mostrar subscripciones");
        System.out.println("10. Darse de baja");
        System.out.println("11. Salir");

        int option = 0;
        while (option < 1 | option > 11) {
            System.out.println("Opcion: ");
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un número entero");
                myObj.next();
            }
            option = myObj.nextInt();
            myObj.nextLine();
        }

        return option;
    }

    public ArrayList<Object> getInfo(Scanner myObj) {

        String tipo = "";
        while (!tipo.equals("Cazador") & !tipo.equals("Vampiro") & !tipo.equals("Licantropo")) {
            System.out.println("Tipo: ");
            tipo = myObj.nextLine();

        }

        String nombre = "";
        while (nombre.equals("")) {
            System.out.println("Nombre: ");
            nombre = myObj.nextLine();
        }

        int oro = -1;
        while (oro < 0) {
            System.out.println("Oro: ");
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un número entero");
                myObj.next();
            }
            oro = myObj.nextInt();
            myObj.nextLine();

        }

        ArrayList<Object> info = new ArrayList<Object>();
        info.add(tipo);
        info.add(nombre);
        info.add(oro);
        
        return info;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPer(Object per) {
        this.personaje = per;
    }

    public Object createPer(Scanner myObj) {
        System.out.println("Creación de Personaje: ");
        ArrayList<Object> perData = this.getInfo(myObj);
        GetPersonajeFactory perFactory = new GetPersonajeFactory();
        Personaje p = perFactory.getPer(perData.get(0).toString());
        p.getData(myObj);
        p.setNombre(perData.get(1).toString());
        p.setOro((int) perData.get(2));
        boolean stop = false;
        while (!stop) {
            Object esb = p.createEsbirro(myObj, p);
            if (esb != null) {
                p.addEsbirro(esb);
            } else {
                stop = true;
            }
        }
        return p;
    }

    public Object getPers() {
        if (personaje instanceof Vampiro) {
            return (Vampiro) personaje;
        } else if (personaje instanceof Cazador) {
            return (Cazador) personaje;
        } else {
            return (Licantropo) personaje;
        }
    }

    public boolean checkTipo(int tipo) {
        boolean notify = false;

        return notify;
    }

    public void subscribeTo(String a) {
        if (!subscribedTo.contains(a)) {
            this.subscribedTo.add(a);
            System.out.println("Subscrito con éxito.");
        }
    }

    public void subsCat(Scanner myObj, int equipo) {
        System.out.println("1. Común");
        System.out.println("2. Rara");
        System.out.println("3. Épica");
        System.out.println("4. Legendaria");
        System.out.println("0. Cancelar");

        int opt = -2;
        while (opt < 0 | opt > 4) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero dentro de (0 - 4)");
                myObj.next();
            }
            opt = myObj.nextInt();
            myObj.nextLine();
        }

        String fin = Integer.toString(equipo);

        switch (opt) {
            case (0): {
                break;
            }
            case (1): {
                this.subscribeTo(fin + "c");
                break;
            }
            case (2): {
                this.subscribeTo(fin + "r");
                break;
            }
            case (3): {
                this.subscribeTo(fin + "e");
                break;
            }
            case (4): {
                this.subscribeTo(fin + "l");
                break;
            }
        }
    }

    public void subsMods(Scanner myObj, int equipo) {
        System.out.println("1. Modificación de Ataque");
        System.out.println("2. Modificación de Defensa");

        int opt = -2;
        while (opt < 1 | opt > 2) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero dentro de (1 - 2)");
                myObj.next();
            }
            opt = myObj.nextInt();
            myObj.nextLine();
        }

        System.out.println("Escriba el valor de la modificación (0 - 3)");
        int optE = -2;
        while (optE < 0 | optE > 3) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero dentro de (0 - 3)");
                myObj.next();
            }
            optE = myObj.nextInt();
            myObj.nextLine();
        }

        String fin = Integer.toString(equipo);
        fin += Integer.toString(opt);
        fin += Integer.toString(optE);

        this.subscribeTo(fin);
    }


    public ArrayList<String> getSubscibedTo() {
        return this.subscribedTo;
    }

    public void subsPrice(Scanner myObj) {
        System.out.println("1. Precio mínimo");
        System.out.println("2. Precio máximo");

        int opt = -2;
        while (opt < 1 | opt > 2) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero dentro de (1 - 2)");
                myObj.next();
            }
            opt = myObj.nextInt();
            myObj.nextLine();
        }

        System.out.println("Escriba el precio");
        int optE = -2;
        while (optE < 0) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero mayor que 0");
                myObj.next();
            }
            optE = myObj.nextInt();
            myObj.nextLine();
        }

        String fin = "";
        if (opt == 1) {
            fin += "min " + Integer.toString(optE);
        } else if (opt == 2) {
            fin += "max " + Integer.toString(optE);
        }

        this.subscribeTo(fin);
    }

    public void subsLeal(Scanner myObj) {
        System.out.println("1. BAJA");
        System.out.println("2. NORMAL");
        System.out.println("3. ALTA");
        System.out.println("0. Cancelar");

        int opt = -2;
        while (opt < 0 | opt > 3) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero dentro de (0 - 3)");
                myObj.next();
            }
            opt = myObj.nextInt();
            myObj.nextLine();
        }
        switch (opt) {
            case (0): {
                break;
            }
            case (1): {
                this.subscribeTo("BAJA");
                break;
            }
            case (2): {
                this.subscribeTo("NORMAL");
                break;
            }
            case (3): {
                this.subscribeTo("ALTA");
                break;
            }
        }
    }

    public void subsPer(Scanner myObj) {
        System.out.println("1. Vampiros");
        System.out.println("2. Cazadores");
        System.out.println("3. Licántropos");
        System.out.println("0. Cancelar");

        int opt = -2;
        while (opt < 0 | opt > 3) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero dentro de (0 - 3)");
                myObj.next();
            }
            opt = myObj.nextInt();
            myObj.nextLine();
        }
        switch (opt) {
            case (0): {
                break;
            }
            case (1): {
                this.subscribeTo("Vampiro");
                break;
            }
            case (2): {
                this.subscribeTo("Cazador");
                break;
            }
            case (3): {
                this.subscribeTo("Licantropo");
                break;
            }
        }
    }

    public void subsEsbirro(Scanner myObj) {
        System.out.println("1. Ghouls");
        System.out.println("2. Humanos");
        System.out.println("3. Demonios");
        System.out.println("0. Cancelar");

        int opt = -2;
        while (opt < 0 | opt > 3) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero dentro de (0 - 3)");
                myObj.next();
            }
            opt = myObj.nextInt();
            myObj.nextLine();
        }
        switch (opt) {
            case (0): {
                break;
            }
            case (1): {
                this.subscribeTo("Ghouls");
                break;
            }
            case (2): {
                this.subscribeTo("Humanos");
                break;
            }
            case (3): {
                this.subscribeTo("Demonios");
                break;
            }
        }
    }

    public void subsTipo(Scanner myObj) {
        System.out.println("1. Esbirros");
        System.out.println("2. Armas");
        System.out.println("3. Armaduras");
        System.out.println("0. Cancelar");

        int opt = -2;
        while (opt < 0 | opt > 6) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero dentro de (0 - 6)");
                myObj.next();
            }
            opt = myObj.nextInt();
            myObj.nextLine();
        }
        switch (opt) {
            case (0): {
                break;
            } case (1): {
                this.subscribeTo("0");
                break;
            } case (2): {
                this.subscribeTo("1");
                break;
            } case (3): {
                this.subscribeTo("2");
                break;
            }
        }
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public boolean checkOffer(Object offer) {
        boolean subs = false;
        Oferta o = (Oferta) offer;
        ArrayList<String> res = o.getStrings();
        for (int i = 0; i < res.size(); i++) {
            if (this.subscribedTo.contains(res.get(i))) {
                subs = true;
                break;
            }
        }
        return subs;
    }

    public void delNotification(Object index) {
        this.notifications.remove(index);
    }

    public boolean priceSub() {
        boolean res = false;
        for (int i = 0; i < this.subscribedTo.size(); i++) {
            if (this.subscribedTo.get(i).startsWith("min") | this.subscribedTo.get(i).startsWith("max")) {
                res = true;
                break;
            }
        }
        return res;
    }

    public boolean checkPrice(int price) {
        String[] dataSpl;
        boolean res = false;
        for (int i = 0; i < this.subscribedTo.size(); i++) {
            if (this.subscribedTo.get(i).startsWith("min")) {
                dataSpl = this.subscribedTo.get(i).split(" ");
                if (Integer.valueOf(dataSpl[1]) <= price) {
                    res = true;
                } else {
                    res = false;
                }
            } else if (this.subscribedTo.get(i).startsWith("max")) {
                dataSpl = this.subscribedTo.get(i).split(" ");
                if (Integer.valueOf(dataSpl[1]) >= price) {
                    res = true;
                } else {
                    res = false;
                }
            }
        }
        return res;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(((Oferta)arg).getVendedor().getId().equals(this.getId()))) {
            boolean subs = this.checkOffer(arg);
            boolean priceCheck = this.priceSub();
            if (priceCheck & !subs) {
                int price = ((Oferta) arg).getPrecio();
                if (this.checkPrice(price)) {
                    this.notifications.add("Nueva Oferta interesante de " + ((Oferta) arg).getVendedor().getId());
                }
            } 

            else if (subs) {
                this.notifications.add("Nueva Oferta interesante de " + ((Oferta)arg).getVendedor().getId());
            }
        }
        
    }
}

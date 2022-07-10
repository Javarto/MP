import entidades.equipo.armaduras.Armadura;
import entidades.equipo.armas.Arma;
import entidades.oferta.Oferta;
import entidades.oferta.Venta;
import entidades.personajes.*;

import java.util.Scanner; // Import the Scanner class

import Users.Admin;
import Users.Usuario;
import Users.UsuarioGeneral;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Observable;
import java.util.Observer;

public class Main extends Observable implements java.io.Serializable{
    private ArrayList<Oferta> offersToVal;
    private ArrayList<Oferta> offersToBuy;
    private ArrayList<Venta> ventas;
    private ArrayList<Object> usuarios;
    private ArrayList<Usuario> banned;
    private ArrayList<Observer> observers;

    public Main() {
        this.offersToVal = new ArrayList<Oferta>();
        this.offersToBuy = new ArrayList<Oferta>();
        this.ventas = new ArrayList<Venta>();
        this.usuarios = new ArrayList<Object>();
        this.banned = new ArrayList<Usuario>();
        this.observers = new ArrayList<>();
    }

    public static void main(String[] args) {
        Main main = null;
        // MAIN Observado observado = new Observado();
        // USUARIO Observador observador = new Observador();

        Scanner myObj = new Scanner(System.in);
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("main.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            main = (Main) in.readObject();

            in.close();
            file.close();
        }

        catch (IOException ex) {
            main = new Main();
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        for (int i=0; i < main.observers.size(); i++) {
            main.addObserver(main.observers.get(i));
        }

        while (true) {
            //System.out.println("Observers: " + main.countObservers());
            int option = main.Options(myObj);
            UsuarioGeneral userG = null;

            if (option == 1) {
                userG = (UsuarioGeneral)main.registrar(myObj);
                main.addNewUser(userG);

            } else if (option == 2) {
                System.out.println("\nNick: ");
                String nick = myObj.nextLine(); // Read user input

                System.out.println("\nPassword: ");
                String password = myObj.nextLine(); // Read user input

                Object signin = main.login(nick, password);
                if (signin != null) {
                    userG = (UsuarioGeneral) signin;
                }
            } else if (option == 3) {
                System.out.println("Saliendo de la app.");
                main.saveObject("main", main);
                break;
            }

            if (userG != null) {
                while (userG != null) {

                    if ((userG instanceof Usuario)) {
                        Usuario user = (Usuario) userG;
                        // Añadir observer
                        Personaje per = null;
                        per = (Personaje)user.getPers();
                        if (per == null) {
                    
                            per = (Personaje)user.createPer(myObj);
                            user.setPer(per);

                        } else {
                            for (int x = 0; x < user.getNotifications().size(); x++) {
                                System.out.println(user.getNotifications().get(x));
                                user.delNotification(user.getNotifications().get(x));
                            }
                            System.out.println("Personaje: " + per.getClass().getSimpleName());
                            System.out.println("Oro actual: " + per.getOro());
                            option = user.Options(myObj);

                            if (option == 11) {

                                System.out.println("Sesión cerrada.");

                                //main.saveObject(filename, user);

                                userG = null;

                            } else if (option == 10) {
                                main.delUser(userG);
                                System.out.println("Cuenta eliminada.");
                                userG = null;
                            } else if (option == 1) {

                                System.out.println("Oro actual: " + per.getOro());
                                System.out.println("Cantidad de oro a añadir: ");
                                while (!myObj.hasNextInt()) {
                                    System.out.println("Por favor introduzca un número entero");
                                    myObj.next();
                                }
                                int cant = myObj.nextInt();
                                myObj.nextLine();

                                per.addOro(cant);
                                System.out.println("Oro actual: " + per.getOro());

                            } else if (option == 2) {

                                System.out.println("Oro actual: " + per.getOro());
                                System.out.println("Cantidad de oro a retirar: ");
                                while (!myObj.hasNextInt()) {
                                    System.out.println("Por favor introduzca un número entero");
                                    myObj.next();
                                }
                                int cant = myObj.nextInt();
                                myObj.nextLine();

                                per.reduceOro(cant);
                                System.out.println("Oro actual: " + per.getOro());
                            } else if (option == 3) {
                                Object equipo = per.addEquipo(myObj);
                                if (equipo instanceof Arma) {
                                    per.addArma((Arma)equipo);
                                } else if (equipo instanceof Armadura) {
                                    per.addArmadura((Armadura)equipo);
                                }
                            } else if (option == 4) {
                                per.deleteEquipo(myObj);
                            } else if (option == 5) {
                                // System.out.println(Arrays.toString(per.getArmasActivas()));
                                int opt = 0;
                                while (opt < 1 | opt > 2) {
                                    System.out.println("1. Mostrar armas");
                                    System.out.println("2. Mostrar armaduras");
                                    while (!myObj.hasNextInt()) {
                                        System.out.println("Por favor introduzca un número entero");
                                        myObj.next();
                                    }
                                    opt = myObj.nextInt();
                                    myObj.nextLine();
                                }

                                if (opt == 1) {
                                    System.out.println(per.getArmas());
                                } else if (opt == 2) {
                                    System.out.println(per.getArmaduras());
                                }
                            } else if (option == 6) {
                                Oferta offer = per.createOffer(myObj, user);
                                if (offer != null) {
                                    System.out.println("Oferta añadida para revisión");
                                    main.addOfferToVal(offer);
                                }
                            } else if (option == 7) {
                                ArrayList<Oferta> offers = main.getOffersToBuy();
                                for (int i = 0; i < offers.size();i++) {
                                    if (!offers.get(i).getVendedor().equals(user.getId())) {
                                        System.out.println(i + " **********************");
                                        offers.get(i).showOferta();
                                    }
                                }
                                int opt = -2;
                                while (opt < -1 | opt > (offers.size() - 1)) {
                                    System.out.println("Oferta a comprar (-1 para cancelar)");
                                    while (!myObj.hasNextInt()) {
                                        System.out.println("Por favor introduzca un numero dentro de (0 - " + (offers.size() - 1) +")");
                                        myObj.next();
                                    }
                                    opt = myObj.nextInt();
                                    myObj.nextLine();
                                }
                                if (opt != -1) {
                                    Oferta offer = offers.get(opt);
                                    Personaje vendedor = (Personaje) main.getPerById(offer.getVendedor().getId());
                                    boolean buyed = per.buyOffer(offer);
                                    if (buyed) {
                                        for (int i = 0; i < offer.getArmasOffer().size(); i++) {
                                            vendedor.delArma(offer.getArmasOffer().get(i));
                                        }
                                        for (int i = 0; i < offer.getArmadurasOffer().size(); i++) {
                                            vendedor.delArmadura(offer.getArmadurasOffer().get(i));
                                        }
                                        for (int i = 0; i < offer.getEsbirrosCompact().size(); i++) {
                                            vendedor.delEsbirro(offer.getEsbirrosCompact().get(i));
                                        }
                                        vendedor.addOro(offer.getPrecio());
                                        main.delOfferToBuy(offer);
                                        main.addVenta(new Venta(offer, user.getId()));
                                    }
                                }
                            } else if (option == 8) {
                                System.out.println("¿A que se quiere subscribir?");
                                System.out.println("1. Tipo");
                                System.out.println("2. Categoría");
                                System.out.println("3. Valor");
                                System.out.println("4. Lealtad");
                                System.out.println("5. Tipo de usuario");
                                System.out.println("6. Tipo de esbirro");
                                System.out.println("7. Precio");
                                System.out.println("0. Cancelar");

                                int opt = -2;
                                while (opt < 0 | opt > 7) {
                                    while (!myObj.hasNextInt()) {
                                        System.out.println("Por favor introduzca un numero dentro de (0 - 7)");
                                        myObj.next();
                                    }
                                    opt = myObj.nextInt();
                                    myObj.nextLine();
                                }
                                switch (opt) {
                                    case (0): {
                                        break;
                                    } case (1): {
                                        user.subsTipo(myObj);
                                        break;
                                    } case (2): {
                                        System.out.println("1. Arma");
                                        System.out.println("2. Armadura");
                                        int optt = 0;
                                        while (optt < 1 | optt > 2) {
                                            while (!myObj.hasNextInt()) {
                                                System.out.println("Por favor introduzca un numero dentro de (1 - 2)");
                                                myObj.next();
                                            }
                                            optt = myObj.nextInt();
                                            myObj.nextLine();
                                        }
                                        user.subsCat(myObj, optt);
                                        break;
                                    } case (3): {
                                        System.out.println("1. Arma");
                                        System.out.println("2. Armadura");
                                        int optt = 0;
                                        while (optt < 1 | optt > 2) {
                                            while (!myObj.hasNextInt()) {
                                                System.out.println("Por favor introduzca un numero dentro de (1 - 2)");
                                                myObj.next();
                                            }
                                            optt = myObj.nextInt();
                                            myObj.nextLine();
                                        }
                                        user.subsMods(myObj, optt);
                                        break;
                                    } case (4): {
                                        user.subsLeal(myObj);
                                        break;
                                    } case (5): {
                                        user.subsPer(myObj);
                                        break;
                                    } case (6): {
                                        user.subsEsbirro(myObj);
                                        break;
                                    } case (7): {
                                        user.subsPrice(myObj);
                                        break;
                                    }
                                }
                            } else if (option == 9) {
                                System.out.println(user.getSubscibedTo());
                            }   
                        }

                    } else {
                        Admin adm = (Admin) userG;
                        System.out.println("\nLogged In as ADMIN");
                        option = adm.Options(myObj);
                        if (option == 5) {
                            System.out.println("Sesión cerrada.");
                            userG = null;

                        } else if (option == 1) {
                            Usuario banUser = adm.banUser(main.getUsuarios(), myObj);
                            if (banUser != null) {
                                main.addBanUser(banUser);
                            }
                        } else if (option == 2) {
                            Usuario unbanUser = adm.unbanUser(main.getBannedUsers(), myObj);
                            if (unbanUser != null) {
                                main.delBanUser(unbanUser);
                            }
                        } else if (option == 3) {
                            while (true) {
                                Oferta offer = adm.validateOffers(main.getOffersToVal(), myObj);
                                if (offer != null) {
                                    main.addOfferToBuy(offer);
                                    main.delOfferToVal(offer);
                                } else {
                                    break;
                                }
                            }
                        } else if (option == 4) {
                            adm.verVentas(main.getVentas());
                        }
                    }
                }
            }

        }

    }

    public Object getPerById(String userId) {
        Object per = null;
        for (int i = 0; i < this.usuarios.size(); i ++) {
            per = this.usuarios.get(i);
            if (per instanceof Usuario) {
                if (((Usuario) per).getId().equals(userId)) {
                    break;
                }
            }
        }
        per = ((Usuario) per).getPers();
        return per;
    }
    

    public Object registrar(Scanner obj) {
        boolean exit = false;
        System.out.println("\nAdmin? (y/n): ");
        String adm = "";
        while(!exit) {
            adm = obj.nextLine(); // Read user input
            if (adm.equals("n") | adm.equals("y")) {
                exit = true;
                break;
            }
        }

        System.out.println("\nNombre: ");
        String name = obj.nextLine(); // Read user input

        System.out.println("\nNick: ");
        String nick = "";
        exit = false;
        while (!exit) {
            nick = obj.nextLine(); // Read user input
            if (!nick.equals("")) {
                if (this.usuarios.size() > 0) {
                    for (int i = 0; i < this.usuarios.size(); i++) {
                        if (((UsuarioGeneral) this.usuarios.get(i)).getNick().equals(nick)) {
                            System.out.println("Ese nick ya existe.");
                            exit = false;
                            break;
                        } else {
                            exit = true;
                        }
                    }
                } else {
                    exit = true;
                }
            } else {
                System.out.println("Este campo no puede ser vacío");
            }
        }

        System.out.println("\nPassword: ");
        String password;
        while (true) {
            password = obj.nextLine();
            if ( 7 < password.length()  & password.length() < 13) {
                break;
            } else {
                System.out.println("Por favor escriba una contraseña entre 8 y 12 carácteres");
            }
        }
        
        String id;
        
        if (adm.equals("n")){
            id = new StringBuilder().append(this.getChar()).append(this.genInt()).append(this.genInt())
                    .append(this.getChar()).append(this.getChar()).toString();
            Usuario new_user = new Usuario(id);
            new_user.setName(name);
            new_user.setNick(nick);
            new_user.setPassword(password);

            System.out.println("\t Nuevo Usuario creado");
            System.out.println("\nNombre: " + new_user.getName());
            System.out.println("Nick: " + new_user.getNick());
            System.out.println("Contraseña: " + new_user.getPassword());
            //this.addObserver(new_user);
            this.observers.add(new_user);
            return new_user;
        } else {
            Admin new_user = new Admin();
            new_user.setName(name);
            new_user.setNick(nick);
            new_user.setPassword(password);
            System.out.println("\t Nuevo Usuario creado");
            System.out.println("\nNombre: " + new_user.getName());
            System.out.println("Nick: " + new_user.getNick());
            System.out.println("Contraseña: " + new_user.getPassword());

            return new_user;
        }
    }
    
    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void addVenta(Venta venta) {
        this.ventas.add(venta);
    }

    public ArrayList<Object> getUsuarios() {
        return this.usuarios;
    }

    public ArrayList<Usuario> getBannedUsers() {
        return this.banned;
    }

    public void addNewUser(Object user) {
        this.usuarios.add(user);
    }

    public void delUser(Object user) {
        this.usuarios.remove(user);
    }

    public void addBanUser(Usuario user) {
        this.banned.add(user);
    }

    public void delBanUser(Usuario user) {
        this.banned.remove(user);
    }

    public void addOfferToBuy(Oferta offer) {
        this.offersToBuy.add(offer);
        setChanged();
        // Notificamos a los observadores y le enviamos el nuevo valor
        notifyObservers(offer);
        // notifyObservers(); Este metodo solo notifica que hubo cambios en el objeto
    }

    public void delOfferToBuy(Oferta offer) {
        this.offersToBuy.remove(offer);
    }

    public ArrayList<Oferta> getOffersToBuy() {
        return this.offersToBuy;
    }


    public void addOfferToVal(Oferta offer) {
        this.offersToVal.add(offer);
    }

    public void delOfferToVal(Oferta offer) {
        this.offersToVal.remove(offer);
    }

    public ArrayList<Oferta> getOffersToVal() {
        return this.offersToVal;
    }

    public int genInt() {
        Random rnd = new Random();
        return rnd.nextInt(9);
    }

    public char getChar() {
        Random rnd = new Random();
        char c = (char) ('a' + rnd.nextInt(26));
        return c;
    }


    /**
     * 
     */
    public int Options(Scanner myObj) {
        System.out.println("Bienvenido \n");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar Sesión");
        System.out.println("3. Salir");

        int option = 9;
        while (option != 1 & option != 2 & option != 3) {
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

    /**
     * 
     */

    public void showUsersBanned() {
        Object user;
        for (int i = 0; i < this.banned.size(); i++) {
            user = this.banned.get(i);
            System.out.println(((Usuario) user).getNick() + " " + ((Usuario) user).getId());
        }
    }

    public boolean checkBan(Usuario user) {

        Usuario banned;
        boolean answer = false;
        for (int i = 0; i < this.banned.size(); i++) {
            banned = this.banned.get(i);
            if (banned.equals(user)) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    public Object login(String nick, String password) {
        Object signin = null;
        boolean check = false;
        for (int i = 0; i < this.usuarios.size(); i++) {
            signin = this.usuarios.get(i);
            if (((UsuarioGeneral)signin).getNick().equals(nick) & ((UsuarioGeneral)signin).getPassword().equals(password)) {
                if (signin instanceof Usuario) {
                    check = !this.checkBan((Usuario) signin);
                    if (!check) {
                        System.out.println("Usuario baneado.");
                    }
                } else {
                    check = true;
                }
                break;
            }
        }
        if (check) {
            return signin;
        } else {
            return null;
        }     
    }

    public Object readObject(String filename) {
        Object obj;
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("main.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            obj = (Object)in.readObject();

            in.close();
            file.close();
            return obj;
        }

        catch (IOException ex) {
            System.out.println(ex);
            return null;
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
            return null;
        }
    }

    

    public void saveObject(String filename, Object objeto) {
        // Save user object in a file
        try {
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename +".ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(objeto);

            out.close();
            file.close();

            System.out.println("Datos Guardados.");

        }

        catch (IOException ex) {
            System.out.println(ex);
        }
    }


}
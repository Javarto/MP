package Users;
import java.util.ArrayList;
import java.util.Scanner;

import entidades.oferta.Oferta;
import entidades.oferta.Venta;

public class Admin extends UsuarioGeneral{

    public Admin() {

    }
    
    // Admin Options
    public int Options(Scanner myObj) {

        System.out.println("1. Banear usuario");
        System.out.println("2. Desbanear usuario");
        System.out.println("3. Validar ofertas");
        System.out.println("4. Ver ventas");
        System.out.println("5. Salir");

        int option = 0;
        while (option < 1 | option > 5) {
            System.out.println("Opcion: ");
            option = myObj.nextInt();
            myObj.nextLine();
        }

        return option;
    }

    public Usuario unbanUser(ArrayList<Usuario> usuarios, Scanner myObj) {
        Object user = null;
        for (int i = 0; i < usuarios.size(); i++) {
            user = usuarios.get(i);
            if (user instanceof Usuario) {
                System.out.println(((Usuario) user).getNick() + " " + ((Usuario) user).getId());
            }
        }
        System.out.println("Escriba ID a desbanear o 'q' para cancelar");
        String userMad = myObj.nextLine();
        Usuario banUser = null;
        if (!userMad.equals("q")) {
            for (int i = 0; i < usuarios.size(); i++) {
                user = usuarios.get(i);
                if (user instanceof Usuario) {
                    if (((Usuario) user).getId().equals(userMad)) {
                        banUser = (Usuario) user;
                        break;
                    }
                }
            }
        }
        return banUser;
        
    }

    public Usuario banUser(ArrayList<Object> usuarios, Scanner myObj) {
        Object user = null;
        for (int i = 0; i< usuarios.size(); i++) {
            user = usuarios.get(i);
            if (user instanceof Usuario) {
                System.out.println(((Usuario)user).getNick() + " " + ((Usuario)user).getId());
            }
        }
        System.out.println("Escriba ID a banear o 'q' para cancelar");
        String userMad = myObj.nextLine();
        Usuario banUser = null;
        if (!userMad.equals("q")) {
            for (int i = 0; i< usuarios.size(); i++) {
                user = usuarios.get(i);
                if (user instanceof Usuario) {
                    if (((Usuario) user).getId().equals(userMad)) {
                        banUser = (Usuario)user;
                        break;
                    }
                }
            }
        }
        return banUser;
    }

    public Oferta validateOffers(ArrayList<Oferta> ofertas, Scanner myObj) {
        Object offer = null;
        for (int i = 0; i < ofertas.size(); i++) {
            offer = ofertas.get(i);
            System.out.println(i + ") ***********************");
            ((Oferta)offer).showOferta();
        }
        System.out.println("Escriba num de oferta para validar o '-1' para cancelar");
        int offerMad = -2;
        while (offerMad < -1 | offerMad > (ofertas.size() -1)) {
            while (!myObj.hasNextInt()) {
                System.out.println("Por favor introduzca un numero válido");
                myObj.next();
            }
            offerMad = myObj.nextInt();
            myObj.nextLine();
        }
        Oferta valOffer = null;
        if (offerMad != -1) {
            valOffer = ofertas.get(offerMad);
        }
        return valOffer;
    }

    public void verVentas(ArrayList<Venta> ventas) {
        for (int i = 0; i < ventas.size(); i++) {
            ventas.get(i).showVenta();
        }
    }
    
}

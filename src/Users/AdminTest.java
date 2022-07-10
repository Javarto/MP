package Users;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import entidades.equipo.armaduras.Armadura;
import entidades.equipo.armas.Arma;
import entidades.esbirros.*;
import entidades.oferta.Oferta;
import entidades.pactos.Pacto;
import entidades.personajes.Cazador;

public class AdminTest {

    @Test
    public void testOptions(){
        // valores dentro de rango
        Scanner scanner;
        String data = "";
        Admin adm = new Admin();
        for (int i = 1; i < 5; i++) {
            data = Integer.toString(i) + "\n";
            System.setIn(new ByteArrayInputStream(data.getBytes()));

            scanner = new Scanner(System.in);

            assertEquals(i, adm.Options(scanner));
        }

        //Valores por debajo
        data = "";
        for (int i = -7; i < 2; i++) {
            data += Integer.toString(i) + "\n";
        }
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);
        assertEquals(1, adm.Options(scanner));

        //Valores por encima
        data = "";
        for (int i = 10; i > 4; i--) {
            data += Integer.toString(i) + "\n";
        }
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);
        assertEquals(5, adm.Options(scanner));
    }

    @Test
    public void testBanUser() throws Throwable{
        ArrayList<Object> users = new ArrayList<>();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        users.add(a);

        Usuario b = new Usuario("id1");
        b.setNick("GC");
        b.setPassword("12345678");

        users.add(b);

        Admin adm = new Admin();

        String data = "id1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);

        assertEquals(a, adm.banUser(users, scanner));

    }

    @Test
    public void testUnbanUser() throws Throwable{
        ArrayList<Object> bannedUsers = new ArrayList<>();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        bannedUsers.add(a);

        Usuario b = new Usuario("id1");
        b.setNick("GC");
        b.setPassword("12345678");

        bannedUsers.add(b);

        Admin adm = new Admin();

        String data = "id1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);

        assertEquals(a, adm.banUser(bannedUsers, scanner));

    }

    @Test
    public void testValidateOffers() throws Throwable{
        Cazador p = new Cazador();
        Usuario a = new Usuario("id1");
        Usuario b = new Usuario("id2");

        a.setPer(p);
        b.setPer(p);

        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        // demon.addEsbirro(demon);

        Arma arma2 = new Arma("Hoz", 0, 0, "c", false);
        
        Oferta offer = new Oferta(a);
        offer.addArmaOffer(arma2);
        offer.addEsbirroOffer(ghoul);
        offer.setPrecio(500);

        Oferta offer2 = new Oferta(b);
        offer.addArmaOffer(arma2);
        offer.addEsbirroOffer(ghoul);
        offer.setPrecio(500);

        ArrayList<Oferta> ofertas = new ArrayList<>();

        ofertas.add(offer);
        ofertas.add(offer2);

        Admin adm = new Admin();

        String data = "1\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Scanner scanner = new Scanner(System.in);

        assertEquals(offer2, adm.validateOffers(ofertas, scanner));
    }
}

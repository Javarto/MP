package Users;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

import entidades.equipo.armaduras.Armadura;
import entidades.equipo.armas.Arma;
import entidades.esbirros.*;
import entidades.oferta.Oferta;
import entidades.pactos.Pacto;
import entidades.personajes.*;

public class UsuarioTest {
    @Test
    public void testCheckOfferEsbirro() {
        Usuario user = new Usuario("id1");

        Cazador p = new Cazador();
        Demonio demon = new Demonio();
        demon.setNombre("Satan");
        demon.setSalud(3);
        Pacto pacto = new Pacto("pacto rancio", p, demon);
        demon.setPacto(pacto);

        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        demon.addEsbirro(human);
        // demon.addEsbirro(demon);

        Arma arma2 = new Arma("Hoz", 0, 0, "c", false);
  
        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma2);
        offer.addEsbirroOffer(demon);
        offer.setPrecio(500);

        Usuario user2 = new Usuario("id2");
        user2.subscribeTo("0");

        assertEquals(true, user2.checkOffer(offer));

        Oferta offer2 = new Oferta(user);
        offer2.addArmaOffer(arma2);
        offer2.setPrecio(500);

        assertEquals(false, user2.checkOffer(offer2));
    }

    @Test
    public void testCheckOfferArma() {
        Usuario user = new Usuario("id1");

        Cazador p = new Cazador();
        Demonio demon = new Demonio();
        demon.setNombre("Satan");
        demon.setSalud(3);
        Pacto pacto = new Pacto("pacto rancio", p, demon);
        demon.setPacto(pacto);

        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        demon.addEsbirro(human);
        // demon.addEsbirro(demon);

        Arma arma2 = new Arma("Hoz", 0, 0, "c", false);
        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma2);
        offer.addEsbirroOffer(demon);
        offer.setPrecio(500);

        Usuario user2 = new Usuario("id2");
        user2.subscribeTo("1");

        assertEquals(true, user2.checkOffer(offer));

        Oferta offer2 = new Oferta(user);
        offer.setPrecio(500);

        assertEquals(false, user2.checkOffer(offer2));
    }

    @Test
    public void testCheckOfferArmadura() {
        Usuario user = new Usuario("id1");

        Cazador p = new Cazador();
        Demonio demon = new Demonio();
        demon.setNombre("Satan");
        demon.setSalud(3);
        Pacto pacto = new Pacto("pacto rancio", p, demon);
        demon.setPacto(pacto);

        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        demon.addEsbirro(human);
        // demon.addEsbirro(demon);

        Arma arma2 = new Arma("Hoz", 0, 0, "c", false);
        Armadura armadura = new Armadura("Pechera", 0, 2, "r");

        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma2);
        offer.addArmaduraOffer(armadura);
        offer.addEsbirroOffer(demon);
        offer.setPrecio(500);

        Usuario user2 = new Usuario("id2");
        user2.subscribeTo("2");

        assertEquals(true, user2.checkOffer(offer));

        Oferta offer2 = new Oferta(user);
        offer.setPrecio(500);

        assertEquals(false, user2.checkOffer(offer2));
    }

    @Test
    public void testCheckOfferValor() {
        Usuario user = new Usuario("id1");

        Cazador p = new Cazador();
        Demonio demon = new Demonio();
        demon.setNombre("Satan");
        demon.setSalud(3);
        Pacto pacto = new Pacto("pacto rancio", p, demon);
        demon.setPacto(pacto);

        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        demon.addEsbirro(human);
        // demon.addEsbirro(demon);

        Arma arma = new Arma("Martillo", 3, 1, "l", true);
        Arma arma2 = new Arma("Hoz", 0, 0, "c", false);
        Armadura armadura = new Armadura("Pechera", 0, 2, "r");

        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma);
        offer.addArmaduraOffer(armadura);
        offer.addEsbirroOffer(demon);
        offer.setPrecio(500);

        Usuario user2 = new Usuario("id2");
        user2.subscribeTo("121");

        assertEquals(true, user2.checkOffer(offer));

        Oferta offer2 = new Oferta(user);
        offer2.addArmaOffer(arma2);
        offer2.addArmaduraOffer(armadura);
        offer2.addEsbirroOffer(demon);
        offer2.setPrecio(500);

        assertEquals(false, user2.checkOffer(offer2));
    }

    @Test
    public void testCheckOfferCategoria() {
        Usuario user = new Usuario("id1");

        Cazador p = new Cazador();
        Demonio demon = new Demonio();
        demon.setNombre("Satan");
        demon.setSalud(3);
        Pacto pacto = new Pacto("pacto rancio", p, demon);
        demon.setPacto(pacto);

        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        demon.addEsbirro(human);
        // demon.addEsbirro(demon);

        Arma arma2 = new Arma("Hoz", 0, 0, "c", false);
        Armadura armadura = new Armadura("Pechera", 0, 2, "r");

        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma2);
        offer.addArmaduraOffer(armadura);
        offer.addEsbirroOffer(demon);
        offer.setPrecio(500);

        Usuario user2 = new Usuario("id2");
        user2.subscribeTo("1l");

        assertEquals(false, user2.checkOffer(offer));

        user2.subscribeTo("1c");
        assertEquals(true, user2.checkOffer(offer));
    }

    @Test
    public void testCheckOfferLealtad() {
        Usuario user = new Usuario("id1");

        Cazador p = new Cazador();
        Demonio demon = new Demonio();
        demon.setNombre("Satan");
        demon.setSalud(3);
        Pacto pacto = new Pacto("pacto rancio", p, demon);
        demon.setPacto(pacto);

        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        demon.addEsbirro(human);
        // demon.addEsbirro(demon);

        Arma arma2 = new Arma("Hoz", 0, 0, "c", false);
        Armadura armadura = new Armadura("Pechera", 0, 2, "r");

        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma2);
        offer.addArmaduraOffer(armadura);
        offer.addEsbirroOffer(human);
        offer.setPrecio(500);

        Usuario user2 = new Usuario("id2");
        user2.subscribeTo("BAJA");

        assertEquals(true, user2.checkOffer(offer));

        Oferta offer2 = new Oferta(user);
        offer2.addArmaOffer(arma2);
        offer2.addArmaduraOffer(armadura);
        offer2.addEsbirroOffer(ghoul);
        offer2.setPrecio(500);

        assertEquals(false, user2.checkOffer(offer2));

        Humano human2 = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("ALTA");

        Oferta offer3 = new Oferta(user);
        offer3.addArmaOffer(arma2);
        offer3.addArmaduraOffer(armadura);
        offer3.addEsbirroOffer(human2);
        offer3.setPrecio(500);

        assertEquals(false, user2.checkOffer(offer3));
    }

    @Test
    public void testCheckOfferTipo() {
        Usuario user = new Usuario("id1");

        Cazador p = new Cazador();

        Demonio demon = new Demonio();
        demon.setNombre("Satan");
        demon.setSalud(3);
        Pacto pacto = new Pacto("pacto rancio", p, demon);
        demon.setPacto(pacto);

        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        demon.addEsbirro(human);

        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addEsbirroOffer(human);
        offer.setPrecio(500);

        Oferta offer2 = new Oferta(user);
        offer2.addEsbirroOffer(ghoul);
        offer2.setPrecio(500);

        Oferta offer3 = new Oferta(user);
        offer3.addEsbirroOffer(demon);
        offer3.setPrecio(500);

        Usuario user2 = new Usuario("id2");
        user2.subscribeTo("Humano");

        assertEquals(true, user2.checkOffer(offer));

        assertEquals(false, user2.checkOffer(offer2));

        assertEquals(false, user2.checkOffer(offer3));

        user2.subscribeTo("Ghoul");

        assertEquals(true, user2.checkOffer(offer));

        assertEquals(true, user2.checkOffer(offer2));

        assertEquals(false, user2.checkOffer(offer3));

        user2.subscribeTo("Demonio");

        assertEquals(true, user2.checkOffer(offer));

        assertEquals(true, user2.checkOffer(offer2));

        assertEquals(true, user2.checkOffer(offer3));
    }

    @Test
    public void testCheckOfferPersonaje() {
        Usuario user1 = new Usuario("id1");
        Usuario user2 = new Usuario("id2");
        Usuario user3 = new Usuario("id3");

        Cazador p = new Cazador();
        Vampiro vamp = new Vampiro();
        Licantropo lica = new Licantropo();

        user1.setPer(p);

        Oferta offer = new Oferta(user1);
        offer.setPrecio(500);

        user2.setPer(vamp);

        Oferta offer2 = new Oferta(user2);
        offer2.setPrecio(500);

        user3.setPer(lica);

        Oferta offer3 = new Oferta(user3);
        offer3.setPrecio(500);

        Usuario user4 = new Usuario("id2");
        user4.subscribeTo("Cazador");

        assertEquals(true, user4.checkOffer(offer));

        assertEquals(false, user4.checkOffer(offer2));

        assertEquals(false, user4.checkOffer(offer3));

        user4.subscribeTo("Vampiro");

        assertEquals(true, user4.checkOffer(offer));

        assertEquals(true, user4.checkOffer(offer2));

        assertEquals(false, user4.checkOffer(offer3));

        user4.subscribeTo("Licantropo");

        assertEquals(true, user4.checkOffer(offer));

        assertEquals(true, user4.checkOffer(offer2));

        assertEquals(true, user4.checkOffer(offer3));
    }

    @Test
    public void testCheckPrice() {
        
    }
    
    @Test
    public void testCreatePerOro() {
        Usuario a = new Usuario("");

        Vampiro p = new Vampiro();
        p.setNombre("Pepo");
        p.setOro(800);
        ((Vampiro) p).setEdad(472);
        ((Vampiro) p).setSangreAcumulada(8);
        String data = "Vampiro\n" + "Pepo\n" + "-100\n" + "800\n" + "472\n" + "8\n" + "3\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = a.createPer(scanner);

        assertEquals(p.getClass(), c.getClass());
        assertEquals(p.getNombre(), ((Vampiro) c).getNombre());
        assertEquals(p.getOro(), ((Vampiro) c).getOro());
        assertEquals(p.getEdad(), ((Vampiro) c).getEdad());
        assertEquals(p.getSangreAcumulada(), ((Vampiro) c).getSangreAcumulada());
    }

    @Test
    public void testCreatePerVampiro1() {
        Usuario a = new Usuario("");

        Vampiro p = new Vampiro();
        p.setNombre("Pepo");
        p.setOro(800);
        ((Vampiro) p).setEdad(472);
        ((Vampiro) p).setSangreAcumulada(8);
        String data = "Vampiro\n" + "Pepo\n" + "800\n" + "472\n" + "8\n" + "3\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = a.createPer(scanner);

        assertEquals(p.getClass(), c.getClass());
        assertEquals(p.getNombre(), ((Vampiro) c).getNombre());
        assertEquals(p.getOro(), ((Vampiro) c).getOro());
        assertEquals(p.getEdad(), ((Vampiro) c).getEdad());
        assertEquals(p.getSangreAcumulada(), ((Vampiro) c).getSangreAcumulada());
    }
    
    @Test
    public void testCreatePerVampiro2() {
        Usuario a = new Usuario("");

        Vampiro p = new Vampiro();
        p.setNombre("Pepo");
        p.setOro(800);
        ((Vampiro) p).setEdad(472);
        ((Vampiro) p).setSangreAcumulada(8);
        String data = "Vampiro\n" + "Pepo\n" + "800\n" + "-7\n" + "-100\n" + "472\n" + "-5\n" + "15\n" + "8\n" + "3\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = a.createPer(scanner);

        assertEquals(p.getClass(), c.getClass());
        assertEquals(p.getNombre(), ((Vampiro) c).getNombre());
        assertEquals(p.getOro(), ((Vampiro) c).getOro());
        assertEquals(p.getEdad(), ((Vampiro) c).getEdad());
        assertEquals(p.getSangreAcumulada(), ((Vampiro) c).getSangreAcumulada());
    }

    @Test
    public void testCreatePerVampiro3() {
        Usuario a = new Usuario("");

        Vampiro p = new Vampiro();
        p.setNombre("Pepo");
        p.setOro(800);
        ((Vampiro) p).setEdad(472);
        ((Vampiro) p).setSangreAcumulada(8);
        String data = "Vampiro\n" + "Pepo\n" + "800\n" + "472\n" + "-5\n" + "15\n" + "8\n" + "3\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = a.createPer(scanner);

        assertEquals(p.getClass(), c.getClass());
        assertEquals(p.getNombre(), ((Vampiro) c).getNombre());
        assertEquals(p.getOro(), ((Vampiro) c).getOro());
        assertEquals(p.getEdad(), ((Vampiro) c).getEdad());
        assertEquals(p.getSangreAcumulada(), ((Vampiro) c).getSangreAcumulada());
    }

    @Test
    public void testCreatePerCazador1() {
        Usuario a = new Usuario("");

        Cazador p = new Cazador();
        p.setNombre("Pepo");
        p.setOro(800);
        ((Cazador)p).setVoluntad(2);
        String data = "Cazador\n" +"Pepo\n" +"800\n" +"2\n" +"4\n";
        
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = a.createPer(scanner);

        assertEquals(p.getClass(), c.getClass());
        assertEquals(p.getNombre(), ((Cazador)c).getNombre());
        assertEquals(p.getOro(), ((Cazador) c).getOro());
        assertEquals(p.getVoluntad(), ((Cazador) c).getVoluntad());
    }

    @Test
    public void testCreatePerCazador2() {
        Usuario a = new Usuario("");

        Cazador p = new Cazador();
        p.setNombre("Pepo");
        p.setOro(800);
        ((Cazador) p).setVoluntad(2);
        String data = "Cazador\n" + "Pepo\n" + "800\n" + "-7\n" + "80\n" + "2\n" + "4\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = a.createPer(scanner);

        assertEquals(p.getClass(), c.getClass());
        assertEquals(p.getNombre(), ((Cazador) c).getNombre());
        assertEquals(p.getOro(), ((Cazador) c).getOro());
        assertEquals(p.getVoluntad(), ((Cazador) c).getVoluntad());
    }

    @Test
    public void testCreatePerLicántropo1() {
        Usuario a = new Usuario("");

        Licantropo p = new Licantropo();
        p.setNombre("Pepo");
        p.setOro(800);
        ((Licantropo) p).setRabia(2);
        String data = "Licantropo\n" + "Pepo\n" + "800\n" + "2\n" + "4\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = a.createPer(scanner);

        assertEquals(p.getClass(), c.getClass());
        assertEquals(p.getNombre(), ((Licantropo) c).getNombre());
        assertEquals(p.getOro(), ((Licantropo) c).getOro());
        assertEquals(p.getRabia(), ((Licantropo) c).getRabia());
    }

    @Test
    public void testCreatePerLicántropo2() {
        Usuario a = new Usuario("");

        Licantropo p = new Licantropo();
        p.setNombre("Pepo");
        p.setOro(800);
        ((Licantropo) p).setRabia(2);
        String data = "Licantropo\n" + "Pepo\n" + "800\n" + "-10\n" + "24\n" + "2\n" + "4\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = a.createPer(scanner);

        assertEquals(p.getClass(), c.getClass());
        assertEquals(p.getNombre(), ((Licantropo) c).getNombre());
        assertEquals(p.getOro(), ((Licantropo) c).getOro());
        assertEquals(p.getRabia(), ((Licantropo) c).getRabia());
    }
}

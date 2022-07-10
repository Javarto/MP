package entidades.personajes;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

import Users.Usuario;
import entidades.equipo.armaduras.Armadura;
import entidades.equipo.armas.Arma;
import entidades.esbirros.*;
import entidades.oferta.Oferta;
import entidades.pactos.Pacto;

public class PersonajeTest {
    @Test
    public void testAddEquipo1() {

        Arma arma = new Arma("Martillo", 3, 1, "l", true);

        Cazador p = new Cazador();
        String data = "caa\n" + "100\n" + "-100\n" + "1\n" + "Martillo\n" + "caa\n" + "100\n" + "-100\n" + "l\n"
                + "caa\n" + "100\n" + "-100\n" + "3\n" + "caa\n" + "100\n" + "-100\n" + "1\n" + "caa\n" + "100\n"
                + "-100\n" + "2\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.addEquipo(scanner);
    
        assertEquals(arma.getClass(), c.getClass());
        assertEquals(arma.getCategoria(), ((Arma)c).getCategoria());
        assertEquals(arma.getModificadorAtaque(), ((Arma) c).getModificadorAtaque());
        assertEquals(arma.getModificadorDefensa(), ((Arma) c).getModificadorDefensa());
        assertEquals(arma.getNombre(), ((Arma) c).getNombre());
        assertEquals(arma.isDosManos(), ((Arma) c).isDosManos());
    }

    @Test
    public void testAddEquipo2() {

        Arma arma = new Arma("Hoz", 0, 0, "c", false);

        Cazador p = new Cazador();
        String data = "80\n" + "-40\n" + "aa\n" + "1\n" + "Hoz\n" + "8\n" + "aa\n" + "c\n" + "caa\n" + "100\n" + "-100\n" + "0\n" 
                + "caa\n" + "100\n" + "-100\n" + "0\n" + "caa\n" + "100\n" + "-100\n" + "1\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.addEquipo(scanner);

        assertEquals(arma.getClass(), c.getClass());
        assertEquals(arma.getCategoria(), ((Arma) c).getCategoria());
        assertEquals(arma.getModificadorAtaque(), ((Arma) c).getModificadorAtaque());
        assertEquals(arma.getModificadorDefensa(), ((Arma) c).getModificadorDefensa());
        assertEquals(arma.getNombre(), ((Arma) c).getNombre());
        assertEquals(arma.isDosManos(), ((Arma) c).isDosManos());
    }

    @Test
    public void testAddEquipo3() {

        Armadura Armadura = new Armadura("Pechera", 0, 2, "r");

        Cazador p = new Cazador();
        String data = "a\n" + "caa\n" + "100\n" + "2\n" + "Pechera\n" + "caa\n" + "100\n" + "-100\n" + "r\n" + "caa\n"
                + "100\n" + "-100\n" + "0\n" + "caa\n" + "100\n" + "-100\n" + "2\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.addEquipo(scanner);

        assertEquals(Armadura.getClass(), c.getClass());
        assertEquals(Armadura.getCategoria(), ((Armadura) c).getCategoria());
        assertEquals(Armadura.getModificadorAtaque(), ((Armadura) c).getModificadorAtaque());
        assertEquals(Armadura.getModificadorDefensa(), ((Armadura) c).getModificadorDefensa());
        assertEquals(Armadura.getNombre(), ((Armadura) c).getNombre());
    }

    @Test
    public void testCreateEsbirro1() {
        Cazador p = new Cazador();
        Demonio demon = new Demonio();
        demon.setNombre("Satan");
        demon.setSalud(3);
        Pacto pacto = new Pacto("pacto rancio", p, demon);
        demon.setPacto(pacto);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        demon.addEsbirro(human);

        String data = "1\n" + "Satan\n" + "3\n" + "pacto rancio\n" + "3\n" + "Paco\n" + "1\n" + "BAJA\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.createEsbirro(scanner, p);

        assertEquals(demon.getClass(), c.getClass());
        assertEquals(demon.getEsbirros().size(), ((Demonio)c).getEsbirros().size());
        assertEquals(demon.getEsbirros().get(0).getClass(), ((Demonio) c).getEsbirros().get(0).getClass());
        assertEquals(demon.getPacto().getDescripcion(), ((Demonio) c).getPacto().getDescripcion());
        assertEquals(demon.getNombre(), ((Demonio) c).getNombre());
        assertEquals(demon.getSalud(), ((Demonio) c).getSalud());
    }

    @Test
    public void testCreateEsbirro2() {
        Cazador p = new Cazador();
        Demonio demon = new Demonio();
        demon.setNombre("Satan");
        demon.setSalud(3);
        Pacto pacto = new Pacto("pacto rancio", p, demon);
        demon.setPacto(pacto);

        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        demon.addEsbirro(human);
        demon.addEsbirro(demon);

        String data = "1\n" + "Satan\n" + "3\n" + "pacto rancio\n" + "3\n" + "Paco\n" + "1\n" + "BAJA\n" + "1\n"
                + "Satan\n" + "3\n" + "pacto rancio\n" + "3\n" + "Paco\n" + "1\n" + "BAJA\n" + "4\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.createEsbirro(scanner, p);

        assertEquals(demon.getClass(), c.getClass());
        assertEquals(demon.getEsbirros().size(), ((Demonio) c).getEsbirros().size());
        assertEquals(demon.getEsbirros().get(0).getClass(), ((Demonio) c).getEsbirros().get(0).getClass());
        assertEquals(demon.getPacto().getDescripcion(), ((Demonio) c).getPacto().getDescripcion());
        assertEquals(demon.getNombre(), ((Demonio) c).getNombre());
        assertEquals(demon.getSalud(), ((Demonio) c).getSalud());
    }

    @Test
    public void testCreateEsbirro3() {
        Cazador p = new Cazador();
        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);


        String data = "2\n" + "Tokyo\n" + "3\n" + "2\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.createEsbirro(scanner, p);

        assertEquals(ghoul.getClass(), c.getClass());
        assertEquals(ghoul.getNombre(), ((Ghoul) c).getNombre());
        assertEquals(ghoul.getSalud(), ((Ghoul) c).getSalud());
        assertEquals(ghoul.getDependencia(), ((Ghoul) c).getDependencia());
    }

    @Test
    public void testCreateEsbirro4() {
        Cazador p = new Cazador();
        Ghoul ghoul = new Ghoul();
        ghoul.setNombre("Tokyo");
        ghoul.setSalud(3);
        ghoul.setDependencia(2);

        String data = "-7\n" + "T\n" + "80\n" + "2\n" + "Tokyo\n" + "aa\n" + "-4\n" + "8\n" + "3\n" + "aa\n" + "-4\n" + "200\n" + "2\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.createEsbirro(scanner, p);

        assertEquals(ghoul.getClass(), c.getClass());
        assertEquals(ghoul.getNombre(), ((Ghoul) c).getNombre());
        assertEquals(ghoul.getSalud(), ((Ghoul) c).getSalud());
        assertEquals(ghoul.getDependencia(), ((Ghoul) c).getDependencia());
    }

    @Test
    public void testCreateEsbirro5() {
        Cazador p = new Cazador();
        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("ALTA");

        String data = "3\n" + "Paco\n" + "aa\n" + "-4\n" + "8\n" + "1\n" + "aa\n" + "-4\n" + "200\n" + "ALTA\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.createEsbirro(scanner, p);

        assertEquals(human.getClass(), c.getClass());
        assertEquals(human.getNombre(), ((Humano) c).getNombre());
        assertEquals(human.getSalud(), ((Humano) c).getSalud());
        assertEquals(human.getLealtad(), ((Humano) c).getLealtad());
    }

    @Test
    public void testCreateEsbirro6() {
        Cazador p = new Cazador();
        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("BAJA");

        String data = "3\n" + "Paco\n" + "aa\n" + "-4\n" + "8\n" + "1\n" + "aa\n" + "-4\n" + "200\n" + "BAJA\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.createEsbirro(scanner, p);

        assertEquals(human.getClass(), c.getClass());
        assertEquals(human.getNombre(), ((Humano) c).getNombre());
        assertEquals(human.getSalud(), ((Humano) c).getSalud());
        assertEquals(human.getLealtad(), ((Humano) c).getLealtad());
    }

    @Test
    public void testCreateEsbirro7() {
        Cazador p = new Cazador();
        Humano human = new Humano();
        human.setNombre("Paco");
        human.setSalud(1);
        human.setLealtad("NORMAL");

        String data = "3\n" + "Paco\n" + "aa\n" + "-4\n" + "8\n" + "1\n" + "aa\n" + "-4\n" + "200\n" + "NORMAL\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = p.createEsbirro(scanner, p);

        assertEquals(human.getClass(), c.getClass());
        assertEquals(human.getNombre(), ((Humano) c).getNombre());
        assertEquals(human.getSalud(), ((Humano) c).getSalud());
        assertEquals(human.getLealtad(), ((Humano) c).getLealtad());
    }

    @Test
    public void testCreateOffer() {
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
        //demon.addEsbirro(demon);

        Arma arma = new Arma("Martillo", 3, 1, "l", true);
        Arma arma2 = new Arma("Hoz", 0, 0, "c", false);
        Armadura armadura = new Armadura("Pechera", 0, 2, "r");

        p.addEsbirro(demon);
        p.addEsbirro(ghoul);
        p.addEsbirro(human);

        p.addArma(arma);
        p.addArma(arma2);
        p.addArmadura(armadura);

        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma2);
        offer.addEsbirroCompact(ghoul);
        offer.setPrecio(500);


        String data = "1\n" + "1\n" + "1\n" + "2\n" + "1\n" + "3\n" + "500\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Oferta c = p.createOffer(scanner, user);
        
        assertEquals(offer.getClass(), c.getClass());
        assertEquals(offer.getPrecio(), c.getPrecio());
        assertEquals(offer.getVendedor().getId(), c.getVendedor().getId());
        assertEquals(offer.getEsbirrosCompact().size(), c.getEsbirrosCompact().size());
        assertEquals(offer.getArmasOffer().size(), c.getArmasOffer().size());
        assertEquals(offer.getArmadurasOffer().size(), c.getArmadurasOffer().size());
        assertEquals(offer.getArmasOffer().get(0).getNombre(), c.getArmasOffer().get(0).getNombre());
        assertEquals(offer.getEsbirrosCompact().get(0).getClass(), c.getEsbirrosCompact().get(0).getClass());
    }

    @Test
    public void testCreateOffer2() {
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

        p.addEsbirro(demon);
        p.addEsbirro(ghoul);
        p.addEsbirro(human);

        p.addArma(arma);
        p.addArma(arma2);
        p.addArmadura(armadura);

        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma2);
        offer.addEsbirroCompact(demon);
        offer.setPrecio(500);

        String data = "aaa\n" + "-100\n" + "100\n" + "1\n" + "aaa\n" + "-100\n" + "100\n" + "1\n" + "aaa\n" + "-100\n"
                + "100\n" + "1\n" + "aaa\n" + "-100\n" + "100\n" + "2\n" + "aaa\n" + "-100\n" + "100\n" + "0\n" + "aaa\n"
                + "-100\n" + "100\n" + "3\n" + "aaa\n" + "-100\n" + "500\n" + "aaa\n" + "-100\n" + "100\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Oferta c = p.createOffer(scanner, user);

        assertEquals(offer.getClass(), c.getClass());
        assertEquals(offer.getPrecio(), c.getPrecio());
        assertEquals(offer.getVendedor().getId(), c.getVendedor().getId());
        assertEquals(offer.getEsbirrosCompact().size(), c.getEsbirrosCompact().size());
        assertEquals(offer.getArmasOffer().size(), c.getArmasOffer().size());
        assertEquals(offer.getArmadurasOffer().size(), c.getArmadurasOffer().size());
        assertEquals(offer.getArmasOffer().get(0).getNombre(), c.getArmasOffer().get(0).getNombre());
        assertEquals(offer.getEsbirrosCompact().get(0).getClass(), c.getEsbirrosCompact().get(0).getClass());
    }

    @Test
    public void testCreateOffer3() {
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

        p.addEsbirro(demon);
        p.addEsbirro(ghoul);
        p.addEsbirro(human);

        p.addArma(arma);
        p.addArma(arma2);
        p.addArmadura(armadura);

        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma2);
        offer.addEsbirroCompact(ghoul);
        offer.setPrecio(500);

        String data = "1\n" + "1\n" + "1\n" + "2\n" + "1\n" + "4\n" + "3\n" + "500\n" + "4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Oferta c = p.createOffer(scanner, user);

        assertEquals(offer.getClass(), c.getClass());
        assertEquals(offer.getPrecio(), c.getPrecio());
        assertEquals(offer.getVendedor().getId(), c.getVendedor().getId());
        assertEquals(offer.getEsbirrosCompact().size(), c.getEsbirrosCompact().size());
        assertEquals(offer.getArmasOffer().size(), c.getArmasOffer().size());
        assertEquals(offer.getArmadurasOffer().size(), c.getArmadurasOffer().size());
        assertEquals(offer.getArmasOffer().get(0).getNombre(), c.getArmasOffer().get(0).getNombre());
        assertEquals(offer.getEsbirrosCompact().get(0).getClass(), c.getEsbirrosCompact().get(0).getClass());
    }

    @Test
    public void testBuyOffer1() {
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

        p.addEsbirro(demon);
        p.addEsbirro(ghoul);
        p.addEsbirro(human);

        p.addArma(arma);
        p.addArma(arma2);
        p.addArmadura(armadura);

        p.addOro(800);

        user.setPer(p);

        Oferta offer = new Oferta(user);
        offer.addArmaOffer(arma2);
        offer.addEsbirroCompact(ghoul);
        offer.setPrecio(500);

        boolean c = p.buyOffer(offer);

        assertEquals(true, c);

        c = p.buyOffer(offer);
        assertEquals(false, c);
    }
}

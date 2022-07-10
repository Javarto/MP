
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;
import Users.Admin;
import Users.Usuario;
import Users.UsuarioGeneral;

public class MainTest {
    @Test
    public void testCheckBan1() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Usuario b = new Usuario("id1");
        b.setNick("GC2");
        b.setPassword("12345678");

        main.addNewUser(a);
        main.addNewUser(b);

        boolean c = main.checkBan(b);

        assertEquals(false, c);
    }

    @Test
    public void testCheckBan2() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Usuario b = new Usuario("id1");
        b.setNick("GC2");
        b.setPassword("12345678");

        main.addBanUser(a);
        main.addBanUser(b);

        boolean c = main.checkBan(b);

        assertEquals(true, c);
    }

    @Test
    public void testLogin1() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Usuario b = new Usuario("id1");
        b.setNick("GC2");
        b.setPassword("12345678");

        main.addNewUser(a);
        main.addNewUser(b);

        Object c = main.login("GC2", "12345678");

        assertEquals(b, c);
    }

    @Test
    public void testLogin2() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Admin b = new Admin();
        b.setNick("adm");
        b.setPassword("adm12345");

        main.addNewUser(a);
        main.addNewUser(b);

        Object c = main.login("adm", "adm12345");

        assertEquals(b, c);
    }

    @Test
    public void testLoginFake1() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Usuario b = new Usuario("id1");
        b.setNick("GC2");
        b.setPassword("12345678");

        main.addNewUser(a);
        main.addNewUser(b);

        Object c = main.login("GC22", "12345678");

        assertEquals(null, c);
    }

    @Test
    public void testLoginFake2() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Usuario b = new Usuario("id1");
        b.setNick("GC2");
        b.setPassword("12345678");

        main.addNewUser(a);
        main.addNewUser(b);

        Object c = main.login("GC2", "1234567");

        assertEquals(null, c);
    }

    @Test
    public void testLoginBanned() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Usuario b = new Usuario("id1");
        b.setNick("GC2");
        b.setPassword("12345678");

        main.addNewUser(a);
        main.addNewUser(b);

        main.addBanUser(b);

        Object c = main.login("GC2", "12345678");

        assertEquals(null, c);
    }

    @Test
    public void testRegistrarUser1() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        String data = "n\n" + "Grant\n" + "GC\n" + "12345678\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = main.registrar(scanner);

        assertEquals(a.getClass(), ((UsuarioGeneral) c).getClass());
        assertEquals(a.getPassword(), ((UsuarioGeneral)c).getPassword());
        assertEquals(a.getNick(), ((UsuarioGeneral) c).getNick());
    }

    @Test
    public void testRegistrarUser2() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Usuario b = new Usuario("id1");
        b.setNick("GC2");
        b.setPassword("12345678");

        main.addNewUser(a);

        String data = "n\n" + "Grant\n" + "GC\n" + "GC2\n" + "12345678\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = main.registrar(scanner);

        assertEquals(b.getClass(), ((UsuarioGeneral) c).getClass());
        assertEquals(b.getPassword(), ((UsuarioGeneral) c).getPassword());
        assertEquals(b.getNick(), ((UsuarioGeneral) c).getNick());
    }

    @Test
    public void testRegistrarUser3() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Usuario b = new Usuario("id1");
        b.setNick("GC2");
        b.setPassword("12345678");

        main.addNewUser(a);

        String data = "n\n" + "Grant\n" + "\n" + "GC2\n" + "12345678\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = main.registrar(scanner);

        assertEquals(b.getClass(), ((UsuarioGeneral) c).getClass());
        assertEquals(b.getPassword(), ((UsuarioGeneral) c).getPassword());
        assertEquals(b.getNick(), ((UsuarioGeneral) c).getNick());
    }

    @Test
    public void testRegistrarUser5() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Usuario b = new Usuario("id1");
        b.setNick("GC2");
        b.setPassword("12345678");

        main.addNewUser(a);

        String data = "n\n" + "Grant\n" + "\n" + "GC2\n" + "12345678491135512425534\n"+ "12\n" + "12345678\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = main.registrar(scanner);

        assertEquals(b.getClass(), ((UsuarioGeneral) c).getClass());
        assertEquals(b.getPassword(), ((UsuarioGeneral) c).getPassword());
        assertEquals(b.getNick(), ((UsuarioGeneral) c).getNick());
    }

    @Test
    public void testRegistrarAdmin() {
        Main main = new Main();

        Usuario a = new Usuario("id1");
        a.setNick("GC");
        a.setPassword("12345678");

        Admin b = new Admin();
        b.setNick("adm");
        b.setPassword("adm12345");

        main.addNewUser(a);

        String data = "y\n" + "adm\n" + "\n" + "adm\n" + "adm\n" + "adm12345\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = main.registrar(scanner);

        assertEquals(b.getClass(), ((UsuarioGeneral) c).getClass());
        assertEquals(b.getPassword(), ((UsuarioGeneral) c).getPassword());
        assertEquals(b.getNick(), ((UsuarioGeneral) c).getNick());
    }
    
    @Test
    public void testRegistrarAdmin2() {
        Main main = new Main();

        Admin a = new Admin();
        a.setNick("adm");
        a.setPassword("adm12345");

        Admin b = new Admin();
        b.setNick("adm2");
        b.setPassword("adm12345");

        main.addNewUser(a);

        String data = "y\n" + "adm\n" + "\n" + "adm\n" + "adm2\n" + "adm\n" + "adm12345\n";

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Object c = main.registrar(scanner);

        assertEquals(b.getClass(), ((UsuarioGeneral) c).getClass());
        assertEquals(b.getPassword(), ((UsuarioGeneral) c).getPassword());
        assertEquals(b.getNick(), ((UsuarioGeneral) c).getNick());
    }
}

package Users;
import entidades.personajes.Licantropo;
import entidades.personajes.Vampiro;
import entidades.personajes.Cazador;
import entidades.personajes.Personaje;

public class GetPersonajeFactory {

    public Personaje getPer(String perType) {
        if (perType == null) {
            return null;
        }
        if (perType.equalsIgnoreCase("Vampiro")) {
            return new Vampiro();
        } else if (perType.equalsIgnoreCase("Licantropo")) {
            return new Licantropo();
        } else if (perType.equalsIgnoreCase("Cazador")) {
            return new Cazador();
        }
        return null;
    }
}

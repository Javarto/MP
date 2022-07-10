package entidades.personajes;
import entidades.esbirros.*;

public class GetEsbirroFactory {

    public Esbirro getEsb(String perType) {
        if (perType == null) {
            return null;
        }
        if (perType.equalsIgnoreCase("Ghoul")) {
            return new Ghoul();
        } else if (perType.equalsIgnoreCase("Demonio")) {
            return new Demonio();
        } else if (perType.equalsIgnoreCase("Humano")) {
            return new Humano();
        }
        return null;
    }
}

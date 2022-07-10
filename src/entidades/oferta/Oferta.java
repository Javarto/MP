package entidades.oferta;

import java.util.ArrayList;

import Users.Usuario;
import entidades.equipo.armaduras.Armadura;
import entidades.equipo.armas.Arma;
import entidades.esbirros.Demonio;
import entidades.esbirros.Humano;

public class Oferta implements java.io.Serializable{
    private Usuario vendedor;
    private int precio;
    private ArrayList<Arma> armas = new ArrayList<>();
    private ArrayList<Armadura> armaduras = new ArrayList<>();
    private ArrayList<Object> esbirros = new ArrayList<>();
    private ArrayList<Object> esbirrosCompact = new ArrayList<>();

    public Oferta(Usuario vendedor) {
        this.precio = -1;
        this.vendedor = vendedor;
    }
    
    public ArrayList<String> getStrings() {
        ArrayList<String> results = new ArrayList<>();
        String cat = "";
        int modA = 0;
        int modD = 0;
        results.add(this.getVendedor().getPers().getClass().getSimpleName().toString());

        if (this.getEsbirrosOffer().size() > 0) {
            results.add("0");
        }
        if (this.getArmasOffer().size() > 0) {
            results.add("1");
        }
        if (this.getArmadurasOffer().size() > 0) {
            results.add("2");
        }
        for (int i = 0; i < this.getArmasOffer().size(); i++) {
            cat = this.getArmasOffer().get(i).getCategoria();
            results.add("1" + cat);

            modA = this.getArmasOffer().get(i).getModificadorAtaque();
            results.add("11" + modA);

            modD = this.getArmasOffer().get(i).getModificadorDefensa();
            results.add("12" + modD);
        }
        for (int i = 0; i < this.getArmadurasOffer().size(); i++) {
            cat = this.getArmadurasOffer().get(i).getCategoria();
            results.add("2" + cat);

            modA = this.getArmadurasOffer().get(i).getModificadorAtaque();
            results.add("21" + modA);
            modD = this.getArmadurasOffer().get(i).getModificadorDefensa();
            results.add("22" + modD);
        }   
        for (int i = 0; i < this.getEsbirrosOffer().size(); i++) {
            if (!(results.contains(this.getEsbirrosOffer().get(i).getClass().getSimpleName()))) {
                results.add(this.getEsbirrosOffer().get(i).getClass().getSimpleName());
            }
            if (this.getEsbirrosOffer().get(i) instanceof Humano) {             
                results.add(((Humano)this.getEsbirrosOffer().get(i)).getLealtad());
            }
        }

        return results;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Usuario getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public void showOferta() {
        System.out.println("Oferta actual");
        System.out.println("Personaje: " + this.vendedor.getPers().getClass().getSimpleName());
        System.out.println("Armas:");
        for (int i=0;i < this.armas.size(); i++) {
            this.armas.get(i).printArma();
        }
        System.out.println("Armaduras:");
        for (int i = 0; i < this.armaduras.size(); i++) {
            this.armaduras.get(i).printArmadura();
        }
        System.out.println("Esbirros:");
        for (int i = 0; i < esbirrosCompact.size(); i++) {
            System.out.println(i + " " + esbirrosCompact.get(i).getClass().getSimpleName());
            if (esbirrosCompact.get(i) instanceof Demonio) {
                ArrayList<Object> demonL = this.getEsbirrosSingle(((Demonio) esbirrosCompact.get(i)).getEsbirros());
                for (int ii = 0; ii < demonL.size(); ii++) {
                    System.out.println("\t" + demonL.get(ii).getClass().getSimpleName());
                }
            }
        }


        System.out.println("Precio: " + this.precio + "\n");
    }

    public ArrayList<Object> getEsbirrosSingle(ArrayList<Object> esbs) {
        ArrayList<Object> names = new ArrayList<>();
        for (int i = 0; i < esbs.size(); i++) {
            names.add(esbs.get(i));
            // System.out.println(((Esbirro) esbs.get(i)).getNombre());
            if (esbs.get(i) instanceof Demonio) {
                ArrayList<Object> demonN = getEsbirrosSingle(((Demonio) esbs.get(i)).getEsbirros());
                for (int ii = 0; ii < demonN.size(); ii++) {
                    names.add(demonN.get(ii));
                }
            }
        }
        return names;
    }

    public ArrayList<Arma> getArmasOffer() {
        return this.armas;
    }

    public void addArmaOffer(Arma arma) {
        this.armas.add(arma);
    }
    

    public ArrayList<Armadura> getArmadurasOffer() {
        return this.armaduras;
    }
    public void addArmaduraOffer(Armadura armadura) {
        this.armaduras.add(armadura);
    }

    public ArrayList<Object> getEsbirrosOffer() {
        return this.esbirros;
    }

    public void addEsbirroOffer(Object esbirro) {
        this.esbirros.add(esbirro);
    }

    public ArrayList<Object> getEsbirrosCompact() {
        return esbirrosCompact;
    }

    public void addEsbirroCompact(Object esbirrosCompact) {
        this.esbirrosCompact.add(esbirrosCompact);
    }

}

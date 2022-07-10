package entidades.oferta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import entidades.equipo.armaduras.Armadura;
import entidades.equipo.armas.Arma;
//import entidades.esbirros.Demonio;
//import entidades.esbirros.Esbirro;

public class Venta implements java.io.Serializable {
    private String comprador;
    private String vendedor;
    private int precio;
    private ArrayList<Arma> armas = new ArrayList<>();
    private ArrayList<Armadura> armaduras = new ArrayList<>();
    private ArrayList<Object> esbirros = new ArrayList<>();
    private Date fechacompra;

    public Venta(Oferta offer, String comprador) {
        this.comprador = comprador;
        this.armaduras = offer.getArmadurasOffer();
        this.armas = offer.getArmasOffer();
        this.esbirros = offer.getEsbirrosOffer();
        this.precio = offer.getPrecio();
        this.vendedor = offer.getVendedor().getId();
        this.fechacompra = new Date();
    }

    public void showVenta() {
        System.out.println("ID Comprador: " + this.comprador);
        System.out.println("ID Vendedor: " + this.vendedor);
        System.out.println("Armas:");
        for (int i = 0; i < this.armas.size(); i++) {
            this.armas.get(i).printArma();
        }
        System.out.println("\nArmaduras:");
        for (int i = 0; i < this.armaduras.size(); i++) {
            this.armaduras.get(i).printArmadura();
        }
        System.out.println("Esbirros:");
        System.out.println(Arrays.toString(this.esbirros.toArray()));
        //for (int i = 0; i < esbirros.size(); i++) {
          //  System.out.println(((Esbirro) this.esbirros.get(i)).getNombre());
            //if (esbirros.get(i) instanceof Demonio) {
              //  ((Esbirro) this.esbirros.get(i)).showEsbirros(((Demonio) this.esbirros.get(i)).getEsbirros());
            //}
        //}
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public ArrayList<Arma> getArmas() {
        return armas;
    }

    public void setArmas(ArrayList<Arma> armas) {
        this.armas = armas;
    }

    public ArrayList<Armadura> getArmaduras() {
        return armaduras;
    }

    public void setArmaduras(ArrayList<Armadura> armaduras) {
        this.armaduras = armaduras;
    }

    public ArrayList<Object> getEsbirros() {
        return esbirros;
    }

    public void setEsbirros(ArrayList<Object> esbirros) {
        this.esbirros = esbirros;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }
}

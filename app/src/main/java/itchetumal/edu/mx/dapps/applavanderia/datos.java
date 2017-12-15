package itchetumal.edu.mx.dapps.applavanderia;



public class datos {

    String id;
    String nombrepedido;
    String costo;
    String estado;

    public datos() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrepedido() {
        return nombrepedido;
    }

    public void setNombrepedido(String nombrepedido) {
        this.nombrepedido = nombrepedido;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombrepedido;
    }


    }

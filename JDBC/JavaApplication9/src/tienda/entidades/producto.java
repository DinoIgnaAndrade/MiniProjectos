
package tienda.entidades;

public final class producto extends fabricante {
    
    private double precio;
    private Integer codigoFabricante;
    
    public producto() {
    }

    public producto(double precio, Integer codigoFabricante, Integer codigo, String nombre) {
        super(codigo, nombre);
        this.precio = precio;
        this.codigoFabricante = codigoFabricante;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(int codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }
    
    @Override
    public String toString() {
        return super.toString() + "precio" + precio + "codigo de fabricante" + codigoFabricante; 
    }
    
}

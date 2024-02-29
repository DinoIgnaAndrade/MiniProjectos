
package tienda.entidades;

public class fabricante {
    protected Integer codigo;
    protected String nombre;

    public fabricante() {
    }

    public fabricante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "fabricante{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
}

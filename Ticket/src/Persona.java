/**
 * Inicio clase Persona que tendra los siguientes roles o procesos del programa tales como el nit, nombre, etc.
 * @author Mario Quiñonez 7690-21-2086
 */
public class Persona {
    private String nitUsuario;
    private String nombre;
    private Rol rol;

    public Persona() {
    }

    public Persona(String nitUsuario, String nombre, Rol rol) {
        this.nitUsuario = nitUsuario;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNitUsuario() {
        return nitUsuario;
    }

    public void setNitUsuario(String nitUsuario) {
        this.nitUsuario = nitUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

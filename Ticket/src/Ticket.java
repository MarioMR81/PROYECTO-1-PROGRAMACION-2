import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Iniciamos la Clase Ticket la cual tendra las siguientes propiedades el nit, id, problema, estado y bitacora, que veremos acontinuacion.
 * @author Mario Quiñonez 7690-21-2086
 */
public class Ticket {
    private String nitUsuario;
    private int id;
    private String problema;
    private String estado;
    private List<Bitacora> bitacora;

    public Ticket() {
        bitacora = new ArrayList<>();
    }

    /**
     * Iniciamos los procesos del ticket por lo que el ticket terndra las siguientes funciones del nit, problema y que se debe de ir agregando los eventos a la bitacora
     * @param id
     * @param nitUsuario
     * @param problema
     */
    public Ticket(int id,String nitUsuario, String problema) {
        this.nitUsuario = nitUsuario;
        this.id = id;
        this.problema = problema;
        this.estado = "Creado";
        this.bitacora = new ArrayList<>();
        bitacora.add(new Bitacora(this.nitUsuario,TipoEvento.crearTicket,"Creado",new Date()));
    }

    public String getNitUsuario() {
        return nitUsuario;
    }

    public void setNitUsuario(String nitUsuario) {
        this.nitUsuario = nitUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Bitacora> getBitacora() {
        return bitacora;
    }

    public void setBitacora(List<Bitacora> bitacora) {
        this.bitacora = bitacora;
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(nitUsuario);
        sb.append("\t").append(id);
        sb.append("\t").append(problema);
        sb.append("\t").append(estado).append("\n");
        for (Bitacora bit: bitacora) {
            sb.append(bit).append("\n");;
        }
        sb.append("\n");
        return sb.toString();
    }
}

import java.util.Date;

/**
 * Vamps a iniciar la clase Bitacora la cual nos ayudara a generar eventos que se crearan en el ticket
 * entonces cada evento que se genere en el ticket debe de quedar en la bitacora, al igual se crearan los distintos
 * tipos de eventos del ticket, como crear ticket, mover, solucion etc.
 * @author Mario Quiñonez 7690-21-2086
 */
public class Bitacora {
    private String nitSoporte;
    private Date fechaHora;
    private String mensaje;
    private TipoEvento evento;

    public Bitacora() {
    }

    /**
     * Iniciamos las opcciones de la bitacora
     * @param nitSoporte soporte que se le debe de dar
     * @param evento el evento que se genera en el ticket
     * @param mensaje mensaje del ticket o estado
     * @param fechaHora cada evento de ticket o cuando se solucione debe de mostar fecha y hora
     */
    public Bitacora(String nitSoporte,TipoEvento evento, String mensaje,Date fechaHora) {
        this.nitSoporte = nitSoporte;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
        this.evento = evento;
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Bitacora{");
        sb.append("nitSoporte='").append(nitSoporte).append('\'');
        sb.append(", fechaHora=").append(fechaHora);
        sb.append(", mensaje='").append(mensaje).append('\'');
        sb.append(", evento=").append(evento);
        sb.append('}');
        return sb.toString();
    }
}

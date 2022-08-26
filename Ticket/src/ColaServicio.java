import java.util.ArrayList;
import java.util.List;

/**
 * Iniciamos la cola del servicio de la bitacora en este caso vamos a darle el numero de correlativo al ticket
 * tipo y tambien agregarlo a la cola para que sea solucionado
 * @author Mario Quiñonez 7690-21-2086
 */
public abstract class ColaServicio {
    protected List<Ticket> cola;
    protected TipoCola tipo;
    private int correlativo;
    public ColaServicio(TipoCola tipo) {
        cola = new ArrayList<>();
        this.tipo = tipo;
        correlativo=0;
    }

    /**
     *
     * @param ticket Iniciamos los parametros para agregar el ticket a la cola
     * como tambien las dichas opciones mencionadas del ticket correlativo, agregar a cola.
     *
     */
    public void agregar(Ticket ticket)
    {
        cola.add(ticket);
    }
    abstract public Ticket quitar();

    public int getCorrelativo()
    {
        return correlativo++;
    }

    public List<Ticket> getCola() {
        return cola;
    }

    public void setCola(List<Ticket> cola) {
        this.cola = cola;
    }
}

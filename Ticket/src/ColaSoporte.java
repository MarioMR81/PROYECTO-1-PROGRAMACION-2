public class ColaSoporte extends ColaServicio{
    public ColaSoporte(TipoCola tipo) {
        super(tipo);
    }

    /**
     * Todas las colas parten de la base de la cola servicio ya que tiene un funcionamiento parecido en lo que en este tipó
     * de clases simplemente agregamos un comando para quitar ticket
     * @return
     */
    public Ticket quitar()
    {
        Ticket ticket = cola.get(cola.size()-1);
        cola.remove(ticket);
        return ticket;
    }
}

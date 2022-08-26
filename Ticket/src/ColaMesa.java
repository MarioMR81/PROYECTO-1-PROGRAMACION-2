public class ColaMesa extends ColaServicio{

    /**
     * Iniciamos una de las opciones cuando el ticket sea resuelto de la cola este debera ser removido
     * de dicha cola
     * @author Mario Quiñonez 7690-21-2086
     * @param tipo
     */
    public ColaMesa(TipoCola tipo) {
        super(tipo);
    }

    public Ticket quitar()
    {
        Ticket ticket = cola.get(0);
        cola.remove(ticket);
        return ticket;
    }

}

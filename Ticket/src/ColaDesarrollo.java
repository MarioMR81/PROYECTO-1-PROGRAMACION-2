public class ColaDesarrollo extends ColaServicio{
    public ColaDesarrollo(TipoCola tipo) {
        super(tipo);
    }

    /**
     * Iniciamos una de las opciones cuando el ticket sea resuelto de la cola este debera ser removido
     * de dicha cola desarrollo
     * @author Mario Quiñonez 7690-21-2086
     * @return
     */
    public Ticket quitar()
    {
        int numero = (int)(Math.random()*this.cola.size());
        Ticket ticket = cola.get(numero);
        cola.remove(ticket);
        return ticket;
    }
}

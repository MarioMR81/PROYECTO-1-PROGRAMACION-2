import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Iniciamos la clase principal del programa por lo que haremos el llamado de algunas clases primero como vemos nuestro archivo.Json es muy importante en este caso ya que el el archivo primeramente
 * sera nombrado Personas el cual tendra una influencia importante en el programa
 * @author Mario Quiñonez 7690-21-2086
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();
        ColaServicio colaMesa = new ColaMesa(TipoCola.Mensa);
        ColaServicio colaSoporte= new ColaMesa(TipoCola.Soporte);
        ColaServicio colaDesarrollo = new ColaMesa(TipoCola.Desarrollo);
        ColaServicio colaAtendidos = new ColaMesa(TipoCola.Atendido);
        Ticket ticket=null;
        String nit;
        int opc2=0;
        leerPersonasJSON(personas);
        /**
         * Comenzamos leyendo el archivo y cuando inciemos el programa en la consola nos mostrara los datos necesarios y tambien las opciones que el usuario desee levar a cabo en la ejecucion del programa
         *
         */
        do {
            System.out.println("Ingresa tu nit");
            nit = input.nextLine();
            System.out.println("Ingresa tu rol");
            System.out.println("1. Cliente");
            System.out.println("2. Mesa");
            System.out.println("3. Soporte");
            System.out.println("4. Desarrolador");
            int opc = Integer.parseInt(input.nextLine());
            /**
             * Iniciamos con una de las primeras opcciones dentro del programa la cual seria el cliente el cual tendra una parte fundamental como vemos en el If el debe de ingresar el problema para ser tratado el cial ira ala cola y tambien se le
             * asiganara un numero correlativo
             */

            if (opc == 1) {
                System.out.println("Ingresa el problema");
                String problema = input.nextLine();
                ticket = new Ticket(colaMesa.getCorrelativo(), nit, problema);
                colaMesa.agregar(ticket);
            } else {
                System.out.println("1. Atender tickets");
                System.out.println("2. Listado general de Tickets");
                System.out.println("3. Listado de ticket por cola");
                System.out.println("4. Listado de Tickets por usuario");
                opc2 = Integer.parseInt(input.nextLine());
                /**
                 * Luego de la opccion mencionada tenemos las diversas opciones en los cases como el id ticket, estado y en que cola puede estar el ticket
                 */

                switch (opc2) {
                    case 1:
                        atenderTicket(colaMesa, colaSoporte, colaDesarrollo, colaAtendidos, opc, nit);
                        break;
                    case 2:
                        System.out.println("Tickets de la mesa de ayuda");
                        System.out.println("ID\tEstado");
                        ListadoGeneral(colaMesa);

                        ListadoGeneral(colaSoporte);

                        ListadoGeneral(colaDesarrollo);
                        break;
                    case 3:
                        System.out.println("Tickets de la mesa de ayuda");
                        System.out.println("ID\tEstado");
                        for (Ticket tick : colaMesa.cola) {
                            System.out.println(tick);
                        }
                        System.out.println("Tickets de soporte");
                        System.out.println("ID\tEstado");
                        for (Ticket tick : colaSoporte.cola) {
                            System.out.println(tick);
                        }
                        System.out.println("Tickets de desarrollo");
                        System.out.println("ID\tEstado");
                        for (Ticket tick : colaDesarrollo.cola) {
                            System.out.println(tick);
                        }
                        break;
                    case 4:
                        for (Persona persona : personas) {
                            System.out.println("Tickers de :" + persona.getNitUsuario());
                            for (Ticket tick : colaMesa.cola) {
                                if (persona.getNitUsuario().equalsIgnoreCase(tick.getNitUsuario())) {
                                    System.out.println(tick);
                                }
                            }

                            for (Ticket tick : colaSoporte.cola) {
                                if (persona.getNitUsuario().equalsIgnoreCase(tick.getNitUsuario())) {
                                    System.out.println(tick);
                                }
                            }
                            for (Ticket tick : colaDesarrollo.cola) {
                                if (persona.getNitUsuario().equalsIgnoreCase(tick.getNitUsuario())) {
                                    System.out.println(tick);
                                }
                            }
                        }
                        break;
                    default:
                        System.out.println("Opcion incorrecta, intente de nuevo");
                }
            }

        }while(true);
    }

    /**
     *
     * @param colaMesa Como una de las opciones importantes tenemos el listado general de los tickects el cual nos ayudara a mostrar las siguientes funciones
     */
    private static void ListadoGeneral(ColaServicio colaMesa) {
        for (Ticket tick: colaMesa.cola) {
            System.out.println(tick.getId() +"\t"+ tick.getEstado());
            List<Bitacora> bitacora = tick.getBitacora();
            for (Bitacora bit: bitacora) {
                System.out.println(bit);
            }
        }
    }

    /**
     * Entre otras tenemos el atender un Ticket, el cual tiene una parte muy importante como vemos los siguientes cases primero como sabemos los tickets tiene una cola dependiendo a donde lo mande el usuario
     * al soporte, desarrollo etc
     * @param colaMesa
     * @param colaSoporte
     * @param colaDesarrollo
     * @param colaAtendidos
     * @param opc
     * @param nit
     */
    public static void atenderTicket(ColaServicio colaMesa,ColaServicio colaSoporte, ColaServicio colaDesarrollo,ColaServicio colaAtendidos,int opc,String nit)
    {
        Scanner input = new Scanner(System.in);
        Ticket ticket = null;
        switch (opc)
        {

            /**
             * Primer case ingresar el problema a trabes de la variable problema el cual creara el ticket y sera agregado a cola
             */
            case 1:
            {
                System.out.println("Ingresa el problema");
                String problema = input.nextLine();
                ticket = new Ticket(colaMesa.getCorrelativo(),nit,problema);
                colaMesa.agregar(ticket);
            }
            break;
            /**
             * Mostrar el ticket las opcciones para solcuionar el ticket
             */
            case 2:
                if(!colaMesa.getCola().isEmpty())
                {
                    ticket = colaMesa.quitar();
                    ticket.getBitacora().add(new Bitacora(nit,TipoEvento.Asignar,"Asignado a mesa de ayuda",new Date()));
                    System.out.println("Nit\tID\tProblema\tEstado");
                    System.out.println(ticket);
                    System.out.println("1. Ticket resuelto");
                    System.out.println("2. Ticket no resuelto");
                    opc = Integer.parseInt(input.nextLine());
                    switch (opc)
                    {
                        /**
                         * metodo para saber como se soluciono el ticket y que muestre las opcciones como la solucion, fecha y evento
                         */
                        case 1:
                        {
                            System.out.println("Ingresa la forma en que lo resolvio");
                            String cadena = input.nextLine();
                            ticket.setEstado("Resuelto");
                            ticket.getBitacora().add(new Bitacora(nit,TipoEvento.Solucion,"Resuelto: "+cadena,new Date()));
                            colaAtendidos.agregar(ticket);
                        }
                        break;
                        /**
                         * En dado caso el ticket que se abrio no tenga solucion esclalarlo al siguiente dep de soporte
                         */
                        case 2:
                            System.out.println("El ticket sera escalado a la cola de soporte");
                            ticket.setEstado("Escalado");
                            ticket.getBitacora().add(new Bitacora(nit,TipoEvento.Mover,"Escalado hacia cola de soporte",new Date()));
                            colaSoporte.agregar(ticket);
                            break;
                        default:
                            System.out.println("Opcion incorrecta, intente de nuevo");
                    }
                }else
                {
                    System.out.println("Cola vacia");
                }
                /**
                 *  Proceso similar al case inicial mostrar en donde este asignado, resuelto o no.
                 */
                break;
            case 3:
                if(!colaSoporte.getCola().isEmpty())
                {
                ticket = colaSoporte.quitar();
                ticket.getBitacora().add(new Bitacora(nit,TipoEvento.Asignar,"Asignado a soporte",new Date()));
                System.out.println("Nit\tID\tProblema\tEstado");
                System.out.println(ticket);
                System.out.println("1. Ticket resuelto");
                System.out.println("2. Ticket no resuelto");
                opc = Integer.parseInt(input.nextLine());
                switch (opc)
                {
                    /**
                     * Proceso para saber como se resolvio el ticket y agregarlo a la bitacora como se resolvio y mostrar los datos ya mencionados como tambien escalarlo si el ticket no tiene
                     * solucion
                     */
                    case 1:
                    {
                        System.out.println("Ingresa la forma en que lo resolvio");
                        String cadena = input.nextLine();
                        ticket.setEstado("Resuelto");
                        ticket.getBitacora().add(new Bitacora(nit,TipoEvento.Solucion,"Resuelto: "+cadena,new Date()));
                        colaAtendidos.agregar(ticket);
                    }
                    break;
                    case 2:
                        System.out.println("El ticket sera escalado a la cola de desarrollo");
                        ticket.setEstado("Escalado");
                        ticket.getBitacora().add(new Bitacora(nit,TipoEvento.Mover,"Escalado hacia cola de desarrollo",new Date()));
                        colaSoporte.agregar(ticket);
                        break;
                    default:
                        System.out.println("Opcion incorrecta, intente de nuevo");
                }
                }else
                {
                    System.out.println("Cola vacia");
                }
                break;
            /**
             * Cada uno de los cases tiene una particularidad similar ya que si son solucionados se mostra lo ya mencionado en los otros cases o sino continuar escalando el ticket dentro de la
             * bitacora del programa por lo que el case 4 tiene una funcionalidad similar a los anteriores
             */
            case 4:
                if(!colaDesarrollo.getCola().isEmpty())
                {
                ticket = colaDesarrollo.quitar();
                ticket.getBitacora().add(new Bitacora(nit,TipoEvento.Asignar,"Asignado a soporte",new Date()));
                System.out.println("Nit\tID\tProblema\tEstado");
                System.out.println(ticket);
                System.out.println("1. Ticket resuelto");
                System.out.println("2. Ticket no resuelto");
                opc = Integer.parseInt(input.nextLine());
                switch (opc)
                {
                    case 1:
                    {
                        System.out.println("Ingresa la forma en que lo resolvio");
                        String cadena = input.nextLine();
                        ticket.setEstado("Resuelto");
                        ticket.getBitacora().add(new Bitacora(nit,TipoEvento.Solucion,"Resuelto: "+cadena,new Date()));
                        colaAtendidos.agregar(ticket);
                    }
                    break;
                    /**
                     * Una de las partes distintas en este metodo es que cuando el ticket ya ha sido escalado pero sin solucion quedara asi en la bitacora como cerrado sin resolver
                     */
                    case 2:
                        System.out.println("El ticket se marcara como atentido pero sin resolver");
                        ticket.setEstado("Cerrado pero sin resolver");
                        ticket.getBitacora().add(new Bitacora(nit,TipoEvento.Solucion,"El ticket no se ha podido resolver. Ticket cerrado",new Date()));
                        colaAtendidos.agregar(ticket);
                        break;
                    default:
                        System.out.println("Opcion incorrecta, intente de nuevo");
                }
                }else
                {
                    System.out.println("Cola vacia");
                }
                break;
            default:
                System.out.println("Opcion incorrecta, intente de nuevo");
        }
    }

    /**
     * Nuesto archivo Json personas nombrado tiene la funcionalidad siguiente en este caso cuando se haga el llamado al archivo JSON dentro del programa se muestra en el codigo la lectura del archivo como
     * tambien los datos que ya hemos mencionado al inicio del programa como los roles
     * @param Personas
     */
    private static void leerPersonasJSON(List Personas) {
        JSONParser parser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("personas.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Object obj = null;
        try {
            obj = parser.parse(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject pJsonObj = (JSONObject) obj;
        JSONArray array = (JSONArray) pJsonObj.get("Personas");

        for (int i = 0; i < array.size(); i++) {
            JSONObject Persona = (JSONObject) array.get(i);

            String nombre = (String) Persona.get("nombre");
            String nitUsuario = (String) Persona.get("nit");
            String rol = (String) Persona.get("rol");
            Rol newRol=null;
            switch (Integer.parseInt(rol))
            {
                case 1: newRol = Rol.Cliente;
                    break;
                case 2: newRol = Rol.Mesa;
                    break;
                case 3: newRol = Rol.Soporte;
                    break;
                case 4: newRol = Rol.Desarrollo;
                    break;
            }
            Persona persona = new Persona(nitUsuario,nombre,newRol);
        }
    }
}



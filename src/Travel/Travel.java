
package Travel;

import FolderPlane.Plane;
import Passenger.Passenger;
import Ticket.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Travel {                       //Resultado final de un viaje- esto es un objeto. Viaje.
    private LocalDate fecha;
    private Integer earnings;             ////Ganancias - total de dinero ingresado en el viaje
    private Distances distance;
    private Ticket ticket;
    private Plane plane;
    private List<Passenger> pax;            ////arreglo de pasajeros nombre y apellido  del viaje
    private List<Ticket> tickets;         // arreglos de todos los tickets del viaje


    // TODO: Ver como crear un metodo que instancie un objeto Travel
    public Travel( Integer earnings, Distances distances, Plane plane, List<Passenger> passenger, List<Ticket> tickets) {
        this.fecha = LocalDate.now();
        this.earnings = earnings;
        this.distance = distances;
        this.plane = plane;
        this.pax = pax;
        this.tickets = tickets;
    }

    public Travel (Ticket ticket, Plane plane){

        this.fecha = LocalDate.now();        // TODO: 05/06/2022 --Es necesario un get de la fecha del  ticket--
        this.earnings = ticket.getPrice() * ticket.getTotal_passengers();
        this.plane = plane;
        //this.distance = ticket.getDestination();
    }

    @Override
    public String toString() {
        return "Travel{" + '\'' +
                "fecha=" + fecha + '\'' +
                ", earnings=" + earnings + '\'' +
                ", destino =" + distance.name() + '\'' +
                ", plane=" + plane.getNombre() + '\'' +
                ", pax=" + pax + '\'' +               // TODO: 08/06/2022 cuando traiga arreglo de person completar
                '}';
}
}
// TODO: 05/06/2022  ----Vamos a intanciar un tavel en el main*----



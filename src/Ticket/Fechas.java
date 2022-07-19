package Ticket;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Fechas {
    /*List<Calendar> listCalendario = new ArrayList<Calendar>();*/


    public Fechas(){
    }

    public  Calendar elegir(){
        Scanner scan = new Scanner(System.in);
        Calendar day = Calendar.getInstance();
        Calendar dayToday = Calendar.getInstance();


        int dia = day.get(Calendar.DATE);
        int mes;
        int diaHoy = dayToday.get(Calendar.DATE);// Cuando instaciamos una fecha en Calendar los meses se valoran de 0 a 11
        int mesHoy = dayToday.get(Calendar.MONTH)+1;// entonces de este modo modificamos su valor para q sea igual al calendario de 1 a 12

        int respuesta;

        do {
            dateMonths();
            respuesta = scan.nextInt();
        } while (respuesta<mesHoy || respuesta > 12);
        mes=respuesta;

        if (mes==mesHoy) {
            do {
                showdates();
                respuesta = scan.nextInt();
            } while (respuesta < diaHoy || respuesta > 31);
        }else {
            do {
                showdates();
                respuesta = scan.nextInt();
            }while (respuesta < 1 || respuesta > 31);
        }
        dia=respuesta;

        day.set(Calendar.MONTH, mes);
        day.set(Calendar.DATE, dia);
        return day;
    }

    private static void showdates(){
        System.out.println("Marque el dia para viajar");
    }
    private static void dateMonths(){
        System.out.println("1-Enero  2-Febrero  3-Marzo  4-Abril" );
        System.out.println("5-Mayo  6-Junio  7-Julio  8-Agosto" );
        System.out.println("9-Septiembre  10-Octubre  11-Noviembre  12- Diciembre");
    }

}
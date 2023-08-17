/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Sebas
 */
public class FuncionesSolicitudes {

    public static String obtenerMes(Date fecha) {
        // Especifica el formato para obtener el número del mes
        SimpleDateFormat formatoNumeroMes = new SimpleDateFormat("MM");
        int numeroMes = Integer.parseInt(formatoNumeroMes.format(fecha));

        // Convierte el número del mes a su nombre
        String[] nombresMeses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        String nombreMes = nombresMeses[numeroMes - 1];
        return nombreMes;
    }

    public static String obtenerDia(Date fecha) {
        // Especifica el formato en el que quieres obtener la fecha separada
        SimpleDateFormat formato = new SimpleDateFormat("dd");

        // Usa el formato para formatear la fecha y obtener una cadena
        String fechaFormateada = formato.format(fecha);

        return fechaFormateada;
    }

    public static String obtenerAnio(Date fecha) {
        // Especifica el formato en el que quieres obtener la fecha separada
        SimpleDateFormat formato = new SimpleDateFormat("yyyy");

        // Usa el formato para formatear la fecha y obtener una cadena
        String fechaFormateada = formato.format(fecha);

        return fechaFormateada;
    }

    public static String calcularAniosMeses(Date fecha) {
    // Convertir java.sql.Date a java.util.Date
    java.util.Date utilDate = new java.util.Date(fecha.getTime());

    // Convertir java.util.Date a LocalDate
    LocalDate fechaDada = utilDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

    // Obtener la fecha actual
    LocalDate fechaActual = LocalDate.now();

    // Calcular la diferencia entre las fechas
    Period diferencia = Period.between(fechaDada, fechaActual);

    int anios = diferencia.getYears();
    int meses = diferencia.getMonths();

    return anios + " Años, " + meses + " Meses";
}
     public static String aumentarUnDia(Date fecha) {
        // Crea un objeto Calendar y configúralo con la fecha dada
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);

        // Aumenta la fecha en 1 día
        calendario.add(Calendar.DAY_OF_YEAR, 1);

        // Obtén la nueva fecha
        Date nuevaFecha = calendario.getTime();
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String nuevaFechaFormateada = formatoFecha.format(nuevaFecha);
        return nuevaFechaFormateada;
    }
     
      public  Date RestarAnios(Date fecha, int years) {
    // Crea un objeto Calendar y configúralo con la fecha dada
    Calendar calendario = Calendar.getInstance();
    calendario.setTime(fecha);

    // Resta los años especificados
    calendario.add(Calendar.YEAR, -years);

    // Obtén la nueva fecha
    Date nuevaFecha = calendario.getTime();
    return nuevaFecha;
}

}

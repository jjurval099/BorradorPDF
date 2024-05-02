package ies.jandula.method;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Method
{

    public void borrarPDF(String carpeta, String texto) 
    {
        File directorio = new File(carpeta);

        // Verificacion si la carpeta existe y es un directorio
        if (!directorio.exists() ) 
        {
            System.out.println("La carpeta especificada no existe");
        }
        
        if(!directorio.isDirectory())
        {
        	System.out.println("La carpeta especificada es un directorio");
        }

        // Calcular la fecha límite para borrar archivos
        LocalDateTime fechaLimite = null;
        LocalDateTime ahora = LocalDateTime.now();

        switch (texto) 
        {
            case "desde ayer":
                fechaLimite = ahora.minusDays(1);
                break;
            case "desde 3 días atrás":
                fechaLimite = ahora.minusDays(3);
                break;
            case "desde la semana pasada":
                fechaLimite = ahora.minusWeeks(1);
                break;
            case "desde el mes pasado":
                fechaLimite = ahora.minusMonths(1);
                break;
            case "todos":
                // No establecer límite, eliminar todos los archivos
                break;
            default:
                System.out.println("Texto no es el indicado.");
                return;
        }

        // Obtener la lista de archivos en la carpeta y borrar los archivos que cumplan con los criterios
        for (File archivo : directorio.listFiles()) 
        {
            if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".pdf")) 
            {
                LocalDateTime fechaModificacion = LocalDateTime.ofInstant(Instant.ofEpochMilli(archivo.lastModified()), ZoneId.systemDefault());

                if (texto.equals("todos") || fechaModificacion.isBefore(fechaLimite)) 
                {
                    if (archivo.delete()) 
                    {
                        System.out.println("Archivo borrado: " + archivo.getName());
                    } 
                    else 
                    {
                        System.out.println("No se pudo borrar el archivo: " + archivo.getName());
                    }
                }
            }
        }
    }

}


package jamps.cu;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by juanp on 29/09/2015.
 */
public class Comentarios
{
    String com;
    int likes;
    Date fecha;
    Comentarios (String comentario)
    {
        com = comentario;
        fecha = new Date();
        likes = 0;
    }
}

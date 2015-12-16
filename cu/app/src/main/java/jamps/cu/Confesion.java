package jamps.cu;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by juanp on 29/09/2015.
 */
public class Confesion {
    String conf;
    Date fecha;
    int likes;
    ArrayList<Comentarios> comments;
    Confesion (String confesion)
    {
        conf = confesion;
        fecha = new Date();
        likes = 0;
        comments = new ArrayList();
    }
    public String darConfesion()
    {
        return conf;
    }
    public void clklike()
    {
        likes++;
    }
    public int darLikes()
    {
        return likes;
    }
    public Date darFecha()
    {
        return fecha;
    }
}

package OpenHelper;

public class datos {

    private int idusuario;
    private String nombre, apellido, fecha, hora, telefono;


    public datos (int idusuario,  String nombre,  String apellido, String telefono, String fecha, String hora) {

        setIdusuario(idusuario);
        setNombre(nombre);
        setApellido(apellido);
        setFecha(fecha);
        setHora(hora);
        setTelefono(telefono);


    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

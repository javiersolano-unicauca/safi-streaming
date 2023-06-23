package modelo;

/**
 * @author javiersolanop
 */
public abstract class PersonaModel extends Modelo {
    
    // Properties:
    protected Integer atrId;
    protected String atrNombre;
    protected Long atrNIdentificacion;
    protected String atrCorreo;
    protected Long atrTelefono;
    protected Long atrNCuentaBan;
    protected String atrBanco;
    
    // Constructors:
    protected PersonaModel()
    {
        atrNombre = "No tiene";
        atrNIdentificacion = (long)0;
        atrCorreo = "No tiene";
        atrTelefono = (long)0;
        atrNCuentaBan = (long)0;
        atrBanco = "No tiene";
    }
    
    protected PersonaModel(String prmNombre, Long prmNIdentificacion, String prmCorreo, 
                           long prmTelefono, Long prmNCuentaBan, String prmBanco)
    {
        atrNombre = prmNombre;
        atrNIdentificacion = prmNIdentificacion;
        atrCorreo = prmCorreo;
        atrTelefono = prmTelefono;
        atrNCuentaBan = prmNCuentaBan;
        atrBanco = prmBanco;
    } 
    
    protected PersonaModel(Integer prmId, String prmNombre, Long prmNIdentificacion, String prmCorreo, 
                           Long prmTelefono, Long prmNCuentaBan, String prmBanco)
    {
        atrId = prmId;
        atrNombre = prmNombre;
        atrNIdentificacion = prmNIdentificacion;
        atrCorreo = prmCorreo;
        atrTelefono = prmTelefono;
        atrNCuentaBan = prmNCuentaBan;
        atrBanco = prmBanco;
    }
    
    // Methods 'setter' and 'getter':
    public int getId() {
        return atrId;
    }

    public String getNombre() {
        return atrNombre;
    }

    public void setNombre(String prmNombre) {
        atrNombre = prmNombre;
    }

    public Long getNIdentificacion() {
        return atrNIdentificacion;
    }

    public void setNIdentificacion(Long prmNIdentificacion) {
        atrNIdentificacion = prmNIdentificacion;
    }

    public String getCorreo() {
        return atrCorreo;
    }

    public void setCorreo(String prmCorreo) {
        atrCorreo = prmCorreo;
    }

    public Long getTelefono() {
        return atrTelefono;
    }

    public void setTelefono(Long prmTelefono) {
        atrTelefono = prmTelefono;
    }

    public Long getNCuentaBan() {
        return atrNCuentaBan;
    }

    public void setNCuentaBan(Long prmNCuentaBan) {
        this.atrNCuentaBan = prmNCuentaBan;
    }

    public String getBanco() {
        return atrBanco;
    }

    public void setBanco(String prmBanco) {
        atrBanco = prmBanco;
    }
}

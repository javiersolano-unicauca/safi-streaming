 package Libraries.Arrays.JSON;

/**
*  @author javiersolanop
*/
public enum EnumJSON {
    
    STRING("String"),
    INTEGER("Integer"),
    LONG("Long"),
    FLOAT("Float"),
    DOUBLE("Double"),
    BOOLEAN("Boolean");

    // Properties:
    private String atrTypeData;

    // Constructors:
    private EnumJSON(String prmTypeData)
    {
        atrTypeData = prmTypeData;
    }

    // Methods:
    public String getTypeData(){ return atrTypeData; }
}

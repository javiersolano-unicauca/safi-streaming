package Libraries.Arrays.ChainOfCharacter;

/**
 * @author javiersolanop
 */
public class ChainOfCharacter {
    
    /**
     *  Metodo para validar si la cadena es un numero real
     * 
     *  @param prmChain Recibe la cadena
     *  @return 'true' si es un numero real. 'false' si no.
     */
    public static boolean isNumberReal(String prmChain)
    {
        if((prmChain.charAt(0) == '-') || ((prmChain.charAt(0) >= '0') && (prmChain.charAt(0) <= '9'))){

            for(int i = 1; i < prmChain.length(); i++){

                if(((prmChain.charAt(i) < '0') || (prmChain.charAt(i) > '9')) && (prmChain.charAt(i) != '.'))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     *  Metodo para validar si la cadena es un numero entero
     * 
     *  @param prmChain Recibe la cadena
     *  @return 'true' si es un numero entero. 'false' si no. 
     */
    public static boolean isNumberInt(String prmChain)
    {
        if((prmChain.charAt(0) == '-') || ((prmChain.charAt(0) >= '0') && (prmChain.charAt(0) <= '9'))){

            for(int i = 1; i < prmChain.length(); i++){

                if((prmChain.charAt(i) < '0') || (prmChain.charAt(i) > '9'))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     *  Metodo para validar si la cadena es un booleano
     * 
     *  @param prmChain Recibe la cadena
     *  @return 'true' si es un booleano. 'false' si no. 
     */
    public static boolean isBoolean(String prmChain)
    {
        return ((prmChain.equals("true")) || (prmChain.equals("false")));
    }

    /**
     *  Metodo para parsear una cadena a numero entero
     * 
     *  @param prmChain Recibe la cadena
     *  @return El numero parseado o null si la cadena no es un numero entero.
     */
    public static Object parseNumberInt(String prmChain)
    {
        if(isNumberInt(prmChain)){

            try{
                return Integer.parseInt(prmChain);
            }catch(NumberFormatException e){
                return Long.parseLong(prmChain);
            }
        }
        return null;
    }
    
    /**
     *  Metodo para parsear una cadena a numero real
     * 
     *  @param prmChain Recibe la cadena
     *  @return El numero parseado o null si la cadena no es un numero real.
     */
    public static Object parseNumberReal(String prmChain)
    {
        if(isNumberReal(prmChain)){

            try{
                return Float.parseFloat(prmChain);
            }catch(NumberFormatException e){
                return Double.parseDouble(prmChain);
            }
        }
        return null;
    }
}

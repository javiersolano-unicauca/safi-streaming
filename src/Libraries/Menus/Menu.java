package Libraries.Menus;

import Libraries.Consoles.Console;
import Libraries.Exceptions.MathException;
import Libraries.Exceptions.MenuException;
import java.io.IOException;
import java.util.ArrayList;

/**
*  @author javiersolanop
*/ 

public class Menu {
    
    // Properties of object:
    private String atrTitulo;
    private int atrOutputOption;
    private ArrayList<String> atrItems;

    // Constructors:
    public Menu(){ atrItems = new ArrayList<>(); }

    public Menu(String prmTitulo)
    {
        atrTitulo = prmTitulo;
        atrItems = new ArrayList<>();
    }

    // Methods 'setter' and 'getter':
    public void setTitulo(String prmTitulo)
    {
        atrTitulo = prmTitulo;
    }

    public String getTitulo(){ return atrTitulo; }

    public int getOutputOption(){ return atrOutputOption; }

    // Methods:
    public void addItem(String prmItem){ 
        atrItems.add(prmItem); 
        atrOutputOption = atrItems.size() + 1;
    }

    /**
     *  Metodo para imprimir todo el menu de items y la opcion de salida.
     */
    public void menuPrint()
    {
        Console.titlePrint(atrTitulo);
        int i = 1;

        for(String item: atrItems){
            Console.println(i+". "+item);
            i++;
        }
        Console.println(i+". Salir del menu.");
    }

    /**
     *  Metodo para validar la opcion recibida por el usuario.
     * 
     * @param prmOption Recibe la opcion
     * @throws MenuException En el caso de que la opcion no sea valida.
     */
    private void validateOption(int prmOption) throws MenuException
    {
        if((prmOption < 1) || (prmOption > atrOutputOption))
            throw new MenuException(MenuException.INVALID_OPTION);
    }

    /**
     *  Metodo para salir del menu.
     *  Si la opcion recibida por el usuario corresponde a la de salida.
     *  Entonces se limpia la consola.
     * 
     *  @param prmOption Recibe la opcion
     *  @return 'true' si la opcion corresponde a la de salida. 'false' si no.
     *  @throws IOException En el caso de que surga al tratar de limpiar la consola.
     *  @throws InterruptedException En el caso de que surga al tratar de ejecutar el comando de limpieza.
     */
    public boolean menuExit(int prmOption) throws IOException, InterruptedException
    {
        if(prmOption == atrOutputOption){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return true;
        }
        return false;
    }

    /**
     *  Metodo para recibir una opcion del menu.
     * 
     *  @return La opcion recibida.
     */
    public int receiveOption()
    {
        int varOpcion = 0;

        do{
            try{
                varOpcion = Console.integerInput("Ingrese una opcion");
                validateOption(varOpcion);
                break;
            
            }catch(MathException | MenuException e){
                Console.printMessage("Error: "+e.getMessage());
            }
        }while(true);

        return varOpcion;
    }
}

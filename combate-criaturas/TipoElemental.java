import java.util.Arrays;

public abstract class TipoElemental  
{
    public static final String AGUA = "Agua";
    public static final String FUEGO = "Fuego";
    public static final String PLANTA = "Planta";
    public static final String ELECTRICO = "Electrico";
    public static final String NORMAL = "Normal";
    
    /*
     * retorno:
     *      1, si tipo1 es efectivo contra tipo2
     *     -1, si tipo1 es poco efectivo contra tipo2
     *      0, si tipo1 tiene efectividad normal contra tipo2
       */
    public static int efectividadDeTipoContra(String tipo1, String tipo2)
    {
        switch (tipo1) {
            case AGUA:
                if (tipo2 == PLANTA) {
                    return -1;
                }
                
                if (tipo2 == FUEGO) {
                    return 1;
                }
                
                return 0;
            
            case FUEGO:
                if (tipo2 == AGUA) {
                    return -1;
                }
                
                if (tipo2 == PLANTA) {
                    return 1;
                }
                
                return 0;
            
            case PLANTA:
                if (tipo2 == FUEGO) {
                    return -1;
                }
                
                if (tipo2 == AGUA) {
                    return 1;
                }
                
                return 0;
            
            case ELECTRICO:
                if (tipo2 == PLANTA) {
                    return -1;
                }
                
                if (tipo2 == AGUA) {
                    return 1;
                }
                
                return 0;
            
            case NORMAL:
                return 0;
        }
        
        return 0;
    }
}

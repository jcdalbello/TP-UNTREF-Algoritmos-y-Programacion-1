public class LoggerExtraFunctions extends Logger 
{
    public LoggerExtraFunctions() { }

    public static void calcularDañoCon(int ataque) {
        System.out.println("El daño se calcula con " + ataque);
    }
    
    public static void dañoRecibido(Criatura criaturaAtacada, int dañoRecibido) {
        System.out.println("La criatura " + criaturaAtacada.toString() + " recibe "
            + dañoRecibido + " de daño (defensa: " + criaturaAtacada.getDefensa() + ")");
    }
    
    public static void asistir(Criatura criaturaActual, Criatura criaturaObjetivo, String ataque) {
        System.out.println("La criatura " + criaturaActual.toString()
            + " asiste con el ataque " + ataque + " a la criatura " + criaturaObjetivo.toString());
    }
    
    public static void afectarCaracteristica(Criatura actual, String caracteristica, int valorAnterior, int valorActual, boolean incremento) {
        int diferencia = 0;
        String tipoDeCambio = incremento ? "incrementada" : "reducida";
        
        if (tipoDeCambio == "incrementada") {
            diferencia = valorActual - valorAnterior;
        }
        else {
            diferencia = valorAnterior - valorActual;
        }
        
        System.out.println("La característica '" + caracteristica + "' de la criatura " + actual.toString()
            + " ha sido " + tipoDeCambio + " en " + diferencia + ". Valor actual: " + valorActual);

    }
    
    public static void curacionDeCambioDeEstado(Criatura criatura) {
        // Falta agregar los estados, como envenenado, paralizado, quemado, etc...
        System.out.println("La criatura " + criatura.toString() + " se curó de su condicion de estado");
    }
    
    public static void ataqueASiMismo(Criatura criatura, String ataque) {
        System.out.println("La criatura " + criatura.toString() + " uso el ataque " + ataque
            + " en si mismo");
    }
    
    public static void resetearCaracteristica(Criatura criatura, String nombreCaracteristica, int valorCaracteristica) {
        System.out.println("La caracteristica de " + nombreCaracteristica + " de la criatura " + criatura.toString() + " ha vuelto a la normalidad. Valor actual: " + valorCaracteristica);
    }
    
    public static void sacrificio(Criatura criatura) {
        System.out.println("La criatura " + criatura.toString() + " se ha sacrificado por el bien del equipo");
    }
}

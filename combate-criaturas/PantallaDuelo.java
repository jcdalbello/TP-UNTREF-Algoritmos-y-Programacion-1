import greenfoot.*;

public class PantallaDuelo extends World {
    private Texto turnoTexto;
    private UIAtaques uiAtaques;
    private Criatura[] criaturas = new Criatura[4];
    private int indiceCriaturaActiva = 0;

    private int ronda = 0;
    private int turno = 0;

    public PantallaDuelo() {
        super(700, 400, 1);

        agregarCriaturas();

        turnoTexto = new Texto("Ronda 1 | Turno 1", 20, Color.BLACK, Color.WHITE);
        addObject(turnoTexto, turnoTexto.getImage().getWidth() / 2, turnoTexto.getImage().getHeight() / 2);

        uiAtaques = new UIAtaques(criaturas);
        addObject(uiAtaques, 350, 300);

        GreenfootImage imagenFondo = new GreenfootImage("fondo.png");
        getBackground().drawImage(imagenFondo, 0, 0);

        ronda();
    }

    private void agregarCriaturas() {
        // Jugador
        criaturas[0] = new Pikachu("PikaPika");
        // criaturas[1] = new Squirtle("Tortuguita");
        criaturas[1] = new Bulbasaur("Saur");

        // Rival
        // criaturas[2] = new Bulbasaur("Saur", true);
        criaturas[2] = new Squirtle("Tortuguita", true);
        criaturas[3] = new Eevee("Eevee", true);
        //criaturas[3] = new Chikorita("Chiko", true);

        addObject(criaturas[0], 100, 80);
        addObject(criaturas[1], 240, 80);
        addObject(criaturas[2], 460, 80);
        addObject(criaturas[3], 600, 80);
    }

    private void ronda() {
        ronda++;
        turno();
    }
    
    public void turno() {
        int cantidadDeCriaturasVivas;
        
        // Daño del veneno
        for (int i = 0; i < criaturas.length; i++) {
            if (criaturas[i].estado == Estado.ENVENENADO) {
                criaturas[i].logger.efectoDeVeneno(criaturas[i]);
                criaturas[i].recibirDañoFijo((int)(criaturas[i].getVida()*(1/8)));
            }
        }
        
        // Detectar si ambos Pokemon de algun jugador estan debilitados, y si es asi, terminar el turno automaticamente retornando
        if (criaturas[0].getVida() == 0 && criaturas[1].getVida() == 0) {
            System.out.println("Jugador 2 gana!");
            return;
        }
        if (criaturas[2].getVida() == 0 && criaturas[3].getVida() == 0) {
            System.out.println("Jugador 1 gana!");
            return;
        }
        
        turno++;
        
        indiceCriaturaActiva = indiceCriaturaActiva();
        cantidadDeCriaturasVivas = cantidadDeCriaturasVivas();
        
        // Registrar las rondas y los espacios en blanco entre turnos
        criaturas[0].logger.cambiarTurno();
        if (turno == 1) {
            criaturas[0].logger.cambiarRonda(ronda);
            criaturas[0].logger.cambiarTurno();
        }
        
        
        for (int i = 0; i < criaturas.length; i++) {
            criaturas[i].setVisualSeleccionado(false);
        }
        
        turnoTexto.actualizarTexto("Ronda " + ronda + " | Turno " + (((turno - 1) % cantidadDeCriaturasVivas)+1) );
        
        if (criaturas[indiceCriaturaActiva].getVida() > 0) {
            uiAtaques.asignarCriaturaActual(criaturas[indiceCriaturaActiva]);
        }
        
        if (turno % cantidadDeCriaturasVivas == 0) {
            ronda++;
            turno = 0;
        }
    }
    
    private int cantidadDeCriaturasVivas() {
        int cantidadDeCriaturasVivas = 0;
        
        for (int i = 0; i < criaturas.length; i++) {
            if (criaturas[i].getVida() > 0) {
                cantidadDeCriaturasVivas++;
            }
        }
        
        return cantidadDeCriaturasVivas;
    }

    private int indiceCriaturaActiva() {
        if (cantidadDeCriaturasVivas() == 4) {
            return ((turno - 1) % 4);
        }
        
        for (int i = indiceCriaturaActiva+1; i < criaturas.length; i++) {
            if (criaturas[i].getVida() > 0) {
                return i;
            }
        }
        
        for (int i = 0; i < criaturas.length; i++) {
            if (criaturas[i].getVida() > 0) {
                return i;
            }
        }
        
        return 0;
    }
    
    public void click(Criatura c) {
            uiAtaques.click(c);
    }

    public void hover(Criatura c) {
        uiAtaques.hover(c);
    }
    
    public int getTurno() {
        return this.turno;
    }
}

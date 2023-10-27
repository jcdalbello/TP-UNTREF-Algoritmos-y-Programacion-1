import java.util.List;
import java.lang.Math;

import greenfoot.*;

// ESTA ES UNA COPIA DEL PROYECTO, NO SE ESTA MODIFICANDO
// EL QUE ESTA SUBIDO A GITHUB

public abstract class Criatura extends Actor {
    protected final String nombre;
    protected final int vidaMaxima;
    protected final int ataqueOriginal;
    protected final int defensaOriginal;

    protected final String[] nombresAtaque;
    protected final String[] detallesAtaque;

    protected final boolean equipo1;
    
    protected LoggerExtraFunctions logger;
    
    protected int vida;
    protected int ataque;
    protected int defensa;
    protected Tipo tipo;

    private UIInfoCriatura uiInfoCriatura;

    private boolean visualHover;
    private boolean visualSeleccionado;

    private final MyGreenfootImage imagenOriginal;

    public Criatura(String nombre, int vida, int ataque, int defensa, String[] nombresAtaque, boolean equipo1, String[] detallesAtaque) {
        this.nombre = nombre;

        this.vidaMaxima = vida;
        this.ataqueOriginal = ataque;
        this.defensaOriginal = defensa;

        this.nombresAtaque = nombresAtaque;
        this.detallesAtaque = detallesAtaque;

        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        
        this.equipo1 = equipo1;
        
        // TODO: Hay que limpiar la consola para que no muestre los logs pasados;

        this.imagenOriginal = new MyGreenfootImage(getImage());
        this.imagenOriginal.scale(130, 130);

        this.uiInfoCriatura = new UIInfoCriatura(this);
    }

    @Override
    protected void addedToWorld(World world) {
        render();

        getWorld().addObject(uiInfoCriatura, getX(), getY());
        // Una vez en el mundo, actualizo segun su tamaño
        uiInfoCriatura.setLocation(getX(), getY() + getImage().getHeight() / 2 - /*Sombra*/ 10 + uiInfoCriatura.getImage().getHeight() / 2);
    }

    public void act() {
        boolean _visualHover = visualHover;
        boolean _visualSeleccionado = visualSeleccionado;

        MouseInfo m = Greenfoot.getMouseInfo();

        // TODO: detecta el mouse-over, no tocar
        if (m != null) {
            List<Actor> actor = getWorld().getObjectsAt(m.getX(), m.getY(), Actor.class);

            if (actor.size() > 0 && actor.get(0) == this) {
                visualHover = true;
                ((PantallaDuelo)getWorld()).hover(this);
            } else {
                visualHover = false;
            }
        }

        if (Greenfoot.mouseClicked(this)) {
            ((PantallaDuelo)getWorld()).click(this);
        }

        render();
    }

    public void render() {
        MyGreenfootImage nuevaImagen = new MyGreenfootImage(imagenOriginal) {
                public void configurar() {
                    if (vida == 0) {
                        grayscale();
                    }

                    if (!equipo1) {
                        flipHorizontally();
                    }
                    
                    if (visualHover && vida > 0) {
                        scaleToRatio(1.15);
                    }
                    
                    if (visualSeleccionado) {
                        highlight();
                    }
                    
                    shadow();
                }
            };

        setImage(nuevaImagen);
    }

    public void atacar1(Criatura otro) {
        int dañoRecibido = otro.recibirDaño(this);
        String nombreDelAtaque = this.getNombresAtaque()[0];
        
        logger.ataque(this, otro, nombreDelAtaque);
        logger.calcularDañoCon(this.getAtaque());
        logger.dañoRecibido(otro, dañoRecibido);
    }

    public abstract void atacar2(Criatura otro);

    public abstract void atacar3(Criatura otro);

    public abstract void atacar4(Criatura otro);

    protected boolean esDelMismoEquipoQue(Criatura otro) {
        return this.equipo1 == otro.equipo1;
    }

    public boolean puedeRealizarAtaque1En(Criatura otro) {
        return !esDelMismoEquipoQue(otro);
    }

    public abstract boolean puedeRealizarAtaque2En(Criatura otro);

    public abstract boolean puedeRealizarAtaque3En(Criatura otro);

    public abstract boolean puedeRealizarAtaque4En(Criatura otro);
    
    public double factorTipo(Tipo tipoOponente) {
        switch (tipo) {
        case FUEGO:
            return (tipoOponente == Tipo.PLANTA) ? 2.0 : (tipoOponente == Tipo.AGUA) ? 0.5 : 1.0;
        case AGUA:
            return (tipoOponente == Tipo.FUEGO) ? 2.0 : (tipoOponente == Tipo.PLANTA) ? 0.5 : 1.0;
        case PLANTA:
            return (tipoOponente == Tipo.AGUA) ? 2.0 : (tipoOponente == Tipo.FUEGO) ? 0.5 : 1.0;
        default:
            return 1.0; // Otros tipos por defecto no tienen ventajas ni desventajas
        }
    } 

    // Tal vez se puedan hacer dos metodos calculoDeDaño(), uno que tenga en cuenta el factor de tipo y otro que no
    private int calculoDelDaño(int ataquePropio, int defensaEnemigo, double factorTipo) {
        return (int)Math.ceil(2 * (1 + ataquePropio/defensaEnemigo) * ((Math.random()% 1.25) + 0.5) * factorTipo);
    }

    protected int recibirDaño(Criatura atacante) {
        double factorTipo = factorTipo(atacante.tipo);
        int dañoRecibido = calculoDelDaño(atacante.ataque, this.defensa, factorTipo);
        
        if (this.vida <= dañoRecibido) {
            this.setVida(0);          
        }
        else {
            this.setVida(this.getVida() - dañoRecibido);
        }
        
        if (this.getVida() == 0) {
            logger.desmayo(this);
        }

        return dañoRecibido;
    }
    
    protected int recibirDañoFijo(int dañoFijo) {
        if (this.vida <= dañoFijo) {
            this.setVida(0);          
        }
        else {
            this.setVida(this.getVida() - dañoFijo);
        }
        
        if (this.getVida() == 0) {
            logger.desmayo(this);
        }
        
        return dañoFijo;
    }

    public int getVida() {
        return vida;
    }
    
    public void setVida(int vida) {
        this.vida = vida;
        uiInfoCriatura.actualizar();
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaque() {
        return ataque;
    }
    
    public void setAtaque(int ataque) {
        this.ataque = ataque > 0 ? ataque : 1;
    }
    
    public int getAtaqueOriginal() {
        return ataqueOriginal;
    }

    public int getDefensa() {
        return defensa;
    }
    
    public void setDefensa(int defensa) {
        this.defensa = defensa > 0 ? defensa : 1;
    }
    
    public int getDefensaOriginal() {
        return defensaOriginal;
    }

    public boolean esEquipo1() {
        return equipo1;
    }

    public void setVisualSeleccionado(boolean visualSeleccionado) {
        this.visualSeleccionado = visualSeleccionado;
        render();
    }

    public String toString() {
        return nombre;
    }

    public String[] getNombresAtaque() {
        return nombresAtaque;
    }

    public String[] getDetallesAtaque() {
        return detallesAtaque;
    }

    public String getStats() {
        return nombre + " (" + this.getClass().getSimpleName() + ")\n" +
        " - Ataque: " + this.ataque + "\n" +
        " - Defensa: " + this.defensa + "\n" +
        " - Velocidad: 0\n"
        ;
    }
}

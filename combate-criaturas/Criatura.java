import java.util.List;
import java.lang.Math;

import greenfoot.*;

public abstract class Criatura extends Actor {
    protected final String nombre;
    protected final int vidaMaxima;
    protected final int ataqueOriginal;
    protected final int defensaOriginal;

    protected final String[] nombresAtaque;
    protected final String[] detallesAtaque;
    protected final String[] tiposDeLosAtaques;

    protected final boolean equipo1;

    protected LoggerExtraFunctions logger;

    protected int vida;
    protected int ataque;
    protected int defensa;

    protected String tipo;

    private UIInfoCriatura uiInfoCriatura;

    private boolean visualHover;
    private boolean visualSeleccionado;

    private final MyGreenfootImage imagenOriginal;

    public Criatura(String nombre, int vida, int ataque, int defensa, String tipo, String[] nombresAtaque, String[] tiposDeLosAtaques, boolean equipo1, String[] detallesAtaque) {
        this.nombre = nombre;

        this.vidaMaxima = vida;
        this.ataqueOriginal = ataque;
        this.defensaOriginal = defensa;

        this.nombresAtaque = nombresAtaque;
        this.detallesAtaque = detallesAtaque;
        this.tiposDeLosAtaques = tiposDeLosAtaques;

        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;

        this.equipo1 = equipo1;

        // TODO: Hay que limpiar la consola para que no muestre los logs pasados

        this.tipo = tipo;

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
        String nombreDelAtaque = this.getNombresAtaque()[0];
        String tipoDelAtaque = this.getTiposDeLosAtaques()[0];
        int efectividad = TipoElemental.efectividadDeTipoContra(tipoDelAtaque, otro.getTipo());
        int dañoRecibido = otro.recibirDaño(this, efectividad);

        logDeAtaqueYCalculoDeDaño(this, otro, nombreDelAtaque, tipoDelAtaque, dañoRecibido);
    }

    protected void logDeAtaqueYCalculoDeDaño(Criatura criaturaActual, Criatura otro, String nombreDelAtaque, String tipoDelAtaque, int dañoRecibido) {
        this.logger.ataque(criaturaActual, otro, nombreDelAtaque);
        logDeEfectividadDeAtaque(tipoDelAtaque, otro);
        this.logger.calcularDañoCon(this.getAtaque());
        this.logger.dañoRecibido(otro, dañoRecibido);
    }

    protected void logDeEfectividadDeAtaque(String tipoDelAtaque, Criatura defensora) {
        int efectividad = TipoElemental.efectividadDeTipoContra(tipoDelAtaque, defensora.getTipo());

        if (efectividad == 1) {
            this.logger.esSuperEfectivo();
        }
        if (efectividad == -1) {
            this.logger.esPocoEfectivo();
        }
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

    // El parametro 'efectividad' puede variar ser -1, 0 o 1, o 2 si el ataque deberia hacer mucho daño
    private int calculoDelDaño(Criatura atacante, Criatura enemigo, int efectividad) {
        int ataqueAtacante = atacante.getAtaque();
        int defensaEnemigo = enemigo.getDefensa();
        double factorRandom = (Math.random() % 1.25) + 0.5;
        double factorDeTipo = (efectividad * 0.25) + 1;

        return (int)Math.ceil(2 * (1 + ataqueAtacante/defensaEnemigo) * factorRandom * factorDeTipo);
    }

    protected int recibirDaño(Criatura atacante, int efectividad) {
        int dañoRecibido = calculoDelDaño(atacante, this, efectividad);

        if (dañoRecibido <= 0) {
            dañoRecibido = 1;
        }

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

    public String getTipo() {
        return tipo;
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

    public String[] getTiposDeLosAtaques() {
        return tiposDeLosAtaques;
    }

    public String getStats() {
        return nombre + " (" + this.getClass().getSimpleName() + ")\n" +
        " - Ataque: " + this.ataque + "(" + this.ataqueOriginal + ")" + "\n" +
        " - Defensa: " + this.defensa + "(" + this.defensaOriginal + ")" + "\n" +
        " - Velocidad: 0\n"
        ;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Charmander extends Criatura
{
    boolean mejorAtaqueUsado;

    public Charmander(String nombre, boolean imagenEspejada, int ataque, int defensa, int velocidad) {
        super(nombre, 22, 24, 15, TipoElemental.FUEGO,
            new String[] { "Arañazo", "Fuego fatuo", "Ascuas", "Erupcion" },
            new String[] {TipoElemental.NORMAL, TipoElemental.NORMAL, TipoElemental.FUEGO, TipoElemental.FUEGO}, imagenEspejada,
            new String[] { "Causa un daño moderado a un enemigo", "Provoca el estado alterado 'quemado' en el objetivo", "Causa un daño de fuego a un enemigo", "Provoca quemaduras con con daño elevado, pero debido al intenso calor, solo se puede usar una vez" });

        this.mejorAtaqueUsado = false;
    }

    public Charmander(String nombre) {
        this(nombre, true, 5, 4, 3);
    }

    public void atacar2(Criatura otro) {
        int ataquePokemonUsuario = this.getAtaque();
        String nombreDelAtaque = this.getNombresAtaque()[1];

        // Falta la implementacion de los estados alterados
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar3(Criatura otro) {
        String nombreDelAtaque = this.getNombresAtaque()[2];
        String tipoDelAtaque = this.getTiposDeLosAtaques()[2];
        int efectividad = TipoElemental.efectividadDeTipoContra(tipoDelAtaque, otro.getTipo());
        int dañoRecibido = otro.recibirDaño(this, efectividad);

        logDeAtaqueYCalculoDeDaño(this, otro, nombreDelAtaque, tipoDelAtaque, dañoRecibido);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar4(Criatura otro) {
        // Falta la implementacion de los estados alterados

        if (this.mejorAtaqueUsado) {
            this.logger.imprimirCualquierMensaje("El ataque " + this.getNombresAtaque()[3] + " solo puede ser usado una vez por combate, hay que dejar que " + this.toString() + " se enfrie");
            return;
        }

        String nombreDelAtaque = this.getNombresAtaque()[3];
        String tipoDelAtaque = this.getTiposDeLosAtaques()[3];
        // PRUEBA DEL MODIFICADOR DE EFECTIVIDAD CON EL +1 (1.50 en vez de 1.25)
        int efectividad = TipoElemental.efectividadDeTipoContra(tipoDelAtaque, otro.getTipo()) + 1;
        int dañoRecibido = otro.recibirDaño(this, efectividad);

        logDeAtaqueYCalculoDeDaño(this, otro, nombreDelAtaque, tipoDelAtaque, dañoRecibido);
        this.logger.dañoDeRetroceso(this);
        this.logger.imprimirCualquierMensaje("La criatura " + this.toString() + " ya no podra volver a usar el ataque " + nombreDelAtaque + " por el resto del combate");
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}

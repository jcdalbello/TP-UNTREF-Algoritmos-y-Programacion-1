import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Squirtle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Squirtle extends Criatura
{
    public Squirtle(String nombre, boolean imagenEspejada) {
        super(nombre, 21, 13, 17, new String[] { "Destructor", "Refugio", "Pistola Agua", "Acua Cola" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo",
                               "Aumenta un nivel la Defensa al usuario.",
                               "Causa un daño moderado a un enemigo",
                               "Causa un daño elevado a un enemigo" });
    }

    public Squirtle(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        int defensaPokemonUsuario = this.getDefensa();
        String nombreDelAtaque = this.getNombresAtaque()[1];
        
        this.logger.ataqueASiMismo(this, nombreDelAtaque);
        this.logger.afectarCaracteristica(this, "Defensa", defensaPokemonUsuario, (int)(defensaPokemonUsuario * 1.25), true);
        
        this.setDefensa((int)(defensaPokemonUsuario * 1.25));
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return otro == this;
    }

    public void atacar3(Criatura otro) {
        // Causa daño de tipo agua
        int dañoRecibido = otro.recibirDaño(this);
        String nombreDelAtaque = this.getNombresAtaque()[2];

        logger.ataque(this, otro, nombreDelAtaque);
        logger.calcularDañoCon(this.getAtaque());
        logger.dañoRecibido(otro, dañoRecibido);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar4(Criatura otro) {
        // Causa mucho daño de tipo agua, pero baja mucho la defensa del usuario
        int dañoRecibido = otro.recibirDaño(this);
        String nombreDelAtaque = this.getNombresAtaque()[3];
        
        logger.ataque(this, otro, nombreDelAtaque);
        logger.calcularDañoCon(this.getAtaque());
        logger.dañoRecibido(otro, dañoRecibido);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}
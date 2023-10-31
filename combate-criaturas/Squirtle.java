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
        super(nombre, 21, 13, 17, new String[] { "Destructor", "Ebullicion", "Pistola Agua", "Rompe coraza" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo",
                               "Quema al objetivo con vapor a altas temperaturas",
                               "Causa un daño moderado a un enemigo",
                               "Rompe su caparazon, aumentando mucho su ataque pero reduciendo mucho su defensa" }, Tipo.AGUA);
    }

    public Squirtle(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        String nombreDelAtaque = this.getNombresAtaque()[1];
        
        this.logger.ataque(this, otro, nombreDelAtaque);
        this.logger.cambiarEstado(otro, Estado.QUEMADO);
        
        if (otro.estado == Estado.SALUDABLE) {
            otro.setEstado(Estado.QUEMADO);
        }
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
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
        String nombreDelAtaque = this.getNombresAtaque()[3];
        this.logger.afectarCaracteristica(this,"Ataque",this.getAtaque(),(int)(this.getAtaque()*2),true);
        this.logger.afectarCaracteristica(this,"Defensa",this.getDefensa(),(int)(this.getDefensa()*0.5),false);
        this.setAtaque((int)(this.getAtaque()*2));
        this.setDefensa((int)(this.getDefensa()*0.5));
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return otro == this;
    }
}
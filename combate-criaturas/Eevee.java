import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eevee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Eevee extends Criatura
{
    /**
     * Act - do whatever the Eevee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Eevee(String nombre, boolean imagenEspejada) {
        super(nombre, 20, 19, 19, new String[] { "Placaje", "Gruñido", "Ataque Rápido", "Mordida" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Causa un daño moderado a un enemigo", "Causa un daño moderado a un enemigo", "Causa un daño elevado a un enemigo" });
    }
    
    public Eevee(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return true;
    }

    public void atacar3(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return true;
    }

    public void atacar4(Criatura otro) {
        // otro.recibirGolpeCritico(7);
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return true;
    }
}

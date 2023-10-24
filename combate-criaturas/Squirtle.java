import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Squirtle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Squirtle extends Criatura
{
    public Squirtle(String nombre, boolean imagenEspejada, int ataque, int defensa, int velocidad) {
        super(nombre, 21, new String[] { "Placaje", "Refugio", "Pistola Agu", "Acua Cola" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Aumenta en dos puntos la Defensa a si mismo.", "Causa un daño moderado a un enemigo", "Causa un daño elevado a un enemigo" },
                ataque, defensa, velocidad);
    }

    public Squirtle(String nombre) {
        this(nombre, true, 4, 3, 4);
    }

    public void atacar2(Criatura otro) {
        modificarDefensa(2, true);
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
        otro.recibirGolpeCritico(6);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return true;
    }
}

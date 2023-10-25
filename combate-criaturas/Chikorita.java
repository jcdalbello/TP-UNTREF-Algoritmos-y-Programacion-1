import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chikorita here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chikorita extends Criatura
{
    public Chikorita(String nombre, boolean imagenEspejada, int ataque, int defensa, int velocidad) {
        super(nombre, 21, new String[] { "Placaje", "Hoja Afilada", "Refugio", "Aromaterapia" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Golpe crítico", "Aumenta en dos puntos la Defensa a si mismo.", "Cura a cualquier miembro de tu equipo." },
                ataque, defensa, velocidad);
    }

    public Chikorita(String nombre) {
        this(nombre, false, 4, 4, 5);
    }

    public void atacar2(Criatura otro) {
        otro.recibirGolpeCritico(6);
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return true;
    }

    public void atacar3(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return false;
    }

    public void atacar4(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return false;
    }
}

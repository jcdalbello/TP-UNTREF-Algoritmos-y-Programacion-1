import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chikorita here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chikorita extends Criatura
{
    public Chikorita(String nombre, boolean imagenEspejada) {
        super(nombre, 21, new String[] { "Placaje", "- Hoja Afilada -", "- Hoja Mágica -", "- Aromaterapia -" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Golpe crítico", "Causa un daño moderado a un enemigo", "Cura a cualquier miembro de tu equipo." });
    }

    public Chikorita(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return false;
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

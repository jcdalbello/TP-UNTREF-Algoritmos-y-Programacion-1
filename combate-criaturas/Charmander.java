import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Charmander extends Criatura
{
    public Charmander(String nombre, boolean imagenEspejada, int ataque, int defensa, int velocidad) {
        super(nombre, 27, new String[] { "Placaje", "Gruñido", "Cara de susto", "Lanzallamas" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Baja dos puntos la defensa al rival", "Baja dos puntos de velocidad al rival", "Provoca quemaduras con con daño elevado" },
                ataque, defensa, velocidad);
    }

    public Charmander(String nombre) {
        this(nombre, true, 5, 4, 3);
    }

    public void atacar2(Criatura otro) {
        otro.modificarDefensa(2, false);
        //atacar1(otro);
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
        otro.recibirDaño(this);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return false;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bulbasaur here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bulbasaur extends Criatura
{
    public Bulbasaur(String nombre, boolean imagenEspejada) {
        super(nombre, 20, 15, 15, new String[] { "Placaje", "Latigo", "Polvo Veneno", "Tormenta de hojas" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", 
                               "Baja un nivel el Defensa al rival",
                               "Causa un daño moderado a un enemigo",
                               "Provoca Envenenamiento con daño elevado" },
                               Tipo.PLANTA);
    }

    public Bulbasaur(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        int ataqueCriaturaEnemiga = otro.getDefensa();
        otro.setDefensa((int)(ataqueCriaturaEnemiga * 0.75));
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar3(Criatura otro) {
        // Causa el estado "envenenado" al enemigo
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar4(Criatura otro) {
        // "Hace mucho daño con el tipo Planta, pero baja el ataque del usuario"
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}

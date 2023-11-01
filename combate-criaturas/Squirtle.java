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
        super(nombre, 21, 13, 17, new String[] { "Pistola Agua", "Ebullicion", "Refugio", "Rompe coraza" }, imagenEspejada,
                new String[] { "Causa daño de tipo AGUA a un enemigo.",
                               "Quema al objetivo con vapor a altas temperaturas.",
                               "Aumenta la defensa del usuario.",
                               "El usuario rompe su caparazon, aumentando mucho su ataque pero tambien reduciendo mucho su defensa" }, Tipo.AGUA);
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
        int defensaUsuario = this.getDefensa();
        String nombreDelAtaque = this.getNombresAtaque()[2];
        
        this.logger.ataqueASiMismo(this, nombreDelAtaque);
        this.logger.afectarCaracteristica(this, "Defensa", defensaUsuario, (int)(defensaUsuario * 1.25), true);
        
        this.setDefensa((int)(defensaUsuario * 1.25));
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return otro == this;
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
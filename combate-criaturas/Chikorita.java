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
        super(nombre, 24, 11, 18, new String[] { "Latigo cepa", "Aromaterapia", "Refuerzo", "Deseo cura" }, imagenEspejada,
                new String[] { "Causa daño de tipo PLANTA a un enemigo.",
                               "Cura los estados alterados de un miembro del equipo.",
                               "Aumenta el ataque y la defensa de su compañero.",
                                // 'Deseo cura' cura la vida del compañero al 100%, saca los efectos de estado y resetea las caracteristicas
                               "Cura completamente a su compañero, pero el usuario cae debilitado." }, 
                Tipo.PLANTA);
    }

    public Chikorita(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        String nombreDelAtaque = this.getNombresAtaque()[1];
         
        if (otro == this) {
            this.logger.ataqueASiMismo(this, nombreDelAtaque);
        }
        else {
            this.logger.asistir(this, otro, nombreDelAtaque);
        }
        
        this.logger.cambiarEstado(otro, Estado.SALUDABLE);
        otro.setEstado(Estado.SALUDABLE);
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return this.esDelMismoEquipoQue(otro);
    }

    public void atacar3(Criatura otro) {
        int ataqueCriatura = otro.getAtaque();
        int defensaCriatura = otro.getDefensa();
        String nombreDelAtaque = this.getNombresAtaque()[2];
        
        this.logger.asistir(this, otro, nombreDelAtaque);
        this.logger.afectarCaracteristica(otro, "Ataque", ataqueCriatura, (int)(ataqueCriatura * 1.25), true);
        this.logger.afectarCaracteristica(otro, "Defensa", defensaCriatura, (int)(defensaCriatura * 1.25), true);
        
        otro.setAtaque((int)(ataqueCriatura * 1.25));
        otro.setDefensa((int)(defensaCriatura * 1.25));
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return this.esDelMismoEquipoQue(otro) && otro != this;
    }

    public void atacar4(Criatura otro) {
        String nombreDelAtaque = this.getNombresAtaque()[3];
        
        otro.setVida(otro.getVidaMaxima());
        
        // El ataque y la defensa solo se resetean si estan mas bajas de lo normal, si estan subidas se mantienen igual
        if (otro.getAtaque() < otro.getAtaqueOriginal()) {
            otro.setAtaque(otro.getAtaqueOriginal());
        }
        if (otro.getDefensa() < otro.getDefensaOriginal()) {
            otro.setDefensa(otro.getDefensaOriginal());
        }
        
        this.logger.asistir(this, otro, nombreDelAtaque);
        this.logger.resetearCaracteristica(otro, "Ataque", otro.getAtaque());
        this.logger.resetearCaracteristica(otro, "Defensa", otro.getDefensa());
        
        this.logger.cambiarEstado(otro, Estado.SALUDABLE);
        otro.setEstado(Estado.SALUDABLE);
        
        this.logger.sacrificio(this);
        
        // Chikorita se muere :'(
        this.recibirDañoFijo(this.getVida());
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return this.esDelMismoEquipoQue(otro) && otro != this;
    }
}
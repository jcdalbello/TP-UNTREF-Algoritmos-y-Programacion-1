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
        super(nombre, 20, 19, 19, new String[] { "Placaje", "Gruñido", "Dar una mano", "Mordida" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", 
                    "Baja el ataque del objetivo", 
                    "Sube mucho el ataque del aliado,pero baja la defensa del usuario", 
                    "Causa un daño elevado a un enemigo" },
                    Tipo.NORMAL);
    }
    
    public Eevee(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        int ataqueCriaturaEnemiga = otro.getAtaque();
        String nombreDelAtaque = this.getNombresAtaque()[1];
        
        this.logger.ataque(this, otro, nombreDelAtaque);
        this.logger.afectarCaracteristica(otro, "Ataque", ataqueCriaturaEnemiga, (int)(ataqueCriaturaEnemiga * 0.75), false);
        
        otro.setAtaque((int)(ataqueCriaturaEnemiga * 0.75));
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar3(Criatura otro) {
        String nombreAtaque=this.getNombresAtaque()[2];
        int ataqueCriaturaAliada=otro.getAtaque();
        int defensaCriaturaActual=this.getDefensa();
        this.logger.asistir(this,otro, nombreAtaque);
        this.logger.afectarCaracteristica(otro,"Ataque", ataqueCriaturaAliada, (int)(ataqueCriaturaAliada*1.5), true);
        this.logger.afectarCaracteristica(this,"Defensa", defensaCriaturaActual, (int)(defensaCriaturaActual*0.75), false);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return this.esDelMismoEquipoQue(otro) && otro !=this;
    }

    public void atacar4(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return true;
    }
}

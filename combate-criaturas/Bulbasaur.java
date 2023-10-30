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
        int defensaCriaturaEnemiga = otro.getDefensa();
        String nombreAtaque=this.getNombresAtaque()[1];
        otro.setDefensa((int)(defensaCriaturaEnemiga * 0.75));
        this.logger.ataque(this,otro,nombreAtaque);
        this.logger.afectarCaracteristica(otro,"Defensa", defensaCriaturaEnemiga,this.reducirCaracterisitica(defensaCriaturaEnemiga), false);
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
        int danioRecibido;
        String nombreAtaque;
        for(int i=0;i<2;i++){
            danioRecibido=otro.recibirDaño(this);
            nombreAtaque=this.getNombresAtaque()[3];
            this.logger.ataque(this,otro, nombreAtaque);
            this.logger.calcularDañoCon(this.getAtaque());
            this.logger.dañoRecibido(otro,danioRecibido);
        }
        this.logger.afectarCaracteristica(this, "Ataque", this.getAtaque(),this.reducirCaracterisitica(this.getAtaque()),false);
        this.setAtaque(this.reducirCaracterisitica(this.getAtaque()));
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
    
    private int reducirCaracterisitica(int ataque){
        return (int)(ataque*0.75);
    }
}

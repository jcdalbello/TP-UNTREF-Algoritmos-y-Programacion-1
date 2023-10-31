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
                "Envenena al objetivo",
                "Hace mucho daño con el tipo Planta, pero baja el ataque del usuario" },
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
        String nombreDelAtaque = this.getNombresAtaque()[2];

        this.logger.ataque(this, otro, nombreDelAtaque);
        this.logger.cambiarEstado(otro, Estado.ENVENENADO);

        if (otro.estado == Estado.SALUDABLE) {
            otro.setEstado(Estado.ENVENENADO);
        }
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar4(Criatura otro) {
        // "Hace mucho daño con el tipo Planta, pero baja el ataque del usuario"
        String nombreAtaque = this.getNombresAtaque()[3];

        int ataqueCriatura = this.getAtaque();

        this.setAtaque((int)(ataqueCriatura * 1.50));
        int dañoRecibido = otro.recibirDaño(this);
        this.setAtaque(ataqueCriatura);

        this.logger.ataque(this, otro, nombreAtaque);
        this.logger.calcularDañoCon(this.getAtaque());
        this.logger.dañoRecibido(otro, dañoRecibido);

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

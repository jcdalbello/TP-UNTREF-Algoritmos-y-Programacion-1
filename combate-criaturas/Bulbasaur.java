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
        super(nombre, 20, 15, 15, TipoElemental.PLANTA,
                new String[] { "Tacleada", "Latigo", "Polvo Veneno", "Tormenta de hojas" },
                new String[] {TipoElemental.NORMAL, TipoElemental.NORMAL, TipoElemental.NORMAL, TipoElemental.PLANTA}, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", 
                               "Baja un nivel el Defensa al rival",
                               "Provoca el estado alterado 'envenenado' al objetivo",
                               "Causa mucho daño de tipo Planta, pero al usar todas sus hojas, el poder ofensivo del usuario se reduce a la mitad" });
    }

    public Bulbasaur(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        int defensaCriaturaEnemiga = otro.getDefensa();
        String nombreDelAtaque = this.getNombresAtaque()[1];
        
        this.logger.ataque(this, otro, nombreDelAtaque);
        this.logger.afectarCaracteristica(otro, "Defensa", defensaCriaturaEnemiga, (int)(defensaCriaturaEnemiga * 0.75), false);
        
        otro.setDefensa((int)(defensaCriaturaEnemiga * 0.75));
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar3(Criatura otro) {
        // Faltan implementar los estados alterados
        // Causa el estado "envenenado" al enemigo
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar4(Criatura otro) {
        // "Hace mucho daño con el tipo Planta, pero reduce el ataque del usuario a la mitad"
        String nombreDelAtaque = this.getNombresAtaque()[3];
        String tipoDelAtaque = this.getTiposDeLosAtaques()[3];
        // PRUEBA DEL MODIFICADOR DE EFECTIVIDAD CON EL +1 (1.50 en vez de 1.25)
        int efectividad = TipoElemental.efectividadDeTipoContra(tipoDelAtaque, otro.getTipo()) + 1;
        int dañoRecibido = otro.recibirDaño(this, efectividad);
        
        this.logger.afectarCaracteristica(this, "Ataque", this.getAtaque(), (int)(this.getAtaque() / 2), false);
        this.setAtaque(this.getAtaque() / 2);        
        
        logDeAtaqueYCalculoDeDaño(this, otro, nombreDelAtaque, dañoRecibido);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}

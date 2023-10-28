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
        super(nombre, 21, 13, 17, TipoElemental.AGUA,
                new String[] { "Destructor", "Refugio", "Pistola Agua", "Acua Cola" }, 
                new String[] {TipoElemental.NORMAL, TipoElemental.NORMAL, TipoElemental.AGUA, TipoElemental.AGUA}, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo",
                               "Aumenta un nivel la Defensa al usuario.",
                               "Causa un daño moderado a un enemigo",
                               "Causa un daño elevado de agua a un enemigo, pero el caparazon del usuario se quiebra, bajando mucho su defensa" });
    }

    public Squirtle(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        int defensaPokemonUsuario = this.getDefensa();
        String nombreDelAtaque = this.getNombresAtaque()[1];
        
        this.logger.ataqueASiMismo(this, nombreDelAtaque);
        this.logger.afectarCaracteristica(this, "Defensa", defensaPokemonUsuario, (int)(defensaPokemonUsuario * 1.25), true);
        
        this.setDefensa((int)(defensaPokemonUsuario * 1.25));
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return otro == this;
    }
    
    
    public void atacar3(Criatura otro) {
        String nombreDelAtaque = this.getNombresAtaque()[2];
        String tipoDelAtaque = this.getTiposDeLosAtaques()[2];
        int efectividad = TipoElemental.efectividadDeTipoContra(tipoDelAtaque, otro.getTipo());
        int dañoRecibido = otro.recibirDaño(this, efectividad);   
        
        logDeAtaqueYCalculoDeDaño(this, otro, nombreDelAtaque, dañoRecibido);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
    
    public void atacar4(Criatura otro) {
        String nombreDelAtaque = this.getNombresAtaque()[3];
        String tipoDelAtaque = this.getTiposDeLosAtaques()[3];
        // PRUEBA DEL MODIFICADOR DE EFECTIVIDAD CON EL +1 (1.50 en vez de 1.25)
        int efectividad = TipoElemental.efectividadDeTipoContra(tipoDelAtaque, otro.getTipo()) + 1;
        int dañoRecibido = otro.recibirDaño(this, efectividad);
        
        this.logger.afectarCaracteristica(this, "Defensa", this.getDefensa(), (int)(this.getDefensa() / 2), false);
        this.setDefensa(this.getDefensa() / 2);        
        
        logDeAtaqueYCalculoDeDaño(this, otro, nombreDelAtaque, dañoRecibido);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}
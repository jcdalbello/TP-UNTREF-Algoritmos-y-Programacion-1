import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eevee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Eevee extends Criatura
{
    int cantidadAfectadosPorOjitosTiernos;
    Criatura afectadosPorOjitosTiernos[];

    public Eevee(String nombre, boolean imagenEspejada, int ataque, int defensa, int velocidad) {
        super(nombre, 20, 19, 19, TipoElemental.NORMAL,
            new String[] { "Tacleada", "Ojitos tiernos", "Imagen", "Golpe de guardia baja" },
            new String[] { TipoElemental.NORMAL, TipoElemental.NORMAL, TipoElemental.NORMAL, TipoElemental.NORMAL }, imagenEspejada,
            new String[] { "Causa un daño moderado a un enemigo", "Baja mucho el ataque del objetivo, pero sube su defensa", "Causa daño adicional si el usuario sufre de un estado alterado", "Causa muchisimo daño, pero solo se puede utilizar si ya se uso 'Ojitos tiernos' en el objetivo, luego el objetivo vuelve a subir la guardia" });

        this.cantidadAfectadosPorOjitosTiernos = 0;
        this.afectadosPorOjitosTiernos = new Criatura[] { null, null };
    }

    public Eevee(String nombre) {
        this(nombre, false, 5, 3, 5);
    }

    public void atacar2(Criatura otro) {
        // Gruñido, reduce el ataque del enemigo un 25%
        int ataqueCriaturaEnemiga = otro.getAtaque();
        int defensaCriaturaEnemiga = otro.getDefensa();
        String nombreDelAtaque = this.getNombresAtaque()[1];

        this.logger.ataque(this, otro, nombreDelAtaque);
        this.logger.afectarCaracteristica(otro, "Ataque", ataqueCriaturaEnemiga, (int)(ataqueCriaturaEnemiga * 0.50), false);
        this.logger.afectarCaracteristica(otro, "Defensa", ataqueCriaturaEnemiga, (int)(ataqueCriaturaEnemiga * 1.25), true);
        
        if (cantidadAfectadosPorOjitosTiernos < 2) {
            if (this.afectadosPorOjitosTiernos[0] != otro) {
                this.afectadosPorOjitosTiernos[this.cantidadAfectadosPorOjitosTiernos] = otro;
                this.cantidadAfectadosPorOjitosTiernos++;
            }
        }
        
        otro.setAtaque((int)(ataqueCriaturaEnemiga * 0.50));
        otro.setDefensa((int)(defensaCriaturaEnemiga * 1.25));
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar3(Criatura otro) {
        String nombreDelAtaque = this.getNombresAtaque()[2];
        String tipoDelAtaque = this.getTiposDeLosAtaques()[2];
        // PRUEBA DEL MODIFICADOR DE EFECTIVIDAD CON EL +1 (1.50 en vez de 1.25)
        int efectividad = TipoElemental.efectividadDeTipoContra(tipoDelAtaque, otro.getTipo());
        
        // Para cuando esten implementados los estados alterados
        // if (<otro.getEstadoAlterado() != null>) { efectividad++; }
        
        int dañoRecibido = otro.recibirDaño(this, efectividad);
        
        this.recibirDañoFijo(this.getVidaMaxima() / 2);
        
        logDeAtaqueYCalculoDeDaño(this, otro, nombreDelAtaque, tipoDelAtaque, dañoRecibido);
        this.logger.dañoDeRetroceso(this);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar4(Criatura otro) {
        String nombreDelAtaque = this.getNombresAtaque()[3];
        String tipoDelAtaque = this.getTiposDeLosAtaques()[3];
        // PRUEBA DEL MODIFICADOR DE EFECTIVIDAD CON EL +1 (1.50 en vez de 1.25)
        int efectividad = TipoElemental.efectividadDeTipoContra(tipoDelAtaque, otro.getTipo()) + 2;
        
        if (otro != this.afectadosPorOjitosTiernos[0] && otro != this.afectadosPorOjitosTiernos[1]) {
            this.logger.imprimirCualquierMensaje("La criatura " + otro.toString() + " no ha bajado la guardia, deberias probar usando 'Ojitos tiernos'");
            return;
        }
        
        int dañoRecibido = otro.recibirDaño(this, efectividad);
        
        this.logger.imprimirCualquierMensaje(otro.toString() + " volvio a subir la guardia");
        
        if (otro == this.afectadosPorOjitosTiernos[0]) {
            this.afectadosPorOjitosTiernos[0] = null;
        }
        if (otro == this.afectadosPorOjitosTiernos[1]) {
            this.afectadosPorOjitosTiernos[1] = null;
        }
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}

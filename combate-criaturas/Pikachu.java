public class Pikachu extends Criatura {
    public Pikachu(String nombre, boolean imagenEspejada) {
        super(nombre, 16, 18, 13, TipoElemental.ELECTRICO,
            new String[] { "Placaje", "Onda trueno", "Impactrueno", "Tacleada de voltios" },
            new String[] {TipoElemental.NORMAL, TipoElemental.NORMAL, TipoElemental.ELECTRICO, TipoElemental.ELECTRICO}, imagenEspejada,
            new String[] { "Causa un daño moderado a un enemigo",
                           "Reduce el ataque del enemigo en un 25%",
                           "Causa un daño de tipo electrico a un enemigo",
                           "Causa un gran daño de tipo electrico al enemigo, pero el usuario recibe daño de retroceso." }
            );
    }

    public Pikachu(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        // Gruñido, reduce el ataque del enemigo un 25%
        int ataqueCriaturaEnemiga = otro.getAtaque();
        String nombreDelAtaque = this.getNombresAtaque()[1];
        
        // Falta la implementacion de tipos
        // Cambiar el estado alterado del objetivo a 'paralizado'
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {        
        return !this.esDelMismoEquipoQue(otro);
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
        
        this.recibirDañoFijo(this.getVidaMaxima() / 2);
        
        logDeAtaqueYCalculoDeDaño(this, otro, nombreDelAtaque, dañoRecibido);
        this.logger.dañoDeRetroceso(this);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}

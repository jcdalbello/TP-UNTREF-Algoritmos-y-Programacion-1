public class Pikachu extends Criatura {
    public Pikachu(String nombre, boolean imagenEspejada) {
        super(nombre, 16, 18, 13, new String[] { "Impactrueno", "Gruñido", "Onda Trueno", "Tacleada de voltios" }, imagenEspejada,
            new String[] { "Causa daño de tipo ELECTRICO a un enemigo.",
                "Reduce el ataque del enemigo.",
                "Paraliza al rival.",
                "Causa daño de tipo ELECTRICO e incrementa el ataque del usuario, pero este recibe daño de retroceso igual a la mitad de su vida actual."},
            Tipo.ELECTRICO);
    }

    public Pikachu(String nombre) {
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
        // Paralizar al objetivo
        String nombreDelAtaque = this.getNombresAtaque()[2];
        
        this.logger.ataque(this, otro, nombreDelAtaque);
        this.logger.cambiarEstado(otro, Estado.PARALIZADO);
        
        if (otro.estado == Estado.SALUDABLE) {
            otro.setEstado(Estado.PARALIZADO);
        }
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar4(Criatura otro) {
        int dañoRecibido = otro.recibirDaño(this);
        int ataqueCriatura = this.getAtaque();
        String nombreDelAtaque = this.getNombresAtaque()[3];

        logger.ataque(this, otro, nombreDelAtaque);
        
        this.logMensajeDeEfectividadDelAtaque(otro.tipo);
        
        logger.calcularDañoCon(this.getAtaque());
        logger.dañoRecibido(otro, dañoRecibido);
        
        this.logger.afectarCaracteristica(this, "Ataque", ataqueCriatura, (int)(ataqueCriatura * 1.25), true);
        this.setAtaque((int)(ataqueCriatura * 1.25));

        this.logger.dañorDeRetroceso(this, this.getVida() / 2);
        this.setVida(this.getVida() / 2);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}

public class Pikachu extends Criatura {
    public Pikachu(String nombre, boolean imagenEspejada) {
        super(nombre, 16, 18, 13, new String[] { "Placaje", "Gruñido", "Impactrueno", "Onda Trueno" }, imagenEspejada,
            new String[] { "Causa un daño moderado a un enemigo",
                           "Reduce el ataque del enemigo en un 25%",
                           "Causa un daño de tipo electrico a un enemigo",
                           "Causa un gran daño de tipo electrico al enemigo, pero el usuario recibe daño de retroceso."}
            );
    }

    public Pikachu(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        // Gruñido, reduce el ataque del enemigo un 25%
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
        // Falta la implementación de tipos
        int dañoRecibido = otro.recibirDaño(this);
        String nombreDelAtaque = this.getNombresAtaque()[2];
        
        logger.ataque(this, otro, nombreDelAtaque);
        logger.calcularDañoCon(this.getAtaque());
        logger.dañoRecibido(otro, dañoRecibido);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar4(Criatura otro) {
        // Falta la implementación de tipos
        int dañoRecibido = otro.recibirDaño(this);
        String nombreDelAtaque = this.getNombresAtaque()[3];
        
        logger.ataque(this, otro, nombreDelAtaque);
        logger.calcularDañoCon(this.getAtaque());
        logger.dañoRecibido(otro, dañoRecibido);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}

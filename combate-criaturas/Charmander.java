public class Charmander extends Criatura {
    boolean erupcionYaFueUsado;
    
    public Charmander(String nombre, boolean imagenEspejada) {
        super(nombre, 15, 22, 13, new String[] { "Ascuas", "Calentar motores", "Fuego fatuo", "Erupcion" }, imagenEspejada,
            new String[] { "Causa daño de tipo FUEGO al objetivo.",
                "El usuario entra en calor, aumentando su ataque.",
                "Quema al rival.",
                "Causa mucho daño de tipo FUEGO, pero por el calor que genera, solo se puede usar una vez por combate"},
            Tipo.FUEGO); 
        erupcionYaFueUsado = false;
    }

    public Charmander(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {       
        int ataqueUsuario = this.getAtaque();
        String nombreDelAtaque = this.getNombresAtaque()[1];
        
        this.logger.ataqueASiMismo(this, nombreDelAtaque);
        this.logger.afectarCaracteristica(this, "Ataque", ataqueUsuario, (int)(ataqueUsuario * 1.25), true);
        
        this.setAtaque((int)(ataqueUsuario * 1.25));
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {        
        return otro == this;
    }

    public void atacar3(Criatura otro) {       
        String nombreDelAtaque = this.getNombresAtaque()[2];
        
        this.logger.ataque(this, otro, nombreDelAtaque);
        this.logger.cambiarEstado(otro, Estado.QUEMADO);
        
        if (otro.estado == Estado.SALUDABLE) {
            otro.setEstado(Estado.QUEMADO);
        }
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }

    public void atacar4(Criatura otro) {
        if (erupcionYaFueUsado) {
            this.logger.mostrarMensaje("El movimiento Erupcion ya fue usado previamente, la criatura " + this.toString() + " no debe sobrecalentarse");
            return;
        }
       
        String nombreAtaque = this.getNombresAtaque()[3];

        int ataqueCriatura = this.getAtaque();

        this.setAtaque((int)(ataqueCriatura * 2.0));
        int dañoRecibido = otro.recibirDaño(this);
        this.setAtaque(ataqueCriatura);

        this.logger.ataque(this, otro, nombreAtaque);
        this.logMensajeDeEfectividadDelAtaque(otro.tipo);
        this.logger.calcularDañoCon(this.getAtaque());
        this.logger.dañoRecibido(otro, dañoRecibido);
        
        this.erupcionYaFueUsado = true;
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}

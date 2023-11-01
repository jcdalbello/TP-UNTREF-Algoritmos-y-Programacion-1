import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Eevee extends Criatura
{
    public Eevee(String nombre, boolean imagenEspejada) {
        super(nombre, 20, 19, 19, new String[] { "Placaje", "Chivo expiatorio", "Truco del azar", "Imagen" }, imagenEspejada,
                new String[] { "Causa daño de tipo NORMAL a un enemigo.", 
                    "El usuario intercambia su estado alterado con el de su compañero, curandolo en el proceso.", 
                    "El usuario llama a una fuerza misteriosa, que tiene una probabilidad aleatoria de dejar al usuario o al objetivo con un estado alterado cualquiera, o curar el que ya tienen", 
                    "Ataca dos veces con un ataque de tipo NORMAL si el usuario sufre de un estado alterado" },
                    Tipo.NORMAL);
    }
    
    public Eevee(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        String nombreDelAtaque = this.getNombresAtaque()[1];
        Estado estadoDelCompañero = otro.estado;
        Estado estadoUsuario = this.estado;
        
        this.logger.asistir(this, otro, nombreDelAtaque);
        
        if (this.estado != Estado.SALUDABLE && otro.estado == Estado.SALUDABLE) {
            this.logger.cambiarEstado(otro, Estado.SALUDABLE);
            return;
        }
        
        this.logger.cambiarEstado(otro, Estado.SALUDABLE);
        this.logger.cambiarEstado(this, estadoDelCompañero);
        
        otro.setEstado(Estado.SALUDABLE);
        this.setEstado(estadoDelCompañero);
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return this.esDelMismoEquipoQue(otro) && otro != this;
    }

    public void atacar3(Criatura otro) {
        String nombreAtaque = this.getNombresAtaque()[2];
        
        Criatura objetivos[] = new Criatura[] {otro, this};
        Estado estados[] = new Estado[] { Estado.SALUDABLE, Estado.QUEMADO, Estado.PARALIZADO, Estado.ENVENENADO };
        
        Random rand = new Random();        
        Criatura objetivoAleatorio = objetivos[rand.nextInt(2)];
        Estado estadoAleatorio = estados[rand.nextInt(4)];
        
        if (objetivoAleatorio.estado != Estado.SALUDABLE && estadoAleatorio != Estado.SALUDABLE) {
            this.logger.cambiarEstado(objetivoAleatorio, estadoAleatorio);
            return;
        }
        
        this.logger.cambiarEstado(objetivoAleatorio, estadoAleatorio);
        objetivoAleatorio.setEstado(estadoAleatorio);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        // return !this.esDelMismoEquipoQue(otro);
        return true;
    }

    public void atacar4(Criatura otro) {
        int dañoRecibido;
        String nombreAtaque = this.getNombresAtaque()[3];;
        
        int cantidadDeAtaques = this.estado != Estado.SALUDABLE ? 1 : 2;
        
        for(int i = 0; i < cantidadDeAtaques; i++){
            dañoRecibido = otro.recibirDaño(this);
            
            this.logger.ataque(this, otro, nombreAtaque);
            this.logger.calcularDañoCon(this.getAtaque());
            this.logger.dañoRecibido(otro, dañoRecibido);
        }
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return !this.esDelMismoEquipoQue(otro);
    }
}

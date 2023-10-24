public class Pikachu extends Criatura {
    public Pikachu(String nombre, boolean imagenEspejada, int ataque, int defensa, int velocidad) {
        super(nombre, 27, new String[] { "Placaje", "Gruñido", "Impactrueno", "Onda Trueno" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Causa un daño moderado a un enemigo", "Causa un daño moderado a un enemigo", "Causa un daño elevado a un enemigo" },
                ataque, defensa, velocidad);
    }

    public Pikachu(String nombre) {
        this(nombre, false, 5, 3, 5);
    }

    public void atacar2(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return true;
    }

    public void atacar3(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return true;
    }

    public void atacar4(Criatura otro) {
        otro.recibirGolpeCritico(7);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return true;
    }
}

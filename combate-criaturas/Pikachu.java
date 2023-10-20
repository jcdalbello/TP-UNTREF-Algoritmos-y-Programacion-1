public class Pikachu extends Criatura {
    public Pikachu(String nombre, boolean imagenEspejada) {
        super(nombre, 27, new String[] { "Placaje", "Gruñido", "Impactrueno", "Onda Trueno" }, imagenEspejada,
                new String[] { "Causa un daño moderado a un enemigo", "Causa un daño moderado a un enemigo", "Causa un daño moderado a un enemigo", "Causa un daño elevado a un enemigo" });
    }

    public Pikachu(String nombre) {
        this(nombre, false);
    }

    public void atacar2(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque2En(Criatura otro) {
        return false;
    }

    public void atacar3(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque3En(Criatura otro) {
        return false;
    }

    public void atacar4(Criatura otro) {
        atacar1(otro);
    }

    public boolean puedeRealizarAtaque4En(Criatura otro) {
        return false;
    }
}

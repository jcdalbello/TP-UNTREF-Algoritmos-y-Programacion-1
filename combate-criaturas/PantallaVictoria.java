import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PantallaVictoria extends World
{
    // ganador = 1 --> jugador 1, ganador = 2 --> jugador 2
    public PantallaVictoria(int ganador)
    {    
        super(700, 400, 1);
        
        Texto textoGanador = new Texto("Jugador " + ganador + " gana!", 50, Color.BLACK, Color.WHITE);
        addObject(textoGanador, 350, 200);
        
        GreenfootImage imagenFondo = new GreenfootImage("ending_screen.png");
        getBackground().drawImage(imagenFondo, 0, 0);
        
        BotonReinicio botonReinicio = new BotonReinicio("Reiniciar combate");
        addObject(botonReinicio, 350, 300);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BotonReinicio extends Boton
{
    public BotonReinicio(String texto) {
        super(texto, null, 30, Color.BLACK, 0, 0);
    }
    
    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new PantallaDuelo());
        }
    }
}

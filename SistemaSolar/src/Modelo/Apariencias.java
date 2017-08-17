/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

/**
 *
 * @author eila
 */
public enum Apariencias {
    Sol(Internal.Sol), 
    Mercurio(Internal.Mercurio), 
    Venus(Internal.Venus), 
    Tierra(Internal.Tierra), 
    Marte(Internal.Marte), 
    Jupiter(Internal.Jupiter), 
    Saturno(Internal.Saturno),
    Urano(Internal.Urano) , 
    Neptuno(Internal.Neptuno), 
    Luna(Internal.Luna), 
    Fobos(Internal.Fobos), 
    Deimos(Internal.Deimos), 
    Io(Internal.Io), 
    Europa(Internal.Europa), 
    Calisto(Internal.Calisto), 
    AnilloA(Internal.AnilloA), 
    AnilloB(Internal.AnilloB), 
    AnilloC(Internal.AnilloC), 
    Titania(Internal.Titania), 
    Ariel(Internal.Ariel), 
    Miranda(Internal.Miranda), 
    Triton(Internal.Triton);

  
    private enum Internal { Sol, Mercurio, Venus, Tierra, Marte, Jupiter, Saturno,
    Urano , Neptuno, Luna, Fobos, Deimos, Io, Europa, Calisto, AnilloA, AnilloB, 
    AnilloC, Titania, Ariel, Miranda, Triton };

    private final Appearance ap;

    private Apariencias(Internal i) {
        ap = new Appearance();
        Material m = new Material (
            new Color3f (0.50f, 0.50f, 0.50f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
            new Color3f (0.30f, 0.30f, 0.30f),   // Color especular
            7.0f );                              // Brillo
        
        Texture aTexture = null;
        
        switch (i) {
            case Sol :
                aTexture = new TextureLoader ("imgs/sol.jpg", null).getTexture();
                m.setEmissiveColor(new Color3f (0.50f, 0.50f, 0.50f));
                break;
            case Mercurio :
                aTexture = new TextureLoader ("imgs/mercurio.jpg", null).getTexture();
                break;
            case Venus :
                aTexture = new TextureLoader ("imgs/venus.jpg", null).getTexture();
                break;
            case Tierra :
                aTexture = new TextureLoader ("imgs/dia_8192.jpg", null).getTexture();                               
                break;
            case Marte :
                aTexture = new TextureLoader ("imgs/marte.jpg", null).getTexture();
                break;
            case Jupiter :
                aTexture = new TextureLoader ("imgs/jupiter.jpg", null).getTexture();
                break;
            case Saturno :
                aTexture = new TextureLoader ("imgs/saturno.jpg", null).getTexture();
                break;
            case Urano :
                aTexture = new TextureLoader ("imgs/urano.jpg", null).getTexture();
                break;
            case Neptuno :
                aTexture = new TextureLoader ("imgs/neptuno.jpg", null).getTexture();
                break;
            case Luna :
                aTexture = new TextureLoader ("imgs/luna.jpg", null).getTexture();
                break;
            case Fobos :
                aTexture = new TextureLoader ("imgs/fobos.jpg", null).getTexture();
                break;
            case Deimos :
                aTexture = new TextureLoader ("imgs/deimos.jpg", null).getTexture();
                break;
            case Io :
                aTexture = new TextureLoader ("imgs/io.jpg", null).getTexture();
                break;
            case Europa :
                aTexture = new TextureLoader ("imgs/europa.jpg", null).getTexture();
                break;
            case Calisto :
                aTexture = new TextureLoader ("imgs/calisto.jpg", null).getTexture();
                break;
            case AnilloA :
                aTexture = new TextureLoader ("imgs/anilloA.jpg", null).getTexture();
                break;
            case AnilloB :
                aTexture = new TextureLoader ("imgs/anilloB.jpg", null).getTexture();
                break;
            case AnilloC :
                aTexture = new TextureLoader ("imgs/anilloC.jpg", null).getTexture();
                break;
            case Titania :
                aTexture = new TextureLoader ("imgs/titania.jpg", null).getTexture();
                break;
            case Ariel :
                aTexture = new TextureLoader ("imgs/ariel.jpg", null).getTexture();
                break;
            case Miranda :
                aTexture = new TextureLoader ("imgs/miranda.jpg", null).getTexture();
                break;
            case Triton :
                aTexture = new TextureLoader ("imgs/triton.jpg", null).getTexture();
                break;        
        }
        ap.setTexture (aTexture); 
        ap.setMaterial (m);                            // Brillo
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        ap.setTextureAttributes(ta);
    }

    public Appearance getAppearance () {
      return ap;
    }
}

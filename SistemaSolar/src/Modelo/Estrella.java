/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.media.j3d.Appearance;
import javax.media.j3d.PointLight;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author jestern
 */
public class Estrella extends Astro {
    
    public Estrella(String n, float vR, float r, Appearance ap){
        super(n, vR, 0, r, 0, ap);
        createEsfera();
        PointLight puntualSol = new PointLight();
        puntualSol.setInfluencingBounds (Limites.getInstance());
        puntualSol.setEnable (true);
        
        this.addChild(puntualSol);
    }
    
    public void addPlaneta(Planeta p) {
        
        TransformGroup translacionOrbita = p.createTranslacionOrbita();
        TransformGroup rotacionOrbita = p.createRotationOrbita();
        
        translacionOrbita.addChild(p);
        rotacionOrbita.addChild(translacionOrbita);
        
        this.addChild(rotacionOrbita);
    }
    
    
    
}

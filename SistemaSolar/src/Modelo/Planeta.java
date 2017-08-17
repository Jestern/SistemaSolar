/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author jestern
 */
public class Planeta extends Astro {
    
    public Planeta(String n, float vR, float vT, float r, float rO, Appearance ap){
        super(n, vR, vT, r, rO, ap);
        createEsfera();
    }
    
    public void addSatelite(Satelite s) {
        TransformGroup translacionOrbita = s.createTranslacionOrbita();
        TransformGroup rotacionOrbita = s.createRotationOrbita();
        
        translacionOrbita.addChild(s);
        rotacionOrbita.addChild(translacionOrbita);
        
        this.addChild(rotacionOrbita);
        
    }
    
    public void addAnillo(Anillo a) {
        this.addChild(a);
    }
}

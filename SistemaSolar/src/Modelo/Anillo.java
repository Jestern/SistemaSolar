/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author jestern
 */
public class Anillo extends Astro {
    
    
    public Anillo(String n, float vR, float vT, float r, float rO, Appearance ap) {
        super(n, vR, vT, r, rO, ap);

        TransformGroup rotacion = createRotation();
        
        Shape3D anillo = new ShapeAnillo(rO, r, DIVISIONES, ap);
        
        rotacion.addChild(anillo);
        
        this.addChild(rotacion);
    }
    
}

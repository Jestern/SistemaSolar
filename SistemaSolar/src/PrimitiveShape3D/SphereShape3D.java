/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimitiveShape3D;


import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;

/**
 *
 * @author jestern
 */
public class SphereShape3D extends Shape3D {
    
    public SphereShape3D(Sphere s) {
        Shape3D esfera = s.getShape();
        Appearance ap = s.getAppearance();
        
        this.setGeometry(esfera.getGeometry());
        this.setAppearance(ap);
    }
    
}

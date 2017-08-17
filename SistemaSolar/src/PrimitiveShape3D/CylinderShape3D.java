/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimitiveShape3D;

import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.Shape3D;

/**
 *
 * @author jestern
 */
public class CylinderShape3D extends Shape3D {
    
    public CylinderShape3D(Cylinder c) {
        Shape3D cilindro = c.getShape(Cylinder.TOP);
        this.addGeometry(cilindro.getGeometry());
        
        cilindro = c.getShape(Cylinder.BOTTOM);
        this.addGeometry(cilindro.getGeometry());
        
        cilindro = c.getShape(Cylinder.BODY);
        this.addGeometry(cilindro.getGeometry());
        
        this.setAppearance(c.getAppearance());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimitiveShape3D;

import com.sun.j3d.utils.geometry.Cone;
import javax.media.j3d.Shape3D;

/**
 *
 * @author jestern
 */
public class ConeShape3D extends Shape3D {
    
    public ConeShape3D(Cone c) {
        Shape3D cono = c.getShape(Cone.CAP);
        this.addGeometry(cono.getGeometry());
        
        cono = c.getShape(Cone.BODY);
        this.addGeometry(cono.getGeometry());
        
        this.setAppearance(c.getAppearance());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimitiveShape3D;

import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Shape3D;

/**
 *
 * @author jestern
 */
public class BoxShape3D extends Shape3D {
    
    public BoxShape3D(Box b) {
        Shape3D lado = b.getShape(Box.FRONT);
        this.addGeometry(lado.getGeometry());
        
        lado = b.getShape(Box.TOP);
        this.addGeometry(lado.getGeometry());
        
        lado = b.getShape(Box.BOTTOM);
        this.addGeometry(lado.getGeometry());
        
        lado = b.getShape(Box.RIGHT);
        this.addGeometry(lado.getGeometry());
        
        lado = b.getShape(Box.LEFT);
        this.addGeometry(lado.getGeometry());
        
        lado = b.getShape(Box.BACK);
        this.addGeometry(lado.getGeometry());
        
        this.setAppearance(b.getAppearance());
    }
    
}

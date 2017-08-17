/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;

/**
 *
 * @author eila
 */
public class Limites extends BoundingSphere{
    private static Limites limite = new Limites();
    
    private Limites(){
        super(new Point3d (0.0f, 0.0f, 0.0f), 500.0f);
    }
    
    public static BoundingSphere getInstance(){
        return limite;
    }
    
    
}

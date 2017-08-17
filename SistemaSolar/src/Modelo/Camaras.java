/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author jestern
 */
public class Camaras {
    
    
    private TransformGroup viewTransformMobile;
    private TransformGroup viewTransformLuna;
    private TransformGroup viewTransformNave;
    private TransformGroup viewTransformPlanta;
    private ViewPlatform[] camaras = new ViewPlatform[4];
    private View view;
    
    public Camaras(Canvas3D vistas, Canvas3D planta) {
        this.createViewPlatform();
        viewTransformMobile = vistaCamara(new Point3d(30,30,30), new Point3d(0,0,0), new Vector3d(0,1,0));
        viewTransformLuna = vistaCamara(new Point3d(-0.5f, 0, 0), new Point3d(-1,0,0), new Vector3d(0,1,0));
        viewTransformNave = vistaCamara(new Point3d(0,0,2), new Point3d(0,0,3), new Vector3d(0,1,0));
        viewTransformPlanta = vistaCamara(new Point3d(0,80,0), new Point3d(0,0,0), new Vector3d(1,0,0));
        
        createZoomRaton(viewTransformMobile);
        createRotacionRaton(viewTransformMobile);
        createTraslacionRaton(viewTransformMobile);       
               
        
        
        viewTransformMobile.addChild(camaras[0]);
        viewTransformLuna.addChild(camaras[1]);
        viewTransformNave.addChild(camaras[2]);
        viewTransformPlanta.addChild(camaras[3]);
        
        view = new View();
        view.setPhysicalBody(new PhysicalBody());
        view.setPhysicalEnvironment(new PhysicalEnvironment());
        view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
        view.setFieldOfView(Math.toRadians(90));
        view.setBackClipDistance(1000.0f);

        view.addCanvas3D(vistas);
        view.attachViewPlatform(camaras[0]);
        
        View viewPlanta = new View();
        viewPlanta.setPhysicalBody(new PhysicalBody());
        viewPlanta.setPhysicalEnvironment(new PhysicalEnvironment());
        viewPlanta.setProjectionPolicy(View.PARALLEL_PROJECTION); 
        viewPlanta.setBackClipDistance(100.0f);
        viewPlanta.setFrontClipDistance(0.01f);
        viewPlanta.setScreenScalePolicy(View.SCALE_EXPLICIT);
        viewPlanta.setScreenScale(0.0005f);

        viewPlanta.addCanvas3D(planta);
        viewPlanta.attachViewPlatform(camaras[3]);
    }
    
    private TransformGroup vistaCamara(Point3d pos, Point3d dir, Vector3d vUp){
        // La transformación de vista, dónde se está, a dónde se mira, Vup
        TransformGroup viewTransformGroup = new TransformGroup();
        
        viewTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        viewTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        Transform3D viewTransform3D = new Transform3D();
        viewTransform3D.lookAt (pos, dir, vUp);
        viewTransform3D.invert();
        viewTransformGroup.setTransform (viewTransform3D);

        return viewTransformGroup;
    }
    
    private void createRotacionRaton(TransformGroup t) {
        MouseRotate rotacion = new MouseRotate(t);
        rotacion.setFactor(0.005d, 0.005d);
        rotacion.getCapability(MouseBehavior.INVERT_INPUT);
        rotacion.setSchedulingBounds (Limites.getInstance()) ;
        t.addChild (rotacion);
    }
    
    private void createTraslacionRaton(TransformGroup t) {
        MouseTranslate traslacion = new MouseTranslate(t) ;
        traslacion.setFactor(0.25d, 0.25d);
        traslacion.setSchedulingBounds (Limites.getInstance()) ;
        t.addChild (traslacion);
    }
    
    private void createZoomRaton(TransformGroup t) {
        MouseWheelZoom zoom = new MouseWheelZoom(t) ;
        zoom.setFactor(2.0d);
        zoom.getCapability(MouseBehavior.INVERT_INPUT);
        zoom.setSchedulingBounds (Limites.getInstance()) ;
        t.addChild (zoom);
    }
    
    private void createViewPlatform() {
        for(int i = 0; i < camaras.length; i++){
            camaras[i] =  new ViewPlatform();
            camaras[i].setActivationRadius (1000f);
        }
    }
    
    public TransformGroup getTransformMobile() {
        return viewTransformMobile;
    }
    
    public TransformGroup getTransformLuna() {
        return viewTransformLuna;
    }
    
    public TransformGroup getTransformNave() {
        return viewTransformNave;
    }
    
    public TransformGroup getTransformPlanta() {
        return viewTransformPlanta;
    }
    
    public void setViewPlatformVistas(int i) {
         if(i < camaras.length - 1)
            view.attachViewPlatform(camaras[i]);
    }
    
}

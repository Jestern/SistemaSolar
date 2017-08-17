/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import PrimitiveShape3D.SphereShape3D;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Appearance;
import javax.media.j3d.Node;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author jestern
 */

public abstract class Astro extends BranchGroup {
    
    protected static final int DIVISIONES = 40;
    protected static final int VELOCIDAD = 3000;
    private final String nombre;
    private final float velocidadRotacion;
    private final float velocidadTraslacion;
    private final float radioOrbita;
    private final float radio;
    private Appearance apariencias;
    private Alpha timer;
    
    public Astro(String n, float vR, float vT, float r, float rO, Appearance ap){
        
        this.nombre = n;
        this.velocidadRotacion = vR;
        this.velocidadTraslacion = vT;
        this.radioOrbita = rO;
        this.radio = r;
        this.apariencias = ap;
        
        
        this.setPickable(true);
        this.setCapability(Node.ENABLE_PICK_REPORTING);
       
    }
    
    protected void createEsfera() {
        int flagsSphere = Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS;
        
        Shape3D sphere = new SphereShape3D (new Sphere (radio, flagsSphere, DIVISIONES , apariencias));   
        
        TransformGroup rotacionAstro = createRotation();
        
        
        
        rotacionAstro.addChild (sphere);
    
        this.addChild (rotacionAstro);
    }
    
    protected TransformGroup createRotation () {
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
      
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        timer = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, (long) (velocidadRotacion*VELOCIDAD), 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        RotationInterpolator rotator = new RotationInterpolator (timer, transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(Limites.getInstance());
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotator);
        return transform;
    }
    
    protected TransformGroup createRotationOrbita () {
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        Alpha timerTraslacion = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, (long) (velocidadTraslacion * VELOCIDAD), 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        RotationInterpolator rotator = new RotationInterpolator (timerTraslacion, transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(Limites.getInstance());
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotator);
        return transform;
    }
    
    protected TransformGroup createTranslacionOrbita () {
              
       Transform3D transSphere = new Transform3D ();
       transSphere.set (new Vector3f (radioOrbita, 0.0f, 0.0f));
       TransformGroup transform = new TransformGroup (transSphere);
       
       return transform;
    }
      
    public void setMoveOnOff() {
       if(timer.isPaused())
           timer.resume();
       else
           timer.pause();
    }
    
    public float getVelocidadRotacion() {
        return this.velocidadRotacion;
    }
    
    public float getVelocidadTraslacion() {
        return this.velocidadTraslacion;
    }
    
    public float getRadioOrbita() {
        return this.radioOrbita;
    }
    
}

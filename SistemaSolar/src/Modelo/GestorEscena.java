package Modelo;

import Comportamiento.PickRotacion;
import PrimitiveShape3D.BoxShape3D;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;


public class GestorEscena {
  
  // Atributos de relación
  private Fondo fondo;
  private Nave nave;
  private Estrella sol;
  private PickRotacion pick;
  private Canvas3D canvasPlanta;
  private Canvas3D canvasVistas;
  private Camaras camaras;
  private VirtualUniverse universo;
  private Locale locale;
 
  // ******* Constructor
  
  public GestorEscena () {
    this.canvasPlanta = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
    this.canvasPlanta.setSize(800, 600);
    this.canvasVistas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
    this.canvasVistas.setSize(800, 600);
    this.camaras = new Camaras(canvasVistas, canvasPlanta);
    
    
    // Todo cuelga de un nodo raiz
    BranchGroup root = new BranchGroup();
  
    AmbientLight ambiental = new AmbientLight ();
    ambiental.setInfluencingBounds (Limites.getInstance());
    ambiental.setEnable(true);
    
    root.addChild(ambiental);    
      
   
    fondo = new Fondo();
    fondo.setPickable(false);
    root.addChild(fondo);
    
    // Se crea el universo. La parte de la vista
    createUniverse ();

    sol = crearSol();
    root.addChild(sol);
    
    crearPlanetas(sol);
    
    TransformGroup ruta = trazarRutaNave();
    ruta.addChild(camaras.getTransformNave());
    
    nave = new Nave("models/naveEspacial/naveEspacial.obj", ruta);
    root.addChild(nave);
    
    pick = new PickRotacion(canvasVistas, canvasPlanta);
    pick.setSchedulingBounds(Limites.getInstance());
    
    pick.setBranchGroup(sol);
    sol.addChild(pick);
    
    // Se optimiza la escena y se cuelga del universo
    root.compile();
    locale.addBranchGraph(root);
  }
  
  // ******* Private
  
  private Locale createUniverse () {
      
    universo = new VirtualUniverse();
    locale = new Locale (universo);
    
    BranchGroup vistas = new BranchGroup();
    
    vistas.addChild(camaras.getTransformMobile());
    vistas.addChild(camaras.getTransformPlanta());
    locale.addBranchGraph(vistas);

    // Se construye y devuelve el Universo con los parametros definidos
    return locale;
  }
  
  private Estrella crearSol(){
      // velociidad de rotacion = 27d 6h 36m = 2,333e+9 ms diametro = 1.4*10^9m
      return (new Estrella("Sol", 27.6f, 17, Apariencias.Sol.getAppearance()));
  }
  
  private void crearPlanetas(Estrella e){
     
      Planeta mercurio = new Planeta("Mercurio", 11.0f, 10.6f, 1.2f, 29.0f, Apariencias.Mercurio.getAppearance());
      Planeta venus = new Planeta("Venus", 13.0f, 20.3f, 3.0f, 54.1f, Apariencias.Venus.getAppearance());

    
      Planeta tierra = new Planeta("Tierra", 0.99f, 30.25f, 3.20f, 73.3f, Apariencias.Tierra.getAppearance());
      Satelite luna = new Satelite("Luna", 10.0f, 10.0f, 0.5f, 6.0f, Apariencias.Luna.getAppearance());  
      
      luna.addChild(camaras.getTransformLuna());
      
      tierra.addSatelite(luna);
      
      Planeta marte = new Planeta("Marte", 1.025f, 40.2f, 2.5f, 100.0f, Apariencias.Marte.getAppearance());
      Satelite fobos = new Satelite("Fobos", 0.39f, 0.39f, 0.2f, 4.5f, Apariencias.Fobos.getAppearance());
      Satelite deimos = new Satelite("Deimos", 1.262f, 1.262f, 0.1f, 7.0f, Apariencias.Deimos.getAppearance());
      marte.addSatelite(fobos);
      marte.addSatelite(deimos);
      
      Planeta jupiter = new Planeta("Jupiter", 0.4125f, 50.9f, 10.0f, 175.0f, Apariencias.Jupiter.getAppearance());
      Satelite io = new Satelite("Io", 1.769f, 1.769f, 0.7f, 14.0f, Apariencias.Io.getAppearance());
      Satelite europa = new Satelite("Europa", 3.551f, 3.551f, 0.5f, 16.0f, Apariencias.Europa.getAppearance());
      Satelite calisto = new Satelite("Calisto", 16.689f, 16.689f, 1.2f, 30.0f, Apariencias.Calisto.getAppearance());
      jupiter.addSatelite(io);
      jupiter.addSatelite(europa);
      jupiter.addSatelite(calisto);
      
      Planeta saturno = new Planeta("Saturno", 0.425f, 60.9f, 8.0f, 250.0f, Apariencias.Saturno.getAppearance());
      Anillo a = new Anillo("Anillo A", 0.425f, 0.0f, 14.3f, 16.7f, Apariencias.AnilloA.getAppearance());
      Anillo b = new Anillo("Anillo B", 0.425f, 0.0f, 10.4f, 14.2f, Apariencias.AnilloB.getAppearance());
      Anillo c = new Anillo("Anillo C", 0.425f, 0.0f, 9.0f, 10.3f, Apariencias.AnilloC.getAppearance());
      saturno.addAnillo(a);
      saturno.addAnillo(b);
      saturno.addAnillo(c);
      
      Planeta urano = new Planeta("Urano", 0.45f, 70.0f, 4.0f, 300.0f, Apariencias.Urano.getAppearance());
      Satelite titania = new Satelite("Titania", 8.7f, 8.7f, 0.4f, 8.0f, Apariencias.Titania.getAppearance());
      Satelite ariel = new Satelite("Ariel", 2.52f, 2.52f, 0.3f, 6.0f, Apariencias.Ariel.getAppearance());
      Satelite miranda = new Satelite("Miranda", 1.41f, 1.41f, 0.25f, 5.25f, Apariencias.Miranda.getAppearance());
      urano.addSatelite(titania);
      urano.addSatelite(ariel);
      urano.addSatelite(miranda);
      
      Planeta neptuno = new Planeta("Neptuno", 0.6583f, 100.0f, 3.5f, 350.0f, Apariencias.Neptuno.getAppearance());
      Satelite triton = new Satelite("Tritón", -5.87f, -5.87f, 0.55f, 7.5f, Apariencias.Triton.getAppearance());
      neptuno.addSatelite(triton);
      
      
      e.addPlaneta(mercurio);
      e.addPlaneta(venus);
      e.addPlaneta(tierra);
      e.addPlaneta(marte);
      e.addPlaneta(jupiter);
      e.addPlaneta(saturno);
      e.addPlaneta(urano);
      e.addPlaneta(neptuno);
  }
  
  // ******* Public
  
  public void closeApp (int code) {
    System.exit (code);
  }
  
  public Canvas3D getCanvasVistas(){
      return canvasVistas;
  }
  
  public Canvas3D getCanvasPlanta(){
      return canvasPlanta;
  }
  
  public void setCamaraMovil() {
      camaras.setViewPlatformVistas(0);
  }
  
  public void setCamaraLuna() {
      camaras.setViewPlatformVistas(1);
  }
  
  public void setCamaraNave() {
      camaras.setViewPlatformVistas(2);
  }
  
   private TransformGroup trazarRutaNave(){
        
        Transform3D t = new Transform3D ();
             
        TransformGroup tg = new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        float [] alphas = {0.0f, 0.15f,0.2f, 0.33f,0.5f, 0.6f, 0.73f, 0.96f, 1.0f};
        Point3f [] posiciones = {
            new Point3f (40.0f, 0.0f, 0.0f), new Point3f (60.0f, 0.0f, 60.0f),
            new Point3f (85.0f, 0.0f, 30.0f), new Point3f (125.0f, 0.0f, 20.0f),
            new Point3f (125.0f, 0.0f, -20.0f), new Point3f (85.0f, 0.0f, -40.0f),
            new Point3f (60.0f, 0.0f, -20.0f), new Point3f (40.0f, 0.0f, -10.0f),
            new Point3f (40.0f, 0.0f, 0.0f)
        };
        Quat4f [] rotaciones = new Quat4f[9];
        for ( int i = 0; i < 9; i++) rotaciones[i] = new Quat4f();
        rotaciones[0].set(new AxisAngle4f (0.0f, 1.0f, 0.0f, (float) Math.toRadians(0)));
        rotaciones[1].set(new AxisAngle4f (0.0f, 1.0f, 0.0f, (float) Math.toRadians(90)));
        rotaciones[2].set(new AxisAngle4f (0.0f, 1.0f, 0.0f, (float) Math.toRadians(80)));
        rotaciones[3].set(new AxisAngle4f (0.0f, 1.0f, 0.0f, (float) Math.toRadians(160)));
        rotaciones[4].set(new AxisAngle4f (0.0f, 1.0f, 0.0f, (float) Math.toRadians(210)));
        rotaciones[5].set(new AxisAngle4f (0.0f, 1.0f, 0.0f, (float) Math.toRadians(270)));
        rotaciones[6].set(new AxisAngle4f (0.0f, 1.0f, 0.0f, (float) Math.toRadians(300)));
        rotaciones[7].set(new AxisAngle4f (0.0f, 1.0f, 0.0f, (float) Math.toRadians(350)));
        rotaciones[8].set(new AxisAngle4f (0.0f, 1.0f, 0.0f, (float) Math.toRadians(0)));
        
        
        Alpha alpha = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 10000 , 0, 0, 0, 0, 0);
        
        RotPosPathInterpolator ruta = new RotPosPathInterpolator(alpha, tg, t, alphas, rotaciones, posiciones);
        ruta.setSchedulingBounds(Limites.getInstance());
        ruta.setEnable(true);
        
        tg.addChild(ruta);
        return tg;
    }

}

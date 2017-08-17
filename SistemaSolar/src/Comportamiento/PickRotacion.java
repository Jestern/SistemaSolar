/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamiento;

import Modelo.Astro;
import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.WakeupOnAWTEvent;

/**
 *
 * @author jestern
 */
public class PickRotacion extends Behavior {
    
    private WakeupOnAWTEvent condition;
    private PickCanvas pickCanvasVistas;
    private Canvas3D canvasVistas;
    private PickCanvas pickCanvasPlanta;
    private Canvas3D canvasPlanta;

    
    public PickRotacion(Canvas3D canvasVistas, Canvas3D canvasPlanta) {
        
        this.canvasVistas = canvasVistas;
        this.canvasPlanta = canvasPlanta;
        condition = new WakeupOnAWTEvent (MouseEvent.MOUSE_CLICKED);
    }
    
    public void setBranchGroup(BranchGroup bg) {
        pickCanvasVistas = new PickCanvas(canvasVistas, bg);
        pickCanvasVistas.setTolerance(0.0f) ;
        pickCanvasVistas.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvasVistas.setFlags(PickInfo.SCENEGRAPHPATH);
        
        pickCanvasPlanta = new PickCanvas(canvasPlanta, bg);
        pickCanvasPlanta.setTolerance(0.0f) ;
        pickCanvasPlanta.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvasPlanta.setFlags(PickInfo.SCENEGRAPHPATH);
        
        
        setEnable(true);
    }

    @Override
    public void initialize() {
        setEnable(true);
        wakeupOn(condition);
    }

    @Override
    public void processStimulus(Enumeration cond) {
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent mouse = (MouseEvent) e[0];
        
        PickInfo pi = null;
        Astro as;
        
        if(mouse.getSource() == canvasVistas) {
            
            pickCanvasVistas.setShapeLocation(mouse);
            pi = pickCanvasVistas.pickClosest();
            
        } else if(mouse.getSource() == canvasPlanta) {
            pickCanvasPlanta.setShapeLocation(mouse);
            pi = pickCanvasPlanta.pickClosest();
        }
        
        if(pi != null) {
            SceneGraphPath astro = pi.getSceneGraphPath();
            as = (Astro) astro.getNode(astro.nodeCount() -1);
            (as).setMoveOnOff();
        }
        
        wakeupOn(condition) ;
        
    }
    
}

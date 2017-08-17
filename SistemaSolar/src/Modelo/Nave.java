/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author jestern
 */
public class Nave extends BranchGroup {
    public Nave (String file, TransformGroup ruta){
        Scene modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {
          modelo = (Scene) archivo.load (file);
        } catch (FileNotFoundException e) {
          System.err.println (e);
          System.exit(1);
        } catch (ParsingErrorException e) {
          System.err.println (e);
          System.exit(1);
        } catch (IncorrectFormatException e) {
          System.err.println (e);
          System.exit(1);
        }
        ruta.addChild ( modelo.getSceneGroup() );
        this.addChild(ruta);
    }
}

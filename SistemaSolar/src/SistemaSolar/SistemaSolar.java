/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaSolar;

import GUI.VentanaControl;
import Modelo.GestorEscena;

/**
 *
 * @author eila
 */
public class SistemaSolar {
    
      public static void main(String[] args) {

    
    // Se crea el Universo con dicho Canvas3D
    GestorEscena gestor = new GestorEscena ();
    
    VentanaControl ventana = new VentanaControl(gestor);
    ventana.showWindow();
   
  }
  
    
}

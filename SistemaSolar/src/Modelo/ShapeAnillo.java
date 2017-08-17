/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;

/**
 *
 * @author jestern
 */
public class ShapeAnillo extends Shape3D {
    
    public ShapeAnillo(float radioExterior, float radioInterior, int res, Appearance app){
        super();
        this.setGeometry(createGeometry(radioExterior, radioInterior, res));
        this.setAppearance(app);
    }
    
    private IndexedTriangleArray createGeometry(float radioExterior, float radioInterior, int res) {
        int nPoints = (res + 1) * 2;
        Point3f[] points = new Point3f[nPoints];
        int[] triangulos = new int[res * 6];
        
        float[] normals = { 0.0f, 1.0f, 0.0f,
                            0.0f, -1.0f, 0.0f };
        
        
        // añadir los primeros puntos
        points[0] =  new Point3f(radioInterior, 0.0f, 0.0f);
        points[1] =  new Point3f(radioExterior, 0.0f, 0.0f);
        
        // angulo a mover los puntos.
        float alpha = (float) Math.toRadians(360.0f / res );
        
        // generar los puntos
        Transform3D rotate = new Transform3D();
        rotate.rotY(alpha);
        
        int t = 0;
        // Sweep de los puntos y generación de triangulos.
        for(int i = 2; i < nPoints - 2; i += 2) {
            Point3f pInterior = new Point3f(points[i - 2]);
            Point3f pExterior = new Point3f(points[i - 1]);
            
            rotate.transform(pInterior);
            rotate.transform(pExterior);
            
            points[i] = pInterior;
            points[i + 1] = pExterior;
            
            // triangulos
            triangulos[t] = i - 2;
            triangulos[t + 1] = i - 1;
            triangulos[t + 2] = i + 1;
            
            triangulos[t + 3] = i + 1;
            triangulos[t + 4] = i;
            triangulos[t + 5] = i - 2;
            
            t += 6;
        }
        
        points[nPoints - 2] = new Point3f(points[0]);
        points[nPoints - 1] = new Point3f(points[1]);
        
        triangulos[t] = nPoints - 4;
        triangulos[t + 1] = nPoints - 3;
        triangulos[t + 2] = nPoints - 1;

        triangulos[t + 3] = nPoints - 1;
        triangulos[t + 4] = nPoints - 2;
        triangulos[t + 5] = nPoints - 4;
        
        triangulos = generateInverseTriangles(triangulos, points.length);
        points = duplicatePoints(points);
        
        int[] inNormals = new int[triangulos.length];
        
        for(int i = 0; i < inNormals.length / 2; ++i) {
            inNormals[i] = 0; 
        }
        
        for(int i = inNormals.length / 2; i < inNormals.length; ++i) {
            inNormals[i] = 1; 
        }
        
        IndexedTriangleArray geo = new IndexedTriangleArray(points.length, GeometryArray.COORDINATES | GeometryArray.NORMALS | GeometryArray.TEXTURE_COORDINATE_2, triangulos.length);
        
        geo.setCoordinates(0, points);
        geo.setCoordinateIndices(0, triangulos);
        
        geo.setNormal(0, normals);
        geo.setNormalIndices(0, inNormals);
        
        TexCoord2f[] texCoords = generarCoordText(res);
        geo.setTextureCoordinates(0, 0, texCoords);
        
        int[] indTexCoords = generarIndCoordText(triangulos.length);
        geo.setTextureCoordinateIndices(0, 0, indTexCoords);
        
        
        
        return geo;
    }
    
    private int[] generateInverseTriangles(int [] array, int vertices) {
        int [] newArray = new int[array.length * 2];
        
        System.arraycopy(array, 0, newArray, 0, array.length);
   
        for(int i = array.length, j = 0; i < newArray.length ; i += 6, j += 6) {
            newArray[i] = array[j + 2] + vertices;
            newArray[i + 1] = array[j + 1] + vertices;
            newArray[i + 2] = array[j] + vertices;

            newArray[i + 3] = array[j + 5] + vertices;
            newArray[i + 4] = array[j + 4] + vertices;
            newArray[i + 5] = array[j + 3] + vertices;
        }
        
        return newArray;
    }
    
    private Point3f[] duplicatePoints(Point3f[] array) {
        Point3f[] newArray = new Point3f[array.length + array.length];
        
        System.arraycopy(array, 0, newArray, 0, array.length);
                
        for(int i = array.length, j = 0; i < newArray.length ; i++, j++) {
            newArray[i] = new Point3f(array[j]);            
        }
        
        return newArray;
    }
    
    private TexCoord2f[] generarCoordText(int res){
        
        TexCoord2f[] coords = new TexCoord2f[(res + 1) * 2];
        float div = 1.0f / res;
        float coord = 0.0f;
        
        for (int i = 0; i < (res + 1) * 2; i += 2){
            coords[i] = new TexCoord2f(coord, 0.0f);
            coords[i + 1] = new TexCoord2f(coord, 1.0f);         
            
            coord += div;                   
        }
        return coords;
    }
    
    private int[] generarIndCoordText(int triangulos){
        int mitad = triangulos / 2;
        int[] indices = new int[triangulos];
        
        for (int i = 0, j = 0; i < mitad; i += 6, j += 2){
            indices[i] = j;
            indices[i + 1] = j + 1;
            indices[i + 2] = j + 3;
            
            indices[i + 3] = j + 3;
            indices[i + 4] = j + 2;
            indices[i + 5] = j;
        }
        
        for (int i = mitad, j = 0; i < triangulos; i += 6, j += 2){
            indices[i] = j + 3;
            indices[i + 1] = j + 1;
            indices[i + 2] = j;
            
            indices[i + 3] = j;
            indices[i + 4] = j + 2;
            indices[i + 5] = j + 3;
        }
        
        return indices;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_estrella;

/**
 *
 * @author miguel
 */
public class Casilla {
    
    private int ID;
    private int H; //heuristica
    private int G=0; // costo de movimientos 
    private int F=0; // G+H  costo 
    private int cordenadaX;//mi ubicacio
    private int cordenadaY;//mi ubicacio
  
    private String caracter;
  
    public void setcordenadaX(int x){
        cordenadaX=x;
    }
    public int getcordenadaX(){
        return cordenadaX;
    }  
    public void setcordenadaY(int y){
        cordenadaY=y;
    }
    public int getcordenadaY(){
        return cordenadaY;
    }     
    public void setH(int h){
        H=h;
    }
    public int getH(){
        return H;
    }
    public void setG(int g){
        G=g;
    }
    public int getG(){
        return G;
    }
    public void setF(int f){
        F=f;
    }
    public int getF(){
        return F;
    }
    public void setID(int id){
        ID = id;
    }
    public int getID(){
        return ID;
    }
    public void setcaracter(String Caracter){
        caracter = Caracter;
    }
    public String getcaracter(){
        return "["+caracter+"]";
    }    
}

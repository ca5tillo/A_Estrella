/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_estrella;

/**
 *
 * @author miguel
 */
public class Listas_de_Estados {
    static private Casilla nodosAbiertos[]=new Casilla[50];
    static private Casilla nodosCerrados[]=new Casilla[100];
    static private Casilla vecinos_de_la_meta[]=new Casilla[10];
    static private int finAbiertos=0,finCerrados=0,finvecinos_de_la_meta=0;
    
    public void inicializar(){
        finAbiertos=0;
        finCerrados=0;
    }
    public void insertarL_Abiertos(Casilla nodo){
        nodosAbiertos[finAbiertos]=nodo;
        finAbiertos+=1;
    }
    public void insertarL_Cerrados(Casilla nodo){
        nodosCerrados[finCerrados]=nodo;
        finCerrados+=1;
    }  
     
    public Casilla extraerL_Abiertos (int id){  //primero en entrar es el primero en sale 
        Casilla salida = new Casilla();;
//        System.out.println("finabietos"+finAbiertos);
        for(int i =0; i<finAbiertos; i++){
            if(id==nodosAbiertos[i].getID()){
                salida = nodosAbiertos[i];
//                System.out.println("i ="+i+" ID = "+nodosAbiertos[i].getID());
                for (int j=i;j<finAbiertos-1;j++){
//                    System.out.println("j ="+j);
                nodosAbiertos[j]=nodosAbiertos[j+1];
                }    
            }           
        }
        finAbiertos=finAbiertos-1;
        return salida;
    }
    public Casilla mostrarL_Cerrados(int indice){
        return nodosCerrados[indice];
    }
    public Casilla mostrarL_vecinos_de_la_meta(int indice){
        return vecinos_de_la_meta[indice];
    }    
    public Casilla mostrasAbiertos(int indice){
        return nodosAbiertos[indice];
    }
    public int tamañoL_Abiertos(){
        return finAbiertos;
    }
    public int tamañoL_Cerrados(){
        return finCerrados;
    }
    public int tamañoL_finvecinos_de_la_meta(){
        return finvecinos_de_la_meta;
    }
    public void añadir_vecinos_de_la_meta(Casilla nodo){
        vecinos_de_la_meta[finvecinos_de_la_meta]=nodo;
        finvecinos_de_la_meta+=1;
    }
    public void impCerrados(){
        int i = finCerrados;
        for(int j =0 ; j<i;j++)
//             System.out.println(nodosCerrados[j].getID());
        System.out.println("nodoCerrado ID= "+nodosCerrados[j].getID()+
                " H= "+nodosCerrados[j].getH()+
                " caracter= "+nodosCerrados[j].getcaracter());
        System.out.println("_______");
    }  
    public void impvecinosDmeta(){
        
        for(int j =0 ; j<finvecinos_de_la_meta;j++)
//             System.out.println(nodosCerrados[j].getID());
        System.out.println("vecinos_de_la_meta ID= "+vecinos_de_la_meta[j].getID()+
                "H= "+vecinos_de_la_meta[j].getH()+
                "caracter= "+vecinos_de_la_meta[j].getcaracter());
        System.out.println("_______");
    }
    public void impAbiertos(){
        
        for(int j =0 ; j<finAbiertos;j++)
//             System.out.println(nodosCerrados[j].getID());
        System.out.println("nodosAbiertos ID= "+nodosAbiertos[j].getID()+
                " H= "+nodosAbiertos[j].getH()+
                " caracter= "+nodosAbiertos[j].getcaracter()+
                " G = "+nodosAbiertos[j].getG()+
                " F = "+nodosAbiertos[j].getF()
                );
        System.out.println("_______");
    }    
}

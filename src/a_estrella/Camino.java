/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_estrella;

/**
 *
 * @author miguel
 */
public class Camino {
    static private Casilla vecinos[]=new Casilla[10];
    static private Casilla vecinos_de_punto_partida[]=new Casilla[10];
    static private Casilla camino[]=new Casilla[10];
    static private Casilla yavisitado[]=new Casilla[10];
    static private int finVecinos=0, fincamino=0, finyavisitados=0,finvecinos_de_punto_partida=0;
    
    public void inicializrvecinos(){
         finVecinos=0;
    }
    public void setvecinos_de_punto_partida(Casilla nodo){
        vecinos_de_punto_partida[finvecinos_de_punto_partida]=nodo;
        finvecinos_de_punto_partida+=1;
    }
    public Casilla getvecinos_de_punto_partida(int indice){
        return vecinos_de_punto_partida[indice];
    }
    public int tamvecinos_de_punto_partida(){
        return finvecinos_de_punto_partida;
    }
    public void setvecinos(Casilla nodo){
        vecinos[finVecinos]=nodo;
        finVecinos+=1;
    }
    public Casilla getvecinos(int indice){
        return vecinos[indice];
       
    }
    public void setcamino(Casilla nodo){
        camino[fincamino]=nodo;
        fincamino+=1;
    }
    public Casilla getcamino(int indice){
        return camino[indice];
       
    }
    public void setyavisitado(Casilla nodo){
        yavisitado[finyavisitados]=nodo;
        finyavisitados+=1;
    }
    public Casilla getyavisitado(int indice){
        return yavisitado[indice];
       
    }
    public int getTamcamino(){
        return fincamino;
    }
    public int getTamyavisitado(){
        return finyavisitados;
    }
    public int gettamVecinos(){
        return finVecinos;
    }    
    public void impvecinos(){
        
        for(int j =0 ; j<finVecinos;j++)
//             System.out.println(nodosCerrados[j].getID());
        System.out.println("impvecinos ID= "+vecinos[j].getID()+
                " H= "+vecinos[j].getH()+
                " caracter= "+vecinos[j].getcaracter()+
                " G = "+vecinos[j].getG()+
                " F = "+vecinos[j].getF()
                );
        System.out.println("_______");
    }     
    public void impyavisitado(){
        
        for(int j =0 ; j<finyavisitados;j++)
//             System.out.println(nodosCerrados[j].getID());
        System.out.println("impyavisitado ID= "+yavisitado[j].getID()+
                " H= "+yavisitado[j].getH()+
                " caracter= "+yavisitado[j].getcaracter()+
                " G = "+yavisitado[j].getG()+
                " F = "+yavisitado[j].getF()
                );
        System.out.println("_______");
        } 
    public void impcamino(){

    for(int j =0 ; j<fincamino;j++)
//             System.out.println(nodosCerrados[j].getID());
    System.out.println("impcamino ID= "+camino[j].getID()+
            " H= "+camino[j].getH()+
            " caracter= "+camino[j].getcaracter()+
            " G = "+camino[j].getG()+
            " F = "+camino[j].getF()
            );
    System.out.println("_______");
} 
}

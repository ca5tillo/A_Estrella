/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_estrella;

/**
 *
 * @author miguel
 */
public class Matriz {    
    private static int imprecorrido=0;//= 0 para no imprimir = 1para imprimir 
    private static int filas=6;
    private static int columnas=6;
    private static int nofin=0;
    private static int nofin2=0;
    private static int count=1;
    private Casilla casilla [][]= new Casilla [filas][columnas];  //[filas] [columnas]
    Listas_de_Estados listas=new Listas_de_Estados();
    Camino camino =new Camino();
    
    public void Default(){
        int xmeta=4;
        int ymeta=4;
        int xinicio=1;
        int yinicio=1;
        colocarmeta(xmeta,ymeta);
        colocarObstaculos(3,1);// le pasamos primero la X lurgo Y (X,Y)
        colocarObstaculos(1,3);
        colocarObstaculos(3,3);
        colocarObstaculos(3,4);
        colocarinicio(xinicio,yinicio);//solo coloca la letra I en las coordenadas de inicio

        insertar_vecinos_de_la_meta(casilla[ymeta][xmeta]);
//        listas.impvecinosDmeta();
        
        vecinos_apartir_inicio(casilla[yinicio][xinicio]);//calcula GyF
        
        insertar_vecinos_de_partida(casilla[yinicio][xinicio]);
        marcarcamino(casilla[ymeta][xmeta]);
        pintarcamino ();
//        camino.impcamino(); // imprime los nodos por los cuales tiene q pasar para llegar a su objetivo
        printmatriz();
//        print();   

    }//fin default
    
   void inicializar(){
        int id =1;
        for (int y = 0 ; y<filas;y++){
            for (int x =0; x<columnas ;x++){
                insertar(x,y,"_",id);
                id+=1;
            }
        }        
    }//Fin inicializar . con la ayuda de insertar inizializa la matriz solo se utilizan estas dos funciones al iniciar
   private void insertar(int X, int Y,String Car,int id){// SOLO PARA INICIALIZAR
                  Casilla insertCasilla = new Casilla();   
                  insertCasilla.setcaracter(Car);
                  insertCasilla.setID(id);
                  insertCasilla.setcordenadaX(X);
                  insertCasilla.setcordenadaY(Y);
                  casilla[Y][X]= insertCasilla;
    }   
   public void colocarmeta(int xmeta,int ymeta){
        inicializar();
        insertH(xmeta,ymeta);
        casilla[ymeta][xmeta].setcaracter("@");
    }
   public void colocarinicio (int x,int y){
       casilla[y][x].setcaracter("I");
   }
   public void insertH(int xmeta,int ymeta){
       for (int y = 0 ; y<filas;y++){
            for (int x =0; x<columnas ;x++){
                if(casilla[y][x].getID()!=casilla[ymeta][xmeta].getID()){
                    casilla[y][x].setH(C_Heuristica(xmeta,ymeta,x,y));
                }
            }
        } 
   }    
   private int C_Heuristica(int xm,int ym,int xacalcular,int yacalcular){
       int xtem=xm-xacalcular;
       int ytem=ym-yacalcular;

       if (xtem<0){
           xtem = (xtem * -1);
       }
       if(ytem<0){
           ytem = (ytem * -1);
       }
       int H=xtem+ytem;
       return  H;
   }//    C_Heuristica
   public void colocarObstaculos(int x, int y){
       casilla [y][x].setH(-1);
       casilla [y][x].setcaracter("X");
       anadir_a_Lista_cerrados(casilla [y][x]);
   }
   public void anadir_a_Lista_cerrados(Casilla nodo){
        listas.insertarL_Cerrados(nodo);
    }// fin anadir_a_Lista_cerrados
   public void anadir_a_Lista_Abiertos(Casilla nodo){
       listas.insertarL_Abiertos(nodo);
   }
   public void insertar_vecinos_de_la_meta(Casilla meta){
       for (int i=1;i<=8;i++){
           if( hola_vecino(meta, i).getH()!=-1){
               listas.añadir_vecinos_de_la_meta(hola_vecino(meta, i));
           }
       }
//       listas.impvecinosDmeta();
   }
   public void insertar_vecinos_de_partida(Casilla meta){
       for (int i=1;i<=8;i++){
           if( hola_vecino(meta, i).getH()!=-1){
               camino.setvecinos_de_punto_partida(hola_vecino(meta, i));
//               if(imprecorrido==1)impCasilla(hola_vecino(meta, i));
           }
       }
//       listas.impvecinosDmeta();
   }   
   public Casilla hola_vecino(Casilla nodo,int idenV){
       /*idenV
        * 1  2  3 
        * 4  yo 5
        * 5  7  8
        */
       int x=nodo.getcordenadaX();//YO
       int y=nodo.getcordenadaY();//yo
       int idenv=idenV;
       Casilla temp =new Casilla();
       temp.setH(-1);
       
       if(idenv==5 && x+1<columnas)                      temp = casilla[y][x+1];
       if(idenv==4 && x-1>=0 )                           temp = casilla[y][x-1];
       if(idenv==7 && y+1 < filas)                       temp = casilla[y+1][x];
       if(idenv==2 && y-1>=0)                            temp = casilla[y-1][x];
       
       if(idenv==1 && y-1>=0 && x-1>=0)                  temp = casilla[y-1][x-1];
       if(idenv==3 &&y-1>=0 && x+1<columnas)             temp = casilla[y-1][x+1];
        
       if(idenv==8 && y+1 < filas && x+1<columnas)       temp = casilla[y+1][x+1];
       if(idenv==6 &&y+1 < filas && x-1>=0)              temp = casilla[y+1][x-1];
       return temp;
   }// fin hola_vecin   
   public void vecinos_apartir_inicio(Casilla nodopadre){
       //verificar que no esten en lista cerrada
       if(nofin==0){
       int tamCerrado=listas.tamañoL_Cerrados();

               
               Casilla temp =new Casilla();
               Casilla ini =new Casilla();
               int xi=nodopadre.getcordenadaX();
               int yi=nodopadre.getcordenadaY();
               int continuar=0;
               ini=casilla[yi][xi];

               for (int i=1;i<=8;i++){
                   if( hola_vecino(ini, i).getH()!=-1){
                       for(int t=0;t< listas.tamañoL_Cerrados();t++){
                           if(listas.mostrarL_Cerrados(t).getID()==hola_vecino(ini, i).getID()){
                               continuar=1;
                           }
                       }
                       if (continuar==0)calcularGyF(ini, hola_vecino(ini, i));
                   }
                   continuar=0;
               }  
               anadir_a_Lista_cerrados(nodopadre);
               if(imprecorrido==1){
                   System.out.print("vuelta "+count+" referencia " );
                   impCasilla(nodopadre);
               }
               if(imprecorrido==1)listas.impAbiertos();
               count+=1;
               selec_sigientepaso();
        }

   }
   public void calcularGyF(Casilla ini, Casilla vecino){
       int xv=vecino.getcordenadaX();
       int yv=vecino.getcordenadaY();
       int costo_d_movimiento=0;
       if (casilla[yv][xv].getG()==0 && casilla[yv][xv].getF()==0){
           if (    (ini.getcordenadaX()==vecino.getcordenadaX() && ini.getcordenadaY()!=vecino.getcordenadaY()) ||
                   (ini.getcordenadaX()!=vecino.getcordenadaX() && ini.getcordenadaY()==vecino.getcordenadaY())
           ){
               costo_d_movimiento=10;//horizontal y vertical vale 10
           }else costo_d_movimiento=14;//horizontal y vertical vale 10
           
           casilla[yv][xv].setG(ini.getG()+costo_d_movimiento);
           casilla[yv][xv].setF(casilla[yv][xv].getG()+casilla[yv][xv].getH());
           anadir_a_Lista_Abiertos(casilla[yv][xv]);
       }
       int tamVmeta=listas.tamañoL_finvecinos_de_la_meta();
       for (int r=0;r<tamVmeta;r++){
           if(listas.mostrarL_vecinos_de_la_meta(r).getID() == casilla[yv][xv].getID()){
               
               fin();
           }
       }
   }
   public void fin(){
       nofin=1;
//       System.out.println("Fin de la busqueda");
   }
   public void fin2(){
       nofin2=1;
   }
   public void selec_sigientepaso(){
       if(nofin==0){
       Casilla temp =new Casilla();
       Casilla tempextraccion =new Casilla();
       int i =listas.tamañoL_Abiertos();
       int Fminimo=99;
       for (int j =0;j<i;j++){  // seleccioneo el de menor valor en F
           if(Fminimo>listas.mostrasAbiertos(j).getF()){
               Fminimo=listas.mostrasAbiertos(j).getF();
               temp=listas.mostrasAbiertos(j);
           }
       }
       
       tempextraccion=listas.extraerL_Abiertos(temp.getID());
//       System.out.print("Extraje de abiertos = ");
//       impCasilla(tempextraccion);
       vecinos_apartir_inicio(tempextraccion);
 
       }
   }
   public void marcarcamino(Casilla nodo){
       if(nofin2==0){
      int tamVmeta=camino.tamvecinos_de_punto_partida();
      for (int r=0;r<tamVmeta;r++){
           if(camino.getvecinos_de_punto_partida(r).getID() == nodo.getID()){
               if(imprecorrido==1)System.out.println("FIN DE TRAZAR EL CAMINO");
//               camino.impcamino();
               fin2();
           }
       }
       insertar_vecinos_retorno(nodo);
       
       int Fminimo=99;
       int tamvecinos=camino.gettamVecinos();
       Casilla temp =new Casilla();
       
       
       
       
       for (int j =0;j<tamvecinos;j++){  // seleccioneo el de menor valor en F
           if(Fminimo>camino.getvecinos(j).getF() && camino.getvecinos(j).getF()!=0){
               Fminimo=camino.getvecinos(j).getF();
               temp=camino.getvecinos(j);
           }
       }
       
       camino.setyavisitado(nodo);
       if(nofin2==0)camino.setcamino(temp);
//       camino.impvecinos();
       camino.inicializrvecinos();
//       impCasilla(temp);
       marcarcamino(temp);
       
//       camino.impyavisitado();
//       camino.impcamino();
       }
   }// se usa solo para CREAR e insertar en 
   //la lista que contendra a los nodos por los que hay que pasar para 
   //llegar a la meta
   public void insertar_vecinos_retorno(Casilla meta){
       if(nofin2==0){
        int continuar=0;
       for (int i=1;i<=8;i++){
           if( hola_vecino(meta, i).getH()!=-1){
               for(int t=0;t<camino.getTamyavisitado();t++){
                   if(camino.getyavisitado(t).getID()==hola_vecino(meta, i).getID()){
                       continuar=1;
                   }
               }
               if(continuar==0)camino.setvecinos(hola_vecino(meta, i));
           }continuar=0;
       }
     }
   }//identifica a los vecinos    
   //del lugar en el que estamos pocisionados para elegir el de menor costo y obtener 
   //el recorrido mas optimo
   public void pintarcamino (){
       int tam=camino.getTamcamino();
       for (int i=0;i<tam;i++){
           casilla[camino.getcamino(i).getcordenadaY()][camino.getcamino(i).getcordenadaX()].setcaracter("o");
       }// ingresa los caracteres q formaran el camino en el mapa
   }
   public void print(){
        for (int y = 0 ; y<filas;y++){
            for (int x =0; x<columnas ;x++){
                System.out.println("coordenadas->"+"("+
                        casilla[y][x].getcordenadaX()+","+casilla[y][x].getcordenadaY()+")"+
                        " ID->"+casilla[y][x].getID()+
                        " caracter->"+casilla[y][x].getcaracter()+
                        " heuristica->"+casilla[y][x].getH()+
                        " G = "+casilla[y][x].getG()+
                        " F = "+casilla[y][x].getF()
                        );
            }
            System.out.println("\n");
        }  
    }   
   public void printmatriz(){
       System.out.print("  ");
       for(int i=0; i<columnas;i++)System.out.print(" "+i+" ");
       System.out.print("--> X");
       System.out.println();
       for(int i=0; i<filas;++i){  
           System.out.print(i+" ");
           for(int j=0; j<columnas;j++){
                  
                  String yyyy = casilla[i][j].getcaracter();
                  System.out.print(yyyy);                              
           }      
           System.out.println();
       } 
       System.out.println("|");
       System.out.println("Y");
    }
   public void impCasilla(Casilla nodo){
       System.out.println("Imprimir datos de casilla");
       System.out.println("coordenadas->"+"("+
                        nodo.getcordenadaX()+","+nodo.getcordenadaY()+")"+
                        " ID->"+nodo.getID()+
                        " caracter->"+nodo.getcaracter()+
                        " heuristica->"+nodo.getH()+
                        " G= "+nodo.getG()+
                        " F= "+nodo.getF()
               );
   }
}

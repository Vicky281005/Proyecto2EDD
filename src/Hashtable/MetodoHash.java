/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hashtable;

import EDD.Nodo;

/**
 *
 * @author NITRO V 15
 */
public class MetodoHash {
        int key;
        
        public ListaEnlazada[]hash= new ListaEnlazada[10];
        
        public MetodoHash(){
            for(int i=0; i<hash.length; i++){
                hash[i] = new ListaEnlazada();
                }
            }
        
          public void  Agregar(int valor ){
              Nodo<Integer> nuevo = new Nodo<>(valor); 
          }
        }

    


/*
 * Estruturas avançadas de dados - 2018/1 - Unisinos
 * Prof. Marcio Garcia Martins
 * Projeto: Tradutor inglês-Português utilizando uma árvore AVL
 * Classe: Classe para interagir com o usuário solicitando valores do teclado
 */
package br.unisinos.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Teclado
{
	 private static String stringLeitura;
     private static InputStreamReader inputStream = new InputStreamReader(System.in);
     private static BufferedReader reader = new BufferedReader(inputStream);

     //Rotina para ler um valor inteiro
     public static int lerInteiro() {   
    	 int temp = 0;
         
    	 try
         {
             stringLeitura = reader.readLine();
             temp = Integer.parseInt(stringLeitura);
         }
         catch (IOException e)
         {
             System.out.println("Erro de I/O: " + e);
         }
         catch (NumberFormatException e)
         {
             System.out.println("o valor digitado deve ser inteiro: " + e);
         }
    	 
         return (temp);
     }

     //Rotina para ler um valor inteiro mostrando uma mensagem antes
     public static int lerInteiro(String msg){   
         System.out.print(msg);
         return lerInteiro();
     }

     //Rotina para ler um valor double
     public static double lerDouble(){   
    	 double temp = 0;
         try
         {
             stringLeitura = reader.readLine();
             temp = Double.parseDouble(stringLeitura);
         }
         catch (IOException e)
         {
             System.out.println("Erro de I/O: " + e);
         }
         catch (NumberFormatException e)
         {
             System.out.println("o valor digitado deve ser número real: " + e);
         }
         return (temp);
     }

     //Rotina para ler um valor double mostrando uma mensagem antes
     public static double leDouble(String msg){       	 
         System.out.print(msg);
         return lerDouble();
     }

     //Rotina para ler um valor String
     public static String lerString(){   
    	 stringLeitura = "";
    	 
         try
         {
            stringLeitura = reader.readLine();
         }
         catch (IOException e)
         {
            System.out.println ("Erro de I/O: " + e);
         }
         return (stringLeitura);
     }

     //Rotina para ler um valor String mostrando uma mensagem antes
     public static String lerString(String msg){   
    	 System.out.print(msg);
    	 return lerString();
     }
     
     //Rotina para ler um valor char
     public static Character lerChar(){   
    	 stringLeitura = "";

         try
         {
            stringLeitura = reader.readLine();
         }
         catch (IOException e)
         {
            System.out.println ("Erro de I/O: " + e);
         }
         return (stringLeitura.charAt(0));
     }
     
     //Rotina para ler um valor char mostrando uma mensagem antes
     public static Character lerChar(String msg){   
         System.out.print(msg);
         return lerChar();
     }
}